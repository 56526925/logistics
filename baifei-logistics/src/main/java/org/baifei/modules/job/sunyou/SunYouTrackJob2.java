package org.baifei.modules.job.sunyou;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.baifei.common.util.ConstantConfig;
import org.baifei.common.util.DateUtils;
import org.baifei.modules.entity.Needtotracknumber108;
import org.baifei.modules.entity.Order0;
import org.baifei.modules.entity.request.common.FindMylogisticsAccount;
import org.baifei.modules.entity.request.common.RequestJson;
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
public class SunYouTrackJob2 implements Job {
	@Autowired
	private INeedtotracknumber108Service needtotracknumber108Service;
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


		LambdaQueryWrapper<Needtotracknumber108> trackQuery = new LambdaQueryWrapper<Needtotracknumber108>();
		trackQuery.eq(Needtotracknumber108::getErrorflag,2);
		trackQuery.last("limit 100");
		List<Needtotracknumber108> list = needtotracknumber108Service.list(trackQuery);
		for(Needtotracknumber108 needtotracknumber108:list){
			//开始交运
			needtotracknumber108.setErrorflag(1);
			needtotracknumber108.setErrordescr("正在获取订单号...");
			needtotracknumber108Service.saveOrUpdate(needtotracknumber108);
			//订单
			LambdaQueryWrapper<Order0> orderQuery = new LambdaQueryWrapper<Order0>();
			orderQuery.eq(Order0::getId,needtotracknumber108.getOrderid());
			Order0 order0 = order0Service.getOne(orderQuery);
			if(order0==null){
				needtotracknumber108.setErrorflag(98);
				needtotracknumber108.setErrordescr("订单不存在!");
				needtotracknumber108Service.saveOrUpdate(needtotracknumber108);
				return;
			}
			RequestJson requestJson = null;
			if(needtotracknumber108.getRequestjson()!=null){
				requestJson = JSONObject.parseObject(needtotracknumber108.getRequestjson(), RequestJson.class);
			}
			//物流账号
			DbMylogisticsaccount mylogisticsaccount = null;
			if(requestJson!=null) {
				FindMylogisticsAccount findMylogisticsAccount = new FindMylogisticsAccount();
				findMylogisticsAccount.setCompanyId(needtotracknumber108.getCompanyid());
				findMylogisticsAccount.setMyLogisticsAccountId(requestJson.getAccountId());

				ResulstCodeWeb<List<DbMylogisticsaccount>> resultDbMylogisticsaccount = restTemplateUtil.getDataListForPost(DbMylogisticsaccount.class, findMylogisticsAccount, "", ConstantConfig.LogisticsUrl + "/findMylogisticsAccount");

				if (resultDbMylogisticsaccount.getAck() != 1000 && resultDbMylogisticsaccount.getData() != null) {
					mylogisticsaccount = resultDbMylogisticsaccount.getData().get(0);
				} else {
					needtotracknumber108.setErrorflag(98);
					needtotracknumber108.setErrordescr("物流账号不存在!");
					needtotracknumber108Service.saveOrUpdate(needtotracknumber108);
					return;
				}
			}

			TrackModel trackModel = new TrackModel();
			trackModel.setOrder0(order0);
			trackModel.setMylogisticsaccount(mylogisticsaccount);

			TrackResultModel trackResultModel = needtotracknumber108Service.runStep2(trackModel);
			needtotracknumber108.setErrorflag(trackResultModel.getFlag());
			needtotracknumber108.setErrordescr(trackResultModel.getDescr());

			if(trackResultModel.getFlag()==3){
				needtotracknumber108.setTracknumber(trackResultModel.getTrackNumber());
				order0.setTracknumber(trackResultModel.getTrackNumber());
				order0.setSynclogisticsstatus(3);
				order0.setIssynclogisticsdescr("获取订单成功！");
				order0Service.saveOrUpdate(order0);
			}

			needtotracknumber108Service.saveOrUpdate(needtotracknumber108);

		}

		log.info(String.format(" 顺友获取订单号step2 org.baifei.modules.job.sunyou.SunYouTrackJob2 ! 执行数:"+list.size()+" 时间:" + DateUtils.getTimestamp()));
	}
}
