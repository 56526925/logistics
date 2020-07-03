package org.baifei.modules.entity.response.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;


/**
 *
 * //@Description:  mabangorderbase.db_mylogistics_account
 * //@Author:       baihailiang
 * //@CreateDate:   2020-03-11 18:07:08
 * //@Version:      V1.0
 *
 */
//@ApiModel(value="DbMylogisticsaccount",description="")
public class DbMylogisticsaccount{
	//@Column(name="Id")
	//@ColumnCondition(columnName="Id",queryType=QueryType.EQUAL)
	private Integer Id;

	//@ApiModelProperty(name = "companyId" , value = "企业编号")
	//@Column(name="companyId")
	//@ColumnCondition(columnName="companyId",queryType=QueryType.EQUAL)
	private Integer companyId;
	//@ApiModelProperty(name = "shopType" , value = "店铺类型,涉及到")
	//@Column(name="shopType")
	//@ColumnCondition(columnName="shopType",queryType=QueryType.EQUAL)
	private Integer shopType;
	//@ApiModelProperty(name = "logisticsId" , value = "物流公司编号")
	//@Column(name="logisticsId")
	//@ColumnCondition(columnName="logisticsId",queryType=QueryType.EQUAL)
	private Integer logisticsId;
	//@ApiModelProperty(name = "mylogisticsId" , value = "货运方式编号")
	//@Column(name="mylogisticsId")
	//@ColumnCondition(columnName="mylogisticsId",queryType=QueryType.EQUAL)
	private Integer mylogisticsId;
	//@ApiModelProperty(name = "nickname" , value = "账号别名")
	//@Column(name="nickname")
	//@ColumnCondition(columnName="nickname",queryType=QueryType.EQUAL)
	private String nickname;
	//@ApiModelProperty(name = "name" , value = "账号名称")
	//@Column(name="name")
	//@ColumnCondition(columnName="name",queryType=QueryType.EQUAL)
	private String name;
	//@ApiModelProperty(name = "password" , value = "账号密码")
	//@Column(name="password")
	//@ColumnCondition(columnName="password",queryType=QueryType.EQUAL)
	private String password;
	//@ApiModelProperty(name = "token" , value = "访问令牌")
	//@Column(name="token")
	//@ColumnCondition(columnName="token",queryType=QueryType.EQUAL)
	private String token;
	//@ApiModelProperty(name = "refreshToken" , value = "长令牌")
	//@Column(name="refreshToken")
	//@ColumnCondition(columnName="refreshToken",queryType=QueryType.EQUAL)
	private String refreshToken;
	//@ApiModelProperty(name = "accessKey" , value = "备用字段，比如邮局的揽收机构号")
	//@Column(name="accessKey")
	//@ColumnCondition(columnName="accessKey",queryType=QueryType.EQUAL)
	private String accessKey;
	//@ApiModelProperty(name = "expireTime" , value = "过期时间")
	//@Column(name="expireTime")
	//@ColumnCondition(columnName="expireTime",queryType=QueryType.EQUAL)
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date expireTime;
	//@ApiModelProperty(name = "status" , value = "状态1.开启2.停用")
	//@Column(name="status")
	//@ColumnCondition(columnName="status",queryType=QueryType.EQUAL)
	private Integer status;
	//@ApiModelProperty(name = "oper" , value = "操作员工")
	//@Column(name="oper")
	//@ColumnCondition(columnName="oper",queryType=QueryType.EQUAL)
	private String oper;
	//@ApiModelProperty(name = "operTime" , value = "操作时间")
	//@Column(name="operTime")
	//@ColumnCondition(columnName="operTime",queryType=QueryType.EQUAL)
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date operTime;

	//@Transient
	private String tableName;
	//@Override
	public String getDynamicTableName(){return tableName;}
	public String getTableName(){return tableName;}
	public void setTableName(String tableName){this.tableName = tableName;}

	public Integer getId() { return Id; }
	public void setId(Integer id) { Id = id; }
	public Integer getCompanyId(){return companyId;}
	public void setCompanyId(Integer companyId){this.companyId = companyId;}
	public Integer getShopType(){return shopType;}
	public void setShopType(Integer shopType){this.shopType = shopType;}
	public Integer getLogisticsId(){return logisticsId;}
	public void setLogisticsId(Integer logisticsId){this.logisticsId = logisticsId;}
	public Integer getMylogisticsId(){return mylogisticsId;}
	public void setMylogisticsId(Integer mylogisticsId){this.mylogisticsId = mylogisticsId;}
	public String getNickname() { return nickname; }
	public void setNickname(String nickname) { this.nickname = nickname; }
	public String getName(){return name;}
	public void setName(String name){this.name = name;}
	public String getPassword(){return password;}
	public void setPassword(String password){this.password = password;}
	public String getToken(){return token;}
	public void setToken(String token){this.token = token;}
	public String getRefreshToken(){return refreshToken;}
	public void setRefreshToken(String refreshToken){this.refreshToken = refreshToken;}
	public String getAccessKey(){return accessKey;}
	public void setAccessKey(String accessKey){this.accessKey = accessKey;}
	public Date getExpireTime(){return expireTime;}
	public void setExpireTime(Date expireTime){this.expireTime = expireTime;}
	public Integer getStatus(){return status;}
	public void setStatus(Integer status){this.status = status;}
	public String getOper(){return oper;}
	public void setOper(String oper){this.oper = oper;}
	public Date getOperTime(){return operTime;}
	public void setOperTime(Date operTime){this.operTime = operTime;}
}