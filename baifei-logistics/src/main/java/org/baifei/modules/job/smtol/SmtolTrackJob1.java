package org.baifei.modules.job.smtol;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.baifei.common.util.ConstantConfig;
import org.baifei.common.util.DateUtils;
import org.baifei.common.util.SSLClient;
import org.baifei.modules.entity.*;
import org.baifei.modules.entity.request.common.*;
import org.baifei.modules.entity.response.common.*;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.*;
import org.baifei.modules.util.RestTemplateUtil;
import org.baifei.modules.util.ResulstCodeWeb;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.PostgresSequenceMaxValueIncrementer;

import java.util.List;
import java.util.regex.PatternSyntaxException;

/**
 * 示例不带参定时任务
 * 
 * @Author Scott
 */
@Slf4j
public class SmtolTrackJob1 implements Job {
	@Autowired
	private INeedtotracknumber103Service needtotracknumber103Service;
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


		LambdaQueryWrapper<Needtotracknumber103> trackQuery = new LambdaQueryWrapper<Needtotracknumber103>();
		trackQuery.eq(Needtotracknumber103::getErrorflag,0);
		trackQuery.last("limit 100");
		List<Needtotracknumber103> list = needtotracknumber103Service.list(trackQuery);
		for(Needtotracknumber103 needtotracknumber103:list){
			//开始交运
			needtotracknumber103.setErrorflag(1);
			needtotracknumber103.setErrordescr("正在获取订单号...");
			needtotracknumber103Service.saveOrUpdate(needtotracknumber103);
			//订单
			LambdaQueryWrapper<Order0> orderQuery = new LambdaQueryWrapper<Order0>();
			orderQuery.eq(Order0::getId,needtotracknumber103.getOrderid());
			Order0 order0 = order0Service.getOne(orderQuery);
			if(order0==null){
				needtotracknumber103.setErrorflag(98);
				needtotracknumber103.setErrordescr("订单不存在!");
				needtotracknumber103Service.saveOrUpdate(needtotracknumber103);
				return;
			}
			//订单商品详情
			List<Orderitem0> orderItem0 = orderitem0Service.getItemsProduct(needtotracknumber103.getOrderid());
			if(orderItem0==null || orderItem0.size()<=0){
				needtotracknumber103.setErrorflag(98);
				needtotracknumber103.setErrordescr("订单商品详情不存在!");
				needtotracknumber103Service.saveOrUpdate(needtotracknumber103);
				return;
			}

			//订单买家信息
			LambdaQueryWrapper<Orderplus0> orderplus0Query = new LambdaQueryWrapper<Orderplus0>();
			orderplus0Query.eq(Orderplus0::getOrderid,needtotracknumber103.getOrderid());
			Orderplus0 orderplus0 = orderplus0Service.getOne(orderplus0Query);
			if(orderplus0==null ){
				needtotracknumber103.setErrorflag(98);
				needtotracknumber103.setErrordescr("订单买家信息不存在!");
				needtotracknumber103Service.saveOrUpdate(needtotracknumber103);
				return;
			}


			//店铺信息
			FindShop findShop = new FindShop();
			findShop.setShopId(needtotracknumber103.getShopid());
			findShop.setCompanyId(needtotracknumber103.getCompanyid());

			ResulstCodeWeb<List<DbShop>> resultShop = restTemplateUtil.getDataListForPost(DbShop.class,findShop,"", ConstantConfig.LogisticsUrl+"/findShopList");
			DbShop shop = null;
			if (resultShop.getAck()!=1000 && resultShop.getData()!=null){
				shop = resultShop.getData().get(0);
			}else{
				needtotracknumber103.setErrorflag(98);
				needtotracknumber103.setErrordescr("店铺信息不存在!");
				needtotracknumber103Service.saveOrUpdate(needtotracknumber103);
				return;
			}

			//货运方式
			FindMyLogisticsChannel findMyLogisticsChannel = new FindMyLogisticsChannel();
			findMyLogisticsChannel.setCompanyId(needtotracknumber103.getCompanyid());
			findMyLogisticsChannel.setMyLogisticsChannelId(needtotracknumber103.getMylogisticschannelid());

			ResulstCodeWeb<List<DbMylogisticschannel>> resultMylogisticschannel = restTemplateUtil.getDataListForPost(DbMylogisticschannel.class,findMyLogisticsChannel,"",ConstantConfig.LogisticsUrl+"/findMylogisticsChannel");
			DbMylogisticschannel mylogisticschannel = null;
			if (resultMylogisticschannel.getAck()!=1000 && resultMylogisticschannel.getData()!=null){
				mylogisticschannel = resultMylogisticschannel.getData().get(0);
			}else{
				needtotracknumber103.setErrorflag(98);
				needtotracknumber103.setErrordescr("货运方式不存在!");
				needtotracknumber103Service.saveOrUpdate(needtotracknumber103);
				return;
			}
			//物流渠道
			FindLogisticsChannel findLogisticsChannel = new FindLogisticsChannel();
			findLogisticsChannel.setLogisticsChannelId(needtotracknumber103.getLogisticschannelid());
			findLogisticsChannel.setLogisticsId(needtotracknumber103.getLogisticsid());

			ResulstCodeWeb<List<DbLogisticschannel>> resultLogisticschannel = restTemplateUtil.getDataListForPost(DbLogisticschannel.class,findLogisticsChannel,"",ConstantConfig.LogisticsUrl+"/findLogisticsChannel");
			DbLogisticschannel logisticschannel = null;
			if (resultLogisticschannel.getAck()!=1000 && resultLogisticschannel.getData()!=null){
				logisticschannel = resultLogisticschannel.getData().get(0);
			}else{
				needtotracknumber103.setErrorflag(98);
				needtotracknumber103.setErrordescr("物流渠道不存在!");
				needtotracknumber103Service.saveOrUpdate(needtotracknumber103);
				return;
			}


			//交运属性
			PropertyJson propertyJson = null;
			if(needtotracknumber103.getPropertyjson()!=null){
				propertyJson = JSONObject.parseObject(needtotracknumber103.getPropertyjson(), PropertyJson.class);
			}

			TrackModel trackModel = new TrackModel();
			trackModel.setShop(shop);
			trackModel.setOrder0(order0);
			trackModel.setOrderitem0(orderItem0);
			trackModel.setOrderplus0(orderplus0);
			trackModel.setDbMylogisticschannel(mylogisticschannel);
			trackModel.setPropertyJson(propertyJson);
			trackModel.setDbLogisticschannel(logisticschannel);

			TrackResultModel trackResultModel = needtotracknumber103Service.runStep1(trackModel);
			needtotracknumber103.setErrorflag(trackResultModel.getFlag());
			needtotracknumber103.setErrordescr(trackResultModel.getDescr());
			if(trackResultModel.getFlag()==2 || trackResultModel.getFlag()==3 ){
				needtotracknumber103.setTracknumber(trackResultModel.getTrackNumber());
				order0.setTracknumber(trackResultModel.getTrackNumber());
				order0.setSynclogisticsstatus(3);
				order0.setIssynclogisticsdescr("获取订单成功！");
				order0Service.saveOrUpdate(order0);
			}

			needtotracknumber103Service.saveOrUpdate(needtotracknumber103);

		}

		log.info(String.format(" smtol获取订单号step1 org.baifei.modules.job.smtol.SmtolTrackJob1 ! 执行数:"+list.size()+" 时间:" + DateUtils.getTimestamp()));
	}

}
