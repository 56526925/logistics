package org.baifei.modules.api;

import com.alibaba.fastjson.JSONObject;
import org.baifei.common.util.*;
import org.baifei.modules.entity.Order0;
import org.baifei.modules.entity.Orderitem0;
import org.baifei.modules.entity.Orderplus0;
import org.baifei.modules.entity.request.common.RequestJson;
import org.baifei.modules.entity.request.fpx.FpxRequest;
import org.baifei.modules.entity.request.fpx.LogisticsServiceInfo;
import org.baifei.modules.entity.request.fpx.OconsignmentDesc;
import org.baifei.modules.entity.request.fpx.OconsignmentSku;
import org.baifei.modules.entity.response.common.DbLogisticschannel;
import org.baifei.modules.entity.response.common.DbMylogisticsaccount;
import org.baifei.modules.entity.response.fpx.FpxWareHouseResponse;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
/****
 *
 *
 *      递四方物流api
 *
 *
 * ****/
@Component
public class CallApiFPX {
    @Autowired
    private SSLClient sslClient;
    //fu.wms.outbound.create创建订单方法
    //正式环境
    private String Url = "http://open.4px.com/router/api/service";
    //测试环境
    private String createOrderUrlTest = "http://open.sandbox.4px.com/router/api/service";
    //创建订单API接口名称
    private String createMethod = "fu.wms.outbound.create";
    //获得仓库API接口名称
    private String getWareHouse = "com.basis.warehouse.getlist";
    //获得渠道API接口名称
    private String getChannels = "com.basis.logistics_product.getlist";
    //API协议版本，参考接口版本号
    private String v = "1.0";

    private String format = "json";

    //创建订单
    public TrackResultModel runStep1(TrackModel trackModel){
        TrackResultModel trackResultModel = new TrackResultModel();

        try{

            String timestamp = String.valueOf(new Date().getTime());
            DbMylogisticsaccount mylogisticsaccount = trackModel.getMylogisticsaccount();
            //应用接入申请的AppKey
            String app_key = mylogisticsaccount.getRefreshToken();
            //密钥
            String app_secret = mylogisticsaccount.getAccessKey();
            //公共参数
            String pubParams="app_key="+app_key+"&format=json&method="+createMethod+"&timestamp="+timestamp+"&v=1.0";
            //请求参数
            String requestJson=getDataStep1(trackModel);
            String signStr =pubParams.replace("=","").replace("&","")+requestJson+app_secret;
            String sign = "&sign="+Util.getMD5(signStr);

            String postUrl = Url+"?"+pubParams+sign;

            ResultCode resultCode = sslClient.doPostHttpClient(postUrl,null,null,requestJson,60*1000);

            if(resultCode.getAck()==0){
                JSONObject jsonObject = JSONObject.parseObject(resultCode.getData().toString());
                if(jsonObject!=null){
                    if("1".equals(jsonObject.getString("result"))){
                        String tracknumber = jsonObject.getJSONObject("data").getString("4px_tracking_no");
                        trackResultModel.setTrackNumber(tracknumber);
                        trackResultModel.setFlag(3);
                        trackResultModel.setDescr(resultCode.getMsg());
                    }else{
                        trackResultModel.setFlag(98);
                        trackResultModel.setDescr(jsonObject.getString("msg"));
                    }
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

    //创建订单数据
    /***
     *    order0  订单信息
     *    orderplus0 订单买家信息
     *    orderitem0List 订单详情
     *    requestJson 账号、仓库
     *    dbLogisticschannel 物流渠道
     *
     *
     * **/
    public  String getDataStep1(TrackModel trackModel){
        Order0 order0 = trackModel.getOrder0();
        Orderplus0 orderplus0 = trackModel.getOrderplus0();
        List<Orderitem0> orderitem0List = trackModel.getOrderitem0();
        RequestJson requestJson = trackModel.getRequestJson();
        DbLogisticschannel dbLogisticschannel = trackModel.getDbLogisticschannel();

        /****************拼装数据******start***************/
        String postJson = "";
        FpxRequest fxpRequest = new FpxRequest();
        /****************拼装数据******start***************/
        //客户参考号（订单主键）
        fxpRequest.setRef_no(Util.isCheckNull(order0.getPlatformorderid()));
        //出库仓库代码（详细参考公共服务，获取仓库代码）
        fxpRequest.setFrom_warehouse_code(Util.isCheckNull(requestJson.getWarehouseCode()));
        //委托类型 可选值： S(标准出库); D(下架销毁); R(退仓出库); DS(dropshipping出库); P(FBA Pro); O(其他出库)。 *注：如果选择是标准出库，SKU的库存质量只能选择良好
        fxpRequest.setConsignment_type("S");
        //物流服务信息
        LogisticsServiceInfo logisticsServiceInfo = new LogisticsServiceInfo();
        //物流产品代码（详细参考公共服务，获取物流产品代码）
        logisticsServiceInfo.setLogistics_product_code(Util.isCheckNull(dbLogisticschannel.getCode()));
        fxpRequest.setLogistics_service_info(logisticsServiceInfo);

        //出库委托收件人信息。*注：如果国家是CN（中国），state（省）、city（市）、district（区）必须填写。其他国家state（州）或city（城市）必须填写一个。
        OconsignmentDesc oconsignmentDesc = new OconsignmentDesc();
        //收件人所在国家;国家二字码，详细参考国际二字码
        oconsignmentDesc.setCountry(Util.isCheckNull(order0.getCountrycode()));
        //收件人所在州/省
        oconsignmentDesc.setState(Util.isCheckNull(orderplus0.getProvince()));
        //收件人所在城市
        oconsignmentDesc.setCity(Util.isCheckNull(orderplus0.getCity()));
        //收件人所在区/县
        oconsignmentDesc.setDistrict(Util.isCheckNull(orderplus0.getDistrict()));
        //收件人的邮编
        oconsignmentDesc.setPost_code(Util.isCheckNull(orderplus0.getPostcode()));
        //收件人所在街道/详细地址
        oconsignmentDesc.setStreet(Util.isCheckNull(orderplus0.getStreet1()));
        //收件人名
        oconsignmentDesc.setFirst_name(Util.isCheckNull(order0.getBuyername()));
        fxpRequest.setOconsignment_desc(oconsignmentDesc);

        //出库委托SKU集合。注：单次最大支持100种SKU种类创建，如超出请分批创建。
        List<OconsignmentSku> oconsignmentSkuList = new ArrayList<>();
        for(Orderitem0 orderitem0:orderitem0List){
            OconsignmentSku oconsignmentSku = new OconsignmentSku();
            //出库SKU编码
            oconsignmentSku.setSku_code(orderitem0.getStocksku());
            //出库SKU数量
            oconsignmentSku.setQty(orderitem0.getQuantity());
            //SKU库存质量。可选值：G（良好）; B（破损）; C（违禁品）; W（淋湿）; M（霉变）; E（异常）。*注：如果选择是标准出库，SKU的库存质量只能选择良好
            oconsignmentSku.setStock_quality("G");
        }

        fxpRequest.setOconsignment_sku(oconsignmentSkuList);

        postJson=JSONObject.toJSONString(fxpRequest);
        /****************拼装数据******end***************/
        return  postJson;
    }
    //获取仓库信息
    public TrackResultModel getWarehouse(String app_key,String app_secret){
        TrackResultModel trackResultModel = new TrackResultModel();
        String timestamp = String.valueOf(new Date().getTime());
        Map header = new HashMap();
        header.put("ContentType","application/json");
        //公共参数
        String pubParams="app_key="+app_key+"&format=json&method="+getWareHouse+"&timestamp="+timestamp+"&v=1.0";
        //请求参数
        String requestJson="{\"service_code\":\"\",\"country\":\"\"}";
        String signStr =pubParams.replace("=","").replace("&","")+requestJson+app_secret;
        String sign = "&sign="+Util.getMD5(signStr);

        String postUrl = Url+"?"+pubParams+sign;

        ResultCode resultCode = sslClient.doPostHttpClient(postUrl,header,null,requestJson,60*1000);

        if(resultCode.getAck()==0){
            trackResultModel.setFlag(0);
            trackResultModel.setDescr(resultCode.getData().toString());
        }else{
            trackResultModel.setFlag(98);
            trackResultModel.setDescr("调用接口异常"+resultCode.getMsg());
        }
        return trackResultModel;
    }

    //获取渠道信息
    public TrackResultModel getChannels(String app_key,String app_secret,String wareHouseCode){
        TrackResultModel trackResultModel = new TrackResultModel();
        String timestamp = String.valueOf(new Date().getTime());
        Map header = new HashMap();
        header.put("ContentType","application/json");
        //公共参数
        String pubParams="app_key="+app_key+"&format=json&method="+getChannels+"&timestamp="+timestamp+"&v=1.0";
        //请求参数
        String requestJson="{\"service_code\": \"F\",\"category_code\": \"end\",\"source_position_code\": \""+wareHouseCode+"\"}";
        String signStr =pubParams.replace("=","").replace("&","")+requestJson+app_secret;
        String sign = "&sign="+Util.getMD5(signStr);

        String postUrl = Url+"?"+pubParams+sign;

        ResultCode resultCode = sslClient.doPostHttpClient(postUrl,header,null,requestJson,60*1000);

        if(resultCode.getAck()==0){
            trackResultModel.setFlag(0);
            trackResultModel.setDescr(resultCode.getData().toString());
        }else{
            trackResultModel.setFlag(98);
            trackResultModel.setDescr("调用接口异常"+resultCode.getMsg());
        }
        return trackResultModel;
    }

}
