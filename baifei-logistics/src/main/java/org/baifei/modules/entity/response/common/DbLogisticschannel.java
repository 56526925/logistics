package org.baifei.modules.entity.response.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;


/**
 *
 * //@Description:  mabangorderbase.db_logisticschannel
 * //@Author:       baihailiang
 * //@CreateDate:   2020-03-11 18:07:08
 * //@Version:      V1.0
 *
 */
//@ApiModel(value="DbLogisticschannel",description="")
	//物流渠道
public class DbLogisticschannel  {
	 private String id;
	//@ApiModelProperty(name = "logisticsId" , value = "物流公司编号")
	//@Column(name="logisticsId")
	//@ColumnCondition(columnName="logisticsId",queryType=QueryType.EQUAL)
	private Integer logisticsId;
	//@ApiModelProperty(name = "logisticsName" , value = "物流公司名称")
	//@Column(name="logisticsName")
	//@ColumnCondition(columnName="logisticsName",queryType=QueryType.EQUAL)
	private String logisticsName;
	//@ApiModelProperty(name = "name" , value = "渠道名称")
	//@Column(name="name")
	//@ColumnCondition(columnName="name",queryType=QueryType.EQUAL)
	private String name;
	//@ApiModelProperty(name = "descr" , value = "渠道描述")
	//@Column(name="descr")
	//@ColumnCondition(columnName="descr",queryType=QueryType.EQUAL)
	private String descr;
	//@ApiModelProperty(name = "code" , value = "渠道编码")
	//@Column(name="code")
	//@ColumnCondition(columnName="code",queryType=QueryType.EQUAL)
	private String code;
	//@ApiModelProperty(name = "warehouseCode" , value = "海外仓仓库编码")
	//@Column(name="warehouseCode")
	//@ColumnCondition(columnName="warehouseCode",queryType=QueryType.EQUAL)
	private String warehouseCode;
	//@ApiModelProperty(name = "customerId" , value = "物流公司内部的渠道编号")
	//@Column(name="customerId")
	//@ColumnCondition(columnName="customerId",queryType=QueryType.EQUAL)
	private String customerId;
	//@ApiModelProperty(name = "tracknumberSource" , value = "跟踪号来源1接口,2资源号段,3手填,4不需要")
	//@Column(name="tracknumberSource")
	//@ColumnCondition(columnName="tracknumberSource",queryType=QueryType.EQUAL)
	private Integer tracknumberSource;
	//@ApiModelProperty(name = "canAutoApi" , value = "是否支持自动获取API:1支持2不支持")
	//@Column(name="canAutoApi")
	//@ColumnCondition(columnName="canAutoApi",queryType=QueryType.EQUAL)
	private Integer canAutoApi;
	//@ApiModelProperty(name = "needTracknumber" , value = "是否有跟踪号:1需要2不需要")
	//@Column(name="needTracknumber")
	//@ColumnCondition(columnName="needTracknumber",queryType=QueryType.EQUAL)
	private Integer needTracknumber;
	//@ApiModelProperty(name = "codeEbay" , value = "ebay平台认可物流")
	//@Column(name="codeEbay")
	//@ColumnCondition(columnName="codeEbay",queryType=QueryType.EQUAL)
	private String codeEbay;
	//@ApiModelProperty(name = "codeAliexpress" , value = "ali平台认可物流")
	//@Column(name="codeAliexpress")
	//@ColumnCondition(columnName="codeAliexpress",queryType=QueryType.EQUAL)
	private String codeAliexpress;
	//@ApiModelProperty(name = "codeAmazon" , value = "amazon平台认可物流")
	//@Column(name="codeAmazon")
	//@ColumnCondition(columnName="codeAmazon",queryType=QueryType.EQUAL)
	private String codeAmazon;
	//@ApiModelProperty(name = "codeDhgate" , value = "敦煌平台认可物流")
	//@Column(name="codeDhgate")
	//@ColumnCondition(columnName="codeDhgate",queryType=QueryType.EQUAL)
	private String codeDhgate;
	//@ApiModelProperty(name = "codeWish" , value = "Wish平台认可物流")
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
	//@ApiModelProperty(name = "isCalculateFee" , value = "是否支持计算运费:1支持,2不支持")
	//@Column(name="isCalculateFee")
	//@ColumnCondition(columnName="isCalculateFee",queryType=QueryType.EQUAL)
	private Integer isCalculateFee;
	//@ApiModelProperty(name = "tracknumberLength" , value = "跟踪号位长,用于发货验证,0表示不验证")
	//@Column(name="tracknumberLength")
	//@ColumnCondition(columnName="tracknumberLength",queryType=QueryType.EQUAL)
	private Integer tracknumberLength;
	//@ApiModelProperty(name = "defaultLabelId1" , value = "打印标签1")
	//@Column(name="defaultLabelId1")
	//@ColumnCondition(columnName="defaultLabelId1",queryType=QueryType.EQUAL)
	private Integer defaultLabelId1;
	//@ApiModelProperty(name = "defaultLabelId2" , value = "打印标签2")
	//@Column(name="defaultLabelId2")
	//@ColumnCondition(columnName="defaultLabelId2",queryType=QueryType.EQUAL)
	private Integer defaultLabelId2;
	//@ApiModelProperty(name = "defaultLabelId3" , value = "打印标签3")
	//@Column(name="defaultLabelId3")
	//@ColumnCondition(columnName="defaultLabelId3",queryType=QueryType.EQUAL)
	private Integer defaultLabelId3;
	//@ApiModelProperty(name = "defaultLabelId4" , value = "打印标签4")
	//@Column(name="defaultLabelId4")
	//@ColumnCondition(columnName="defaultLabelId4",queryType=QueryType.EQUAL)
	private Integer defaultLabelId4;
	//@ApiModelProperty(name = "url" , value = "查询方式")
	//@Column(name="url")
	//@ColumnCondition(columnName="url",queryType=QueryType.EQUAL)
	private String url;
	//@ApiModelProperty(name = "activeFlag" , value = "1可用2不可用")
	//@Column(name="activeFlag")
	//@ColumnCondition(columnName="activeFlag",queryType=QueryType.EQUAL)
	private Integer activeFlag;
	//@ApiModelProperty(name = "declareName" , value = "")
	//@Column(name="declareName")
	//@ColumnCondition(columnName="declareName",queryType=QueryType.EQUAL)
	private String declareName;
	//@ApiModelProperty(name = "declareEname" , value = "")
	//@Column(name="declareEname")
	//@ColumnCondition(columnName="declareEname",queryType=QueryType.EQUAL)
	private String declareEname;
	//@ApiModelProperty(name = "declareFee" , value = "")
	//@Column(name="declareFee")
	//@ColumnCondition(columnName="declareFee",queryType=QueryType.EQUAL)
	private Double declareFee;
	//@ApiModelProperty(name = "propertyJson" , value = "")
	//@Column(name="propertyJson")
	//@ColumnCondition(columnName="propertyJson",queryType=QueryType.EQUAL)
	private String propertyJson;
	//@ApiModelProperty(name = "isGuahao" , value = "是否挂号,1是2不是")
	//@Column(name="isGuahao")
	//@ColumnCondition(columnName="isGuahao",queryType=QueryType.EQUAL)
	private Integer isGuahao;
	//@ApiModelProperty(name = "isTruck" , value = "是否支持物流跟踪,1是2不是")
	//@Column(name="isTruck")
	//@ColumnCondition(columnName="isTruck",queryType=QueryType.EQUAL)
	private Integer isTruck;
	//@ApiModelProperty(name = "canReturn" , value = "渠道是否支持退件1支持2不支持")
	//@Column(name="canReturn")
	//@ColumnCondition(columnName="canReturn",queryType=QueryType.EQUAL)
	private Integer canReturn;
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
	//@ApiModelProperty(name = "isVersion" , value = "1:大马帮;2:小马帮;3:大小马")
	//@Column(name="isVersion")
	//@ColumnCondition(columnName="isVersion",queryType=QueryType.EQUAL)
	private Integer isVersion;
	//@ApiModelProperty(name = "LabelId1" , value = "打印标签地址单(高妨标签测试)")
	//@Column(name="LabelId1")
	//@ColumnCondition(columnName="LabelId1",queryType=QueryType.EQUAL)
	private Integer LabelId1;
	//@ApiModelProperty(name = "LabelId2" , value = "打印标签报关单(高妨标签测试)")
	//@Column(name="LabelId2")
	//@ColumnCondition(columnName="LabelId2",queryType=QueryType.EQUAL)
	private Integer LabelId2;
	//@ApiModelProperty(name = "LabelId3" , value = "打印标签配货单(高妨标签测试)")
	//@Column(name="LabelId3")
	//@ColumnCondition(columnName="LabelId3",queryType=QueryType.EQUAL)
	private Integer LabelId3;
	//@ApiModelProperty(name = "LabelId4" , value = "打印标签发票(高妨标签测试)")
	//@Column(name="LabelId4")
	//@ColumnCondition(columnName="LabelId4",queryType=QueryType.EQUAL)
	private Integer LabelId4;
	//@ApiModelProperty(name = "LabelType" , value = "标签类型(A-C-P,AC-P, 。。。)")
	//@Column(name="LabelType")
	//@ColumnCondition(columnName="LabelType",queryType=QueryType.EQUAL)
	private String LabelType;
	//@ApiModelProperty(name = "has10Label" , value = "1:有10*10官方;2:没有10*10官方")
	//@Column(name="has10Label")
	//@ColumnCondition(columnName="has10Label",queryType=QueryType.EQUAL)
	private Integer has10Label;
	//@ApiModelProperty(name = "hasA4Label" , value = "1:有A4官方;2:没有A4官方")
	//@Column(name="hasA4Label")
	//@ColumnCondition(columnName="hasA4Label",queryType=QueryType.EQUAL)
	private Integer hasA4Label;
	//@ApiModelProperty(name = "codeEnsogo" , value = "Ensogo认可物流")
	//@Column(name="codeEnsogo")
	//@ColumnCondition(columnName="codeEnsogo",queryType=QueryType.EQUAL)
	private String codeEnsogo;
	//@ApiModelProperty(name = "recommendCodeAli" , value = "推荐ali认可物流方式")
	//@Column(name="recommendCodeAli")
	//@ColumnCondition(columnName="recommendCodeAli",queryType=QueryType.EQUAL)
	private String recommendCodeAli;
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
	//@ApiModelProperty(name = "codeJoom" , value = "Joom认可物流")
	//@Column(name="codeJoom")
	//@ColumnCondition(columnName="codeJoom",queryType=QueryType.EQUAL)
	private String codeJoom;
	//@ApiModelProperty(name = "codeBigcommerce" , value = "Bigcommerce认可物流")
	//@Column(name="codeBigcommerce")
	//@ColumnCondition(columnName="codeBigcommerce",queryType=QueryType.EQUAL)
	private String codeBigcommerce;
	//@ApiModelProperty(name = "codeJolly" , value = "Jolly认可物流")
	//@Column(name="codeJolly")
	//@ColumnCondition(columnName="codeJolly",queryType=QueryType.EQUAL)
	private String codeJolly;
	//@ApiModelProperty(name = "codeJumia" , value = "Jumia认可物流")
	//@Column(name="codeJumia")
	//@ColumnCondition(columnName="codeJumia",queryType=QueryType.EQUAL)
	private String codeJumia;
	//@ApiModelProperty(name = "codeEzbuy" , value = "Ezbuy认可物流")
	//@Column(name="codeEzbuy")
	//@ColumnCondition(columnName="codeEzbuy",queryType=QueryType.EQUAL)
	private String codeEzbuy;
	//@ApiModelProperty(name = "isCaiNiao" , value = "是否菜鸟物流渠道1.是2否")
	//@Column(name="isCaiNiao")
	//@ColumnCondition(columnName="isCaiNiao",queryType=QueryType.EQUAL)
	private Integer isCaiNiao;
	//@ApiModelProperty(name = "labelSplitType" , value = "标签拆分类型1.常规拆分")
	//@Column(name="labelSplitType")
	//@ColumnCondition(columnName="labelSplitType",queryType=QueryType.EQUAL)
	private Double labelSplitType;
	//@ApiModelProperty(name = "htmlSize" , value = "html标签json")
	//@Column(name="htmlSize")
	//@ColumnCondition(columnName="htmlSize",queryType=QueryType.EQUAL)
	private String htmlSize;
	//@ApiModelProperty(name = "modifytime" , value = "数据同步时间")
	//@Column(name="modifytime")
	//@ColumnCondition(columnName="modifytime",queryType=QueryType.EQUAL)
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date modifytime;
	//@ApiModelProperty(name = "codeWalmart" , value = "Walmart认可物流")
	//@Column(name="codeWalmart")
	//@ColumnCondition(columnName="codeWalmart",queryType=QueryType.EQUAL)
	private String codeWalmart;

	//@Transient
	private String tableName;
	//@Override

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDynamicTableName(){return tableName;}
	public String getTableName(){return tableName;}
	public void setTableName(String tableName){this.tableName = tableName;}

	public Integer getLogisticsId(){return logisticsId;}
	public void setLogisticsId(Integer logisticsId){this.logisticsId = logisticsId;}
	public String getLogisticsName(){return logisticsName;}
	public void setLogisticsName(String logisticsName){this.logisticsName = logisticsName;}
	public String getName(){return name;}
	public void setName(String name){this.name = name;}
	public String getDescr(){return descr;}
	public void setDescr(String descr){this.descr = descr;}
	public String getCode(){return code;}
	public void setCode(String code){this.code = code;}
	public String getWarehouseCode(){return warehouseCode;}
	public void setWarehouseCode(String warehouseCode){this.warehouseCode = warehouseCode;}
	public String getCustomerId(){return customerId;}
	public void setCustomerId(String customerId){this.customerId = customerId;}
	public Integer getTracknumberSource(){return tracknumberSource;}
	public void setTracknumberSource(Integer tracknumberSource){this.tracknumberSource = tracknumberSource;}
	public Integer getCanAutoApi(){return canAutoApi;}
	public void setCanAutoApi(Integer canAutoApi){this.canAutoApi = canAutoApi;}
	public Integer getNeedTracknumber(){return needTracknumber;}
	public void setNeedTracknumber(Integer needTracknumber){this.needTracknumber = needTracknumber;}
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
	public Integer getIsCalculateFee(){return isCalculateFee;}
	public void setIsCalculateFee(Integer isCalculateFee){this.isCalculateFee = isCalculateFee;}
	public Integer getTracknumberLength(){return tracknumberLength;}
	public void setTracknumberLength(Integer tracknumberLength){this.tracknumberLength = tracknumberLength;}
	public Integer getDefaultLabelId1(){return defaultLabelId1;}
	public void setDefaultLabelId1(Integer defaultLabelId1){this.defaultLabelId1 = defaultLabelId1;}
	public Integer getDefaultLabelId2(){return defaultLabelId2;}
	public void setDefaultLabelId2(Integer defaultLabelId2){this.defaultLabelId2 = defaultLabelId2;}
	public Integer getDefaultLabelId3(){return defaultLabelId3;}
	public void setDefaultLabelId3(Integer defaultLabelId3){this.defaultLabelId3 = defaultLabelId3;}
	public Integer getDefaultLabelId4(){return defaultLabelId4;}
	public void setDefaultLabelId4(Integer defaultLabelId4){this.defaultLabelId4 = defaultLabelId4;}
	public String getUrl(){return url;}
	public void setUrl(String url){this.url = url;}
	public Integer getActiveFlag(){return activeFlag;}
	public void setActiveFlag(Integer activeFlag){this.activeFlag = activeFlag;}
	public String getDeclareName(){return declareName;}
	public void setDeclareName(String declareName){this.declareName = declareName;}
	public String getDeclareEname(){return declareEname;}
	public void setDeclareEname(String declareEname){this.declareEname = declareEname;}
	public Double getDeclareFee(){return declareFee;}
	public void setDeclareFee(Double declareFee){this.declareFee = declareFee;}
	public String getPropertyJson(){return propertyJson;}
	public void setPropertyJson(String propertyJson){this.propertyJson = propertyJson;}
	public Integer getIsGuahao(){return isGuahao;}
	public void setIsGuahao(Integer isGuahao){this.isGuahao = isGuahao;}
	public Integer getIsTruck(){return isTruck;}
	public void setIsTruck(Integer isTruck){this.isTruck = isTruck;}
	public Integer getCanReturn(){return canReturn;}
	public void setCanReturn(Integer canReturn){this.canReturn = canReturn;}
	public Date getUpdateTime(){return updateTime;}
	public void setUpdateTime(Date updateTime){this.updateTime = updateTime;}
	public String getRemark(){return remark;}
	public void setRemark(String remark){this.remark = remark;}
	public Integer getIsVersion(){return isVersion;}
	public void setIsVersion(Integer isVersion){this.isVersion = isVersion;}
	public Integer getLabelId1(){return LabelId1;}
	public void setLabelId1(Integer LabelId1){this.LabelId1 = LabelId1;}
	public Integer getLabelId2(){return LabelId2;}
	public void setLabelId2(Integer LabelId2){this.LabelId2 = LabelId2;}
	public Integer getLabelId3(){return LabelId3;}
	public void setLabelId3(Integer LabelId3){this.LabelId3 = LabelId3;}
	public Integer getLabelId4(){return LabelId4;}
	public void setLabelId4(Integer LabelId4){this.LabelId4 = LabelId4;}
	public String getLabelType(){return LabelType;}
	public void setLabelType(String LabelType){this.LabelType = LabelType;}
	public Integer getHas10Label(){return has10Label;}
	public void setHas10Label(Integer has10Label){this.has10Label = has10Label;}
	public Integer getHasA4Label(){return hasA4Label;}
	public void setHasA4Label(Integer hasA4Label){this.hasA4Label = hasA4Label;}
	public String getCodeEnsogo(){return codeEnsogo;}
	public void setCodeEnsogo(String codeEnsogo){this.codeEnsogo = codeEnsogo;}
	public String getRecommendCodeAli(){return recommendCodeAli;}
	public void setRecommendCodeAli(String recommendCodeAli){this.recommendCodeAli = recommendCodeAli;}
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
	public String getCodeJoom(){return codeJoom;}
	public void setCodeJoom(String codeJoom){this.codeJoom = codeJoom;}
	public String getCodeBigcommerce(){return codeBigcommerce;}
	public void setCodeBigcommerce(String codeBigcommerce){this.codeBigcommerce = codeBigcommerce;}
	public String getCodeJolly(){return codeJolly;}
	public void setCodeJolly(String codeJolly){this.codeJolly = codeJolly;}
	public String getCodeJumia(){return codeJumia;}
	public void setCodeJumia(String codeJumia){this.codeJumia = codeJumia;}
	public String getCodeEzbuy(){return codeEzbuy;}
	public void setCodeEzbuy(String codeEzbuy){this.codeEzbuy = codeEzbuy;}
	public Integer getIsCaiNiao(){return isCaiNiao;}
	public void setIsCaiNiao(Integer isCaiNiao){this.isCaiNiao = isCaiNiao;}
	public Double getLabelSplitType(){return labelSplitType;}
	public void setLabelSplitType(Double labelSplitType){this.labelSplitType = labelSplitType;}
	public String getHtmlSize(){return htmlSize;}
	public void setHtmlSize(String htmlSize){this.htmlSize = htmlSize;}
	public Date getModifytime(){return modifytime;}
	public void setModifytime(Date modifytime){this.modifytime = modifytime;}
	public String getCodeWalmart(){return codeWalmart;}
	public void setCodeWalmart(String codeWalmart){this.codeWalmart = codeWalmart;}
}