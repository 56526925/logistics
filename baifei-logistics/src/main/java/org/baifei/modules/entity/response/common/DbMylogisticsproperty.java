package org.baifei.modules.entity.response.common;

/**
 *
 * //@Description:  mabangorderbase.db_mylogistics_property
 * //@Author:       baihailiang
 * //@CreateDate:   2020-03-11 18:07:09
 * //@Version:      V1.0
 *
 */
//@ApiModel(value="DbMylogisticsproperty",description="")
public class DbMylogisticsproperty  {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	//@ApiModelProperty(name = "name" , value = "字段名称")
	//@Column(name="name")
	//@ColumnCondition(columnName="name",queryType=QueryType.EQUAL)
	private String name;
	//@ApiModelProperty(name = "filed" , value = "")
	//@Column(name="filed")
	//@ColumnCondition(columnName="filed",queryType=QueryType.EQUAL)
	private String filed;
	//@ApiModelProperty(name = "defaultvalue" , value = "")
	//@Column(name="defaultvalue")
	//@ColumnCondition(columnName="defaultvalue",queryType=QueryType.EQUAL)
	private String defaultvalue;
	//@ApiModelProperty(name = "property" , value = "字段属性")
	//@Column(name="property")
	//@ColumnCondition(columnName="property",queryType=QueryType.EQUAL)
	private String property;

	//@Transient
	private String tableName;
	//@Override
	public String getDynamicTableName(){return tableName;}
	public String getTableName(){return tableName;}
	public void setTableName(String tableName){this.tableName = tableName;}

	public String getName(){return name;}
	public void setName(String name){this.name = name;}
	public String getFiled(){return filed;}
	public void setFiled(String filed){this.filed = filed;}
	public String getDefaultvalue(){return defaultvalue;}
	public void setDefaultvalue(String defaultvalue){this.defaultvalue = defaultvalue;}
	public String getProperty(){return property;}
	public void setProperty(String property){this.property = property;}
}