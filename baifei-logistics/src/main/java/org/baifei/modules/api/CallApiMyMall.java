package org.baifei.modules.api;

import com.alibaba.fastjson.JSONObject;
import org.baifei.common.util.ConstantConfig;
import org.baifei.common.util.PdfUtil;
import org.baifei.common.util.ResultCode;
import org.baifei.common.util.SSLClient;
import org.baifei.modules.entity.Order0;
import org.baifei.modules.entity.request.common.PropertyJson;
import org.baifei.modules.entity.request.common.RequestJson;
import org.baifei.modules.entity.response.common.DbMylogisticsaccount;
import org.baifei.modules.entity.response.common.DbShop;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/****
 *
 *
 *      MyMall物流api
 *
 *
 * ****/
@Component
public class CallApiMyMall {
    @Autowired
    private SSLClient sslClient;

    private String serviceUrl ="https://mall.my.com/merchant/api/v1/purchase/order/";



    //创建订单
    public TrackResultModel runStep1(TrackModel trackModel){
        TrackResultModel trackResultModel = new TrackResultModel();
        Order0 order0 = trackModel.getOrder0();
        DbShop shop = trackModel.getShop();
        String createOrderUrl = serviceUrl+order0.getSalesrecordnumber();
        Map header = new HashMap();
        header.put("Authorization","Bearer "+shop.getTokenid());
        try{
            ResultCode resultCode = sslClient.doGetHttpClient(createOrderUrl,header,60*1000);
            if(resultCode.getAck()==0){
                String resultStr = resultCode.getData().toString();
               JSONObject result = JSONObject.parseObject(resultStr);
                if("200".equals(result.getJSONObject("meta").getString("code"))){
                     String trackingNumber = result.getJSONObject("data").getString("trackingNumber");
                     trackResultModel.setTrackNumber(trackingNumber);
                     trackResultModel.setFlag(3);
                     trackResultModel.setDescr("调用接口成功，返回订单号："+trackingNumber);
                }else{
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr("调用接口失败"+result.getString("message"));
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

    //下载订单
    public TrackResultModel runStep2(TrackModel trackModel){
        TrackResultModel trackResultModel = new TrackResultModel();
        Order0 order0 = trackModel.getOrder0();
        DbShop shop = trackModel.getShop();
        String orderId = order0.getSalesrecordnumber();
        String token = shop.getTokenid();
        //测试
//        String orderId = "L27423NP";
//        String token = "SEV0001MTU5MjA0NjgwMXx5R2xHdEFPLTM2ZjNsOGd5TU1SYTZTMGsxaWU4ZXRxUVZhbkpZVklPcldWeGtBbHRSV2R0MG9WaEc0VmRMMmlSV0FsT3JCZFVTRVdvVDZGaGQ3MS1GbXZHbDROY3YxdUZKUDdxeVpESFg5R2lnRy12Ynk1Zk1TRE5ZVFppRzJJR2oxSUNfS0p2Szh5dHQ5Z054NWVEaS1RbmFJQ21yRnFiTjFxQk1hWHZKejFrRUpRPXyVSCxExsfudaih8aUc2q73jCRkn-xhI-JBpOY8oeLgVg==";
        Map header = new HashMap();
        header.put("Content-Type","x-www-form-urlencoded");
        header.put("Authorization","Bearer "+token);
        String orderStatusUrl=serviceUrl+orderId+"/parcel/label";
        try{
            ResultCode resultCode = PdfUtil.doGetHttpClient(orderStatusUrl,header,60*1000,ConstantConfig.PDF_ABSOLUTE_PATH,order0.getPlatformorderid());
            if(resultCode.getAck()==0){
                trackResultModel.setFlag(3);
                trackResultModel.setDescr("下载订单成功!");
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

}
