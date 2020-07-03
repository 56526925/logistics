package org.baifei.modules.api;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.sunyou.api.model.v1_0_0.common.*;
import com.sunyou.api.model.v1_0_0.in.CreateAndConfirmPackagesRequest;
import com.sunyou.api.model.v1_0_0.in.FindShippingMethodsRequest;
import com.sunyou.api.model.v1_0_0.in.GetPackagesLabelRequest;
import com.sunyou.api.model.v1_0_0.in.GetPackagesTrackingNumberRequest;
import com.sunyou.api.model.v1_0_0.out.CreateAndConfirmPackagesResponse;
import com.sunyou.api.model.v1_0_0.out.FindShippingMethodsResponse;
import com.sunyou.api.model.v1_0_0.out.GetPackagesLabelResponse;
import com.sunyou.api.model.v1_0_0.out.GetPackagesTrackingNumberResponse;
import org.baifei.common.util.*;
import org.baifei.modules.entity.Order0;
import org.baifei.modules.entity.Orderitem0;
import org.baifei.modules.entity.Orderplus0;
import org.baifei.modules.entity.request.common.PropertyJson;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/************
 *
 *
 *
 *          顺友api
 *
 *
 */
@Component
public class CallApiSunYou {
    @Autowired
    private SSLClient sslClient;


    private String apiUrl = "http://api.sypost.com/logistics/";

    public final static String DEV_TOKEN = "AAA89F877F5D576B94D04D663E3DCE1F";//正式环境

    //创建订单
    public TrackResultModel runStep1(TrackModel trackModel){
        TrackResultModel trackResultModel = new TrackResultModel();
        String createOrderUrl =apiUrl+"createAndConfirmPackages";
        try{
            ResultCode resultCode = new ResultCode();

            String requestJson = getDataStep1(trackModel);

            resultCode = sslClient.doPostHttpClient(createOrderUrl,null,null,requestJson,60*1000);

            if(resultCode.getAck()==0){
                APIResponse<CreateAndConfirmPackagesResponse> apiResponse= JSONObject.parseObject(resultCode.getData().toString(),new TypeReference<APIResponse<CreateAndConfirmPackagesResponse>>() {});
                    CreateAndConfirmPackagesResult result = apiResponse.getData().getResultList().get(0);
                    if("success".equals(result.getProcessStatus())){
                        if(Util.isNull(result.getTrackingNumber())){
                            trackResultModel.setFlag(2);
                            trackResultModel.setDescr("调用接口成功,稍后获取追踪单号");
                        }else{
                            trackResultModel.setTrackNumber(result.getTrackingNumber());
                            trackResultModel.setFlag(3);
                            trackResultModel.setDescr("调用接口成功返回订单号:"+result.getTrackingNumber());
                        }
                    }else{
                        String errors ="";
                        for(SError sError : result.getErrorList()){
                            errors+=sError.getErrorMsg()+";";
                        }
                        trackResultModel.setFlag(98);
                        trackResultModel.setDescr("调用接口失败,"+errors);
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

    //获取追踪单号
    public TrackResultModel runStep2(TrackModel trackModel){
        TrackResultModel trackResultModel = new TrackResultModel();
        String trackingNumberUrl =apiUrl+"getPackagesTrackingNumber";
        try{
            ResultCode resultCode = new ResultCode();

            String requestJson = getDataStep2(trackModel);

            resultCode = sslClient.doPostHttpClient(trackingNumberUrl,null,null,requestJson,60*1000);

            if(resultCode.getAck()==0){
                APIResponse<GetPackagesTrackingNumberResponse> apiResponse= JSONObject.parseObject(resultCode.getData().toString(),new TypeReference<APIResponse<GetPackagesTrackingNumberResponse>>() {});
                if("success".equals(apiResponse.getAck())){
                    GetPackagesTrackingNumberResult result = apiResponse.getData().getResultList().get(0);
                    if(!Util.isNull(result.getTrackingNumber())){
                        trackResultModel.setTrackNumber(result.getTrackingNumber());
                        trackResultModel.setFlag(3);
                        trackResultModel.setDescr("调用接口成功返回订单号:"+result.getTrackingNumber());
                    }
                }else{
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr("调用接口失败,"+apiResponse.getErrorMsg());
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
    public TrackResultModel runStep3(TrackModel trackModel){
        TrackResultModel trackResultModel = new TrackResultModel();
        String labelUrl = apiUrl+"getPackagesLabel";
        Order0 order0 = trackModel.getOrder0();
        String platformOrderId = order0.getPlatformorderid();
        try{
            String postJson = "";
            ResultCode resultCode = new ResultCode();
            postJson = getDataStep3(trackModel);

            resultCode = sslClient.doPostHttpClient(labelUrl,null,null,postJson,60*1000);

            if(resultCode.getAck()==0){
                APIResponse<GetPackagesLabelResponse> result = JSONObject.parseObject(resultCode.getData().toString(),new TypeReference<APIResponse<GetPackagesLabelResponse>>() {});

                if("success".equals(result.getAck())){
                    String pdfUrl =  result.getData().getLabelPath();
                    ResultCode resultCodePdf = PdfUtil.doPostHttpClient(pdfUrl,null,null,null,60*1000,ConstantConfig.PDF_ABSOLUTE_PATH,platformOrderId);
                    if(resultCodePdf.getAck()==0){
                        trackResultModel.setFlag(3);
                        trackResultModel.setDescr("下载订单成功!");
                    }else{
                        trackResultModel.setFlag(98);
                        trackResultModel.setDescr("下载订单失败!");
                    }
                }else{
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr("请求下载订单失败!");
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

        String postJson = "";
        String time= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Order0 order0 = trackModel.getOrder0();
        Orderplus0 orderplus0 = trackModel.getOrderplus0();
        List<Orderitem0> orderitem0List = trackModel.getOrderitem0();
        DbMylogisticschannel dbMylogisticschannel = trackModel.getDbMylogisticschannel();
        DbLogisticschannel dbLogisticschannel = trackModel.getDbLogisticschannel();
        DbMylogisticsaccount dbMylogisticsaccount = trackModel.getMylogisticsaccount();
        String token = dbMylogisticsaccount.getToken();

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

        APIRequest<CreateAndConfirmPackagesRequest> apiRequest = new APIRequest<>();

        //顺友物流第三方开发者的 API Token
        apiRequest.setApiDevUserToken(DEV_TOKEN);
        //顺友物流客户公司的 API Token
        apiRequest.setApiLogUsertoken(token);

        CreateAndConfirmPackagesRequest createAndConfirmPackagesRequest = new CreateAndConfirmPackagesRequest();

        //createAndConfirmPackagesRequest
        List<CreateAndConfirmPackage> createAndConfirmPackageList = new ArrayList<>();

        CreateAndConfirmPackage createAndConfirmPackage = new CreateAndConfirmPackage();

        //客户订单号
        createAndConfirmPackage.setCustomerOrderNo(order0.getPlatformorderid());

        //邮寄方式编码(findShippingMethods接口获取)
        createAndConfirmPackage.setShippingMethodCode(dbLogisticschannel.getCode());

        //包裹总重量（单位：kg）
        createAndConfirmPackage.setPredictionWeight(cargo_total_weight);

        //收件人姓名
        createAndConfirmPackage.setRecipientName(order0.getBuyername());

        //收件人国家二字代码，
        createAndConfirmPackage.setRecipientCountryCode(order0.getCountrycode());

        //收件人邮编
        createAndConfirmPackage.setRecipientPostCode(orderplus0.getPostcode());
        //收件人省州
        createAndConfirmPackage.setRecipientState(orderplus0.getProvince());
        //收件人城市
        createAndConfirmPackage.setRecipientCity(orderplus0.getCity());
        //收件人街道
        createAndConfirmPackage.setRecipientStreet(orderplus0.getStreet1());
        //是否投保 0：不投保 1：投保
        createAndConfirmPackage.setInsuranceFlag("0");

        List<Product> productList = new ArrayList<>();

        for(Orderitem0 orderitem0 :orderitem0List){
            Product product = new Product();
            //产品 SKU
            product.setProductSku(orderitem0.getStocksku());
            //申报英文名称
            product.setDeclareEnName(orderitem0.getDeclareEnName());
            //产品数量
            product.setQuantity(orderitem0.getQuantity());
            //海关申报单价（币种：USD）
            product.setDeclarePrice(single_value);
            productList.add(product);
        }
        createAndConfirmPackage.setProductList(productList);

        createAndConfirmPackageList.add(createAndConfirmPackage);

        createAndConfirmPackagesRequest.setPackageList(createAndConfirmPackageList);

        apiRequest.setData(createAndConfirmPackagesRequest);

        postJson =  JSONObject.toJSONString(apiRequest);
        return  postJson;
        /****************拼装数据******end***************/
    }


    //获取追踪单号
    /***
     *    dbMylogisticsaccount 账号
     * **/
    public  String getDataStep2(TrackModel trackModel){
        DbMylogisticsaccount dbMylogisticsaccount = trackModel.getMylogisticsaccount();
        String token = dbMylogisticsaccount.getToken();
        String orderId = trackModel.getOrder0().getPlatformorderid();
        APIRequest<GetPackagesTrackingNumberRequest> getPackagesTrackingNumberRequestAPIRequest = new APIRequest<>();
        //顺友物流第三方开发者的 API Token
        getPackagesTrackingNumberRequestAPIRequest.setApiDevUserToken(DEV_TOKEN);
        //顺友物流客户公司的 API Token
        getPackagesTrackingNumberRequestAPIRequest.setApiLogUsertoken(token);

        GetPackagesTrackingNumberRequest getPackagesTrackingNumberRequest = new GetPackagesTrackingNumberRequest();

        List<String> orderList = new ArrayList<>();
        //客户订单号
        orderList.add(orderId);

        getPackagesTrackingNumberRequest.setCustomerNoList(orderList);

        getPackagesTrackingNumberRequestAPIRequest.setData(getPackagesTrackingNumberRequest);

        return JSONObject.toJSONString(getPackagesTrackingNumberRequestAPIRequest);
    }

    //下载面单
    public  String getDataStep3(TrackModel trackModel){
        DbMylogisticsaccount dbMylogisticsaccount = trackModel.getMylogisticsaccount();
        String token = dbMylogisticsaccount.getToken();
        String orderId = trackModel.getOrder0().getPlatformorderid();
        APIRequest<GetPackagesLabelRequest> getPackagesLabelRequestAPIRequest = new APIRequest<>();
        //顺友物流第三方开发者的 API Token
        getPackagesLabelRequestAPIRequest.setApiDevUserToken(DEV_TOKEN);
        //顺友物流客户公司的 API Token
        getPackagesLabelRequestAPIRequest.setApiLogUsertoken(token);

        GetPackagesLabelRequest getPackagesLabelRequest = new GetPackagesLabelRequest();
        List<String> orderList = new ArrayList<>();
        //客户订单号
        orderList.add(orderId);
        getPackagesLabelRequest.setCustomerNoList(orderList);
        getPackagesLabelRequest.setPackMethod("0");
        getPackagesLabelRequest.setLabelFormat("1");
        getPackagesLabelRequestAPIRequest.setData(getPackagesLabelRequest);

        return JSONObject.toJSONString(getPackagesLabelRequestAPIRequest);
    }


    public TrackResultModel getChannels(String token) {
        TrackResultModel trackResultModel = new TrackResultModel();
        String channelsUrl = apiUrl+"findShippingMethods";
        ResultCode resultCode = new ResultCode();
        try {
            String postJson = "";
            APIRequest<FindShippingMethodsRequest> apiRequest = new APIRequest<>();
            //顺友物流第三方开发者的 API Token
            apiRequest.setApiDevUserToken(DEV_TOKEN);
            //顺友物流客户公司的 API Token
            apiRequest.setApiLogUsertoken(token);

            postJson = JSONObject.toJSONString(apiRequest);

            resultCode = sslClient.doPostHttpClient(channelsUrl,null,null,postJson,60*1000);

            if(resultCode.getAck()==0){
                trackResultModel.setFlag(0);
                trackResultModel.setDescr(resultCode.getData().toString());
            }else{
                trackResultModel.setFlag(98);
                trackResultModel.setDescr("调用获取渠道接口失败");
            }
        }catch (Exception e){
            trackResultModel.setFlag(98);
            trackResultModel.setDescr("调用获取渠道接口异常"+e.getMessage());
        }
        return trackResultModel;
    }

}
