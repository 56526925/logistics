package org.baifei.modules.job.ykd;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.baifei.common.util.ConstantConfig;
import org.baifei.common.util.DateUtils;
import org.baifei.modules.entity.Needtotracknumber118;
import org.baifei.modules.entity.Order0;
import org.baifei.modules.entity.Orderitem0;
import org.baifei.modules.entity.Orderplus0;
import org.baifei.modules.entity.request.common.FindLogisticsChannel;
import org.baifei.modules.entity.request.common.FindMylogisticsAccount;
import org.baifei.modules.entity.request.common.RequestJson;
import org.baifei.modules.entity.response.common.DbLogisticschannel;
import org.baifei.modules.entity.response.common.DbMylogisticsaccount;
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
public class YkdTrackJob1 implements Job {
	@Autowired
	private INeedtotracknumber118Service needtotracknumber118Service;
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


		LambdaQueryWrapper<Needtotracknumber118> trackQuery = new LambdaQueryWrapper<Needtotracknumber118>();
		trackQuery.eq(Needtotracknumber118::getErrorflag,0);
		trackQuery.last("limit 100");
		List<Needtotracknumber118> list = needtotracknumber118Service.list(trackQuery);
		for(Needtotracknumber118 needtotracknumber118:list){
			//开始交运
			needtotracknumber118.setErrorflag(1);
			needtotracknumber118.setErrordescr("正在获取订单号...");
			needtotracknumber118Service.saveOrUpdate(needtotracknumber118);
			//订单
			LambdaQueryWrapper<Order0> orderQuery = new LambdaQueryWrapper<Order0>();
			orderQuery.eq(Order0::getId,needtotracknumber118.getOrderid());
			Order0 order0 = order0Service.getOne(orderQuery);
			if(order0==null){
				needtotracknumber118.setErrorflag(98);
				needtotracknumber118.setErrordescr("订单不存在!");
				needtotracknumber118Service.saveOrUpdate(needtotracknumber118);
				return;
			}
			//订单商品详情
			List<Orderitem0> orderItem0 = orderitem0Service.getItemsProduct(needtotracknumber118.getOrderid());
			if(orderItem0==null || orderItem0.size()<=0){
				needtotracknumber118.setErrorflag(98);
				needtotracknumber118.setErrordescr("订单商品详情不存在!");
				needtotracknumber118Service.saveOrUpdate(needtotracknumber118);
				return;
			}

			//订单买家信息
			LambdaQueryWrapper<Orderplus0> orderplus0Query = new LambdaQueryWrapper<Orderplus0>();
			orderplus0Query.eq(Orderplus0::getOrderid,needtotracknumber118.getOrderid());
			Orderplus0 orderplus0 = orderplus0Service.getOne(orderplus0Query);
			if(orderplus0==null ){
				needtotracknumber118.setErrorflag(98);
				needtotracknumber118.setErrordescr("订单买家信息不存在!");
				needtotracknumber118Service.saveOrUpdate(needtotracknumber118);
				return;
			}

			//物流渠道
			FindLogisticsChannel findLogisticsChannel = new FindLogisticsChannel();
			findLogisticsChannel.setLogisticsChannelId(needtotracknumber118.getLogisticschannelid());
			findLogisticsChannel.setLogisticsId(needtotracknumber118.getLogisticsid());

			ResulstCodeWeb<List<DbLogisticschannel>> resultLogisticschannel = restTemplateUtil.getDataListForPost(DbLogisticschannel.class,findLogisticsChannel,"", ConstantConfig.LogisticsUrl+"/findLogisticsChannel");
			DbLogisticschannel logisticschannel = null;
			if (resultLogisticschannel.getAck()!=1000 && resultLogisticschannel.getData()!=null){
				logisticschannel = resultLogisticschannel.getData().get(0);
			}else{
				needtotracknumber118.setErrorflag(98);
				needtotracknumber118.setErrordescr("物流渠道不存在!");
				needtotracknumber118Service.saveOrUpdate(needtotracknumber118);
				return;
			}


			//交运属性
			RequestJson requestJson =null;
			if(needtotracknumber118.getRequestjson()!=null){
				requestJson = JSONObject.parseObject(needtotracknumber118.getRequestjson(),RequestJson.class);
			}

			DbMylogisticsaccount mylogisticsaccount = null;
			if(requestJson!=null) {
				FindMylogisticsAccount findMylogisticsAccount = new FindMylogisticsAccount();
				findMylogisticsAccount.setCompanyId(needtotracknumber118.getCompanyid());
				findMylogisticsAccount.setMyLogisticsAccountId(requestJson.getAccountId());

				ResulstCodeWeb<List<DbMylogisticsaccount>> resultDbMylogisticsaccount = restTemplateUtil.getDataListForPost(DbMylogisticsaccount.class, findMylogisticsAccount, "", ConstantConfig.LogisticsUrl + "/findMylogisticsAccount");

				if (resultDbMylogisticsaccount.getAck() != 1000 && resultDbMylogisticsaccount.getData() != null) {
					mylogisticsaccount = resultDbMylogisticsaccount.getData().get(0);
				} else {
					needtotracknumber118.setErrorflag(98);
					needtotracknumber118.setErrordescr("物流账号不存在!");
					needtotracknumber118Service.saveOrUpdate(needtotracknumber118);
					return;
				}
			}


			TrackModel trackModel = new TrackModel();
			trackModel.setOrder0(order0);
			trackModel.setOrderitem0(orderItem0);
			trackModel.setOrderplus0(orderplus0);
			trackModel.setDbLogisticschannel(logisticschannel);
			trackModel.setRequestJson(requestJson);
			trackModel.setMylogisticsaccount(mylogisticsaccount);

			TrackResultModel trackResultModel = needtotracknumber118Service.runStep1(trackModel);
			needtotracknumber118.setErrorflag(trackResultModel.getFlag());
			needtotracknumber118.setErrordescr(trackResultModel.getDescr());
			if(trackResultModel.getFlag()==2){
				needtotracknumber118.setTracknumber1(trackResultModel.getTrackNumber1());
				order0.setTracknumber1(trackResultModel.getTrackNumber1());
				order0Service.saveOrUpdate(order0);
			}

			needtotracknumber118Service.saveOrUpdate(needtotracknumber118);

		}

		log.info(String.format("易可达获取订单号step1 org.baifei.modules.job.ykd.YkdTrackJob1 ! 执行数:"+list.size()+" 时间:" + DateUtils.getTimestamp()));
	}
}
