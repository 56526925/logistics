package org.baifei.modules.api;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;
import org.baifei.common.util.*;
import org.baifei.modules.entity.Order0;
import org.baifei.modules.entity.Orderitem0;
import org.baifei.modules.entity.Orderplus0;
import org.baifei.modules.entity.request.common.PropertyJson;
import org.baifei.modules.entity.request.jltz.*;
import org.baifei.modules.entity.response.common.DbLogisticschannel;
import org.baifei.modules.entity.response.common.DbMylogisticsaccount;
import org.baifei.modules.entity.response.common.DbMylogisticschannel;
import org.baifei.modules.entity.response.common.DbMylogisticsreturnaddress;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.webservice.haihehui.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
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
 *          急路驼舟api
 *
 *
 */
@Component
public class CallApiJLTZ {
    @Autowired
    private SSLClient sslClient;

    //创建订单
    public static  String authcodejltz = "be28f290562a2884b46f49994270a063";//授权码
    public static  String privateKeyjltz = "5e03f64dbcbb11e88c6800163e080166";//私钥
    public static  String createOrderUrl="http://logistics.jltzcq.com/openapi/order/create";//创建订单
    public static  String labelUrl="http://logistics.jltzcq.com/openapi/order/getlabelpdf";//标签

    //创建订单
    public TrackResultModel runStep1(TrackModel trackModel){
         TrackResultModel trackResultModel = new TrackResultModel();
        String userToken = trackModel.getMylogisticsaccount().getToken();
        try{
            ResultCode resultCode = new ResultCode();
            Map header = new HashMap();
            header.put("Content-Type","application/json");

            String requestXml = getDataStep1(trackModel);

            String signature = Util.getMD5(requestXml+privateKeyjltz).toLowerCase();

            Map params = new HashMap();
            params.put("auth",authcodejltz);
            params.put("data",requestXml);
            params.put("signature",signature);

            resultCode = sslClient.doPostHttpClient(createOrderUrl,header,params,null,60*1000);

            if(resultCode.getAck()==0){
                Document document = DocumentHelper.parseText(resultCode.getData().toString());
                Element root = document.getRootElement();//获取根节点  root
                if("0x000".equals(root.element("Code").getText())){
                    String  tracknumber = root.element("Waybill").element("Number").getText();
                    trackResultModel.setTrackNumber(tracknumber);
                    trackResultModel.setFlag(3);
                    trackResultModel.setDescr("调用接口成功返回订单号:"+tracknumber);
                }else{
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr("调用接口失败,"+root.element("Message").getText());
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
        Order0 order0 = trackModel.getOrder0();
        String platformOrderId = order0.getPlatformorderid();
        String trackNumber = order0.getTracknumber();
        try{
            String requestXml = "<Lable>\n" +
                    " <WaybillNo>"+trackNumber+"</WaybillNo>\n" +
                    "</Lable>";
            String signature = Util.getMD5(requestXml+privateKeyjltz).toLowerCase();

            Map params = new HashMap();
            params.put("auth",authcodejltz);
            params.put("data",requestXml);
            params.put("signature",signature);

            ResultCode resultCodePdf = PdfUtil.doPostHttpClient(labelUrl,null,params,null,60*1000,ConstantConfig.PDF_ABSOLUTE_PATH,platformOrderId);
            if(resultCodePdf.getAck()==0){
                trackResultModel.setFlag(3);
                trackResultModel.setDescr("下载订单成功!");
            }else{
                trackResultModel.setFlag(98);
                trackResultModel.setDescr("下载订单失败!");
            }

        }catch (Exception e){
            trackResultModel.setFlag(98);
            trackResultModel.setDescr("调用接口异常"+e.getMessage());
        }
        return trackResultModel;
    }

    public static void main(String[] args) {
        CallApiJLTZ callApiJLTZ = new CallApiJLTZ();
        TrackModel trackModel = new TrackModel();

        Order0 order0 = new Order0();
        order0.setTracknumber("RV358183402CN");
        order0.setPlatformorderid("1");
        trackModel.setOrder0(order0);
        callApiJLTZ.runStep2(trackModel);
    }

    //创建订单数据1
    /***
     *    order0  订单信息
     *    orderplus0 买家信息
     *    orderitem0List 订单详情
     *    dbMylogisticschannel 物流方式 取最大最小申报值
     *    dbMylogisticsreturnaddress 回邮地址
     *    dbMylogisticsaccount 账号
     *    propertyJson 交运属性
     *
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
        DbMylogisticsreturnaddress dbMylogisticsreturnaddress = trackModel.getDbMylogisticsreturnaddress();
        PropertyJson propertyJson = trackModel.getPropertyJson();
        XStream xStream = new XStream(new Xpp3Driver(new NoNameCoder()));

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

        cargo_total_weight+=0.01;

        JltzRequestStep1 requestStep1 = new JltzRequestStep1();
        JltzOrder order = new JltzOrder();
        //订单标识
        order.setECommerceNo(order0.getPlatformorderid());
        //仓库代码  重庆仓:W_CQ
        order.setWarehouseCode("W_CQ");
        //渠道代码：
        //陆运-中欧班列挂号类（中欧班列挂号）=E_CNPOST_CREPRESS_CPRM;
        //陆运-中欧班列平邮类（中欧班列平邮）=E_CNPOST_CREPRESS_CPNM;
        //航空-平常类资费（中邮小包-平件）=E_CNPOST_CPNM;
        //航空-挂号类和跟踪类资费（挂号和跟踪小包）=E_CNPOST_CPRMTRACK;
        //陆运-中欧班列（渝新欧）专线陆运包裹-大包=E_CNPOST_SLL_LINE_BIG
        order.setExpressCode(dbLogisticschannel.getCode());
        //包裹长度(cm)
        order.setLength("1.0");
        //包裹宽度(cm)
        order.setWidth("1.0");
        //包裹高度(cm)
        order.setHeight("1.0");
        //包裹重量
        order.setWeight(cargo_total_weight);
        //卖家信息
        JltzSender sender = new JltzSender();
        //发件人姓名
        sender.setName(dbMylogisticsreturnaddress.getContactEn());
        //电话
        sender.setTel(dbMylogisticsreturnaddress.getPhone());
        //手机
        sender.setMobile(dbMylogisticsreturnaddress.getMobile());
        //国家的编码
        sender.setCountry(dbMylogisticsreturnaddress.getCountryCode());
        //省/州
        sender.setProvince(dbMylogisticsreturnaddress.getProvinceEn());
        //城市
        sender.setCity(dbMylogisticsreturnaddress.getCityEn());
        //区
        sender.setDistrict(dbMylogisticsreturnaddress.getAreaEn());
        //详细街道地址
        sender.setAddress(dbMylogisticsreturnaddress.getStreetEn());
        //邮编
        sender.setZipCode(dbMylogisticsreturnaddress.getPostcode());
        //买家信息
        JltzReceiver receiver = new JltzReceiver();
        //收件人姓名
        receiver.setName(order0.getBuyername());
        //电话
        receiver.setTel(orderplus0.getPhone1());
        //手机
        receiver.setMobile(orderplus0.getPhone2());
        //国家
        receiver.setCountry(order0.getCountrycode());
        //省
        receiver.setProvince(orderplus0.getProvince());
        //城市
        receiver.setCity(orderplus0.getCity());
        //详细街道
        receiver.setAddress1(orderplus0.getStreet1());
        //邮编
        receiver.setZipCode(orderplus0.getPostcode());

        List<JltzItems> itemsList = new ArrayList<>();
        for(Orderitem0 orderitem0 :orderitem0List){
            if(single_value==0){
                single_value = orderitem0.getDeclareValue();
            }
            JltzItems items = new JltzItems();
            //商品ID
            items.setNo(orderitem0.getItemid());
            //商品名称（中文）
            items.setNameCN(orderitem0.getDeclareCnName());
            //商品名称（英文）
            items.setNameEN(orderitem0.getDeclareEnName());
            //商品类目名称（中文）
            items.setCategoryNameCN(orderitem0.getDeclareCnName());
            //商品类目名称（英文）
            items.setCategoryNameEN(orderitem0.getDeclareEnName());
            //商品长度(cm)
            items.setLength("1.0");
            //商品宽度(cm)
            items.setWidth("1.0");
            //商品高度(cm)
            items.setHeight("1.0");
            //商品重量 单位 g
            items.setWeight(orderitem0.getTestWeight()/1000);
            //是否含电
            items.setHasBattery(propertyJson.getBatteryflag());
            //商品单价
            items.setUnitPrice(single_value);
            //商品数量
            items.setQuantity(orderitem0.getQuantity());
            //海关编码
            items.setHsCode(orderitem0.getDeclarecustoms());
            itemsList.add(items);
        }

        order.setSender(sender);
        order.setReceiver(receiver);
        order.setItems(itemsList);
        requestStep1.setOrder(order);

        xStream.alias("order",JltzOrder.class);
        xStream.alias("sender",JltzSender.class);
        xStream.alias("receiver",JltzReceiver.class);
        xStream.alias("items",JltzItems.class);

        return  xStream.toXML(requestStep1);
        /****************拼装数据******end***************/
    }

}
