package org.baifei.modules.entity.request.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;
/**
 *
 * @Description:  mabangorderbase.db_mylogistics_account
 * @Author:       baihailiang
 * @CreateDate:   2020-06-05 10:25:45
 * @Version:      V1.0
 *
 */
@ApiModel(value="FindMylogisticsAccount",description="")
public class FindMylogisticsAccount {
	@ApiModelProperty(name = "myLogisticsAccountId" , value = "物流账号id")
	private Integer myLogisticsAccountId;
	@ApiModelProperty(name="myLogisticsAccountIdList", value = "物流账号id集合")
	private List<Integer> myLogisticsAccountIdList;
	@ApiModelProperty(name = "companyId" , value = "企业编号")
	private Integer companyId;
	@ApiModelProperty(name = "shopType" , value = "店铺类型,涉及到")
	private Integer shopType;
	@ApiModelProperty(name = "logisticsId" , value = "物流公司编号")
	private Integer logisticsId;
	@ApiModelProperty(name = "mylogisticsId" , value = "货运方式编号")
	private Integer mylogisticsId;
	@ApiModelProperty(name = "name" , value = "物流账号")
	private String name;
	@ApiModelProperty(name = "nickname" , value = "物流账号别名")
	private String nickname;
	@ApiModelProperty(name = "status" , value = "状态1.开启2.停用")
	private Integer status;


	public Integer getMyLogisticsAccountId() {
		return myLogisticsAccountId;
	}

	public void setMyLogisticsAccountId(Integer myLogisticsAccountId) {
		this.myLogisticsAccountId = myLogisticsAccountId;
	}

	public List<Integer> getMyLogisticsAccountIdList() {
		return myLogisticsAccountIdList;
	}

	public void setMyLogisticsAccountIdList(List<Integer> myLogisticsAccountIdList) {
		this.myLogisticsAccountIdList = myLogisticsAccountIdList;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getShopType() {
		return shopType;
	}

	public void setShopType(Integer shopType) {
		this.shopType = shopType;
	}

	public Integer getLogisticsId() {
		return logisticsId;
	}

	public void setLogisticsId(Integer logisticsId) {
		this.logisticsId = logisticsId;
	}

	public Integer getMylogisticsId() {
		return mylogisticsId;
	}

	public void setMylogisticsId(Integer mylogisticsId) {
		this.mylogisticsId = mylogisticsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}