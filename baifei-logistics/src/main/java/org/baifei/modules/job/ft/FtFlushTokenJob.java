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

import java.util.Date;
import java.util.List;

/**
 * 示例不带参定时任务
 * 
 * @Author Scott
 */
@Slf4j
public class FtFlushTokenJob implements Job {
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

		try{
			//物流账号
			DbMylogisticsaccount mylogisticsaccount = null;
			FindMylogisticsAccount findMylogisticsAccount = new FindMylogisticsAccount();
			findMylogisticsAccount.setCompanyId(101300);
			findMylogisticsAccount.setMyLogisticsAccountId(999);

			ResulstCodeWeb<List<DbMylogisticsaccount>> resultDbMylogisticsaccount = restTemplateUtil.getDataListForPost(DbMylogisticsaccount.class, findMylogisticsAccount, "", ConstantConfig.LogisticsUrl + "/findMylogisticsAccount");

			if (resultDbMylogisticsaccount.getAck() != 1000 && resultDbMylogisticsaccount.getData() != null) {
				mylogisticsaccount = resultDbMylogisticsaccount.getData().get(0);
				if(mylogisticsaccount.getExpireTime().getTime()<=new Date().getTime()){
					needtotracknumber115Service.flushToken();

					log.info("飞特刷新令牌 org.baifei.modules.job.ft.FtFlushTokenJob,刷新成功");
				}
			} else {
				log.info("飞特刷新令牌异常"+resultDbMylogisticsaccount.getMsg());
			}
		}catch (Exception e){
			log.info("飞特刷新令牌异常"+e.getMessage());
		}
	}
}
