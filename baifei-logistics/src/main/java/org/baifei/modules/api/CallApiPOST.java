package org.baifei.modules.api;

import com.alibaba.fastjson.JSONObject;
import org.baifei.common.util.*;
import org.baifei.modules.entity.Order0;
import org.baifei.modules.entity.Orderitem0;
import org.baifei.modules.entity.Orderplus0;
import org.baifei.modules.entity.request.chinapost.step1.ChinapostRequestStep1;
import org.baifei.modules.entity.request.chinapost.step1.Order;
import org.baifei.modules.entity.request.chinapost.step2.Items;
import org.baifei.modules.entity.request.chinapost.step2.Receiver;
import org.baifei.modules.entity.request.chinapost.step2.Sender;
import org.baifei.modules.entity.request.chinapost.step2.ChinapostRequestStep2;
import org.baifei.modules.entity.response.chinapost.step1.ChinapostResponseStep1;
import org.baifei.modules.entity.response.chinapost.step2.ChinapostResponseStep2;
import org.baifei.modules.entity.response.common.DbLogisticschannel;
import org.baifei.modules.entity.response.common.DbMylogisticsaccount;
import org.baifei.modules.entity.response.common.DbMylogisticschannel;
import org.baifei.modules.entity.response.common.DbMylogisticsreturnaddress;
import org.baifei.modules.entity.request.common.PropertyJson;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import java.awt.font.TransformAttribute;
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
public class CallApiPOST {
    @Autowired
    private SSLClient sslClient;

    //条码分配接口2.1
    private String barCodeService="https://my.ems.com.cn/pcpErp-web/a/pcp/barCodesAssgine/barCodeService";

    //电商向信息平台发送订单接口2.2
    private String orderReceive="https://my.ems.com.cn/pcpErp-web/a/pcp/orderService/orderReceive";

    //创建订单返回订单号2.3
    private String OrderReceiveBack = "https://my.ems.com.cn/pcpErp-web/a/pcp/orderService/OrderReceiveBack";

    //下载标签2.4
    private String orderLabel = "https://my.ems.com.cn/pcpErp-web/a/pcp/surface/download";//标签地址

    //获取渠道
    private String channelsUrl = "https://my.ems.com.cn/pcpErp-web/a/pcp/businessDataService/getBusinessData?queryType=queryBusinessType";

    private String KEY_MD5 = "MD5";

    private String mailType = "BAIFEI";
    /*****************测试环境*************
     *
     *     private String barCodeService = "https://211.156.195.25:443/pcpErp-web/a/pcp/barCodesAssgine/barCodeService";
     *     private String orderReceive = "https://211.156.195.25:443/pcpErp-web/a/pcp/orderService/orderReceive";
     *     private String OrderReceiveBack = "https://211.156.195.25:443/pcpErp-web/a/pcp/orderService/OrderReceiveBack";
     *     private String orderLabel = "https://211.156.195.25:443/pcpErp-web/a/pcp/surface/download";
     *     //客户 ID
     *     private String ecCompanyId ="90000003465705";
     *     //密钥
     *     private String digestKey = "8nVV209U16ml9q63";
     *     //邮政的揽收机构号
     *     private String wh_code = "23803100";
     *     //电商标识,找群里的“中邮 API对接”这个人注册
     *     private String mailType ="BAIFEI";
     *
     * ************************************/


    //创建订单
    public TrackResultModel runStep1(TrackModel trackModel){
        TrackResultModel trackResultModel = new TrackResultModel();
        try{
            ResultCode resultCode = new ResultCode();
            Map<String, Object> header = new HashMap<>();
            header.put("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");

            PropertyJson propertyJson = trackModel.getPropertyJson();
            //请求数据
            Map params = null;
            if(propertyJson!=null){
                String senddataflag =propertyJson.getSenddataflag();
                //是否主动推送数据1推送0不推送(状态6，第二步推送)
                if("1".equals(senddataflag)){
                    params = getDataStep2(trackModel);
                    resultCode = sslClient.doPostHttpClient(OrderReceiveBack,header,params,null,60*1000);
                    if(resultCode.getAck()==0){
                        ChinapostResponseStep2 result = JSONObject.parseObject(resultCode.getData().toString(), ChinapostResponseStep2.class);
                        if("true".equals(result.getSuccess())){
                            trackResultModel.setFlag(3);
                            trackResultModel.setTrackNumber(result.getWaybillNo());
                            trackResultModel.setDescr("获取运单号成功："+result.getWaybillNo());
                        }else{
                            trackResultModel.setFlag(98);
                            trackResultModel.setDescr(result.getReason());
                        }
                    }
                }else{
                    params = getDataStep1(trackModel);
                    resultCode = sslClient.doPostHttpClient(barCodeService,header,params,null,60*1000);

                    if(resultCode.getAck()==0){
                        ChinapostResponseStep1 result = JSONObject.parseObject(resultCode.getData().toString(), ChinapostResponseStep1.class);
                        if("true".equals(result.getReturn_success())){
                            trackResultModel.setTrackNumber(result.getBarCodeList().get(0).getBar_code());
                            trackResultModel.setFlag(2);
                            trackResultModel.setDescr("获取运单号成功:"+result.getBarCodeList().get(0).getBar_code());
                        }else{
                            trackResultModel.setFlag(98);
                            trackResultModel.setDescr(result.getReturn_reason());
                        }
                    }else{
                        trackResultModel.setFlag(98);
                        trackResultModel.setDescr("调用接口异常"+resultCode.getMsg());
                    }
                }
            }


        }catch (Exception e){
            trackResultModel.setFlag(98);
            trackResultModel.setDescr("调用接口异常"+e.getMessage());
        }
        return trackResultModel;
    }
    //推送订单
    public TrackResultModel runStep2(TrackModel trackModel){
        TrackResultModel trackResultModel = new TrackResultModel();
        try{

            String time= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            ResultCode resultCode = new ResultCode();
            Map<String, Object> header = new HashMap<>();

            header.put("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");

            //请求数据
            Map params = getDataStep2(trackModel);

            resultCode = sslClient.doPostHttpClient(orderReceive,header,params,null,60*1000);

            if(resultCode.getAck()==0){
                ChinapostResponseStep2 result = JSONObject.parseObject(resultCode.getData().toString(), ChinapostResponseStep2.class);
                if("true".equals(result.getSuccess())){
                    trackResultModel.setFlag(3);
                    trackResultModel.setDescr("获取运单号成功");
                }else{
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr(result.getMsg());
                }
            }
        }catch (Exception e){
            trackResultModel.setFlag(98);
            trackResultModel.setDescr("调用接口异常"+e.getMessage());
        }

        return trackResultModel;
    }

    //下载订单
    public TrackResultModel runStep3(TrackModel trackModel){
        TrackResultModel trackResultModel = new TrackResultModel();
        try{

            String postJson = "";
            Map params = new HashMap();
            ResultCode resultCode = new ResultCode();
            Map<String, Object> header = new HashMap<>();
            Order0 order = trackModel.getOrder0();
            DbMylogisticsaccount dbMylogisticsaccount = trackModel.getMylogisticsaccount();

            header.put("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");

            /****************拼装数据******start***************/

            postJson=order.getTracknumber();

            /****************拼装数据******end***************/


            //获得签名
            String digestSend ="";
            try {
                digestSend = encryptBASE64(encryptMD5((postJson +
                        dbMylogisticsaccount.getToken()).getBytes()));
            } catch (Exception e) {
                e.printStackTrace();
            }


            params.put("barCode",postJson);
            params.put("dataDigest",digestSend);
            params.put("msg_type","B2C_TRADE");
            params.put("ecCompanyId",dbMylogisticsaccount.getRefreshToken());
            params.put("version","1.0");

            resultCode = PdfUtil.doPostHttpClient(orderLabel,header,params,null,60*1000,ConstantConfig.PDF_ABSOLUTE_PATH,order.getPlatformorderid());

            if(resultCode.getAck()==0){
                trackResultModel.setFlag(3);
                trackResultModel.setDescr("下载订单成功!");
            }else{
                trackResultModel.setFlag(98);
                trackResultModel.setDescr("调用接口异常"+resultCode.getMsg());
            }
        }catch (Exception e){
            trackResultModel.setFlag(98);
            trackResultModel.setDescr("调用接口异常"+e.getMessage());
        }
        return trackResultModel;
    }

    //创建订单数据1
    /***
     *    order0  订单信息
     *    dbLogisticschannel 物流渠道
     *
     *
     * **/
    public  Map getDataStep1(TrackModel trackModel){
        /****************拼装数据******start***************/
        String postJson = "";
        Map params = new HashMap();
        String time= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        ChinapostRequestStep1 chinapostRequestStep1 = new ChinapostRequestStep1();
        DbLogisticschannel dbLogisticschannel = trackModel.getDbLogisticschannel();
        Order0 order0 = trackModel.getOrder0();
        DbMylogisticsaccount dbMylogisticsaccount = trackModel.getMylogisticsaccount();

        List<Order> tracknumberList = new ArrayList<>();
        Order order = new Order();
        order.setEventTime(time);
        order.setEcCompanyId(dbMylogisticsaccount.getRefreshToken());
        order.setWhCode(dbMylogisticsaccount.getAccessKey());
        order.setLogisticsOrderId(Util.isCheckNull(order0.getPlatformorderid()));
        order.setLogisticsCompany("POST");
        order.setLogisticsBiz(dbLogisticschannel.getCode());
        order.setMailType(mailType);
        order.setFaceType("1");
        order.setRcountry(Util.isCheckNull(order0.getCountrycode()));
        tracknumberList.add(order);


        chinapostRequestStep1.setOrder(tracknumberList);
        postJson = JSONObject.toJSONString(chinapostRequestStep1);
        //获得签名
        String digestSend ="";
        try {
            digestSend = encryptBASE64(encryptMD5((postJson +
                    dbMylogisticsaccount.getToken()).getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }


        params.put("logisticsOrder",postJson);
        params.put("data_digest",digestSend);
        params.put("msg_type","B2C_TRADE");
        params.put("ecCompanyId",dbMylogisticsaccount);
        params.put("version","1.0");
        return  params;
        /****************拼装数据******end***************/
    }
    //创建订单推送数据
    /***
     *    order0  订单信息
     *    orderplus0 订单买家信息
     *    orderitem0List 订单详情
     *    dbMylogisticsreturnaddress 回邮地址
     *    dbLogisticschannel 物流渠道
     *    dbMylogisticschannel 物流方式
     *
     *
     * **/
    public Map getDataStep2(TrackModel trackModel){
        /****************拼装数据******start***************/
        String postJson = "";
        Map params = new HashMap();
        String time= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Order0 order0 = trackModel.getOrder0();
        Orderplus0 orderplus0 = trackModel.getOrderplus0();
        List<Orderitem0> orderitem0List = trackModel.getOrderitem0();
        DbMylogisticsreturnaddress dbMylogisticsreturnaddress = trackModel.getDbMylogisticsreturnaddress();
        DbMylogisticschannel dbMylogisticschannel =trackModel.getDbMylogisticschannel();
        DbLogisticschannel dbLogisticschannel = trackModel.getDbLogisticschannel();
        DbMylogisticsaccount dbMylogisticsaccount= trackModel.getMylogisticsaccount();
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

        ChinapostRequestStep2 tracknumberOrder = new ChinapostRequestStep2();
        //订单接入时间
        tracknumberOrder.setCreated_time(time);
        //协议客户代码
        tracknumberOrder.setSender_no(dbMylogisticsaccount.getRefreshToken());
        //电商标识
        tracknumberOrder.setMailType(mailType);
        //用户揽收机构编号
        tracknumberOrder.setWh_code(dbMylogisticsaccount.getAccessKey());
        //物流订单号
        tracknumberOrder.setLogistics_order_no(Util.isCheckNull(order0.getPlatformorderid()));
        //运单号（即前一个接口获取的运单号）
        tracknumberOrder.setWaybill_no(Util.isCheckNull(order0.getTracknumber1()));
        //业务产品代码
        tracknumberOrder.setBiz_product_no(Util.isCheckNull(dbLogisticschannel.getCode()));
        //邮件重量
        tracknumberOrder.setWeight(Util.isCheckNull(order0.getOrderweight()));

        double total_vale=0,total_weight=0,single_value=0;
        for(Orderitem0 orderitem0:orderitem0List){
            total_vale+=Util.isCheckNull(orderitem0.getDeclareValue());
            total_weight+=Util.isCheckNull(orderitem0.getTestWeight())/1000;
        }
        //内件总重量
        tracknumberOrder.setContents_total_weight(total_weight);
        //内件总价值
        if(total_vale>maxDeclareFeeOrigin){
            total_vale=maxDeclareFeeOrigin;
            single_value = total_vale/order0.getItemquantity();
        }else if(total_vale<declareFeeOrigin){
            total_vale=declareFeeOrigin;
            single_value = total_vale/order0.getItemquantity();
        }
        tracknumberOrder.setContents_total_value(Util.isCheckNull(total_vale));

        //申报信息来源 1、个人申报；2:企业申报；3:个人税款复核；
        tracknumberOrder.setDeclare_source("2");
        //申报类别 1:物品 2：货物
        tracknumberOrder.setDeclare_type("1");
        //申报币制代码
        tracknumberOrder.setDeclare_curr_code("USD");
        //预报关：0-无预报关信息
        // 1-有预报关信息
        tracknumberOrder.setForecastshut("0");
        //9610 标识 1:是 2：否；目前填 2：否
        tracknumberOrder.setMail_sign("2");
        //寄件人信息
        Sender sender = new Sender();
        //用户姓名
        sender.setName(Util.isCheckNull(dbMylogisticsreturnaddress.getName()));
        //用户详细地址
        sender.setAddress(Util.isCheckNull(dbMylogisticsreturnaddress.getStreetEn()));
        //用户邮编
        sender.setPost_code(Util.isCheckNull(dbMylogisticsreturnaddress.getPostcode()));
        //用户电话
        sender.setPhone(Util.isCheckNull(dbMylogisticsreturnaddress.getPhone()));
        //用户移动电话
        sender.setMobile(Util.isCheckNull(dbMylogisticsreturnaddress.getPhone()));
//            sender.setPhone("8613832174455");
//            //用户移动电话
//            sender.setMobile("8613832174455");
        //用户所在国家
        sender.setNation(Util.isCheckNull(order0.getCountrycode()));
        //用户所在省（洲）
        sender.setProvince(Util.isCheckNull(dbMylogisticsreturnaddress.getProvinceEn()));
        //用户所在城市
        sender.setCity(Util.isCheckNull(dbMylogisticsreturnaddress.getCityEn()));
        //用户所在区
        sender.setCounty(Util.isCheckNull(dbMylogisticsreturnaddress.getAreaEn()));
        //用户email
        sender.setEmail(Util.isCheckNull(dbMylogisticsreturnaddress.getEmail()));
        //用户联系人
        sender.setLinker(Util.isCheckNull(dbMylogisticsreturnaddress.getName()));

        tracknumberOrder.setSender(sender);
        //收件人信息
        Receiver receiver = new Receiver();
        //用户姓名
        receiver.setName(Util.isCheckNull(order0.getBuyername()));
        //用户详细地址
        receiver.setAddress(Util.isCheckNull(orderplus0.getStreet1()));
        //用户邮编
        receiver.setPost_code(Util.isCheckNull(orderplus0.getPostcode()));
        //用户电话
        receiver.setPhone(Util.isCheckNull(orderplus0.getPhone1()));
        //用户移动电话
        receiver.setMobile(Util.isCheckNull(orderplus0.getPhone1()));
        //用户所在国家
        receiver.setNation(Util.isCheckNull(order0.getCountrycode()));
        //用户所在省（洲）
        receiver.setProvince(Util.isCheckNull(orderplus0.getProvince()));
        //用户所在城市
        receiver.setCity(Util.isCheckNull(orderplus0.getCity()));
        //用户所在区
        receiver.setCounty(Util.isCheckNull(orderplus0.getDistrict()));
        //用户email
        receiver.setEmail(Util.isCheckNull(orderplus0.getEmail()));
        //用户联系人
        receiver.setLinker(Util.isCheckNull(order0.getBuyername()));

        tracknumberOrder.setReceiver(receiver);
        //商品信息
        List<Items> items = new ArrayList<Items>();

        for(Orderitem0 orderitem0:orderitem0List){
            Items item = new Items();
            //商品 id
            item.setCargo_no(Util.isCheckNull(orderitem0.getSkuname()));
            //商品名称（中文）
            item.setCargo_name(Util.isCheckNull(orderitem0.getDeclareCnName()));
            //商品名称（英文）
            item.setCargo_name_en(Util.isCheckNull(orderitem0.getDeclareEnName()));
            //商品类型名称（中文）
            item.setCargo_type_name(Util.isCheckNull(orderitem0.getDeclareCnName()));
            //商品数量
            item.setCargo_quantity(Util.isCheckNull(orderitem0.getQuantity()));
            if(single_value!=0){
                //商品单价
                item.setCargo_value(single_value);
                //申报价值 默认美元，申报价值可以和商品单价一样
                item.setCost(single_value);
            }else{
                //商品单价
                item.setCargo_value(orderitem0.getDeclareValue());
                //申报价值 默认美元，申报价值可以和商品单价一样
                item.setCost(orderitem0.getDeclareValue());
            }
            //商品申报币制
            item.setCargo_currency("USD");
            //商品重量
            item.setCarogo_weight(Util.isCheckNull(orderitem0.getTestWeight())/1000);
            //商品描述（内件成分）
            item.setCargo_description(Util.isCheckNull(orderitem0.getDeclareEnName()));
            //计量单位 默认（个）
            item.setUnit("个");

            items.add(item);

        };
        tracknumberOrder.setItems(items);

        postJson = JSONObject.toJSONString(tracknumberOrder);
        //获得签名
        String digestSend ="";
        try {
            digestSend = encryptBASE64(encryptMD5((postJson +
                    dbMylogisticsaccount.getToken()).getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }


        params.put("logistics_interface",postJson);
        params.put("data_digest",digestSend);
        params.put("msg_type","B2C_TRADE");
        params.put("ecCompanyId",dbMylogisticsaccount.getRefreshToken());
        params.put("data_type","JSON");

        return params;
        /****************拼装数据******end***************/
    }

    //获取渠道
    public TrackResultModel getChannels(){
        TrackResultModel resultModel = new TrackResultModel();
        Map params = new HashMap();
        params.put("queryType","queryBusinessType");
        ResultCode resultCode = sslClient.doPostHttpClient(channelsUrl,null,params,null,60*1000);
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
        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data);
        return md5.digest();
    }

    public static  void main(String args[]){
        CallApiPOST callApiPOST = new CallApiPOST();

        Order0 order0 = new Order0();
        order0.setPlatformorderid("9999999");
        order0.setCountrycode("US");
        TrackModel trackModel = new TrackModel();
        trackModel.setOrder0(order0);
        callApiPOST.runStep1(trackModel);

    }
}
