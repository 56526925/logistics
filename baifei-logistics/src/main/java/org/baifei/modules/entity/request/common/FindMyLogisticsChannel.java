package org.baifei.modules.entity.request.common;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="FindMyLogisticsChannel",description="")
public class FindMyLogisticsChannel {
	@ApiModelProperty(value="公司编号",name="companyId")
    private Integer companyId;
	@ApiModelProperty(value="货运方式id",name="myLogisticsChannelId")
	private Integer myLogisticsChannelId;
	@ApiModelProperty(value="渠道id集合",name="myLogisticsChannelIdList")
	private List<Integer> myLogisticsChannelIdList;
	@ApiModelProperty(value="物流公司编号",name="logisticsId")
	private Integer logisticsId;
	@ApiModelProperty(value="物流公司渠道编号",name="logisticsChannelId")
	private Integer logisticsChannelId;
	@ApiModelProperty(value="授权状态:1授权成功,2授权失败,0未授权,3无需授权",name="tokenFlagList")
	private Integer activeFlags;
	@ApiModelProperty(value="启动标志1启动2停用",name="openFlags")
	private Integer openFlags;
	

	public Integer getActiveFlags() {
		return activeFlags;
	}
	public void setActiveFlags(Integer activeFlags) {
		this.activeFlags = activeFlags;
	}
	public Integer getOpenFlags() {
		return openFlags;
	}
	public void setOpenFlags(Integer openFlags) {
		this.openFlags = openFlags;
	}
	public List<Integer> getMyLogisticsChannelIdList() {
		return myLogisticsChannelIdList;
	}
	public void setMyLogisticsChannelIdList(List<Integer> myLogisticsChannelIdList) { this.myLogisticsChannelIdList = myLogisticsChannelIdList; }
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getMyLogisticsChannelId() {
		return myLogisticsChannelId;
	}
	public void setMyLogisticsChannelId(Integer myLogisticsChannelId) {
		this.myLogisticsChannelId = myLogisticsChannelId;
	}
	public Integer getLogisticsId() { return logisticsId; }
	public void setLogisticsId(Integer logisticsId) { this.logisticsId = logisticsId; }
	public Integer getLogisticsChannelId() { return logisticsChannelId; }
	public void setLogisticsChannelId(Integer logisticsChannelId) { this.logisticsChannelId = logisticsChannelId; }
}
