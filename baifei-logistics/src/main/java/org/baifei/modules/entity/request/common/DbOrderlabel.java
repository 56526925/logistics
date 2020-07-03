package org.baifei.modules.entity.request.common;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DbOrderlabel {
    private long id;
	private static final long serialVersionUID = 5836275880143344247L;
	//@ApiModelProperty(value="企业编号",name="companyId")
    //@Column(name="companyId")
    //@ColumnCondition(columnName="companyId",queryType=QueryType.EQUAL)
    private Integer companyId;
    //@ApiModelProperty(value="订单编号",name="platformOrderId")
    //@Column(name="platformOrderId")
    //@ColumnCondition(columnName="platformOrderId",queryType=QueryType.EQUAL)
    private String platformOrderId;
    //@ApiModelProperty(value="订单编号",name="platformOrderIds")
    //@Transient
    //@ColumnCondition(columnName="platformOrderId",queryType=QueryType.IN)
    private List<String> platformOrderIds;
    //@ApiModelProperty(value="标签类型 a地址单 c报关单 p配货单 ac地址单+配货单",name="labelType")
    //@Column(name="labelType")
    //@ColumnCondition(columnName="labelType",queryType=QueryType.EQUAL)
    private String labelType;
    //@ApiModelProperty(value="标签尺寸 a4 10x10",name="size")
    //@Column(name="size")
    //@ColumnCondition(columnName="size",queryType=QueryType.EQUAL)
    private String size;
    //@ApiModelProperty(value="物流单号",name="trackNumer")
    //@Column(name="trackNumer")
    //@ColumnCondition(columnName="trackNumer",queryType=QueryType.EQUAL)
    private String trackNumer;
    //@ApiModelProperty(value="0官方标签 >0自定义标签编号",name="labelId")
    //@Column(name="labelId")
    //@ColumnCondition(columnName="labelId",queryType=QueryType.EQUAL)
    private Integer labelId;
    //@ApiModelProperty(value="物流渠道编号",name="logisticsChannelId")
    //@Column(name="logisticsChannelId")
    //@ColumnCondition(columnName="logisticsChannelId",queryType=QueryType.EQUAL)
    private Integer logisticsChannelId;
    //@ApiModelProperty(value="标签地址",name="path")
    //@Column(name="path")
    //@ColumnCondition(columnName="path",queryType=QueryType.EQUAL)
    private String path;
    //@ApiModelProperty(value="2:非高仿 1高仿",name="isFang")
    //@Column(name="isFang")
    //@ColumnCondition(columnName="isFang",queryType=QueryType.EQUAL)
    private Integer isFang;
    //@ApiModelProperty(value="创建时间",name="createTime")
    //@Column(name="createTime")
    //@ColumnCondition(columnName="createTime",queryType=QueryType.EQUAL)
    private Date createTime;

}
