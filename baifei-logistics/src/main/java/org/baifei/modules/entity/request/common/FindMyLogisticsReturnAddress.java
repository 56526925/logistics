package org.baifei.modules.entity.request.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
/**
 *
 * @Description:  mabangorderbase.db_mylogistics_returnaddress
 * @Author:       baihailiang
 * @CreateDate:   2020-06-05 10:25:46
 * @Version:      V1.0
 *
 */
@ApiModel(value="FindMyLogisticsReturnAddressId",description="")
public class FindMyLogisticsReturnAddress {


	@ApiModelProperty(name = "myLogisticsReturnAddressId" , value = "地址id")
	private Integer myLogisticsReturnAddressId;
	@ApiModelProperty(value="",name="myLogisticsReturnAddressIdList")
	private List<Integer> myLogisticsReturnAddressIdList;
	@ApiModelProperty(name = "companyId" , value = "公司编号")
	private Integer companyId;
	@ApiModelProperty(name = "logisticsId" , value = "物流公司id")
	private Integer logisticsId;
	@ApiModelProperty(name = "openflag" , value = "1开启2关闭")
	private Integer openflag;

	public Integer getMyLogisticsReturnAddressId() {
		return myLogisticsReturnAddressId;
	}

	public void setMyLogisticsReturnAddressId(Integer myLogisticsReturnAddressId) {
		this.myLogisticsReturnAddressId = myLogisticsReturnAddressId;
	}

	public List<Integer> getMyLogisticsReturnAddressIdList() {
		return myLogisticsReturnAddressIdList;
	}

	public void setMyLogisticsReturnAddressIdList(List<Integer> myLogisticsReturnAddressIdList) {
		this.myLogisticsReturnAddressIdList = myLogisticsReturnAddressIdList;
	}

	public Integer getLogisticsId() {
		return logisticsId;
	}

	public void setLogisticsId(Integer logisticsId) {
		this.logisticsId = logisticsId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getOpenflag() {
		return openflag;
	}

	public void setOpenflag(Integer openflag) {
		this.openflag = openflag;
	}
}