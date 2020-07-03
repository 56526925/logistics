package org.baifei.modules.entity.request.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;
/**
 *
 * @Description:  mabangorderbase.db_logistics
 * @Author:       baihailiang
 * @CreateDate:   2020-06-04 15:53:43
 * @Version:      V1.0
 *
 */
@ApiModel(value="FindDbLogistics",description="")
public class FindLogistics {

	@ApiModelProperty(name = "logisticsId" , value = "")
	private Integer logisticsId;
	@ApiModelProperty(value="",name="logisticsIdList")
	private List<Integer> logisticsIdList;
	@ApiModelProperty(name = "companyId" , value = "企业编号")
	private Integer companyId;
	@ApiModelProperty(name = "logisticsCode" , value = "编码")
	private String logisticsCode;
	@ApiModelProperty(name = "openflag" , value = "开关标志1开2关")
	private Integer openflag;


	public Integer getLogisticsId() {
		return logisticsId;
	}

	public void setLogisticsId(Integer logisticsId) {
		this.logisticsId = logisticsId;
	}

	public List<Integer> getLogisticsIdList() {
		return logisticsIdList;
	}

	public void setLogisticsIdList(List<Integer> logisticsIdList) {
		this.logisticsIdList = logisticsIdList;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getLogisticsCode() {
		return logisticsCode;
	}

	public void setLogisticsCode(String logisticsCode) {
		this.logisticsCode = logisticsCode;
	}

	public Integer getOpenflag() {
		return openflag;
	}

	public void setOpenflag(Integer openflag) {
		this.openflag = openflag;
	}
}