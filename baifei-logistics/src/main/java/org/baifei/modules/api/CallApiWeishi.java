package org.baifei.modules.api;

import com.alibaba.fastjson.JSONObject;
import org.baifei.common.util.*;
import org.baifei.modules.entity.Order0;
import org.baifei.modules.entity.Orderitem0;
import org.baifei.modules.entity.Orderplus0;
import org.baifei.modules.entity.request.weishi.WeishiConsignee;
import org.baifei.modules.entity.request.weishi.WeishiItemArr;
import org.baifei.modules.entity.request.weishi.WeishiRequestStep1;
import org.baifei.modules.entity.request.weishi.WeishiShipper;
import org.baifei.modules.entity.response.common.DbLogisticschannel;
import org.baifei.modules.entity.response.common.DbMylogisticschannel;
import org.baifei.modules.entity.response.common.DbMylogisticsreturnaddress;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/************
 *
 *
 *
 *          纬狮 api
 *
 *
 */
@Component
public class CallApiWeishi {
    @Autowired
    private SSLClient sslClient;
//    String appToken = "a405c85f52c73a0e94b3319668a0ed0d";
//
//    String appKey ="a405c85f52c73a0e94b3319668a0ed0da00f5d33eb1d04104c8e6f1bc09e5d20";
//
//    String customerCode = "00035";
//
//    //测试地址
//    String serviceUrl ="http://track.360lion.com:28080/toms/service";
    //正式地址
    String serviceUrl = "http://track.360lion.com/api/service";

    String appToken = "031cc4e034d3dfdf1a24eee09a86a471";

    String appKey = "031cc4e034d3dfdf1a24eee09a86a471bb717cc2ff42b98919ec69456699ef98";

    String customerCode = "00317";


    //创建订单
    public TrackResultModel runStep1(TrackModel trackModel){
         TrackResultModel trackResultModel = new TrackResultModel();

        try{
            ResultCode resultCode = new ResultCode();

            String requestJson = "{\n" +
                    "    \"paramsJson\":\n"
                    +getDataStep1(trackModel)+
                    ",   \"appToken\": \""+appToken+"\", \n" +
                    "    \"service\": \"createOrder\", \n" +
                    "    \"customerCode\": \""+customerCode+"\", \n" +
                    "    \"appKey\": \""+appKey+"\"\n" +
                    "}";
            resultCode = sslClient.doPostHttpClient(serviceUrl,null,null,requestJson,60*1000);
            if(resultCode.getAck()==0){
                JSONObject result = JSONObject.parseObject(resultCode.getData().toString());
                if("Success".equals(result.getString("ask"))){
                    trackResultModel.setTrackNumber(result.getString("shipping_method_no"));
                    trackResultModel.setFlag(3);
                    trackResultModel.setDescr("调用接口成功，返回单号："+result.getString("shipping_method_no"));
                }else{
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr("调用接口异常"+result.get("message"));
                }
            }else{
                trackResultModel.setFlag(98);
                trackResultModel.setDescr("调用接口异常");
            }
        }catch (Exception e){
            e.printStackTrace();
            trackResultModel.setFlag(98);
            trackResultModel.setDescr("调用接口异常"+e.getMessage());
        }
        return trackResultModel;
    }

    //下载订单
    public TrackResultModel runStep2(TrackModel trackModel){
        TrackResultModel trackResultModel = new TrackResultModel();
        Order0 order0 = trackModel.getOrder0();
        String platformOrderId = order0.getPlatformorderid();
        try{
            ResultCode resultCode = new ResultCode();

           String requestJson ="{\n" +
                   "    \"paramsJson\": {\n" +
                   "        \"reference_no\":\""+platformOrderId+"\"\n" +
                   "    }, \n" +
                   "    \"appToken\": \""+appToken+"\", \n" +
                   "    \"service\": \"getLabelUrl\", \n" +
                   "    \"customerCode\": \""+customerCode+"\", \n" +
                   "\"appKey\": \""+appKey+"\"\n" +
                   "}";
            resultCode = sslClient.doPostHttpClient(serviceUrl,null,null,requestJson,60*1000);
            if(resultCode.getAck()==0){
                JSONObject result = JSONObject.parseObject(resultCode.getData().toString());
                if("Success".equals(result.getString("ask"))){
                    String pdfUrl =result.getString("url");
                    ResultCode resultCodePdf = PdfUtil.doGetHttpClient(pdfUrl,null,60*1000,ConstantConfig.PDF_ABSOLUTE_PATH,platformOrderId);
                    if(resultCodePdf.getAck()==0){
                        trackResultModel.setFlag(3);
                        trackResultModel.setDescr("下载订单成功!");
                    }else{
                        trackResultModel.setFlag(98);
                        trackResultModel.setDescr("下载订单失败!");
                    }
                }else{
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr("调用接口异常"+result.get("message"));
                }
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

    public TrackResultModel getChannels(){
        TrackResultModel trackResultModel = new TrackResultModel();
        ResultCode resultCode = new ResultCode();
        try{
            String requestJson ="{\n" +
                    "    \"paramsJson\":\"\",\n" +
                    "    \"appToken\":\""+appToken+"\",\n" +
                    "    \"service\":\"getShippingMethod\",\n" +
                    "    \"appKey\":\""+appKey+"\"\n" +
                    "}";
            resultCode = sslClient.doPostHttpClient(serviceUrl,null,null,requestJson,60*1000);
            if(resultCode.getAck()==0){
                trackResultModel.setFlag(0);
                trackResultModel.setDescr(resultCode.getData().toString());
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
     *    orderplus0 买家信息
     *    orderitem0List 订单详情
     *    dbMylogisticschannel 物流方式 取最大最小申报值
     *    dbMylogisticsreturnaddress 回邮地址
     *    dbMylogisticsaccount 账号
     *
     *
     * **/
    public  String getDataStep1(TrackModel trackModel){
        /****************拼装数据******start***************/

        String postJson = "";
        String time= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Order0 order0 = trackModel.getOrder0();
        Orderplus0 orderplus0 = trackModel.getOrderplus0();
        List<Orderitem0> orderitem0List = trackModel.getOrderitem0();
        DbMylogisticschannel dbMylogisticschannel = trackModel.getDbMylogisticschannel();
        DbLogisticschannel dbLogisticschannel = trackModel.getDbLogisticschannel();
        DbMylogisticsreturnaddress dbMylogisticsreturnaddress = trackModel.getDbMylogisticsreturnaddress();


        //测试PK0002
//        dbLogisticschannel.setCode("PK0002");
//        order0.setCountrycode("MX");
//        orderplus0.setPostcode("94297");

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


        WeishiRequestStep1 requestStep1 = new WeishiRequestStep1();

        //客户参考号
        requestStep1.setReference_no(order0.getPlatformorderid());
        //配送方式
        requestStep1.setShipping_method(dbLogisticschannel.getCode());
        //收件人国家二字码
        requestStep1.setCountry_code(order0.getCountrycode());
        //订单重量，单位KG，默认为0.2
        requestStep1.setOrder_weight(cargo_total_weight);
        //收件人信息
        WeishiConsignee consignee = new WeishiConsignee();
        //收件人省
        consignee.setConsignee_province(Util.isCheckNull(orderplus0.getProvince()));
        //收件人城市
        consignee.setConsignee_city(Util.isCheckNull(orderplus0.getCity()));
        //收件人地址1
        consignee.setConsignee_street(Util.isCheckNull(orderplus0.getStreet1()));
        //收件人邮编
        consignee.setConsignee_postcode(Util.isCheckNull(orderplus0.getPostcode()));
        //收件人姓名
        consignee.setConsignee_name(Util.isCheckNull(order0.getBuyername()));
        //收件人手机
        consignee.setConsignee_mobile(Util.isCheckNull(orderplus0.getPhone1()));
        //收件人电话
        consignee.setConsignee_telephone(Util.isCheckNull(orderplus0.getPhone2()));
        //发件人信息
        WeishiShipper shipper = new WeishiShipper();
        //发件人国家二字码
        shipper.setShipper_countrycode(Util.isCheckNull(dbMylogisticsreturnaddress.getCountryCode()));
        //发件人省
        shipper.setShipper_province(Util.isCheckNull(dbMylogisticsreturnaddress.getProvinceEn()));
        //发件人城市
        shipper.setShipper_city(Util.isCheckNull(dbMylogisticsreturnaddress.getCityEn()));
        //发件人地址
        shipper.setShipper_street(Util.isCheckNull(dbMylogisticsreturnaddress.getStreetEn()));
        //发件人邮编
        shipper.setShipper_postcode(Util.isCheckNull(dbMylogisticsreturnaddress.getPostcode()));
        //发件人姓名
        shipper.setShipper_name(Util.isCheckNull(dbMylogisticsreturnaddress.getContactEn()));
        //发件人电话
        shipper.setShipper_telephone(Util.isCheckNull(dbMylogisticsreturnaddress.getPhone()));
        //发件人手机
        shipper.setShipper_mobile(Util.isCheckNull(dbMylogisticsreturnaddress.getMobile()));
        //海关申报信息
        List<WeishiItemArr> itemArrlist = new ArrayList<>();
        for(Orderitem0 orderitem0 :orderitem0List) {
            WeishiItemArr itemArr = new WeishiItemArr();
            //海关申报品名
            itemArr.setInvoice_enname(Util.isCheckNull(orderitem0.getDeclareEnName()));
            //申报重量，单位KG, 精确到三位小数。
            itemArr.setInvoice_weight(String.format("%.3f", Util.isCheckNull(orderitem0.getTestWeight())/1000000));
            //数量
            itemArr.setInvoice_quantity(Util.isCheckNull(orderitem0.getQuantity())+"");
            //申报币种，默认为USD(美元)
            itemArr.setInvoice_currencycode("USD");
            //单价
            itemArr.setInvoice_unitcharge(single_value+"");
            itemArrlist.add(itemArr);
        }

        requestStep1.setConsignee(consignee);
        requestStep1.setShipper(shipper);
        requestStep1.setItemArr(itemArrlist);

        postJson = JSONObject.toJSONString(requestStep1);

        return  postJson;
        /****************拼装数据******end***************/
    }

}
