package org.baifei.modules.api;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;
import org.baifei.common.util.*;
import org.baifei.modules.entity.Order0;
import org.baifei.modules.entity.Ordercurrency0;
import org.baifei.modules.entity.Orderitem0;
import org.baifei.modules.entity.Orderplus0;
import org.baifei.modules.entity.request.common.PropertyJson;
import org.baifei.modules.entity.request.wishu.WishuOrder;
import org.baifei.modules.entity.request.wishu.WishuOrders;
import org.baifei.modules.entity.request.wishu.WishuProductItem;
import org.baifei.modules.entity.request.wishu.WishuProductItems;
import org.baifei.modules.entity.response.common.*;
import org.baifei.modules.entity.response.wishu.WishuStatusResponse;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
/****
 *
 *
 *      wishu物流api
 *
 *
 * ****/
@Component
public class CallApiWISHU {
    @Autowired
    private SSLClient sslClient;

    //创建订单
    private String createOrderUrl = "https://www.wishpost.cn/api/v2/create_order";
    //订单状态
    private String orderStatusUrl = "https://www.wishpost.cn/api/v3/order_status";
    //下载标签
    private String getLabelUrl = "https://www.wishpost.cn/api/v2/generate_label";
    //获得渠道
    private String getChannelsUrl = "https://www.wishpost.cn/api/v3/get_channels";
    //刷新令牌
    private String token_url = "https://www.wishpost.cn/api/v3/access_token/refresh";
    //wish开发者ID
    public static String wishclient_id = "57bee4ec2b343d784b9e792b";
    //wish开发者秘钥
    public static String wishclient_secret = "e86c5705ab554ce9aac02e3024e18026";

    //创建订单
    public TrackResultModel runStep1(TrackModel trackModel){
        TrackResultModel trackResultModel = new TrackResultModel();
        Map header = new HashMap();
        header.put("Content-Type","application/xml");
        try{
            String requestJson = "<?xml version=\"1.0\" ?><root>";
            requestJson+=getDataStep1(trackModel)+"</root>";

            ResultCode resultCode = sslClient.doPostHttpClient(createOrderUrl,header,null,requestJson,60*1000);

            if(resultCode.getAck()==0){
                String resultStr = resultCode.getData().toString();
                Document document = DocumentHelper.parseText(resultStr);

                Element root = document.getRootElement();//获取根节点  root
                String  status = root.element("status").getText();
                if("0".equals(status)){
                    String wish_standard_tracking_id = root.element("wish_standard_tracking_id").getText();
                    String barcodeState = root.element("barcode").attributeValue("state");
                    if("Created".equals(barcodeState)){
                        trackResultModel.setFlag(3);
                        trackResultModel.setTrackNumber(wish_standard_tracking_id);
                    }else{
                        trackResultModel.setFlag(2);
                        trackResultModel.setTrackNumber1(root.element("barcode").getText());
                    }
                }else{
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr("调用接口失败"+root.element("error_message").getText());
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

    //获取订单号
    public TrackResultModel runStep2(TrackModel trackModel,String tracknumber1){
        TrackResultModel trackResultModel = new TrackResultModel();
        DbMylogisticsaccount dbMylogisticsaccount= trackModel.getMylogisticsaccount();
        String token = dbMylogisticsaccount.getToken();
        Map header = new HashMap();
        header.put("Content-Type","application/json");
        try{
            String requestJson = "{\"access_token\": \""+token+"\",\"wish_standard_tracking_ids\": [\""+tracknumber1+"\"]}";

            ResultCode resultCode = sslClient.doPostHttpClient(orderStatusUrl,header,null,requestJson,60*1000);
            if(resultCode.getAck()==0){
                String resultStr = resultCode.getData().toString();
                WishuStatusResponse wishuStatusResponse = JSONObject.parseObject(resultStr, WishuStatusResponse.class);
                if(null != wishuStatusResponse){
                    if("0".equals(wishuStatusResponse.getCode())){
                        String tracknumber = wishuStatusResponse.getOrders().get(0).getLogistics_order_code();
                        if(!"".equals(tracknumber) && null!= tracknumber){
                            trackResultModel.setFlag(3);
                            trackResultModel.setTrackNumber(tracknumber);
                            trackResultModel.setDescr("获取订单号成功,订单号："+tracknumber);
                        }else{
                            trackResultModel.setFlag(2);
                            trackResultModel.setDescr("未获得订单号，继续获取");
                        }
                    }else{
                        trackResultModel.setFlag(98);
                        trackResultModel.setDescr(wishuStatusResponse.getMessage());
                    }
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
    public TrackResultModel runStep3(TrackModel trackModel,String tracknumber){
        TrackResultModel trackResultModel = new TrackResultModel();
        DbMylogisticsaccount dbMylogisticsaccount= trackModel.getMylogisticsaccount();
        Order0 order0 =trackModel.getOrder0();
        String token = dbMylogisticsaccount.getToken();
        Map header = new HashMap();
        header.put("Content-Type","application/xml");
        try{
            String requestXml = "<?xml version=\"1.0\" ?>" +
                    "<root>" +
                    "<access_token>"+token+"</access_token>" +
                    "<printlang>1</printlang>" +
                    "<printcode>2</printcode>" +
                    "<barcodes>" +
                    "<barcode>"+tracknumber+"</barcode>" +
                    "</barcodes>" +
                    "</root>";
            ResultCode resultCode = sslClient.doPostHttpClient(getLabelUrl,header,null,requestXml,60*1000);
            if(resultCode.getAck()==0){
                String resultStr = resultCode.getData().toString();
                Document document = DocumentHelper.parseText(resultStr);
                Element root = document.getRootElement();//获取根节点  root
                String status = root.element("status").getText();
                if("0".equals(status)){
                    String pdfUrl = root.element("PDF_URL").getText();
                    ResultCode  resultCodePdf = PdfUtil.doGetHttpClient(pdfUrl,null,60*1000, ConstantConfig.PDF_ABSOLUTE_PATH,order0.getPlatformorderid());
                    if(resultCodePdf.getAck()==0){
                        trackResultModel.setFlag(3);
                        trackResultModel.setDescr("下载订单成功!");
                    }else{
                        trackResultModel.setFlag(98);
                        trackResultModel.setDescr("调用接口异常"+resultCodePdf.getMsg());
                    }
                }else{
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr("下载订单失败!"+root.element("error_message").getText());
                }
            }else{
                String resultStr = resultCode.getMsg().toString();
                Document document = DocumentHelper.parseText(resultStr);
                Element root = document.getRootElement();//获取根节点  root
                String error_message = root.element("error_message").getText();
                trackResultModel.setFlag(98);
                trackResultModel.setDescr("调用接口失败："+error_message);
            }
        }catch (Exception e){
            e.printStackTrace();
            trackResultModel.setFlag(98);
            trackResultModel.setDescr("调用接口异常"+e.getMessage());
        }
        return trackResultModel;
    }
    //创建订单数据
    /***
     *    order0  订单信息
     *    orderplus0 订单买家信息
     *    ordercurrency0 订单金额
     *    orderitem0List 订单详情
     *    propertyJson 交运属性
     *    dbMylogisticschannel 物流方式
     *    dbLogisticschannel 物流渠道
     *    dbMylogisticsaccount 物流账号
     *    dbMylogisticsreturnaddress 回邮地址
     *    dbMylogisticswarehouse 揽收仓库
     *
     * **/
    public String getDataStep1(TrackModel trackModel){
        Order0 order0 = trackModel.getOrder0();
        Orderplus0 orderplus0 = trackModel.getOrderplus0();
        Ordercurrency0 ordercurrency0 = trackModel.getOrdercurrency0();
        List<Orderitem0> orderitem0List = trackModel.getOrderitem0();
        PropertyJson propertyJson = trackModel.getPropertyJson();
        DbMylogisticschannel dbMylogisticschannel = trackModel.getDbMylogisticschannel();
        DbLogisticschannel dbLogisticschannel = trackModel.getDbLogisticschannel();
        DbMylogisticsaccount dbMylogisticsaccount= trackModel.getMylogisticsaccount();
        DbMylogisticsreturnaddress dbMylogisticsreturnaddress = trackModel.getDbMylogisticsreturnaddress();
        DbMylogisticswarehouse dbMylogisticswarehouse = trackModel.getDbMylogisticswarehouse();

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

        /****************拼装数据******start***************/
        String postJson = "";
        XStream xStream = new XStream(new Xpp3Driver(new NoNameCoder()));
        WishuOrders wishuOrders =new WishuOrders();
        //用户认证后生成的访问令牌  token
        wishuOrders.setAccess_token(dbMylogisticsaccount.getToken());
        wishuOrders.setBid("0");
        List<WishuOrder> wishuOrderList = new ArrayList<>();
        WishuOrder wishuOrder = new WishuOrder();
        //用户提供的运单唯一标识，将跟随运单号返回，传订单号
        wishuOrder.setGuid(order0.getPlatformorderid());
        //渠道code
        wishuOrder.setOtype(dbLogisticschannel.getCode());
        //寄件人姓名
        wishuOrder.setFrom(dbMylogisticsreturnaddress.getName());
        //寄件人省名
        wishuOrder.setSender_province(dbMylogisticsreturnaddress.getProvinceEn());
        //寄件人城市名
        wishuOrder.setSender_city(dbMylogisticsreturnaddress.getCityEn());
        //寄件人区/县名
        wishuOrder.setSender_district(dbMylogisticsreturnaddress.getAreaEn());
        //寄件人地址
        wishuOrder.setSender_addres(dbMylogisticsreturnaddress.getStreetEn());
        //寄件人电话
        wishuOrder.setSender_phone(dbMylogisticsreturnaddress.getPhone());
        //寄件人邮编
        wishuOrder.setSender_zipcode(dbMylogisticsreturnaddress.getPostcode());

        //是否上门揽收 0=自送 1=上门揽收
        wishuOrder.setDoorpickup(propertyJson.getSendtype());
        //揽收服务,针对于EPC/A+/Smart渠道,如果doorpickup选择1(上门揽收),则必填25-1(4PX揽收)或者27-1(WISE揽收)或者42-0(燕文揽收),如果doorpickup选择0(自寄)，则可不填，对于其他渠道，均不填写此字段
        if("1".equals(propertyJson.getSendtype())){
            wishuOrder.setPickup_service(propertyJson.getPickupcode());
            //揽收人姓名(当地国语言),Sinotrans、IB、EPC、YunExpress和Wiseexpress必填
            wishuOrder.setPickup_from_local(dbMylogisticswarehouse.getContactEn());
            //揽收人电话,Sinotrans、IB、EPC、YunExpress和Wiseexpress必填
            wishuOrder.setPickup_phone(dbMylogisticswarehouse.getPhone());
            //揽收人邮编
            wishuOrder.setPickup_zip_code(dbMylogisticswarehouse.getPostcode());
            //揽收人地址(当地国语言),Sinotrans、IB、EPC、YunExpress和Wiseexpress必填
            wishuOrder.setPickup_addres_local(dbMylogisticswarehouse.getStreetEn());
            //揽收人省名(当地国语言),Sinotrans、IB、EPC、YunExpress和Wiseexpress必填
            wishuOrder.setPickup_province_local(dbMylogisticswarehouse.getProvinceEn());
            //揽收人城市名(当地国语言),Sinotrans、IB、EPC、YunExpress和Wiseexpress必填
            wishuOrder.setPickup_city_local(dbMylogisticswarehouse.getCityEn());
            //揽收人区/县名(当地国语言)
            wishuOrder.setPickup_district_local(dbMylogisticswarehouse.getAreaEn());
            //揽收人姓名英文
            wishuOrder.setPickup_from(dbMylogisticswarehouse.getContactEn());
            //揽收人地址英文
            wishuOrder.setPickup_addres(dbMylogisticswarehouse.getStreetEn());
            //揽收人省名英文
            wishuOrder.setPickup_province(dbMylogisticswarehouse.getProvinceEn());
            //揽收人城市名英文
            wishuOrder.setPickup_city(dbMylogisticswarehouse.getCityEn());
            //揽收人区/县名英文
            wishuOrder.setPickup_district(dbMylogisticswarehouse.getAreaEn());
            //揽收人国家(当地国语言)
            wishuOrder.setPickup_country_local(dbMylogisticswarehouse.getCountryEn());
            //揽收人国家英文
            wishuOrder.setPickup_country(dbMylogisticswarehouse.getCountryEn());
            //揽收人国家代码
            wishuOrder.setPickup_country_code(dbMylogisticswarehouse.getCountryCode());

            //揽收信息联系人(必须中文)
            wishuOrder.setReceive_from(dbMylogisticswarehouse.getContactCn());
            //揽收信息省名(必须中文)
            wishuOrder.setReceive_province(dbMylogisticswarehouse.getProvinceCn());
            //揽收信息城市名(必须中文)
            wishuOrder.setReceive_city(dbMylogisticswarehouse.getCityCn());
            //揽收信息区/县名(必须中文)
            wishuOrder.setReceive_district(dbMylogisticswarehouse.getAreaCn());
            //揽收信息(必须中文)
            wishuOrder.setReceive_addres(dbMylogisticswarehouse.getStreetCn());
            //揽收信息电话
            wishuOrder.setReceive_phone(dbMylogisticswarehouse.getPhone());
        }

        //收件人姓名(必须是英文),Wishpost EPC 选填
        wishuOrder.setTo(order0.getBuyername());
        //收件人国家（英文）
        wishuOrder.setRecipient_country(order0.getCountrynameEn());
        //收件人国家简码
        wishuOrder.setRecipient_country_short(order0.getCountrycode());
        //收件人州名（英文）
        wishuOrder.setRecipient_province(orderplus0.getProvince());
        //收件人城市名（英文）
        wishuOrder.setRecipient_city(orderplus0.getCity());
        //收件人区/县名（英文）
        wishuOrder.setRecipient_district(orderplus0.getDistrict());
        //收件人地址（英文）
        wishuOrder.setReceive_addres(orderplus0.getStreet1());
        //收件人邮编
        wishuOrder.setRecipient_postcode(orderplus0.getPostcode());
        //收件人电话
        wishuOrder.setRecipient_phone(orderplus0.getPhone1());
        //收件人姓名(到达国语言)
        wishuOrder.setTo_local(order0.getBuyername());
        //收件人国家
        wishuOrder.setRecipient_country_local(order0.getCountrynameEn());
        //收件人州名
        wishuOrder.setRecipient_province_local(orderplus0.getProvince());
        //收件人城市名
        wishuOrder.setRecipient_city_local(orderplus0.getCity());
        //收件人区/县名
        wishuOrder.setRecipient_district_local(orderplus0.getDistrict());
        //收件人地址
        wishuOrder.setRecipient_addres_local(orderplus0.getStreet1());

        //内件类型代码 //1=礼品 2=文件 3=商品货样 4=其他
        wishuOrder.setType_no(propertyJson.getProducttype());
        //英文计量单位
        wishuOrder.setUnit_measurement("item");
        //中文计量单位
        wishuOrder.setUnit_measurement_chinese("个");
        //商户自定义信息，例如商户订单号， 商品编号，库位号等
        wishuOrder.setUser_desc("");
        //WISH网站的交易金额,单位为美元， 包含物品价值和物流费用
        //wish中国默认币种是rmb (商品总售价+物流总收入)/6.5
        double trade_amount = ordercurrency0.getItemtotal().add(ordercurrency0.getShippingfee()).divide(new BigDecimal(6.5),2, BigDecimal.ROUND_HALF_UP).doubleValue();
        if(declareFeeOrigin<trade_amount){
            trade_amount = declareFeeOrigin;
        }else if(trade_amount>maxDeclareFeeOrigin){
            trade_amount = maxDeclareFeeOrigin;
        }
        wishuOrder.setTrade_amount(trade_amount);
        //分仓代码(中邮小包、IB必填)
        wishuOrder.setWarehouse_code(propertyJson.getWishwarehouse());


        WishuProductItems wishuProductItems = new WishuProductItems();
        List<WishuProductItem> wishuProductItemList = new ArrayList<>();
        for(Orderitem0 orderitem0:orderitem0List){
            WishuProductItem wishuProductItem = new WishuProductItem();
            //此种产品原产国（英文)
            wishuProductItem.setProduct_item_from_country("CN");
            //此种产品的详细名称（英文）, 必须包含至少一个英文字母
            wishuProductItem.setProduct_item_name(orderitem0.getDeclareEnName());
            //此种产品的详细中文名称（必填，必须包含中文）
            wishuProductItem.setProduct_item_name_chinese(orderitem0.getDeclareCnName());
            //此种产品数量
            wishuProductItem.setProduct_item_num(orderitem0.getQuantity());
            //此种产品的重量(此种产品的总重，非单件重量，是此种产品单重*此种产品数量)，千克（三位小数
            wishuProductItem.setProduct_item_weight(String.format("%.3f", orderitem0.getTestWeight()*orderitem0.getQuantity()/1000000));
            //此种产品申报价值（单价）(两位小数)
            wishuProductItem.setProduct_item_single_price(String.format("%.2f", trade_amount/order0.getItemquantity()));
            /********
             在Wish订单ID，所有渠道必填。
             该字段必须是英文字母、数字、或英文逗号字符（分割多个交易单号时使用）中间不得有空格。
             如不属于Wish平台交易，请填写默认值NonWishOrder，注意此类物流订单的跟踪号无法回填到Wish商户后台；
             如属于Wish平台交易号，请正确填写Wish商户后台显示的24位字符串（API接口中对应字段：order_id）。
             该字段仅支持单个ID，如：56f1205a44dc6fb51631f26
             *******/
            wishuProductItem.setWish_order_id(order0.getSalesrecordnumber());
            //是否带电, 0=不带电,1=带电
            wishuProductItem.setProduct_item_has_battery(propertyJson.getBatteryflag());
            //货物敏感类型 0=普货,1=带电,2=敏感货,此字段值为0时，product_item_has_battery字段值需置为0；此字段值为1或2时，product_item_has_battery字段值需置为1。否则，将返回错误
            wishuProductItem.setProduct_item_sensitivity_type(propertyJson.getBatteryflag());
            //产品网页链接(必填)
            String itemUrl ="https://www.wish.com/product/"+orderitem0.getItemid();
            wishuProductItem.setProduct_item_product_url(itemUrl);

            wishuProductItemList.add(wishuProductItem);
        }
        wishuProductItems.setProduct_item(wishuProductItemList);
        wishuOrder.setProduct_items(wishuProductItems);
        wishuOrderList.add(wishuOrder);
        wishuOrders.setOrder(wishuOrderList);

        xStream.alias("orders", WishuOrders.class);
        xStream.alias("order", WishuOrder.class);
        xStream.alias("product_items", WishuProductItems.class);
        xStream.alias("product_item", WishuProductItem.class);
        postJson = xStream.toXML(wishuOrders);
        System.out.println(postJson);
//        xStream.toString()
        /****************拼装数据******end***************/
        return  postJson;
    }

    //获得渠道
    public TrackResultModel getChannels(String token){
        TrackResultModel trackResultModel = new TrackResultModel();
        Map header = new HashMap();
        header.put("Content-Type","application/json");
        try{
            String requestJson = "{\"access_token\": \""+token+"\",\"channels\": []}";
            ResultCode resultCode = sslClient.doPostHttpClient(getChannelsUrl,header,null,requestJson,60*1000);
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
