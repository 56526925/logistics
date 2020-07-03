package org.baifei.modules.api;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;
import org.baifei.common.util.ConstantConfig;
import org.baifei.common.util.PdfUtil;
import org.baifei.common.util.ResultCode;
import org.baifei.common.util.SSLClient;
import org.baifei.modules.entity.Order0;
import org.baifei.modules.entity.Ordercurrency0;
import org.baifei.modules.entity.Orderitem0;
import org.baifei.modules.entity.Orderplus0;
import org.baifei.modules.entity.request.common.PropertyJson;
import org.baifei.modules.entity.request.common.RequestJson;
import org.baifei.modules.entity.request.wishu.WishuOrder;
import org.baifei.modules.entity.request.wishu.WishuOrders;
import org.baifei.modules.entity.request.wishu.WishuProductItem;
import org.baifei.modules.entity.request.wishu.WishuProductItems;
import org.baifei.modules.entity.response.common.*;
import org.baifei.modules.entity.response.wishu.WishuStatusResponse;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.webservice.haihehui.Order;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/****
 *
 *
 *      Joom物流api
 *
 *
 * ****/
@Component
public class CallApiJoom {
    @Autowired
    private SSLClient sslClient;

    private String serviceUrl ="https://api-merchant.joom.com/api/v2";



    //创建订单
    public TrackResultModel runStep1(TrackModel trackModel){
        TrackResultModel trackResultModel = new TrackResultModel();
        Order0 order0 = trackModel.getOrder0();
        PropertyJson propertyJson = trackModel.getPropertyJson();
        RequestJson requestJson = trackModel.getRequestJson();
        DbShop shop = trackModel.getShop();
        String createOrderUrl = serviceUrl+"/order/fulfill-online";
        Map header = new HashMap();
        header.put("Content-Type","x-www-form-urlencoded");
        header.put("Authorization","Bearer "+shop.getTokenid());
        Map param = new HashMap();


        try{
            param.put("ids",order0.getSalesrecordnumber());
            ///承运商
            param.put("tracking_provider","Joom Logistics");
            //是否揽收
            if("1".equals(propertyJson.getSendtype())){
                param.put("pickup","true");
            }else {
                param.put("pickup","false");
            }
            //揽收仓库
            param.put("warehouse_id",requestJson.getWarehouseCode());

            ResultCode resultCode = sslClient.doPostHttpClient(createOrderUrl,header,param,null,60*1000);

            if(resultCode.getAck()==0){
                String resultStr = resultCode.getData().toString();
               JSONObject result = JSONObject.parseObject(resultStr);
                if("0".equals(result.getString("code"))){
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

        Map header = new HashMap();
        header.put("Content-Type","x-www-form-urlencoded");
        header.put("Authorization","Bearer "+token);
        String orderStatusUrl=serviceUrl+"/order/shipping-label?id="+orderId+"&access_token="+token;
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

    //获得渠道
    public TrackResultModel getChannels(String token){
        TrackResultModel trackResultModel = new TrackResultModel();
        String channelUrl = serviceUrl+"/trackingProviders";
        Map header = new HashMap();
        header.put("Content-Type","x-www-form-urlencoded");
        header.put("Authorization","Bearer "+token);
        try{
            ResultCode resultCode = sslClient.doGetHttpClient(channelUrl,header,60*1000);
            if(resultCode.getAck()==0){
                trackResultModel.setFlag(0);
                trackResultModel.setDescr(resultCode.getData().toString());
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
