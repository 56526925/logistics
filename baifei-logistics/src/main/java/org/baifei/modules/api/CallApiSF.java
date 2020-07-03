package org.baifei.modules.api;

import com.alibaba.fastjson.JSONObject;
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
import org.baifei.modules.webservice.sf.SfKtsServiceImplServiceLocator;
import org.baifei.modules.webservice.sf.SfKtsServiceImplServiceSoapBindingStub;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/************
 *
 *
 *
 *          顺丰api
 *
 *
 */
@Component
public class CallApiSF {
    @Autowired
    private SSLClient sslClient;


    private String labelUrl = "http://sfapi.trackmeeasy.com/ruserver/api/getLabelUrl.action";

    //创建订单
    public TrackResultModel runStep1(TrackModel trackModel){
        TrackResultModel trackResultModel = new TrackResultModel();

        try{
            ResultCode resultCode = new ResultCode();

            String requestXml = getDataStep1(trackModel);

            //签名
            String sign =  digest(requestXml, "UTF-8");

            //开始调接口获取数据
            SfKtsServiceImplServiceSoapBindingStub binding = null;
            try {
                binding = (SfKtsServiceImplServiceSoapBindingStub)
                        new SfKtsServiceImplServiceLocator().getSfKtsServiceImplPort();
            }catch (Exception e){
                e.printStackTrace();
            }
            String resultStr = binding.sfKtsService(requestXml, sign);
            //接受返回结果
            Document document = DocumentHelper.parseText(resultStr);
            Element root = document.getRootElement();//获取根节点  root
            String msg =root.element("Head").getText();
            if("OK".equals(msg)){
                String tracknumber = root.element("Body").element("OrderResponse").attribute("mailno").getValue();
                trackResultModel.setFlag(3);
                trackResultModel.setTrackNumber(tracknumber);
                trackResultModel.setDescr("获取运单号成功："+tracknumber);
            }else{
                trackResultModel.setFlag(98);
                trackResultModel.setDescr(root.element("ERROR").getText());
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
        DbMylogisticsaccount dbMylogisticsaccount = trackModel.getMylogisticsaccount();
        String username = dbMylogisticsaccount.getNickname();
        String token = dbMylogisticsaccount.getToken();
        try{
            String postJson = "";
            Map params = new HashMap();
            ResultCode resultCode = new ResultCode();
            Map<String, Object> header = new HashMap<>();

            header.put("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");

            String signature= digest(username + token, "UTF-8");
            System.out.println(signature);

            params.put("mailno", trackNumber); // 顺丰运单号组合
            params.put("orderid",platformOrderId); // 客户订单号组合
            params.put("onepdf", "false"); // 是否打印一个pdf eg:true/false
            params.put("username", username); // 接入编码
            params.put("jianhuodan", "false"); // 是否打印拣货单 eg:true/false
            params.put("signature", signature); // 签名

            resultCode = sslClient.doPostHttpClient(labelUrl,header,params,null,60*1000);

            if(resultCode.getAck()==0){
                JSONObject result = JSONObject.parseObject(resultCode.getData().toString());
                if("true".equals(result.getString("success"))){
                    String pdfUrl =  ((JSONObject)result.getJSONArray("array").get(0)).getString("url");
                    ResultCode resultCodePdf = PdfUtil.doPostHttpClient(pdfUrl,header,params,null,60*1000,ConstantConfig.PDF_ABSOLUTE_PATH,platformOrderId);
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
     *    dbMylogisticsreturnaddress 回邮地址
     *    dbMylogisticsaccount 账号
     *    propertyJson 交运属性
     *
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
        DbMylogisticsreturnaddress dbMylogisticsreturnaddress = trackModel.getDbMylogisticsreturnaddress();
        PropertyJson propertyJson = trackModel.getPropertyJson();
        DbMylogisticsaccount dbMylogisticsaccount = trackModel.getMylogisticsaccount();
        String username = dbMylogisticsaccount.getNickname();
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
        //平台1 ebay,2 amazon,3 aliexpress,4  wish,8 cdiscount,9 PM,14 tophatter,16 shopify, 17 shopee 18 alibaba,19 joom, 26 mymall, 34 vova,  42 mercadolibre 48 real  87 rumall, 88 Allegro 89 RDC 90 souq 100 YL 999 other
        String platform_code="";
        if("1".equals(order0.getPlatformid())){
            platform_code="0004";
        }else if("2".equals(order0.getPlatformid())){
            platform_code="0003";
        }else if("3".equals(order0.getPlatformid())){
            platform_code="0001";
        }else if("4".equals(order0.getPlatformid())){
            platform_code="0002";
        }else if("19".equals(order0.getPlatformid())){
            platform_code="0005";
        }else if("87".equals(order0.getPlatformid())){
            platform_code="0006";
        }else{
            platform_code="0000";
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
        String cargoStr="";

        for(Orderitem0 orderitem0 :orderitem0List){
            if(single_value==0){
                single_value = orderitem0.getDeclareValue();
            }
            cargoStr+="	    <Cargo name=\""+Util.isCheckNull(orderitem0.getDeclareEnName())+"\" \n" + //商品（英文）报关品名
                    "              count=\""+Util.isCheckNull(orderitem0.getQuantity())+"\" \n" + //货物数量
                    "			   unit=\"item\" \n" + //数量单位 货物单位（英文）如：piece
                    "              weight=\""+(Util.isCheckNull(orderitem0.getTestWeight())/1000000)+"\" \n" + //货物重量kg
                    "			   amount=\""+single_value+"\" \n" + //货物单位价值
                    "              currency=\"USD\" \n" + //单位价值单位
                    "			   cname=\""+Util.isCheckNull(orderitem0.getDeclareCnName())+"\"/>\n" ; //商品（中文）报关品名
        }
        cargo_total_weight+=0.01;

        String requestXml = "<Request service=\"OrderService\" lang=\"zh_CN\">\n" +
                "	<Head>"+username+"</Head>\n" +
                "	<Body>\n" +
                "		<Order orderid=\""+order0.getPlatformorderid()+"\"\n" +   //客户订单号，不能重复（仅限：字母、数字、中划线、下划线
                "              platform_order_id=\""+order0.getPlatformorderid()+"\" \n" + //平台订单号，不能重复（仅限：字母、数字、中划线、下划线 ）
                "              platform_code=\""+platform_code+"\" \n" +   //0000未知电商平台0001速卖通平台0002wish平台0003亚马逊平台0004ebay平台0005JOOM平台0006Rumall平台
                "              erp_code=\"0000\" \n" + //ERP平台code
                "              express_type=\""+Util.isCheckNull(dbLogisticschannel.getCode())+"\" \n" + //快件产品类别
                "              j_company=\"baifei\" \n" + //寄件方公司名称  不能包含中文
                "              j_contact=\""+Util.isCheckNull(dbMylogisticsreturnaddress.getContactEn())+"\"\n" + //寄件方联系人
                "              j_mobile=\""+Util.isCheckNull(dbMylogisticsreturnaddress.getPhone())+"\" \n" + //寄件方手机
                "              j_tel=\""+Util.isCheckNull(dbMylogisticsreturnaddress.getPhone())+"\" \n" +  //寄方电话号码
                "              j_province=\""+Util.isCheckNull(dbMylogisticsreturnaddress.getProvinceEn())+"\" \n" + //寄方所在省份
                "              j_city=\""+Util.isCheckNull(dbMylogisticsreturnaddress.getCityEn())+"\" \n" + //寄方所在城市
                "              j_address=\""+Util.isCheckNull(dbMylogisticsreturnaddress.getStreetEn())+"\" \n" +  //寄方详细地址
                "              d_contact=\""+Util.isCheckNull(order0.getBuyername())+"\" \n"+ //到件方联系人
                "              d_mobile=\""+Util.isCheckNull(orderplus0.getPhone1())+"\" \n" + //到方手机号码
                "              d_tel=\""+Util.isCheckNull(orderplus0.getPhone1())+"\" \n" + //到方电话号码
                "              d_province=\""+Util.isCheckNull(orderplus0.getProvince())+"\" \n" + //到方所在省份
                "              d_city=\""+Util.isCheckNull(orderplus0.getCity())+"\" \n" + //到方所在城市
                "              d_address=\""+Util.isCheckNull(orderplus0.getStreet1())+"\" \n" + //到方详细地址
                "			   parcel_quantity=\"1\" \n" + //包裹数（固定为1）
                "              pay_method=\"1\" \n" + //付款方式：1：寄方付
                "	           declared_value=\""+declared_value+"\" \n" + //订单托寄物声明价值=货物单价*数量 产品类型23货物申报价值不能大于2USD（美元）
                "              declared_value_currency=\"USD\" \n" + //托寄物声明价值币别：USD：美元
                "              j_country=\"CN\" \n" + //始发地
                "              j_post_code=\""+Util.isCheckNull(dbMylogisticsreturnaddress.getPostcode())+"\" \n" + //寄方邮编
                "			   d_country=\""+Util.isCheckNull(order0.getCountrycode())+"\" \n" + //到方国家
                "              d_county=\""+Util.isCheckNull(orderplus0.getDistrict())+"\" \n" +//到方县/区
                "			   d_post_code=\""+Util.isCheckNull(orderplus0.getPostcode())+"\" \n" +//到方邮编
                "              cargo_total_weight=\""+cargo_total_weight+"\" \n" + //货物总重量单位KG 此值必须大于0且不能超过2KG，且该值要大于货物单位重量X货物数量总和。
                "              cargo_length=\"10\" \n" +//货物长（单位cm）超过10位
                "			   cargo_width=\"10\" \n" + //货物宽
                "              cargo_height=\"10\" \n" + //货物高
                "			   operate_flag=\"1\" \n" + //固定值：1（确认下单）
                "              isBat=\""+Util.isCheckNull(propertyJson.getBatteryflag())+"\" \n" + //是否带电 0：带电 1 不带电
                "              remark=\"remark\">\n" + //备注
                cargoStr+
                "		</Order>\n" +
                "	</Body>\n" +
                "</Request>";
        //签名字符串
        requestXml = requestXml+token;
        return  requestXml;
        /****************拼装数据******end***************/
    }




    public static String digest(String toVerifyText, String encode) {
        String base64Str = null;
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(toVerifyText.getBytes(encode));
            byte[] md = md5.digest();

            base64Str = org.apache.commons.codec.binary.Base64.encodeBase64String(md);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return base64Str;
    }

    public static void main(String[] args) throws RemoteException {
        CallApiSF callApiSF = new CallApiSF();
//        String requestXml = "<Request service=\"OrderService\" lang=\"zh_CN\">\n" +
//                "	<Head>erptest</Head>\n" +
//                "	<Body>\n" +
//                "		<Order orderid=\"04094b0550c7d00a-5\"\n" +
//                "              platform_order_id=\"04094b0550c7d00a-5\" \n" +
//                "              platform_code=\"0000\" \n" +
//                "              erp_code=\"0000\" \n" +
//                "              express_type=\"10\" \n" +
//                "              j_company=\"baifei\" \n" +
//                "              j_contact=\"lizhanying\"\n" +
//                "              j_mobile=\"13832174546\" \n" +
//                "              j_tel=\"\" \n" +
//                "              j_province=\"hebei\" \n" +
//                "              j_city=\"sjz\" \n" +
//                "              j_address=\"sjz\" \n" +
//                "              d_contact=\"monica bertarelli\" \n" +
//                "              d_mobile=\"3479631949\" \n" +
//                "              d_tel=\"3479631949\" \n" +
//                "              d_province=\"italia\" \n" +
//                "              d_city=\"sappada\" \n" +
//                "              d_address=\"borgata Ecche 74\" \n" +
//                "			   parcel_quantity=\"1\" \n" +
//                "              pay_method=\"1\" \n" +
//                "	           declared_value=\"5.0\" \n" +
//                "              declared_value_currency=\"USD\" \n" +
//                "              j_country=\"CN\" \n" +
//                "              j_post_code=\"000005\" \n" +
//                "			   d_country=\"RU\" \n" +
//                "              d_county=\"\" \n" +
//                "			   d_post_code=\"33012\" \n" +
//                "              cargo_total_weight=\"0.9901\" \n" +
//                "              cargo_length=\"10\" \n" +
//                "			   cargo_width=\"10\" \n" +
//                "              cargo_height=\"10\" \n" +
//                "			   operate_flag=\"1\" \n" +
//                "              isBat=\"0\" \n" +
//                "              remark=\"remark\">\n" +
//                "	    <Cargo name=\"Nail Brush\" \n" +
//                "              count=\"2\" \n" +
//                "			   unit=\"item\" \n" +
//                "              weight=\"0.33\" \n" +
//                "			   amount=\"1.25\" \n" +
//                "              currency=\"USD\" \n" +
//                "			   cname=\"美甲刷\"/>\n" +
//                "	    <Cargo name=\"Nail Brush\" \n" +
//                "              count=\"1\" \n" +
//                "			   unit=\"item\" \n" +
//                "              weight=\"0.33\" \n" +
//                "			   amount=\"1.25\" \n" +
//                "              currency=\"USD\" \n" +
//                "			   cname=\"美甲刷\"/>\n" +
//                "	    <Cargo name=\"Nail Brush\" \n" +
//                "              count=\"1\" \n" +
//                "			   unit=\"item\" \n" +
//                "              weight=\"0.33\" \n" +
//                "			   amount=\"1.25\" \n" +
//                "              currency=\"USD\" \n" +
//                "			   cname=\"美甲刷\"/>\n" +
//                "		</Order>\n" +
//                "	</Body>\n" +
//                "</Request>";
//        String sign = CallApiSF.digest(requestXml+"78BE1BCAAED1EE08D344F894FBB296D3","UTF-8");
//
//        SfKtsServiceImplServiceSoapBindingStub binding = null;
//        try {
//
//            binding = (SfKtsServiceImplServiceSoapBindingStub)
//                    new SfKtsServiceImplServiceLocator().getSfKtsServiceImplPort();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        String resultStr = binding.sfKtsService(requestXml, sign);
//        System.out.println(resultStr);
//        System.out.println(sign);

        String sign = CallApiSF.digest("saituo41694BC5BB655113366AD43A2F05C373323","UTF-8");
        System.out.println(sign);
    }

}
