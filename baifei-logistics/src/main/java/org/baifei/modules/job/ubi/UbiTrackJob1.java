package org.baifei.modules.job.ubi;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.baifei.common.util.ConstantConfig;
import org.baifei.common.util.DateUtils;
import org.baifei.modules.entity.Needtotracknumber123;
import org.baifei.modules.entity.Order0;
import org.baifei.modules.entity.Orderitem0;
import org.baifei.modules.entity.Orderplus0;
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
public class UbiTrackJob1 implements Job {
	@Autowired
	private INeedtotracknumber123Service needtotracknumber123Service;
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


		LambdaQueryWrapper<Needtotracknumber123> trackQuery = new LambdaQueryWrapper<Needtotracknumber123>();
		trackQuery.eq(Needtotracknumber123::getErrorflag,0);
		trackQuery.last("limit 100");
		List<Needtotracknumber123> list = needtotracknumber123Service.list(trackQuery);
		for(Needtotracknumber123 needtotracknumber123:list){
			//开始交运
			needtotracknumber123.setErrorflag(1);
			needtotracknumber123.setErrordescr("正在获取订单号...");
			needtotracknumber123Service.saveOrUpdate(needtotracknumber123);
			//订单
			LambdaQueryWrapper<Order0> orderQuery = new LambdaQueryWrapper<Order0>();
			orderQuery.eq(Order0::getId,needtotracknumber123.getOrderid());
			Order0 order0 = order0Service.getOne(orderQuery);
			if(order0==null){
				needtotracknumber123.setErrorflag(98);
				needtotracknumber123.setErrordescr("订单不存在!");
				needtotracknumber123Service.saveOrUpdate(needtotracknumber123);
				return;
			}
			//订单商品详情
			List<Orderitem0> orderItem0 = orderitem0Service.getItemsProduct(needtotracknumber123.getOrderid());
			if(orderItem0==null || orderItem0.size()<=0){
				needtotracknumber123.setErrorflag(98);
				needtotracknumber123.setErrordescr("订单商品详情不存在!");
				needtotracknumber123Service.saveOrUpdate(needtotracknumber123);
				return;
			}

			//订单买家信息
			LambdaQueryWrapper<Orderplus0> orderplus0Query = new LambdaQueryWrapper<Orderplus0>();
			orderplus0Query.eq(Orderplus0::getOrderid,needtotracknumber123.getOrderid());
			Orderplus0 orderplus0 = orderplus0Service.getOne(orderplus0Query);
			if(orderplus0==null ){
				needtotracknumber123.setErrorflag(98);
				needtotracknumber123.setErrordescr("订单买家信息不存在!");
				needtotracknumber123Service.saveOrUpdate(needtotracknumber123);
				return;
			}

			//货运方式
			FindMyLogisticsChannel findMyLogisticsChannel = new FindMyLogisticsChannel();
			findMyLogisticsChannel.setCompanyId(needtotracknumber123.getCompanyid());
			findMyLogisticsChannel.setMyLogisticsChannelId(needtotracknumber123.getMylogisticschannelid());

			ResulstCodeWeb<List<DbMylogisticschannel>> resultMylogisticschannel = restTemplateUtil.getDataListForPost(DbMylogisticschannel.class,findMyLogisticsChannel,"", ConstantConfig.LogisticsUrl+"/findMylogisticsChannel");
			DbMylogisticschannel mylogisticschannel = null;
			if (resultMylogisticschannel.getAck()!=1000 && resultMylogisticschannel.getData()!=null){
				mylogisticschannel = resultMylogisticschannel.getData().get(0);
			}else{
				needtotracknumber123.setErrorflag(98);
				needtotracknumber123.setErrordescr("货运方式不存在!");
				needtotracknumber123Service.saveOrUpdate(needtotracknumber123);
				return;
			}
			//物流渠道
			FindLogisticsChannel findLogisticsChannel = new FindLogisticsChannel();
			findLogisticsChannel.setLogisticsChannelId(needtotracknumber123.getLogisticschannelid());
			findLogisticsChannel.setLogisticsId(needtotracknumber123.getLogisticsid());

			ResulstCodeWeb<List<DbLogisticschannel>> resultLogisticschannel = restTemplateUtil.getDataListForPost(DbLogisticschannel.class,findLogisticsChannel,"",ConstantConfig.LogisticsUrl+"/findLogisticsChannel");
			DbLogisticschannel logisticschannel = null;
			if (resultLogisticschannel.getAck()!=1000 && resultLogisticschannel.getData()!=null){
				logisticschannel = resultLogisticschannel.getData().get(0);
			}else{
				needtotracknumber123.setErrorflag(98);
				needtotracknumber123.setErrordescr("物流渠道不存在!");
				needtotracknumber123Service.saveOrUpdate(needtotracknumber123);
				return;
			}

			//寄件信息 DbMylogisticschannel中 returnAddress1
			FindMyLogisticsReturnAddress findMyLogisticsReturnAddress = new FindMyLogisticsReturnAddress();
			findMyLogisticsReturnAddress.setCompanyId(needtotracknumber123.getCompanyid());
			findMyLogisticsReturnAddress.setLogisticsId(needtotracknumber123.getLogisticsid());
			findMyLogisticsReturnAddress.setMyLogisticsReturnAddressId(mylogisticschannel.getReturnAddress1());

			ResulstCodeWeb<List<DbMylogisticsreturnaddress>> resultDbMylogisticsreturnaddress = restTemplateUtil.getDataListForPost(DbMylogisticsreturnaddress.class,findMyLogisticsReturnAddress,"",ConstantConfig.LogisticsUrl+"/findMylogisticsReturnAddress");
			DbMylogisticsreturnaddress mylogisticsreturnaddress = null;
			if (resultDbMylogisticsreturnaddress.getAck()!=1000 && resultDbMylogisticsreturnaddress.getData()!=null){
				mylogisticsreturnaddress = resultDbMylogisticsreturnaddress.getData().get(0);
			}else{
				needtotracknumber123.setErrorflag(98);
				needtotracknumber123.setErrordescr("寄件信息不存在!");
				needtotracknumber123Service.saveOrUpdate(needtotracknumber123);
				return;
			}
			//交运属性
			PropertyJson propertyJson = null;
			if(needtotracknumber123.getPropertyjson()!=null){
				propertyJson = JSONObject.parseObject(needtotracknumber123.getPropertyjson(), PropertyJson.class);
			}
			RequestJson requestJson = null;
			if(needtotracknumber123.getRequestjson()!=null){
				requestJson = JSONObject.parseObject(needtotracknumber123.getRequestjson(), RequestJson.class);
			}
			DbMylogisticsaccount mylogisticsaccount = null;
			if(requestJson!=null) {
				FindMylogisticsAccount findMylogisticsAccount = new FindMylogisticsAccount();
				findMylogisticsAccount.setCompanyId(needtotracknumber123.getCompanyid());
				findMylogisticsAccount.setMyLogisticsAccountId(requestJson.getAccountId());

				ResulstCodeWeb<List<DbMylogisticsaccount>> resultDbMylogisticsaccount = restTemplateUtil.getDataListForPost(DbMylogisticsaccount.class, findMylogisticsAccount, "", ConstantConfig.LogisticsUrl + "/findMylogisticsAccount");

				if (resultDbMylogisticsaccount.getAck() != 1000 && resultDbMylogisticsaccount.getData() != null) {
					mylogisticsaccount = resultDbMylogisticsaccount.getData().get(0);
				} else {
					needtotracknumber123.setErrorflag(98);
					needtotracknumber123.setErrordescr("物流账号不存在!");
					needtotracknumber123Service.saveOrUpdate(needtotracknumber123);
					return;
				}
			}

			TrackModel trackModel = new TrackModel();
			trackModel.setOrder0(order0);
			trackModel.setOrderitem0(orderItem0);
			trackModel.setOrderplus0(orderplus0);
			trackModel.setDbMylogisticschannel(mylogisticschannel);
			trackModel.setDbLogisticschannel(logisticschannel);
			trackModel.setDbMylogisticsreturnaddress(mylogisticsreturnaddress);
			trackModel.setMylogisticsaccount(mylogisticsaccount);

			TrackResultModel trackResultModel = needtotracknumber123Service.runStep1(trackModel);
			needtotracknumber123.setErrorflag(trackResultModel.getFlag());
			needtotracknumber123.setErrordescr(trackResultModel.getDescr());
			if(trackResultModel.getFlag()==3){
				needtotracknumber123.setTracknumber(trackResultModel.getTrackNumber());
				order0.setTracknumber(trackResultModel.getTrackNumber());
				order0.setSynclogisticsstatus(3);
				order0.setIssynclogisticsdescr("获取订单成功！");
				order0Service.saveOrUpdate(order0);
			}

			needtotracknumber123Service.saveOrUpdate(needtotracknumber123);

		}

		log.info(String.format(" Ubi获取订单号step1 org.baifei.modules.job.ubi.UbiTrackJob1 ! 执行数:"+list.size()+" 时间:" + DateUtils.getTimestamp()));
	}
}
