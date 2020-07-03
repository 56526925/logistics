package org.baifei.modules.job.ft;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.baifei.common.util.ConstantConfig;
import org.baifei.common.util.DateUtils;
import org.baifei.modules.entity.Needtotracknumber115;
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
public class FtTrackOnlineJob implements Job {
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
		trackQuery.eq(Needtotracknumber115::getIslabel,3);
		trackQuery.last("limit 100");
		List<Needtotracknumber115> list = needtotracknumber115Service.list(trackQuery);
		for(Needtotracknumber115 needtotracknumber115:list){
			Integer compantId = needtotracknumber115.getCompanyid();
            if(true){
				//开始交运
				needtotracknumber115.setIslabel(7);
				needtotracknumber115.setLabeldescr("预报线上订单...");
				needtotracknumber115Service.saveOrUpdate(needtotracknumber115);

				//订单
				LambdaQueryWrapper<Order0> orderQuery = new LambdaQueryWrapper<Order0>();
				orderQuery.eq(Order0::getId,needtotracknumber115.getOrderid());
				Order0 order0 = order0Service.getOne(orderQuery);
				if(order0==null){
					needtotracknumber115.setIslabel(98);
					needtotracknumber115.setLabeldescr("订单不存在!");
					needtotracknumber115Service.saveOrUpdate(needtotracknumber115);
					return;
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
						needtotracknumber115.setIslabel(98);
						needtotracknumber115.setLabeldescr("物流账号不存在!");
						needtotracknumber115Service.saveOrUpdate(needtotracknumber115);
						return;
					}
				}


				TrackModel trackModel = new TrackModel();
				trackModel.setOrder0(order0);
				trackModel.setMylogisticsaccount(mylogisticsaccount);


				//交运完成
				TrackResultModel trackResultModel = needtotracknumber115Service.runStep3(trackModel);
				needtotracknumber115.setIslabel(trackResultModel.getFlag());
				needtotracknumber115.setLabeldescr(trackResultModel.getDescr());
				needtotracknumber115Service.saveOrUpdate(needtotracknumber115);
			}
		}

		log.info(String.format(" 飞特线上预报 org.baifei.modules.job.ft.FtTrackOnlineJob  !  执行数:"+list.size()+" 时间:" + DateUtils.getTimestamp()));
	}
}
