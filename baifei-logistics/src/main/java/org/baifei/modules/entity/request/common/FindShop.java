package org.baifei.modules.entity.request.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;
/**
 *
 * @Description:  mabang.db_shop
 * @Author:       baihailiang
 * @CreateDate:   2020-06-05 14:49:03
 * @Version:      V1.0
 *
 */
@ApiModel(value="FindShop",description="")
public class FindShop  {
	@ApiModelProperty(name = "shopId" , value = "店铺id")
	private Integer shopId;
	@ApiModelProperty(name="shopIdList",value="店铺列表")
	private List<Integer> idList;
	@ApiModelProperty(name = "companyId" , value = "企业编号")
	private Integer companyId;
	@ApiModelProperty(name = "platformId" , value = "平台id")
	private Integer platformId;
	@ApiModelProperty(name = "name" , value = "店铺别名")
	private String name;
	@ApiModelProperty(name = "source" , value = "店铺资源:1,2,3,4,5,6,7")
	private String source;


	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Integer platformId) {
		this.platformId = platformId;
	}
}