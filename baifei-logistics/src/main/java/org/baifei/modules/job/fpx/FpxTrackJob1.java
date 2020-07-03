package org.baifei.modules.job.fpx;

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

import java.util.List;

/**
 * 示例不带参定时任务
 * 
 * @Author Scott
 */
@Slf4j
public class FpxTrackJob1 implements Job {
	@Autowired
	private INeedtotracknumber104Service needtotracknumber104Service;
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


		LambdaQueryWrapper<Needtotracknumber104> trackQuery = new LambdaQueryWrapper<Needtotracknumber104>();
		trackQuery.eq(Needtotracknumber104::getErrorflag,0);
		trackQuery.last("limit 100");
		List<Needtotracknumber104> list = needtotracknumber104Service.list(trackQuery);
		for(Needtotracknumber104 needtotracknumber104:list){
			//开始交运
			needtotracknumber104.setErrorflag(1);
			needtotracknumber104.setErrordescr("正在获取订单号...");
			needtotracknumber104Service.saveOrUpdate(needtotracknumber104);
			//订单
			LambdaQueryWrapper<Order0> orderQuery = new LambdaQueryWrapper<Order0>();
			orderQuery.eq(Order0::getId,needtotracknumber104.getOrderid());
			Order0 order0 = order0Service.getOne(orderQuery);
			if(order0==null){
				needtotracknumber104.setErrorflag(98);
				needtotracknumber104.setErrordescr("订单不存在!");
				needtotracknumber104Service.saveOrUpdate(needtotracknumber104);
				return;
			}
			//订单商品详情
			List<Orderitem0> orderItem0 = orderitem0Service.getItemsProduct(needtotracknumber104.getOrderid());
			if(orderItem0==null || orderItem0.size()<=0){
				needtotracknumber104.setErrorflag(98);
				needtotracknumber104.setErrordescr("订单商品详情不存在!");
				needtotracknumber104Service.saveOrUpdate(needtotracknumber104);
				return;
			}

			//订单买家信息
			LambdaQueryWrapper<Orderplus0> orderplus0Query = new LambdaQueryWrapper<Orderplus0>();
			orderplus0Query.eq(Orderplus0::getOrderid,needtotracknumber104.getOrderid());
			Orderplus0 orderplus0 = orderplus0Service.getOne(orderplus0Query);
			if(orderplus0==null ){
				needtotracknumber104.setErrorflag(98);
				needtotracknumber104.setErrordescr("订单买家信息不存在!");
				needtotracknumber104Service.saveOrUpdate(needtotracknumber104);
				return;
			}

			//物流渠道
			FindLogisticsChannel findLogisticsChannel = new FindLogisticsChannel();
			findLogisticsChannel.setLogisticsChannelId(needtotracknumber104.getLogisticschannelid());
			findLogisticsChannel.setLogisticsId(needtotracknumber104.getLogisticsid());

			ResulstCodeWeb<List<DbLogisticschannel>> resultLogisticschannel = restTemplateUtil.getDataListForPost(DbLogisticschannel.class,findLogisticsChannel,"", ConstantConfig.LogisticsUrl+"/findLogisticsChannel");
			DbLogisticschannel logisticschannel = null;
			if (resultLogisticschannel.getAck()!=1000 && resultLogisticschannel.getData()!=null){
				logisticschannel = resultLogisticschannel.getData().get(0);
			}else{
				needtotracknumber104.setErrorflag(98);
				needtotracknumber104.setErrordescr("物流渠道不存在!");
				needtotracknumber104Service.saveOrUpdate(needtotracknumber104);
				return;
			}


			//交运属性
			RequestJson requestJson =null;
			if(needtotracknumber104.getRequestjson()!=null){
				requestJson = JSONObject.parseObject(needtotracknumber104.getRequestjson(),RequestJson.class);
			}


			TrackModel trackModel = new TrackModel();
			trackModel.setOrder0(order0);
			trackModel.setOrderitem0(orderItem0);
			trackModel.setOrderplus0(orderplus0);
			trackModel.setDbLogisticschannel(logisticschannel);
			trackModel.setRequestJson(requestJson);

			TrackResultModel trackResultModel = needtotracknumber104Service.runStep1(trackModel);
			needtotracknumber104.setErrorflag(trackResultModel.getFlag());
			needtotracknumber104.setErrordescr(trackResultModel.getDescr());
			if(trackResultModel.getFlag()==3 ){
				needtotracknumber104.setTracknumber(trackResultModel.getTrackNumber());
				order0.setTracknumber(trackResultModel.getTrackNumber());
				order0.setSynclogisticsstatus(3);
				order0.setIssynclogisticsdescr("获取订单成功！");
				order0Service.saveOrUpdate(order0);
			}

			needtotracknumber104Service.saveOrUpdate(needtotracknumber104);

		}

		log.info(String.format(" 获取订单号step1 org.baifei.modules.job.fxp.TracknumberJob1 ! 执行数:"+list.size()+" 时间:" + DateUtils.getTimestamp()));
	}
}
