package org.baifei.modules.job.sf;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.baifei.common.util.ConstantConfig;
import org.baifei.common.util.DateUtils;
import org.baifei.common.util.SSLClient;
import org.baifei.modules.entity.Needtotracknumber106;
import org.baifei.modules.entity.Order0;
import org.baifei.modules.entity.request.common.DbOrderlabel;
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
public class SfLabelJob implements Job {
	@Autowired
	private INeedtotracknumber106Service needtotracknumber106Service;
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


		LambdaQueryWrapper<Needtotracknumber106> trackQuery = new LambdaQueryWrapper<Needtotracknumber106>();
		trackQuery.eq(Needtotracknumber106::getErrorflag,3);
		trackQuery.eq(Needtotracknumber106::getIslabel,0);
		trackQuery.last("limit 100");
		List<Needtotracknumber106> list = needtotracknumber106Service.list(trackQuery);
		for(Needtotracknumber106 needtotracknumber106:list){
			Integer compantId = needtotracknumber106.getCompanyid();
            if(true){
				//开始交运
				needtotracknumber106.setIslabel(1);
				needtotracknumber106.setLabeldescr("开始下载单面...");
				needtotracknumber106Service.saveOrUpdate(needtotracknumber106);

				//订单
				LambdaQueryWrapper<Order0> orderQuery = new LambdaQueryWrapper<Order0>();
				orderQuery.eq(Order0::getId,needtotracknumber106.getOrderid());
				Order0 order0 = order0Service.getOne(orderQuery);
				if(order0==null){
					needtotracknumber106.setIslabel(98);
					needtotracknumber106.setLabeldescr("订单不存在!");
					needtotracknumber106Service.saveOrUpdate(needtotracknumber106);
					return;
				}
				RequestJson requestJson = null;
				if(needtotracknumber106.getRequestjson()!=null){
					requestJson = JSONObject.parseObject(needtotracknumber106.getRequestjson(), RequestJson.class);
				}
				DbMylogisticsaccount mylogisticsaccount = null;
				if(requestJson!=null) {
					FindMylogisticsAccount findMylogisticsAccount = new FindMylogisticsAccount();
					findMylogisticsAccount.setCompanyId(needtotracknumber106.getCompanyid());
					findMylogisticsAccount.setMyLogisticsAccountId(requestJson.getAccountId());

					ResulstCodeWeb<List<DbMylogisticsaccount>> resultDbMylogisticsaccount = restTemplateUtil.getDataListForPost(DbMylogisticsaccount.class, findMylogisticsAccount, "", ConstantConfig.LogisticsUrl + "/findMylogisticsAccount");

					if (resultDbMylogisticsaccount.getAck() != 1000 && resultDbMylogisticsaccount.getData() != null) {
						mylogisticsaccount = resultDbMylogisticsaccount.getData().get(0);
					} else {
						needtotracknumber106.setIslabel(98);
						needtotracknumber106.setLabeldescr("物流账号不存在!");
						needtotracknumber106Service.saveOrUpdate(needtotracknumber106);
						return;
					}
				}


				TrackModel trackModel = new TrackModel();
				trackModel.setOrder0(order0);
				trackModel.setMylogisticsaccount(mylogisticsaccount);

				//交运完成
				TrackResultModel trackResultModel = needtotracknumber106Service.runStep2(trackModel);
				needtotracknumber106.setIslabel(trackResultModel.getFlag());
				needtotracknumber106.setLabeldescr(trackResultModel.getDescr());
				needtotracknumber106Service.saveOrUpdate(needtotracknumber106);

				if("3".equals(trackResultModel.getFlag())){
					DbOrderlabel orderlabel = new DbOrderlabel();
					orderlabel.setCompanyId(needtotracknumber106.getCompanyid());
					orderlabel.setPlatformOrderId(order0.getPlatformorderid());
					ResulstCodeWeb<List<DbOrderlabel>> resultOrderlabel = restTemplateUtil.getDataListForPost(DbOrderlabel.class, orderlabel, "", ConstantConfig.LogisticsUrl + "/syncDbOrderLabel");
				}
			}
		}

		log.info(String.format(" 顺丰下载面单 org.baifei.modules.job.sf.SfLabelJob  !  执行数:"+list.size()+" 时间:" + DateUtils.getTimestamp()));
	}

}
