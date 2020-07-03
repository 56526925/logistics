package org.baifei.modules.job.zyhw;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.baifei.common.util.ConstantConfig;
import org.baifei.common.util.DateUtils;
import org.baifei.modules.entity.Needtotracknumber122;
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
public class ZyhwTrackJob2 implements Job {
	@Autowired
	private INeedtotracknumber122Service needtotracknumber122Service;
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


		LambdaQueryWrapper<Needtotracknumber122> trackQuery = new LambdaQueryWrapper<Needtotracknumber122>();
		trackQuery.eq(Needtotracknumber122::getErrorflag,2);
		trackQuery.last("limit 100");
		List<Needtotracknumber122> list = needtotracknumber122Service.list(trackQuery);
		for(Needtotracknumber122 needtotracknumber122:list){
			//开始交运
			needtotracknumber122.setErrorflag(1);
			needtotracknumber122.setErrordescr("正在获取订单号...");
			needtotracknumber122Service.saveOrUpdate(needtotracknumber122);
			//订单
			LambdaQueryWrapper<Order0> orderQuery = new LambdaQueryWrapper<Order0>();
			orderQuery.eq(Order0::getId,needtotracknumber122.getOrderid());
			Order0 order0 = order0Service.getOne(orderQuery);
			if(order0==null){
				needtotracknumber122.setErrorflag(98);
				needtotracknumber122.setErrordescr("订单不存在!");
				needtotracknumber122Service.saveOrUpdate(needtotracknumber122);
				return;
			}

			//交运属性
			RequestJson requestJson =null;
			if(needtotracknumber122.getRequestjson()!=null){
				requestJson = JSONObject.parseObject(needtotracknumber122.getRequestjson(),RequestJson.class);
			}

			DbMylogisticsaccount mylogisticsaccount = null;
			if(requestJson!=null) {
				FindMylogisticsAccount findMylogisticsAccount = new FindMylogisticsAccount();
				findMylogisticsAccount.setCompanyId(needtotracknumber122.getCompanyid());
				findMylogisticsAccount.setMyLogisticsAccountId(requestJson.getAccountId());

				ResulstCodeWeb<List<DbMylogisticsaccount>> resultDbMylogisticsaccount = restTemplateUtil.getDataListForPost(DbMylogisticsaccount.class, findMylogisticsAccount, "", ConstantConfig.LogisticsUrl + "/findMylogisticsAccount");

				if (resultDbMylogisticsaccount.getAck() != 1000 && resultDbMylogisticsaccount.getData() != null) {
					mylogisticsaccount = resultDbMylogisticsaccount.getData().get(0);
				} else {
					needtotracknumber122.setErrorflag(98);
					needtotracknumber122.setErrordescr("物流账号不存在!");
					needtotracknumber122Service.saveOrUpdate(needtotracknumber122);
					return;
				}
			}


			TrackModel trackModel = new TrackModel();
			trackModel.setOrder0(order0);
			trackModel.setMylogisticsaccount(mylogisticsaccount);

			TrackResultModel trackResultModel = needtotracknumber122Service.runStep2(trackModel);
			needtotracknumber122.setErrorflag(trackResultModel.getFlag());
			needtotracknumber122.setErrordescr(trackResultModel.getDescr());
			if(trackResultModel.getFlag()==3){
				needtotracknumber122.setTracknumber(trackResultModel.getTrackNumber());
				order0.setTracknumber(trackResultModel.getTrackNumber());
				order0Service.saveOrUpdate(order0);
			}

			needtotracknumber122Service.saveOrUpdate(needtotracknumber122);

		}

		log.info(String.format(" 中邮海外获取订单号step2 org.baifei.modules.job.zyhw.ZyhwTrackJob2 ! 执行数:"+list.size()+" 时间:" + DateUtils.getTimestamp()));
	}
}
