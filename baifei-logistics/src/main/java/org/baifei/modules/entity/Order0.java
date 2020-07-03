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
 * @Description: 订单
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
@Data
@TableName("db_order0")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="db_order0对象", description="订单")
public class Order0 {
    
	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
	private java.lang.Integer id;
	/**平台订单编号*/
	@Excel(name = "平台订单编号", width = 15)
    @ApiModelProperty(value = "平台订单编号")
	private java.lang.String platformorderid;
	/**公司编号*/
	@Excel(name = "公司编号", width = 15)
    @ApiModelProperty(value = "公司编号")
	private java.lang.Integer companyid;
	/**平台1 ebay,2 amazon,3 aliexpress,4  wish,8 cdiscount,9 PM,14 tophatter,16 shopify, 17 shopee 18 alibaba,19 joom, 26 mymall, 34 vova,  42 mercadolibre 48 real  87 fy, 88 Allegro 89 RDC 90 souq 100 YL 999 other*/
	@Excel(name = "平台1 ebay,2 amazon,3 aliexpress,4  wish,8 cdiscount,9 PM,14 tophatter,16 shopify, 17 shopee 18 alibaba,19 joom, 26 mymall, 34 vova,  42 mercadolibre 48 real  87 fy, 88 Allegro 89 RDC 90 souq 100 YL 999 other", width = 15)
    @ApiModelProperty(value = "平台1 ebay,2 amazon,3 aliexpress,4  wish,8 cdiscount,9 PM,14 tophatter,16 shopify, 17 shopee 18 alibaba,19 joom, 26 mymall, 34 vova,  42 mercadolibre 48 real  87 fy, 88 Allegro 89 RDC 90 souq 100 YL 999 other")
	private java.lang.Integer platformid;
	/**店铺编号*/
	@Excel(name = "店铺编号", width = 15)
    @ApiModelProperty(value = "店铺编号")
	private java.lang.Integer shopid;
	/**付款时间*/
	@Excel(name = "付款时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "付款时间")
	private java.util.Date paidtime;
	/**付款标志  1已付款  2未付款  3资金在途  4已退款*/
	@Excel(name = "付款标志  1已付款  2未付款  3资金在途  4已退款", width = 15)
    @ApiModelProperty(value = "付款标志  1已付款  2未付款  3资金在途  4已退款")
	private java.lang.Integer paidflag;
	/**订单发货状态  1新订单2已支付  3匹配货运4预估利润  5自动交运 6待配货 7 配货中 8已发货 9已完成 10已作废*/
	@Excel(name = "订单发货状态  1新订单2已支付  3匹配货运4预估利润  5自动交运 6待配货 7 配货中 8已发货 9已完成 10已作废", width = 15)
    @ApiModelProperty(value = "订单发货状态  1新订单2已支付  3匹配货运4预估利润  5自动交运 6待配货 7 配货中 8已发货 9已完成 10已作废")
	private java.lang.Integer orderstatus;
	/**交易号*/
	@Excel(name = "交易号", width = 15)
    @ApiModelProperty(value = "交易号")
	private java.lang.String salesrecordnumber;
	/**CRM客户关系ID*/
	@Excel(name = "CRM客户关系ID", width = 15)
    @ApiModelProperty(value = "CRM客户关系ID")
	private java.lang.Integer customerid;
	/**买家账号*/
	@Excel(name = "买家账号", width = 15)
    @ApiModelProperty(value = "买家账号")
	private java.lang.String buyeruserid;
	/**买家姓名*/
	@Excel(name = "买家姓名", width = 15)
    @ApiModelProperty(value = "买家姓名")
	private java.lang.String buyername;
	/**国家编码*/
	@Excel(name = "国家编码", width = 15)
    @ApiModelProperty(value = "国家编码")
	private java.lang.String countrycode;
	/**订单总金额（商品售价和物流收入）*/
	@Excel(name = "订单总金额（商品售价和物流收入）", width = 15)
    @ApiModelProperty(value = "订单总金额（商品售价和物流收入）")
	private java.math.BigDecimal orderfee;
	/**订单总成本(商品成本，物流成本，平台交易费，资金转账费，包裹费等)--暂时不用*/
	@Excel(name = "订单总成本(商品成本，物流成本，平台交易费，资金转账费，包裹费等)--暂时不用", width = 15)
    @ApiModelProperty(value = "订单总成本(商品成本，物流成本，平台交易费，资金转账费，包裹费等)--暂时不用")
	private java.math.BigDecimal ordercost;
	/**订单预估重量=商品总重量+包材重量*/
	@Excel(name = "订单预估重量=商品总重量+包材重量", width = 15)
    @ApiModelProperty(value = "订单预估重量=商品总重量+包材重量")
	private java.math.BigDecimal orderweight;
	/**美元汇率*/
	@Excel(name = "美元汇率", width = 15)
    @ApiModelProperty(value = "美元汇率")
	private java.math.BigDecimal usdrate;
	/**是否有买家留言  1有留言  2无留言---暂时不用*/
	@Excel(name = "是否有买家留言  1有留言  2无留言---暂时不用", width = 15)
    @ApiModelProperty(value = "是否有买家留言  1有留言  2无留言---暂时不用")
	private java.lang.Integer hasbuyermessage;
	/**下载批次*/
	@Excel(name = "下载批次", width = 15)
    @ApiModelProperty(value = "下载批次")
	private java.lang.String importid;
	/**物流公司编号-暂不用*/
	@Excel(name = "物流公司编号-暂不用", width = 15)
    @ApiModelProperty(value = "物流公司编号-暂不用")
	private java.lang.Integer mylogisticsid;
	/**货运方式主键 base_logistics_channel*/
	@Excel(name = "货运方式主键 base_logistics_channel", width = 15)
    @ApiModelProperty(value = "货运方式主键 base_logistics_channel")
	private java.lang.Integer mylogisticschannelid;
	/**跟踪号1*/
	@Excel(name = "跟踪号1", width = 15)
    @ApiModelProperty(value = "跟踪号1")
	private java.lang.String tracknumber;
	/**内部单号*/
	@Excel(name = "内部单号", width = 15)
    @ApiModelProperty(value = "内部单号")
	private java.lang.String tracknumber1;
	/**跟踪号2（常用存储物流公司内部单号）*/
	@Excel(name = "跟踪号2（常用存储物流公司内部单号）", width = 15)
    @ApiModelProperty(value = "跟踪号2（常用存储物流公司内部单号）")
	private java.lang.String tracknumber2;
	/**打印次数*/
	@Excel(name = "打印次数", width = 15)
    @ApiModelProperty(value = "打印次数")
	private java.lang.Integer printcount;
	/**打印时间*/
	@Excel(name = "打印时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "打印时间")
	private java.util.Date printtime;
	/**是否同步到电商平台 1未同步 2等待交运 6不需要同步 7同步失败 9同步成功*/
	@Excel(name = "是否同步到电商平台 1未同步 2等待交运 6不需要同步 7同步失败 9同步成功", width = 15)
    @ApiModelProperty(value = "是否同步到电商平台 1未同步 2等待交运 6不需要同步 7同步失败 9同步成功")
	private java.lang.Integer issyncplatform;
	/**同步结果*/
	@Excel(name = "同步结果", width = 15)
    @ApiModelProperty(value = "同步结果")
	private java.lang.String issyncplatformdescr;
	/**是否允许自动交运 1 是 2否   自动交运脚本需要过滤该字段，只执行1的订单*/
	@Excel(name = "是否允许自动交运 1 是 2否   自动交运脚本需要过滤该字段，只执行1的订单", width = 15)
    @ApiModelProperty(value = "是否允许自动交运 1 是 2否   自动交运脚本需要过滤该字段，只执行1的订单")
	private java.lang.Integer issynclogistics;
	/**交运流程状态 1未执行自动交运  2 自动交运中 3 单号已上，无面单文件  4 单号已上，有面单文件 5 上单异常 */
	@Excel(name = "交运流程状态 1未执行自动交运  2 自动交运中 3 单号已上，无面单文件  4 单号已上，有面单文件 5 上单异常 ", width = 15)
    @ApiModelProperty(value = "交运流程状态 1未执行自动交运  2 自动交运中 3 单号已上，无面单文件  4 单号已上，有面单文件 5 上单异常 ")
	private java.lang.Integer synclogisticsstatus;
	/**交运结果*/
	@Excel(name = "交运结果", width = 15)
    @ApiModelProperty(value = "交运结果")
	private java.lang.String issynclogisticsdescr;
	/**库存是否充足  1充足  2不充足  0未计算过*/
	@Excel(name = "库存是否充足  1充足  2不充足  0未计算过", width = 15)
    @ApiModelProperty(value = "库存是否充足  1充足  2不充足  0未计算过")
	private java.lang.Integer hasgoods;
	/**是否允许发货  1允许发货  2~9各种禁止发货状态*/
	@Excel(name = "是否允许发货  1允许发货  2~9各种禁止发货状态", width = 15)
    @ApiModelProperty(value = "是否允许发货  1允许发货  2~9各种禁止发货状态")
	private java.lang.Integer cansend;
	/**转账流水编号*/
	@Excel(name = "转账流水编号", width = 15)
    @ApiModelProperty(value = "转账流水编号")
	private java.lang.String paypalid;
	/**是否多仓库  1是2不是*/
	@Excel(name = "是否多仓库  1是2不是", width = 15)
    @ApiModelProperty(value = "是否多仓库  1是2不是")
	private java.lang.Integer multiwarehouse;
	/**仓库编号,多个以逗号间隔*/
	@Excel(name = "仓库编号,多个以逗号间隔", width = 15)
    @ApiModelProperty(value = "仓库编号,多个以逗号间隔")
	private java.lang.String stockwarehouseid;
	/**仓位,多个以逗号间隔  ,a,b,c,*/
	@Excel(name = "仓位,多个以逗号间隔  ,a,b,c,", width = 15)
    @ApiModelProperty(value = "仓位,多个以逗号间隔  ,a,b,c,")
	private java.lang.String stockgrid;
	/**订单下商品种类*/
	@Excel(name = "订单下商品种类", width = 15)
    @ApiModelProperty(value = "订单下商品种类")
	private java.lang.Integer itemcount;
	/**订单下商品的累计总个数*/
	@Excel(name = "订单下商品的累计总个数", width = 15)
    @ApiModelProperty(value = "订单下商品的累计总个数")
	private java.lang.Integer itemquantity;
	/**订单锁定标志:  1锁定2未锁定*/
	@Excel(name = "订单锁定标志:  1锁定2未锁定", width = 15)
    @ApiModelProperty(value = "订单锁定标志:  1锁定2未锁定")
	private java.lang.Integer islock;
	/**锁定备注*/
	@Excel(name = "锁定备注", width = 15)
    @ApiModelProperty(value = "锁定备注")
	private java.lang.String lockdescr;
	/**发货时间*/
	@Excel(name = "发货时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "发货时间")
	private java.util.Date expresstime;
	/**发货人*/
	@Excel(name = "发货人", width = 15)
    @ApiModelProperty(value = "发货人")
	private java.lang.Integer expressoperid;
	/**是否有Paypal信息  1.有，2无*/
	@Excel(name = "是否有Paypal信息  1.有，2无", width = 15)
    @ApiModelProperty(value = "是否有Paypal信息  1.有，2无")
	private java.lang.Integer haspaypalinfo;
	/**原始订单ID*/
	@Excel(name = "原始订单ID", width = 15)
    @ApiModelProperty(value = "原始订单ID")
	private java.lang.Integer originid;
	/**订单来源:1手动2上传3接口*/
	@Excel(name = "订单来源:1手动2上传3接口", width = 15)
    @ApiModelProperty(value = "订单来源:1手动2上传3接口")
	private java.lang.Integer source;
	/**是否合并订单:1合并2不合并*/
	@Excel(name = "是否合并订单:1合并2不合并", width = 15)
    @ApiModelProperty(value = "是否合并订单:1合并2不合并")
	private java.lang.Integer isunion;
	/**是否拆分订单1拆分2不拆分*/
	@Excel(name = "是否拆分订单1拆分2不拆分", width = 15)
    @ApiModelProperty(value = "是否拆分订单1拆分2不拆分")
	private java.lang.Integer issplit;
	/**是否重发订单1重发2不重发*/
	@Excel(name = "是否重发订单1重发2不重发", width = 15)
    @ApiModelProperty(value = "是否重发订单1重发2不重发")
	private java.lang.Integer isresend;
	/**是否退款订单1退款2不退款*/
	@Excel(name = "是否退款订单1退款2不退款", width = 15)
    @ApiModelProperty(value = "是否退款订单1退款2不退款")
	private java.lang.Integer isrefund;
	/**是否含捆绑商品订单1含2不含*/
	@Excel(name = "是否含捆绑商品订单1含2不含", width = 15)
    @ApiModelProperty(value = "是否含捆绑商品订单1含2不含")
	private java.lang.Integer iscombo;
	/**1.退货 2.非退货*/
	@Excel(name = "1.退货 2.非退货", width = 15)
    @ApiModelProperty(value = "1.退货 2.非退货")
	private java.lang.Integer isreturned;
	/**是否含带电池订单1含2不含*/
	@Excel(name = "是否含带电池订单1含2不含", width = 15)
    @ApiModelProperty(value = "是否含带电池订单1含2不含")
	private java.lang.Integer hasbattery;
	/**含自动创建商品:1含2不含*/
	@Excel(name = "含自动创建商品:1含2不含", width = 15)
    @ApiModelProperty(value = "含自动创建商品:1含2不含")
	private java.lang.Integer hascreatedgoods;
	/**黑名单用户1是2不是*/
	@Excel(name = "黑名单用户1是2不是", width = 15)
    @ApiModelProperty(value = "黑名单用户1是2不是")
	private java.lang.Integer isblackuser;
	/**退款用户1是2不是*/
	@Excel(name = "退款用户1是2不是", width = 15)
    @ApiModelProperty(value = "退款用户1是2不是")
	private java.lang.Integer isrefunduser;
	/**差评用户1是2不是*/
	@Excel(name = "差评用户1是2不是", width = 15)
    @ApiModelProperty(value = "差评用户1是2不是")
	private java.lang.Integer isbadfeedbackuser;
	/**用户级别：101金牌102银牌103铜牌*/
	@Excel(name = "用户级别：101金牌102银牌103铜牌", width = 15)
    @ApiModelProperty(value = "用户级别：101金牌102银牌103铜牌")
	private java.lang.Integer userlevel;
	/**商品目录id,多个以逗号间隔*/
	@Excel(name = "商品目录id,多个以逗号间隔", width = 15)
    @ApiModelProperty(value = "商品目录id,多个以逗号间隔")
	private java.lang.String categoryid;
	/**库存SKU,多个以逗号间隔*/
	@Excel(name = "库存SKU,多个以逗号间隔", width = 15)
    @ApiModelProperty(value = "库存SKU,多个以逗号间隔")
	private java.lang.String stocksku;
	/**1已验货2未验货*/
	@Excel(name = "1已验货2未验货", width = 15)
    @ApiModelProperty(value = "1已验货2未验货")
	private java.lang.Integer ischecked;
	/**物流收入，用于查询*/
	@Excel(name = "物流收入，用于查询", width = 15)
    @ApiModelProperty(value = "物流收入，用于查询")
	private java.math.BigDecimal shippingfee;
	/**10代表单商品单数量等待任务;11单商品单数量发货完成;12单商品单数量非单商品单数量订单;13单商品单数量订单发货异常;14单商品单数量订单已打印;15单商品单数量配货完成;20代表单商品多数量等待任务;21单商品多数量发货完成;22单商品多数量非单商品单数量订单;23单商品多数量订单发货异常;24单商品多数量订单已打印;25单商品多数量配货完成;30代表多商品多数量等待任务;31多商品多数量发货完成;32多商品多数量非单商品单数量订单;33多商品多数量订单发货异常;34多商品多数量订单已打印;35多商品多数量配货完成;默认0(不走快速发货系统);1代表单商品单数量任务池;2代表单商品多数量;3代表多商品多数量*/
	@Excel(name = "10代表单商品单数量等待任务;11单商品单数量发货完成;12单商品单数量非单商品单数量订单;13单商品单数量订单发货异常;14单商品单数量订单已打印;15单商品单数量配货完成;20代表单商品多数量等待任务;21单商品多数量发货完成;22单商品多数量非单商品单数量订单;23单商品多数量订单发货异常;24单商品多数量订单已打印;25单商品多数量配货完成;30代表多商品多数量等待任务;31多商品多数量发货完成;32多商品多数量非单商品单数量订单;33多商品多数量订单发货异常;34多商品多数量订单已打印;35多商品多数量配货完成;默认0(不走快速发货系统);1代表单商品单数量任务池;2代表单商品多数量;3代表多商品多数量", width = 15)
    @ApiModelProperty(value = "10代表单商品单数量等待任务;11单商品单数量发货完成;12单商品单数量非单商品单数量订单;13单商品单数量订单发货异常;14单商品单数量订单已打印;15单商品单数量配货完成;20代表单商品多数量等待任务;21单商品多数量发货完成;22单商品多数量非单商品单数量订单;23单商品多数量订单发货异常;24单商品多数量订单已打印;25单商品多数量配货完成;30代表多商品多数量等待任务;31多商品多数量发货完成;32多商品多数量非单商品单数量订单;33多商品多数量订单发货异常;34多商品多数量订单已打印;35多商品多数量配货完成;默认0(不走快速发货系统);1代表单商品单数量任务池;2代表单商品多数量;3代表多商品多数量")
	private java.lang.Integer quickpicktype;
	/**是否云分销订单1:是;2:3.0自营发货订单;3:海外仓订单;*/
	@Excel(name = "是否云分销订单1:是;2:3.0自营发货订单;3:海外仓订单;", width = 15)
    @ApiModelProperty(value = "是否云分销订单1:是;2:3.0自营发货订单;3:海外仓订单;")
	private java.lang.Integer iscloud;
	/**对应云分销平台记录ID*/
	@Excel(name = "对应云分销平台记录ID", width = 15)
    @ApiModelProperty(value = "对应云分销平台记录ID")
	private java.lang.Integer cloudid;
	/**云仓储的供货商ID*/
	@Excel(name = "云仓储的供货商ID", width = 15)
    @ApiModelProperty(value = "云仓储的供货商ID")
	private java.lang.Integer retailid;
	/**1 ：云仓储发货申请中   2：云仓储发货未申请*/
	@Excel(name = "1 ：云仓储发货申请中   2：云仓储发货未申请", width = 15)
    @ApiModelProperty(value = "1 ：云仓储发货申请中   2：云仓储发货未申请")
	private java.lang.Integer applydistribution;
	/**数据创建时间*/
	@Excel(name = "数据创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "数据创建时间")
	private java.util.Date createdate;
	/**快速发货时间*/
	@Excel(name = "快速发货时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "快速发货时间")
	private java.util.Date quickpicktime;
	/**1代表非fba订单，2代表fba订单*/
	@Excel(name = "1代表非fba订单，2代表fba订单", width = 15)
    @ApiModelProperty(value = "1代表非fba订单，2代表fba订单")
	private java.lang.Integer fbaflag;
	/**1代表非没有移动，2代表移到有货*/
	@Excel(name = "1代表非没有移动，2代表移到有货", width = 15)
    @ApiModelProperty(value = "1代表非没有移动，2代表移到有货")
	private java.lang.Integer movehasgoods;
	/**1代表未过期，2代表过期*/
	@Excel(name = "1代表未过期，2代表过期", width = 15)
    @ApiModelProperty(value = "1代表未过期，2代表过期")
	private java.lang.Integer smtflag;
	/**物流商信息同步 0未更新 1已更新速卖通线上运费*/
	@Excel(name = "物流商信息同步 0未更新 1已更新速卖通线上运费", width = 15)
    @ApiModelProperty(value = "物流商信息同步 0未更新 1已更新速卖通线上运费")
	private java.lang.Integer logisticsupdated;
	/**默认为订单拉取时间，由风控转为正常时更新此时间*/
	@Excel(name = "默认为订单拉取时间，由风控转为正常时更新此时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "默认为订单拉取时间，由风控转为正常时更新此时间")
	private java.util.Date createtime;
	/**发货后扣库存 1:扣 2不扣*/
	@Excel(name = "发货后扣库存 1:扣 2不扣", width = 15)
    @ApiModelProperty(value = "发货后扣库存 1:扣 2不扣")
	private java.lang.Integer isimmediately;
	/**countrycodereg*/
	@Excel(name = "countrycodereg", width = 15)
    @ApiModelProperty(value = "countrycodereg")
	private java.lang.String countrycodereg;
	/**订单真实重量-称重重量*/
	@Excel(name = "订单真实重量-称重重量", width = 15)
    @ApiModelProperty(value = "订单真实重量-称重重量")
	private java.lang.Double orderweight1;
	/**交运时间*/
	@Excel(name = "交运时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "交运时间")
	private java.util.Date transporttime;
	/**是否带磁性1-是 2-否*/
	@Excel(name = "是否带磁性1-是 2-否", width = 15)
    @ApiModelProperty(value = "是否带磁性1-是 2-否")
	private java.lang.Integer hasmagnetic;
	/**是否粉末 1-是 2-否*/
	@Excel(name = "是否粉末 1-是 2-否", width = 15)
    @ApiModelProperty(value = "是否粉末 1-是 2-否")
	private java.lang.Integer haspowder;
	/**是否侵权 1-是 2-否*/
	@Excel(name = "是否侵权 1-是 2-否", width = 15)
    @ApiModelProperty(value = "是否侵权 1-是 2-否")
	private java.lang.Integer hastort;
	/**退款时间*/
	@Excel(name = "退款时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "退款时间")
	private java.util.Date refundtime;
	/**转fba订单:0未转;1原始订单;2转后fba订单*/
	@Excel(name = "转fba订单:0未转;1原始订单;2转后fba订单", width = 15)
    @ApiModelProperty(value = "转fba订单:0未转;1原始订单;2转后fba订单")
	private java.lang.String fbaordertype;
	/**转fba订单Id;fbaOrderType=1为转后订单Id,=2为转前订单*/
	@Excel(name = "转fba订单Id;fbaOrderType=1为转后订单Id,=2为转前订单", width = 15)
    @ApiModelProperty(value = "转fba订单Id;fbaOrderType=1为转后订单Id,=2为转前订单")
	private java.lang.String fbaorderid;
	/**真实运费重量信息{\'shippingCost\':\'\',\'shippingWeight\':\'\'}*/
	@Excel(name = "真实运费重量信息{\'shippingCost\':\'\',\'shippingWeight\':\'\'}", width = 15)
    @ApiModelProperty(value = "真实运费重量信息{\'shippingCost\':\'\',\'shippingWeight\':\'\'}")
	private java.lang.String shippinginfo;
	/**用户是否确认 1 是 2 否*/
	@Excel(name = "用户是否确认 1 是 2 否", width = 15)
    @ApiModelProperty(value = "用户是否确认 1 是 2 否")
	private java.lang.Integer isconfirm;
	/**作废时间*/
	@Excel(name = "作废时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "作废时间")
	private java.util.Date timecancel;
	/**Wish订单是否妥投,1是2不是*/
	@Excel(name = "Wish订单是否妥投,1是2不是", width = 15)
    @ApiModelProperty(value = "Wish订单是否妥投,1是2不是")
	private java.lang.Integer istuotou;
	/**是否wish epc订单1是2不是*/
	@Excel(name = "是否wish epc订单1是2不是", width = 15)
    @ApiModelProperty(value = "是否wish epc订单1是2不是")
	private java.lang.Integer iswishepc;
	/**预览打印次数*/
	@Excel(name = "预览打印次数", width = 15)
    @ApiModelProperty(value = "预览打印次数")
	private java.lang.Integer printpreviewcount;
	/**标记是否有速卖通纠纷1，是 2，不是*/
	@Excel(name = "标记是否有速卖通纠纷1，是 2，不是", width = 15)
    @ApiModelProperty(value = "标记是否有速卖通纠纷1，是 2，不是")
	private java.lang.Integer isissueorder;
	/**statsu纠纷状态，afterSaleWarranty1是售后宝纠纷，2不是售后宝纠纷*/
	@Excel(name = "statsu纠纷状态，afterSaleWarranty1是售后宝纠纷，2不是售后宝纠纷", width = 15)
    @ApiModelProperty(value = "statsu纠纷状态，afterSaleWarranty1是售后宝纠纷，2不是售后宝纠纷")
	private java.lang.String issuestatus;
	/**默认1,10：cod标识    11：we标识   12：既是cod标识也是WE标识*/
	@Excel(name = "默认1,10：cod标识    11：we标识   12：既是cod标识也是WE标识", width = 15)
    @ApiModelProperty(value = "默认1,10：cod标识    11：we标识   12：既是cod标识也是WE标识")
	private java.lang.Integer codflag;
	/**默认0;wishA计划订单=1 vova集运订单=2*/
	@Excel(name = "默认0;wishA计划订单=1 vova集运订单=2", width = 15)
    @ApiModelProperty(value = "默认0;wishA计划订单=1 vova集运订单=2")
	private java.lang.Integer orderstandbyfield1;
	/**默认0;shopee的子店铺id*/
	@Excel(name = "默认0;shopee的子店铺id", width = 15)
    @ApiModelProperty(value = "默认0;shopee的子店铺id")
	private java.lang.Integer orderstandbyfield2;
	/**1.显示,2.不显示*/
	@Excel(name = "1.显示,2.不显示", width = 15)
    @ApiModelProperty(value = "1.显示,2.不显示")
	private java.lang.Integer orderflag;
	/**预估利润*/
	@Excel(name = "预估利润", width = 15)
    @ApiModelProperty(value = "预估利润")
	private java.math.BigDecimal prepaidprofit;
	/**预估利润率   乘以100%之后的利润率*/
	@Excel(name = "预估利润率   乘以100%之后的利润率", width = 15)
    @ApiModelProperty(value = "预估利润率   乘以100%之后的利润率")
	private java.math.BigDecimal prepaymentmargin;
	/**预估利润状态1预估利润未计算 2预估利润计算中 3 预估利润计算成功 4预估利润计算异常*/
	@Excel(name = "预估利润状态1预估利润未计算 2预估利润计算中 3 预估利润计算成功 4预估利润计算异常", width = 15)
    @ApiModelProperty(value = "预估利润状态1预估利润未计算 2预估利润计算中 3 预估利润计算成功 4预估利润计算异常")
	private java.lang.Integer prepaidprofitstatus;
	/**预估利润是否达标 1达标 2不达标*/
	@Excel(name = "预估利润是否达标 1达标 2不达标", width = 15)
    @ApiModelProperty(value = "预估利润是否达标 1达标 2不达标")
	private java.lang.Integer isupfrontprofit;
	/**订单类型 1正常订单 2FB订单 3补发订单 4 未收到重发 5 已收到重发*/
	@Excel(name = "订单类型 1正常订单 2FB订单 3补发订单 4 未收到重发 5 已收到重发", width = 15)
    @ApiModelProperty(value = "订单类型 1正常订单 2FB订单 3补发订单 4 未收到重发 5 已收到重发")
	private java.lang.Integer ordertype;
	/**sku状态 1 sku待审核 2初步解析成功 3sku审核中 4 sku审核成功 5 sku审核异常 备注订单明细中若有一个sku未解析成可发货SKU，状态都是SKU审核异常*/
	@Excel(name = "sku状态 1 sku待审核 2初步解析成功 3sku审核中 4 sku审核成功 5 sku审核异常 备注订单明细中若有一个sku未解析成可发货SKU，状态都是SKU审核异常", width = 15)
    @ApiModelProperty(value = "sku状态 1 sku待审核 2初步解析成功 3sku审核中 4 sku审核成功 5 sku审核异常 备注订单明细中若有一个sku未解析成可发货SKU，状态都是SKU审核异常")
	private java.lang.Integer skustatus;
	/**匹配货运方式 1未执行物流脚本2执行物流脚本中 3执行物流脚本成功 4执行二次物流脚本 5执行二次物流脚本成功 6匹配货运方式异常*/
	@Excel(name = "匹配货运方式 1未执行物流脚本2执行物流脚本中 3执行物流脚本成功 4执行二次物流脚本 5执行二次物流脚本成功 6匹配货运方式异常", width = 15)
    @ApiModelProperty(value = "匹配货运方式 1未执行物流脚本2执行物流脚本中 3执行物流脚本成功 4执行二次物流脚本 5执行二次物流脚本成功 6匹配货运方式异常")
	private java.lang.Integer matchshippingmethod;
	/**是否需要人工审核 1 是 0 否*/
	@Excel(name = "是否需要人工审核 1 是 0 否", width = 15)
    @ApiModelProperty(value = "是否需要人工审核 1 是 0 否")
	private java.lang.Integer ismanualreview;
	/**审核类型 1 新订单 2匹配货运方式 3 预估利润 4 自动交运 5 无*/
	@Excel(name = "审核类型 1 新订单 2匹配货运方式 3 预估利润 4 自动交运 5 无", width = 15)
    @ApiModelProperty(value = "审核类型 1 新订单 2匹配货运方式 3 预估利润 4 自动交运 5 无")
	private java.lang.Integer audittype;
	/**物流等级,ID之间逗号分隔,前后都有逗号，例：,1,2,*/
	@Excel(name = "物流等级,ID之间逗号分隔,前后都有逗号，例：,1,2,", width = 15)
    @ApiModelProperty(value = "物流等级,ID之间逗号分隔,前后都有逗号，例：,1,2,")
	private java.lang.String logisticslevel;
	/**订单属性,属性名称中间用逗号分隔，例：,属性1,属性2,*/
	@Excel(name = "订单属性,属性名称中间用逗号分隔，例：,属性1,属性2,", width = 15)
    @ApiModelProperty(value = "订单属性,属性名称中间用逗号分隔，例：,属性1,属性2,")
	private java.lang.String orderattr;
	/**平台发货天数*/
	@Excel(name = "平台发货天数", width = 15)
    @ApiModelProperty(value = "平台发货天数")
	private java.lang.Integer expressdays;
	/**国家中文名*/
	@Excel(name = "国家中文名", width = 15)
    @ApiModelProperty(value = "国家中文名")
	private java.lang.String countrynameCn;
	/**国家英文名*/
	@Excel(name = "国家英文名", width = 15)
    @ApiModelProperty(value = "国家英文名")
	private java.lang.String countrynameEn;
	/**即将发货最小天数：（当前日期-订单日期）的天数 - 平台发货天数 < 即将发货最小天数，即为即将到期订单*/
	@Excel(name = "即将发货最小天数：（当前日期-订单日期）的天数 - 平台发货天数 < 即将发货最小天数，即为即将到期订单", width = 15)
    @ApiModelProperty(value = "即将发货最小天数：（当前日期-订单日期）的天数 - 平台发货天数 < 即将发货最小天数，即为即将到期订单")
	private java.lang.Integer expressmindays;
	/**下载时间*/
	@Excel(name = "下载时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "下载时间")
	private java.util.Date downtime;
	/**订单拆分后，关联的订单编号platformOrderId 以 id1，id2*/
	@Excel(name = "订单拆分后，关联的订单编号platformOrderId 以 id1，id2", width = 15)
    @ApiModelProperty(value = "订单拆分后，关联的订单编号platformOrderId 以 id1，id2")
	private java.lang.String splitplatformorderids;
	/**货运方式名称*/
	@Excel(name = "货运方式名称", width = 15)
    @ApiModelProperty(value = "货运方式名称")
	private java.lang.String logisticschannelname;
	/**店铺标签主键，以 ,1,2, 格式保存*/
	@Excel(name = "店铺标签主键，以 ,1,2, 格式保存", width = 15)
    @ApiModelProperty(value = "店铺标签主键，以 ,1,2, 格式保存")
	private java.lang.String shoptagids;
	/**修改时间*/
	@Excel(name = "修改时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
	private java.util.Date gmtModified;
	/**订单合并后，关联的订单编号platformOrderId 以 id1，id2*/
	@Excel(name = "订单合并后，关联的订单编号platformOrderId 以 id1，id2", width = 15)
    @ApiModelProperty(value = "订单合并后，关联的订单编号platformOrderId 以 id1，id2")
	private java.lang.String mergeplatformorderids;
	/**是否海外仓订单 1 是 0 否*/
	@Excel(name = "是否海外仓订单 1 是 0 否", width = 15)
    @ApiModelProperty(value = "是否海外仓订单 1 是 0 否")
	private java.lang.Integer ishw;
	/**货运方式标签ids*/
	@Excel(name = "货运方式标签ids", width = 15)
    @ApiModelProperty(value = "货运方式标签ids")
	private java.lang.String logisticschanneltags;
	/**自动交运标识 1 是 0 否 匹配是否可以自动交运的脚本修改该标识，添加自动交运任务时只添加1的订单，添加手动标记的自动交运任务时只添加0的订单*/
	@Excel(name = "自动交运标识 1 是 0 否 匹配是否可以自动交运的脚本修改该标识，添加自动交运任务时只添加1的订单，添加手动标记的自动交运任务时只添加0的订单", width = 15)
    @ApiModelProperty(value = "自动交运标识 1 是 0 否 匹配是否可以自动交运的脚本修改该标识，添加自动交运任务时只添加1的订单，添加手动标记的自动交运任务时只添加0的订单")
	private java.lang.Integer allowautomaticlogistics;
	/**自动交运添加任务dataStr字段值*/
	@Excel(name = "自动交运添加任务dataStr字段值", width = 15)
    @ApiModelProperty(value = "自动交运添加任务dataStr字段值")
	private java.lang.Object datastr;
	/**复制、重发订单关联的订单编号*/
	@Excel(name = "复制、重发订单关联的订单编号", width = 15)
    @ApiModelProperty(value = "复制、重发订单关联的订单编号")
	private java.lang.String copyplatformorderids;
	/**是否可转发货系统，默认0不可转发货系统，1可转。由自动交运后修改状态；由此字段判断是否可转发货系统；海外订单默认0；手工上传单号和面单文件的中仓订单成功后，修改成1；马帮更新该字段*/
	@Excel(name = "是否可转发货系统，默认0不可转发货系统，1可转。由自动交运后修改状态；由此字段判断是否可转发货系统；海外订单默认0；手工上传单号和面单文件的中仓订单成功后，修改成1；马帮更新该字段", width = 15)
    @ApiModelProperty(value = "是否可转发货系统，默认0不可转发货系统，1可转。由自动交运后修改状态；由此字段判断是否可转发货系统；海外订单默认0；手工上传单号和面单文件的中仓订单成功后，修改成1；马帮更新该字段")
	private java.lang.Integer sendautomaticlogistics;
}
