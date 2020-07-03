package org.baifei.modules.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.baifei.common.util.*;
import org.baifei.modules.entity.Order0;
import org.baifei.modules.entity.Orderitem0;
import org.baifei.modules.entity.Orderplus0;
import org.baifei.modules.entity.request.smwl.SmwlInvoices;
import org.baifei.modules.entity.request.smwl.SmwlRequestStep1;
import org.baifei.modules.entity.request.ubi.UbiOrderItems;
import org.baifei.modules.entity.request.ubi.UbiRequestStep1;
import org.baifei.modules.entity.response.common.DbLogisticschannel;
import org.baifei.modules.entity.response.common.DbMylogisticsaccount;
import org.baifei.modules.entity.response.common.DbMylogisticschannel;
import org.baifei.modules.entity.response.common.DbMylogisticsreturnaddress;
import org.baifei.modules.entity.response.ubi.UbiResponseStep1;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class CallApiSMWL {
    @Autowired
    private SSLClient sslClient;
    private  String URL = "http://api.somaxn.cn/write";

    //创建订单
    public TrackResultModel runStep1(TrackModel trackModel){
        String url_track=URL+"/webapi/orderpush";
        TrackResultModel trackResultModel = new TrackResultModel();
        ResultCode resultCode = new ResultCode();
        TrackResultModel rt = new TrackResultModel();
        try {
            DbMylogisticsaccount mylogisticsaccount = trackModel.getMylogisticsaccount();

            //请求头
            Map<String, Object> header = new HashMap<String, Object>();
            header.put("Content-Type", "application/json");
            header.put("api_key", mylogisticsaccount.getToken());

            String requestJson = getDataStep1(trackModel);

            resultCode = sslClient.doPostHttpClient(url_track, header, null, requestJson, 60 * 1000);
            if(resultCode.getAck()==0){
                JSONObject result = JSONObject.parseObject(resultCode.getData().toString());


               if(result.getJSONArray("success")!=null){
                    String tracknumber =result.getJSONArray("success").getJSONObject(0).getString("barcode");
                    trackResultModel.setTrackNumber(tracknumber);
                    trackResultModel.setFlag(3);
                    trackResultModel.setDescr(resultCode.getMsg());
                }else{
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr(result.getJSONArray("failing").getJSONObject(0).getString("message"));
                }

            }else{
                trackResultModel.setFlag(98);
                trackResultModel.setDescr("调用接口异常"+resultCode.getMsg());
            }
        }catch (Exception e){
            trackResultModel.setFlag(98);
            trackResultModel.setDescr("调用接口异常"+e.getMessage());
        }
        return rt;
    }

    public TrackResultModel runStep2(TrackModel trackModel){
        String url_label=URL+"/webapi/downloadlabels";
        ResultCode resultCode = new ResultCode();
        TrackResultModel trackResultModel = new TrackResultModel();
        try {
            DbMylogisticsaccount mylogisticsaccount = trackModel.getMylogisticsaccount();
            Order0 order0 = trackModel.getOrder0();

            //请求头
            Map<String, Object> header = new HashMap<String, Object>();
            header.put("Content-Type", "application/json");
            header.put("api_key", mylogisticsaccount.getToken());

            String requestJson=" \"serials\": [\n" +
                    " \""+order0.getTracknumber()+"\"\n" +
                    " ],\n" +
                    " \"paper\": 7 }";

            resultCode = PdfUtil.doPostHttpClient(url_label,header,null,requestJson,60*1000, ConstantConfig.PDF_ABSOLUTE_PATH,order0.getPlatformorderid());
            if(resultCode.getAck()==0){
                trackResultModel.setFlag(3);
                trackResultModel.setDescr("下载订单成功!");
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

    public String getDataStep1(TrackModel trackModel){
        Order0 order0 = trackModel.getOrder0();
        Orderplus0 orderplus0 = trackModel.getOrderplus0();
        DbLogisticschannel logisticschannel = trackModel.getDbLogisticschannel();
        DbMylogisticschannel dbMylogisticschannel = trackModel.getDbMylogisticschannel();
        List<Orderitem0> orderitem0List = trackModel.getOrderitem0();

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

        SmwlRequestStep1 requestStep1 = new SmwlRequestStep1();

        //客户参考号（不做唯一标准）
        requestStep1.setRef_no(order0.getPlatformorderid());
        //运输方式代码
        requestStep1.setChannel_code(logisticschannel.getCode());
        //收件人姓名
        requestStep1.setReceiver(order0.getBuyername());
        //国家二字简码
        requestStep1.setCountry(order0.getCountrycode());
        //收件人省份
        requestStep1.setProvince(orderplus0.getProvince());
        //收件人城市
        requestStep1.setCity(orderplus0.getCity());
        //收件人地址
        requestStep1.setAddress(orderplus0.getStreet1());
        //收件人邮编
        requestStep1.setPostcode(orderplus0.getPostcode());
        //收件人电话号码
        requestStep1.setPhone(orderplus0.getPhone1());
        //邮件重量 以公斤为单位
        requestStep1.setWeight(cargo_total_weight);
        //内件申报信息
        List<SmwlInvoices> orderItems = new ArrayList<SmwlInvoices>();
        for(Orderitem0 orderitem0:orderitem0List){
            SmwlInvoices smwlInvoices = new SmwlInvoices();
            //中文品名
            smwlInvoices.setName_cn(orderitem0.getDeclareCnName());
            //英文品名
            smwlInvoices.setName_en(orderitem0.getDeclareEnName());
            //重量
            smwlInvoices.setWeight(orderitem0.getTestWeight()/1000000);
            //单价
            smwlInvoices.setPrice(single_value);
            orderItems.add(smwlInvoices);
        }
        requestStep1.setInvoices(orderItems);

        List<SmwlRequestStep1> datalist = new ArrayList<SmwlRequestStep1>();
        datalist.add(requestStep1);
        return JSONObject.toJSONString(datalist);
    }

    public TrackResultModel getChannels(String token){
        String url_channels=URL+"/webapi/getchannellist";
        ResultCode resultCode = new ResultCode();
        TrackResultModel trackResultModel = new TrackResultModel();

        try {
            //请求头
            Map<String, Object> header = new HashMap<String, Object>();
            header.put("Content-Type", "application/json");
            header.put("api_key", token);

            resultCode = sslClient.doGetHttpClient(url_channels, header, 60 * 1000);
            if(resultCode.getAck()==0){
                JSONArray resultData = JSON.parseArray(resultCode.getData().toString());

                if(null!=resultData){
                    trackResultModel.setFlag(0);
                    trackResultModel.setDescr(resultCode.getData().toString());
                }else{
                    trackResultModel.setFlag(98);
                    trackResultModel.setDescr("获取渠道失败");
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

}
