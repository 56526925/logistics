package org.baifei.modules.api;

import com.alibaba.fastjson.JSONObject;
import org.baifei.common.util.*;
import org.baifei.modules.entity.Order0;
import org.baifei.modules.entity.Orderitem0;
import org.baifei.modules.entity.Orderplus0;
import org.baifei.modules.entity.request.common.PropertyJson;
import org.baifei.modules.entity.request.vova.*;
import org.baifei.modules.entity.response.common.*;
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

/****
 *
 *
 *      VOVA物流api
 *
 *
 * ****/
@Component
public class CallApiVOVA {
    @Autowired
    private SSLClient sslClient;

    private String serviceUrl ="https://vovapost-api.vova.com/v1";

    //创建订单
    public TrackResultModel runStep1(TrackModel trackModel){
        TrackResultModel trackResultModel = new TrackResultModel();
        String createOrderUrl = serviceUrl+"/order/CreateLogisticsOrder";
        Map header = new HashMap();
        header.put("Content-Type","application/json");
        try{
            String requestJson =getDataStep1(trackModel);
            ResultCode resultCode = sslClient.doPostHttpClient(createOrderUrl,header,null,requestJson,60*1000);
            if(resultCode.getAck()==0){
                String resultStr = resultCode.getData().toString();
               JSONObject result = JSONObject.parseObject(resultStr);
                if("20000".equals(result.getString("code"))){
                     String trackingNumber = result.getString("vovapost_order_sn");
                     trackResultModel.setTrackNumber1(trackingNumber);
                     trackResultModel.setFlag(2);
                     trackResultModel.setDescr("调用接口成功，返回Vova单号："+trackingNumber+",稍后获取物流单号");
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
    public TrackResultModel runStep2(TrackModel trackModel){
        TrackResultModel trackResultModel = new TrackResultModel();
        DbShop shop = trackModel.getShop();
        Order0 order0 = trackModel.getOrder0();
        String createOrderUrl = serviceUrl+"/order/GetOrderStatus";
        Map header = new HashMap();
        header.put("Content-Type","application/json");
        try{
            String requestJson ="{\n" +
                    "  \"access_token\": \""+shop.getTokenid()+"\",\n" +
                    "  \"vovapost_order_sn\": [\n" +
                    "    \""+order0.getTracknumber1()+"\"\n" +
                    "  ]\n" +
                    "}";
            ResultCode resultCode = sslClient.doPostHttpClient(createOrderUrl,header,null,requestJson,60*1000);
            if(resultCode.getAck()==0){
                String resultStr = resultCode.getData().toString();
                JSONObject result = JSONObject.parseObject(resultStr);
                if("20000".equals(result.getString("code"))){
                    String trackingNumber = result.getJSONArray("orders").getJSONObject(0).getString("logistics_tracking_number");
                    trackResultModel.setTrackNumber(trackingNumber);
                    trackResultModel.setFlag(3);
                    trackResultModel.setDescr("调用接口成功，返回物流单号："+trackingNumber+"");
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
    public TrackResultModel runStep3(TrackModel trackModel){
        TrackResultModel trackResultModel = new TrackResultModel();
        DbShop shop = trackModel.getShop();
        Order0 order0 = trackModel.getOrder0();
        String labelUrl = serviceUrl+"/order/GetOrderStatus";
        Map header = new HashMap();
        header.put("Content-Type","application/json");
        try{
            String requestJson ="{\n" +
                    "  \"access_token\": \""+shop.getTokenid()+"\",\n" +
                    "  \"vovapost_order_sn\": [\n" +
                    "    \""+order0.getTracknumber1()+"\"\n" +
                    "  ]\n" +
                    "}";
            ResultCode resultCode = sslClient.doPostHttpClient(labelUrl,header,null,requestJson,60*1000);
            if(resultCode.getAck()==0){
                String resultStr = resultCode.getData().toString();
                JSONObject result = JSONObject.parseObject(resultStr);
                if("20000".equals(result.getString("code"))){
                    String pdfUrl = result.getJSONArray("orders").getJSONObject(0).getString("label_url");
                    resultCode = PdfUtil.doGetHttpClient(pdfUrl,header,60*1000,ConstantConfig.PDF_ABSOLUTE_PATH,order0.getPlatformorderid());
                    if(resultCode.getAck()==0){
                        trackResultModel.setFlag(3);
                        trackResultModel.setDescr("下载订单成功!");
                    }else{
                        trackResultModel.setFlag(98);
                        trackResultModel.setDescr("调用接口异常"+resultCode.getMsg());
                    }
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

    public TrackResultModel getChannels(String token){
        TrackResultModel trackResultModel = new TrackResultModel();
        Map header = new HashMap();
        header.put("Content-Type","application/json");
        try{
            String channelUrl ="https://merchant-api.vova.com/v1/order/ShippingCarrierList?token="+token;
            ResultCode resultCode = sslClient.doGetHttpClient(channelUrl,header,60*1000);
            if(resultCode.getAck()==0){
                String resultStr = resultCode.getData().toString();
                JSONObject result = JSONObject.parseObject(resultStr);
                if("20000".equals(result.getString("code"))){
                    trackResultModel.setFlag(0);
                    trackResultModel.setDescr(resultStr);
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


    public String getDataStep1(TrackModel trackModel){
        String time= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        PropertyJson propertyJson = trackModel.getPropertyJson();
        DbLogisticschannel logisticschannel = trackModel.getDbLogisticschannel();
        DbMylogisticsreturnaddress mylogisticsreturnaddress = trackModel.getDbMylogisticsreturnaddress();
        DbMylogisticswarehouse mylogisticswarehouse = trackModel.getDbMylogisticswarehouse();
        DbMylogisticsaccount mylogisticsaccount = trackModel.getMylogisticsaccount();
        DbShop shop = trackModel.getShop();
        Orderplus0 orderplus0 = trackModel.getOrderplus0();
        Order0 order0 = trackModel.getOrder0();
        DbMylogisticschannel mylogisticschannel = trackModel.getDbMylogisticschannel();
        List<Orderitem0> orderitem0List = trackModel.getOrderitem0();

        //最小申报价格
        double declareFeeOrigin = mylogisticschannel.getDeclareFeeOrigin();
        if(declareFeeOrigin==0){
            declareFeeOrigin =1;
        }
        //最大申报价格
        double maxDeclareFeeOrigin = mylogisticschannel.getMaxDeclareFeeOrigin();
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

        VovaRequestStep1 requestStep1 = new VovaRequestStep1();
        //商家登录令牌
        requestStep1.setAccess_token(Util.isCheckNull(shop.getTokenid()));
        //平台ID
        requestStep1.setVova_platform_id(Util.isCheckNull(mylogisticsaccount.getToken()));
        //线上仓库登录令牌(线上仓库必填)
//        requestStep1.setWh_token("");
        //UTC时间戳
        requestStep1.setTimestamp(time);
        //揽收方式(中邮小包和E邮宝,EMS,E特快必填):
        //0: decided by carrier
        //1: on site (上门揽收)
        //2: wait arrival (等待送达)
        requestStep1.setPickup_type(Util.isCheckNull(propertyJson.getSendtype()));
        //退回方式(中邮小包和E邮宝,EMS,E特快必填):
        //1: Abandoned (丢弃)
        //2: Returned (退回)
        requestStep1.setChinapost_return_type(2);
        //包裹类型(中邮小包必填):
        //1: 文件
        //2: 信函
        //3: 物品
        //4: 包裹
        requestStep1.setChinapost_mail_kind("4");
        //寄件方式(中邮小包必填):
        //HK: HK-航空
        //SAL: SAL-航空水陆路
        //SLL: SLL-水陆路
        requestStep1.setChinapost_channel("SAL");
        //面单尺寸(E邮宝,EMS,E特快必填):
        //00: print label on A4 size paper
        //01: print label on 44 paper
        //03: print label on 46 paper
        requestStep1.setChinapost_printcode("00");
        //仓库代码(中邮小包必填):
        requestStep1.setChinapost_whcode(Util.isCheckNull(mylogisticswarehouse.getCode()));
        //产品渠道ID
        requestStep1.setLogistics_product_id(Util.isCheckNull(logisticschannel.getCode()));

        //使用VOVA线上支付
//        VovaPaymentAccount paymentAccount = new VovaPaymentAccount();
//        paymentAccount.setPayer_id("");
//        paymentAccount.setPayer_name("");
//        paymentAccount.setPayer_contact_name("");
//        paymentAccount.setPayer_email("");
//        paymentAccount.setPayer_phone("");
//        paymentAccount.setPayer_mobile("");
//        requestStep1.setPayment_account(paymentAccount);

        //发件人信息
        VovaSender sender = new VovaSender();
        //电话
        sender.setPhone(Util.isCheckNull(mylogisticsreturnaddress.getPhone()));
        //国家二字码
        sender.setCountry_code(Util.isCheckNull(mylogisticsreturnaddress.getCountryCode()));
        //省份代码(E邮宝,EMS,E特快必填)
        sender.setChinapost_province_code(Util.isCheckNull(mylogisticsreturnaddress.getProvinceCode()));
        //城市代码(E邮宝,EMS,E特快必填)
        sender.setChinapost_city_code(Util.isCheckNull(mylogisticsreturnaddress.getCityCode()));
        //乡镇代码(E邮宝,EMS,E特快必填)
        sender.setChinapost_county_code(Util.isCheckNull(mylogisticsreturnaddress.getAreaCode()));
        //地址本地
        VovaAddressLocal addressLocal = new VovaAddressLocal();
        //姓名
        addressLocal.setName(Util.isCheckNull(mylogisticsreturnaddress.getContactEn()));
        //国家
        addressLocal.setCountry("China");
        //省/州
        addressLocal.setProvince(Util.isCheckNull(mylogisticsreturnaddress.getProvinceCn()));
        //城市
        addressLocal.setCity(Util.isCheckNull(mylogisticsreturnaddress.getCityCn()));
        //地址1
        addressLocal.setStreet_address1(Util.isCheckNull(mylogisticsreturnaddress.getStreetCn()));
        sender.setAddress_local(addressLocal);

        VovaAddressEn addressEn = new VovaAddressEn();
        //姓名
        addressEn.setName(Util.isCheckNull(mylogisticsreturnaddress.getContactEn()));
        //国家
        addressEn.setCountry("China");
        //省/州
        addressEn.setProvince(Util.isCheckNull(mylogisticsreturnaddress.getProvinceEn()));
        //城市
        addressEn.setCity(Util.isCheckNull(mylogisticsreturnaddress.getCityEn()));
        //地址1
        addressEn.setStreet_address1(Util.isCheckNull(mylogisticsreturnaddress.getStreetEn()));
        sender.setAddress_en(addressEn);
        requestStep1.setSender(sender);

        //揽收人信息(E邮宝,EMS,E特快必填)
        VovaChinapostPickup chinapostPickup = new VovaChinapostPickup();
        //电话
        chinapostPickup.setPhone(Util.isCheckNull(mylogisticswarehouse.getPhone()));
        //国家二字码
        chinapostPickup.setCountry_code(Util.isCheckNull(mylogisticswarehouse.getCountryCode()));
        //揽收地址
        VovaAddress pickAddress = new VovaAddress();
        //姓名
        pickAddress.setName(Util.isCheckNull(mylogisticswarehouse.getContactEn()));
        //国家
        pickAddress.setCountry(Util.isCheckNull(mylogisticswarehouse.getCountryEn()));
        //省/州
        pickAddress.setProvince(Util.isCheckNull(mylogisticswarehouse.getProvinceEn()));
        //城市
        pickAddress.setCity(Util.isCheckNull(mylogisticswarehouse.getCityEn()));
        //地址1
        pickAddress.setStreet_address1(Util.isCheckNull(mylogisticswarehouse.getStreetEn()));
        chinapostPickup.setAddress(pickAddress);
        requestStep1.setChinapost_pickup(chinapostPickup);

        //收件人信息
        VovaReceiver receiver = new VovaReceiver();
        //电话
        receiver.setPhone(Util.isCheckNull(orderplus0.getPhone1()));
        //国家二字码
        receiver.setCountry_code(Util.isCheckNull(order0.getCountrycode()));
        //地址
        VovaAddress receiverAddress = new VovaAddress();
        //姓名
        receiverAddress.setName(Util.isCheckNull(order0.getBuyername()));
        //国家
        receiverAddress.setCountry(Util.isCheckNull(order0.getCountrynameEn()));
        //省/州
        receiverAddress.setProvince(Util.isCheckNull(orderplus0.getProvince()));
        //城市
        receiverAddress.setCity(Util.isCheckNull(orderplus0.getCity()));
        //地址1
        receiverAddress.setStreet_address1(Util.isCheckNull(orderplus0.getStreet1()));
        receiver.setAddress(receiverAddress);
        requestStep1.setReceiver(receiver);

        //商品信息
        VovaParcel parcel = new VovaParcel();
        //包裹重量(中邮小包必填)
        parcel.setParcel_weight(cargo_total_weight);
        //详细信息
        List<VovaProductList> productList = new ArrayList<>();
        for(Orderitem0 orderitem0 : orderitem0List){
            VovaProductList vovaProductList = new VovaProductList();
            //order_goods_sn
            vovaProductList.setOrder_goods_sn(Util.isCheckNull(orderitem0.getSalesrecordnumber()));
            //英文描述
            vovaProductList.setDescription_en(Util.isCheckNull(orderitem0.getDeclareEnName()));
            //中文描述
            vovaProductList.setDescription_local(Util.isCheckNull(orderitem0.getDeclareCnName()));
            //英文商品类别(中邮小包必填)
            vovaProductList.setCategory_en(Util.isCheckNull(orderitem0.getDeclareEnName()));
            //中文商品类别(中邮小包必填)
            vovaProductList.setCategory_local(Util.isCheckNull(orderitem0.getDeclareCnName()));
            //产地China
            vovaProductList.setCountry_of_origin("China");
            //产地国家二字码
            vovaProductList.setCountry_of_origin_code("CN");
            //申报价值
            vovaProductList.setDeclare_value(single_value);
            //价格单位
            vovaProductList.setPrice_unit("dollar");
            //货币
            vovaProductList.setPrice_currency("USD");
            //重量
            vovaProductList.setWeight(orderitem0.getTestWeight()/1000000);
            //重量单位
            vovaProductList.setWeight_unit("kg");
            //数量
            vovaProductList.setQuantity(orderitem0.getQuantity());
            //含有电池
            vovaProductList.setHas_battery("1".equals(propertyJson.getBatteryflag())?true:false);
            //含有金属
            vovaProductList.setHas_metal(false);
            //易燃物
            vovaProductList.set_flammable(false);
            //粉末
            vovaProductList.set_powder("1".equals(propertyJson.getPowerflag())?true:false);
            //液体
            vovaProductList.set_liquid("1".equals(propertyJson.getLiquidflag())?true:false);
            //海关编码
            vovaProductList.setHs_code(orderitem0.getDeclarecustoms());
            productList.add(vovaProductList);
        }
        parcel.setProduct_list(productList);

        return JSONObject.toJSONString(requestStep1);

    }

}
