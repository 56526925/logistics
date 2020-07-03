package org.baifei.modules.job.ft;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.baifei.common.util.ConstantConfig;
import org.baifei.common.util.DateUtils;
import org.baifei.modules.entity.*;
import org.baifei.modules.entity.request.common.*;
import org.baifei.modules.entity.response.common.DbLogisticschannel;
import org.baifei.modules.entity.response.common.DbMylogisticsaccount;
import org.baifei.modules.entity.response.common.DbMylogisticschannel;
import org.baifei.modules.entity.response.common.DbMylogisticsreturnaddress;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.*;
import org.baifei.modules.util.RestTemplateUtil;
import org.baifei.modules.util.ResulstCodeWeb;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 示例不带参定时任务
 * 
 * @Author Scott
 */
@Slf4j
public class FtTrackJob1 implements Job {
	@Autowired
	private INeedtotracknumber115Service needtotracknumber115Service;
	@Autowired
	private IOrder0Service order0Service;
	@Autowired
	private IOrderitem0Service orderitem0Service;
	@Autowired
	private IOrdercurrency0Service ordercurrency0Service;
	@Autowired
	private IOrderplus0Service orderplus0Service;
	@Autowired
	private RestTemplateUtil restTemplateUtil;

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {


		LambdaQueryWrapper<Needtotracknumber115> trackQuery = new LambdaQueryWrapper<Needtotracknumber115>();
		trackQuery.eq(Needtotracknumber115::getErrorflag,0);
		trackQuery.last("limit 100");
		List<Needtotracknumber115> list = needtotracknumber115Service.list(trackQuery);
		for(Needtotracknumber115 needtotracknumber115:list){
			//开始交运
			needtotracknumber115.setErrorflag(1);
			needtotracknumber115.setErrordescr("正在获取订单号...");
			needtotracknumber115Service.saveOrUpdate(needtotracknumber115);
			//订单
			LambdaQueryWrapper<Order0> orderQuery = new LambdaQueryWrapper<Order0>();
			orderQuery.eq(Order0::getId,needtotracknumber115.getOrderid());
			Order0 order0 = order0Service.getOne(orderQuery);
			if(order0==null){
				needtotracknumber115.setErrorflag(98);
				needtotracknumber115.setErrordescr("订单不存在!");
				needtotracknumber115Service.saveOrUpdate(needtotracknumber115);
				return;
			}
			//订单商品详情
			List<Orderitem0> orderItem0 = orderitem0Service.getItemsProduct(needtotracknumber115.getOrderid());
			if(orderItem0==null || orderItem0.size()<=0){
				needtotracknumber115.setErrorflag(98);
				needtotracknumber115.setErrordescr("订单商品详情不存在!");
				needtotracknumber115Service.saveOrUpdate(needtotracknumber115);
				return;
			}

			//订单买家信息
			LambdaQueryWrapper<Orderplus0> orderplus0Query = new LambdaQueryWrapper<Orderplus0>();
			orderplus0Query.eq(Orderplus0::getOrderid,needtotracknumber115.getOrderid());
			Orderplus0 orderplus0 = orderplus0Service.getOne(orderplus0Query);
			if(orderplus0==null ){
				needtotracknumber115.setErrorflag(98);
				needtotracknumber115.setErrordescr("订单买家信息不存在!");
				needtotracknumber115Service.saveOrUpdate(needtotracknumber115);
				return;
			}

			//货运方式
			FindMyLogisticsChannel findMyLogisticsChannel = new FindMyLogisticsChannel();
			findMyLogisticsChannel.setCompanyId(needtotracknumber115.getCompanyid());
			findMyLogisticsChannel.setMyLogisticsChannelId(needtotracknumber115.getMylogisticschannelid());

			ResulstCodeWeb<List<DbMylogisticschannel>> resultMylogisticschannel = restTemplateUtil.getDataListForPost(DbMylogisticschannel.class,findMyLogisticsChannel,"", ConstantConfig.LogisticsUrl+"/findMylogisticsChannel");
			DbMylogisticschannel mylogisticschannel = null;
			if (resultMylogisticschannel.getAck()!=1000 && resultMylogisticschannel.getData()!=null){
				mylogisticschannel = resultMylogisticschannel.getData().get(0);
			}else{
				needtotracknumber115.setErrorflag(98);
				needtotracknumber115.setErrordescr("货运方式不存在!");
				needtotracknumber115Service.saveOrUpdate(needtotracknumber115);
				return;
			}
			//物流渠道
			FindLogisticsChannel findLogisticsChannel = new FindLogisticsChannel();
			findLogisticsChannel.setLogisticsChannelId(needtotracknumber115.getLogisticschannelid());
			findLogisticsChannel.setLogisticsId(needtotracknumber115.getLogisticsid());

			ResulstCodeWeb<List<DbLogisticschannel>> resultLogisticschannel = restTemplateUtil.getDataListForPost(DbLogisticschannel.class,findLogisticsChannel,"",ConstantConfig.LogisticsUrl+"/findLogisticsChannel");
			DbLogisticschannel logisticschannel = null;
			if (resultLogisticschannel.getAck()!=1000 && resultLogisticschannel.getData()!=null){
				logisticschannel = resultLogisticschannel.getData().get(0);
			}else{
				needtotracknumber115.setErrorflag(98);
				needtotracknumber115.setErrordescr("物流渠道不存在!");
				needtotracknumber115Service.saveOrUpdate(needtotracknumber115);
				return;
			}

			//交运属性
			PropertyJson propertyJson = null;
			if(needtotracknumber115.getPropertyjson()!=null){
				propertyJson = JSONObject.parseObject(needtotracknumber115.getPropertyjson(), PropertyJson.class);
			}
			RequestJson requestJson = null;
			if(needtotracknumber115.getRequestjson()!=null){
				requestJson = JSONObject.parseObject(needtotracknumber115.getRequestjson(), RequestJson.class);
			}
			//物流账号
			DbMylogisticsaccount mylogisticsaccount = null;
			if(requestJson!=null) {
				FindMylogisticsAccount findMylogisticsAccount = new FindMylogisticsAccount();
				findMylogisticsAccount.setCompanyId(needtotracknumber115.getCompanyid());
				findMylogisticsAccount.setMyLogisticsAccountId(requestJson.getAccountId());

				ResulstCodeWeb<List<DbMylogisticsaccount>> resultDbMylogisticsaccount = restTemplateUtil.getDataListForPost(DbMylogisticsaccount.class, findMylogisticsAccount, "", ConstantConfig.LogisticsUrl + "/findMylogisticsAccount");

				if (resultDbMylogisticsaccount.getAck() != 1000 && resultDbMylogisticsaccount.getData() != null) {
					mylogisticsaccount = resultDbMylogisticsaccount.getData().get(0);
				} else {
					needtotracknumber115.setErrorflag(98);
					needtotracknumber115.setErrordescr("物流账号不存在!");
					needtotracknumber115Service.saveOrUpdate(needtotracknumber115);
					return;
				}
			}

			TrackModel trackModel = new TrackModel();
			trackModel.setOrder0(order0);
			trackModel.setOrderitem0(orderItem0);
			trackModel.setOrderplus0(orderplus0);
			trackModel.setDbMylogisticschannel(mylogisticschannel);
			trackModel.setDbLogisticschannel(logisticschannel);
			trackModel.setMylogisticsaccount(mylogisticsaccount);
			trackModel.setPropertyJson(propertyJson);

			TrackResultModel trackResultModel = needtotracknumber115Service.runStep1(trackModel);
			needtotracknumber115.setErrorflag(trackResultModel.getFlag());
			needtotracknumber115.setErrordescr(trackResultModel.getDescr());
			if(trackResultModel.getFlag()==3 ){
				needtotracknumber115.setTracknumber(trackResultModel.getTrackNumber());
				order0.setTracknumber(trackResultModel.getTrackNumber());
				order0.setSynclogisticsstatus(3);
				order0.setIssynclogisticsdescr("获取订单成功！");
				order0Service.saveOrUpdate(order0);
			}

			needtotracknumber115Service.saveOrUpdate(needtotracknumber115);

		}

		log.info(String.format(" 飞特获取订单号step1 org.baifei.modules.job.ft.FtTrackJob1 ! 执行数:"+list.size()+" 时间:" + DateUtils.getTimestamp()));
	}
}
