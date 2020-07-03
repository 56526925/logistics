package org.baifei.modules.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.baifei.common.util.*;
import org.baifei.modules.entity.Order0;
import org.baifei.modules.entity.response.common.DbShop;
import org.baifei.modules.entity.response.sls.SlsResponseStep1;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.util.ShopeeHmacDigest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CallApiSLS {
    @Autowired
    private SSLClient sslClient;



    private String url_express = "https://partner.shopeemobile.com/api/v1/logistics/init_info/get";
    private String url_init = "https://partner.shopeemobile.com/api/v1/logistics/init";
    private String url_label ="https://partner.shopeemobile.com/api/v1/logistics/airway_bill/get_mass";
    private int shopeePartnerID = 844011;
    private String shopeeKey = "fe7312436b29fff84161340a11c2471a019f61365a421e75f203497aab6bbe0c";
    //公共参数

    //创建订单
    public TrackResultModel runStep1(TrackModel trackModel){
        String tracknumber ="";
        ResultCode resultCode = new ResultCode();
        TrackResultModel trackResultModel = new TrackResultModel();
        Long timestamp=System.currentTimeMillis()/1000;//时间戳,生成签名，拼接url需要
        Map<String, Object> param = new HashMap<>();
        DbShop shop = trackModel.getShop();
        Order0 order0 = trackModel.getOrder0();
        //请求头
        Map<String, Object> header = new HashMap<String, Object>();
        try {
            String shopid = Util.isCheckNull(shop.getAccountUsername());
            String ordersn = Util.isCheckNull(order0.getPlatformorderid());
            String params="{" +
                    "\"shopid\":"+shopid+"," +
                    "\"ordersn\":\""+ordersn+"\"," +
                    "\"partner_id\":"+shopeePartnerID+"," +
                    "\"timestamp\":"+timestamp+"" +
                    "}";
            StringBuffer sb = new StringBuffer();
            sb.append("");
            sb.append(url_express);
            sb.append("|");
            sb.append(params);

            header.put("Content-Type", "application/json");
            header.put("Authorization", ShopeeHmacDigest.hmacDigest(sb.toString(),shopeeKey));

            //第一步shopee.logistics.GetLogisticInfo

            try {
                resultCode = sslClient.doPostHttpClient(url_express, header, null ,params,60*1000 );
                //{"dropoff": {}, "pickup": {}, "info_needed": {"dropoff": []}, "request_id": "254e4a9e23c0d0ff4f7ddfc12875df40"}
                //resultCode_info.setData("{\"dropoff\": {}, \"pickup\": {}, \"info_needed\": {}, \"request_id\": \"254e4a9e23c0d0ff4f7ddfc12875df40\"}");
                JSONObject obj =  JSONObject.parseObject(resultCode.getData().toString());
                String info_needed = obj.getString("info_needed");
                JSONObject obj_need = JSONObject.parseObject(info_needed);
                String dropoff = obj_need.getString("dropoff");

                if(dropoff==null){
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr("第一步获取接口信息失败"+resultCode.getData().toString());
                    return trackResultModel;
                }
            }catch (Exception e){
                trackResultModel.setDescr("第一步获取接口信息失败"+e.getMessage());
                trackResultModel.setFlag(98);
                return trackResultModel;
            }

            //第二步 shopee.logistics.Init
            if(resultCode.getAck()!=0){
                trackResultModel.setFlag(98);
                trackResultModel.setDescr("第一步shopee.logistics.GetLogisticInfo获取接口信息失败");
                return trackResultModel;
            }

            params="{" +
                    "\"shopid\":"+shopid+"," +
                    "\"dropoff\":{" +
                    "\"sender_real_name\":\"libingqing\"" +
                    "}," +
                    "\"ordersn\":\""+ordersn+"\"," +
                    "\"partner_id\":"+shopeePartnerID+"," +
                    "\"timestamp\":"+timestamp+"" +
                    "}";
            sb = new StringBuffer();
            sb.append("");
            sb.append(url_init);
            sb.append("|");
            sb.append(params);

            header.put("Content-Type", "application/json");
            header.put("Authorization", ShopeeHmacDigest.hmacDigest(sb.toString(),shopeeKey));

            for(int i=0;i<30;i++){
                if(Util.isNull(tracknumber)){
                    resultCode = sslClient.doPostHttpClient(url_init, header, null ,params,60*1000 );
                    SlsResponseStep1 result = JSON.parseObject(resultCode.getData().toString(), SlsResponseStep1.class);
                    tracknumber=result.getTracking_number();
                    if(Util.isNull(tracknumber)) {
                        try {
                            Thread.sleep(5 * 1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                   break;
                }
            }
            if(Util.isNull(tracknumber)){
                trackResultModel.setFlag(98);
                trackResultModel.setDescr("交运失败，无法获得交易单号");
            }else{
                if(resultCode.getAck() == 0) {
                    SlsResponseStep1 result = JSON.parseObject(resultCode.getData().toString(), SlsResponseStep1.class);
                    if(result != null) {
                        trackResultModel.setFlag(3);
                        trackResultModel.setTrackNumber(tracknumber);
                        trackResultModel.setDescr("交运成功,货运单号:"+tracknumber);
                    }else {
                        trackResultModel.setFlag(98);
                        trackResultModel.setDescr("没有订单信息");
                    }
                }else {
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr(resultCode.getMsg());
                }
            }
        } catch (Exception e) {
            trackResultModel.setDescr(e.getMessage());
            trackResultModel.setFlag(98);
        }
        return trackResultModel;
    }

    //下载订单
    public TrackResultModel runStep2(TrackModel trackModel){
        TrackResultModel trackResultModel = new TrackResultModel();
        Map<String, Object> header = new HashMap<String, Object>();
        ResultCode resultCode = new ResultCode();
        String pdfurl="";
        DbShop shop = trackModel.getShop();
        Order0 order0 = trackModel.getOrder0();
        String shopId =shop.getAccountUsername();
        String orderId = order0.getPlatformorderid();
        try {
            Long timestamp=System.currentTimeMillis()/1000;
            String params="{" +
                    "\"shopid\":"+shopId+"," +
                    "\"ordersn_list\":[\""+orderId+"\"]," +
                    "\"partner_id\":"+shopeePartnerID+"," +
                    "\"timestamp\":"+timestamp+"" +
                    "}";
            StringBuffer sb = new StringBuffer();
            sb.append("");
            sb.append(url_label);
            sb.append("|");
            sb.append(params);

            header.put("Content-Type", "application/json");
            header.put("Authorization", ShopeeHmacDigest.hmacDigest(sb.toString(),shopeeKey));
            ResultCode  resultCode_info =new ResultCode();
            resultCode_info = sslClient.doPostHttpClient(url_label, header, null, params, 60 * 1000);
            if(resultCode_info.getAck()==0){
                JSONObject jsonObject = JSONObject.parseObject(resultCode_info.getData().toString());
                JSONObject result = JSONObject.parseObject(jsonObject.getString("result"));
                JSONArray airway_bills = JSONArray.parseArray(result.getString("airway_bills"));
                if(airway_bills ==null){
                    return trackResultModel;
                }
                JSONObject airway_bill = airway_bills.getJSONObject(0);
                pdfurl = airway_bill.getString("airway_bill");
                resultCode = PdfUtil.doGetHttpClient(pdfurl,null,60*1000, ConstantConfig.PDF_ABSOLUTE_PATH,orderId);
                if(resultCode.getAck()==0){
                    trackResultModel.setDescr("订单下载成功");
                    trackResultModel.setFlag(3);
                }else{
                    trackResultModel.setDescr("调取接口失败"+resultCode.getMsg());
                    trackResultModel.setFlag(98);
                }
            }else{
                trackResultModel.setDescr("调取接口失败"+resultCode_info.getMsg());
                trackResultModel.setFlag(98);
            }
        } catch (Exception e) {
            trackResultModel.setDescr(e.getMessage());
            trackResultModel.setFlag(98);
            return trackResultModel;
        }
        return trackResultModel;
    }
}
