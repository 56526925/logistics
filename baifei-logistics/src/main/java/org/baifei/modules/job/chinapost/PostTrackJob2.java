package org.baifei.modules.job.chinapost;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.baifei.common.util.ConstantConfig;
import org.baifei.common.util.DateUtils;
import org.baifei.modules.entity.*;
import org.baifei.modules.entity.request.common.*;
import org.baifei.modules.entity.response.common.DbMylogisticsaccount;
import org.baifei.modules.entity.response.common.DbMylogisticschannel;
import org.baifei.modules.entity.response.common.DbMylogisticsreturnaddress;
import org.baifei.modules.entity.response.common.DbShop;
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
public class PostTrackJob2 implements Job {
	@Autowired
	private INeedtotracknumber101Service needtotracknumber101Service;
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


		LambdaQueryWrapper<Needtotracknumber101> trackQuery = new LambdaQueryWrapper<Needtotracknumber101>();
		trackQuery.eq(Needtotracknumber101::getErrorflag,6);
		trackQuery.last("limit 100");
		List<Needtotracknumber101> list = needtotracknumber101Service.list(trackQuery);
		for(Needtotracknumber101 needtotracknumber101:list){
			//开始交运
			needtotracknumber101.setErrorflag(7);
			needtotracknumber101.setErrordescr("非推送订单正在交运...");
			needtotracknumber101Service.saveOrUpdate(needtotracknumber101);
			//订单
			LambdaQueryWrapper<Order0> orderQuery = new LambdaQueryWrapper<Order0>();
			orderQuery.eq(Order0::getId,needtotracknumber101.getOrderid());
			Order0 order0 = order0Service.getOne(orderQuery);
			if(order0==null){
				needtotracknumber101.setErrorflag(98);
				needtotracknumber101.setErrordescr("订单不存在!");
				needtotracknumber101Service.saveOrUpdate(needtotracknumber101);
				return;
			}
			//订单商品详情
			List<Orderitem0> orderItem0 = orderitem0Service.getItemsProduct(needtotracknumber101.getOrderid());
			if(orderItem0==null || orderItem0.size()<=0){
				needtotracknumber101.setErrorflag(98);
				needtotracknumber101.setErrordescr("订单商品详情不存在!");
				needtotracknumber101Service.saveOrUpdate(needtotracknumber101);
				return;
			}


			//订单金额
			LambdaQueryWrapper<Ordercurrency0> orderCurrencyQuery = new LambdaQueryWrapper<Ordercurrency0>();
			orderCurrencyQuery.eq(Ordercurrency0::getOrderid,needtotracknumber101.getOrderid());
			Ordercurrency0 ordercurrency0 = ordercurrency0Service.getOne(orderCurrencyQuery);
			if(ordercurrency0==null){
				needtotracknumber101.setErrorflag(98);
				needtotracknumber101.setErrordescr("订单金额信息不存在!");
				needtotracknumber101Service.saveOrUpdate(needtotracknumber101);
				return;
			}
			//订单买家信息
			LambdaQueryWrapper<Orderplus0> orderplus0Query = new LambdaQueryWrapper<Orderplus0>();
			orderplus0Query.eq(Orderplus0::getOrderid,needtotracknumber101.getOrderid());
			Orderplus0 orderplus0 = orderplus0Service.getOne(orderplus0Query);
			if(orderplus0==null ){
				needtotracknumber101.setErrorflag(98);
				needtotracknumber101.setErrordescr("订单买家信息不存在!");
				needtotracknumber101Service.saveOrUpdate(needtotracknumber101);
				return;
			}


			//货运方式
			FindMyLogisticsChannel findMyLogisticsChannel = new FindMyLogisticsChannel();
			findMyLogisticsChannel.setCompanyId(needtotracknumber101.getCompanyid());
			findMyLogisticsChannel.setMyLogisticsChannelId(needtotracknumber101.getMylogisticschannelid());

			ResulstCodeWeb<List<DbMylogisticschannel>> resultMylogisticschannel = restTemplateUtil.getDataListForPost(DbMylogisticschannel.class,findMyLogisticsChannel,"", ConstantConfig.LogisticsUrl+"/findMylogisticsChannel");
			DbMylogisticschannel mylogisticschannel = null;
			if (resultMylogisticschannel.getAck()!=1000 && resultMylogisticschannel.getData()!=null){
				mylogisticschannel = resultMylogisticschannel.getData().get(0);
			}else{
				needtotracknumber101.setErrorflag(98);
				needtotracknumber101.setErrordescr("货运方式不存在!");
				needtotracknumber101Service.saveOrUpdate(needtotracknumber101);
				return;
			}

			RequestJson requestJson = null;
			if(needtotracknumber101.getRequestjson()!=null){
				requestJson = JSONObject.parseObject(needtotracknumber101.getRequestjson(), RequestJson.class);
			}
			//物流账号
			DbMylogisticsaccount mylogisticsaccount = null;
			if(requestJson!=null) {
				FindMylogisticsAccount findMylogisticsAccount = new FindMylogisticsAccount();
				findMylogisticsAccount.setCompanyId(needtotracknumber101.getCompanyid());
				findMylogisticsAccount.setMyLogisticsAccountId(requestJson.getAccountId());

				ResulstCodeWeb<List<DbMylogisticsaccount>> resultDbMylogisticsaccount = restTemplateUtil.getDataListForPost(DbMylogisticsaccount.class, findMylogisticsAccount, "", ConstantConfig.LogisticsUrl + "/findMylogisticsAccount");

				if (resultDbMylogisticsaccount.getAck() != 1000 && resultDbMylogisticsaccount.getData() != null) {
					mylogisticsaccount = resultDbMylogisticsaccount.getData().get(0);
				} else {
					needtotracknumber101.setErrorflag(98);
					needtotracknumber101.setErrordescr("物流账号不存在!");
					needtotracknumber101Service.saveOrUpdate(needtotracknumber101);
					return;
				}
			}


			//寄件信息 DbMylogisticschannel中 returnAddress1
			FindMyLogisticsReturnAddress findMyLogisticsReturnAddress = new FindMyLogisticsReturnAddress();
			findMyLogisticsReturnAddress.setCompanyId(needtotracknumber101.getCompanyid());
			findMyLogisticsReturnAddress.setLogisticsId(needtotracknumber101.getLogisticsid());
			findMyLogisticsReturnAddress.setMyLogisticsReturnAddressId(mylogisticschannel.getReturnAddress1());

			ResulstCodeWeb<List<DbMylogisticsreturnaddress>> resultDbMylogisticsreturnaddress = restTemplateUtil.getDataListForPost(DbMylogisticsreturnaddress.class,findMyLogisticsReturnAddress,"",ConstantConfig.LogisticsUrl+"/findMylogisticsReturnAddress");
			DbMylogisticsreturnaddress mylogisticsreturnaddress = null;
			if (resultDbMylogisticsreturnaddress.getAck()!=1000 && resultDbMylogisticsreturnaddress.getData()!=null){
				mylogisticsreturnaddress = resultDbMylogisticsreturnaddress.getData().get(0);
			}else{
				needtotracknumber101.setErrorflag(98);
				needtotracknumber101.setErrordescr("寄件信息不存在!");
				needtotracknumber101Service.saveOrUpdate(needtotracknumber101);
				return;
			}


			TrackModel trackModel = new TrackModel();
			trackModel.setOrder0(order0);
			trackModel.setOrderitem0(orderItem0);
			trackModel.setOrdercurrency0(ordercurrency0);
			trackModel.setOrderplus0(orderplus0);
			trackModel.setDbMylogisticschannel(mylogisticschannel);
			trackModel.setDbMylogisticsreturnaddress(mylogisticsreturnaddress);
			trackModel.setMylogisticsaccount(mylogisticsaccount);

			//交运完成
			TrackResultModel trackResultModel = needtotracknumber101Service.runStep2(trackModel);
			needtotracknumber101.setErrorflag(trackResultModel.getFlag());
			needtotracknumber101.setErrordescr(trackResultModel.getDescr());
			needtotracknumber101Service.saveOrUpdate(needtotracknumber101);
		}
		log.info(String.format(" chinapost获取订单号step2 org.baifei.modules.job.chinapost.TracknumberJob2 ! 执行数:"+list.size()+" 时间:" + DateUtils.getTimestamp()));
	}
}
