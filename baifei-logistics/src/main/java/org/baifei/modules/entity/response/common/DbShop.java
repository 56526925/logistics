package org.baifei.modules.entity.response.common;

import java.util.Date;

/**
 *
 * //@Description:  mabang.db_shop
 * //@Author:       baihailiang
 * //@CreateDate:   2020-06-05 14:49:03
 * //@Version:      V1.0
 *
 */
//@ApiModel(value="DbShop",description="")
public class DbShop    {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	//@ApiModelProperty(name = "companyId" , value = "Company ID")
	//@Column(name="companyId")
	//@ColumnCondition(columnName="companyId",queryType=QueryType.EQUAL)
	private Integer companyId;
	//@ApiModelProperty(name = "developAccountId" , value = "开发者账号ID")
	//@Column(name="developAccountId")
	//@ColumnCondition(columnName="developAccountId",queryType=QueryType.EQUAL)
	private Integer developAccountId;
	//@ApiModelProperty(name = "name" , value = "店铺别名")
	//@Column(name="name")
	//@ColumnCondition(columnName="name",queryType=QueryType.EQUAL)
	private String name;
	//@ApiModelProperty(name = "accountUsername" , value = "平台账号")
	//@Column(name="accountUsername")
	//@ColumnCondition(columnName="accountUsername",queryType=QueryType.EQUAL)
	private String accountUsername;
	//@ApiModelProperty(name = "accountStoreName" , value = "平台店铺名")
	//@Column(name="accountStoreName")
	//@ColumnCondition(columnName="accountStoreName",queryType=QueryType.EQUAL)
	private String accountStoreName;
	//@ApiModelProperty(name = "parentAccount" , value = "父店铺账号名")
	//@Column(name="parentAccount")
	//@ColumnCondition(columnName="parentAccount",queryType=QueryType.EQUAL)
	private String parentAccount;
	//@ApiModelProperty(name = "remark" , value = "备注信息")
	//@Column(name="remark")
	//@ColumnCondition(columnName="remark",queryType=QueryType.EQUAL)
	private String remark;
	//@ApiModelProperty(name = "platformid" , value = "平台ID1:ebay,2:amazon,3:aliexpress ....")
	//@Column(name="platformid")
	//@ColumnCondition(columnName="platformid",queryType=QueryType.EQUAL)
	private Integer platformid;
	//@ApiModelProperty(name = "tokenid" , value = "店铺令牌  ")
	//@Column(name="tokenid")
	//@ColumnCondition(columnName="tokenid",queryType=QueryType.EQUAL)
	private String tokenid;
	//@ApiModelProperty(name = "tokenflag" , value = "授权标志0未授权 1成功  2授权失败")
	//@Column(name="tokenflag")
	//@ColumnCondition(columnName="tokenflag",queryType=QueryType.EQUAL)
	private Integer tokenflag;
	//@ApiModelProperty(name = "tokendescr" , value = "授权失败描述")
	//@Column(name="tokendescr")
	//@ColumnCondition(columnName="tokendescr",queryType=QueryType.EQUAL)
	private String tokendescr;
	//@ApiModelProperty(name = "paypalVisibleAccount" , value = "paypal大金额帐号")
	//@Column(name="paypalVisibleAccount")
	//@ColumnCondition(columnName="paypalVisibleAccount",queryType=QueryType.EQUAL)
	private String paypalVisibleAccount;
	//@ApiModelProperty(name = "riskflag" , value = "速卖通风控标志：1：是风控，2：非风控")
	//@Column(name="riskflag")
	//@ColumnCondition(columnName="riskflag",queryType=QueryType.EQUAL)
	private Integer riskflag;
	//@ApiModelProperty(name = "accesskeyid" , value = "亚马逊账号id ")
	//@Column(name="accesskeyid")
	//@ColumnCondition(columnName="accesskeyid",queryType=QueryType.EQUAL)
	private String accesskeyid;
	//@ApiModelProperty(name = "secretkey" , value = "亚马逊密钥")
	//@Column(name="secretkey")
	//@ColumnCondition(columnName="secretkey",queryType=QueryType.EQUAL)
	private String secretkey;
	//@ApiModelProperty(name = "merchantid" , value = "亚马逊商户id")
	//@Column(name="merchantid")
	//@ColumnCondition(columnName="merchantid",queryType=QueryType.EQUAL)
	private String merchantid;
	//@ApiModelProperty(name = "amazonsite" , value = "亚马逊站点")
	//@Column(name="amazonsite")
	//@ColumnCondition(columnName="amazonsite",queryType=QueryType.EQUAL)
	private String amazonsite;
	//@ApiModelProperty(name = "marketplaceid" , value = "亚马逊市场id")
	//@Column(name="marketplaceid")
	//@ColumnCondition(columnName="marketplaceid",queryType=QueryType.EQUAL)
	private String marketplaceid;
	//@ApiModelProperty(name = "amazonurl" , value = "亚马逊接口地址")
	//@Column(name="amazonurl")
	//@ColumnCondition(columnName="amazonurl",queryType=QueryType.EQUAL)
	private String amazonurl;
	//@ApiModelProperty(name = "fbaflag" , value = "亚马逊FBA类型：1.FBA 2.普通店铺")
	//@Column(name="fbaflag")
	//@ColumnCondition(columnName="fbaflag",queryType=QueryType.EQUAL)
	private Integer fbaflag;
	//@ApiModelProperty(name = "lastordertime" , value = "最后一次订单下载时间")
	//@Column(name="lastordertime")
	//@ColumnCondition(columnName="lastordertime",queryType=QueryType.EQUAL)
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date lastordertime;
	//@ApiModelProperty(name = "lastmessagetime" , value = "最后一次消息下载时间")
	//@Column(name="lastmessagetime")
	//@ColumnCondition(columnName="lastmessagetime",queryType=QueryType.EQUAL)
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date lastmessagetime;
	//@ApiModelProperty(name = "producttime" , value = "")
	//@Column(name="producttime")
	//@ColumnCondition(columnName="producttime",queryType=QueryType.EQUAL)
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date producttime;
	//@ApiModelProperty(name = "downloaddiff" , value = "下载时间间隔(单位分钟) ")
	//@Column(name="downloaddiff")
	//@ColumnCondition(columnName="downloaddiff",queryType=QueryType.EQUAL)
	private Integer downloaddiff;
	//@ApiModelProperty(name = "status" , value = "状态：1.启用2.停用")
	//@Column(name="status")
	//@ColumnCondition(columnName="status",queryType=QueryType.EQUAL)
	private Integer status;
	//@ApiModelProperty(name = "createOperator" , value = "")
	//@Column(name="createOperator")
	//@ColumnCondition(columnName="createOperator",queryType=QueryType.EQUAL)
	private String createOperator;
	//@ApiModelProperty(name = "modifyOperator" , value = "")
	//@Column(name="modifyOperator")
	//@ColumnCondition(columnName="modifyOperator",queryType=QueryType.EQUAL)
	private String modifyOperator;
	//@ApiModelProperty(name = "isUnifiedReply" , value = "是否统一回复")
	//@Column(name="isUnifiedReply")
	//@ColumnCondition(columnName="isUnifiedReply",queryType=QueryType.EQUAL)
	private Integer isUnifiedReply;
	//@ApiModelProperty(name = "replyNoComment" , value = "默认评价")
	//@Column(name="replyNoComment")
	//@ColumnCondition(columnName="replyNoComment",queryType=QueryType.EQUAL)
	private String replyNoComment;
	//@ApiModelProperty(name = "replyPositiveComment" , value = "回复好评的内容")
	//@Column(name="replyPositiveComment")
	//@ColumnCondition(columnName="replyPositiveComment",queryType=QueryType.EQUAL)
	private String replyPositiveComment;
	//@ApiModelProperty(name = "replyNeutralComment" , value = "回复中评的内容")
	//@Column(name="replyNeutralComment")
	//@ColumnCondition(columnName="replyNeutralComment",queryType=QueryType.EQUAL)
	private String replyNeutralComment;
	//@ApiModelProperty(name = "replyNegativeComment" , value = "回复差评的内容")
	//@Column(name="replyNegativeComment")
	//@ColumnCondition(columnName="replyNegativeComment",queryType=QueryType.EQUAL)
	private String replyNegativeComment;
	//@ApiModelProperty(name = "timeCreated" , value = "创建时间")
	//@Column(name="timeCreated")
	//@ColumnCondition(columnName="timeCreated",queryType=QueryType.EQUAL)
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date timeCreated;
	//@ApiModelProperty(name = "timeLastModified" , value = "")
	//@Column(name="timeLastModified")
	//@ColumnCondition(columnName="timeLastModified",queryType=QueryType.EQUAL)
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date timeLastModified;
	//@ApiModelProperty(name = "nopayflag" , value = "")
	//@Column(name="nopayflag")
	//@ColumnCondition(columnName="nopayflag",queryType=QueryType.EQUAL)
	private Integer nopayflag;
	//@ApiModelProperty(name = "returnAddress1" , value = "回邮地址")
	//@Column(name="returnAddress1")
	//@ColumnCondition(columnName="returnAddress1",queryType=QueryType.EQUAL)
	private Integer returnAddress1;
	//@ApiModelProperty(name = "returnAddress2" , value = "揽收地址")
	//@Column(name="returnAddress2")
	//@ColumnCondition(columnName="returnAddress2",queryType=QueryType.EQUAL)
	private Integer returnAddress2;
	//@ApiModelProperty(name = "hasAccount2" , value = "是否有小账号,1有2无")
	//@Column(name="hasAccount2")
	//@ColumnCondition(columnName="hasAccount2",queryType=QueryType.EQUAL)
	private Integer hasAccount2;
	//@ApiModelProperty(name = "paypalVisibleAccount2" , value = "paypal小金额帐号")
	//@Column(name="paypalVisibleAccount2")
	//@ColumnCondition(columnName="paypalVisibleAccount2",queryType=QueryType.EQUAL)
	private String paypalVisibleAccount2;
	//@ApiModelProperty(name = "currencyJson" , value = "保存付款账号临界值,支持多币种,格式:[{}]")
	//@Column(name="currencyJson")
	//@ColumnCondition(columnName="currencyJson",queryType=QueryType.EQUAL)
	private String currencyJson;
	//@ApiModelProperty(name = "descr" , value = "备注描述")
	//@Column(name="descr")
	//@ColumnCondition(columnName="descr",queryType=QueryType.EQUAL)
	private String descr;
	//@ApiModelProperty(name = "nickName" , value = "店铺昵称(从消息中抓取分析)")
	//@Column(name="nickName")
	//@ColumnCondition(columnName="nickName",queryType=QueryType.EQUAL)
	private String nickName;
	//@ApiModelProperty(name = "amazonEmailEnabled" , value = "是否启用亚马逊邮箱(1为启用,2为不启用)")
	//@Column(name="amazonEmailEnabled")
	//@ColumnCondition(columnName="amazonEmailEnabled",queryType=QueryType.EQUAL)
	private Integer amazonEmailEnabled;
	//@ApiModelProperty(name = "amazonEmailJson" , value = "亚马逊邮件参数【type,account,password】")
	//@Column(name="amazonEmailJson")
	//@ColumnCondition(columnName="amazonEmailJson",queryType=QueryType.EQUAL)
	private String amazonEmailJson;
	//@ApiModelProperty(name = "lastAccessTokenTime" , value = "短令牌过期时间")
	//@Column(name="lastAccessTokenTime")
	//@ColumnCondition(columnName="lastAccessTokenTime",queryType=QueryType.EQUAL)
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date lastAccessTokenTime;
	//@ApiModelProperty(name = "lastShipedOrderTime" , value = "最后一次下载已发货订单时间")
	//@Column(name="lastShipedOrderTime")
	//@ColumnCondition(columnName="lastShipedOrderTime",queryType=QueryType.EQUAL)
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date lastShipedOrderTime;
	//@ApiModelProperty(name = "lastCaseTime" , value = "最后下载纠纷时间")
	//@Column(name="lastCaseTime")
	//@ColumnCondition(columnName="lastCaseTime",queryType=QueryType.EQUAL)
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date lastCaseTime;
	//@ApiModelProperty(name = "downloadProductFlag" , value = "是否下过在线商品,0未下载1已下载")
	//@Column(name="downloadProductFlag")
	//@ColumnCondition(columnName="downloadProductFlag",queryType=QueryType.EQUAL)
	private Integer downloadProductFlag;
	//@ApiModelProperty(name = "accessTokenTime" , value = "短令牌获取时间")
	//@Column(name="accessTokenTime")
	//@ColumnCondition(columnName="accessTokenTime",queryType=QueryType.EQUAL)
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date accessTokenTime;
	//@ApiModelProperty(name = "exceptionOrderTime" , value = "")
	//@Column(name="exceptionOrderTime")
	//@ColumnCondition(columnName="exceptionOrderTime",queryType=QueryType.EQUAL)
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date exceptionOrderTime;
	//@ApiModelProperty(name = "fbaWarehouseId" , value = "当fbaflag=1时存fba仓库编号")
	//@Column(name="fbaWarehouseId")
	//@ColumnCondition(columnName="fbaWarehouseId",queryType=QueryType.EQUAL)
	private Integer fbaWarehouseId;
	//@ApiModelProperty(name = "refreshTokenTime" , value = "长令牌获取时间")
	//@Column(name="refreshTokenTime")
	//@ColumnCondition(columnName="refreshTokenTime",queryType=QueryType.EQUAL)
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date refreshTokenTime;
	//@ApiModelProperty(name = "senderAddressId" , value = "速卖通线上发货地址ID")
	//@Column(name="senderAddressId")
	//@ColumnCondition(columnName="senderAddressId",queryType=QueryType.EQUAL)
	private String senderAddressId;
	//@ApiModelProperty(name = "pickupAddressId" , value = "速卖通线上捡货地址ID")
	//@Column(name="pickupAddressId")
	//@ColumnCondition(columnName="pickupAddressId",queryType=QueryType.EQUAL)
	private String pickupAddressId;
	//@ApiModelProperty(name = "shippingType" , value = "运输方式 1:海运 2：空运")
	//@Column(name="shippingType")
	//@ColumnCondition(columnName="shippingType",queryType=QueryType.EQUAL)
	private Integer shippingType;
	//@ApiModelProperty(name = "homeDays" , value = "国内天数")
	//@Column(name="homeDays")
	//@ColumnCondition(columnName="homeDays",queryType=QueryType.EQUAL)
	private Integer homeDays;
	//@ApiModelProperty(name = "onWayDays" , value = "头程天数")
	//@Column(name="onWayDays")
	//@ColumnCondition(columnName="onWayDays",queryType=QueryType.EQUAL)
	private Integer onWayDays;
	//@ApiModelProperty(name = "inHouseDays" , value = "入仓天数")
	//@Column(name="inHouseDays")
	//@ColumnCondition(columnName="inHouseDays",queryType=QueryType.EQUAL)
	private Integer inHouseDays;
	//@ApiModelProperty(name = "mulSite" , value = "亚马逊店铺对应站点")
	//@Column(name="mulSite")
	//@ColumnCondition(columnName="mulSite",queryType=QueryType.EQUAL)
	private String mulSite;
	//@ApiModelProperty(name = "platformRate" , value = "平台费率")
	//@Column(name="platformRate")
	//@ColumnCondition(columnName="platformRate",queryType=QueryType.EQUAL)
	private Double platformRate;
	//@ApiModelProperty(name = "transferRate" , value = "转账费")
	//@Column(name="transferRate")
	//@ColumnCondition(columnName="transferRate",queryType=QueryType.EQUAL)
	private Double transferRate;
	//@ApiModelProperty(name = "riskTime" , value = "速卖通风控下载时间")
	//@Column(name="riskTime")
	//@ColumnCondition(columnName="riskTime",queryType=QueryType.EQUAL)
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date riskTime;
	//@ApiModelProperty(name = "priceUpdatedTime" , value = "在线商品价格下载时间")
	//@Column(name="priceUpdatedTime")
	//@ColumnCondition(columnName="priceUpdatedTime",queryType=QueryType.EQUAL)
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date priceUpdatedTime;
	//@ApiModelProperty(name = "warehouseId" , value = "指定仓库编号,0表示没有")
	//@Column(name="warehouseId")
	//@ColumnCondition(columnName="warehouseId",queryType=QueryType.EQUAL)
	private Integer warehouseId;
	//@ApiModelProperty(name = "tWarehouseId" , value = "fba中转仓id")
	//@Column(name="tWarehouseId")
	//@ColumnCondition(columnName="tWarehouseId",queryType=QueryType.EQUAL)
	private Integer tWarehouseId;
	//@ApiModelProperty(name = "FBAPendingOrder" , value = "下载亚马逊FBA的pending订单:1下载;2:不下载")
	//@Column(name="FBAPendingOrder")
	//@ColumnCondition(columnName="FBAPendingOrder",queryType=QueryType.EQUAL)
	private Integer FBAPendingOrder;
	//@ApiModelProperty(name = "cainiaoToken" , value = "菜鸟Token")
	//@Column(name="cainiaoToken")
	//@ColumnCondition(columnName="cainiaoToken",queryType=QueryType.EQUAL)
	private String cainiaoToken;
	//@ApiModelProperty(name = "cainiaoUId" , value = "菜鸟客户编号")
	//@Column(name="cainiaoUId")
	//@ColumnCondition(columnName="cainiaoUId",queryType=QueryType.EQUAL)
	private String cainiaoUId;
	//@ApiModelProperty(name = "cainiaoCdate" , value = "菜鸟授权时间")
	//@Column(name="cainiaoCdate")
	//@ColumnCondition(columnName="cainiaoCdate",queryType=QueryType.EQUAL)
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date cainiaoCdate;
	//@ApiModelProperty(name = "lockOrder" , value = "0未锁定下载,1锁定下载")
	//@Column(name="lockOrder")
	//@ColumnCondition(columnName="lockOrder",queryType=QueryType.EQUAL)
	private Integer lockOrder;
	//@ApiModelProperty(name = "orderApiTime" , value = "最后一次调接口时间")
	//@Column(name="orderApiTime")
	//@ColumnCondition(columnName="orderApiTime",queryType=QueryType.EQUAL)
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date orderApiTime;
	//@ApiModelProperty(name = "extend1" , value = "扩展字段1: 1.速卖通奇门店铺")
	//@Column(name="extend1")
	//@ColumnCondition(columnName="extend1",queryType=QueryType.EQUAL)
	private String extend1;
	//@ApiModelProperty(name = "extend2" , value = "扩展字段2:奇门店铺令牌")
	//@Column(name="extend2")
	//@ColumnCondition(columnName="extend2",queryType=QueryType.EQUAL)
	private String extend2;
	//@ApiModelProperty(name = "shopCode" , value = "企业自定义的店铺编号")
	//@Column(name="shopCode")
	//@ColumnCondition(columnName="shopCode",queryType=QueryType.EQUAL)
	private String shopCode;
	//@ApiModelProperty(name = "source" , value = "店铺资源:1,2,3,4,5,6,7")
	//@Column(name="source")
	//@ColumnCondition(columnName="source",queryType=QueryType.EQUAL)
	private String source;
	//@ApiModelProperty(name = "lastRefundTime" , value = "上一次拉取退款订单时间")
	//@Column(name="lastRefundTime")
	//@ColumnCondition(columnName="lastRefundTime",queryType=QueryType.EQUAL)
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date lastRefundTime;
	//@ApiModelProperty(name = "lockRefundOrder" , value = "0未锁定下载,1锁定下载(退款)")
	//@Column(name="lockRefundOrder")
	//@ColumnCondition(columnName="lockRefundOrder",queryType=QueryType.EQUAL)
	private String lockRefundOrder;
	//@ApiModelProperty(name = "lastTokenTime" , value = "长令牌过期时间")
	//@Column(name="lastTokenTime")
	//@ColumnCondition(columnName="lastTokenTime",queryType=QueryType.EQUAL)
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date lastTokenTime;
	//@ApiModelProperty(name = "lastissuetime" , value = "最后拉取纠纷时间")
	//@Column(name="lastissuetime")
	//@ColumnCondition(columnName="lastissuetime",queryType=QueryType.EQUAL)
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date lastissuetime;
	//@ApiModelProperty(name = "mwsAuthToken" , value = "亚马逊店铺密钥")
	//@Column(name="mwsAuthToken")
	//@ColumnCondition(columnName="mwsAuthToken",queryType=QueryType.EQUAL)
	private String mwsAuthToken;
	//@ApiModelProperty(name = "privateType" , value = "店铺类别 暂时不用")
	//@Column(name="privateType")
	//@ColumnCondition(columnName="privateType",queryType=QueryType.EQUAL)
	private Integer privateType;
	//@ApiModelProperty(name = "unpaidFlag" , value = "是否下载未付款订单 1 下载 2不下载 3.下载海外仓订单 fbw")
	//@Column(name="unpaidFlag")
	//@ColumnCondition(columnName="unpaidFlag",queryType=QueryType.EQUAL)
	private Integer unpaidFlag;
	//@ApiModelProperty(name = "lockUnpaidOrder" , value = "下载未付款订单锁定状态 0未锁定下载,1锁定下载")
	//@Column(name="lockUnpaidOrder")
	//@ColumnCondition(columnName="lockUnpaidOrder",queryType=QueryType.EQUAL)
	private Integer lockUnpaidOrder;
	//@ApiModelProperty(name = "lastUnpaidTime" , value = "最后下载未付款订单时间")
	//@Column(name="lastUnpaidTime")
	//@ColumnCondition(columnName="lastUnpaidTime",queryType=QueryType.EQUAL)
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date lastUnpaidTime;
	//@ApiModelProperty(name = "vatRate" , value = "")
	//@Column(name="vatRate")
	//@ColumnCondition(columnName="vatRate",queryType=QueryType.EQUAL)
	private Double vatRate;
	//@ApiModelProperty(name = "vatType" , value = "")
	//@Column(name="vatType")
	//@ColumnCondition(columnName="vatType",queryType=QueryType.EQUAL)
	private String vatType;
	//@ApiModelProperty(name = "loanTime" , value = "上次获取海外仓订单时间")
	//@Column(name="loanTime")
	//@ColumnCondition(columnName="loanTime",queryType=QueryType.EQUAL)
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date loanTime;

	//@Transient
	private String tableName;
	//@Override
	public String getDynamicTableName(){return tableName;}
	public String getTableName(){return tableName;}
	public void setTableName(String tableName){this.tableName = tableName;}

	public Integer getCompanyId(){return companyId;}
	public void setCompanyId(Integer companyId){this.companyId = companyId;}
	public Integer getDevelopAccountId(){return developAccountId;}
	public void setDevelopAccountId(Integer developAccountId){this.developAccountId = developAccountId;}
	public String getName(){return name;}
	public void setName(String name){this.name = name;}
	public String getAccountUsername(){return accountUsername;}
	public void setAccountUsername(String accountUsername){this.accountUsername = accountUsername;}
	public String getAccountStoreName(){return accountStoreName;}
	public void setAccountStoreName(String accountStoreName){this.accountStoreName = accountStoreName;}
	public String getParentAccount(){return parentAccount;}
	public void setParentAccount(String parentAccount){this.parentAccount = parentAccount;}
	public String getRemark(){return remark;}
	public void setRemark(String remark){this.remark = remark;}
	public Integer getPlatformid(){return platformid;}
	public void setPlatformid(Integer platformid){this.platformid = platformid;}
	public String getTokenid(){return tokenid;}
	public void setTokenid(String tokenid){this.tokenid = tokenid;}
	public Integer getTokenflag(){return tokenflag;}
	public void setTokenflag(Integer tokenflag){this.tokenflag = tokenflag;}
	public String getTokendescr(){return tokendescr;}
	public void setTokendescr(String tokendescr){this.tokendescr = tokendescr;}
	public String getPaypalVisibleAccount(){return paypalVisibleAccount;}
	public void setPaypalVisibleAccount(String paypalVisibleAccount){this.paypalVisibleAccount = paypalVisibleAccount;}
	public Integer getRiskflag(){return riskflag;}
	public void setRiskflag(Integer riskflag){this.riskflag = riskflag;}
	public String getAccesskeyid(){return accesskeyid;}
	public void setAccesskeyid(String accesskeyid){this.accesskeyid = accesskeyid;}
	public String getSecretkey(){return secretkey;}
	public void setSecretkey(String secretkey){this.secretkey = secretkey;}
	public String getMerchantid(){return merchantid;}
	public void setMerchantid(String merchantid){this.merchantid = merchantid;}
	public String getAmazonsite(){return amazonsite;}
	public void setAmazonsite(String amazonsite){this.amazonsite = amazonsite;}
	public String getMarketplaceid(){return marketplaceid;}
	public void setMarketplaceid(String marketplaceid){this.marketplaceid = marketplaceid;}
	public String getAmazonurl(){return amazonurl;}
	public void setAmazonurl(String amazonurl){this.amazonurl = amazonurl;}
	public Integer getFbaflag(){return fbaflag;}
	public void setFbaflag(Integer fbaflag){this.fbaflag = fbaflag;}
	public Date getLastordertime(){return lastordertime;}
	public void setLastordertime(Date lastordertime){this.lastordertime = lastordertime;}
	public Date getLastmessagetime(){return lastmessagetime;}
	public void setLastmessagetime(Date lastmessagetime){this.lastmessagetime = lastmessagetime;}
	public Date getProducttime(){return producttime;}
	public void setProducttime(Date producttime){this.producttime = producttime;}
	public Integer getDownloaddiff(){return downloaddiff;}
	public void setDownloaddiff(Integer downloaddiff){this.downloaddiff = downloaddiff;}
	public Integer getStatus(){return status;}
	public void setStatus(Integer status){this.status = status;}
	public String getCreateOperator(){return createOperator;}
	public void setCreateOperator(String createOperator){this.createOperator = createOperator;}
	public String getModifyOperator(){return modifyOperator;}
	public void setModifyOperator(String modifyOperator){this.modifyOperator = modifyOperator;}
	public Integer getIsUnifiedReply(){return isUnifiedReply;}
	public void setIsUnifiedReply(Integer isUnifiedReply){this.isUnifiedReply = isUnifiedReply;}
	public String getReplyNoComment(){return replyNoComment;}
	public void setReplyNoComment(String replyNoComment){this.replyNoComment = replyNoComment;}
	public String getReplyPositiveComment(){return replyPositiveComment;}
	public void setReplyPositiveComment(String replyPositiveComment){this.replyPositiveComment = replyPositiveComment;}
	public String getReplyNeutralComment(){return replyNeutralComment;}
	public void setReplyNeutralComment(String replyNeutralComment){this.replyNeutralComment = replyNeutralComment;}
	public String getReplyNegativeComment(){return replyNegativeComment;}
	public void setReplyNegativeComment(String replyNegativeComment){this.replyNegativeComment = replyNegativeComment;}
	public Date getTimeCreated(){return timeCreated;}
	public void setTimeCreated(Date timeCreated){this.timeCreated = timeCreated;}
	public Date getTimeLastModified(){return timeLastModified;}
	public void setTimeLastModified(Date timeLastModified){this.timeLastModified = timeLastModified;}
	public Integer getNopayflag(){return nopayflag;}
	public void setNopayflag(Integer nopayflag){this.nopayflag = nopayflag;}
	public Integer getReturnAddress1(){return returnAddress1;}
	public void setReturnAddress1(Integer returnAddress1){this.returnAddress1 = returnAddress1;}
	public Integer getReturnAddress2(){return returnAddress2;}
	public void setReturnAddress2(Integer returnAddress2){this.returnAddress2 = returnAddress2;}
	public Integer getHasAccount2(){return hasAccount2;}
	public void setHasAccount2(Integer hasAccount2){this.hasAccount2 = hasAccount2;}
	public String getPaypalVisibleAccount2(){return paypalVisibleAccount2;}
	public void setPaypalVisibleAccount2(String paypalVisibleAccount2){this.paypalVisibleAccount2 = paypalVisibleAccount2;}
	public String getCurrencyJson(){return currencyJson;}
	public void setCurrencyJson(String currencyJson){this.currencyJson = currencyJson;}
	public String getDescr(){return descr;}
	public void setDescr(String descr){this.descr = descr;}
	public String getNickName(){return nickName;}
	public void setNickName(String nickName){this.nickName = nickName;}
	public Integer getAmazonEmailEnabled(){return amazonEmailEnabled;}
	public void setAmazonEmailEnabled(Integer amazonEmailEnabled){this.amazonEmailEnabled = amazonEmailEnabled;}
	public String getAmazonEmailJson(){return amazonEmailJson;}
	public void setAmazonEmailJson(String amazonEmailJson){this.amazonEmailJson = amazonEmailJson;}
	public Date getLastAccessTokenTime(){return lastAccessTokenTime;}
	public void setLastAccessTokenTime(Date lastAccessTokenTime){this.lastAccessTokenTime = lastAccessTokenTime;}
	public Date getLastShipedOrderTime(){return lastShipedOrderTime;}
	public void setLastShipedOrderTime(Date lastShipedOrderTime){this.lastShipedOrderTime = lastShipedOrderTime;}
	public Date getLastCaseTime(){return lastCaseTime;}
	public void setLastCaseTime(Date lastCaseTime){this.lastCaseTime = lastCaseTime;}
	public Integer getDownloadProductFlag(){return downloadProductFlag;}
	public void setDownloadProductFlag(Integer downloadProductFlag){this.downloadProductFlag = downloadProductFlag;}
	public Date getAccessTokenTime(){return accessTokenTime;}
	public void setAccessTokenTime(Date accessTokenTime){this.accessTokenTime = accessTokenTime;}
	public Date getExceptionOrderTime(){return exceptionOrderTime;}
	public void setExceptionOrderTime(Date exceptionOrderTime){this.exceptionOrderTime = exceptionOrderTime;}
	public Integer getFbaWarehouseId(){return fbaWarehouseId;}
	public void setFbaWarehouseId(Integer fbaWarehouseId){this.fbaWarehouseId = fbaWarehouseId;}
	public Date getRefreshTokenTime(){return refreshTokenTime;}
	public void setRefreshTokenTime(Date refreshTokenTime){this.refreshTokenTime = refreshTokenTime;}
	public String getSenderAddressId(){return senderAddressId;}
	public void setSenderAddressId(String senderAddressId){this.senderAddressId = senderAddressId;}
	public String getPickupAddressId(){return pickupAddressId;}
	public void setPickupAddressId(String pickupAddressId){this.pickupAddressId = pickupAddressId;}
	public Integer getShippingType(){return shippingType;}
	public void setShippingType(Integer shippingType){this.shippingType = shippingType;}
	public Integer getHomeDays(){return homeDays;}
	public void setHomeDays(Integer homeDays){this.homeDays = homeDays;}
	public Integer getOnWayDays(){return onWayDays;}
	public void setOnWayDays(Integer onWayDays){this.onWayDays = onWayDays;}
	public Integer getInHouseDays(){return inHouseDays;}
	public void setInHouseDays(Integer inHouseDays){this.inHouseDays = inHouseDays;}
	public String getMulSite(){return mulSite;}
	public void setMulSite(String mulSite){this.mulSite = mulSite;}
	public Double getPlatformRate(){return platformRate;}
	public void setPlatformRate(Double platformRate){this.platformRate = platformRate;}
	public Double getTransferRate(){return transferRate;}
	public void setTransferRate(Double transferRate){this.transferRate = transferRate;}
	public Date getRiskTime(){return riskTime;}
	public void setRiskTime(Date riskTime){this.riskTime = riskTime;}
	public Date getPriceUpdatedTime(){return priceUpdatedTime;}
	public void setPriceUpdatedTime(Date priceUpdatedTime){this.priceUpdatedTime = priceUpdatedTime;}
	public Integer getWarehouseId(){return warehouseId;}
	public void setWarehouseId(Integer warehouseId){this.warehouseId = warehouseId;}
	public Integer getTWarehouseId(){return tWarehouseId;}
	public void setTWarehouseId(Integer tWarehouseId){this.tWarehouseId = tWarehouseId;}
	public Integer getFBAPendingOrder(){return FBAPendingOrder;}
	public void setFBAPendingOrder(Integer FBAPendingOrder){this.FBAPendingOrder = FBAPendingOrder;}
	public String getCainiaoToken(){return cainiaoToken;}
	public void setCainiaoToken(String cainiaoToken){this.cainiaoToken = cainiaoToken;}
	public String getCainiaoUId(){return cainiaoUId;}
	public void setCainiaoUId(String cainiaoUId){this.cainiaoUId = cainiaoUId;}
	public Date getCainiaoCdate(){return cainiaoCdate;}
	public void setCainiaoCdate(Date cainiaoCdate){this.cainiaoCdate = cainiaoCdate;}
	public Integer getLockOrder(){return lockOrder;}
	public void setLockOrder(Integer lockOrder){this.lockOrder = lockOrder;}
	public Date getOrderApiTime(){return orderApiTime;}
	public void setOrderApiTime(Date orderApiTime){this.orderApiTime = orderApiTime;}
	public String getExtend1(){return extend1;}
	public void setExtend1(String extend1){this.extend1 = extend1;}
	public String getExtend2(){return extend2;}
	public void setExtend2(String extend2){this.extend2 = extend2;}
	public String getShopCode(){return shopCode;}
	public void setShopCode(String shopCode){this.shopCode = shopCode;}
	public String getSource(){return source;}
	public void setSource(String source){this.source = source;}
	public Date getLastRefundTime(){return lastRefundTime;}
	public void setLastRefundTime(Date lastRefundTime){this.lastRefundTime = lastRefundTime;}
	public String getLockRefundOrder(){return lockRefundOrder;}
	public void setLockRefundOrder(String lockRefundOrder){this.lockRefundOrder = lockRefundOrder;}
	public Date getLastTokenTime(){return lastTokenTime;}
	public void setLastTokenTime(Date lastTokenTime){this.lastTokenTime = lastTokenTime;}
	public Date getLastissuetime(){return lastissuetime;}
	public void setLastissuetime(Date lastissuetime){this.lastissuetime = lastissuetime;}
	public String getMwsAuthToken(){return mwsAuthToken;}
	public void setMwsAuthToken(String mwsAuthToken){this.mwsAuthToken = mwsAuthToken;}
	public Integer getPrivateType(){return privateType;}
	public void setPrivateType(Integer privateType){this.privateType = privateType;}
	public Integer getUnpaidFlag(){return unpaidFlag;}
	public void setUnpaidFlag(Integer unpaidFlag){this.unpaidFlag = unpaidFlag;}
	public Integer getLockUnpaidOrder(){return lockUnpaidOrder;}
	public void setLockUnpaidOrder(Integer lockUnpaidOrder){this.lockUnpaidOrder = lockUnpaidOrder;}
	public Date getLastUnpaidTime(){return lastUnpaidTime;}
	public void setLastUnpaidTime(Date lastUnpaidTime){this.lastUnpaidTime = lastUnpaidTime;}
	public Double getVatRate(){return vatRate;}
	public void setVatRate(Double vatRate){this.vatRate = vatRate;}
	public String getVatType(){return vatType;}
	public void setVatType(String vatType){this.vatType = vatType;}
	public Date getLoanTime(){return loanTime;}
	public void setLoanTime(Date loanTime){this.loanTime = loanTime;}
}