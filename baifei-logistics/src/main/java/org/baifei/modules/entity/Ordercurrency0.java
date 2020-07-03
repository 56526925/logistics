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
 * @Description: 订单金额汇总信息
 * @Author: jeecg-boot
 * @Date:   2020-06-11
 * @Version: V1.0
 */
@Data
@TableName("db_ordercurrency0")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="db_ordercurrency0对象", description="订单金额汇总信息")
public class Ordercurrency0 {

	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
	@ApiModelProperty(value = "id")
	private java.lang.Integer id;
	/**companyid*/
	@Excel(name = "companyid", width = 15)
	@ApiModelProperty(value = "companyid")
	private java.lang.Integer companyid;
	/**shopid*/
	@Excel(name = "shopid", width = 15)
	@ApiModelProperty(value = "shopid")
	private java.lang.Integer shopid;
	/**orderid*/
	@Excel(name = "orderid", width = 15)
	@ApiModelProperty(value = "orderid")
	private java.lang.Integer orderid;
	/**平台资金状态*/
	@Excel(name = "平台资金状态", width = 15)
	@ApiModelProperty(value = "平台资金状态")
	private java.lang.String fundstatus;
	/**原始商品售价*/
	@Excel(name = "原始商品售价", width = 15)
	@ApiModelProperty(value = "原始商品售价")
	private java.math.BigDecimal itemtotalorigin;
	/**原始物流总价*/
	@Excel(name = "原始物流总价", width = 15)
	@ApiModelProperty(value = "原始物流总价")
	private java.math.BigDecimal shippingtotalorigin;
	/**原始平台交易费*/
	@Excel(name = "原始平台交易费", width = 15)
	@ApiModelProperty(value = "原始平台交易费")
	private java.math.BigDecimal platformfeeorigin;
	/**原始保险费*/
	@Excel(name = "原始保险费", width = 15)
	@ApiModelProperty(value = "原始保险费")
	private java.math.BigDecimal insurancefeeorigin;
	/**平台币种*/
	@Excel(name = "平台币种", width = 15)
	@ApiModelProperty(value = "平台币种")
	private java.lang.String currencyid;
	/**原始贝宝转账费*/
	@Excel(name = "原始贝宝转账费", width = 15)
	@ApiModelProperty(value = "原始贝宝转账费")
	private java.math.BigDecimal paypalfeeorigin;
	/**贝宝币种*/
	@Excel(name = "贝宝币种", width = 15)
	@ApiModelProperty(value = "贝宝币种")
	private java.lang.String paypalcurrencyid;
	/**汇率*/
	@Excel(name = "汇率", width = 15)
	@ApiModelProperty(value = "汇率")
	private java.math.BigDecimal currencyrate;
	/**商品总售价*/
	@Excel(name = "商品总售价", width = 15)
	@ApiModelProperty(value = "商品总售价")
	private java.math.BigDecimal itemtotal;
	/**物流总收入*/
	@Excel(name = "物流总收入", width = 15)
	@ApiModelProperty(value = "物流总收入")
	private java.math.BigDecimal shippingfee;
	/**平台费*/
	@Excel(name = "平台费", width = 15)
	@ApiModelProperty(value = "平台费")
	private java.math.BigDecimal platformfee;
	/**保险费*/
	@Excel(name = "保险费", width = 15)
	@ApiModelProperty(value = "保险费")
	private java.math.BigDecimal insurancefee;
	/**贝宝转账费*/
	@Excel(name = "贝宝转账费", width = 15)
	@ApiModelProperty(value = "贝宝转账费")
	private java.math.BigDecimal paypalfee;
	/**商品总成本*/
	@Excel(name = "商品总成本", width = 15)
	@ApiModelProperty(value = "商品总成本")
	private java.math.BigDecimal itemtotalcost;
	/**真实物流运费*/
	@Excel(name = "真实物流运费", width = 15)
	@ApiModelProperty(value = "真实物流运费")
	private java.math.BigDecimal shippingcost;
	/**估算物流运费*/
	@Excel(name = "估算物流运费", width = 15)
	@ApiModelProperty(value = "估算物流运费")
	private java.math.BigDecimal shippingprecost;
	/**包材费*/
	@Excel(name = "包材费", width = 15)
	@ApiModelProperty(value = "包材费")
	private java.math.BigDecimal packagefee;
	/**利润*/
	@Excel(name = "利润", width = 15)
	@ApiModelProperty(value = "利润")
	private java.math.BigDecimal profit;
	/**利润率*/
	@Excel(name = "利润率", width = 15)
	@ApiModelProperty(value = "利润率")
	private java.math.BigDecimal profitrate;
	/**拆分费用标志0未拆分1已拆分*/
	@Excel(name = "拆分费用标志0未拆分1已拆分", width = 15)
	@ApiModelProperty(value = "拆分费用标志0未拆分1已拆分")
	private java.lang.Integer splitflag;
	/**FBA每笔订单配送服务费*/
	@Excel(name = "FBA每笔订单配送服务费", width = 15)
	@ApiModelProperty(value = "FBA每笔订单配送服务费")
	private java.math.BigDecimal fbaperorderfulfillmentfee;
	/**亚马逊平台佣金*/
	@Excel(name = "亚马逊平台佣金", width = 15)
	@ApiModelProperty(value = "亚马逊平台佣金")
	private java.math.BigDecimal fbacommission;
	/**原始退款金额*/
	@Excel(name = "原始退款金额", width = 15)
	@ApiModelProperty(value = "原始退款金额")
	private java.math.BigDecimal refundfeeorigin;
	/**退款金额币种*/
	@Excel(name = "退款金额币种", width = 15)
	@ApiModelProperty(value = "退款金额币种")
	private java.lang.String refundfeecurrencyid;
	/**折扣原始金额*/
	@Excel(name = "折扣原始金额", width = 15)
	@ApiModelProperty(value = "折扣原始金额")
	private java.math.BigDecimal promotionamountorigin;
	/**折扣RMB金额*/
	@Excel(name = "折扣RMB金额", width = 15)
	@ApiModelProperty(value = "折扣RMB金额")
	private java.math.BigDecimal promotionamount;
	/**亚马逊物流基础服务费*/
	@Excel(name = "亚马逊物流基础服务费", width = 15)
	@ApiModelProperty(value = "亚马逊物流基础服务费")
	private java.math.BigDecimal fbaperunitfulfillmentfee;
	/**fba亚马逊物流配送费*/
	@Excel(name = "fba亚马逊物流配送费", width = 15)
	@ApiModelProperty(value = "fba亚马逊物流配送费")
	private java.math.BigDecimal fbaweightbasedfee;
	/**FBA货到付款金额*/
	@Excel(name = "FBA货到付款金额", width = 15)
	@ApiModelProperty(value = "FBA货到付款金额")
	private java.lang.String codcharge;
	/**联盟佣金（支出）*/
	@Excel(name = "联盟佣金（支出）", width = 15)
	@ApiModelProperty(value = "联盟佣金（支出）")
	private java.math.BigDecimal alliancefee;
	/**原始联盟佣金（支出）*/
	@Excel(name = "原始联盟佣金（支出）", width = 15)
	@ApiModelProperty(value = "原始联盟佣金（支出）")
	private java.math.BigDecimal alliancefeeorigin;
	/**税费*/
	@Excel(name = "税费", width = 15)
	@ApiModelProperty(value = "税费")
	private java.math.BigDecimal fax;
	/**原始税费*/
	@Excel(name = "原始税费", width = 15)
	@ApiModelProperty(value = "原始税费")
	private java.math.BigDecimal originfax;
	/**赠品费用*/
	@Excel(name = "赠品费用", width = 15)
	@ApiModelProperty(value = "赠品费用")
	private java.math.BigDecimal giftwrapprice;
	/**原始赠品费用*/
	@Excel(name = "原始赠品费用", width = 15)
	@ApiModelProperty(value = "原始赠品费用")
	private java.math.BigDecimal giftwrappriceorigin;
	/**原始vat费用（支出）*/
	@Excel(name = "原始vat费用（支出）", width = 15)
	@ApiModelProperty(value = "原始vat费用（支出）")
	private java.math.BigDecimal vatfeeorigin;
	/**vat费用*/
	@Excel(name = "vat费用", width = 15)
	@ApiModelProperty(value = "vat费用")
	private java.math.BigDecimal vatfee;
	/**shippingchargeback*/
	@Excel(name = "shippingchargeback", width = 15)
	@ApiModelProperty(value = "shippingchargeback")
	private java.math.BigDecimal shippingchargeback;
	/**variableclosingfee*/
	@Excel(name = "variableclosingfee", width = 15)
	@ApiModelProperty(value = "variableclosingfee")
	private java.math.BigDecimal variableclosingfee;
	/**原始优惠金额*/
	@Excel(name = "原始优惠金额", width = 15)
	@ApiModelProperty(value = "原始优惠金额")
	private java.math.BigDecimal voucherpriceorigin;
	/**优惠金额RMB*/
	@Excel(name = "优惠金额RMB", width = 15)
	@ApiModelProperty(value = "优惠金额RMB")
	private java.math.BigDecimal voucherprice;
	/**原始补贴金额*/
	@Excel(name = "原始补贴金额", width = 15)
	@ApiModelProperty(value = "原始补贴金额")
	private java.math.BigDecimal subsidyamountorigin;
	/**补贴金额*/
	@Excel(name = "补贴金额", width = 15)
	@ApiModelProperty(value = "补贴金额")
	private java.math.BigDecimal subsidyamount;
}
