package org.baifei.modules.api;

import com.alibaba.fastjson.JSONObject;
import org.baifei.common.util.ConstantConfig;
import org.baifei.common.util.PdfUtil;
import org.baifei.common.util.ResultCode;
import org.baifei.common.util.Util;
import org.baifei.modules.entity.Order0;
import org.baifei.modules.entity.Orderitem0;
import org.baifei.modules.entity.Orderplus0;
import org.baifei.modules.entity.request.common.PropertyJson;
import org.baifei.modules.entity.request.common.RequestJson;
import org.baifei.modules.entity.request.ykd.YkdItem;
import org.baifei.modules.entity.request.ykd.YkdRequestStep1;
import org.baifei.modules.entity.response.common.*;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.webservice.haihehui.*;
import org.baifei.modules.webservice.ykd.EcSOAPStub;
import org.baifei.modules.webservice.ykd.Ec_ServiceLocator;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


/************
 *
 *
 *
 *          谷仓易可达api
 *
 *
 */
@Component
public class CallApiYKD {

//    private String appToken = "ee0b46b958b338dbbda4b6176c9ddb40";
//
//    private String appKey = "d3fa78fb1613cfd561320c895bf0a48d";

    //创建订单
    public TrackResultModel runStep1(TrackModel trackModel){
        TrackResultModel trackResultModel = new TrackResultModel();
        DbMylogisticsaccount mylogisticsaccount = trackModel.getMylogisticsaccount();
        try{

            String  paramsJson = getDataStep1(trackModel);

            EcSOAPStub binding = null;
            try {
                binding = (EcSOAPStub) new Ec_ServiceLocator().getEcSOAP();
            }catch (javax.xml.rpc.ServiceException jre) {

            }
            // Time out after a minute
            binding.setTimeout(60000);

            // Test operation
            String value = null;
            value = binding.callService(paramsJson, mylogisticsaccount.getToken(), mylogisticsaccount.getAccessKey(), "zh_CN", "createOrder");

            if(Util.isCheckJson(value)){
                JSONObject result = JSONObject.parseObject(value);
                if("Success".equals(result.getString("ask"))){
                    trackResultModel.setFlag(2);
                    trackResultModel.setTrackNumber1(result.getString("order_code"));
                    trackResultModel.setDescr("调用接口成功，返回系统单号:"+result.getString("order_code")+",稍后返回真实订单号");
                }else{
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr("调用接口失败"+result.getString("message"));
                }
            }else{
                trackResultModel.setFlag(98);
                trackResultModel.setDescr("调用接口失败,返回结果错误"+value);
            }
        }catch (Exception e){
            e.printStackTrace();
            trackResultModel.setFlag(98);
            trackResultModel.setDescr("调用接口异常"+e.getMessage());
        }
        return trackResultModel;
    }

    //获取真实订单号
    public TrackResultModel runStep2(TrackModel trackModel){
        TrackResultModel trackResultModel = new TrackResultModel();
        DbMylogisticsaccount mylogisticsaccount = trackModel.getMylogisticsaccount();
        Order0 order0 = trackModel.getOrder0();
        //测试
//        order0.setPlatformorderid("00423110531112");
//        mylogisticsaccount.setToken("ee0b46b958b338dbbda4b6176c9ddb40");
//        mylogisticsaccount.setAccessKey("d3fa78fb1613cfd561320c895bf0a48d");
        try{

            String  paramsJson = "{\"reference_no\":\""+order0.getPlatformorderid()+"\"}";

            EcSOAPStub binding = null;
            try {
                binding = (EcSOAPStub) new Ec_ServiceLocator().getEcSOAP();
            }catch (javax.xml.rpc.ServiceException jre) {

            }
            // Time out after a minute
            binding.setTimeout(60000);

            // Test operation
            String value = null;
            value = binding.callService(paramsJson, mylogisticsaccount.getToken(), mylogisticsaccount.getAccessKey(), "zh_CN", "getOrderByRefCode");

            if(Util.isCheckJson(value)){
                JSONObject result = JSONObject.parseObject(value);
                if("Success".equals(result.getString("ask"))){
                    String tracknumber = result.getJSONObject("data").getString("tracking_no");
                    trackResultModel.setFlag(3);
                    trackResultModel.setTrackNumber(tracknumber);
                    trackResultModel.setDescr("调用接口成功，返回订单号:"+tracknumber);
                }else{
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr("调用接口失败"+result.getString("message"));
                }
            }else{
                trackResultModel.setFlag(98);
                trackResultModel.setDescr("调用接口失败,返回结果错误"+value);
            }
        }catch (Exception e){
            e.printStackTrace();
            trackResultModel.setFlag(98);
            trackResultModel.setDescr("调用接口异常"+e.getMessage());
        }
        return trackResultModel;
    }

    //获取仓库信息
    public TrackResultModel getWarehouse(String appToken,String appKey){
        TrackResultModel trackResultModel = new TrackResultModel();

        try{
            EcSOAPStub binding = null;
            try {
                binding = (EcSOAPStub) new Ec_ServiceLocator().getEcSOAP();
            }catch (javax.xml.rpc.ServiceException jre) {
                jre.printStackTrace();
            }
            // Time out after a minute
            binding.setTimeout(60000);
            // Test operation
            String value = null;
            value = binding.callService("{}", appToken, appKey, "zh_CN", "getWarehouse");

            if(Util.isCheckJson(value)){
                JSONObject result = JSONObject.parseObject(value);
                if("Success".equals(result.getString("ask"))){
                    trackResultModel.setFlag(0);
                    trackResultModel.setDescr(value);
                }else{
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr("调用接口失败"+result.getString("message"));
                }
            }else{
                trackResultModel.setFlag(98);
                trackResultModel.setDescr("调用接口失败,返回结果错误"+value);
            }
        }catch (Exception e){
            e.printStackTrace();
            trackResultModel.setFlag(98);
            trackResultModel.setDescr("调用接口异常"+e.getMessage());
        }

        return trackResultModel;
    }

    //获取渠道信息
    public TrackResultModel getChannels(String appToken,String appKey,String wareHouseCode){
        TrackResultModel trackResultModel = new TrackResultModel();
        try{
            String paramsJson = "{\"warehouseCode\":\""+wareHouseCode+"\"}";
            EcSOAPStub binding = null;
            try {
                binding = (EcSOAPStub) new Ec_ServiceLocator().getEcSOAP();
            }catch (javax.xml.rpc.ServiceException jre) {
                jre.printStackTrace();
            }
            // Time out after a minute
            binding.setTimeout(60000);
            // Test operation
            String value = null;
            value = binding.callService(paramsJson, appToken, appKey, "zh_CN", "getShippingMethod");

            if(Util.isCheckJson(value)){
                JSONObject result = JSONObject.parseObject(value);
                if("Success".equals(result.getString("ask"))){
                    trackResultModel.setFlag(0);
                    trackResultModel.setDescr(value);
                }else{
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr("调用接口失败"+result.getString("message"));
                }
            }else{
                trackResultModel.setFlag(98);
                trackResultModel.setDescr("调用接口失败,返回结果错误"+value);
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
     *    dbMylogisticsaccount 账号
     *    requestJson
     *
     * **/
    public  String getDataStep1(TrackModel trackModel){
        /****************拼装数据******start***************/

        String time= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Order0 order0 = trackModel.getOrder0();
        Orderplus0 orderplus0 = trackModel.getOrderplus0();
        List<Orderitem0> orderitem0List = trackModel.getOrderitem0();
        DbLogisticschannel dbLogisticschannel = trackModel.getDbLogisticschannel();
        RequestJson requestJson = trackModel.getRequestJson();


        YkdRequestStep1 requestStep1 = new YkdRequestStep1();
        //订单参考号(建议使用平台单号)
        requestStep1.setReference_no(order0.getPlatformorderid());
        //配送方式
        requestStep1.setShipping_method(dbLogisticschannel.getCode());
        //配送仓库，参考getWarehouse
        requestStep1.setWarehouse_code(requestJson.getWarehouseCode());
        //收件人国家，参考getCountry
        requestStep1.setCountry_code(order0.getCountrycode());
        //省
        requestStep1.setProvince(orderplus0.getProvince());
        //城市
        requestStep1.setCity(orderplus0.getCity());
        //地址1
        requestStep1.setAddress1(orderplus0.getStreet1());
        //邮编
        requestStep1.setZipcode(orderplus0.getPostcode());
        //收件人姓名
        requestStep1.setName(order0.getBuyername());
        //收件人联系方式
        requestStep1.setPhone(orderplus0.getPhone1());
        //订单明细
        List<YkdItem> itemList = new ArrayList<>();
        for(Orderitem0 orderitem0 :orderitem0List){
            YkdItem item= new YkdItem();
            item.setProduct_sku(orderitem0.getPlatformsku());
            item.setQuantity(orderitem0.getQuantity());
            itemList.add(item);
        }
        requestStep1.setItems(itemList);

        return  JSONObject.toJSONString(requestStep1);
        /****************拼装数据******end***************/
    }

}
