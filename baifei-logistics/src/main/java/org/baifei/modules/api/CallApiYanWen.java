package org.baifei.modules.api;

import org.baifei.common.util.*;
import org.baifei.modules.entity.Order0;
import org.baifei.modules.entity.Orderitem0;
import org.baifei.modules.entity.Orderplus0;
import org.baifei.modules.entity.response.common.DbLogisticschannel;
import org.baifei.modules.entity.response.common.DbMylogisticschannel;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/************
 *
 *
 *
 *          顺丰api
 *
 *
 */
@Component
public class CallApiYanWen {
    @Autowired
    private SSLClient sslClient;


    //测试环境
//    private String servicesUrl ="http://47.96.220.163:802/service/Users/";
    //正式环境
    private String servicesUrl ="http://online.yw56.com.cn/service/Users/";
    //userid
    String userId = "100000";
    //apitoken
    String apiToken = "D6140AA383FD8515B09028C586493DDB";

    //创建订单
    public TrackResultModel runStep1(TrackModel trackModel){
        TrackResultModel trackResultModel = new TrackResultModel();
        String createOrderUrl = servicesUrl+userId+"/Expresses";
        try{
            ResultCode resultCode = new ResultCode();
            Map header = new HashMap();
            header.put("Content-Type","text/xml; charset=utf-8");
            header.put("Authorization","basic "+apiToken);
            header.put("Accept","application/xml");
            String requestXml = getDataStep1(trackModel);

            resultCode = sslClient.doPostHttpClient(createOrderUrl,header,null,requestXml,60*1000);
            if(resultCode.getAck()==0){
                Document document = DocumentHelper.parseText(resultCode.getData().toString());
                Element root = document.getRootElement();//获取根节点  root
                if(null!=root.element("Response")){
                    String tracknumber = root.element("Response").element("Epcode").getText();
                    trackResultModel.setTrackNumber(tracknumber);
                    trackResultModel.setFlag(3);
                    trackResultModel.setDescr("获取订单号成,订单号："+tracknumber);
                }else{
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr("调用接口失败"+root.element("Response").element("ReasonMessage").getText());
                }
            }else{
                trackResultModel.setFlag(98);
                trackResultModel.setDescr("调用接口失败"+resultCode.getMsg());
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
        String labelUrl = servicesUrl+userId+"/Expresses/"+order0.getTracknumber()+"/A10x10LCILabel";

        try{
            String postJson = "";
            Map params = new HashMap();
            ResultCode resultCode = new ResultCode();
            Map header = new HashMap();
            header.put("Content-Type","text/xml; charset=utf-8");
            header.put("Authorization","basic "+apiToken);
            header.put("Accept","application/xml");

            resultCode = PdfUtil.doGetHttpClient(labelUrl,header,60*1000,ConstantConfig.PDF_ABSOLUTE_PATH,order0.getPlatformorderid());
            if(resultCode.getAck()==0){
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

    public TrackResultModel getChannels(String userId,String apiToken){
        TrackResultModel trackResultModel = new TrackResultModel();
        String getChannelsUrl = servicesUrl+userId+"/GetChannels";
        try{
            ResultCode resultCode = new ResultCode();
            Map header = new HashMap();
            header.put("Content-Type","text/xml; charset=utf-8");
            header.put("Authorization","basic "+apiToken);
            header.put("Accept","application/xml");

            resultCode = sslClient.doGetHttpClient(getChannelsUrl,header,60*1000);
            if(resultCode.getAck()==0){
                trackResultModel.setFlag(0);
                trackResultModel.setDescr(resultCode.getData().toString());
            }else{
                trackResultModel.setFlag(98);
                trackResultModel.setDescr("调用接口失败"+resultCode.getMsg());
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
     *
     *
     * **/
    public  String getDataStep1(TrackModel trackModel){
        /****************拼装数据******start***************/

        String postJson = "";

        String time= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        Order0 order0 = trackModel.getOrder0();
        Orderplus0 orderplus0 = trackModel.getOrderplus0();
        List<Orderitem0> orderitem0List = trackModel.getOrderitem0();
        DbMylogisticschannel dbMylogisticschannel = trackModel.getDbMylogisticschannel();
        DbLogisticschannel dbLogisticschannel = trackModel.getDbLogisticschannel();


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
        String goods="";

        for(Orderitem0 orderitem0 :orderitem0List){
            if(single_value==0){
                single_value = orderitem0.getDeclareValue();
            }
            goods +="    <GoodsName>\n" +
                    "        <Userid>"+userId+"</Userid>\n" +
                    "        <NameCh>"+Util.isCheckNull(orderitem0.getDeclareCnName())+"</NameCh>\n" +
                    "        <NameEn>"+Util.isCheckNull(orderitem0.getDeclareEnName())+"</NameEn>\n" +
                    "        <Weight>"+ Math.round(Util.isCheckNull(orderitem0.getTestWeight())/1000)+"</Weight>\n" +
                    "        <DeclaredValue>"+single_value+"</DeclaredValue>\n" +
                    "        <DeclaredCurrency>USD</DeclaredCurrency>\n" +
                    "    </GoodsName>\n" ;
        }
        cargo_total_weight+=0.01;

        String requestXml = "<ExpressType>\n" +
                            "    <Epcode></Epcode>\n" + //运单号
                            "    <Userid>"+userId+"</Userid>\n" + //客户号
                            "    <Channel>"+dbLogisticschannel.getName()+"</Channel>\n" + //发货方式
                            "    <UserOrderNumber>"+order0.getPlatformorderid()+"</UserOrderNumber>\n" + //客户订单号
                            "    <SendDate>"+time+"</SendDate>\n" + //发货日期
                            "    <Receiver>\n" +
                            "        <Userid>"+userId+"</Userid>\n" + //客户号
                            "        <Name>"+Util.isCheckNull(order0.getBuyername())+"</Name>\n" + //收货人-姓名
                            "        <Phone>"+Util.isCheckNull(orderplus0.getPhone1())+"</Phone>\n" +// 收货人-座机，手机。美国专线至少填一项
                            "        <Mobile>"+Util.isCheckNull(orderplus0.getPhone1())+"</Mobile>\n" +
                            "        <Email>"+Util.isCheckNull(orderplus0.getEmail())+"</Email>\n" + //收货人-邮箱
                            "        <Company></Company>\n" +
                            "        <Country>"+Util.isCheckNull(order0.getCountrynameCn())+"</Country>\n" + //收货人-国家
                            "        <Postcode>"+Util.isCheckNull(orderplus0.getPostcode())+"</Postcode>\n" + //收货人-邮编
                            "        <State>"+Util.isCheckNull(orderplus0.getProvince())+"</State>\n" + //收货人-州
                            "        <City>"+Util.isCheckNull(orderplus0.getCity())+"</City>\n" + //收货人-城市
                            "        <Address1>"+Util.isCheckNull(orderplus0.getStreet1())+"</Address1>\n" +//收货人-地址1
                            "        <Address2>"+Util.isCheckNull(orderplus0.getStreet2())+"</Address2>\n" +//收货人-地址2
                            "    </Receiver>\n" +
                            "    <Memo></Memo>\n" +
                            "    <Quantity>"+Util.isCheckNull(order0.getItemquantity())+"</Quantity>\n" + //货品总数量
                            goods+
                            "</ExpressType>";
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
        String time= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        System.out.println(time);

    }

}
