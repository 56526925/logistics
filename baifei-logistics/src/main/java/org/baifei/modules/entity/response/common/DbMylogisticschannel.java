package org.baifei.modules.entity.response.common;


import java.util.Date;


/**
 *
 * //@Description:  mabangorderbase.db_mylogisticschannel
 * //@Author:       baihailiang
 * //@CreateDate:   2020-03-11 18:07:08
 * //@Version:      V1.0
 *
 */
//货运方式
public class DbMylogisticschannel  {
	private  String id;
	//@ApiModelProperty(name = "name" , value = "渠道名称")
	//@Column(name="name")
	//@ColumnCondition(columnName="name",queryType=QueryType.EQUAL)
	private String name;
	//@ApiModelProperty(name = "companyId" , value = "公司编号")
	//@Column(name="companyId")
	//@ColumnCondition(columnName="companyId",queryType=QueryType.EQUAL)
	private Integer companyId;
	//@ApiModelProperty(name = "warehouseCode" , value = "海外仓仓库编码")
	//@Column(name="warehouseCode")
	//@ColumnCondition(columnName="warehouseCode",queryType=QueryType.EQUAL)
	private String warehouseCode;
	//@ApiModelProperty(name = "myLogisticsId" , value = "我的物流公司编号")
	//@Column(name="myLogisticsId")
	//@ColumnCondition(columnName="myLogisticsId",queryType=QueryType.EQUAL)
	private Integer myLogisticsId;
	//@ApiModelProperty(name = "logisticsId" , value = "物流公司编号,如果是自建的填写")
	//@Column(name="logisticsId")
	//@ColumnCondition(columnName="logisticsId",queryType=QueryType.EQUAL)
	private Integer logisticsId;
	//@ApiModelProperty(name = "logisticsName" , value = "物流公司名称")
	//@Column(name="logisticsName")
	//@ColumnCondition(columnName="logisticsName",queryType=QueryType.EQUAL)
	private String logisticsName;
	//@ApiModelProperty(name = "logisticsChannelId" , value = "渠道编号")
	//@Column(name="logisticsChannelId")
	//@ColumnCondition(columnName="logisticsChannelId",queryType=QueryType.EQUAL)
	private Integer logisticsChannelId;
	//@ApiModelProperty(name = "logisticsChannelName" , value = "物流渠道名称")
	//@Column(name="logisticsChannelName")
	//@ColumnCondition(columnName="logisticsChannelName",queryType=QueryType.EQUAL)
	private String logisticsChannelName;
	//@ApiModelProperty(name = "source" , value = "是否自创1.公用,2自建")
	//@Column(name="source")
	//@ColumnCondition(columnName="source",queryType=QueryType.EQUAL)
	private Integer source;
	//@ApiModelProperty(name = "tokenFlag" , value = "授权状态:1授权成功,2授权失败,0未授权,3无需授权")
	//@Column(name="tokenFlag")
	//@ColumnCondition(columnName="tokenFlag",queryType=QueryType.EQUAL)
	private Integer tokenFlag;
	//@ApiModelProperty(name = "openFlag" , value = "启动标志1启动2停用")
	//@Column(name="openFlag")
	//@ColumnCondition(columnName="openFlag",queryType=QueryType.EQUAL)
	private Integer openFlag;
	//@ApiModelProperty(name = "hideTracknumber" , value = "是否向平台隐藏跟踪信息:1隐藏2不隐藏")
	//@Column(name="hideTracknumber")
	//@ColumnCondition(columnName="hideTracknumber",queryType=QueryType.EQUAL)
	private Integer hideTracknumber;
	//@ApiModelProperty(name = "tracknumberLength" , value = "跟踪号位长,用于发货验证")
	//@Column(name="tracknumberLength")
	//@ColumnCondition(columnName="tracknumberLength",queryType=QueryType.EQUAL)
	private Integer tracknumberLength;
	//@ApiModelProperty(name = "sort" , value = "企业内部排序")
	//@Column(name="sort")
	//@ColumnCondition(columnName="sort",queryType=QueryType.EQUAL)
	private Integer sort;
	//@ApiModelProperty(name = "isCopy" , value = "是否Copy渠道:1是2不是")
	//@Column(name="isCopy")
	//@ColumnCondition(columnName="isCopy",queryType=QueryType.EQUAL)
	private Integer isCopy;
	//@ApiModelProperty(name = "tokenJson" , value = "用户自定义授权信息")
	//@Column(name="tokenJson")
	//@ColumnCondition(columnName="tokenJson",queryType=QueryType.EQUAL)
	private String tokenJson;
	//@ApiModelProperty(name = "codeEbay" , value = "ebay认可物流")
	//@Column(name="codeEbay")
	//@ColumnCondition(columnName="codeEbay",queryType=QueryType.EQUAL)
	private String codeEbay;
	//@ApiModelProperty(name = "codeAliexpress" , value = "速卖通认可物流")
	//@Column(name="codeAliexpress")
	//@ColumnCondition(columnName="codeAliexpress",queryType=QueryType.EQUAL)
	private String codeAliexpress;
	//@ApiModelProperty(name = "codeAmazon" , value = "amazon认可物流")
	//@Column(name="codeAmazon")
	//@ColumnCondition(columnName="codeAmazon",queryType=QueryType.EQUAL)
	private String codeAmazon;
	//@ApiModelProperty(name = "codeDhgate" , value = "敦煌认可物流")
	//@Column(name="codeDhgate")
	//@ColumnCondition(columnName="codeDhgate",queryType=QueryType.EQUAL)
	private String codeDhgate;
	//@ApiModelProperty(name = "codeWish" , value = "wish认可物流")
	//@Column(name="codeWish")
	//@ColumnCondition(columnName="codeWish",queryType=QueryType.EQUAL)
	private String codeWish;
	//@ApiModelProperty(name = "codeLazada" , value = "lazada认可物流")
	//@Column(name="codeLazada")
	//@ColumnCondition(columnName="codeLazada",queryType=QueryType.EQUAL)
	private String codeLazada;
	//@ApiModelProperty(name = "codeCdiscount" , value = "cdiscount认可物流")
	//@Column(name="codeCdiscount")
	//@ColumnCondition(columnName="codeCdiscount",queryType=QueryType.EQUAL)
	private String codeCdiscount;
	//@ApiModelProperty(name = "codeTopHatter" , value = "Ensogo认可物流")
	//@Column(name="codeTopHatter")
	//@ColumnCondition(columnName="codeTopHatter",queryType=QueryType.EQUAL)
	private String codeTopHatter;
	//@ApiModelProperty(name = "codeLinio" , value = "Linio认可物流")
	//@Column(name="codeLinio")
	//@ColumnCondition(columnName="codeLinio",queryType=QueryType.EQUAL)
	private String codeLinio;
	//@ApiModelProperty(name = "codeJoom" , value = "Joom认可物流")
	//@Column(name="codeJoom")
	//@ColumnCondition(columnName="codeJoom",queryType=QueryType.EQUAL)
	private String codeJoom;
	//@ApiModelProperty(name = "isCalculateFee" , value = "是否估算运费")
	//@Column(name="isCalculateFee")
	//@ColumnCondition(columnName="isCalculateFee",queryType=QueryType.EQUAL)
	private Integer isCalculateFee;
	//@ApiModelProperty(name = "shippingFeeTypeId" , value = "运费估算公式ID,对应DB_ShippingFeeType.id")
	//@Column(name="shippingFeeTypeId")
	//@ColumnCondition(columnName="shippingFeeTypeId",queryType=QueryType.EQUAL)
	private Integer shippingFeeTypeId;
	//@ApiModelProperty(name = "shippingFeeType" , value = "运费公式类型；1：马帮；2：自定义")
	//@Column(name="shippingFeeType")
	//@ColumnCondition(columnName="shippingFeeType",queryType=QueryType.EQUAL)
	private Integer shippingFeeType;
	//@ApiModelProperty(name = "labelId1" , value = "打印标签1")
	//@Column(name="labelId1")
	//@ColumnCondition(columnName="labelId1",queryType=QueryType.EQUAL)
	private Integer labelId1;
	//@ApiModelProperty(name = "labelId2" , value = "打印标签2")
	//@Column(name="labelId2")
	//@ColumnCondition(columnName="labelId2",queryType=QueryType.EQUAL)
	private Integer labelId2;
	//@ApiModelProperty(name = "labelId3" , value = "打印标签3")
	//@Column(name="labelId3")
	//@ColumnCondition(columnName="labelId3",queryType=QueryType.EQUAL)
	private Integer labelId3;
	//@ApiModelProperty(name = "labelId4" , value = "打印标签4")
	//@Column(name="labelId4")
	//@ColumnCondition(columnName="labelId4",queryType=QueryType.EQUAL)
	private Integer labelId4;
	//@ApiModelProperty(name = "url" , value = "查询网址")
	//@Column(name="url")
	//@ColumnCondition(columnName="url",queryType=QueryType.EQUAL)
	private String url;
	//@ApiModelProperty(name = "canAutoApi" , value = "是否支持自动获取API:1支持2不支持")
	//@Column(name="canAutoApi")
	//@ColumnCondition(columnName="canAutoApi",queryType=QueryType.EQUAL)
	private Integer canAutoApi;
	//@ApiModelProperty(name = "discount" , value = "运费折扣,最大值填1")
	//@Column(name="discount")
	//@ColumnCondition(columnName="discount",queryType=QueryType.EQUAL)
	private Double discount;
	//@ApiModelProperty(name = "activeFlag" , value = "1可用2不可用")
	//@Column(name="activeFlag")
	//@ColumnCondition(columnName="activeFlag",queryType=QueryType.EQUAL)
	private Integer activeFlag;
	//@ApiModelProperty(name = "tracknumberSource" , value = "跟踪号来源1接口,2资源号段,3手填,4不需要")
	//@Column(name="tracknumberSource")
	//@ColumnCondition(columnName="tracknumberSource",queryType=QueryType.EQUAL)
	private Integer tracknumberSource;
	//@ApiModelProperty(name = "returnAddress1" , value = "回邮地址1")
	//@Column(name="returnAddress1")
	//@ColumnCondition(columnName="returnAddress1",queryType=QueryType.EQUAL)
	private Integer returnAddress1;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	//@ApiModelProperty(name = "returnAddress2" , value = "回邮地址2")
	//@Column(name="returnAddress2")
	//@ColumnCondition(columnName="returnAddress2",queryType=QueryType.EQUAL)
	private Integer returnAddress2;
	//@ApiModelProperty(name = "declareName" , value = "")
	//@Column(name="declareName")
	//@ColumnCondition(columnName="declareName",queryType=QueryType.EQUAL)
	private String declareName;
	//@ApiModelProperty(name = "declareEname" , value = "")
	//@Column(name="declareEname")
	//@ColumnCondition(columnName="declareEname",queryType=QueryType.EQUAL)
	private String declareEname;
	//@ApiModelProperty(name = "declareWeight" , value = "海关申报重量")
	//@Column(name="declareWeight")
	//@ColumnCondition(columnName="declareWeight",queryType=QueryType.EQUAL)
	private Integer declareWeight;
	//@ApiModelProperty(name = "declareFee" , value = "最小申报价值(RMB)")
	//@Column(name="declareFee")
	//@ColumnCondition(columnName="declareFee",queryType=QueryType.EQUAL)
	private Double declareFee;
	//@ApiModelProperty(name = "declareFeeOrigin" , value = "最小申报价值(美元)")
	//@Column(name="declareFeeOrigin")
	//@ColumnCondition(columnName="declareFeeOrigin",queryType=QueryType.EQUAL)
	private Double declareFeeOrigin;
	//@ApiModelProperty(name = "maxDeclareFee" , value = "最大申报价值(RMB)")
	//@Column(name="maxDeclareFee")
	//@ColumnCondition(columnName="maxDeclareFee",queryType=QueryType.EQUAL)
	private Double maxDeclareFee;
	//@ApiModelProperty(name = "maxDeclareFeeOrigin" , value = "最大申报价值(美元)")
	//@Column(name="maxDeclareFeeOrigin")
	//@ColumnCondition(columnName="maxDeclareFeeOrigin",queryType=QueryType.EQUAL)
	private Double maxDeclareFeeOrigin;
	//@ApiModelProperty(name = "declareFeeRate" , value = "申报价值占订单商品金额百分比")
	//@Column(name="declareFeeRate")
	//@ColumnCondition(columnName="declareFeeRate",queryType=QueryType.EQUAL)
	private Double declareFeeRate;
	//@ApiModelProperty(name = "propertyJson" , value = "")
	//@Column(name="propertyJson")
	//@ColumnCondition(columnName="propertyJson",queryType=QueryType.EQUAL)
	private String propertyJson;
	//@ApiModelProperty(name = "isReturn" , value = "只有当公用渠道表canReturn=1时才可供用户选择是否退货1退2不退")
	//@Column(name="isReturn")
	//@ColumnCondition(columnName="isReturn",queryType=QueryType.EQUAL)
	private Integer isReturn;
	//@ApiModelProperty(name = "updateTime" , value = "最后修改时间")
	//@Column(name="updateTime")
	//@ColumnCondition(columnName="updateTime",queryType=QueryType.EQUAL)
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date updateTime;
	//@ApiModelProperty(name = "remark" , value = "备注")
	//@Column(name="remark")
	//@ColumnCondition(columnName="remark",queryType=QueryType.EQUAL)
	private String remark;
	//@ApiModelProperty(name = "tWarehourseCode" , value = "海外仓渠道编码")
	//@Column(name="tWarehourseCode")
	//@ColumnCondition(columnName="tWarehourseCode",queryType=QueryType.EQUAL)
	private String tWarehourseCode;
	//@ApiModelProperty(name = "printJson" , value = "打印设置")
	//@Column(name="printJson")
	//@ColumnCondition(columnName="printJson",queryType=QueryType.EQUAL)
	private String printJson;
	//@ApiModelProperty(name = "warehouseAddress" , value = "首公里仓库")
	//@Column(name="warehouseAddress")
	//@ColumnCondition(columnName="warehouseAddress",queryType=QueryType.EQUAL)
	private String warehouseAddress;
	//@ApiModelProperty(name = "codeEnsogo" , value = "Ensogo认可物流")
	//@Column(name="codeEnsogo")
	//@ColumnCondition(columnName="codeEnsogo",queryType=QueryType.EQUAL)
	private String codeEnsogo;
	//@ApiModelProperty(name = "codeShopify" , value = "Shoppfy认可物流")
	//@Column(name="codeShopify")
	//@ColumnCondition(columnName="codeShopify",queryType=QueryType.EQUAL)
	private String codeShopify;
	//@ApiModelProperty(name = "codeShopee" , value = "Shopee认可物流")
	//@Column(name="codeShopee")
	//@ColumnCondition(columnName="codeShopee",queryType=QueryType.EQUAL)
	private String codeShopee;
	//@ApiModelProperty(name = "codeTmall" , value = "天猫认可物流")
	//@Column(name="codeTmall")
	//@ColumnCondition(columnName="codeTmall",queryType=QueryType.EQUAL)
	private String codeTmall;
	//@ApiModelProperty(name = "codeJd" , value = "京东认可物流")
	//@Column(name="codeJd")
	//@ColumnCondition(columnName="codeJd",queryType=QueryType.EQUAL)
	private String codeJd;
	//@ApiModelProperty(name = "codeAlibaba" , value = "alibaba认可物流")
	//@Column(name="codeAlibaba")
	//@ColumnCondition(columnName="codeAlibaba",queryType=QueryType.EQUAL)
	private String codeAlibaba;
	//@ApiModelProperty(name = "codeBigcommerce" , value = "")
	//@Column(name="codeBigcommerce")
	//@ColumnCondition(columnName="codeBigcommerce",queryType=QueryType.EQUAL)
	private String codeBigcommerce;
	//@ApiModelProperty(name = "codeEzbuy" , value = "")
	//@Column(name="codeEzbuy")
	//@ColumnCondition(columnName="codeEzbuy",queryType=QueryType.EQUAL)
	private String codeEzbuy;
	//@ApiModelProperty(name = "codeJolly" , value = "")
	//@Column(name="codeJolly")
	//@ColumnCondition(columnName="codeJolly",queryType=QueryType.EQUAL)
	private String codeJolly;
	//@ApiModelProperty(name = "channelType" , value = "渠道类型1.中仓2.海外仓")
	//@Column(name="channelType")
	//@ColumnCondition(columnName="channelType",queryType=QueryType.EQUAL)
	private Integer channelType;
	//@ApiModelProperty(name = "autoApi" , value = "1是2否")
	//@Column(name="autoApi")
	//@ColumnCondition(columnName="autoApi",queryType=QueryType.EQUAL)
	private Integer autoApi;
	//@ApiModelProperty(name = "autoApiProperty" , value = "")
	//@Column(name="autoApiProperty")
	//@ColumnCondition(columnName="autoApiProperty",queryType=QueryType.EQUAL)
	private String autoApiProperty;
	//@ApiModelProperty(name = "sqlFilter1" , value = "")
	//@Column(name="sqlFilter1")
	//@ColumnCondition(columnName="sqlFilter1",queryType=QueryType.EQUAL)
	private String sqlFilter1;
	//@ApiModelProperty(name = "sqlFilter2" , value = "")
	//@Column(name="sqlFilter2")
	//@ColumnCondition(columnName="sqlFilter2",queryType=QueryType.EQUAL)
	private String sqlFilter2;
	//@ApiModelProperty(name = "sqlFilter3" , value = "")
	//@Column(name="sqlFilter3")
	//@ColumnCondition(columnName="sqlFilter3",queryType=QueryType.EQUAL)
	private String sqlFilter3;
	//@ApiModelProperty(name = "sqlFilter4" , value = "")
	//@Column(name="sqlFilter4")
	//@ColumnCondition(columnName="sqlFilter4",queryType=QueryType.EQUAL)
	private String sqlFilter4;
	//@ApiModelProperty(name = "sqlFilter5" , value = "")
	//@Column(name="sqlFilter5")
	//@ColumnCondition(columnName="sqlFilter5",queryType=QueryType.EQUAL)
	private String sqlFilter5;
	//@ApiModelProperty(name = "fieldValues" , value = "")
	//@Column(name="fieldValues")
	//@ColumnCondition(columnName="fieldValues",queryType=QueryType.EQUAL)
	private String fieldValues;
	//@ApiModelProperty(name = "codeMagento" , value = "")
	//@Column(name="codeMagento")
	//@ColumnCondition(columnName="codeMagento",queryType=QueryType.EQUAL)
	private String codeMagento;
	//@ApiModelProperty(name = "codeJumia" , value = "")
	//@Column(name="codeJumia")
	//@ColumnCondition(columnName="codeJumia",queryType=QueryType.EQUAL)
	private String codeJumia;
	//@ApiModelProperty(name = "deliverJson" , value = "记录上次交运录入的信息")
	//@Column(name="deliverJson")
	//@ColumnCondition(columnName="deliverJson",queryType=QueryType.EQUAL)
	private String deliverJson;
	//@ApiModelProperty(name = "ebayplatformflag" , value = "ebay上传平台单号:0、未设置1、货运单号  2、内部单号  3、物流虚拟单号")
	//@Column(name="ebayplatformflag")
	//@ColumnCondition(columnName="ebayplatformflag",queryType=QueryType.EQUAL)
	private Integer ebayplatformflag;
	//@ApiModelProperty(name = "Wishplatformflag" , value = "wish上传平台单号：0、未设置1、货运单号  2、内部单号  3、物流虚拟单号")
	//@Column(name="Wishplatformflag")
	//@ColumnCondition(columnName="Wishplatformflag",queryType=QueryType.EQUAL)
	private Integer Wishplatformflag;
	//@ApiModelProperty(name = "amazonplatformflag" , value = "amazon上传平台单号：0、未设置1、货运单号  2、内部单号  3、物流虚拟单号")
	//@Column(name="amazonplatformflag")
	//@ColumnCondition(columnName="amazonplatformflag",queryType=QueryType.EQUAL)
	private Integer amazonplatformflag;
	//@ApiModelProperty(name = "Aliexpressplatformflag" , value = "Aliexpress上传平台单号：0、未设置1、货运单号  2、内部单号  3、物流虚拟单号")
	//@Column(name="Aliexpressplatformflag")
	//@ColumnCondition(columnName="Aliexpressplatformflag",queryType=QueryType.EQUAL)
	private Integer Aliexpressplatformflag;
	//@ApiModelProperty(name = "otherplatformflag" , value = "其他上传平台单号：0、未设置1、货运单号  2、内部单号  3、物流虚拟单号")
	//@Column(name="otherplatformflag")
	//@ColumnCondition(columnName="otherplatformflag",queryType=QueryType.EQUAL)
	private Integer otherplatformflag;
	//@ApiModelProperty(name = "lastPropertyJson" , value = "记录上次的选择")
	//@Column(name="lastPropertyJson")
	//@ColumnCondition(columnName="lastPropertyJson",queryType=QueryType.EQUAL)
	private String lastPropertyJson;
	//@ApiModelProperty(name = "isCaiNiao" , value = "")
	//@Column(name="isCaiNiao")
	//@ColumnCondition(columnName="isCaiNiao",queryType=QueryType.EQUAL)
	private Integer isCaiNiao;
	//@ApiModelProperty(name = "oldChannelId" , value = "")
	//@Column(name="oldChannelId")
	//@ColumnCondition(columnName="oldChannelId",queryType=QueryType.EQUAL)
	private Integer oldChannelId;
	//@ApiModelProperty(name = "smtShopAddJson" , value = "")
	//@Column(name="smtShopAddJson")
	//@ColumnCondition(columnName="smtShopAddJson",queryType=QueryType.EQUAL)
	private String smtShopAddJson;
	//@ApiModelProperty(name = "rebate" , value = "")
	//@Column(name="rebate")
	//@ColumnCondition(columnName="rebate",queryType=QueryType.EQUAL)
	private Double rebate;
	//@ApiModelProperty(name = "codeWalmart" , value = "")
	//@Column(name="codeWalmart")
	//@ColumnCondition(columnName="codeWalmart",queryType=QueryType.EQUAL)
	private String codeWalmart;
	//@ApiModelProperty(name = "channelFlag" , value = "")
	//@Column(name="channelFlag")
	//@ColumnCondition(columnName="channelFlag",queryType=QueryType.EQUAL)
	private Integer channelFlag;
	//@ApiModelProperty(name = "codePosition" , value = "")
	//@Column(name="codePosition")
	//@ColumnCondition(columnName="codePosition",queryType=QueryType.EQUAL)
	private String codePosition;
	//@ApiModelProperty(name = "codeVova" , value = "")
	//@Column(name="codeVova")
	//@ColumnCondition(columnName="codeVova",queryType=QueryType.EQUAL)
	private String codeVova;
	//@ApiModelProperty(name = "codeAlabom" , value = "")
	//@Column(name="codeAlabom")
	//@ColumnCondition(columnName="codeAlabom",queryType=QueryType.EQUAL)
	private String codeAlabom;
	//@ApiModelProperty(name = "codeOberlo" , value = "")
	//@Column(name="codeOberlo")
	//@ColumnCondition(columnName="codeOberlo",queryType=QueryType.EQUAL)
	private String codeOberlo;
	//@ApiModelProperty(name = "codeJdId" , value = "")
	//@Column(name="codeJdId")
	//@ColumnCondition(columnName="codeJdId",queryType=QueryType.EQUAL)
	private String codeJdId;
	//@ApiModelProperty(name = "freightCountryCode" , value = "")
	//@Column(name="freightCountryCode")
	//@ColumnCondition(columnName="freightCountryCode",queryType=QueryType.EQUAL)
	private String freightCountryCode;
	//@ApiModelProperty(name = "codeShoplaza" , value = "")
	//@Column(name="codeShoplaza")
	//@ColumnCondition(columnName="codeShoplaza",queryType=QueryType.EQUAL)
	private String codeShoplaza;
	//@ApiModelProperty(name = "isChannelQuery" , value = "")
	//@Column(name="isChannelQuery")
	//@ColumnCondition(columnName="isChannelQuery",queryType=QueryType.EQUAL)
	private Integer isChannelQuery;
	//@ApiModelProperty(name = "codeYandex" , value = "")
	//@Column(name="codeYandex")
	//@ColumnCondition(columnName="codeYandex",queryType=QueryType.EQUAL)
	private String codeYandex;
	//@ApiModelProperty(name = "forecastSetup" , value = "")
	//@Column(name="forecastSetup")
	//@ColumnCondition(columnName="forecastSetup",queryType=QueryType.EQUAL)
	private Integer forecastSetup;
	//@ApiModelProperty(name = "codeMercadolibre" , value = "")
	//@Column(name="codeMercadolibre")
	//@ColumnCondition(columnName="codeMercadolibre",queryType=QueryType.EQUAL)
	private String codeMercadolibre;
	//@ApiModelProperty(name = "codeReal" , value = "")
	//@Column(name="codeReal")
	//@ColumnCondition(columnName="codeReal",queryType=QueryType.EQUAL)
	private String codeReal;
	//@ApiModelProperty(name = "codeRakuten" , value = "")
	//@Column(name="codeRakuten")
	//@ColumnCondition(columnName="codeRakuten",queryType=QueryType.EQUAL)
	private String codeRakuten;

	//@Transient
	private String tableName;
	//@Override
	public String getDynamicTableName(){return tableName;}
	public String getTableName(){return tableName;}
	public void setTableName(String tableName){this.tableName = tableName;}

	public String getName(){return name;}
	public void setName(String name){this.name = name;}
	public Integer getCompanyId(){return companyId;}
	public void setCompanyId(Integer companyId){this.companyId = companyId;}
	public String getWarehouseCode(){return warehouseCode;}
	public void setWarehouseCode(String warehouseCode){this.warehouseCode = warehouseCode;}
	public Integer getMyLogisticsId(){return myLogisticsId;}
	public void setMyLogisticsId(Integer myLogisticsId){this.myLogisticsId = myLogisticsId;}
	public Integer getLogisticsId(){return logisticsId;}
	public void setLogisticsId(Integer logisticsId){this.logisticsId = logisticsId;}
	public String getLogisticsName(){return logisticsName;}
	public void setLogisticsName(String logisticsName){this.logisticsName = logisticsName;}
	public Integer getLogisticsChannelId(){return logisticsChannelId;}
	public void setLogisticsChannelId(Integer logisticsChannelId){this.logisticsChannelId = logisticsChannelId;}
	public String getLogisticsChannelName(){return logisticsChannelName;}
	public void setLogisticsChannelName(String logisticsChannelName){this.logisticsChannelName = logisticsChannelName;}
	public Integer getSource(){return source;}
	public void setSource(Integer source){this.source = source;}
	public Integer getTokenFlag(){return tokenFlag;}
	public void setTokenFlag(Integer tokenFlag){this.tokenFlag = tokenFlag;}
	public Integer getOpenFlag(){return openFlag;}
	public void setOpenFlag(Integer openFlag){this.openFlag = openFlag;}
	public Integer getHideTracknumber(){return hideTracknumber;}
	public void setHideTracknumber(Integer hideTracknumber){this.hideTracknumber = hideTracknumber;}
	public Integer getTracknumberLength(){return tracknumberLength;}
	public void setTracknumberLength(Integer tracknumberLength){this.tracknumberLength = tracknumberLength;}
	public Integer getSort(){return sort;}
	public void setSort(Integer sort){this.sort = sort;}
	public Integer getIsCopy(){return isCopy;}
	public void setIsCopy(Integer isCopy){this.isCopy = isCopy;}
	public String getTokenJson(){return tokenJson;}
	public void setTokenJson(String tokenJson){this.tokenJson = tokenJson;}
	public String getCodeEbay(){return codeEbay;}
	public void setCodeEbay(String codeEbay){this.codeEbay = codeEbay;}
	public String getCodeAliexpress(){return codeAliexpress;}
	public void setCodeAliexpress(String codeAliexpress){this.codeAliexpress = codeAliexpress;}
	public String getCodeAmazon(){return codeAmazon;}
	public void setCodeAmazon(String codeAmazon){this.codeAmazon = codeAmazon;}
	public String getCodeDhgate(){return codeDhgate;}
	public void setCodeDhgate(String codeDhgate){this.codeDhgate = codeDhgate;}
	public String getCodeWish(){return codeWish;}
	public void setCodeWish(String codeWish){this.codeWish = codeWish;}
	public String getCodeLazada(){return codeLazada;}
	public void setCodeLazada(String codeLazada){this.codeLazada = codeLazada;}
	public String getCodeCdiscount(){return codeCdiscount;}
	public void setCodeCdiscount(String codeCdiscount){this.codeCdiscount = codeCdiscount;}
	public String getCodeTopHatter(){return codeTopHatter;}
	public void setCodeTopHatter(String codeTopHatter){this.codeTopHatter = codeTopHatter;}
	public String getCodeLinio(){return codeLinio;}
	public void setCodeLinio(String codeLinio){this.codeLinio = codeLinio;}
	public String getCodeJoom(){return codeJoom;}
	public void setCodeJoom(String codeJoom){this.codeJoom = codeJoom;}
	public Integer getIsCalculateFee(){return isCalculateFee;}
	public void setIsCalculateFee(Integer isCalculateFee){this.isCalculateFee = isCalculateFee;}
	public Integer getShippingFeeTypeId(){return shippingFeeTypeId;}
	public void setShippingFeeTypeId(Integer shippingFeeTypeId){this.shippingFeeTypeId = shippingFeeTypeId;}
	public Integer getShippingFeeType(){return shippingFeeType;}
	public void setShippingFeeType(Integer shippingFeeType){this.shippingFeeType = shippingFeeType;}
	public Integer getLabelId1(){return labelId1;}
	public void setLabelId1(Integer labelId1){this.labelId1 = labelId1;}
	public Integer getLabelId2(){return labelId2;}
	public void setLabelId2(Integer labelId2){this.labelId2 = labelId2;}
	public Integer getLabelId3(){return labelId3;}
	public void setLabelId3(Integer labelId3){this.labelId3 = labelId3;}
	public Integer getLabelId4(){return labelId4;}
	public void setLabelId4(Integer labelId4){this.labelId4 = labelId4;}
	public String getUrl(){return url;}
	public void setUrl(String url){this.url = url;}
	public Integer getCanAutoApi(){return canAutoApi;}
	public void setCanAutoApi(Integer canAutoApi){this.canAutoApi = canAutoApi;}
	public Double getDiscount(){return discount;}
	public void setDiscount(Double discount){this.discount = discount;}
	public Integer getActiveFlag(){return activeFlag;}
	public void setActiveFlag(Integer activeFlag){this.activeFlag = activeFlag;}
	public Integer getTracknumberSource(){return tracknumberSource;}
	public void setTracknumberSource(Integer tracknumberSource){this.tracknumberSource = tracknumberSource;}
	public Integer getReturnAddress1(){return returnAddress1;}
	public void setReturnAddress1(Integer returnAddress1){this.returnAddress1 = returnAddress1;}
	public Integer getReturnAddress2(){return returnAddress2;}
	public void setReturnAddress2(Integer returnAddress2){this.returnAddress2 = returnAddress2;}
	public String getDeclareName(){return declareName;}
	public void setDeclareName(String declareName){this.declareName = declareName;}
	public String getDeclareEname(){return declareEname;}
	public void setDeclareEname(String declareEname){this.declareEname = declareEname;}
	public Integer getDeclareWeight(){return declareWeight;}
	public void setDeclareWeight(Integer declareWeight){this.declareWeight = declareWeight;}
	public Double getDeclareFee(){return declareFee;}
	public void setDeclareFee(Double declareFee){this.declareFee = declareFee;}
	public Double getDeclareFeeOrigin(){return declareFeeOrigin;}
	public void setDeclareFeeOrigin(Double declareFeeOrigin){this.declareFeeOrigin = declareFeeOrigin;}
	public Double getMaxDeclareFee(){return maxDeclareFee;}
	public void setMaxDeclareFee(Double maxDeclareFee){this.maxDeclareFee = maxDeclareFee;}
	public Double getMaxDeclareFeeOrigin(){return maxDeclareFeeOrigin;}
	public void setMaxDeclareFeeOrigin(Double maxDeclareFeeOrigin){this.maxDeclareFeeOrigin = maxDeclareFeeOrigin;}
	public Double getDeclareFeeRate(){return declareFeeRate;}
	public void setDeclareFeeRate(Double declareFeeRate){this.declareFeeRate = declareFeeRate;}
	public String getPropertyJson(){return propertyJson;}
	public void setPropertyJson(String propertyJson){this.propertyJson = propertyJson;}
	public Integer getIsReturn(){return isReturn;}
	public void setIsReturn(Integer isReturn){this.isReturn = isReturn;}
	public Date getUpdateTime(){return updateTime;}
	public void setUpdateTime(Date updateTime){this.updateTime = updateTime;}
	public String getRemark(){return remark;}
	public void setRemark(String remark){this.remark = remark;}
	public String getTWarehourseCode(){return tWarehourseCode;}
	public void setTWarehourseCode(String tWarehourseCode){this.tWarehourseCode = tWarehourseCode;}
	public String getPrintJson(){return printJson;}
	public void setPrintJson(String printJson){this.printJson = printJson;}
	public String getWarehouseAddress(){return warehouseAddress;}
	public void setWarehouseAddress(String warehouseAddress){this.warehouseAddress = warehouseAddress;}
	public String getCodeEnsogo(){return codeEnsogo;}
	public void setCodeEnsogo(String codeEnsogo){this.codeEnsogo = codeEnsogo;}
	public String getCodeShopify(){return codeShopify;}
	public void setCodeShopify(String codeShopify){this.codeShopify = codeShopify;}
	public String getCodeShopee(){return codeShopee;}
	public void setCodeShopee(String codeShopee){this.codeShopee = codeShopee;}
	public String getCodeTmall(){return codeTmall;}
	public void setCodeTmall(String codeTmall){this.codeTmall = codeTmall;}
	public String getCodeJd(){return codeJd;}
	public void setCodeJd(String codeJd){this.codeJd = codeJd;}
	public String getCodeAlibaba(){return codeAlibaba;}
	public void setCodeAlibaba(String codeAlibaba){this.codeAlibaba = codeAlibaba;}
	public String getCodeBigcommerce(){return codeBigcommerce;}
	public void setCodeBigcommerce(String codeBigcommerce){this.codeBigcommerce = codeBigcommerce;}
	public String getCodeEzbuy(){return codeEzbuy;}
	public void setCodeEzbuy(String codeEzbuy){this.codeEzbuy = codeEzbuy;}
	public String getCodeJolly(){return codeJolly;}
	public void setCodeJolly(String codeJolly){this.codeJolly = codeJolly;}
	public Integer getChannelType(){return channelType;}
	public void setChannelType(Integer channelType){this.channelType = channelType;}
	public Integer getAutoApi(){return autoApi;}
	public void setAutoApi(Integer autoApi){this.autoApi = autoApi;}
	public String getAutoApiProperty(){return autoApiProperty;}
	public void setAutoApiProperty(String autoApiProperty){this.autoApiProperty = autoApiProperty;}
	public String getSqlFilter1(){return sqlFilter1;}
	public void setSqlFilter1(String sqlFilter1){this.sqlFilter1 = sqlFilter1;}
	public String getSqlFilter2(){return sqlFilter2;}
	public void setSqlFilter2(String sqlFilter2){this.sqlFilter2 = sqlFilter2;}
	public String getSqlFilter3(){return sqlFilter3;}
	public void setSqlFilter3(String sqlFilter3){this.sqlFilter3 = sqlFilter3;}
	public String getSqlFilter4(){return sqlFilter4;}
	public void setSqlFilter4(String sqlFilter4){this.sqlFilter4 = sqlFilter4;}
	public String getSqlFilter5(){return sqlFilter5;}
	public void setSqlFilter5(String sqlFilter5){this.sqlFilter5 = sqlFilter5;}
	public String getFieldValues(){return fieldValues;}
	public void setFieldValues(String fieldValues){this.fieldValues = fieldValues;}
	public String getCodeMagento(){return codeMagento;}
	public void setCodeMagento(String codeMagento){this.codeMagento = codeMagento;}
	public String getCodeJumia(){return codeJumia;}
	public void setCodeJumia(String codeJumia){this.codeJumia = codeJumia;}
	public String getDeliverJson(){return deliverJson;}
	public void setDeliverJson(String deliverJson){this.deliverJson = deliverJson;}
	public Integer getEbayplatformflag(){return ebayplatformflag;}
	public void setEbayplatformflag(Integer ebayplatformflag){this.ebayplatformflag = ebayplatformflag;}
	public Integer getWishplatformflag(){return Wishplatformflag;}
	public void setWishplatformflag(Integer Wishplatformflag){this.Wishplatformflag = Wishplatformflag;}
	public Integer getAmazonplatformflag(){return amazonplatformflag;}
	public void setAmazonplatformflag(Integer amazonplatformflag){this.amazonplatformflag = amazonplatformflag;}
	public Integer getAliexpressplatformflag(){return Aliexpressplatformflag;}
	public void setAliexpressplatformflag(Integer Aliexpressplatformflag){this.Aliexpressplatformflag = Aliexpressplatformflag;}
	public Integer getOtherplatformflag(){return otherplatformflag;}
	public void setOtherplatformflag(Integer otherplatformflag){this.otherplatformflag = otherplatformflag;}
	public String getLastPropertyJson(){return lastPropertyJson;}
	public void setLastPropertyJson(String lastPropertyJson){this.lastPropertyJson = lastPropertyJson;}
	public Integer getIsCaiNiao(){return isCaiNiao;}
	public void setIsCaiNiao(Integer isCaiNiao){this.isCaiNiao = isCaiNiao;}
	public Integer getOldChannelId(){return oldChannelId;}
	public void setOldChannelId(Integer oldChannelId){this.oldChannelId = oldChannelId;}
	public String getSmtShopAddJson(){return smtShopAddJson;}
	public void setSmtShopAddJson(String smtShopAddJson){this.smtShopAddJson = smtShopAddJson;}
	public Double getRebate(){return rebate;}
	public void setRebate(Double rebate){this.rebate = rebate;}
	public String getCodeWalmart(){return codeWalmart;}
	public void setCodeWalmart(String codeWalmart){this.codeWalmart = codeWalmart;}
	public Integer getChannelFlag(){return channelFlag;}
	public void setChannelFlag(Integer channelFlag){this.channelFlag = channelFlag;}
	public String getCodePosition(){return codePosition;}
	public void setCodePosition(String codePosition){this.codePosition = codePosition;}
	public String getCodeVova(){return codeVova;}
	public void setCodeVova(String codeVova){this.codeVova = codeVova;}
	public String getCodeAlabom(){return codeAlabom;}
	public void setCodeAlabom(String codeAlabom){this.codeAlabom = codeAlabom;}
	public String getCodeOberlo(){return codeOberlo;}
	public void setCodeOberlo(String codeOberlo){this.codeOberlo = codeOberlo;}
	public String getCodeJdId(){return codeJdId;}
	public void setCodeJdId(String codeJdId){this.codeJdId = codeJdId;}
	public String getFreightCountryCode(){return freightCountryCode;}
	public void setFreightCountryCode(String freightCountryCode){this.freightCountryCode = freightCountryCode;}
	public String getCodeShoplaza(){return codeShoplaza;}
	public void setCodeShoplaza(String codeShoplaza){this.codeShoplaza = codeShoplaza;}
	public Integer getIsChannelQuery(){return isChannelQuery;}
	public void setIsChannelQuery(Integer isChannelQuery){this.isChannelQuery = isChannelQuery;}
	public String getCodeYandex(){return codeYandex;}
	public void setCodeYandex(String codeYandex){this.codeYandex = codeYandex;}
	public Integer getForecastSetup(){return forecastSetup;}
	public void setForecastSetup(Integer forecastSetup){this.forecastSetup = forecastSetup;}
	public String getCodeMercadolibre(){return codeMercadolibre;}
	public void setCodeMercadolibre(String codeMercadolibre){this.codeMercadolibre = codeMercadolibre;}
	public String getCodeReal(){return codeReal;}
	public void setCodeReal(String codeReal){this.codeReal = codeReal;}
	public String getCodeRakuten(){return codeRakuten;}
	public void setCodeRakuten(String codeRakuten){this.codeRakuten = codeRakuten;}
}