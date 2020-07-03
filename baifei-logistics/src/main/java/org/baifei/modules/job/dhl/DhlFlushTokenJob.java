package org.baifei.modules.job.dhl;

import lombok.extern.slf4j.Slf4j;
import org.baifei.common.util.ConstantConfig;
import org.baifei.modules.entity.request.common.FindMylogisticsAccount;
import org.baifei.modules.entity.response.common.DbMylogisticsaccount;
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
public class DhlFlushTokenJob implements Job {
	@Autowired
	private INeedtotracknumber112Service needtotracknumber112Service;
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
			findMylogisticsAccount.setLogisticsId(112);

			ResulstCodeWeb<List<DbMylogisticsaccount>> resultDbMylogisticsaccount = restTemplateUtil.getDataListForPost(DbMylogisticsaccount.class, findMylogisticsAccount, "", ConstantConfig.LogisticsUrl + "/findMylogisticsAccount");

			if (resultDbMylogisticsaccount.getAck() != 1000 && resultDbMylogisticsaccount.getData() != null) {
				for(int i=0;i<resultDbMylogisticsaccount.getData().size();i++){
					mylogisticsaccount = resultDbMylogisticsaccount.getData().get(i);

					if(mylogisticsaccount.getExpireTime().getTime()<=new Date().getTime()){
						mylogisticsaccount.setName("LTExMTgwNTI4MTY=");
						mylogisticsaccount.setPassword("APITest1");
						needtotracknumber112Service.flushToken(mylogisticsaccount.getId());
						log.info("dhl刷新令牌 org.baifei.modules.job.dhl.DhlFlushTokenJob,刷新成功");
					}
				}

			} else {
				log.info("dhl刷新令牌异常"+resultDbMylogisticsaccount.getMsg());
			}
		}catch (Exception e){
			log.info("dhl刷新令牌异常"+e.getMessage());
		}
	}
}
