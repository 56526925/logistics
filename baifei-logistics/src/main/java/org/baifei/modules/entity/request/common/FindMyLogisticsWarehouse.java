package org.baifei.modules.entity.request.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;
/**
 *
 * @Description:  mabangorderbase.db_mylogistics_warehouse
 * @Author:       baihailiang
 * @CreateDate:   2020-06-05 10:25:46
 * @Version:      V1.0
 *
 */
@ApiModel(value="FindMyLogisticsWarehouse",description="")
public class FindMyLogisticsWarehouse  {

	@ApiModelProperty(name = "myLogisticsWarehouseId" , value = "")
	private Integer myLogisticsWarehouseId;
	@ApiModelProperty(value="",name="myLogisticsWarehouseIdList")
	private List<Integer> myLogisticsWarehouseIdList;
	@ApiModelProperty(name = "myLogisticsWarehouseCode" , value = "仓库编号")
	private String myLogisticsWarehouseCode;
	@ApiModelProperty(name = "companyId" , value = "公司编号")
	private Integer companyId;
	@ApiModelProperty(name = "logisticsId" , value = "公用物流公司编号")
	private Integer logisticsId;
	@ApiModelProperty(name = "myLogisticsId" , value = "我的物流公司编号")
	private Integer myLogisticsId;
	@ApiModelProperty(name = "status" , value = "1开启2关闭")
	private Integer status;

	public Integer getMyLogisticsWarehouseId() {
		return myLogisticsWarehouseId;
	}

	public void setMyLogisticsWarehouseId(Integer myLogisticsWarehouseId) {
		this.myLogisticsWarehouseId = myLogisticsWarehouseId;
	}

	public List<Integer> getMyLogisticsWarehouseIdList() {
		return myLogisticsWarehouseIdList;
	}

	public void setMyLogisticsWarehouseIdList(List<Integer> myLogisticsWarehouseIdList) {
		this.myLogisticsWarehouseIdList = myLogisticsWarehouseIdList;
	}

	public String getMyLogisticsWarehouseCode() {
		return myLogisticsWarehouseCode;
	}

	public void setMyLogisticsWarehouseCode(String myLogisticsWarehouseCode) {
		this.myLogisticsWarehouseCode = myLogisticsWarehouseCode;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getLogisticsId() {
		return logisticsId;
	}

	public void setLogisticsId(Integer logisticsId) {
		this.logisticsId = logisticsId;
	}

	public Integer getMyLogisticsId() {
		return myLogisticsId;
	}

	public void setMyLogisticsId(Integer myLogisticsId) {
		this.myLogisticsId = myLogisticsId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}