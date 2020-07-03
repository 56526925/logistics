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
 * @Description: 订单详情
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
@Data
@TableName("db_orderitem0")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="db_orderitem0对象", description="订单详情")
public class Orderitem0 {
    
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
	/**库存SKU编号-默认仓库库存ID*/
	@Excel(name = "库存SKU编号-默认仓库库存ID", width = 15)
    @ApiModelProperty(value = "库存SKU编号-默认仓库库存ID")
	private java.lang.Integer stockid;
	/**库存SKU*/
	@Excel(name = "库存SKU", width = 15)
    @ApiModelProperty(value = "库存SKU")
	private java.lang.String stocksku;
	/**商品标题*/
	@Excel(name = "商品标题", width = 15)
    @ApiModelProperty(value = "商品标题")
	private java.lang.String title;
	/**数量*/
	@Excel(name = "数量", width = 15)
    @ApiModelProperty(value = "数量")
	private java.lang.Integer quantity;
	/**交易编号(店铺唯一)*/
	@Excel(name = "交易编号(店铺唯一)", width = 15)
    @ApiModelProperty(value = "交易编号(店铺唯一)")
	private java.lang.String salesrecordnumber;
	/**交易编号(平台唯一，ebay用)*/
	@Excel(name = "交易编号(平台唯一，ebay用)", width = 15)
    @ApiModelProperty(value = "交易编号(平台唯一，ebay用)")
	private java.lang.String transactionid;
	/**itemid*/
	@Excel(name = "itemid", width = 15)
    @ApiModelProperty(value = "itemid")
	private java.lang.String itemid;
	/**原始售价*/
	@Excel(name = "原始售价", width = 15)
    @ApiModelProperty(value = "原始售价")
	private java.math.BigDecimal sellpriceorigin;
	/**原始平台交易费*/
	@Excel(name = "原始平台交易费", width = 15)
    @ApiModelProperty(value = "原始平台交易费")
	private java.math.BigDecimal platformfeeorigin;
	/**平台交易费*/
	@Excel(name = "平台交易费", width = 15)
    @ApiModelProperty(value = "平台交易费")
	private java.math.BigDecimal platformfee;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private java.lang.String currencyid;
	/**汇率*/
	@Excel(name = "汇率", width = 15)
    @ApiModelProperty(value = "汇率")
	private java.math.BigDecimal currencyrate;
	/**原始物流费(收入)*/
	@Excel(name = "原始物流费(收入)", width = 15)
    @ApiModelProperty(value = "原始物流费(收入)")
	private java.math.BigDecimal shippingfeeorigin;
	/**物流费(收入)*/
	@Excel(name = "物流费(收入)", width = 15)
    @ApiModelProperty(value = "物流费(收入)")
	private java.math.BigDecimal shippingfee;
	/**导入批次（yyyyMMddhhmmss)*/
	@Excel(name = "导入批次（yyyyMMddhhmmss)", width = 15)
    @ApiModelProperty(value = "导入批次（yyyyMMddhhmmss)")
	private java.lang.String importid;
	/**多物品选择*/
	@Excel(name = "多物品选择", width = 15)
    @ApiModelProperty(value = "多物品选择")
	private java.lang.String specifics;
	/**售价*/
	@Excel(name = "售价", width = 15)
    @ApiModelProperty(value = "售价")
	private java.math.BigDecimal sellprice;
	/**成本价*/
	@Excel(name = "成本价", width = 15)
    @ApiModelProperty(value = "成本价")
	private java.math.BigDecimal costprice;
	/**产品单位如pcs,piece,lots等*/
	@Excel(name = "产品单位如pcs,piece,lots等", width = 15)
    @ApiModelProperty(value = "产品单位如pcs,piece,lots等")
	private java.lang.String productunit;
	/**买家自选物流*/
	@Excel(name = "买家自选物流", width = 15)
    @ApiModelProperty(value = "买家自选物流")
	private java.lang.String shippingservice;
	/**交易单号+sku+数量生成的唯一码，防止重复*/
	@Excel(name = "交易单号+sku+数量生成的唯一码，防止重复", width = 15)
    @ApiModelProperty(value = "交易单号+sku+数量生成的唯一码，防止重复")
	private java.lang.String identifyid;
	/**图片路径*/
	@Excel(name = "图片路径", width = 15)
    @ApiModelProperty(value = "图片路径")
	private java.lang.String pictureurl;
	/**支付时间*/
	@Excel(name = "支付时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "支付时间")
	private java.util.Date paidtime;
	/**目录ID*/
	@Excel(name = "目录ID", width = 15)
    @ApiModelProperty(value = "目录ID")
	private java.lang.Integer categoryid;
	/**仓库id*/
	@Excel(name = "仓库id", width = 15)
    @ApiModelProperty(value = "仓库id")
	private java.lang.Integer stockwarehouseid;
	/**仓位id-暂不用*/
	@Excel(name = "仓位id-暂不用", width = 15)
    @ApiModelProperty(value = "仓位id-暂不用")
	private java.lang.Integer stockgridid;
	/**仓位*/
	@Excel(name = "仓位", width = 15)
    @ApiModelProperty(value = "仓位")
	private java.lang.String stockgrid;
	/**库存是否充足  1充足  2不充足  0未计算过*/
	@Excel(name = "库存是否充足  1充足  2不充足  0未计算过", width = 15)
    @ApiModelProperty(value = "库存是否充足  1充足  2不充足  0未计算过")
	private java.lang.Integer hasgoods;
	/**买家给的评价  1未评价  2好评  3中评  4差评*/
	@Excel(name = "买家给的评价  1未评价  2好评  3中评  4差评", width = 15)
    @ApiModelProperty(value = "买家给的评价  1未评价  2好评  3中评  4差评")
	private java.lang.Integer buyerfeedback;
	/**是否已经给买家好评  1未评价  2已给予  3好评失败*/
	@Excel(name = "是否已经给买家好评  1未评价  2已给予  3好评失败", width = 15)
    @ApiModelProperty(value = "是否已经给买家好评  1未评价  2已给予  3好评失败")
	private java.lang.Integer isfeedback;
	/**是否同步到电商平台  1未同步  2强制同步  6不需要同步  7同步失败  9同步成功*/
	@Excel(name = "是否同步到电商平台  1未同步  2强制同步  6不需要同步  7同步失败  9同步成功", width = 15)
    @ApiModelProperty(value = "是否同步到电商平台  1未同步  2强制同步  6不需要同步  7同步失败  9同步成功")
	private java.lang.Integer issyncplatform;
	/**同步结果描述*/
	@Excel(name = "同步结果描述", width = 15)
    @ApiModelProperty(value = "同步结果描述")
	private java.lang.String issyncplatformdescr;
	/**商品处理状态  1未付款订单,不发货  2已付款订单,暂不发货  3已发货  4作废*/
	@Excel(name = "商品处理状态  1未付款订单,不发货  2已付款订单,暂不发货  3已发货  4作废", width = 15)
    @ApiModelProperty(value = "商品处理状态  1未付款订单,不发货  2已付款订单,暂不发货  3已发货  4作废")
	private java.lang.Integer status;
	/**原始SKU*/
	@Excel(name = "原始SKU", width = 15)
    @ApiModelProperty(value = "原始SKU")
	private java.lang.String platformsku;
	/**原始数量*/
	@Excel(name = "原始数量", width = 15)
    @ApiModelProperty(value = "原始数量")
	private java.lang.Integer platformquantity;
	/**是否捆绑商品拆分  1是  2不是*/
	@Excel(name = "是否捆绑商品拆分  1是  2不是", width = 15)
    @ApiModelProperty(value = "是否捆绑商品拆分  1是  2不是")
	private java.lang.Integer iscombo;
	/**商品单位重量*/
	@Excel(name = "商品单位重量", width = 15)
    @ApiModelProperty(value = "商品单位重量")
	private java.math.BigDecimal unitweight;
	/**包材编号*/
	@Excel(name = "包材编号", width = 15)
    @ApiModelProperty(value = "包材编号")
	private java.lang.Integer packageid;
	/**配货员*/
	@Excel(name = "配货员", width = 15)
    @ApiModelProperty(value = "配货员")
	private java.lang.Integer deliveroperid;
	/**销售员账号*/
	@Excel(name = "销售员账号", width = 15)
    @ApiModelProperty(value = "销售员账号")
	private java.lang.Integer salesoperid;
	/**拆分包材成本*/
	@Excel(name = "拆分包材成本", width = 15)
    @ApiModelProperty(value = "拆分包材成本")
	private java.math.BigDecimal splitpackagefee;
	/**拆分物流收入*/
	@Excel(name = "拆分物流收入", width = 15)
    @ApiModelProperty(value = "拆分物流收入")
	private java.math.BigDecimal splitshippingfee;
	/**拆分物流成本*/
	@Excel(name = "拆分物流成本", width = 15)
    @ApiModelProperty(value = "拆分物流成本")
	private java.math.BigDecimal splitshippingcost;
	/**拆分转账费*/
	@Excel(name = "拆分转账费", width = 15)
    @ApiModelProperty(value = "拆分转账费")
	private java.math.BigDecimal splitpaypalfee;
	/**操作员*/
	@Excel(name = "操作员", width = 15)
    @ApiModelProperty(value = "操作员")
	private java.lang.Integer operid;
	/**操作时间*/
	@Excel(name = "操作时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "操作时间")
	private java.util.Date opertime;
	/**平台类型:1eBay,2Amazon,3SMT*/
	@Excel(name = "平台类型:1eBay,2Amazon,3SMT", width = 15)
    @ApiModelProperty(value = "平台类型:1eBay,2Amazon,3SMT")
	private java.lang.Integer platformid;
	/**原始订单id,以后不再修改*/
	@Excel(name = "原始订单id,以后不再修改", width = 15)
    @ApiModelProperty(value = "原始订单id,以后不再修改")
	private java.lang.Integer originorderid;
	/**订单来源:1手动2上传3接口*/
	@Excel(name = "订单来源:1手动2上传3接口", width = 15)
    @ApiModelProperty(value = "订单来源:1手动2上传3接口")
	private java.lang.Integer source;
	/**1已验货2未验货*/
	@Excel(name = "1已验货2未验货", width = 15)
    @ApiModelProperty(value = "1已验货2未验货")
	private java.lang.Integer ischecked;
	/**amazonAsin*/
	@Excel(name = "amazonAsin", width = 15)
    @ApiModelProperty(value = "amazonAsin")
	private java.lang.String amazonasin;
	/**亚马逊的站点*/
	@Excel(name = "亚马逊的站点", width = 15)
    @ApiModelProperty(value = "亚马逊的站点")
	private java.lang.String amazonsite;
	/**申报名称*/
	@Excel(name = "申报名称", width = 15)
    @ApiModelProperty(value = "申报名称")
	private java.lang.String declarename;
	/**申报英文名*/
	@Excel(name = "申报英文名", width = 15)
    @ApiModelProperty(value = "申报英文名")
	private java.lang.String declareename;
	/**申报价值*/
	@Excel(name = "申报价值", width = 15)
    @ApiModelProperty(value = "申报价值")
	private java.math.BigDecimal declarefee;
	/**海关申报重量(g)*/
	@Excel(name = "海关申报重量(g)", width = 15)
    @ApiModelProperty(value = "海关申报重量(g)")
	private java.math.BigDecimal declareweight;
	/**海关编码*/
	@Excel(name = "海关编码", width = 15)
    @ApiModelProperty(value = "海关编码")
	private java.lang.String declarecustoms;
	/**对应云仓储的原厂SKU*/
	@Excel(name = "对应云仓储的原厂SKU", width = 15)
    @ApiModelProperty(value = "对应云仓储的原厂SKU")
	private java.lang.String storagesku;
	/**美元汇率*/
	@Excel(name = "美元汇率", width = 15)
    @ApiModelProperty(value = "美元汇率")
	private java.math.BigDecimal usdrate;
	/**fba订单商品的OrderFulfillmentFee费用*/
	@Excel(name = "fba订单商品的OrderFulfillmentFee费用", width = 15)
    @ApiModelProperty(value = "fba订单商品的OrderFulfillmentFee费用")
	private java.math.BigDecimal fbaperorderfulfillmentfee;
	/**fba订单商品的UnitFulfillmentFee费用*/
	@Excel(name = "fba订单商品的UnitFulfillmentFee费用", width = 15)
    @ApiModelProperty(value = "fba订单商品的UnitFulfillmentFee费用")
	private java.math.BigDecimal fbaperunitfulfillmentfee;
	/**fba订单商品的WeightBasedFee费用*/
	@Excel(name = "fba订单商品的WeightBasedFee费用", width = 15)
    @ApiModelProperty(value = "fba订单商品的WeightBasedFee费用")
	private java.math.BigDecimal fbaweightbasedfee;
	/**fba订单商品的WeightBasedFee费用*/
	@Excel(name = "fba订单商品的WeightBasedFee费用", width = 15)
    @ApiModelProperty(value = "fba订单商品的WeightBasedFee费用")
	private java.math.BigDecimal fbacommission;
	/**1因拆份而重复SKU,默认0不是*/
	@Excel(name = "1因拆份而重复SKU,默认0不是", width = 15)
    @ApiModelProperty(value = "1因拆份而重复SKU,默认0不是")
	private java.lang.Integer isrepeatitem;
	/**fba平均头程*/
	@Excel(name = "fba平均头程", width = 15)
    @ApiModelProperty(value = "fba平均头程")
	private java.math.BigDecimal headshipping;
	/**折扣原始金额*/
	@Excel(name = "折扣原始金额", width = 15)
    @ApiModelProperty(value = "折扣原始金额")
	private java.math.BigDecimal promotionamountorigin;
	/**折扣RMB金额*/
	@Excel(name = "折扣RMB金额", width = 15)
    @ApiModelProperty(value = "折扣RMB金额")
	private java.math.BigDecimal promotionamount;
	/**fba分摊头程运费,号分割，两边加,后续搜索*/
	@Excel(name = "fba分摊头程运费,号分割，两边加,后续搜索", width = 15)
    @ApiModelProperty(value = "fba分摊头程运费,号分割，两边加,后续搜索")
	private java.lang.String fbahpreeids;
	/**手动订单报关json*/
	@Excel(name = "手动订单报关json", width = 15)
    @ApiModelProperty(value = "手动订单报关json")
	private java.lang.String declarejson;
	/**销售标题*/
	@Excel(name = "销售标题", width = 15)
    @ApiModelProperty(value = "销售标题")
	private java.lang.String origintitle;
	/**采购编码*/
	@Excel(name = "采购编码", width = 15)
    @ApiModelProperty(value = "采购编码")
	private java.lang.String purchasecode;
	/**是否ALI尖货仓库1-是 2-否*/
	@Excel(name = "是否ALI尖货仓库1-是 2-否", width = 15)
    @ApiModelProperty(value = "是否ALI尖货仓库1-是 2-否")
	private java.lang.Integer isalitipwhse;
	/**联盟佣金*/
	@Excel(name = "联盟佣金", width = 15)
    @ApiModelProperty(value = "联盟佣金")
	private java.lang.Double alliancefee;
	/**原始联盟佣金*/
	@Excel(name = "原始联盟佣金", width = 15)
    @ApiModelProperty(value = "原始联盟佣金")
	private java.lang.Double alliancefeeorigin;
	/**拆分后退款费用*/
	@Excel(name = "拆分后退款费用", width = 15)
    @ApiModelProperty(value = "拆分后退款费用")
	private java.lang.Double splitrefundfee;
	/**亚马逊买家定制信息*/
	@Excel(name = "亚马逊买家定制信息", width = 15)
    @ApiModelProperty(value = "亚马逊买家定制信息")
	private java.lang.String customizedurl;
	/**头程运费状态1-未分摊 2-分摊完成 3-部分分摊*/
	@Excel(name = "头程运费状态1-未分摊 2-分摊完成 3-部分分摊", width = 15)
    @ApiModelProperty(value = "头程运费状态1-未分摊 2-分摊完成 3-部分分摊")
	private java.lang.String headshippingstatus;
	/**商品备注*/
	@Excel(name = "商品备注", width = 15)
    @ApiModelProperty(value = "商品备注")
	private java.lang.String itemremark;
	/**商品开发员*/
	@Excel(name = "商品开发员", width = 15)
    @ApiModelProperty(value = "商品开发员")
	private java.lang.String developerid;
	/**采购员*/
	@Excel(name = "采购员", width = 15)
    @ApiModelProperty(value = "采购员")
	private java.lang.String purchaseid;
	/**美工*/
	@Excel(name = "美工", width = 15)
    @ApiModelProperty(value = "美工")
	private java.lang.String artdesignerid;
	/**原始税费*/
	@Excel(name = "原始税费", width = 15)
    @ApiModelProperty(value = "原始税费")
	private java.lang.Double originfax;
	/**税费*/
	@Excel(name = "税费", width = 15)
    @ApiModelProperty(value = "税费")
	private java.lang.Double fax;
	/**是否赠品*/
	@Excel(name = "是否赠品", width = 15)
    @ApiModelProperty(value = "是否赠品")
	private java.lang.Integer isgift;
	/**是否液体1-是 2否*/
	@Excel(name = "是否液体1-是 2否", width = 15)
    @ApiModelProperty(value = "是否液体1-是 2否")
	private java.lang.String noliquidcosmetic;
	/**库存状态，默认1*/
	@Excel(name = "库存状态，默认1", width = 15)
    @ApiModelProperty(value = "库存状态，默认1")
	private java.lang.String stockstatus;
	/**父目录ID*/
	@Excel(name = "父目录ID", width = 15)
    @ApiModelProperty(value = "父目录ID")
	private java.lang.Integer parentcategoryid;
	/**供应商ID*/
	@Excel(name = "供应商ID", width = 15)
    @ApiModelProperty(value = "供应商ID")
	private java.lang.Integer defaultretailid;
	/**扩展字段*/
	@Excel(name = "扩展字段", width = 15)
    @ApiModelProperty(value = "扩展字段")
	private java.lang.String extend1;
	/**赠品成本*/
	@Excel(name = "赠品成本", width = 15)
    @ApiModelProperty(value = "赠品成本")
	private java.math.BigDecimal giftwrapprice;
	/**原始赠品费用*/
	@Excel(name = "原始赠品费用", width = 15)
    @ApiModelProperty(value = "原始赠品费用")
	private java.math.BigDecimal giftwrappriceorigin;
	/**是否锁定库存:0未锁定,1锁定*/
	@Excel(name = "是否锁定库存:0未锁定,1锁定", width = 15)
    @ApiModelProperty(value = "是否锁定库存:0未锁定,1锁定")
	private java.lang.Integer lockstock;
	/**商品的长、宽、高，格式：长x宽x高*/
	@Excel(name = "商品的长、宽、高，格式：长x宽x高", width = 15)
    @ApiModelProperty(value = "商品的长、宽、高，格式：长x宽x高")
	private java.lang.String stocklwh;
	/**SKU名称*/
	@Excel(name = "SKU名称", width = 15)
    @ApiModelProperty(value = "SKU名称")
	private java.lang.String skuname;
	/**仓库编码*/
	@Excel(name = "仓库编码", width = 15)
    @ApiModelProperty(value = "仓库编码")
	private java.lang.String warehousecode;
	/**商品ID*/
	@Excel(name = "商品ID", width = 15)
    @ApiModelProperty(value = "商品ID")
	private java.lang.Integer productid;
	/**临时产品SKU标签名称*/
	@Excel(name = "临时产品SKU标签名称", width = 15)
    @ApiModelProperty(value = "临时产品SKU标签名称")
	private java.lang.String temporaryproducttag;
	/**成品SKU标签名称*/
	@Excel(name = "成品SKU标签名称", width = 15)
    @ApiModelProperty(value = "成品SKU标签名称")
	private java.lang.String producttag;
	/**高度 单位mm*/
	@Excel(name = "高度 单位mm", width = 15)
    @ApiModelProperty(value = "高度 单位mm")
	private java.lang.Integer height;
	/**宽度 单位mm*/
	@Excel(name = "宽度 单位mm", width = 15)
    @ApiModelProperty(value = "宽度 单位mm")
	private java.lang.Integer width;
	/**长度 单位mm*/
	@Excel(name = "长度 单位mm", width = 15)
    @ApiModelProperty(value = "长度 单位mm")
	private java.lang.Integer skulength;
	/**广告链接*/
	@Excel(name = "广告链接", width = 15)
    @ApiModelProperty(value = "广告链接")
	private java.lang.String itemurl;
	/**物流标签 1,2*/
	@Excel(name = "物流标签 1,2", width = 15)
    @ApiModelProperty(value = "物流标签 1,2")
	private java.lang.String logistictag;
	/**物流等级*/
	@Excel(name = "物流等级", width = 15)
    @ApiModelProperty(value = "物流等级")
	private java.lang.String logisticslevel;

	private String declareCnName;
	private String declareEnName;
	private double declareValue;
	private double testWeight;
}
