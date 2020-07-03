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
 * @Description: 物流交运
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
@Data
@TableName("db_needtotracknumber_101")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="db_needtotracknumber_101对象", description="物流交运")
public class Needtotracknumber101 {
    
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
	/**订单ID*/
	@Excel(name = "订单ID", width = 15)
    @ApiModelProperty(value = "订单ID")
	private java.lang.Integer orderid;
	/**平台订单编号*/
	@Excel(name = "平台订单编号", width = 15)
    @ApiModelProperty(value = "平台订单编号")
	private java.lang.String platformorderid;
	/**salesrecordnumber*/
	@Excel(name = "salesrecordnumber", width = 15)
    @ApiModelProperty(value = "salesrecordnumber")
	private java.lang.String salesrecordnumber;
	/**跟踪单号*/
	@Excel(name = "跟踪单号", width = 15)
	@ApiModelProperty(value = "跟踪单号")
	private java.lang.String tracknumber;
	/**跟踪单号*/
	@Excel(name = "跟踪单号", width = 15)
    @ApiModelProperty(value = "跟踪单号1")
	private java.lang.String tracknumber1;
	/**内部单号1*/
	@Excel(name = "内部单号1", width = 15)
    @ApiModelProperty(value = "内部单号2")
	private java.lang.String tracknumber2;
	/**内部单号2*/
	@Excel(name = "内部单号2", width = 15)
    @ApiModelProperty(value = "内部单号3")
	private java.lang.String tracknumber3;
	/**线上发货 单号（年月日）*/
	@Excel(name = "线上发货 单号（年月日）", width = 15)
    @ApiModelProperty(value = "线上发货 单号（年月日）")
	private java.lang.String tracknumber4;
	/**我的物流公司编号(base_logistics_channel表logisticsId)*/
	@Excel(name = "我的物流公司编号(base_logistics_channel表logisticsId)", width = 15)
    @ApiModelProperty(value = "我的物流公司编号(base_logistics_channel表logisticsId)")
	private java.lang.Integer mylogisticsid;
	/**物流公司编号(base_logistics_channel表logisticsId)*/
	@Excel(name = "物流公司编号(base_logistics_channel表logisticsId)", width = 15)
    @ApiModelProperty(value = "物流公司编号(base_logistics_channel表logisticsId)")
	private java.lang.Integer logisticsid;
	/**货运方式id(base_logistics_channel表ID)*/
	@Excel(name = "货运方式id(base_logistics_channel表ID)", width = 15)
    @ApiModelProperty(value = "货运方式id(base_logistics_channel表ID)")
	private java.lang.Integer mylogisticschannelid;
	/**物流渠道(base_logistics_channel_info表ID)*/
	@Excel(name = "物流渠道(base_logistics_channel_info表ID)", width = 15)
    @ApiModelProperty(value = "物流渠道(base_logistics_channel_info表ID)")
	private java.lang.Integer logisticschannelid;
	/**创建时间*/
	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
	private java.util.Date createtime;
	/**操作工号*/
	@Excel(name = "操作工号", width = 15)
    @ApiModelProperty(value = "操作工号")
	private java.lang.Integer operid;
	/**erp登陆员工姓名*/
	@Excel(name = "erp登陆员工姓名", width = 15)
    @ApiModelProperty(value = "erp登陆员工姓名")
	private java.lang.String oper;
	/**账号名称*/
	@Excel(name = "账号名称", width = 15)
    @ApiModelProperty(value = "账号名称")
	private java.lang.String accountname;
	/**0-未上传-(插入任务的初始状态) 
1-开始上传-(后台应用程序获取到该任务以后为了防止二次获取修改成1)
2-已交运(获取转单号成功)-(物流公司返回中间单号=>转单号或者物流公司的内部单号)
3-已交运(所有)-(成功返回可跟踪的物流单号)
4-已交运(获取真实单号中)
98-参数校验失败
99-api交运失败*/
//	@Excel(name = "0-未上传-(插入任务的初始状态)
//1-开始上传-(后台应用程序获取到该任务以后为了防止二次获取修改成1)
//2-已交运(获取转单号成功)-(物流公司返回中间单号=>转单号或者物流公司的内部单号)
//3-已交运(所有)-(成功返回可跟踪的物流单号)
//4-已交运(获取真实单号中)
//98-参数校验失败
//99-api交运失败", width = 15)
//    @ApiModelProperty(value = "0-未上传-(插入任务的初始状态)
//1-开始上传-(后台应用程序获取到该任务以后为了防止二次获取修改成1)
//2-已交运(获取转单号成功)-(物流公司返回中间单号=>转单号或者物流公司的内部单号)
//3-已交运(所有)-(成功返回可跟踪的物流单号)
//4-已交运(获取真实单号中)
//98-参数校验失败
//99-api交运失败")
	private java.lang.Integer errorflag;
	/**errordescr*/
	@Excel(name = "errordescr", width = 15)
    @ApiModelProperty(value = "errordescr")
	private java.lang.Object errordescr;
	/**apitime*/
	@Excel(name = "apitime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "apitime")
	private java.util.Date apitime;
	/**labelurl*/
	@Excel(name = "labelurl", width = 15)
    @ApiModelProperty(value = "labelurl")
	private java.lang.String labelurl;
	/**labeljson*/
	@Excel(name = "labeljson", width = 15)
    @ApiModelProperty(value = "labeljson")
	private java.lang.Object labeljson;
	/**requestjson*/
	@Excel(name = "requestjson", width = 15)
    @ApiModelProperty(value = "requestjson")
	private String requestjson;
	/**property1*/
	@Excel(name = "property1", width = 15)
    @ApiModelProperty(value = "property1")
	private java.lang.String property1;
	/**property2*/
	@Excel(name = "property2", width = 15)
    @ApiModelProperty(value = "property2")
	private java.lang.String property2;
	/**property3*/
	@Excel(name = "property3", width = 15)
    @ApiModelProperty(value = "property3")
	private java.lang.String property3;
	/**property4*/
	@Excel(name = "property4", width = 15)
    @ApiModelProperty(value = "property4")
	private java.lang.String property4;
	/**tryagain*/
	@Excel(name = "tryagain", width = 15)
    @ApiModelProperty(value = "tryagain")
	private java.lang.Integer tryagain;
	/**trytime*/
	@Excel(name = "trytime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "trytime")
	private java.util.Date trytime;
	/**订单付款时间,e邮宝交运用的着*/
	@Excel(name = "订单付款时间,e邮宝交运用的着", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "订单付款时间,e邮宝交运用的着")
	private java.util.Date paidtime;
	/**物流交运属性*/
	@Excel(name = "物流交运属性", width = 15)
    @ApiModelProperty(value = "物流交运属性")
	private java.lang.String propertyjson;
	/**申报价值(人民币)*/
	@Excel(name = "申报价值(人民币)", width = 15)
    @ApiModelProperty(value = "申报价值(人民币)")
	private java.math.BigDecimal declarefee;
	/**申报占比(和订单金额)*/
	@Excel(name = "申报占比(和订单金额)", width = 15)
    @ApiModelProperty(value = "申报占比(和订单金额)")
	private java.math.BigDecimal declarefeerate;
	/**云分销订单1：是；2：否*/
	@Excel(name = "云分销订单1：是；2：否", width = 15)
    @ApiModelProperty(value = "云分销订单1：是；2：否")
	private java.lang.Integer iscloud;
	/**0未调用 1 开始调用  2 正在调用  3 获取成功   98 获取失败   99 获取异常*/
	@Excel(name = "0未调用 1 开始调用  2 正在调用  3 获取成功   98 获取失败   99 获取异常", width = 15)
    @ApiModelProperty(value = "0未调用 1 开始调用  2 正在调用  3 获取成功   98 获取失败   99 获取异常")
	private java.lang.Integer islabel;
	/**下载描述*/
	@Excel(name = "下载描述", width = 15)
    @ApiModelProperty(value = "下载描述")
	private java.lang.String labeldescr;
	/**标签拉取时间*/
	@Excel(name = "标签拉取时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "标签拉取时间")
	private java.util.Date labletime;
	/**默认1手动交运,0自动匹配*/
	@Excel(name = "默认1手动交运,0自动匹配", width = 15)
    @ApiModelProperty(value = "默认1手动交运,0自动匹配")
	private java.lang.Integer flag;
}
