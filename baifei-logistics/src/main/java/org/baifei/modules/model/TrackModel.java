package org.baifei.modules.model;

import lombok.Data;
import org.baifei.modules.entity.*;
import org.baifei.modules.entity.request.common.RequestJson;
import org.baifei.modules.entity.response.common.*;
import org.baifei.modules.entity.request.common.PropertyJson;

import java.util.List;

@Data
public class TrackModel {
    //店铺
    private DbShop shop;
    //订单
    private Order0 order0;
    //订单商品详情
    private List<Orderitem0> orderitem0;
    //订单金额
    private Ordercurrency0 ordercurrency0;
    //订单买家信息
    private Orderplus0 orderplus0;
    //货运方式
    private DbMylogisticschannel dbMylogisticschannel;
    //寄件人地址
    private DbMylogisticsreturnaddress dbMylogisticsreturnaddress;
    //交运信息
//    private Needtotracknumber101 needtotracknumber101;
//    private Needtotracknumber103 needtotracknumber103;
//    private Needtotracknumber104 needtotracknumber104;
//    private Needtotracknumber105 needtotracknumber105;
    //交运属性
    private PropertyJson propertyJson;
    //
    private RequestJson requestJson;
    //物流账号
    private DbMylogisticsaccount mylogisticsaccount;
    //物流渠道
    private DbLogisticschannel dbLogisticschannel;
    //揽收仓库
    private DbMylogisticswarehouse dbMylogisticswarehouse;
}
