package org.baifei.modules.entity.response.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 *
 * //@Description:  mabangorderbase.db_logistics
 * //@Author:       baihailiang
 * //@CreateDate:   2020-03-11 18:07:08
 * //@Version:      V1.0
 *
 */
//@ApiModel(value="DbLogistics",description="")
	//物流公司
public class DbLogistics {
	//@Column(name="Id")
	//@ColumnCondition(columnName="Id",queryType=QueryType.EQUAL)
	private Integer Id;

	//@ApiModelProperty(name = "name" , value = "物流公司名称")
	//@Column(name="name")
	//@ColumnCondition(columnName="name",queryType=QueryType.EQUAL)
	private String name;
	//@ApiModelProperty(name = "companyId" , value = "企业编号")
	//@Column(name="companyId")
	//@ColumnCondition(columnName="companyId",queryType=QueryType.EQUAL)
	private Integer companyId;
	//@ApiModelProperty(name = "code" , value = "编码")
	//@Column(name="code")
	//@ColumnCondition(columnName="code",queryType=QueryType.EQUAL)
	private String code;
	//@ApiModelProperty(name = "descr" , value = "描述")
	//@Column(name="descr")
	//@ColumnCondition(columnName="descr",queryType=QueryType.EQUAL)
	private String descr;
	//@ApiModelProperty(name = "sortby" , value = "排序")
	//@Column(name="sortby")
	//@ColumnCondition(columnName="sortby",queryType=QueryType.EQUAL)
	private Integer sortby;
	//@ApiModelProperty(name = "isApi" , value = "是否支持API对接")
	//@Column(name="isApi")
	//@ColumnCondition(columnName="isApi",queryType=QueryType.EQUAL)
	private Integer isApi;
	//@ApiModelProperty(name = "hasAddressConfig" , value = "有无揽收地址配置 1有2没有")
	//@Column(name="hasAddressConfig")
	//@ColumnCondition(columnName="hasAddressConfig",queryType=QueryType.EQUAL)
	private Integer hasAddressConfig;
	//@ApiModelProperty(name = "openflag" , value = "开关标志1开2关")
	//@Column(name="openflag")
	//@ColumnCondition(columnName="openflag",queryType=QueryType.EQUAL)
	private Integer openflag;
	//@ApiModelProperty(name = "labelJson" , value = "标准标签的打印备注如:SKU+数量")
	//@Column(name="labelJson")
	//@ColumnCondition(columnName="labelJson",queryType=QueryType.EQUAL)
	private String labelJson;
	//@ApiModelProperty(name = "labelJson2" , value = "标准标签的打印备注2")
	//@Column(name="labelJson2")
	//@ColumnCondition(columnName="labelJson2",queryType=QueryType.EQUAL)
	private String labelJson2;
	//@ApiModelProperty(name = "isBiaoJu" , value = "是否来源镖局1是2不是")
	//@Column(name="isBiaoJu")
	//@ColumnCondition(columnName="isBiaoJu",queryType=QueryType.EQUAL)
	private Integer isBiaoJu;
	//@ApiModelProperty(name = "isStandardTag" , value = "是否有标准标签 1有2没有")
	//@Column(name="isStandardTag")
	//@ColumnCondition(columnName="isStandardTag",queryType=QueryType.EQUAL)
	private Integer isStandardTag;
	//@ApiModelProperty(name = "isCloud" , value = "1:是;2:否")
	//@Column(name="isCloud")
	//@ColumnCondition(columnName="isCloud",queryType=QueryType.EQUAL)
	private Integer isCloud;
	//@ApiModelProperty(name = "torkenJson" , value = "物流账号")
	//@Column(name="torkenJson")
	//@ColumnCondition(columnName="torkenJson",queryType=QueryType.EQUAL)
	private String torkenJson;
	//@ApiModelProperty(name = "warehouseJson" , value = "海外仓仓库信息")
	//@Column(name="warehouseJson")
	//@ColumnCondition(columnName="warehouseJson",queryType=QueryType.EQUAL)
	private String warehouseJson;
	//@ApiModelProperty(name = "propertyJson" , value = "")
	//@Column(name="propertyJson")
	//@ColumnCondition(columnName="propertyJson",queryType=QueryType.EQUAL)
	private String propertyJson;
	//@ApiModelProperty(name = "isDomestic" , value = "适合国内还是国外:1:国内;2:国外;3:国内外")
	//@Column(name="isDomestic")
	//@ColumnCondition(columnName="isDomestic",queryType=QueryType.EQUAL)
	private Integer isDomestic;
	//@ApiModelProperty(name = "isSLogisticsChannel" , value = "是否支持二次交运;1:是;2:否")
	//@Column(name="isSLogisticsChannel")
	//@ColumnCondition(columnName="isSLogisticsChannel",queryType=QueryType.EQUAL)
	private Integer isSLogisticsChannel;
	//@ApiModelProperty(name = "isPickupAddress" , value = "是否有揽收地址;1:是；2：否")
	//@Column(name="isPickupAddress")
	//@ColumnCondition(columnName="isPickupAddress",queryType=QueryType.EQUAL)
	private Integer isPickupAddress;
	//@ApiModelProperty(name = "isHwarehouse" , value = "是否为海外仓物流1：是-对接物流 2：否 3:是-物流对接马帮")
	//@Column(name="isHwarehouse")
	//@ColumnCondition(columnName="isHwarehouse",queryType=QueryType.EQUAL)
	private Integer isHwarehouse;
	//@ApiModelProperty(name = "countryCode" , value = "海外仓预定国家,多个 二字码用';'隔开")
	//@Column(name="countryCode")
	//@ColumnCondition(columnName="countryCode",queryType=QueryType.EQUAL)
	private String countryCode;
	//@ApiModelProperty(name = "isBiaojuLogistics" , value = "是否是镖局合作物流：1.是 2.否")
	//@Column(name="isBiaojuLogistics")
	//@ColumnCondition(columnName="isBiaojuLogistics",queryType=QueryType.EQUAL)
	private Integer isBiaojuLogistics;
	//@ApiModelProperty(name = "platformId" , value = "支持平台")
	//@Column(name="platformId")
	//@ColumnCondition(columnName="platformId",queryType=QueryType.EQUAL)
	private Integer platformId;
	//@ApiModelProperty(name = "isOpenFreight" , value = "")
	//@Column(name="isOpenFreight")
	//@ColumnCondition(columnName="isOpenFreight",queryType=QueryType.EQUAL)
	private Integer isOpenFreight;
	//@ApiModelProperty(name = "contact" , value = "")
	//@Column(name="contact")
	//@ColumnCondition(columnName="contact",queryType=QueryType.EQUAL)
	private String contact;
	//@ApiModelProperty(name = "operId" , value = "操作员")
	//@Column(name="operId")
	//@ColumnCondition(columnName="operId",queryType=QueryType.EQUAL)
	private Integer operId;
	//@ApiModelProperty(name = "operTime" , value = "操作时间")
	//@Column(name="operTime")
	//@ColumnCondition(columnName="operTime",queryType=QueryType.EQUAL)
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date operTime;
	//@ApiModelProperty(name = "modifytime" , value = "")
	//@Column(name="modifytime")
	//@ColumnCondition(columnName="modifytime",queryType=QueryType.EQUAL)
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date modifytime;
	//@ApiModelProperty(name = "hasForecastChannel" , value = "")
	//@Column(name="hasForecastChannel")
	//@ColumnCondition(columnName="hasForecastChannel",queryType=QueryType.EQUAL)
	private Integer hasForecastChannel;
	//@ApiModelProperty(name = "forecastChannel" , value = "")
	//@Column(name="forecastChannel")
	//@ColumnCondition(columnName="forecastChannel",queryType=QueryType.EQUAL)
	private String forecastChannel;

	//@Transient
	private String tableName;
	//@Override
	public String getDynamicTableName(){return tableName;}
	public String getTableName(){return tableName;}
	public void setTableName(String tableName){this.tableName = tableName;}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName(){return name;}
	public void setName(String name){this.name = name;}
	public Integer getCompanyId(){return companyId;}
	public void setCompanyId(Integer companyId){this.companyId = companyId;}
	public String getCode(){return code;}
	public void setCode(String code){this.code = code;}
	public String getDescr(){return descr;}
	public void setDescr(String descr){this.descr = descr;}
	public Integer getSortby(){return sortby;}
	public void setSortby(Integer sortby){this.sortby = sortby;}
	public Integer getIsApi(){return isApi;}
	public void setIsApi(Integer isApi){this.isApi = isApi;}
	public Integer getHasAddressConfig(){return hasAddressConfig;}
	public void setHasAddressConfig(Integer hasAddressConfig){this.hasAddressConfig = hasAddressConfig;}
	public Integer getOpenflag(){return openflag;}
	public void setOpenflag(Integer openflag){this.openflag = openflag;}
	public String getLabelJson(){return labelJson;}
	public void setLabelJson(String labelJson){this.labelJson = labelJson;}
	public String getLabelJson2(){return labelJson2;}
	public void setLabelJson2(String labelJson2){this.labelJson2 = labelJson2;}
	public Integer getIsBiaoJu(){return isBiaoJu;}
	public void setIsBiaoJu(Integer isBiaoJu){this.isBiaoJu = isBiaoJu;}
	public Integer getIsStandardTag(){return isStandardTag;}
	public void setIsStandardTag(Integer isStandardTag){this.isStandardTag = isStandardTag;}
	public Integer getIsCloud(){return isCloud;}
	public void setIsCloud(Integer isCloud){this.isCloud = isCloud;}
	public String getTorkenJson(){return torkenJson;}
	public void setTorkenJson(String torkenJson){this.torkenJson = torkenJson;}
	public String getWarehouseJson(){return warehouseJson;}
	public void setWarehouseJson(String warehouseJson){this.warehouseJson = warehouseJson;}
	public String getPropertyJson(){return propertyJson;}
	public void setPropertyJson(String propertyJson){this.propertyJson = propertyJson;}
	public Integer getIsDomestic(){return isDomestic;}
	public void setIsDomestic(Integer isDomestic){this.isDomestic = isDomestic;}
	public Integer getIsSLogisticsChannel(){return isSLogisticsChannel;}
	public void setIsSLogisticsChannel(Integer isSLogisticsChannel){this.isSLogisticsChannel = isSLogisticsChannel;}
	public Integer getIsPickupAddress(){return isPickupAddress;}
	public void setIsPickupAddress(Integer isPickupAddress){this.isPickupAddress = isPickupAddress;}
	public Integer getIsHwarehouse(){return isHwarehouse;}
	public void setIsHwarehouse(Integer isHwarehouse){this.isHwarehouse = isHwarehouse;}
	public String getCountryCode(){return countryCode;}
	public void setCountryCode(String countryCode){this.countryCode = countryCode;}
	public Integer getIsBiaojuLogistics(){return isBiaojuLogistics;}
	public void setIsBiaojuLogistics(Integer isBiaojuLogistics){this.isBiaojuLogistics = isBiaojuLogistics;}
	public Integer getPlatformId(){return platformId;}
	public void setPlatformId(Integer platformId){this.platformId = platformId;}
	public Integer getIsOpenFreight(){return isOpenFreight;}
	public void setIsOpenFreight(Integer isOpenFreight){this.isOpenFreight = isOpenFreight;}
	public String getContact(){return contact;}
	public void setContact(String contact){this.contact = contact;}
	public Integer getOperId(){return operId;}
	public void setOperId(Integer operId){this.operId = operId;}
	public Date getOperTime(){return operTime;}
	public void setOperTime(Date operTime){this.operTime = operTime;}
	public Date getModifytime(){return modifytime;}
	public void setModifytime(Date modifytime){this.modifytime = modifytime;}
	public Integer getHasForecastChannel(){return hasForecastChannel;}
	public void setHasForecastChannel(Integer hasForecastChannel){this.hasForecastChannel = hasForecastChannel;}
	public String getForecastChannel(){return forecastChannel;}
	public void setForecastChannel(String forecastChannel){this.forecastChannel = forecastChannel;}
}