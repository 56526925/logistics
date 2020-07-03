package org.baifei.modules.entity.request.common;

import lombok.Data;

@Data
public class PropertyJson {
    //揽收方式
    private String sendtype;
    //揽收商
    private String pickupcode;
    //国内快递
    private String deliverytype;
    //快递单号
    private String expressid;
    //是否挂号
    private String expresstype;
    //是否含有电池
    private String batteryflag;
    //是否含有液体
    private String liquidflag;
    //是否含有粉末
    private String powerflag;
    //是否含有化妆品
    private String cosmeticsflag;
    //是否立即提交数据
    private String senddataflag;
    //商品类型
    private String producttype;
    //无法投递方案
    private String undeliverabledecision;
    //保险类型
    private String insurtype;
    //交税选项
    private String hasrevenue;
    //偏远地区是否走货
    private String remoteflag;
    //顺丰发货类型
    private String sfexpresstype;
    //WISHU揽收仓库
    private String wishwarehouse;
    //JOOM揽收仓库
    private String joomwarehouse;
    //VOVA揽收仓库
    private String vovawarehouse;
    //中国邮政账号选择
    private String chinapostaccount;
    //第四方账号
    private String fpxaccount;
    //WISHU账号
    private String wishuaccount;
    //顺丰账号
    private String sfaccount;
    //顺友账号
    private String syaccount;
    //纬狮账号
    private String wsaccount;
    //海河汇账号
    private String hhhaccount;
    //DHL账号
    private String dhlaccount;
}
