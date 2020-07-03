package org.baifei.modules.api;

import com.alibaba.fastjson.JSONObject;
import org.baifei.common.util.PdfUtil;
import org.baifei.common.util.ResultCode;
import org.baifei.common.util.SSLClient;
import org.baifei.common.util.Util;
import org.baifei.modules.entity.Order0;
import org.baifei.modules.entity.Orderitem0;
import org.baifei.modules.entity.Orderplus0;
import org.baifei.modules.entity.request.common.PropertyJson;
import org.baifei.modules.entity.request.dhl.*;
import org.baifei.modules.entity.response.common.*;
import org.baifei.modules.entity.response.dhl.DhlResponseStep1;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.util.RestTemplateUtil;
import org.baifei.modules.util.ResulstCodeWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class CallApiDHL {
    @Autowired
    private SSLClient sslClient;
    @Autowired
    private RestTemplateUtil restTemplateUtil;
    private String createOrderUrl = "https://sandbox.dhlecommerce.asia/rest/v2/Label";
    private String labelUrl = "https://sandbox.dhlecommerce.asia/rest/v2.Label.Reprint/";

    private String clientId = "MTAwNzE5MDE4Mg==";

    private String password = "LTQ4Njg0M2906201593425407";

    //创建订单
    public TrackResultModel runStep1(TrackModel trackModel){
        TrackResultModel trackResultModel = new TrackResultModel();
        ResultCode resultCode = new ResultCode();
        try {
            //请求头
            Map<String, Object> header = new HashMap<String, Object>();
            header.put("Content-Type", "application/json");

            String requestJson = getDataStep1(trackModel);

            resultCode = sslClient.doPostHttpClient(createOrderUrl, header, null, requestJson, 60 * 1000);
            if(resultCode.getAck()==0){
                DhlResponseStep1 result = JSONObject.parseObject(resultCode.getData().toString(),DhlResponseStep1.class);

                if("200".equals(result.getLabelResponse().getBd().getResponseStatus().getCode())){
                        String tracknumber =result.getLabelResponse().getBd().getLabels().get(0).getShipmentID();
                        trackResultModel.setTrackNumber(tracknumber);
                        trackResultModel.setFlag(3);
                        trackResultModel.setDescr(resultCode.getMsg());
                }else{
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr("调用接口失败"+result.getLabelResponse().getBd().getResponseStatus().getMessageDetails().get(0).getMessageDetail());
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

    public TrackResultModel runStep2(TrackModel trackModel){
        ResultCode resultCode = new ResultCode();
        TrackResultModel trackResultModel = new TrackResultModel();
        try {
            DbMylogisticsaccount mylogisticsaccount = trackModel.getMylogisticsaccount();
            //测试
            // mylogisticsaccount.setRefreshToken("47b56362c73e486b8dc36df01a6d0152");
            // mylogisticsaccount.setToken("CNXXX");


            Order0 order0 = trackModel.getOrder0();
            //请求头
            Map<String, Object> header = new HashMap<String, Object>();
            header.put("Content-Type", "application/json");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ");
            GregorianCalendar gc = new GregorianCalendar();
            String dateString = sdf.format(gc.getTime());
            XMLGregorianCalendar date2 = null;
            try {
                gc.setTime(sdf.parse(dateString));
                date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
            } catch (Exception e) {
                e.printStackTrace();
            }

            mylogisticsaccount.setRefreshToken(mylogisticsaccount.getRefreshToken());

            String requestJson=" {\"labelReprintRequest\": {\n" +
                    " \"hdr\": {\n" +
                    " \"messageType\": \"LABELREPRINT\",\n" +
                    " \"messageDateTime\": \""+date2.toString()+"\",\n" +
                    " \"accessToken\": \""+mylogisticsaccount.getRefreshToken()+"\",\n" +
                    " \"messageVersion\": \"1.0\",\n" +
                    " \"messageLanguage\": \"zh_CN\"\n" +
                    " },\n" +
                    " \"bd\": {\n" +
                    " \"pickupAccountId\": \""+mylogisticsaccount.getName()+"\",\n" +
                    " \"soldToAccountId\": \""+mylogisticsaccount.getName()+"\",\n" +
                    " \"shipmentItems\": [\n" +
                    " {\n" +
//                    " \"deliveryConfirmationNo\": \""+order0.getTracknumber()+"\"\n" +
                    "\"shipmentID\": \""+mylogisticsaccount.getToken()+order0.getPlatformorderid()+"\""+
                    " }\n" +
                    " ]\n" +
                    " }\n" +
                    " } }";

            resultCode = sslClient.doPostHttpClient(labelUrl, header, null, requestJson, 60 * 1000);
            if(resultCode.getAck()==0){
                JSONObject result = JSONObject.parseObject(resultCode.getData().toString());
                if("200".equals(result.getJSONObject("labelReprintResponse").getJSONObject("bd").getJSONObject("responseStatus").getString("code"))){
                    String labelBase64 =  result.getJSONObject("labelReprintResponse").getJSONObject("bd").getJSONArray("shipmentItems").getJSONObject(0).getString("content");
                    PdfUtil.decoderBase64File(labelBase64,order0.getPlatformorderid());
                    trackResultModel.setFlag(3);
                    trackResultModel.setDescr(resultCode.getMsg());
                }else{
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr(result.getJSONObject("labelReprintResponse").getJSONObject("bd").getJSONObject("responseStatus").getString("messageDetails"));
                }

            }else{
                trackResultModel.setFlag(98);
                trackResultModel.setDescr("调用接口异常"+resultCode.getMsg());
            }
        }catch (Exception e){
            e.printStackTrace();
            trackResultModel.setFlag(98);
            trackResultModel.setDescr("调用接口异常"+e.getMessage());
        }
        return trackResultModel;
    }

    public String getDataStep1(TrackModel trackModel){
        Order0 order0 = trackModel.getOrder0();
        Orderplus0 orderplus0 = trackModel.getOrderplus0();
        DbLogisticschannel logisticschannel = trackModel.getDbLogisticschannel();
        DbMylogisticschannel dbMylogisticschannel = trackModel.getDbMylogisticschannel();
        DbMylogisticsreturnaddress dbMylogisticsreturnaddress = trackModel.getDbMylogisticsreturnaddress();
        List<Orderitem0> orderitem0List = trackModel.getOrderitem0();
        DbMylogisticswarehouse dbMylogisticswarehouse= trackModel.getDbMylogisticswarehouse();
        DbMylogisticsaccount mylogisticsaccount = trackModel.getMylogisticsaccount();
        PropertyJson propertyJson = trackModel.getPropertyJson();

        //测试
//        dbMylogisticswarehouse.setContactEn("libingqing");
//        dbMylogisticswarehouse.setStreetEn("shijiazhuang");
//        dbMylogisticswarehouse.setCityEn("shijiazhuang");
//        logisticschannel.setCode("PPS");
//        mylogisticsaccount.setToken("CNXXX");
//        mylogisticsaccount.setRefreshToken("47b56362c73e486b8dc36df01a6d0152");


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ");
        GregorianCalendar gc = new GregorianCalendar();
        String dateString = sdf.format(gc.getTime());
        XMLGregorianCalendar date2 = null;
        try {
            gc.setTime(sdf.parse(dateString));
            date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        String desc="";
        for(Orderitem0 orderitem0 :orderitem0List) {
            declared_value += orderitem0.getDeclareValue();
            cargo_total_weight += orderitem0.getTestWeight();
            desc = orderitem0.getDeclareEnName();
        }
        cargo_total_weight=cargo_total_weight/1000;
        //内件总价值
        if(declared_value>maxDeclareFeeOrigin){
            declared_value=maxDeclareFeeOrigin;
            single_value = declared_value/order0.getItemquantity();
        }else if(declared_value<declareFeeOrigin){
            declared_value=declareFeeOrigin;
            single_value = declared_value/order0.getItemquantity();
        }
        String battery="00";
        if("1".equals(propertyJson.getBatteryflag())){
            battery="04";
        }


        DhlRequestStep1 requestStep1 = new DhlRequestStep1();
        DhllabelRequest dhllabelRequest = new DhllabelRequest();
        DhlHdr hdr = new DhlHdr();
        //token
        hdr.setAccessToken(Util.isCheckNull(mylogisticsaccount.getRefreshToken()));
        //请求时间
        //CCYY-MMDDThh:mm:ssTZD
        //2017-03-
        //27T15:28:15+08:00
        hdr.setMessageDateTime(date2.toString());
        //en/zh_CH/th_TH
        hdr.setMessageLanguage("zh_CN");
        //“LABEL”
        hdr.setMessageType("LABEL");
        //1.4
        hdr.setMessageVersion("1.4");
        dhllabelRequest.setHdr(hdr);

        DhlBd bd = new DhlBd();

        //DHL Soldto Account
        bd.setPickupAccountId(mylogisticsaccount.getName());
        //DHL Pickup Account
        bd.setSoldToAccountId(mylogisticsaccount.getName());
        //提货日期
        bd.setPickupDateTime(date2.toString());
        //提货地址
        DhlPickupAddress pickupAddress = new DhlPickupAddress();
        //地址
        pickupAddress.setAddress1(Util.isCheckNull(dbMylogisticswarehouse.getStreetEn()));
        //提货城市
        pickupAddress.setCity(Util.isCheckNull(dbMylogisticswarehouse.getCityEn()));
        //提货国家
        pickupAddress.setCountry(dbMylogisticswarehouse.getCountryCode());
        //提货联系人
        pickupAddress.setName(Util.isCheckNull(dbMylogisticswarehouse.getContactEn()));
        bd.setPickupAddress(pickupAddress);

        //发件地址
        DhlShipperAddress shipperAddress = new DhlShipperAddress();
        //发件地址1
        shipperAddress.setAddress1(Util.isCheckNull(dbMylogisticsreturnaddress.getStreetEn()));
        //发件地址2
        shipperAddress.setAddress2(Util.isCheckNull(dbMylogisticsreturnaddress.getStreetEn()));
        //发件城市
        shipperAddress.setCity(Util.isCheckNull(dbMylogisticsreturnaddress.getCityEn()));
        //发件国家
        shipperAddress.setCountry(dbMylogisticsreturnaddress.getCountryCode());
        //发件人
        shipperAddress.setName(Util.isCheckNull(dbMylogisticsreturnaddress.getContactEn()));
        bd.setShipperAddress(shipperAddress);

        //发货包裹信息
        List<DhlShipmentItems> shipmentItemsList = new ArrayList<>();
        DhlShipmentItems shipmentItems = new DhlShipmentItems();
        //收件人地址详情
        DhlConsigneeAddress consigneeAddress = new DhlConsigneeAddress();
        consigneeAddress.setAddress1(Util.isCheckNull(orderplus0.getStreet1()));
        consigneeAddress.setCity(Util.isCheckNull(orderplus0.getCity()));
        consigneeAddress.setCountry(order0.getCountrycode());
        consigneeAddress.setName(Util.isCheckNull(order0.getBuyername()));
        consigneeAddress.setState(Util.isCheckNull(orderplus0.getProvince()));
        shipmentItems.setConsigneeAddress(consigneeAddress);

        //退件地址详情
        DhlReturnAddress returnAddress = new DhlReturnAddress();
        returnAddress.setAddress1(Util.isCheckNull(dbMylogisticsreturnaddress.getStreetEn()));
        returnAddress.setCity(Util.isCheckNull(dbMylogisticsreturnaddress.getCityEn()));
        returnAddress.setCountry(dbMylogisticsreturnaddress.getCountryCode());
        returnAddress.setName(Util.isCheckNull(dbMylogisticsreturnaddress.getContactEn()));

        //发货号码 CNXXX201910110001 15位
        shipmentItems.setShipmentID(mylogisticsaccount.getToken()+order0.getPlatformorderid());
        //包裹概述
        shipmentItems.setPackageDesc(desc);
        //包裹重量
        shipmentItems.setTotalWeight(new Double(cargo_total_weight).intValue());
        //重量单位 默认G
        shipmentItems.setTotalWeightUOM("G");
        //带点 00-普货 04-带电
        shipmentItems.setContentIndicator(battery);
        //包裹总价值
        shipmentItems.setTotalValue(declared_value);
        //币种
        shipmentItems.setCurrency("USD");
        //包裹长度单位
        shipmentItems.setDimensionUOM("CM");
        //国际贸易简制(DDU-平邮、挂号、中英、中澳)(DDP-中美、中以)
        shipmentItems.setIncoterm("DDP");
        //包裹保险金额
        shipmentItems.setInsuranceValue(new BigDecimal(0));
        //包裹产品编码
        shipmentItems.setProductCode(logisticschannel.getCode());

        List<DhlshipmentContents> shipmentContentsList = new ArrayList<>();
        for(Orderitem0 orderitem0:orderitem0List){
            int single_weight =new Double(orderitem0.getTestWeight()/1000).intValue();
            if(single_weight<1){
                single_weight=1;
            }
            //包裹产品详述
            DhlshipmentContents shipmentContents = new DhlshipmentContents();
            //带点 00-普货 04-带电
            shipmentContents.setContentIndicator(battery);
            //产品原产国
            shipmentContents.setCountryOfOrigin("CN");
            //产品描述
            shipmentContents.setDescription(Util.isCheckNull(orderitem0.getDeclareEnName()));
            //产品中文描述
            shipmentContents.setDescriptionExport(Util.isCheckNull(orderitem0.getDeclareCnName()));
            //产品英文描述
            shipmentContents.setDescriptionImport(Util.isCheckNull(orderitem0.getDeclareEnName()));
            //产品净重
            shipmentContents.setGrossWeight(single_weight);
            //产品净重单位
            shipmentContents.setWeightUOM("G");
            //产品数量
            shipmentContents.setItemQuantity(orderitem0.getQuantity());
            //
            shipmentContents.setItemValue(single_value);
            //产品sku
            shipmentContents.setSkuNumber(orderitem0.getStocksku());
            shipmentContentsList.add(shipmentContents);
        }
        shipmentItems.setShipmentContents(shipmentContentsList);
        shipmentItemsList.add(shipmentItems);
        bd.setShipmentItems(shipmentItemsList);
        DhlLabel label = new DhlLabel();


        label.setLayout("1x1");
        label.setFormat("PDF");
        label.setPageSize("400x400");

        bd.setLabel(label);
        bd.setInlineLabelReturn("N");
        dhllabelRequest.setBd(bd);


        requestStep1.setLabelRequest(dhllabelRequest);

        return JSONObject.toJSONString(requestStep1);
    }

    public TrackResultModel flushToken(Integer accountId){
        TrackResultModel trackResultModel = new TrackResultModel();
        String url ="https://sandbox.dhlecommerce.asia/rest/v1/OAuth/AccessToken?clientId="+clientId+"&password="+password+"&returnFormat=json";
        try{
            ResultCode resultCode = new ResultCode();
            Map header = new HashMap();
            header.put("Content-Type","application/json");
            resultCode = sslClient.doGetHttpClient(url,header,60*1000);
            if(resultCode.getAck()==0){
                JSONObject result = JSONObject.parseObject(resultCode.getData().toString());
                DbMylogisticsaccount mylogisticsaccount = new DbMylogisticsaccount();
                mylogisticsaccount.setRefreshToken(result.getJSONObject("accessTokenResponse").getString("token"));
                Calendar nowTime = Calendar.getInstance();
                nowTime.add(Calendar.SECOND,Integer.parseInt(result.getJSONObject("accessTokenResponse").getString("expires_in_seconds")));
                mylogisticsaccount.setExpireTime(nowTime.getTime());
                mylogisticsaccount.setId(accountId);
                ResulstCodeWeb<List<DbMylogisticsaccount>> resultAccount = restTemplateUtil.getDataListForPost(DbMylogisticsaccount.class,mylogisticsaccount,"",url+"/syncDbMyLogisticsAccount");
                if(resultAccount.getAck()==0){
                    trackResultModel.setFlag(0);
                    trackResultModel.setDescr("刷新令牌成功");
                }else{
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr("刷新令牌失败"+resultAccount.getMsg());
                }
                trackResultModel.setDescr(resultCode.getData().toString());
                trackResultModel.setFlag(0);
            }else{
                trackResultModel.setFlag(98);
                trackResultModel.setDescr("刷新令牌异常"+resultCode.getMsg());
            }
        }catch (Exception e){
            trackResultModel.setFlag(98);
            trackResultModel.setDescr("刷新令牌异常"+e.getMessage());
        }
        return null;
    }


    public static void main(String[] args) throws DatatypeConfigurationException, ParseException {
       DhlRequestStep1 requestStep1 = new DhlRequestStep1();
       DhllabelRequest dhllabelRequest = new DhllabelRequest();
       DhlBd bd = new DhlBd();
       bd.setSoldToAccountId("asd''dd");
        dhllabelRequest.setBd(bd);
       requestStep1.setLabelRequest(dhllabelRequest);
        System.out.println(JSONObject.toJSONString(requestStep1));

    }
}
