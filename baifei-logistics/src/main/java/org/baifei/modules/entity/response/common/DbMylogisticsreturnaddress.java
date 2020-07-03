package org.baifei.modules.entity.response.common;

/**
 *
 * //@Description:  mabangorderbase.db_mylogistics_returnaddress
 * //@Author:       baihailiang
 * //@CreateDate:   2020-03-11 18:07:08
 * //@Version:      V1.0
 *
 */
//@ApiModel(value="DbMylogisticsreturnaddress",description="")
	//寄件回邮信息
public class DbMylogisticsreturnaddress   {
	//@Column(name="Id")
	//@ColumnCondition(columnName="Id",queryType=QueryType.EQUAL)
	private Integer Id;
	//@ApiModelProperty(name = "name" , value = "地址名称")
	//@Column(name="name")
	//@ColumnCondition(columnName="name",queryType=QueryType.EQUAL)
	private String name;
	//@ApiModelProperty(name = "myLogisticsId" , value = "我的物流公司编号")
	//@Column(name="myLogisticsId")
	//@ColumnCondition(columnName="myLogisticsId",queryType=QueryType.EQUAL)
	private Integer myLogisticsId;
	//@ApiModelProperty(name = "logisticsId" , value = "公用物流公司编号")
	//@Column(name="logisticsId")
	//@ColumnCondition(columnName="logisticsId",queryType=QueryType.EQUAL)
	private Integer logisticsId;
	//@ApiModelProperty(name = "companyId" , value = "公司编号")
	//@Column(name="companyId")
	//@ColumnCondition(columnName="companyId",queryType=QueryType.EQUAL)
	private Integer companyId;
	//@ApiModelProperty(name = "countryCode" , value = "国家编码")
	//@Column(name="countryCode")
	//@ColumnCondition(columnName="countryCode",queryType=QueryType.EQUAL)
	private String countryCode;
	//@ApiModelProperty(name = "provinceCode" , value = "省市编码")
	//@Column(name="provinceCode")
	//@ColumnCondition(columnName="provinceCode",queryType=QueryType.EQUAL)
	private String provinceCode;
	//@ApiModelProperty(name = "provinceCn" , value = "省中文")
	//@Column(name="provinceCn")
	//@ColumnCondition(columnName="provinceCn",queryType=QueryType.EQUAL)
	private String provinceCn;
	//@ApiModelProperty(name = "provinceEn" , value = "省英文")
	//@Column(name="provinceEn")
	//@ColumnCondition(columnName="provinceEn",queryType=QueryType.EQUAL)
	private String provinceEn;
	//@ApiModelProperty(name = "cityCode" , value = "城市编码")
	//@Column(name="cityCode")
	//@ColumnCondition(columnName="cityCode",queryType=QueryType.EQUAL)
	private String cityCode;
	//@ApiModelProperty(name = "cityCn" , value = "城市中文")
	//@Column(name="cityCn")
	//@ColumnCondition(columnName="cityCn",queryType=QueryType.EQUAL)
	private String cityCn;
	//@ApiModelProperty(name = "cityEn" , value = "城市英文")
	//@Column(name="cityEn")
	//@ColumnCondition(columnName="cityEn",queryType=QueryType.EQUAL)
	private String cityEn;
	//@ApiModelProperty(name = "areaCode" , value = "地区编码")
	//@Column(name="areaCode")
	//@ColumnCondition(columnName="areaCode",queryType=QueryType.EQUAL)
	private String areaCode;
	//@ApiModelProperty(name = "areaCn" , value = "地区中文")
	//@Column(name="areaCn")
	//@ColumnCondition(columnName="areaCn",queryType=QueryType.EQUAL)
	private String areaCn;
	//@ApiModelProperty(name = "areaEn" , value = "地区英文")
	//@Column(name="areaEn")
	//@ColumnCondition(columnName="areaEn",queryType=QueryType.EQUAL)
	private String areaEn;
	//@ApiModelProperty(name = "contactCn" , value = "中文联系人")
	//@Column(name="contactCn")
	//@ColumnCondition(columnName="contactCn",queryType=QueryType.EQUAL)
	private String contactCn;
	//@ApiModelProperty(name = "contactEn" , value = "英文联系人")
	//@Column(name="contactEn")
	//@ColumnCondition(columnName="contactEn",queryType=QueryType.EQUAL)
	private String contactEn;
	//@ApiModelProperty(name = "phone" , value = "电话")
	//@Column(name="phone")
	//@ColumnCondition(columnName="phone",queryType=QueryType.EQUAL)
	private String phone;
	//@ApiModelProperty(name = "mobile" , value = "手机")
	//@Column(name="mobile")
	//@ColumnCondition(columnName="mobile",queryType=QueryType.EQUAL)
	private String mobile;
	//@ApiModelProperty(name = "postcode" , value = "邮编")
	//@Column(name="postcode")
	//@ColumnCondition(columnName="postcode",queryType=QueryType.EQUAL)
	private String postcode;
	//@ApiModelProperty(name = "email" , value = "邮箱")
	//@Column(name="email")
	//@ColumnCondition(columnName="email",queryType=QueryType.EQUAL)
	private String email;
	//@ApiModelProperty(name = "fax" , value = "传真")
	//@Column(name="fax")
	//@ColumnCondition(columnName="fax",queryType=QueryType.EQUAL)
	private String fax;
	//@ApiModelProperty(name = "streetCn" , value = "中文街道")
	//@Column(name="streetCn")
	//@ColumnCondition(columnName="streetCn",queryType=QueryType.EQUAL)
	private String streetCn;
	//@ApiModelProperty(name = "streetEn" , value = "英文街道")
	//@Column(name="streetEn")
	//@ColumnCondition(columnName="streetEn",queryType=QueryType.EQUAL)
	private String streetEn;
	//@ApiModelProperty(name = "companyCn" , value = "公司中文名")
	//@Column(name="companyCn")
	//@ColumnCondition(columnName="companyCn",queryType=QueryType.EQUAL)
	private String companyCn;
	//@ApiModelProperty(name = "companyEn" , value = "公司英文名")
	//@Column(name="companyEn")
	//@ColumnCondition(columnName="companyEn",queryType=QueryType.EQUAL)
	private String companyEn;
	//@ApiModelProperty(name = "openflag" , value = "1开启2关闭")
	//@Column(name="openflag")
	//@ColumnCondition(columnName="openflag",queryType=QueryType.EQUAL)
	private Integer openflag;

	//@Transient
	private String tableName;
	//@Override
	public String getDynamicTableName(){return tableName;}
	public String getTableName(){return tableName;}
	public void setTableName(String tableName){this.tableName = tableName;}

	public Integer getId() { return Id; }
	public void setId(Integer id) { Id = id; }
	public String getName(){return name;}
	public void setName(String name){this.name = name;}
	public Integer getMyLogisticsId(){return myLogisticsId;}
	public void setMyLogisticsId(Integer myLogisticsId){this.myLogisticsId = myLogisticsId;}
	public Integer getLogisticsId(){return logisticsId;}
	public void setLogisticsId(Integer logisticsId){this.logisticsId = logisticsId;}
	public Integer getCompanyId(){return companyId;}
	public void setCompanyId(Integer companyId){this.companyId = companyId;}
	public String getCountryCode(){return countryCode;}
	public void setCountryCode(String countryCode){this.countryCode = countryCode;}
	public String getProvinceCode(){return provinceCode;}
	public void setProvinceCode(String provinceCode){this.provinceCode = provinceCode;}
	public String getProvinceCn(){return provinceCn;}
	public void setProvinceCn(String provinceCn){this.provinceCn = provinceCn;}
	public String getProvinceEn(){return provinceEn;}
	public void setProvinceEn(String provinceEn){this.provinceEn = provinceEn;}
	public String getCityCode(){return cityCode;}
	public void setCityCode(String cityCode){this.cityCode = cityCode;}
	public String getCityCn(){return cityCn;}
	public void setCityCn(String cityCn){this.cityCn = cityCn;}
	public String getCityEn(){return cityEn;}
	public void setCityEn(String cityEn){this.cityEn = cityEn;}
	public String getAreaCode(){return areaCode;}
	public void setAreaCode(String areaCode){this.areaCode = areaCode;}
	public String getAreaCn(){return areaCn;}
	public void setAreaCn(String areaCn){this.areaCn = areaCn;}
	public String getAreaEn(){return areaEn;}
	public void setAreaEn(String areaEn){this.areaEn = areaEn;}
	public String getContactCn(){return contactCn;}
	public void setContactCn(String contactCn){this.contactCn = contactCn;}
	public String getContactEn(){return contactEn;}
	public void setContactEn(String contactEn){this.contactEn = contactEn;}
	public String getPhone(){return phone;}
	public void setPhone(String phone){this.phone = phone;}
	public String getMobile(){return mobile;}
	public void setMobile(String mobile){this.mobile = mobile;}
	public String getPostcode(){return postcode;}
	public void setPostcode(String postcode){this.postcode = postcode;}
	public String getEmail(){return email;}
	public void setEmail(String email){this.email = email;}
	public String getFax(){return fax;}
	public void setFax(String fax){this.fax = fax;}
	public String getStreetCn(){return streetCn;}
	public void setStreetCn(String streetCn){this.streetCn = streetCn;}
	public String getStreetEn(){return streetEn;}
	public void setStreetEn(String streetEn){this.streetEn = streetEn;}
	public String getCompanyCn(){return companyCn;}
	public void setCompanyCn(String companyCn){this.companyCn = companyCn;}
	public String getCompanyEn(){return companyEn;}
	public void setCompanyEn(String companyEn){this.companyEn = companyEn;}
	public Integer getOpenflag(){return openflag;}
	public void setOpenflag(Integer openflag){this.openflag = openflag;}
}