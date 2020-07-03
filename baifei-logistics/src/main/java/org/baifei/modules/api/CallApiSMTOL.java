package org.baifei.modules.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qimencloud.api.DefaultQimenCloudClient;
import com.qimencloud.api.QimenCloudClient;
import com.qimencloud.api.sceneqimen.request.AliexpressLogisticsCreatewarehouseorderRequest;
import com.qimencloud.api.sceneqimen.request.AliexpressLogisticsGetlogisticsselleraddressesRequest;
import com.qimencloud.api.sceneqimen.request.AliexpressLogisticsGetonlinelogisticsservicelistbyorderidRequest;
import com.qimencloud.api.sceneqimen.request.AliexpressLogisticsGetprintinfoRequest;
import com.qimencloud.api.sceneqimen.response.AliexpressLogisticsCreatewarehouseorderResponse;
import com.qimencloud.api.sceneqimen.response.AliexpressLogisticsGetlogisticsselleraddressesResponse;
import com.qimencloud.api.sceneqimen.response.AliexpressLogisticsGetonlinelogisticsservicelistbyorderidResponse;
import com.qimencloud.api.sceneqimen.response.AliexpressLogisticsGetprintinfoResponse;
import org.baifei.common.util.*;
import org.baifei.modules.entity.Order0;
import org.baifei.modules.entity.Orderitem0;
import org.baifei.modules.entity.Orderplus0;
import org.baifei.modules.entity.request.common.RequestJson;
import org.baifei.modules.entity.request.smtol.ChannelBean;
import org.baifei.modules.entity.request.smtol.Constant;
import org.baifei.modules.entity.request.smtol.SmtAddressBean;
import org.baifei.modules.entity.response.common.DbLogisticschannel;
import org.baifei.modules.entity.response.common.DbMylogisticschannel;
import org.baifei.modules.entity.response.common.DbShop;
import org.baifei.modules.entity.request.common.PropertyJson;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/****
 *
 *
 *      速卖通smtol物流api
 *
 *
 * ****/

@Component
public class CallApiSMTOL {
    @Autowired
    private SSLClient sslClient;
    public static String serverUrl = "http://qimen.api.taobao.com/router/qm";//奇门接口
    public static String appKey = "24709531"; // 可替换为您的沙箱环境应用的appKey
    public static String appSecret = "db0ffce8724a103e55f776b890c79a4b"; // //奇门接口
    //map存默认的地址信息
    HashMap<String, SmtAddressBean> map = null;

    //创建订单
    /***********
     *      交运属性         propertyJson
     *      物流渠道         logisticschannel
     *      订单信息         order0
     *      订单买家信息      orderplus0
     *      订单详情         orderitem0
     *      物流方式         mylogisticschannel
     *      店铺信息         shop
     *
     *
     * *************/
    public TrackResultModel runStep1(TrackModel trackModel){
        TrackResultModel trackResultModel = new TrackResultModel();
        try{
            ResultCode resultCode1 = new ResultCode();
            Map<String, Object> header = new HashMap<>();
            header.put("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");

            PropertyJson propertyJson = trackModel.getPropertyJson();
            HashMap<String, SmtAddressBean> smtaddress = null;
            Order0 order0 = trackModel.getOrder0();
            Orderplus0 orderplus0 = trackModel.getOrderplus0();
            List<Orderitem0> orderitem0 = trackModel.getOrderitem0();
            DbMylogisticschannel mylogisticschannel =trackModel.getDbMylogisticschannel();
            DbLogisticschannel dbLogisticschannel = trackModel.getDbLogisticschannel();
            DbShop shop = trackModel.getShop();
            try {
                map = this.getDbSmtaddress(trackModel);
                if(map != null && map.size() > 0){
                    ;
                }else{
                    SmtAddressBean a = new SmtAddressBean();
                    a.setId(0);
                    map.put("pickup", a);
                    map.put("sender", a);
                    map.put("refund", a);
                }
            }catch (Exception e){
                SmtAddressBean a = new SmtAddressBean();
                a.setId(0);
                map.put("pickup", a);
                map.put("sender", a);
                map.put("refund", a);
            }
            JSONObject jspickup = new JSONObject();
            JSONObject jsrefund = new JSONObject();
            JSONObject jssender = new JSONObject();

            //这里获取订单和商品信息,然后地址信息

            //todo  从本地取 smtaddress
            if (smtaddress != null && smtaddress.size() > 0) {
                if(smtaddress.get("pickup") != null){
                    jspickup.put("address_id", ""+smtaddress.get("pickup").getId());
                }else{
                    jspickup.put("address_id", ""+map.get("pickup").getId());
                }
                if(smtaddress.get("sender") != null){
                    jssender.put("address_id", ""+smtaddress.get("sender").getId());
                }else{
                    jssender.put("address_id", ""+map.get("sender").getId());
                }
                if(smtaddress.get("refund") != null){
                    jsrefund.put("address_id", ""+smtaddress.get("refund").getId());
                }else{
                    jsrefund.put("address_id", ""+map.get("refund").getId());
                }
            }else {//开始获取默认的地址信息
                jspickup.put("address_id", ""+map.get("pickup").getId());
                jsrefund.put("address_id", ""+map.get("refund").getId());
                jssender.put("address_id", ""+map.get("sender").getId());
            }
            //组装json数据
            JSONObject jsreceiver = new JSONObject();
            jsreceiver.put("phone", ""+Util.isCheckNull(orderplus0.getPhone1()));
            jsreceiver.put("fax", "");
            jsreceiver.put("member_type", "receiver");
            jsreceiver.put("trademanage_id", "wu");
            jsreceiver.put("post_code", ""+Util.isCheckNull(orderplus0.getPostcode()));
            //todo 忘记这块当时什么要求了
//        if (ask.getExpresstypeid().equals("E_PACKET")) {//很奇怪的设置要求EUB时候这个字段传地址信息，其他渠道这里传空
//            jsreceiver.put("street", ""+orderplus0.getStreet1());
//        }else {
            jsreceiver.put("street", "");
//        }
            //判断下国家简称
            if(order0.getCountrycode().length() > 3){
                order0.setCountrycode(Util.getCountryJC(order0.getCountrycode().trim()));
            }
            if (order0.getCountrycode().trim().equals("GB")) {
                order0.setCountrycode("UK");
            }else if (order0.getCountrycode().trim().equals("AA")) {
                order0.setCountrycode("US");
            }else if (order0.getCountrycode().trim().equals("RS")) {
                order0.setCountrycode("SRB");
            }else if (order0.getCountrycode().trim().equals("BL")) {
                order0.setCountrycode("PS");
            }
            jsreceiver.put("city", ""+Util.isCheckNull(orderplus0.getCity()));
            jsreceiver.put("country", ""+order0.getCountrycode());
            jsreceiver.put("address_id", "0");
            jsreceiver.put("email", ""+Util.isCheckNull(orderplus0.getEmail()));
            jsreceiver.put("county", "");
            jsreceiver.put("name", ""+order0.getBuyername());
            jsreceiver.put("province", ""+Util.isCheckNull(orderplus0.getProvince()));
//        if (ask.getExpresstypeid().equals("E_PACKET")) {//很奇怪的设置要求EUB时候这个字段street_address传6个空格，其他渠道这里地址信息
//            jsreceiver.accumulate("street_address", "      ");
//        }else {
            jsreceiver.put("street_address", ""+orderplus0.getStreet1());
//        }
            jsreceiver.put("mobile", ""+orderplus0.getPhone1());

            JSONObject jsaddress_d_t_os  = new JSONObject();
            jsaddress_d_t_os.put("receiver", jsreceiver);
            //如果是上门揽收就传揽收地址，如果是自送就不用传揽收地址（0客户自送，1上门揽收）
            if("1".equals(Util.isCheckNull(propertyJson.getSendtype()))){
                jsaddress_d_t_os.put("pickup", jspickup);
            }
            jsaddress_d_t_os.put("refund", jsrefund);
            jsaddress_d_t_os.put("sender", jssender);
            StringBuffer productbuf = new  StringBuffer();
            StringBuffer priductids = new  StringBuffer();
            double toatlDeclaredvalue = 0.0;
            for (int i = 0; i < orderitem0.size(); i++) {//这里循环检查申报价值是否超出范围
                try {
                    if(Util.isCheckNull(orderitem0.get(i).getDeclareValue())<=0){
                        orderitem0.get(i).setDeclareValue(0.1);
                    }
                    double declaredvalue = orderitem0.get(i).getDeclareValue();
                    if (Util.isCheckNull(orderitem0.get(i).getTestWeight()) <=0) {
                        orderitem0.get(i).setTestWeight(1d);
                    }else{
                        if(orderitem0.get(i).getTestWeight() < 1){//重量小于1g,默认为1g，后面转换成KG小于0.001KG，物流默认为0；
                            orderitem0.get(i).setTestWeight(1d);
                        }
                    }
                    double wieght = orderitem0.get(i).getTestWeight()/1000000;//转换重量为千克
                    if (Util.isCheckNull(declaredvalue) < 0.1) {//小于0.1美金，那么直接0.1美金
                        declaredvalue = 0.1;
                    }
                    orderitem0.get(i).setTestWeight(wieght);
                    orderitem0.get(i).setDeclareValue(declaredvalue);
                    toatlDeclaredvalue = toatlDeclaredvalue + declaredvalue*orderitem0.get(i).getQuantity();
                } catch (Exception e) {
                    ;
                }
            }
            if(mylogisticschannel != null){
                System.out.println("最小申报价值"+mylogisticschannel.getDeclareFeeOrigin());
                System.out.println("最大申报价值"+mylogisticschannel.getMaxDeclareFeeOrigin());
                if(mylogisticschannel.getDeclareFeeOrigin() <=0.0){mylogisticschannel.setDeclareFeeOrigin(0.1);}
                if (mylogisticschannel.getDeclareFeeOrigin() >0 && mylogisticschannel.getMaxDeclareFeeOrigin() > 0) {//最大申报价值最小申报价值要都大于0才生效
                    if (toatlDeclaredvalue > mylogisticschannel.getDeclareFeeOrigin() && toatlDeclaredvalue < mylogisticschannel.getMaxDeclareFeeOrigin()) {
                        //符合规则
                    }else if(toatlDeclaredvalue < mylogisticschannel.getDeclareFeeOrigin()){
                        for (int i = 0; i < orderitem0.size(); i++) {//这种以渠道最小申报价值申报
                            double declaredvalue = (orderitem0.get(i).getDeclareValue())
                                    *mylogisticschannel.getDeclareFeeOrigin()/toatlDeclaredvalue;
                            orderitem0.get(i).setDeclareValue(declaredvalue);
                        }
                    }else if (toatlDeclaredvalue > mylogisticschannel.getMaxDeclareFeeOrigin()) {
                        for (int i = 0; i < orderitem0.size(); i++) {//这种以渠道最大申报价值申报
                            double declaredvalue = (orderitem0.get(i).getDeclareValue())
                                    *mylogisticschannel.getMaxDeclareFeeOrigin()/toatlDeclaredvalue;
                            orderitem0.get(i).setDeclareValue(declaredvalue);
                        }
                    }
                }
            }else{
                ;
            }
            /**
             * 如果是捆绑商品或者是新增了商品信息
             */
            double count =0d;
            double toldeclareFee = 0.0;
            for(Orderitem0 b:orderitem0){
                if (Util.isNull(b.getItemid()) == true || productbuf.indexOf(b.getItemid())> 0) {
                    count = count + b.getTestWeight()*b.getQuantity();//新增商品或者捆绑商品的重量  COUNT=211*4=844
                    toldeclareFee = toldeclareFee + b.getDeclareValue()*b.getQuantity();
                }
                productbuf.append(","+b.getItemid());
            }

            //以上是地址信息，下面是商品信息
            JSONArray jsonArraydeclare_product_d_t_os = new JSONArray();
            for (int i = 0; i < orderitem0.size(); i++) {
                Orderitem0 orderitem = orderitem0.get(i);
                try {
                    //判断itemid是不是包含在productids，包含，说明已经拼过了
                    if (Util.isNull(orderitem.getItemid()) == true || priductids.indexOf(orderitem.getItemid())> 0) {
                        continue;
                    }
                    if(orderitem.getOriginorderid()!= null
                            && orderitem.getOriginorderid().intValue()== 0
                    ){//originid=null 说明是手动添加的商品  一般当作赠品处理不需要提交给平台
                        JSONObject jsitem = new JSONObject();
                        //是否非液体化妆品   1含非液体化妆品，0不含
                        if(Util.isNotNull(propertyJson.getCosmeticsflag()) && ("0".equals(Util.isCheckNull(propertyJson.getCosmeticsflag()).trim()) || "1".equals(Util.isCheckNull(propertyJson.getCosmeticsflag()).trim()))){
                            if("1".equals(propertyJson.getCosmeticsflag().trim())){
                                jsitem.put("aneroid_markup", "true");
                            }else{
                                jsitem.put("aneroid_markup", "false");//默认不含
                            }
                        }else{
                            if(Util.isNull(orderitem.getNoliquidcosmetic())){orderitem.setNoliquidcosmetic("0");}
                            if("1".equals(orderitem.getNoliquidcosmetic().trim())){
                                jsitem.put("aneroid_markup", "true");
                            }else{
                                jsitem.put("aneroid_markup", "false");//默认不含
                            }
                        }
                        //海关编码临时取商品目录中的
                        jsitem.put("hs_code", ""+orderitem.getDeclarecustoms());
                        jsitem.put("breakable", "false");//是否易碎品
                        jsitem.put("product_id", ""+orderitem.getItemid());
                        jsitem.put("sc_item_id", ""+orderitem.getItemid());

                        //如果是捆绑商品或者新增商品，这个商品的申报金额和重量都直接会累加到第一个商品里；
                        if(i==0){
                            jsitem.put("product_declare_amount", orderitem.getDeclareValue()*orderitem.getQuantity()+toldeclareFee);
                            jsitem.put("product_weight", orderitem.getTestWeight()*orderitem.getQuantity()+count);

                        }else{
                            jsitem.put("product_declare_amount", orderitem.getDeclareValue()*orderitem.getQuantity());
                            jsitem.put("product_weight", orderitem.getTestWeight()*orderitem.getQuantity());
                        }
                        //jsitem.accumulate("category_en_desc", ""+category.getCategoryenname());
                        jsitem.put("category_en_desc", ""+orderitem.getDeclareEnName());
                        jsitem.put("product_num", orderitem.getQuantity());
                        //是否纯电池，取商品里的字段  ,1纯电池，0不是
                        jsitem.put("only_battery", "false");//默认不是纯电池
                        jsitem.put("sc_item_name", ""+orderitem.getDeclareEnName());
                        jsitem.put("sc_item_code", ""+orderitem.getDeclareEnName());
                        jsitem.put("category_cn_desc", ""+orderitem.getDeclareCnName());
                        jsitem.put("sku_code", orderitem.getSkuname());
                        jsitem.put("sku_value", orderitem.getPlatformsku());
                        //是否包含电池    1包含电池，0不包含电池
                        if(Util.isNotNull(propertyJson.getBatteryflag()) && ("0".equals(Util.isCheckNull(propertyJson.getBatteryflag()).trim()) || "1".equals(Util.isCheckNull(propertyJson.getBatteryflag())))){
                            if("1".equals(propertyJson.getBatteryflag())){
                                jsitem.put("contains_battery","true");
                            }else {
                                jsitem.put("contains_battery", "false");
                            }
                        }
                        jsonArraydeclare_product_d_t_os.add(jsitem);

                    }else{
                        continue;//说明不是订单的原始商品，跳过
                    }
                } catch (Exception e) {
                    ;
                }
                priductids.append(","+orderitem.getItemid());
            }
            if(jsonArraydeclare_product_d_t_os.size() < 0){
                System.out.println("订单"+order0.getPlatformorderid()+"提交失败：没有找到原始商品信息");
                trackResultModel.setFlag(98);
                trackResultModel.setDescr("订单"+order0.getPlatformorderid()+"提交失败：没有找到原始商品信息");
            }else{
                QimenCloudClient client = new DefaultQimenCloudClient(this.serverUrl, this.appKey, this.appSecret);
                AliexpressLogisticsCreatewarehouseorderRequest req = new AliexpressLogisticsCreatewarehouseorderRequest();
                req.setId(shop.getAccountUsername());
                req.setTradeOrderId(Long.valueOf(order0.getSalesrecordnumber()));//交易订单号
                req.setAddressDTOs(jsaddress_d_t_os.toString());//地址信息
                req.setDeclareProductDTOs(jsonArraydeclare_product_d_t_os.toString());//申报产品信息,
                req.setDomesticLogisticsCompany("韵达快递");//国内快递公司名称
                req.setDomesticLogisticsCompanyId(Long.valueOf(propertyJson.getDeliverytype()));//国内快递ID
                req.setDomesticTrackingNo(Util.getCtime().substring(0,8));//国内快递运单号,长度1-32
                req.setTradeOrderFrom("ESCROW");//订单来源
                req.setWarehouseCarrierService(dbLogisticschannel.getCode());//物流渠道
                req.setUndeliverableDecision(Long.valueOf(propertyJson.getUndeliverabledecision()));//不可达处理(退回:0/销毁:1)
                req.setTargetAppKey(this.appKey);

                //控制台输出start
                JSONObject obj_json = new JSONObject();
                obj_json.put("Id", shop.getAccountUsername());
                obj_json.put("TradeOrderId", Long.valueOf(order0.getSalesrecordnumber()));
                obj_json.put("AddressDTOs", jsaddress_d_t_os.toString());
                obj_json.put("DeclareProductDTOs", jsonArraydeclare_product_d_t_os.toString());
                obj_json.put("DomesticLogisticsCompany", "韵达快递");//国内快递公司名称
                obj_json.put("DomesticLogisticsCompanyId", Long.valueOf(propertyJson.getDeliverytype()));//国内快递ID
                obj_json.put("DomesticTrackingNo", Util.getCtime().substring(0,8));//国内快递运单号,长度1-32
                obj_json.put("TradeOrderFrom", "ESCROW");
                obj_json.put("WarehouseCarrierService", dbLogisticschannel.getCode());//实际发货物流服务
                obj_json.put("UndeliverableDecision", Long.valueOf(propertyJson.getUndeliverabledecision()));//不可达处理(退回:0/销毁:1)
                obj_json.put("TargetAppKey", this.appKey);
                System.out.println("订单："+order0.getPlatformorderid()+"上传数据："+obj_json.toString());
                //控制台输出end

                AliexpressLogisticsCreatewarehouseorderResponse rsp = null;
                //ShowMsg("速卖通线上发货", "开始提交第"+i+"个订单"+obj.getTradeOrderId()+"到速卖通线上发货");
                String response = "";
                try {
                    rsp = client.execute(req);
                    response = rsp.getBody();
                    System.out.println("创建订单到速卖通线上发货走奇门后返回的数据："+response);
                    JSONObject jsonobj =null;
                    String warehouseOrderId = "";
                    String out_order_id = "";//看奇门接口这个可能是运单号
                    if(rsp.getResultSuccess() != null && rsp.getResultSuccess()==true){
                        jsonobj = JSONObject.parseObject(response);
                        String response1 = jsonobj.getString("response");
                        JSONObject response_obJ = JSONObject.parseObject(response1);
                        if (response1.indexOf("result") >= 0) {
                            String resultString = response_obJ.getString("result");
                            JSONObject result_obJ = JSONObject.parseObject(resultString);
                            if (resultString.indexOf("warehouse_order_id") >= 0) {
                                warehouseOrderId = Util.isCheckNull(result_obJ.getString("warehouse_order_id"));
                            }
                            if (resultString.indexOf("out_order_id") >= 0) {
                                out_order_id = Util.isCheckNull(result_obJ.getString("out_order_id"));
                            }
                        }
                        String result = "error";
                        String isSuccess = response_obJ.getString("result_success");
                        if(jsonobj!=null && "true".equals(isSuccess)){
                            System.out.println("提交订单"+order0.getSalesrecordnumber()+"到速卖通平台成功");
                            String resultString = response_obJ.getString("result");
                            JSONObject result_obJ = JSONObject.parseObject(resultString);
                            String success_status = result_obJ.getString("success");
                            String sqlString = "";

                            if("true".equals(success_status)){

                                if (Util.isNotNull(out_order_id)) {
                                    System.out.println("订单获取运单号成功!");
                                    String resultData="{" +
                                            "\"code\":0," +
                                            "\"message\":\"获取物流单号成功\"," +
                                            "\"expressid\":\""+Util.isCheckNull(out_order_id)+"\"," +
                                            "\"orderid\":\""+Util.isCheckNull(order0.getPlatformorderid())+"\""+
                                            "}";
                                    trackResultModel.setFlag(3);
                                    trackResultModel.setDescr(resultData);
                                    trackResultModel.setTrackNumber(out_order_id);
                                }else {
                                    trackResultModel.setFlag(98);
                                    trackResultModel.setDescr("调取接口成功但是没有获取到物流单号这是搞什么？");
                                }

                                //String updateAsk = " update db_asktradernumber set errflag='2',errdescr='订单申请成功',customertel1='0' "+sqlString+" where sequenceid='"+order.getOrderid()+"'";
                                //ebayMgr.updateExecuteSQL(updateAsk);

                                if(Util.isNotNull(warehouseOrderId)==true){//赖骏那个客户死活要这个单号，说到运费的时候要用；  20170907
//                                    String update = " update db_order set customerreserve6='"+warehouseOrderId+"' where orderid='"+order.getOrderid()+"'";
//                                    ebayMgr.updateExecuteSQL(update);
                                    System.out.println("订单获取运单号成功!");
                                    String resultData="{" +
                                            "\"code\":0," +
                                            "\"message\":\"获取物流单号成功\"," +
                                            "\"out_order_id\":\""+Util.isCheckNull(out_order_id)+"\"," +
                                            "\"warehouseOrderId\":\""+Util.isCheckNull(warehouseOrderId)+"\"," +
                                            "\"orderid\":\""+Util.isCheckNull(order0.getPlatformorderid())+"\""+
                                            "}";
                                    trackResultModel.setFlag(3);
                                    trackResultModel.setDescr(resultData);
                                    trackResultModel.setTrackNumber(out_order_id);
                                }
                            }else{
                                String error_desc = response_obJ.getString("error_desc");
                                error_desc = error_desc.replaceAll("'", "");
                                //String updateAsk = " update db_asktradernumber set errflag='98',errdescr='订单申请失败，原因："+error_desc+"',field1='订单申请失败，原因："+error_desc+"'  where sequenceid='"+order.getOrderid()+"'";
                                //ebayMgr.updateExecuteSQL(updateAsk);
                                trackResultModel.setFlag(98);
                                trackResultModel.setDescr("订单申请失败，原因："+error_desc);
                            }
                        }else{
                            System.out.println("提交订单"+order0.getPlatformorderid()+"到速卖通平台失败;原因："+response);
                            result = response.replace("'", "''");
                            String error="";
                            if(result.indexOf("wlTradeProductInfoDTO can not be empty")>0){
                                error="合并商品不能交运:建议先交运在合并!(wlTradeProductInfoDTO can not be empty!)";
                            }else if(result.indexOf("streetAddress can not be empty and length can not exceed 90")>0){
                                error="收件人地址长度不能超过90!(streetAddress can not be empty and length can not exceed 90!)";
                            }else if(result.indexOf("{\"stackTrace\":[]}")>0){
                                error="JSON格式错误：请检查收件人地址中是否有特殊字符或者双引号、单引号等";
                            }else if(result.indexOf("tradeOrder country not  support")>0){
                                error="该订单不支持线上发货,请检查！(tradeOrder country not  support!)";
                            }else if(result.indexOf("please signing the Shipping Fee agreement")>0){
                                error="请到速卖通平台检查！(please signing the Shipping Fee agreement!)";
                            }else if(result.indexOf("pickup city must be Chinese")>0){
                                error="请检查买家地址是否含有不是英文的其他字符(pickup city must be Chinese!)";
                            }else if(result.indexOf("EnCategoryName")>0 && result.indexOf("please enter English only")>0){
                                error="商品目录英文名只能是英文(eclareProductDTOs.EnCategoryName:please enter English only!)";
                            }else if(result.indexOf("pickup city must be Chinese")>0 && result.indexOf("pickupAddress")>0){
                                error="请检查揽收地址是否不是中文";
                            }else if(result.indexOf(" is not longer supported")>0 && result.indexOf("longer supported")>0){
                                error="此物流渠道不再支持!";
                            }else{
                                error=result;
                            }
                            try {
                                if(error.length()>500){
                                    error=error.substring(0, 500);
                                }
                                //String updateSQL = "update db_asktradernumber set errflag='98',errdescr='"+error+"' where sequenceid='"+order.getOrderid()+"'";
                                //ebayMgr.updateExecuteSQL(updateSQL);
                                trackResultModel.setFlag(98);
                                trackResultModel.setDescr("订单申请失败，原因："+error);
                            } catch (Exception e) {
                                //String updateSQL = "update db_asktradernumber set errflag='98',errdescr='"+e.getMessage()+"' where sequenceid='"+order.getOrderid()+"'";
                                //ebayMgr.updateExecuteSQL(updateSQL);
                                trackResultModel.setFlag(98);
                                trackResultModel.setDescr("订单申请失败，原因："+e.getMessage());
                            }
                        }
                    }else{
                        jsonobj = JSONObject.parseObject(response);
                        JSONObject date_obj = jsonobj.getJSONObject("response");
                        String message = date_obj.getString("message");
                        String sub_message = "";
                        //奇门也是坑，有时候有sub_message，有时候没有
                        if(date_obj.toString().indexOf("sub_message") >= 0){
                            sub_message = date_obj.getString("sub_message");
                        }
                        message = (message+","+sub_message).replaceAll("'", "");
//                        String updateSQL = "update db_asktradernumber set errflag='98',errdescr='提交订单失败，原因:"+message+"' where sequenceid='"+order0.getId()+"'";
                        //ebayMgr.updateExecuteSQL(updateSQL);
                        //ShowMsg("线程运行", "提交订单"+order.getTradeid()+"到速卖通平台失败;原因："+message);
                        trackResultModel.setFlag(98);
                        trackResultModel.setDescr("订单申请失败，原因："+message);
                    }
                }catch (Exception e) {
                    //String updateSQL = "update db_asktradernumber set errflag='98',errdescr='提交订单失败，原因:"+e.getMessage()+";"+response+"' where sequenceid='"+order.getOrderid()+"'";
                    //ebayMgr.updateExecuteSQL(updateSQL);
                    //ShowMsg("线程运行", "提交订单"+order.getTradeid()+"到速卖通平台失败;原因："+e.getMessage());
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr("订单申请失败，原因："+e.getMessage());
                }
            }
        }catch (Exception e){
            trackResultModel.setFlag(98);
            trackResultModel.setDescr("调用接口异常"+e.getMessage());
        }
        return trackResultModel;
    }

    //下载订单
    /***********
     *   shop   店铺信息
     *   order0 订单信息
     * **********/
    public TrackResultModel runStep2(TrackModel trackModel){
        TrackResultModel trackResultModel = new TrackResultModel();
        try{
            DbShop shop = trackModel.getShop();
            Order0 order0 = trackModel.getOrder0();
            if(Util.isNull(shop.getAccountUsername()) || Util.isNull(order0.getTracknumber())){
                trackResultModel.setFlag(98);
                trackResultModel.setDescr("订单id为空或货运单号为空");
                return trackResultModel;
            }
            QimenCloudClient client = new DefaultQimenCloudClient(this.serverUrl, this.appKey, this.appSecret);
            AliexpressLogisticsGetprintinfoRequest req = new AliexpressLogisticsGetprintinfoRequest();
            req.setId(shop.getAccountUsername());
            req.setTargetAppKey(this.appKey);
            req.setInternationalLogisticsId(order0.getTracknumber());
            AliexpressLogisticsGetprintinfoResponse rsp = null;

            rsp = client.execute(req);
            String response = rsp.getBody();
            if(rsp.getResultSuccess() != null && rsp.getResultSuccess()==true){
                System.out.println("订单："+order0.getPlatformorderid()+"获取标签返回信息："+response);
                JSONObject res_obj = JSONObject.parseObject(response);
                if(response.indexOf("response")>=0){
                    String date = res_obj.getString("response");
                    JSONObject date_obj = JSONObject.parseObject(date);
                    String resultString = date_obj.getString("result");
                    JSONObject result_obj = JSONObject.parseObject(resultString);
                    if(resultString.indexOf("StatusCode")>=0){
                        int StatusCode = result_obj.getInteger("StatusCode");
                        System.out.println(StatusCode);
                        if(StatusCode == 200){
                            String body = result_obj.getString("body");
                            body = new String(body.getBytes(),"UTF-8");
                            BASE64Decoder decoder = new BASE64Decoder();
                            byte[] decodedBytes = decoder.decodeBuffer(body);
                            //获取到pdf文件
                            try {
                                //判断目录存在,否则自动创建
                                if(new File(ConstantConfig.PDF_ABSOLUTE_PATH).isDirectory()==false){
                                    new File(ConstantConfig.PDF_ABSOLUTE_PATH).mkdirs();
                                }
                            } catch (Exception e) {
                                trackResultModel.setFlag(98);
                                trackResultModel.setDescr("标签目录创建Exception异常:"+e.getMessage());
                                return trackResultModel;
                            }
                            //标签全路径名=路径+文件名
                            String pdfFullname = ConstantConfig.PDF_ABSOLUTE_PATH +File.separator+"label"+File.separator+"pdf"+File.separator+order0.getPlatformorderid()+".pdf";
                            File file = new File(pdfFullname);;//生成标签文件
                            //保存文件
                            try {
                                FileOutputStream outStream = new FileOutputStream(file);
                                outStream.write(decodedBytes);
                                outStream.flush();
                                outStream.close();
                                trackResultModel.setFlag(3);
                                trackResultModel.setDescr("获取标签成功");
                                if((file.length()/1024)<1){
                                    trackResultModel.setFlag(98);
                                    trackResultModel.setDescr("PDF文件大小为"+file.length()/1024+"KB");
                                }else{
//                                    String Path = ConstantConfig.PDF_URL_PATH +File.separator+"pdf"+File.separator+"pdf"+File.separator+orderid+".pdf";
//                                    rt.setObj(Path);//标签路径

                                }
                            }catch (Exception e){
                                trackResultModel.setFlag(98);
                                trackResultModel.setDescr("接口返回错误,错误代码：");
                            }

                        }else {

                        }
                    }else{
                        trackResultModel.setFlag(98);
                        trackResultModel.setDescr("保存PDF标签失败，原因："+response);
                        return trackResultModel;
                    }


                }else {
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr("获取标签失败，原因："+response);
                }
            }else {
                trackResultModel.setFlag(98);
                trackResultModel.setDescr("获取标签失败，原因："+response);
            }

        }catch (Exception e){
            trackResultModel.setFlag(98);
            trackResultModel.setDescr("调用接口异常"+e.getMessage());
        }
        return trackResultModel;
    }

    //获取渠道
    public TrackResultModel getChannels(String orderid,String customid){
        TrackResultModel resultModel = new TrackResultModel();
        try {
            QimenCloudClient client = new DefaultQimenCloudClient(serverUrl, appKey, appSecret);
            AliexpressLogisticsGetonlinelogisticsservicelistbyorderidRequest req = new AliexpressLogisticsGetonlinelogisticsservicelistbyorderidRequest();
            req.setId(customid);
            req.setOrderId(orderid);
            req.setTargetAppKey(appKey);
            AliexpressLogisticsGetonlinelogisticsservicelistbyorderidResponse rsp = null;
            rsp = client.execute(req);
            String result = rsp.getBody();
            System.out.println("订单"+orderid+"页面单独获取速卖通线上发货渠道返回的数据-->"+result);
            if(rsp.getIsSuccess()!= null && rsp.getIsSuccess() == true){
                JSONObject date_obj = null;
                if(result.length() >0 && result.indexOf("response")>=0) {
                    JSONObject response_obj = JSONObject.parseObject(result);
                    String response = response_obj.getString("response");
                    ChannelBean channelResult = JSON.parseObject(response, ChannelBean.class);
                    if(channelResult != null) {
                        if(!"true".equals(channelResult.getIs_success())) {
                            resultModel.setFlag(98);
                            resultModel.setDescr(response);
                            return resultModel;
                        }
                        resultModel.setFlag(0);
                        resultModel.setDescr(JSON.toJSONString(channelResult.getResult_list()).toString());
                    }else {
                        resultModel.setFlag(98);
                        resultModel.setDescr(response);
                    }

                }
            }else {
                System.out.println("获取渠道失败，原因："+result);
            }
        } catch (Exception e) {
            resultModel.setDescr(e.getMessage());
            resultModel.setFlag(98);
            return resultModel;
        }
        return resultModel;
    }

    //获取揽收信息
    public  HashMap<String, SmtAddressBean> getDbSmtaddress(TrackModel trackModel){
        HashMap<String, SmtAddressBean> aMap = new HashMap<String, SmtAddressBean>();
        String address = "sender,pickup,refund";
        DbShop shop = trackModel.getShop();
        if(Constant.addressMap.get(shop.getId())!=null ){
            return Constant.addressMap.get(shop.getId());
        }
        try {
            QimenCloudClient client = new DefaultQimenCloudClient(this.serverUrl, this.appKey, this.appSecret);
            AliexpressLogisticsGetlogisticsselleraddressesRequest req = new AliexpressLogisticsGetlogisticsselleraddressesRequest();
            req.setId(shop.getAccountUsername());
            req.setTargetAppKey(this.appKey);
            req.setSellerAddressQuery(address);
            AliexpressLogisticsGetlogisticsselleraddressesResponse rsp = null;
            rsp = client.execute(req);
            String result = rsp.getBody();
            System.out.println("店铺："+shop.getName()+"返回地址信息："+result);
            if(Util.isNull(result)==false){
                try {
                    JSONObject obj = JSONObject.parseObject(result);
                    if(rsp.getResultSuccess() != null && rsp.getResultSuccess()==true &&  result.indexOf("response") >=0){
                        String  data = obj.getString("response");
                        JSONObject data_obj = JSONObject.parseObject(data);
                        JSONArray pickupSellerAddressesList = data_obj.getJSONArray("pickup_seller_address_list");
                        if(pickupSellerAddressesList.size()>0){
                            for(int i=0;i<pickupSellerAddressesList.size();i++){
                                SmtAddressBean a = new SmtAddressBean();
                                try {
                                    JSONObject pickupSellerAddresses = pickupSellerAddressesList.getJSONObject(i);
                                    if(pickupSellerAddresses.getInteger("is_default")==1){
                                        a.setId(pickupSellerAddresses.getInteger("address_id"));
                                        aMap.put("pickup", a);
                                        break;
                                    }

                                } catch (Exception e) {
                                    a.setId(0);
                                    aMap.put("pickup", a);
                                }
                            }
                        }
                        JSONArray senderSellerAddressesList = data_obj.getJSONArray("sender_seller_address_list");

                        if(senderSellerAddressesList.size()>0){
                            for(int i=0;i<senderSellerAddressesList.size();i++){
                                SmtAddressBean a = new SmtAddressBean();
                                try {
                                    JSONObject senderSellerAddresses = senderSellerAddressesList.getJSONObject(i);
                                    if(senderSellerAddresses.getInteger("is_default")==1){
                                        a.setId(senderSellerAddresses.getInteger("address_id"));
                                        System.out.println(a.getId());
                                        aMap.put("sender", a);
                                        break;
                                    }
                                } catch (Exception e) {
                                    a.setId(0);
                                    aMap.put("sender", a);
                                }
                            }
                        }
                        JSONArray refundSellerAddressesList = data_obj.getJSONArray("refund_seller_address_list");
                        if(refundSellerAddressesList.size()>0){
                            for(int i=0;i<refundSellerAddressesList.size();i++){
                                SmtAddressBean a = new SmtAddressBean();
                                try {
                                    JSONObject refundSellerAddresses = refundSellerAddressesList.getJSONObject(i);
                                    if(refundSellerAddresses.getInteger("is_default")==1){
                                        a.setId(refundSellerAddresses.getInteger("address_id"));
                                        System.out.println(a.getId());
                                        aMap.put("refund", a);
                                        break;
                                    }
                                } catch (Exception e) {
                                    a.setId(0);
                                    aMap.put("refund", a);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("解析揽收地址异常："+e.getMessage());
                    SmtAddressBean a = new SmtAddressBean();
                    a.setId(0);
                    aMap.put("refund", a);
                    aMap.put("sender", a);
                    aMap.put("pickup", a);
                }
            }
            Constant.addressMap.put(shop.getId(), aMap);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return aMap;
    }

    public static  void main(String args[]){
        CallApiSMTOL callApiPOST = new CallApiSMTOL();
        Order0 order0 = new Order0();
        order0.setPlatformorderid("9999999");
        order0.setCountrycode("US");
        TrackModel trackModel = new TrackModel();
        trackModel.setOrder0(order0);
        callApiPOST.runStep1(trackModel);

    }
}
