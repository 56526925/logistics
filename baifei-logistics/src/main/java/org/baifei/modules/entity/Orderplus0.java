package org.baifei.modules.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 订单买家信息
 * @Author: jeecg-boot
 * @Date:   2020-06-05
 * @Version: V1.0
 */
@Data
@TableName("db_orderplus0")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="db_orderplus0对象", description="订单买家信息")
public class Orderplus0 {
    
	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
	private java.lang.Integer id;
	/**公司编号*/
	@Excel(name = "公司编号", width = 15)
    @ApiModelProperty(value = "公司编号")
	private java.lang.Integer companyid;
	/**店铺编号*/
	@Excel(name = "店铺编号", width = 15)
    @ApiModelProperty(value = "店铺编号")
	private java.lang.Integer shopid;
	/**订单编号*/
	@Excel(name = "订单编号", width = 15)
    @ApiModelProperty(value = "订单编号")
	private java.lang.Integer orderid;
	/**买家账号*/
	@Excel(name = "买家账号", width = 15)
    @ApiModelProperty(value = "买家账号")
	private java.lang.String buyeraccountid;
	/**买家自定义标记（扩展）  如:1重要客户  2难缠客户*/
	@Excel(name = "买家自定义标记（扩展）  如:1重要客户  2难缠客户", width = 15)
    @ApiModelProperty(value = "买家自定义标记（扩展）  如:1重要客户  2难缠客户")
	private java.lang.Integer buyerlabelid;
	/**马帮创建时间*/
	@Excel(name = "马帮创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "马帮创建时间")
	private java.util.Date createdtime;
	/**买家名*/
	@Excel(name = "买家名", width = 15)
    @ApiModelProperty(value = "买家名")
	private java.lang.String firstname;
	/**买家姓*/
	@Excel(name = "买家姓", width = 15)
    @ApiModelProperty(value = "买家姓")
	private java.lang.String lastname;
	/**交易关闭时间*/
	@Excel(name = "交易关闭时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "交易关闭时间")
	private java.util.Date closedate;
	/**街道1*/
	@Excel(name = "街道1", width = 15)
    @ApiModelProperty(value = "街道1")
	private java.lang.String street1;
	/**街道2*/
	@Excel(name = "街道2", width = 15)
    @ApiModelProperty(value = "街道2")
	private java.lang.String street2;
	/**城市*/
	@Excel(name = "城市", width = 15)
    @ApiModelProperty(value = "城市")
	private java.lang.String city;
	/**省*/
	@Excel(name = "省", width = 15)
    @ApiModelProperty(value = "省")
	private java.lang.String province;
	/**电话1*/
	@Excel(name = "电话1", width = 15)
    @ApiModelProperty(value = "电话1")
	private java.lang.String phone1;
	/**电话2*/
	@Excel(name = "电话2", width = 15)
    @ApiModelProperty(value = "电话2")
	private java.lang.String phone2;
	/**邮编*/
	@Excel(name = "邮编", width = 15)
    @ApiModelProperty(value = "邮编")
	private java.lang.String postcode;
	/**电子邮件*/
	@Excel(name = "电子邮件", width = 15)
    @ApiModelProperty(value = "电子邮件")
	private java.lang.String email;
	/**买家自选物流ID*/
	@Excel(name = "买家自选物流ID", width = 15)
    @ApiModelProperty(value = "买家自选物流ID")
	private java.lang.Integer shippingserviceid;
	/**买家选择物流*/
	@Excel(name = "买家选择物流", width = 15)
    @ApiModelProperty(value = "买家选择物流")
	private java.lang.String shippingservice;
	/**争议订单（速卖通专用）  1争议  2非争议*/
	@Excel(name = "争议订单（速卖通专用）  1争议  2非争议", width = 15)
    @ApiModelProperty(value = "争议订单（速卖通专用）  1争议  2非争议")
	private java.lang.Integer issuestatus;
	/**资金冻结订单（速卖通专用）  1冻结订单  2非冻结*/
	@Excel(name = "资金冻结订单（速卖通专用）  1冻结订单  2非冻结", width = 15)
    @ApiModelProperty(value = "资金冻结订单（速卖通专用）  1冻结订单  2非冻结")
	private java.lang.Integer frozenstatus;
	/**卖家账号（速卖通专用）*/
	@Excel(name = "卖家账号（速卖通专用）", width = 15)
    @ApiModelProperty(value = "卖家账号（速卖通专用）")
	private java.lang.String sellername;
	/**创建者*/
	@Excel(name = "创建者", width = 15)
    @ApiModelProperty(value = "创建者")
	private java.lang.String createoper;
	/**最后一次操作员工*/
	@Excel(name = "最后一次操作员工", width = 15)
    @ApiModelProperty(value = "最后一次操作员工")
	private java.lang.Integer operid;
	/**操作时间*/
	@Excel(name = "操作时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "操作时间")
	private java.util.Date opertime;
	/**包装材料编号*/
	@Excel(name = "包装材料编号", width = 15)
    @ApiModelProperty(value = "包装材料编号")
	private java.lang.Integer packageid;
	/**包材费*/
	@Excel(name = "包材费", width = 15)
    @ApiModelProperty(value = "包材费")
	private java.math.BigDecimal packagefee;
	/**包材重量*/
	@Excel(name = "包材重量", width = 15)
    @ApiModelProperty(value = "包材重量")
	private java.math.BigDecimal packageweight;
	/**买家给的评价  1未评价  2好评  3中评  4差评*/
	@Excel(name = "买家给的评价  1未评价  2好评  3中评  4差评", width = 15)
    @ApiModelProperty(value = "买家给的评价  1未评价  2好评  3中评  4差评")
	private java.lang.Integer buyerfeedback;
	/**是否已经给买家好评  1未评价  2已给予  3好评失败*/
	@Excel(name = "是否已经给买家好评  1未评价  2已给予  3好评失败", width = 15)
    @ApiModelProperty(value = "是否已经给买家好评  1未评价  2已给予  3好评失败")
	private java.lang.Integer isfeedback;
	/**高(mm)*/
	@Excel(name = "高(mm)", width = 15)
    @ApiModelProperty(value = "高(mm)")
	private java.lang.Integer height;
	/**宽(mm)*/
	@Excel(name = "宽(mm)", width = 15)
    @ApiModelProperty(value = "宽(mm)")
	private java.lang.Integer width;
	/**长(mm)*/
	@Excel(name = "长(mm)", width = 15)
    @ApiModelProperty(value = "长(mm)")
	private java.lang.Integer length;
	/**体积(mm)*/
	@Excel(name = "体积(mm)", width = 15)
    @ApiModelProperty(value = "体积(mm)")
	private java.lang.Integer volume;
	/**paypal收款账号*/
	@Excel(name = "paypal收款账号", width = 15)
    @ApiModelProperty(value = "paypal收款账号")
	private java.lang.String paypalemail;
	/**申报价值*/
	@Excel(name = "申报价值", width = 15)
    @ApiModelProperty(value = "申报价值")
	private java.math.BigDecimal declarefee;
	/**申报名*/
	@Excel(name = "申报名", width = 15)
    @ApiModelProperty(value = "申报名")
	private java.lang.String declarename;
	/**计算标志  0未计算运费  1没拆分运费  9拆分完毕*/
	@Excel(name = "计算标志  0未计算运费  1没拆分运费  9拆分完毕", width = 15)
    @ApiModelProperty(value = "计算标志  0未计算运费  1没拆分运费  9拆分完毕")
	private java.lang.Integer calculateflag;
	/**平台上的订单状态*/
	@Excel(name = "平台上的订单状态", width = 15)
    @ApiModelProperty(value = "平台上的订单状态")
	private java.lang.String platformorderstatus;
	/**平台上交运的运单号*/
	@Excel(name = "平台上交运的运单号", width = 15)
    @ApiModelProperty(value = "平台上交运的运单号")
	private java.lang.String platformtracknumber;
	/**平台上交运的物流方式*/
	@Excel(name = "平台上交运的物流方式", width = 15)
    @ApiModelProperty(value = "平台上交运的物流方式")
	private java.lang.String platformshippingcarrierused;
	/**labelproperty1*/
	@Excel(name = "labelproperty1", width = 15)
    @ApiModelProperty(value = "labelproperty1")
	private java.lang.String labelproperty1;
	/**labelproperty2*/
	@Excel(name = "labelproperty2", width = 15)
    @ApiModelProperty(value = "labelproperty2")
	private java.lang.String labelproperty2;
	/**labelproperty3*/
	@Excel(name = "labelproperty3", width = 15)
    @ApiModelProperty(value = "labelproperty3")
	private java.lang.String labelproperty3;
	/**labelproperty4*/
	@Excel(name = "labelproperty4", width = 15)
    @ApiModelProperty(value = "labelproperty4")
	private java.lang.String labelproperty4;
	/**标签的自定义属性5*/
	@Excel(name = "标签的自定义属性5", width = 15)
    @ApiModelProperty(value = "标签的自定义属性5")
	private java.lang.String labelproperty5;
	/**标签的自定义属性5*/
	@Excel(name = "标签的自定义属性5", width = 15)
    @ApiModelProperty(value = "标签的自定义属性5")
	private java.lang.String labelproperty6;
	/**虚拟发货订单：1是；2不是*/
	@Excel(name = "虚拟发货订单：1是；2不是", width = 15)
    @ApiModelProperty(value = "虚拟发货订单：1是；2不是")
	private java.lang.Integer isvirtual;
	/**虚假发货时间*/
	@Excel(name = "虚假发货时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "虚假发货时间")
	private java.util.Date virtualtime;
	/**物流跟踪轨迹*/
	@Excel(name = "物流跟踪轨迹", width = 15)
    @ApiModelProperty(value = "物流跟踪轨迹")
	private java.lang.Object trackresult;
	/**物流跟踪轨迹(最后一个)*/
	@Excel(name = "物流跟踪轨迹(最后一个)", width = 15)
    @ApiModelProperty(value = "物流跟踪轨迹(最后一个)")
	private java.lang.String tracklast;
	/**是否完成跟踪  1是   2否*/
	@Excel(name = "是否完成跟踪  1是   2否", width = 15)
    @ApiModelProperty(value = "是否完成跟踪  1是   2否")
	private java.lang.Integer istrackend;
	/**物流跟踪时间*/
	@Excel(name = "物流跟踪时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "物流跟踪时间")
	private java.util.Date istracktime;
	/**失败订单:1:已处理;2:未处理*/
	@Excel(name = "失败订单:1:已处理;2:未处理", width = 15)
    @ApiModelProperty(value = "失败订单:1:已处理;2:未处理")
	private java.lang.Integer iserrordel;
	/**是否新订单：1：是；2：不是*/
	@Excel(name = "是否新订单：1：是；2：不是", width = 15)
    @ApiModelProperty(value = "是否新订单：1：是；2：不是")
	private java.lang.Integer isneworder;
	/**门牌号*/
	@Excel(name = "门牌号", width = 15)
    @ApiModelProperty(value = "门牌号")
	private java.lang.String doorcode;
	/**1:马帮发货；2：第三方平台或者erp发货*/
	@Excel(name = "1:马帮发货；2：第三方平台或者erp发货", width = 15)
    @ApiModelProperty(value = "1:马帮发货；2：第三方平台或者erp发货")
	private java.lang.Integer expressflag;
	/**1:云分销订单非挂号;2:云分销订单申请走挂号*/
	@Excel(name = "1:云分销订单非挂号;2:云分销订单申请走挂号", width = 15)
    @ApiModelProperty(value = "1:云分销订单非挂号;2:云分销订单申请走挂号")
	private java.lang.Integer isguahao;
	/**重发原因*/
	@Excel(name = "重发原因", width = 15)
    @ApiModelProperty(value = "重发原因")
	private java.lang.Integer exceptionreasonid;
	/**物流跟踪状态*/
	@Excel(name = "物流跟踪状态", width = 15)
    @ApiModelProperty(value = "物流跟踪状态")
	private java.lang.Integer logisstatus;
	/**确认收货时间*/
	@Excel(name = "确认收货时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "确认收货时间")
	private java.util.Date comfirmdate;
	/**亚马逊FBA类型：1.FBA 2.普通店铺*/
	@Excel(name = "亚马逊FBA类型：1.FBA 2.普通店铺", width = 15)
    @ApiModelProperty(value = "亚马逊FBA类型：1.FBA 2.普通店铺")
	private java.lang.Integer fbaflag;
	/**1:需要同步,2:不需要同步*/
	@Excel(name = "1:需要同步,2:不需要同步", width = 15)
    @ApiModelProperty(value = "1:需要同步,2:不需要同步")
	private java.lang.Integer unsynplatform;
	/**发货类型*/
	@Excel(name = "发货类型", width = 15)
    @ApiModelProperty(value = "发货类型")
	private java.lang.String shippingtype;
	/**优惠金额*/
	@Excel(name = "优惠金额", width = 15)
    @ApiModelProperty(value = "优惠金额")
	private java.math.BigDecimal voucherprice;
	/**支付方式*/
	@Excel(name = "支付方式", width = 15)
    @ApiModelProperty(value = "支付方式")
	private java.lang.String paymentname;
	/**是否自提信息*/
	@Excel(name = "是否自提信息", width = 15)
    @ApiModelProperty(value = "是否自提信息")
	private java.lang.String dlypname;
	/**发货商家名*/
	@Excel(name = "发货商家名", width = 15)
    @ApiModelProperty(value = "发货商家名")
	private java.lang.String kilimailsellername;
	/**发货备注*/
	@Excel(name = "发货备注", width = 15)
    @ApiModelProperty(value = "发货备注")
	private java.lang.String deliveryremark;
	/**是否加急1是 2否*/
	@Excel(name = "是否加急1是 2否", width = 15)
    @ApiModelProperty(value = "是否加急1是 2否")
	private java.lang.Integer isurgent;
	/**是否打印配货单1-是 2-否*/
	@Excel(name = "是否打印配货单1-是 2-否", width = 15)
    @ApiModelProperty(value = "是否打印配货单1-是 2-否")
	private java.lang.Integer isdistributionlistprint;
	/**是否打印自定义配货单1-是2-否*/
	@Excel(name = "是否打印自定义配货单1-是2-否", width = 15)
    @ApiModelProperty(value = "是否打印自定义配货单1-是2-否")
	private java.lang.Integer isdistributionlistprint2;
	/**FBA配送开始时间*/
	@Excel(name = "FBA配送开始时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "FBA配送开始时间")
	private java.util.Date fbastartdatetime;
	/**FBA配送结束时间*/
	@Excel(name = "FBA配送结束时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "FBA配送结束时间")
	private java.util.Date fbaenddatetime;
	/**FBA承运商*/
	@Excel(name = "FBA承运商", width = 15)
    @ApiModelProperty(value = "FBA承运商")
	private java.lang.String carriercode;
	/**预报（自动交运）状态1-未上传2-上传中3上传成功99上传失败*/
	@Excel(name = "预报（自动交运）状态1-未上传2-上传中3上传成功99上传失败", width = 15)
    @ApiModelProperty(value = "预报（自动交运）状态1-未上传2-上传中3上传成功99上传失败")
	private java.lang.String forecaststatus;
	/**退包类型1-邮局退保2-买家退包；其余为定义分类*/
	@Excel(name = "退包类型1-邮局退保2-买家退包；其余为定义分类", width = 15)
    @ApiModelProperty(value = "退包类型1-邮局退保2-买家退包；其余为定义分类")
	private java.lang.String refundstype;
	/**预报物流*/
	@Excel(name = "预报物流", width = 15)
    @ApiModelProperty(value = "预报物流")
	private java.lang.String forecastlogistics;
	/**预报物流渠道*/
	@Excel(name = "预报物流渠道", width = 15)
    @ApiModelProperty(value = "预报物流渠道")
	private java.lang.String forecastchannel;
	/**预报描述*/
	@Excel(name = "预报描述", width = 15)
    @ApiModelProperty(value = "预报描述")
	private java.lang.String forecastdescr;
	/**订单是否上网（是否有物流跟踪信息）0-未上网1已上网2-逾期*/
	@Excel(name = "订单是否上网（是否有物流跟踪信息）0-未上网1已上网2-逾期", width = 15)
    @ApiModelProperty(value = "订单是否上网（是否有物流跟踪信息）0-未上网1已上网2-逾期")
	private java.lang.Integer istrackonline;
	/**物流跟踪信息*/
	@Excel(name = "物流跟踪信息", width = 15)
    @ApiModelProperty(value = "物流跟踪信息")
	private java.lang.String trackonlinedescr;
	/**税号*/
	@Excel(name = "税号", width = 15)
    @ApiModelProperty(value = "税号")
	private java.lang.String ausdetails;
	/**是否*/
	@Excel(name = "是否", width = 15)
    @ApiModelProperty(value = "是否")
	private java.lang.Integer isupdateaddress;
	/**异常分类*/
	@Excel(name = "异常分类", width = 15)
    @ApiModelProperty(value = "异常分类")
	private java.lang.Integer abnormaltype;
	/**最优高*/
	@Excel(name = "最优高", width = 15)
    @ApiModelProperty(value = "最优高")
	private java.lang.Integer height1;
	/**最优宽*/
	@Excel(name = "最优宽", width = 15)
    @ApiModelProperty(value = "最优宽")
	private java.lang.Integer width1;
	/**最优长*/
	@Excel(name = "最优长", width = 15)
    @ApiModelProperty(value = "最优长")
	private java.lang.Integer length1;
	/**最优体积*/
	@Excel(name = "最优体积", width = 15)
    @ApiModelProperty(value = "最优体积")
	private java.lang.Integer volume1;
	/**区域*/
	@Excel(name = "区域", width = 15)
    @ApiModelProperty(value = "区域")
	private java.lang.String district;
}
