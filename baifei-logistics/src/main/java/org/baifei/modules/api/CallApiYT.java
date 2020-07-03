package org.baifei.modules.api;

import cn.hutool.extra.template.engine.rythm.RythmEngine;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.sunyou.api.model.v1_0_0.common.*;
import com.sunyou.api.model.v1_0_0.in.CreateAndConfirmPackagesRequest;
import com.sunyou.api.model.v1_0_0.in.FindShippingMethodsRequest;
import com.sunyou.api.model.v1_0_0.in.GetPackagesLabelRequest;
import com.sunyou.api.model.v1_0_0.in.GetPackagesTrackingNumberRequest;
import com.sunyou.api.model.v1_0_0.out.CreateAndConfirmPackagesResponse;
import com.sunyou.api.model.v1_0_0.out.GetPackagesLabelResponse;
import com.sunyou.api.model.v1_0_0.out.GetPackagesTrackingNumberResponse;
import org.baifei.common.util.*;
import org.baifei.modules.entity.Order0;
import org.baifei.modules.entity.Orderitem0;
import org.baifei.modules.entity.Orderplus0;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


/************
 *
 *
 *
 *          云途api
 *
 *
 */
@Component
public class CallApiYT {
    @Autowired
    private SSLClient sslClient;

    //正式地址
    private String serviceUrl = "http://oms.api.yunexpress.com/api/";

    //创建订单
    public TrackResultModel runStep1(TrackModel trackModel){
        TrackResultModel trackResultModel = new TrackResultModel();
        String createOrderUrl =serviceUrl+"WayBill/CreateOrder";
        try{
            ResultCode resultCode = new ResultCode();
            Map header = new HashMap();
            header.put("Content-Type","application/json");
            header.put("Accept","application/json");

            DbMylogisticsaccount mylogisticsaccount = trackModel.getMylogisticsaccount();
            String token =mylogisticsaccount.getName()+"&"+mylogisticsaccount.getToken();

            token = Base64.getEncoder().encodeToString(token.getBytes("UTF-8"));

            header.put("Authorization","Basic "+token);

            String requestJson = getDataStep1(trackModel);

            resultCode = sslClient.doPostHttpClient(createOrderUrl,header,null,requestJson,60*1000);

            if(resultCode.getAck()==0){
                    JSONObject result = JSONObject.parseObject(resultCode.getData().toString());
                    if("0000".equals(result.getString("Code"))){
                        String  tracknumber = result.getJSONArray("Item").getJSONObject(0).getString("WayBillNumber");
                        trackResultModel.setTrackNumber(tracknumber);
                        trackResultModel.setFlag(3);
                        trackResultModel.setDescr("调用接口成功返回订单号:"+tracknumber);
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

    //下载订单
    public TrackResultModel runStep2(TrackModel trackModel){
        TrackResultModel trackResultModel = new TrackResultModel();
        String labelUrl =serviceUrl+"Label/Print";
        try{
            ResultCode resultCode = new ResultCode();
            Map header = new HashMap();
            header.put("Content-Type","application/json");
            header.put("Accept","application/json");
            DbMylogisticsaccount mylogisticsaccount = trackModel.getMylogisticsaccount();
            Order0 order0 = trackModel.getOrder0();
            
            String token =mylogisticsaccount.getName()+"&"+mylogisticsaccount.getToken();

            token = Base64.getEncoder().encodeToString(token.getBytes("UTF-8"));

            header.put("Authorization","Basic "+token);

            String requestJson = "[\""+order0.getTracknumber()+"\"]";

            resultCode = sslClient.doPostHttpClient(labelUrl,header,null,requestJson,60*1000);

            if(resultCode.getAck()==0){
                JSONObject result = JSONObject.parseObject(resultCode.getData().toString());
                if("0000".equals(result.getString("Code"))){
                    String  pdfUrl = result.getJSONArray("Item").getJSONObject(0).getString("Url");
                    resultCode = PdfUtil.doGetHttpClient(pdfUrl,null,60*1000,ConstantConfig.PDF_ABSOLUTE_PATH,order0.getPlatformorderid());
                    if(resultCode.getAck()==0){
                        trackResultModel.setFlag(3);
                        trackResultModel.setDescr("下载订单成功");
                    }else{
                        trackResultModel.setFlag(98);
                        trackResultModel.setDescr("下载订单失败"+resultCode.getMsg());
                    }
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

        YtRequestStep1 requestStep1 = new YtRequestStep1();
        //客户订单号
        requestStep1.setCustomerOrderNumber(order0.getPlatformorderid());
        //运输方式代码
        requestStep1.setShippingMethodCode(dbLogisticschannel.getCode());
        //运单包裹的件数
        requestStep1.setPackageCount(order0.getItemcount());
        //预估包裹总重量，单位kg,最多3位小数
        requestStep1.setWeight(cargo_total_weight);
        //收件人信息
        YtReceiver receiver = new YtReceiver();
        //收件人所在国家
        receiver.setCountryCode(order0.getCountrycode());
        //收件人姓
        receiver.setFirstName(order0.getBuyername());
        //收件人详细地址
        receiver.setStreet(orderplus0.getStreet1());
        //收件人所在城市
        receiver.setCity(orderplus0.getCity());
        //
        requestStep1.setReceiver(receiver);
        //发件人信息
        YtSender sender = new YtSender();
        //发件人所在国家
        sender.setCountryCode(mylogisticsreturnaddress.getCountryCode());
        //发件人姓
        sender.setFirstName(mylogisticsreturnaddress.getName());
        //发件人详细地址   FBA 必填
        sender.setStreet(mylogisticsreturnaddress.getStreetEn());
        //发件人邮编
        sender.setStreet(mylogisticsreturnaddress.getPostcode());
        //发件人电话
        sender.setPhone(mylogisticsreturnaddress.getPhone());
        requestStep1.setSender(sender);
        //申报信息
        List<YtParcels> parcelsList = new ArrayList<>();
        for(Orderitem0 orderitem0 :orderitem0List){
            YtParcels parcels = new YtParcels();
            //包裹申报名称(英文)必填
            parcels.setEName(orderitem0.getDeclareEnName());
            //包裹申报名称(中文)
            parcels.setCName(orderitem0.getDeclareCnName());
            //申报数量
            parcels.setQuantity(orderitem0.getQuantity());
            //申报价格(单价),单位USD
            parcels.setUnitPrice(single_value);
            //申报重量(单重)，单位kg,
            parcels.setUnitWeight(orderitem0.getTestWeight());
            //申报币种，默认：USD
            parcels.setCurrencyCode("USD");
            //用于填写商品SKU，FBA订单必填
            parcels.setSKU(orderitem0.getStocksku());
            parcelsList.add(parcels);
        }
        requestStep1.setParcels(parcelsList);

        return  JSONObject.toJSONString(requestStep1);
        /****************拼装数据******end***************/
    }

    public TrackResultModel getChannels(String token) {
        TrackResultModel trackResultModel = new TrackResultModel();
        String channelUrl =serviceUrl+"Common/GetShippingMethods";
        try{
            ResultCode resultCode = new ResultCode();
            Map header = new HashMap();
            header.put("Content-Type","application/json");
            header.put("Accept","application/json");

            header.put("Authorization","Basic "+token);

            resultCode = sslClient.doGetHttpClient(channelUrl,header,60*1000);

            if(resultCode.getAck()==0){
                JSONObject result = JSONObject.parseObject(resultCode.getData().toString());
                if("0000".equals(result.getString("Code"))){
                    trackResultModel.setFlag(0);
                    trackResultModel.setDescr(resultCode.getData().toString());
                }else{
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr("调用接口失败,"+result.getString("Message"));
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

    public static void main(String[] args) {
        CallApiYT apiYT = new CallApiYT();
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
