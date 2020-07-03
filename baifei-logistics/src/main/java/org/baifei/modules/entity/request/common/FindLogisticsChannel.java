package org.baifei.modules.entity.request.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;
/**
 *
 * @Description:  mabangorderbase.db_logisticschannel
 * @Author:       baihailiang
 * @CreateDate:   2020-06-05 10:25:45
 * @Version:      V1.0
 *
 */
@ApiModel(value="FindLogisticsChannel",description="")
public class FindLogisticsChannel {
	@ApiModelProperty(name = "logisticsChannelId" , value = "货运方式Id")
	private Integer logisticsChannelId;
	@ApiModelProperty(name="logisticsChannelIdList",value="货运方式Id集合")
	private List<Integer> logisticsChannelIdList;
	@ApiModelProperty(name = "logisticsId" , value = "物流公司编号")
	private Integer logisticsId;
	@ApiModelProperty(name = "logisticsChannelCode" , value = "渠道编码")
	private String logisticsChannelCode;
	@ApiModelProperty(name = "activeFlag" , value = "渠道编码")
	private Integer activeFlag;


	public Integer getLogisticsChannelId() {
		return logisticsChannelId;
	}

	public void setLogisticsChannelId(Integer logisticsChannelId) {
		this.logisticsChannelId = logisticsChannelId;
	}

	public List<Integer> getLogisticsChannelIdList() {
		return logisticsChannelIdList;
	}

	public void setLogisticsChannelIdList(List<Integer> logisticsChannelIdList) {
		this.logisticsChannelIdList = logisticsChannelIdList;
	}

	public Integer getLogisticsId() {
		return logisticsId;
	}

	public void setLogisticsId(Integer logisticsId) {
		this.logisticsId = logisticsId;
	}

	public String getLogisticsChannelCode() {
		return logisticsChannelCode;
	}

	public void setLogisticsChannelCode(String logisticsChannelCode) {
		this.logisticsChannelCode = logisticsChannelCode;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}
}