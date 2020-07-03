package org.baifei.modules.job.qufahuo;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.baifei.common.util.ConstantConfig;
import org.baifei.common.util.DateUtils;
import org.baifei.modules.entity.*;
import org.baifei.modules.entity.request.common.FindMyLogisticsChannel;
import org.baifei.modules.entity.request.common.FindMyLogisticsReturnAddress;
import org.baifei.modules.entity.request.common.FindMylogisticsAccount;
import org.baifei.modules.entity.request.common.RequestJson;
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
public class QufahuoTrackJob2 implements Job {
	@Autowired
	private INeedtotracknumber114Service needtotracknumber114Service;
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


		LambdaQueryWrapper<Needtotracknumber114> trackQuery = new LambdaQueryWrapper<Needtotracknumber114>();
		trackQuery.eq(Needtotracknumber114::getErrorflag,2);
		trackQuery.last("limit 100");
		List<Needtotracknumber114> list = needtotracknumber114Service.list(trackQuery);
		for(Needtotracknumber114 needtotracknumber114:list){
			//开始交运
			needtotracknumber114.setErrorflag(1);
			needtotracknumber114.setErrordescr("正在获取订单号...");
			needtotracknumber114Service.saveOrUpdate(needtotracknumber114);
			//订单
			LambdaQueryWrapper<Order0> orderQuery = new LambdaQueryWrapper<Order0>();
			orderQuery.eq(Order0::getId,needtotracknumber114.getOrderid());
			Order0 order0 = order0Service.getOne(orderQuery);
			if(order0==null){
				needtotracknumber114.setErrorflag(98);
				needtotracknumber114.setErrordescr("订单不存在!");
				needtotracknumber114Service.saveOrUpdate(needtotracknumber114);
				return;
			}
			//订单商品详情
			List<Orderitem0> orderItem0 = orderitem0Service.getItemsProduct(needtotracknumber114.getOrderid());
			if(orderItem0==null || orderItem0.size()<=0){
				needtotracknumber114.setErrorflag(98);
				needtotracknumber114.setErrordescr("订单商品详情不存在!");
				needtotracknumber114Service.saveOrUpdate(needtotracknumber114);
				return;
			}


			//订单金额
			LambdaQueryWrapper<Ordercurrency0> orderCurrencyQuery = new LambdaQueryWrapper<Ordercurrency0>();
			orderCurrencyQuery.eq(Ordercurrency0::getOrderid,needtotracknumber114.getOrderid());
			Ordercurrency0 ordercurrency0 = ordercurrency0Service.getOne(orderCurrencyQuery);
			if(ordercurrency0==null){
				needtotracknumber114.setErrorflag(98);
				needtotracknumber114.setErrordescr("订单金额信息不存在!");
				needtotracknumber114Service.saveOrUpdate(needtotracknumber114);
				return;
			}
			//订单买家信息
			LambdaQueryWrapper<Orderplus0> orderplus0Query = new LambdaQueryWrapper<Orderplus0>();
			orderplus0Query.eq(Orderplus0::getOrderid,needtotracknumber114.getOrderid());
			Orderplus0 orderplus0 = orderplus0Service.getOne(orderplus0Query);
			if(orderplus0==null ){
				needtotracknumber114.setErrorflag(98);
				needtotracknumber114.setErrordescr("订单买家信息不存在!");
				needtotracknumber114Service.saveOrUpdate(needtotracknumber114);
				return;
			}


			//货运方式
			FindMyLogisticsChannel findMyLogisticsChannel = new FindMyLogisticsChannel();
			findMyLogisticsChannel.setCompanyId(needtotracknumber114.getCompanyid());
			findMyLogisticsChannel.setMyLogisticsChannelId(needtotracknumber114.getMylogisticschannelid());

			ResulstCodeWeb<List<DbMylogisticschannel>> resultMylogisticschannel = restTemplateUtil.getDataListForPost(DbMylogisticschannel.class,findMyLogisticsChannel,"", ConstantConfig.LogisticsUrl+"/findMylogisticsChannel");
			DbMylogisticschannel mylogisticschannel = null;
			if (resultMylogisticschannel.getAck()!=1000 && resultMylogisticschannel.getData()!=null){
				mylogisticschannel = resultMylogisticschannel.getData().get(0);
			}else{
				needtotracknumber114.setErrorflag(98);
				needtotracknumber114.setErrordescr("货运方式不存在!");
				needtotracknumber114Service.saveOrUpdate(needtotracknumber114);
				return;
			}

			RequestJson requestJson = null;
			if(needtotracknumber114.getRequestjson()!=null){
				requestJson = JSONObject.parseObject(needtotracknumber114.getRequestjson(), RequestJson.class);
			}
			//物流账号
			DbMylogisticsaccount mylogisticsaccount = null;
			if(requestJson!=null) {
				FindMylogisticsAccount findMylogisticsAccount = new FindMylogisticsAccount();
				findMylogisticsAccount.setCompanyId(needtotracknumber114.getCompanyid());
				findMylogisticsAccount.setMyLogisticsAccountId(requestJson.getAccountId());

				ResulstCodeWeb<List<DbMylogisticsaccount>> resultDbMylogisticsaccount = restTemplateUtil.getDataListForPost(DbMylogisticsaccount.class, findMylogisticsAccount, "", ConstantConfig.LogisticsUrl + "/findMylogisticsAccount");

				if (resultDbMylogisticsaccount.getAck() != 1000 && resultDbMylogisticsaccount.getData() != null) {
					mylogisticsaccount = resultDbMylogisticsaccount.getData().get(0);
				} else {
					needtotracknumber114.setErrorflag(98);
					needtotracknumber114.setErrordescr("物流账号不存在!");
					needtotracknumber114Service.saveOrUpdate(needtotracknumber114);
					return;
				}
			}


			//寄件信息 DbMylogisticschannel中 returnAddress1
			FindMyLogisticsReturnAddress findMyLogisticsReturnAddress = new FindMyLogisticsReturnAddress();
			findMyLogisticsReturnAddress.setCompanyId(needtotracknumber114.getCompanyid());
			findMyLogisticsReturnAddress.setLogisticsId(needtotracknumber114.getLogisticsid());
			findMyLogisticsReturnAddress.setMyLogisticsReturnAddressId(mylogisticschannel.getReturnAddress1());

			ResulstCodeWeb<List<DbMylogisticsreturnaddress>> resultDbMylogisticsreturnaddress = restTemplateUtil.getDataListForPost(DbMylogisticsreturnaddress.class,findMyLogisticsReturnAddress,"",ConstantConfig.LogisticsUrl+"/findMylogisticsReturnAddress");
			DbMylogisticsreturnaddress mylogisticsreturnaddress = null;
			if (resultDbMylogisticsreturnaddress.getAck()!=1000 && resultDbMylogisticsreturnaddress.getData()!=null){
				mylogisticsreturnaddress = resultDbMylogisticsreturnaddress.getData().get(0);
			}else{
				needtotracknumber114.setErrorflag(98);
				needtotracknumber114.setErrordescr("寄件信息不存在!");
				needtotracknumber114Service.saveOrUpdate(needtotracknumber114);
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
			TrackResultModel trackResultModel = needtotracknumber114Service.runStep2(trackModel);
			needtotracknumber114.setErrorflag(trackResultModel.getFlag());
			needtotracknumber114.setErrordescr(trackResultModel.getDescr());
			needtotracknumber114Service.saveOrUpdate(needtotracknumber114);
			if(trackResultModel.getFlag()==3 ){
				needtotracknumber114.setTracknumber(trackResultModel.getTrackNumber());
				order0.setTracknumber(trackResultModel.getTrackNumber());
				order0.setSynclogisticsstatus(3);
				order0.setIssynclogisticsdescr("获取订单成功！");
				order0Service.saveOrUpdate(order0);
			}
		}
		log.info(String.format(" 去发货获取订单号step2 org.baifei.modules.job.qufahuo.QufahuoTrackJob2 ! 执行数:"+list.size()+" 时间:" + DateUtils.getTimestamp()));
	}
}
