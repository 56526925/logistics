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

import org.baifei.modules.webservice.haihehui.*;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;


/************
 *
 *
 *
 *          海河汇api
 *
 *
 */
@Component
public class CallApiHaihehui {

    private String serviceUrl="http://120.78.72.68:8086";

    //创建订单
    public TrackResultModel runStep1(TrackModel trackModel){
         TrackResultModel trackResultModel = new TrackResultModel();
        String userToken = trackModel.getMylogisticsaccount().getToken();
        try{
            ResultCode resultCode = new ResultCode();

            CreateOrderRequest req = getDataStep1(trackModel);

            OrderOnlineServiceServiceSoapBindingStub binding = null;
            try {
                binding = (OrderOnlineServiceServiceSoapBindingStub)
                        new OrderOnlineServiceServiceLocator().getOrderOnlineServicePort();
            }
            catch (javax.xml.rpc.ServiceException jre) {
                if(jre.getLinkedCause()!=null)
                    jre.getLinkedCause().printStackTrace();
            }

            // Time out after a minute
            binding.setTimeout(60000);

            // Test operation
            CreateOrderResponse value = binding.createAndAuditOrder(userToken, req);
            // TBD - validate results
            if(value.getSuccess()){
                trackResultModel.setFlag(3);
                trackResultModel.setTrackNumber(value.getTrackingNo());
                trackResultModel.setDescr("调用接口成功，返回订单号:"+value.getTrackingNo());
            }else{
                trackResultModel.setFlag(98);
                trackResultModel.setDescr("调用接口失败"+value.getError().getErrorInfo());
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
        String userToken = dbMylogisticsaccount.getToken();
//        userToken="47237624cf374f0a88ed45c6f8674c82";
        try{
            String pageSizeCode = "6";  //默认10*10热敏标签
            String printSelect = "3";  //默认地址标签+报关单
            String pdfUrl = serviceUrl+"/xms/client/order_online!printPdf.action?userToken="+userToken+"&trackingNo="+trackNumber+"&pageSizeCode="+pageSizeCode+"&printSelect="+printSelect;

            ResultCode resultCodePdf = PdfUtil.doGetHttpClient(pdfUrl,null,60*1000,ConstantConfig.PDF_ABSOLUTE_PATH,platformOrderId);
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

    //获取渠道
    public TrackResultModel getChannels(String token){
        TrackResultModel trackResultModel = new TrackResultModel();
        try{
            OrderOnlineServiceServiceSoapBindingStub binding = null;
            try {
                binding = (OrderOnlineServiceServiceSoapBindingStub)
                        new OrderOnlineServiceServiceLocator().getOrderOnlineServicePort();
            }
            catch (javax.xml.rpc.ServiceException jre) {
                if(jre.getLinkedCause()!=null)
                    jre.getLinkedCause().printStackTrace();
            }

            // Time out after a minute
            binding.setTimeout(60000);

            // Test operation
            GetTransportWayListResponse value = binding.getTransportWayList(token);
            if(value.getSuccess()){
                trackResultModel.setFlag(0);
                trackResultModel.setDescr(JSONObject.toJSONString(value));
            }else{
                trackResultModel.setFlag(98);
                trackResultModel.setDescr("调用接口异常"+value.getError().getErrorInfo());
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
    public  CreateOrderRequest getDataStep1(TrackModel trackModel){
        /****************拼装数据******start***************/

        String time= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Order0 order0 = trackModel.getOrder0();
        Orderplus0 orderplus0 = trackModel.getOrderplus0();
        List<Orderitem0> orderitem0List = trackModel.getOrderitem0();
        DbMylogisticschannel dbMylogisticschannel = trackModel.getDbMylogisticschannel();
        DbLogisticschannel dbLogisticschannel = trackModel.getDbLogisticschannel();
        DbMylogisticsreturnaddress dbMylogisticsreturnaddress = trackModel.getDbMylogisticsreturnaddress();
        PropertyJson propertyJson = trackModel.getPropertyJson();

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

        String goodsCategory = "G";
        if("1".equals(propertyJson.getProducttype())){
            goodsCategory = "G";
        }else if("2".equals(propertyJson.getProducttype())){
            goodsCategory = "D";
        }else if("3".equals(propertyJson.getProducttype())){
            goodsCategory = "S";
        }else if("4".equals(propertyJson.getProducttype())){
            goodsCategory = "O";
        }

        CreateOrderRequest req = new CreateOrderRequest();
        req.setOrderNo(order0.getPlatformorderid()); //客户单号
        req.setTransportWayCode(dbLogisticschannel.getCode());//运输方式代码（渠道代码）。必填
        req.setCargoCode("W");//货物类型(W包裹/D文件)。必填
        req.setInsured("N"); //购买保险（投保：Y，不投保：N）。 必填
        req.setWeight(cargo_total_weight);//货物预报重量。必填；0<=value
        req.setGoodsCategory(goodsCategory);//物品类别(G/D/S/R/O)'。必填
        req.setPieces(order0.getItemquantity().longValue());//货物件数。
        //收货人
        req.setDestinationCountryCode(order0.getCountrycode()); //目的国家二字简码。必填
        req.setConsigneeName(order0.getBuyername()); //收件人姓名。 length<=100
        req.setConsigneeTelephone(orderplus0.getPhone1()); //收件人电话号码。length<=32
        req.setConsigneeMobile(orderplus0.getPhone2());  //收件人手机号码。length<=32
        req.setStreet(orderplus0.getStreet1()); //街道。必填；length<=200
        req.setCity(orderplus0.getCity()); //城市。必填；length<=60
        req.setProvince(orderplus0.getProvince());  //省/州。必填；length<=60
        req.setConsigneePostcode(orderplus0.getPostcode()); //收件人邮编。length<=10
        //发货人
        req.setOriginCountryCode(dbMylogisticsreturnaddress.getCountryCode());
        req.setShipperName(dbMylogisticsreturnaddress.getContactEn()); //发件人姓名.length<=100
        req.setShipperTelephone(dbMylogisticsreturnaddress.getPhone()); //发件人电话号码。length<=32
        req.setShipperMobile(dbMylogisticsreturnaddress.getMobile()); //发件人手机号码。length<=32
        req.setShipperAddress(dbMylogisticsreturnaddress.getStreetEn()); //发件人地址。length<=200

        DeclareItem[] declareItems = new DeclareItem[orderitem0List.size()];

        for(Orderitem0 orderitem0 :orderitem0List){
            if(single_value==0){
                single_value = orderitem0.getDeclareValue();
            }
            //报关明细
            DeclareItem declareItem1 = new DeclareItem();
            declareItem1.setName(orderitem0.getDeclareEnName());  //申报品名。必填；0<length<100
            declareItem1.setCnName(orderitem0.getDeclareCnName());
            declareItem1.setNetWeight(orderitem0.getTestWeight()/1000000);//净重(kg)。必填;0<=value
            declareItem1.setUnitPrice(single_value); //单价。必填；0<value
            declareItem1.setPieces(orderitem0.getQuantity().longValue());   //件数。必填；1<value
        }

        req.setDeclareItems(declareItems);

        return  req;
        /****************拼装数据******end***************/
    }

}
