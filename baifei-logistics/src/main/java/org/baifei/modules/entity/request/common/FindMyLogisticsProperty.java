package org.baifei.modules.entity.request.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;
/**
 *
 * @Description:  mabangorderbase.db_mylogistics_property
 * @Author:       baihailiang
 * @CreateDate:   2020-06-05 10:25:46
 * @Version:      V1.0
 *
 */
@ApiModel(value="FindMyLogisticsPropertyId",description="")
public class FindMyLogisticsProperty {
	@ApiModelProperty(name = "myLogisticsPropertyId" , value = "属性id")
	private Integer myLogisticsPropertyId;
	@ApiModelProperty(value="",name="myLogisticsPropertyIdList")
	private List<Integer> myLogisticsPropertyIdList;
	@ApiModelProperty(name = "name" , value = "字段名称")
	private String names;
	@ApiModelProperty(name = "filed" , value = "")
	private String fileds;

	public Integer getMyLogisticsPropertyId() {
		return myLogisticsPropertyId;
	}

	public void setMyLogisticsPropertyId(Integer myLogisticsPropertyId) {
		this.myLogisticsPropertyId = myLogisticsPropertyId;
	}

	public List<Integer> getMyLogisticsPropertyIdList() {
		return myLogisticsPropertyIdList;
	}

	public void setMyLogisticsPropertyIdList(List<Integer> myLogisticsPropertyIdList) {
		this.myLogisticsPropertyIdList = myLogisticsPropertyIdList;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getFileds() {
		return fileds;
	}

	public void setFileds(String fileds) {
		this.fileds = fileds;
	}
}