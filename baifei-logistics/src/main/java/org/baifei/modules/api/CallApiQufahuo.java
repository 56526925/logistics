package org.baifei.modules.api;

import com.alibaba.fastjson.JSONObject;
import org.baifei.common.util.*;
import org.baifei.modules.entity.Order0;
import org.baifei.modules.entity.Orderitem0;
import org.baifei.modules.entity.Orderplus0;
import org.baifei.modules.entity.request.common.PropertyJson;
import org.baifei.modules.entity.request.qufahuo.QufahuoDeclareInfos;
import org.baifei.modules.entity.request.qufahuo.QufahuoRecipient;
import org.baifei.modules.entity.request.qufahuo.QufahuoRequestStep1;
import org.baifei.modules.entity.request.qufahuo.QufahuoSender;
import org.baifei.modules.entity.response.common.DbLogisticschannel;
import org.baifei.modules.entity.response.common.DbMylogisticschannel;
import org.baifei.modules.entity.response.common.DbMylogisticsreturnaddress;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/************
 *
 *
 *
 *          去发货api
 *
 *
 */
@Component
public class CallApiQufahuo {
    @Autowired
    private SSLClient sslClient;

    private String url_channel="http://116.7.251.49:9696/api/order/getLogisticsChannel";//获取渠道www.qfh56.com:9696/api

    private String url_order = "http://116.7.251.49:9696/api/order/createOrder";//创建订单

    private String url_order_callback = "http://116.7.251.49:9696/api/order/orderCallback";

    //private String url_token = "http://exapi.flytcloud.com/api/auth/Authorization/printOrder";//获取令牌

    private String url_label = "http://116.7.251.49:9696/api/order/printOrder";//标签接口；

    //创建订单
    public TrackResultModel runStep1(TrackModel trackModel){
        TrackResultModel trackResultModel = new TrackResultModel();
        String token  = trackModel.getMylogisticsaccount().getToken();
        try{
            ResultCode resultCode = new ResultCode();
            Map header = new HashMap();
            header.put("Content-Type","application/json;charset=utf-8");
            header.put("userToken",token);

            DbLogisticschannel dbLogisticschannel = trackModel.getDbLogisticschannel();
            Order0 order0 = trackModel.getOrder0();
            Orderplus0 orderplus0 = trackModel.getOrderplus0();
            String channelCode= Util.isCheckNull(dbLogisticschannel.getCode());
            String orderId = Util.isCheckNull(order0.getPlatformorderid());
            String buyername = Util.isCheckNull(order0.getBuyername());
            String countryCode = Util.isCheckNull(order0.getCountrycode());
            String province = Util.isCheckNull(orderplus0.getProvince());
            String city = Util.isCheckNull(orderplus0.getCity());
            String address = Util.isCheckNull(orderplus0.getStreet1());
            String address2 = Util.isCheckNull(orderplus0.getStreet2());
            String address3 = Util.isCheckNull(orderplus0.getStreet2());
            String telNo = Util.isCheckNull(orderplus0.getPhone2());
            String mobile = Util.isCheckNull(orderplus0.getPhone1());
            String zip = Util.isCheckNull(orderplus0.getPostcode());

            String singStr= token+channelCode+orderId+buyername+countryCode+province+city+address+address2+address3+telNo+mobile+zip;



            String requestXml = getDataStep1(trackModel);
            header.put("sign",Util.getMD5(singStr).toUpperCase());

            resultCode = sslClient.doPostHttpClient(url_order,header,null,requestXml,60*1000);
            if(resultCode.getAck()==0){
               JSONObject result = JSONObject.parseObject(resultCode.getData().toString());
                if("true".equals(result.getString("success"))){
                    String isCallBack = result.getString("isCallBack");
                    if("0".equals(isCallBack)){
                        trackResultModel.setTrackNumber(result.getString("logistics_no"));
                        trackResultModel.setFlag(3);
                        trackResultModel.setDescr("获取订单号成,订单号："+result.getString("logistics_no"));
                    }else{
                        trackResultModel.setFlag(2);
                        trackResultModel.setDescr("调用成功，稍后生成订单号");
                    }
                }else{
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr("调用接口失败"+result.getString("msg"));
                }
            }else{
                trackResultModel.setFlag(98);
                trackResultModel.setDescr("调用接口失败"+resultCode.getMsg());
            }
        }catch (Exception e){
            e.printStackTrace();
            trackResultModel.setFlag(98);
            trackResultModel.setDescr("调用接口异常"+e.getMessage());
        }
        return trackResultModel;
    }


    public TrackResultModel runStep2(TrackModel trackModel){
        TrackResultModel trackResultModel = new TrackResultModel();
        String token  = trackModel.getMylogisticsaccount().getToken();
        Order0 order0 = trackModel.getOrder0();
        DbLogisticschannel dbLogisticschannel = trackModel.getDbLogisticschannel();
        String orderId = Util.isCheckNull(order0.getPlatformorderid());
        String logisticsId = Util.isCheckNull(dbLogisticschannel.getCode());
        try{
            ResultCode resultCode = new ResultCode();
            Map header = new HashMap();
            header.put("Content-Type","application/json;charset=utf-8");
            header.put("userToken",token);

            String requestXml = "{ \"orderNo\": \""+orderId+"\", \"logisticsId\": \""+logisticsId+"\" }";

            String singStr =token+logisticsId+orderId;

            header.put("sign",Util.getMD5(singStr).toUpperCase());

            resultCode = sslClient.doPostHttpClient(url_order_callback,header,null,requestXml,60*1000);
            if(resultCode.getAck()==0) {
                JSONObject result = JSONObject.parseObject(resultCode.getData().toString());
                if ("true".equals(result.getString("success"))) {
                        trackResultModel.setTrackNumber(result.getString("logistics_no"));
                        trackResultModel.setFlag(3);
                        trackResultModel.setDescr("获取订单号成,订单号：" + result.getString("logistics_no"));
                } else {
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr("调用接口失败" + result.getString("msg"));
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
        Order0 order0 = trackModel.getOrder0();
        DbLogisticschannel dbLogisticschannel = trackModel.getDbLogisticschannel();
        String token  = trackModel.getMylogisticsaccount().getToken();
        String orderId = Util.isCheckNull(order0.getPlatformorderid());
        String logisticsId = Util.isCheckNull(dbLogisticschannel.getCode());
        String tracknumber = Util.isCheckNull(order0.getTracknumber());
        String countryCode = Util.isCheckNull(order0.getCountrycode());

//        //测试
//        String token = "B9F032A65D114E8AFA9972E379781956";
//        String orderId = "3003826957726186";
//        String logisticsId = "ZPPXGPEF876";
//        String tracknumber = "UB965114457TW";
//        String countryCode = "UA";

        try{
            ResultCode resultCode = new ResultCode();
            Map header = new HashMap();
            header.put("Content-Type","application/json;charset=utf-8");
            header.put("userToken",token);

            String requestXml = "{" +
                    "    \"orderNo\": \""+orderId+"\"," +
                    "    \"logisticsId\": \""+logisticsId+"\"," +
                    "    \"trackNo\": \""+tracknumber+"\"," +
                    "    \"countryCode\": \""+countryCode+"\"" +
                    "}";
            header.put("sign",Util.getMD5(token+logisticsId+orderId+tracknumber).toUpperCase());


            resultCode = sslClient.doPostHttpClient(url_label,header,null,requestXml,60*1000);
            if(resultCode.getAck()==0){
                JSONObject result = JSONObject.parseObject(resultCode.getData().toString());
                if("true".equals(result.getString("success"))){
                    String pdfBase64 = result.getString("base64");
                    PdfUtil.decoderBase64File(pdfBase64,order0.getPlatformorderid());
                    trackResultModel.setFlag(3);
                    trackResultModel.setDescr("下载订单成功!");
                }else{
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr("下载订单接口异常"+result.getString("msg"));
                }
            }else{
                trackResultModel.setFlag(98);
                trackResultModel.setDescr("下载订单接口异常"+resultCode.getMsg());
            }
        }catch (Exception e){
            trackResultModel.setFlag(98);
            trackResultModel.setDescr("调用接口异常"+e.getMessage());
        }
        return trackResultModel;
    }

    public TrackResultModel getChannels(String token){
        TrackResultModel trackResultModel = new TrackResultModel();
        try{
            ResultCode resultCode = new ResultCode();
            Map header = new HashMap();
            header.put("Content-Type","application/json;charset=utf-8");
            header.put("userToken",token);

            resultCode = sslClient.doPostHttpClient(url_channel,header,null,null,60*1000);
            if(resultCode.getAck()==0){
                trackResultModel.setFlag(0);
                trackResultModel.setDescr(resultCode.getData().toString());
            }else{
                trackResultModel.setFlag(98);
                trackResultModel.setDescr("调用接口失败"+resultCode.getMsg());
            }
        }catch (Exception e){
            e.printStackTrace();
            trackResultModel.setFlag(98);
            trackResultModel.setDescr("调用接口异常"+e.getMessage());
        }
        return trackResultModel;
    }


    //创建订单数据1
    /***
     *    order0  订单信息
     *    orderplus0 买家信息
     *    orderitem0List 订单详情
     *    dbMylogisticschannel 物流方式 取最大最小申报值
     *
     *
     * **/
    public  String getDataStep1(TrackModel trackModel){
        /****************拼装数据******start***************/

        String postJson = "";

        String time= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        Order0 order0 = trackModel.getOrder0();
        Orderplus0 orderplus0 = trackModel.getOrderplus0();
        List<Orderitem0> orderitem0List = trackModel.getOrderitem0();
        DbMylogisticschannel dbMylogisticschannel = trackModel.getDbMylogisticschannel();
        DbLogisticschannel dbLogisticschannel = trackModel.getDbLogisticschannel();
        DbMylogisticsreturnaddress dbMylogisticsreturnaddress = trackModel.getDbMylogisticsreturnaddress();
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

        cargo_total_weight+=0.01;

        int itemType = 0;
        if("1".equals(propertyJson.getProducttype())){
            itemType = 0;
        }else if("2".equals(propertyJson.getProducttype())){
            itemType = 1;
        }else if("3".equals(propertyJson.getProducttype())){
            itemType = 2;
        }else if("4".equals(propertyJson.getProducttype())){
            itemType = 4;
        }

        QufahuoRequestStep1 requestStep1 = new QufahuoRequestStep1();
        //带电与否（0：否 ; 1：是）
        requestStep1.setCharged(Util.isCheckNull(propertyJson.getBatteryflag()));
        //物品类型（0、礼物；1、文 件;2、商业样本;3、回货品;4、 其他） 默认 0：礼物
        requestStep1.setItemType(itemType);
        //物流编码
        requestStep1.setLogisticsId(Util.isCheckNull(dbLogisticschannel.getCode()));
        //订单编号
        requestStep1.setOrderNo(Util.isCheckNull(order0.getPlatformorderid()));
        //包裹类型（0、文件；1、包 裹;）默认 1：包裹
        requestStep1.setPracelType(1);
        //预报重量（kg）
        requestStep1.setWeight(cargo_total_weight);

        //申报明细列表
        List<QufahuoDeclareInfos> declareInfosList = new ArrayList<>();
        for(Orderitem0 orderitem0 :orderitem0List){
            if(single_value==0){
                single_value = orderitem0.getDeclareValue();
            }
            QufahuoDeclareInfos qufahuoDeclareInfos = new QufahuoDeclareInfos();
            //申报币别（币别码）
            qufahuoDeclareInfos.setCurrency("USD");
            //物品中文名称
            qufahuoDeclareInfos.setNameCN(Util.isCheckNull(orderitem0.getDeclareCnName()));
            //物品英文名称
            qufahuoDeclareInfos.setNameEN(Util.isCheckNull(orderitem0.getDeclareEnName()));
            //申报物品价格
            qufahuoDeclareInfos.setPrice(single_value);
            //申报数量
            qufahuoDeclareInfos.setQty(orderitem0.getQuantity());
            //申报重量（kg）
            qufahuoDeclareInfos.setWeight(Util.isCheckNull(orderitem0.getTestWeight())/1000000);

            declareInfosList.add(qufahuoDeclareInfos);
        }

        requestStep1.setDeclareInfos(declareInfosList);

        //卖家信息表
        QufahuoSender qufahuoSender = new QufahuoSender();
        //地址 1
        qufahuoSender.setAddress1(Util.isCheckNull(dbMylogisticsreturnaddress.getStreetEn()));
        //城市
        qufahuoSender.setCity(Util.isCheckNull(dbMylogisticsreturnaddress.getCityEn()));
        // 国家二字简码
        qufahuoSender.setCountry(Util.isCheckNull(dbMylogisticsreturnaddress.getCountryCode()));
        //手机
        qufahuoSender.setMobile(Util.isCheckNull(dbMylogisticsreturnaddress.getPhone()));
        //姓名
        qufahuoSender.setName(Util.isCheckNull(dbMylogisticsreturnaddress.getContactEn()));
        //邮编
        qufahuoSender.setPostcode(Util.isCheckNull(dbMylogisticsreturnaddress.getPostcode()));
        //州省
        qufahuoSender.setProvince(Util.isCheckNull(dbMylogisticsreturnaddress.getProvinceEn()));
        //电话
        qufahuoSender.setTel(Util.isCheckNull(dbMylogisticsreturnaddress.getMobile()));

        requestStep1.setSender(qufahuoSender);

        //recipient 买家信息表
        QufahuoRecipient qufahuoRecipient = new QufahuoRecipient();
        //地址 1
        qufahuoRecipient.setAddress(Util.isCheckNull(orderplus0.getStreet1()));
        //地址 2
        qufahuoRecipient.setAddress2(Util.isCheckNull(orderplus0.getStreet2()));
        //地址 3
        qufahuoRecipient.setAddress3(Util.isCheckNull(orderplus0.getStreet2()));
        //城市
        qufahuoRecipient.setCity(Util.isCheckNull(orderplus0.getCity()));
        //联系人
        qufahuoRecipient.setContact_person(Util.isCheckNull(order0.getBuyername()));
        //国 家 二 字 简 码 code(ISO 3166-2)
        qufahuoRecipient.setCountry_code(Util.isCheckNull(order0.getCountrycode()));
        //手机
        qufahuoRecipient.setMobile_no(Util.isCheckNull(orderplus0.getPhone1()));
        //州省
        qufahuoRecipient.setProvince(Util.isCheckNull(orderplus0.getProvince()));
        //电话
        qufahuoRecipient.setTel_no(Util.isCheckNull(orderplus0.getPhone2()));
        //邮编
        qufahuoRecipient.setZip(Util.isCheckNull(orderplus0.getPostcode()));

        requestStep1.setRecipient(qufahuoRecipient);

        return  JSONObject.toJSONString(requestStep1);
        /****************拼装数据******end***************/
    }

    public static String digest(String toVerifyText, String encode) {
        String base64Str = null;
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(toVerifyText.getBytes(encode));
            byte[] md = md5.digest();

            base64Str = org.apache.commons.codec.binary.Base64.encodeBase64String(md);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return base64Str;
    }

}
