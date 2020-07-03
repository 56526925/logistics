package org.baifei.modules.api;

import com.alibaba.fastjson.JSONObject;
import org.baifei.common.util.ConstantConfig;
import org.baifei.common.util.PdfUtil;
import org.baifei.common.util.ResultCode;
import org.baifei.common.util.SSLClient;
import org.baifei.modules.entity.Order0;
import org.baifei.modules.entity.Ordercurrency0;
import org.baifei.modules.entity.Orderitem0;
import org.baifei.modules.entity.Orderplus0;
import org.baifei.modules.entity.request.lwe.*;
import org.baifei.modules.entity.request.yt.YtParcels;
import org.baifei.modules.entity.request.yt.YtReceiver;
import org.baifei.modules.entity.request.yt.YtRequestStep1;
import org.baifei.modules.entity.request.yt.YtSender;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


/************
 *
 *
 *
 *          利威api
 *
 *
 */
@Component
public class CallApiLwe {
    @Autowired
    private SSLClient sslClient;
    @Autowired
    private RestTemplateUtil restTemplateUtil;
    private String url="http://122.51.68.40:8008";

    //创建订单
    private String createOrderUrl = "https://apiv2.unixus.com.my/shipment/v2/Create";
    //打印标签
    private String labelUrl = "https://apiv2.unixus.com.my/shipment/v2/Print";
    //刷新令牌
    private String flushTokenUrl = "https://apiv2.unixus.com.my/shipment/v2/Token/Refresh";

    //创建订单
    public TrackResultModel runStep1(TrackModel trackModel){
        TrackResultModel trackResultModel = new TrackResultModel();
        try{
            ResultCode resultCode = new ResultCode();
            Map header = new HashMap();
            header.put("Content-Type","application/json");
            header.put("Accept-Language","en");

            DbMylogisticsaccount mylogisticsaccount = trackModel.getMylogisticsaccount();

            header.put("Authorization","Bearer "+mylogisticsaccount.getRefreshToken());

            String requestJson = getDataStep1(trackModel);

            resultCode = sslClient.doPostHttpClient(createOrderUrl,header,null,requestJson,60*1000);

            if(resultCode.getAck()==0){
                JSONObject result = JSONObject.parseObject(resultCode.getData().toString());
                if(result.getJSONArray("ShipmentsResponse").size()>0){
                    String  tracknumber = result.getJSONArray("ShipmentsResponse").getJSONObject(0).getJSONObject("ShipmentDetails").getString("HawbNo");
                    trackResultModel.setTrackNumber(tracknumber);
                    trackResultModel.setFlag(3);
                    trackResultModel.setDescr("调用接口成功返回订单号:"+tracknumber);
                }else{
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr("调用接口失败,"+result.getJSONArray("ShipmentsResponse").getJSONObject(0));
                }
            }else{
                trackResultModel.setFlag(98);
                trackResultModel.setDescr("调用接口失败");
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
        String labelUrl ="Label/Print";
        try{
            ResultCode resultCode = new ResultCode();
            Map header = new HashMap();
            header.put("Content-Type","application/json");
            header.put("Accept-Language","en");

            DbMylogisticsaccount mylogisticsaccount = trackModel.getMylogisticsaccount();
            Order0 order0 = trackModel.getOrder0();

            header.put("Authorization","Bearer "+mylogisticsaccount.getRefreshToken());

            String requestJson = "{\n" +
                    "    \"HawbNo\": [ \n" +
                    "        \""+order0.getTracknumber()+"\"\n" +
                    "    ],\n" +
                    "  \"LabelFormat\": \"PDF\",\n" +
                    "  \"LabelSize\": \"Cm_10x10\",\n" +
                    "  \"TrackReport\": \"Label\"\n" +
                    "}";

            resultCode = sslClient.doPostHttpClient(labelUrl,header,null,requestJson,60*1000);

            if(resultCode.getAck()==0){
                JSONObject result = JSONObject.parseObject(resultCode.getData().toString());
                if("true".equals(result.getJSONObject("LabelResponse").getString("IsSuccess"))){
                    String  pdfBase64 = result.getJSONObject("LabelResponse").getString("LabelImageString");
                    PdfUtil.decoderBase64File(pdfBase64,order0.getPlatformorderid());
                    trackResultModel.setFlag(3);
                    trackResultModel.setDescr("下载订单成功");
                }else{
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr("调用接口失败,"+result.getJSONArray("Item").getJSONObject(0).getString("Remark"));
                }
            }else{
                trackResultModel.setFlag(98);
                trackResultModel.setDescr("调用接口失败");
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
     *    dbLogisticschannel 渠道code
     *
     * **/
    public  String getDataStep1(TrackModel trackModel){
        /****************拼装数据******start***************/

        String time= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Order0 order0 = trackModel.getOrder0();
        Orderplus0 orderplus0 = trackModel.getOrderplus0();
        List<Orderitem0> orderitem0List = trackModel.getOrderitem0();
        DbMylogisticschannel dbMylogisticschannel = trackModel.getDbMylogisticschannel();
        DbLogisticschannel dbLogisticschannel = trackModel.getDbLogisticschannel();
        DbMylogisticsreturnaddress mylogisticsreturnaddress = trackModel.getDbMylogisticsreturnaddress();
        Ordercurrency0 ordercurrency0 = trackModel.getOrdercurrency0();

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

        LweRequestStep1 requestStep1 = new LweRequestStep1();
        List<LweShipments> shipmentsList = new ArrayList<>();
        LweShipments shipments = new LweShipments();

        //卖家
        LweShipper shipper = new LweShipper();
        shipper.setContactPerson(mylogisticsreturnaddress.getContactEn());
        shipper.setAddress1(mylogisticsreturnaddress.getStreetEn());
        shipper.setPostcode(mylogisticsreturnaddress.getPostcode());
        shipper.setCity(mylogisticsreturnaddress.getCityEn());
        shipper.setState(mylogisticsreturnaddress.getProvinceEn());
        shipper.setCountryCode(mylogisticsreturnaddress.getCountryCode());
        shipper.setPhone1(mylogisticsreturnaddress.getPhone());
        shipments.setShipper(shipper);

        //买家
        LweConsignee consignee = new LweConsignee();
        consignee.setContactPerson(order0.getBuyername());
        consignee.setAddress1(orderplus0.getStreet1());
        consignee.setPostcode(orderplus0.getPostcode());
        consignee.setCity(orderplus0.getCity());
        consignee.setState(orderplus0.getProvince());
        consignee.setCountryCode(order0.getCountrycode());
        consignee.setPhone1(orderplus0.getPhone1());
        shipments.setConsignee(consignee);

        //商品、包装
        List<LweItems> itemsList = new ArrayList<>();
        List<LwePackages> packagesList = new ArrayList<>();
        for(Orderitem0 orderitem0 :orderitem0List){
            LweItems items = new LweItems();
            items.setDescription(orderitem0.getDeclareEnName());
            items.setQuantity(orderitem0.getQuantity());
            items.setUnitValue(single_value);
            LwePackages packages = new LwePackages();
            packages.setPackageReference(orderitem0.getDeclareEnName());
            packages.setLength(1);
            packages.setWidth(1);
            packages.setHeight(1);
            //KG
            packages.setActualWeight(cargo_total_weight);

            packagesList.add(packages);
            itemsList.add(items);
            packagesList.add(packages);
        }
        shipments.setItems(itemsList);
        shipments.setPackages(packagesList);

        //服务类型
        shipments.setTOSMode(GetTOSMode(order0.getCountrycode()));
        //
        shipments.setPackageType("SPX");
        shipments.setWeightType("KG");
        shipments.setCurrencyCode(ordercurrency0.getCurrencyid());

        shipmentsList.add(shipments);
        requestStep1.setShipments(shipmentsList);



        return  JSONObject.toJSONString(requestStep1);
        /****************拼装数据******end***************/
    }

    //刷新令牌
    public TrackResultModel flushToken(Integer accountId){
        Map<String, Object> header = new HashMap<>();
        TrackResultModel trackResultModel = new TrackResultModel();
        header.put("Content-Type", "application/json");
        header.put("Accept","application/json");
        ResultCode resultCode = new ResultCode();

        String requestTokenJson="{\n" +
                "  \"RefreshToken\" : \"refresh_token\",\n" +
                "}";

        resultCode = sslClient.doPostHttpClient(flushTokenUrl,header,null,requestTokenJson,60*1000);
        if(resultCode.getAck()==0){
            JSONObject result = JSONObject.parseObject(resultCode.getData().toString());
            DbMylogisticsaccount mylogisticsaccount = new DbMylogisticsaccount();
            mylogisticsaccount.setRefreshToken(result.getString("AccessToken"));
            mylogisticsaccount.setExpireTime(new Date(result.getString("Expires")));
            mylogisticsaccount.setId(accountId);
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

    public String GetTOSMode(String consigneeCountry) {
        switch (consigneeCountry) {
            case "MY":
                return "MY-E-EXPRESS";
            case "TH":
                return "TH-E-EXPRESS";
            case "HK":
                return "HK-E-EXPRESS";
            case "SG":
                return "SG-E-EXPRESS";
            case "ID":
                return "ID-E-EXPRESS";
            case "CN":
                return "CN-E-EXPRESS";
            default:
                // error
        }
        return "";
    }

    public static void main(String[] args) {
        CallApiLwe apiYT = new CallApiLwe();
        TrackModel trackModel = new TrackModel();
        DbMylogisticsaccount mylogisticsaccount = new DbMylogisticsaccount();
        mylogisticsaccount.setName("C03949");
        mylogisticsaccount.setToken("fBQP22qIOs8=");
        Order0 order0 = new Order0();
        order0.setTracknumber("YT2011321266119037");
        trackModel.setMylogisticsaccount(mylogisticsaccount);
        trackModel.setOrder0(order0);
        apiYT.runStep2(trackModel);
    }
}
