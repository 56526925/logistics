package org.baifei.modules.api;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.weaver.ast.Or;
import org.baifei.common.util.*;
import org.baifei.modules.entity.Order0;
import org.baifei.modules.entity.Orderitem0;
import org.baifei.modules.entity.Orderplus0;
import org.baifei.modules.entity.request.chinapost.step1.ChinapostRequestStep1;
import org.baifei.modules.entity.request.chinapost.step1.Order;
import org.baifei.modules.entity.request.chinapost.step2.ChinapostRequestStep2;
import org.baifei.modules.entity.request.chinapost.step2.Items;
import org.baifei.modules.entity.request.chinapost.step2.Receiver;
import org.baifei.modules.entity.request.chinapost.step2.Sender;
import org.baifei.modules.entity.request.common.PropertyJson;
import org.baifei.modules.entity.request.ft.*;
import org.baifei.modules.entity.response.chinapost.step1.ChinapostResponseStep1;
import org.baifei.modules.entity.response.chinapost.step2.ChinapostResponseStep2;
import org.baifei.modules.entity.response.common.DbLogisticschannel;
import org.baifei.modules.entity.response.common.DbMylogisticsaccount;
import org.baifei.modules.entity.response.common.DbMylogisticschannel;
import org.baifei.modules.entity.response.common.DbMylogisticsreturnaddress;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.util.RestTemplateUtil;
import org.baifei.modules.util.ResulstCodeWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/************
 *
 *
 *
 *          中邮api
 *
 *
 */
@Component
public class CallApiFT {
    @Autowired
    private SSLClient sslClient;
    @Autowired
    private RestTemplateUtil restTemplateUtil;
    private String url="http://122.51.68.40:8008";

    //创建订单
    private String createOrder = "http://exorderwebapi.flytcloud.com/api/OrderSyn/ErpUploadOrder";
    //面单授权
    private String orderLabelToken = "http://exapi.flytcloud.com/api/auth/Authorization/GetAccessToken";
    //获取面单
    private String orderLabel = "http://exapi.flytcloud.com/api/label/LabelProvider/GetLabelFromErp";
    //线上发货
    private String createOrderOnline = "http://exorderwebapi.flytcloud.com/api/OrderSyn/ErpOnlineOrderUpload";
    //获取渠道
    private String channelUrl = "http://exorderwebapi.flytcloud.com/BaseInfo/GetPostTypes";

    //创建订单
    public TrackResultModel runStep1(TrackModel trackModel){
        TrackResultModel trackResultModel = new TrackResultModel();
        try{
            ResultCode resultCode = new ResultCode();
            Map<String, Object> header = new HashMap<>();
            header.put("Content-Type","aapplication/json");

            //请求数据
            String  requestJson = getDataStep1(trackModel);

            resultCode = sslClient.doPostHttpClient(createOrder,header,null,requestJson,60*1000);
            if(0==resultCode.getAck()){
                JSONObject result = JSONObject.parseObject(resultCode.getData().toString());
                if("true".equals(result.getString("Success"))){
                    String  tracknumber = result.getJSONArray("ErpSuccessOrders").getJSONObject(0).getString("TraceId");
                    String error = result.getJSONArray("ErpSuccessOrders").getJSONObject(0).getString("Remark");
                    if(!Util.isNull(tracknumber)){
                        trackResultModel.setTrackNumber(tracknumber);
                        trackResultModel.setFlag(3);
                        trackResultModel.setDescr("调用接口成功，返回订单号:"+tracknumber);
                    }else{
                        trackResultModel.setFlag(98);
                        trackResultModel.setDescr("调用接口成功，未获取到订单号，"+error);
                    }
                }else{
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr("调用接口失败，"+result.getString("Remark"));
                }
            }else{
                trackResultModel.setFlag(98);
                trackResultModel.setDescr("调用接口失败，"+resultCode.getMsg());
            }
        }catch (Exception e){
            trackResultModel.setFlag(98);
            trackResultModel.setDescr("调用接口异常"+e.getMessage());
        }
        return trackResultModel;
    }
    //下载订单
    public TrackResultModel runStep2(TrackModel trackModel){
        Map<String, Object> header = new HashMap<>();
        TrackResultModel trackResultModel = new TrackResultModel();
        try{
            String time= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            ResultCode resultCode = new ResultCode();

            DbMylogisticsaccount mylogisticsaccount = trackModel.getMylogisticsaccount();

            header.put("token",mylogisticsaccount.getRefreshToken());
            header.put("Content-Type", "application/json");
            Order0 order0 =trackModel.getOrder0();
            String requestJson ="{\"trackNumber\":\""+order0.getTracknumber()+"\"}";

            requestJson = "{" +
                    "\"OrderIdlst\": [\""+order0.getTracknumber()+"\"]," +
                    "\"Format\": \"0\"" +
                    "}";

            resultCode = sslClient.doPostHttpClient(orderLabel,header,null,requestJson,60*1000);

            if(resultCode.getAck()==0){
               JSONObject result = JSONObject.parseObject(resultCode.getData().toString());
                if("0".equals(result.getString("Status"))){
                    if(!Util.isNull(result.getString("data"))){
                        PdfUtil.bytetoFile(order0.getPlatformorderid(),result.getJSONObject("data").getJSONArray("Label").getString(0).getBytes());
                        trackResultModel.setFlag(3);
                        trackResultModel.setDescr("下载订单成功");
                    }else{
                        trackResultModel.setFlag(98);
                        trackResultModel.setDescr(result.getString("ErrMsg"));
                    }
                }else{
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr(result.getString("ErrMsg"));
                }
            }
        }catch (Exception e){
            trackResultModel.setFlag(98);
            trackResultModel.setDescr("调用接口异常"+e.getMessage());
        }
        return trackResultModel;
    }
    //刷新令牌
    public TrackResultModel flushToken(){
        Map<String, Object> header = new HashMap<>();
        TrackResultModel trackResultModel = new TrackResultModel();
        header.put("Content-Type", "application/json; charset=utf-8");
        ResultCode resultCode = new ResultCode();
        //请求数据
        //用户名： beifei  密码：%KjCgwPZ8j
        String requestTokenJson="{" +
                "\"grant_type\": \"password\"," +
                "\"username\": \"beifei\"," +
                "\"password\": \"888981E00C997885A4C153AFA03D0696\"" +
                "}";//请求数据  格式json

        resultCode = sslClient.doPostHttpClient(orderLabelToken,header,null,requestTokenJson,60*1000);
        if(resultCode.getAck()==0){
            JSONObject result = JSONObject.parseObject(resultCode.getData().toString());
            DbMylogisticsaccount mylogisticsaccount = new DbMylogisticsaccount();
            mylogisticsaccount.setRefreshToken(result.getString("access_token"));
            Calendar nowTime = Calendar.getInstance();
            nowTime.add(Calendar.SECOND,Integer.parseInt(result.getString("expires_in")));
            mylogisticsaccount.setExpireTime(nowTime.getTime());
            mylogisticsaccount.setId(999);
            ResulstCodeWeb<List<DbMylogisticsaccount>> resultAccount = restTemplateUtil.getDataListForPost(DbMylogisticsaccount.class,mylogisticsaccount,"",url+"/syncDbMyLogisticsAccount");
            if(resultAccount.getAck()==0){
                trackResultModel.setFlag(0);
                trackResultModel.setDescr("刷新令牌成功");
            }else{
                trackResultModel.setFlag(98);
                trackResultModel.setDescr("刷新令牌失败"+resultAccount.getMsg());
            }
        }else{
            trackResultModel.setFlag(98);
            trackResultModel.setDescr("刷新令牌失败"+resultCode.getMsg());
        }
        return  trackResultModel;
    }

    //线上订单
    public TrackResultModel runStep3(TrackModel trackModel){
        TrackResultModel trackResultModel = new TrackResultModel();
        try{
            ResultCode resultCode = new ResultCode();
            Map<String, Object> header = new HashMap<>();
            header.put("Content-Type","aapplication/json");
            DbMylogisticsaccount mylogisticsaccount = trackModel.getMylogisticsaccount();
            Order0 order0 = trackModel.getOrder0();
            DbLogisticschannel logisticschannel = trackModel.getDbLogisticschannel();

            //请求数据
            String  requestJson = "{\n" +
                    "    \"UAccount\": \""+mylogisticsaccount.getName()+"\",\n" +
                    "    \"Password\": \""+mylogisticsaccount.getPassword()+"\",\n" +
                    "    \"Token\": \""+mylogisticsaccount.getToken()+"\",\n" +
                    "    \"OrderList\": [{\n" +
                    "        \"CiId\": \""+order0.getCountrycode()+"\",\n" +
                    "        \"PtId\": \""+logisticschannel.getCode()+"\",\n" +
                    "        \"SyncPlatformFlag\": \"flyt.logistics.beifei\",\n" +
                    "        \"TraceId\": \""+order0.getTracknumber()+"\"\n" +
                    "    }]\n" +
                    "}\n";

            resultCode = sslClient.doPostHttpClient(createOrderOnline,header,null,requestJson,60*1000);
            if(0==resultCode.getAck()){
                JSONObject result = JSONObject.parseObject(resultCode.getData().toString());
                if("true".equals(result.getString("Success"))){
                    String  tracknumber = result.getJSONArray("ErpSuccessOrders").getJSONObject(0).getString("TraceId");
                    String error = result.getJSONArray("ErpSuccessOrders").getJSONObject(0).getString("Remark");
                    if(!Util.isNull(tracknumber)){
                        trackResultModel.setTrackNumber(tracknumber);
                        trackResultModel.setFlag(6);
                        trackResultModel.setDescr("调用接口成功，返回订单号:"+tracknumber);
                    }else{
                        trackResultModel.setFlag(98);
                        trackResultModel.setDescr("调用接口成功，未获取到订单号，"+error);
                    }
                }else{
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr("调用接口失败，"+result.getString("Remark"));
                }
            }else{
                trackResultModel.setFlag(98);
                trackResultModel.setDescr("调用接口失败，"+resultCode.getMsg());
            }
        }catch (Exception e){
            trackResultModel.setFlag(98);
            trackResultModel.setDescr("调用接口异常"+e.getMessage());
        }
        return trackResultModel;
    }


    //创建订单数据1
    public  String getDataStep1(TrackModel trackModel){
        /****************拼装数据******start***************/
        String time= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        DbLogisticschannel dbLogisticschannel = trackModel.getDbLogisticschannel();
        DbMylogisticschannel dbMylogisticschannel = trackModel.getDbMylogisticschannel();
        Order0 order0 = trackModel.getOrder0();
        Orderplus0 orderplus0 = trackModel.getOrderplus0();
        List<Orderitem0> orderitem0List = trackModel.getOrderitem0();
        DbMylogisticsaccount dbMylogisticsaccount = trackModel.getMylogisticsaccount();
        PropertyJson propertyJson = trackModel.getPropertyJson();

        //最小申报价格
        double declareFeeOrigin = dbMylogisticschannel.getDeclareFeeOrigin();
        if(declareFeeOrigin==0){
            declareFeeOrigin =1;
        }
        //最大申报价格
        double maxDeclareFeeOrigin = dbMylogisticschannel.getMaxDeclareFeeOrigin();
        if(maxDeclareFeeOrigin==0){
            declareFeeOrigin =5;
        }
        double declared_value = 0, cargo_total_weight = 0,single_value = 0;
        for(Orderitem0 orderitem0 :orderitem0List) {
            declared_value += orderitem0.getDeclareValue();
            cargo_total_weight += orderitem0.getTestWeight();
        }
        cargo_total_weight=cargo_total_weight/1000000;
        //内件总价值
        if(declared_value>maxDeclareFeeOrigin){
            declared_value=maxDeclareFeeOrigin;
            single_value = declared_value/order0.getItemquantity();
        }else if(declared_value<declareFeeOrigin){
            declared_value=declareFeeOrigin;
            single_value = declared_value/order0.getItemquantity();
        }

        FtRequestStep1 requestStep1 = new FtRequestStep1();
        //令牌
        requestStep1.setToken(dbMylogisticsaccount.getToken());
        //物流账号
        requestStep1.setUAccount(dbMylogisticsaccount.getName());
        //密码
        requestStep1.setPassword(dbMylogisticsaccount.getPassword());
        //订单信息集合
        List<FtOrderList> orderList = new ArrayList<>();

        FtOrderList order = new FtOrderList();
        //地址1
        order.setAddress1(orderplus0.getStreet1());
        //第三方平台订单号
        order.setApiOrderId(order0.getPlatformorderid());
        //城市
        order.setCity(orderplus0.getCity());
        //国家/地区简码
        order.setCiId(order0.getCountrycode());
        //州/省
        order.setCounty(orderplus0.getProvince());
        //包装类型（1：信封，2：文件，3：包裹）(默认包裹)
        order.setPackType(3);
        //货运方式(邮递方式简码)
        order.setPtId(dbLogisticschannel.getCode());
        //收件人姓名
        order.setReceiverName(order0.getBuyername());
        //销售平台标识[0=默认(不分);1=ebay;2=amazon();3=aliexpress(速卖通);4=wish](ebay,amazon,aliexpress,wish平台订单必填，其余可不填)
        order.setSalesPlatformFlag(order0.getPlatformid());
        //订单同步平台标识(一般指第三方平台标识，格式类似：scb.logistics.flyt，具体可询问飞特技术人员)
        order.setSyncPlatformFlag("flyt.logistics.beifei");
        //邮编
        order.setZip(orderplus0.getPostcode());

        //订单明细集合
        List<FtOrderDetailList> orderDetailList = new ArrayList<>();

        //报关明细集合
        List<FtHaikwanDetialList> haikwanDetialList = new ArrayList<>();

        //预报重量明细集合
        List<FtOrderVolumeWeights> orderVolumeWeights = new ArrayList<>();

        for(Orderitem0 orderitem0 :orderitem0List){
            FtOrderDetailList orderDetail = new FtOrderDetailList();
            FtHaikwanDetialList haikwanDetial = new FtHaikwanDetialList();
            FtOrderVolumeWeights orderVolumeWeight = new FtOrderVolumeWeights();
            //物品id[ebay等销售平台订单必填]
            orderDetail.setItemId(orderitem0.getItemid());
            //物品名称
            orderDetail.setItemName(orderitem0.getDeclareEnName());
            //物品交易号[ebay订单必填]
            orderDetail.setItemTransactionId(orderitem0.getTransactionid());
            //销售平台订单号[销售平台订单必填]
            orderDetail.setOriginalPlatformOrderId(orderitem0.getSalesrecordnumber());
            //价格（单价）
            orderDetail.setPrice(single_value);
            //数量
            orderDetail.setQuantities(orderitem0.getQuantity());
            orderDetailList.add(orderDetail);

            //物品中文名称
            haikwanDetial.setItemCnName(orderitem0.getDeclareCnName());
            //物品英文名称
            haikwanDetial.setItemEnName(orderitem0.getDeclareEnName());
            //原产地（默认值：CN）
            haikwanDetial.setProducingArea("CN");
            //物品数量
            haikwanDetial.setQuantities(orderitem0.getQuantity());
            //报关单价
            haikwanDetial.setUnitPrice(single_value);
            //重量(kg)
            haikwanDetial.setWeight(orderitem0.getTestWeight()/1000000);
            //货币代码(默认为USD美元)
            haikwanDetial.setCCode("USD");
            haikwanDetialList.add(haikwanDetial);

            //订单号
            orderVolumeWeight.setOrderId(order0.getPlatformorderid());
            //分箱单号
            orderVolumeWeight.setPackageCode(order0.getPlatformorderid());
            //长
            orderVolumeWeight.setLength(1.0);
            //宽
            orderVolumeWeight.setWidth(1.0);
            //高
            orderVolumeWeight.setHeight(1.0);
            //称重重量
            orderVolumeWeight.setWeighingWeight(cargo_total_weight);
            //跟踪号
            //orderVolumeWeight.setTraceId("");
            //FBA单号
            //orderVolumeWeight.setFbaShipmentId("");
            orderVolumeWeights.add(orderVolumeWeight);
        }
        order.setOrderDetailList(orderDetailList);
        order.setHaikwanDetialList(haikwanDetialList);
        order.setOrderVolumeWeights(orderVolumeWeights);
        orderList.add(order);
        requestStep1.setOrderList(orderList);
        return  JSONObject.toJSONString(requestStep1);
        /****************拼装数据******end***************/
    }


    //获取渠道
    public TrackResultModel getChannels(){
        TrackResultModel resultModel = new TrackResultModel();
        ResultCode resultCode = sslClient.doGetHttpClient(channelUrl,null,60*1000);
        if(resultCode.getAck()==0){
            resultModel.setFlag(0);
            resultModel.setDescr(resultCode.getData().toString());
        }else{
            resultModel.setFlag(98);
            resultModel.setDescr("调用获取渠道接口失败");
        }
        return resultModel;
    }

    public String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    public  byte[] encryptMD5(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(data);
        return md5.digest();
    }

    public static  void main(String args[]){
        CallApiFT callApiPOST = new CallApiFT();

        Order0 order0 = new Order0();
        //F332292002290001
        order0.setTracknumber("F332292002290001");
        DbMylogisticsaccount mylogisticsaccount = new DbMylogisticsaccount();
        mylogisticsaccount.setRefreshToken("MjAyMDA2MTExNTE4MTQyMzQxQTA4NEE4QjE1OEI2NDkzRENGRTJFM0Y4QzJDOEIwNA==");
        TrackModel trackModel = new TrackModel();
        trackModel.setOrder0(order0);
        trackModel.setMylogisticsaccount(mylogisticsaccount);
        callApiPOST.runStep2(trackModel);

    }
}
