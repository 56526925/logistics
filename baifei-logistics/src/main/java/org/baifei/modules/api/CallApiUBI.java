package org.baifei.modules.api;

import com.alibaba.fastjson.JSONObject;
import org.baifei.common.util.*;
import org.baifei.modules.entity.Order0;
import org.baifei.modules.entity.Orderitem0;
import org.baifei.modules.entity.Orderplus0;
import org.baifei.modules.entity.request.ubi.UbiOrderItems;
import org.baifei.modules.entity.request.ubi.UbiRequestStep1;
import org.baifei.modules.entity.response.common.*;
import org.baifei.modules.entity.response.ubi.UbiResponseStep1;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class CallApiUBI {
    @Autowired
    private SSLClient sslClient;
    private  String URL = "https://cn.etowertech.com";

//    private String key = "rHjPlPi225b5QwUm-T_zvA";
//    private String token = "pclGURJQD24wpWyR8kgrJ9";

    //创建订单
    public TrackResultModel runStep1(TrackModel trackModel){
        String url_track=URL+"/services/shipper/orders";
        TrackResultModel trackResultModel = new TrackResultModel();
        ResultCode resultCode = new ResultCode();
        SimpleDateFormat sdf3 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        sdf3.setTimeZone(TimeZone.getTimeZone("GMT"));
        String rfc1123 = sdf3.format(new Date());
        try {
            DbMylogisticsaccount mylogisticsaccount = trackModel.getMylogisticsaccount();
            String signStr = "POST" + "\n" + rfc1123 + "\n" + url_track;

            //Authorization <Base64 Encoded HMAC SHA-1 Hash>
            String sign = Base64.getEncoder().encodeToString(hamcsha1(signStr, mylogisticsaccount.getAccessKey()));

            //请求头
            Map<String, Object> header = new HashMap<String, Object>();
            header.put("Content-Type", "application/json");
            header.put("X-WallTech-Date", rfc1123);
            header.put("Authorization", "WallTech " + mylogisticsaccount.getRefreshToken() + ":" + sign);

            String requestJson = getDataStep1(trackModel);

            resultCode = sslClient.doPostHttpClient(url_track, header, null, requestJson, 60 * 1000);
            if(resultCode.getAck()==0){
                UbiResponseStep1 result = JSONObject.parseObject(resultCode.getData().toString(),UbiResponseStep1.class);
                String status= result.getStatus();

                if(status!=null && "success".equals(status.toLowerCase())){
                        String tracknumber =result.getData().get(0).getTrackingNo();
                        trackResultModel.setTrackNumber(tracknumber);
                        trackResultModel.setFlag(3);
                        trackResultModel.setDescr(resultCode.getMsg());
                }else{
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr(result.getErrors());
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
        String url_label=URL+"/services/shipper/labels";
        ResultCode resultCode = new ResultCode();
        TrackResultModel trackResultModel = new TrackResultModel();
        SimpleDateFormat sdf3 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        sdf3.setTimeZone(TimeZone.getTimeZone("GMT"));
        String rfc1123 = sdf3.format(new Date());
        try {
            DbMylogisticsaccount mylogisticsaccount = trackModel.getMylogisticsaccount();
            Order0 order0 = trackModel.getOrder0();
            String signStr = "POST" + "\n" + rfc1123 + "\n" + url_label;

            //Authorization <Base64 Encoded HMAC SHA-1 Hash>
            String sign = Base64.getEncoder().encodeToString(hamcsha1(signStr, mylogisticsaccount.getAccessKey()));

            //请求头
            Map<String, Object> header = new HashMap<String, Object>();
            header.put("Content-Type", "application/json");
            header.put("X-WallTech-Date", rfc1123);
            header.put("Authorization", "WallTech " + mylogisticsaccount.getRefreshToken() + ":" + sign);

            String requestJson="{\"orderIds\":[\""+order0.getTracknumber()+"\"]," +
                    "\"labelType\":1," +
                    "\"packinglist\":false," +
                    "\"merged\":false}";

            resultCode = sslClient.doPostHttpClient(url_label, header, null, requestJson, 60 * 1000);
            if(resultCode.getAck()==0){
                JSONObject resultData = JSONObject.parseObject(resultCode.getData().toString());
                String status= resultData.getString("status");
                if("Success".equals(status)){
                    String labelBase64 =  resultData.getJSONArray("data").getJSONObject(0).getString("labelContent");
                    PdfUtil.decoderBase64File(labelBase64,order0.getPlatformorderid());
                    trackResultModel.setFlag(3);
                    trackResultModel.setDescr(resultCode.getMsg());
                }else{
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr(resultData.getJSONObject("errors").getString("message"));
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

    public String getDataStep1(TrackModel trackModel){
        Order0 order0 = trackModel.getOrder0();
        Orderplus0 orderplus0 = trackModel.getOrderplus0();
        DbLogisticschannel logisticschannel = trackModel.getDbLogisticschannel();
        DbMylogisticschannel dbMylogisticschannel = trackModel.getDbMylogisticschannel();
        DbMylogisticsreturnaddress dbMylogisticsreturnaddress = trackModel.getDbMylogisticsreturnaddress();
        List<Orderitem0> orderitem0List = trackModel.getOrderitem0();

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

        UbiRequestStep1 requestStep1 = new UbiRequestStep1();
        //客户端订单唯一标识
        requestStep1.setReferenceNo(order0.getPlatformorderid());
        //收货人姓名（英文）
        requestStep1.setRecipientName(Util.isCheckNull(order0.getBuyername()));
        //收货人地址第一行（英文）
        requestStep1.setAddressLine1(Util.isCheckNull(orderplus0.getStreet1()));
        //收货人城市（英文）
        requestStep1.setCity(Util.isCheckNull(orderplus0.getCity()));
        //州
        requestStep1.setState(Util.isCheckNull(orderplus0.getProvince()));
        //收货人邮编
        requestStep1.setPostcode(Util.isCheckNull(orderplus0.getPostcode()));
        //电话
        requestStep1.setPhone(Util.isCheckNull(orderplus0.getPhone1()));
        //email
        requestStep1.setEmail(Util.isCheckNull(orderplus0.getEmail()));

        /*
        O/M start
        * */
        //收货人国家（英文）
        requestStep1.setCountry(Util.isCheckNull(order0.getCountrycode()));
        //服务代码(需要向物流提供商咨询）
        requestStep1.setServiceCode(Util.isCheckNull(logisticschannel.getCode()));

        //服务类型(需要向物流提供商咨询）
        requestStep1.setServiceOption("");
        //发货人站点代码(需要向物流提供商咨询）
        requestStep1.setFacility("");

        /*
        O/M end
        * */
        //包裹重量
        requestStep1.setWeight(cargo_total_weight);
        //货值（清关需要）

        requestStep1.setInvoiceValue(Util.isCheckNull(declared_value));

        requestStep1.setInvoiceCurrency("CNY");

        List<UbiOrderItems> orderItems = new ArrayList<UbiOrderItems>();

        String nativeDescription="",description="";
        for(Orderitem0 orderitem0:orderitem0List){
            nativeDescription=orderitem0.getDeclareCnName();
            description =orderitem0.getDeclareEnName();
            UbiOrderItems orderItem = new UbiOrderItems();
            orderItem.setSku(orderitem0.getStocksku());
            orderItem.setItemNo(orderitem0.getItemid());
            orderItem.setDescription(orderitem0.getDeclareEnName());
            orderItem.setOriginCountry("CN");
            orderItem.setWeight(orderitem0.getTestWeight()/1000000);
            orderItem.setUnitValue(single_value);
            orderItem.setItemCount(orderitem0.getQuantity());
            orderItems.add(orderItem);
        }

        requestStep1.setOrderItems(orderItems);

        //商品中文描述（必须包含中文）
        requestStep1.setNativeDescription(Util.isCheckNull(nativeDescription));
        //商品描述（必须包含英文）
        requestStep1.setDescription(Util.isCheckNull(description));


        //发货人姓名（英文）
        requestStep1.setShipperName(Util.isCheckNull(dbMylogisticsreturnaddress.getContactEn()));
        //发货人地址第一行（英文）
        requestStep1.setShipperAddressLine1(Util.isCheckNull(dbMylogisticsreturnaddress.getStreetEn()));
        //发货人邮编
        requestStep1.setShipperPostcode(dbMylogisticsreturnaddress.getPostcode());

        //发货人城市（英文）
        requestStep1.setShipperCity(Util.isCheckNull(dbMylogisticsreturnaddress.getCityEn()));
        //发货人州（英文）
        requestStep1.setShipperState(Util.isCheckNull(dbMylogisticsreturnaddress.getProvinceEn()));
        //退件城市（英文）
        requestStep1.setReturnCity(Util.isCheckNull(dbMylogisticsreturnaddress.getCityEn()));
        List<UbiRequestStep1> datalist = new ArrayList<UbiRequestStep1>();
        datalist.add(requestStep1);

        return JSONObject.toJSONString(datalist);
    }

    public TrackResultModel getChannels(String key,String token){
        String url_channels=URL+"/services/shipper/service-catalog";
        ResultCode resultCode = new ResultCode();
        TrackResultModel trackResultModel = new TrackResultModel();
        SimpleDateFormat sdf3 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        sdf3.setTimeZone(TimeZone.getTimeZone("GMT"));
        String rfc1123 = sdf3.format(new Date());
        try {
            String signStr = "GET" + "\n" + rfc1123 + "\n" + url_channels;
            sslClient = new SSLClient();
            //Authorization <Base64 Encoded HMAC SHA-1 Hash>
            String sign = Base64.getEncoder().encodeToString(hamcsha1(signStr, key));

            //请求头
            Map<String, Object> header = new HashMap<String, Object>();
            header.put("Content-Type", "application/json");
            header.put("X-WallTech-Date", rfc1123);
            header.put("Authorization", "WallTech " + token + ":" + sign);

            resultCode = sslClient.doGetHttpClient(url_channels, header, 60 * 1000);
            if(resultCode.getAck()==0){
                JSONObject resultData = JSONObject.parseObject(resultCode.getData().toString());
                String status= resultData.getString("status");
                if("Success".equals(status)){
                    trackResultModel.setFlag(0);
                    trackResultModel.setDescr(resultCode.getData().toString());
                }else{
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr(resultData.getJSONObject("errors").getString("message"));
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

    public static byte[] hamcsha1(String data, String key) {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            return mac.doFinal(data.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        CallApiUBI callApiUBI= new CallApiUBI();
//        TrackModel trackModel = new TrackModel();
//        DbMylogisticsaccount dbMylogisticsaccount = new DbMylogisticsaccount();
//        dbMylogisticsaccount.setAccessKey("rHjPlPi225b5QwUm-T_zvA");
//        dbMylogisticsaccount.setRefreshToken("pclGURJQD24wpWyR8kgrJ9");
//        Order0 order0 = new Order0();
//        order0.setTracknumber("LVS2020933300972914N1");
//        order0.setPlatformorderid("1");
//        trackModel.setOrder0(order0);
//        trackModel.setMylogisticsaccount(dbMylogisticsaccount);
//        callApiUBI.runStep2(trackModel);

        callApiUBI.getChannels("rHjPlPi225b5QwUm-T_zvA","pclGURJQD24wpWyR8kgrJ9");
    }
}
