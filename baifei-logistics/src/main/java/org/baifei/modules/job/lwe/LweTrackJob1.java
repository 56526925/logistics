package org.baifei.modules.job.lwe;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.baifei.common.util.ConstantConfig;
import org.baifei.common.util.DateUtils;
import org.baifei.modules.entity.Needtotracknumber120;
import org.baifei.modules.entity.Order0;
import org.baifei.modules.entity.Orderitem0;
import org.baifei.modules.entity.Orderplus0;
import org.baifei.modules.entity.request.common.*;
import org.baifei.modules.entity.response.common.DbLogisticschannel;
import org.baifei.modules.entity.response.common.DbMylogisticsaccount;
import org.baifei.modules.entity.response.common.DbMylogisticschannel;
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
public class LweTrackJob1 implements Job {
	@Autowired
	private INeedtotracknumber120Service needtotracknumber120Service;
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


		LambdaQueryWrapper<Needtotracknumber120> trackQuery = new LambdaQueryWrapper<Needtotracknumber120>();
		trackQuery.eq(Needtotracknumber120::getErrorflag,0);
		trackQuery.last("limit 100");
		List<Needtotracknumber120> list = needtotracknumber120Service.list(trackQuery);
		for(Needtotracknumber120 needtotracknumber120:list){
			//开始交运
			needtotracknumber120.setErrorflag(1);
			needtotracknumber120.setErrordescr("正在获取订单号...");
			needtotracknumber120Service.saveOrUpdate(needtotracknumber120);
			//订单
			LambdaQueryWrapper<Order0> orderQuery = new LambdaQueryWrapper<Order0>();
			orderQuery.eq(Order0::getId,needtotracknumber120.getOrderid());
			Order0 order0 = order0Service.getOne(orderQuery);
			if(order0==null){
				needtotracknumber120.setErrorflag(98);
				needtotracknumber120.setErrordescr("订单不存在!");
				needtotracknumber120Service.saveOrUpdate(needtotracknumber120);
				return;
			}
			//订单商品详情
			List<Orderitem0> orderItem0 = orderitem0Service.getItemsProduct(needtotracknumber120.getOrderid());
			if(orderItem0==null || orderItem0.size()<=0){
				needtotracknumber120.setErrorflag(98);
				needtotracknumber120.setErrordescr("订单商品详情不存在!");
				needtotracknumber120Service.saveOrUpdate(needtotracknumber120);
				return;
			}

			//订单买家信息
			LambdaQueryWrapper<Orderplus0> orderplus0Query = new LambdaQueryWrapper<Orderplus0>();
			orderplus0Query.eq(Orderplus0::getOrderid,needtotracknumber120.getOrderid());
			Orderplus0 orderplus0 = orderplus0Service.getOne(orderplus0Query);
			if(orderplus0==null ){
				needtotracknumber120.setErrorflag(98);
				needtotracknumber120.setErrordescr("订单买家信息不存在!");
				needtotracknumber120Service.saveOrUpdate(needtotracknumber120);
				return;
			}

			//货运方式
			FindMyLogisticsChannel findMyLogisticsChannel = new FindMyLogisticsChannel();
			findMyLogisticsChannel.setCompanyId(needtotracknumber120.getCompanyid());
			findMyLogisticsChannel.setMyLogisticsChannelId(needtotracknumber120.getMylogisticschannelid());

			ResulstCodeWeb<List<DbMylogisticschannel>> resultMylogisticschannel = restTemplateUtil.getDataListForPost(DbMylogisticschannel.class,findMyLogisticsChannel,"", ConstantConfig.LogisticsUrl+"/findMylogisticsChannel");
			DbMylogisticschannel mylogisticschannel = null;
			if (resultMylogisticschannel.getAck()!=1000 && resultMylogisticschannel.getData()!=null){
				mylogisticschannel = resultMylogisticschannel.getData().get(0);
			}else{
				needtotracknumber120.setErrorflag(98);
				needtotracknumber120.setErrordescr("货运方式不存在!");
				needtotracknumber120Service.saveOrUpdate(needtotracknumber120);
				return;
			}
			//物流渠道
			FindLogisticsChannel findLogisticsChannel = new FindLogisticsChannel();
			findLogisticsChannel.setLogisticsChannelId(needtotracknumber120.getLogisticschannelid());
			findLogisticsChannel.setLogisticsId(needtotracknumber120.getLogisticsid());

			ResulstCodeWeb<List<DbLogisticschannel>> resultLogisticschannel = restTemplateUtil.getDataListForPost(DbLogisticschannel.class,findLogisticsChannel,"",ConstantConfig.LogisticsUrl+"/findLogisticsChannel");
			DbLogisticschannel logisticschannel = null;
			if (resultLogisticschannel.getAck()!=1000 && resultLogisticschannel.getData()!=null){
				logisticschannel = resultLogisticschannel.getData().get(0);
			}else{
				needtotracknumber120.setErrorflag(98);
				needtotracknumber120.setErrordescr("物流渠道不存在!");
				needtotracknumber120Service.saveOrUpdate(needtotracknumber120);
				return;
			}

			//交运属性
			PropertyJson propertyJson = null;
			if(needtotracknumber120.getPropertyjson()!=null){
				propertyJson = JSONObject.parseObject(needtotracknumber120.getPropertyjson(), PropertyJson.class);
			}
			RequestJson requestJson = null;
			if(needtotracknumber120.getRequestjson()!=null){
				requestJson = JSONObject.parseObject(needtotracknumber120.getRequestjson(), RequestJson.class);
			}
			//物流账号
			DbMylogisticsaccount mylogisticsaccount = null;
			if(requestJson!=null) {
				FindMylogisticsAccount findMylogisticsAccount = new FindMylogisticsAccount();
				findMylogisticsAccount.setCompanyId(needtotracknumber120.getCompanyid());
				findMylogisticsAccount.setMyLogisticsAccountId(requestJson.getAccountId());

				ResulstCodeWeb<List<DbMylogisticsaccount>> resultDbMylogisticsaccount = restTemplateUtil.getDataListForPost(DbMylogisticsaccount.class, findMylogisticsAccount, "", ConstantConfig.LogisticsUrl + "/findMylogisticsAccount");

				if (resultDbMylogisticsaccount.getAck() != 1000 && resultDbMylogisticsaccount.getData() != null) {
					mylogisticsaccount = resultDbMylogisticsaccount.getData().get(0);
				} else {
					needtotracknumber120.setErrorflag(98);
					needtotracknumber120.setErrordescr("物流账号不存在!");
					needtotracknumber120Service.saveOrUpdate(needtotracknumber120);
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

			TrackResultModel trackResultModel = needtotracknumber120Service.runStep1(trackModel);
			needtotracknumber120.setErrorflag(trackResultModel.getFlag());
			needtotracknumber120.setErrordescr(trackResultModel.getDescr());
			if(trackResultModel.getFlag()==3 ){
				needtotracknumber120.setTracknumber(trackResultModel.getTrackNumber());
				order0.setTracknumber(trackResultModel.getTrackNumber());
				order0.setSynclogisticsstatus(3);
				order0.setIssynclogisticsdescr("获取订单成功！");
				order0Service.saveOrUpdate(order0);
			}

			needtotracknumber120Service.saveOrUpdate(needtotracknumber120);

		}

		log.info(String.format(" lwe获取订单号step1 org.baifei.modules.job.lwe.LweTrackJob1 ! 执行数:"+list.size()+" 时间:" + DateUtils.getTimestamp()));
	}
}
