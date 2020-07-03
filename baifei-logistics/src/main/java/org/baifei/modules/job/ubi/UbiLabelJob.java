package org.baifei.modules.job.ubi;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.baifei.common.util.ConstantConfig;
import org.baifei.common.util.DateUtils;
import org.baifei.modules.entity.Needtotracknumber123;
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
public class UbiLabelJob implements Job {
	@Autowired
	private INeedtotracknumber123Service needtotracknumber123Service;
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


		LambdaQueryWrapper<Needtotracknumber123> trackQuery = new LambdaQueryWrapper<Needtotracknumber123>();
		trackQuery.eq(Needtotracknumber123::getErrorflag,3);
		trackQuery.eq(Needtotracknumber123::getIslabel,0);
		trackQuery.last("limit 100");
		List<Needtotracknumber123> list = needtotracknumber123Service.list(trackQuery);
		for(Needtotracknumber123 needtotracknumber123:list){
			Integer compantId = needtotracknumber123.getCompanyid();
            if(true){
				//开始交运
				needtotracknumber123.setIslabel(1);
				needtotracknumber123.setLabeldescr("开始下载单面...");
				needtotracknumber123Service.saveOrUpdate(needtotracknumber123);

				//订单
				LambdaQueryWrapper<Order0> orderQuery = new LambdaQueryWrapper<Order0>();
				orderQuery.eq(Order0::getId,needtotracknumber123.getOrderid());
				Order0 order0 = order0Service.getOne(orderQuery);
				if(order0==null){
					needtotracknumber123.setIslabel(98);
					needtotracknumber123.setLabeldescr("订单不存在!");
					needtotracknumber123Service.saveOrUpdate(needtotracknumber123);
					return;
				}
				RequestJson requestJson = null;
				if(needtotracknumber123.getRequestjson()!=null){
					requestJson = JSONObject.parseObject(needtotracknumber123.getRequestjson(), RequestJson.class);
				}
				DbMylogisticsaccount mylogisticsaccount = null;
				if(requestJson!=null) {
					FindMylogisticsAccount findMylogisticsAccount = new FindMylogisticsAccount();
					findMylogisticsAccount.setCompanyId(needtotracknumber123.getCompanyid());
					findMylogisticsAccount.setMyLogisticsAccountId(requestJson.getAccountId());

					ResulstCodeWeb<List<DbMylogisticsaccount>> resultDbMylogisticsaccount = restTemplateUtil.getDataListForPost(DbMylogisticsaccount.class, findMylogisticsAccount, "", ConstantConfig.LogisticsUrl + "/findMylogisticsAccount");

					if (resultDbMylogisticsaccount.getAck() != 1000 && resultDbMylogisticsaccount.getData() != null) {
						mylogisticsaccount = resultDbMylogisticsaccount.getData().get(0);
					} else {
						needtotracknumber123.setIslabel(98);
						needtotracknumber123.setLabeldescr("物流账号不存在!");
						needtotracknumber123Service.saveOrUpdate(needtotracknumber123);
						return;
					}
				}


				TrackModel trackModel = new TrackModel();
				trackModel.setOrder0(order0);
				trackModel.setMylogisticsaccount(mylogisticsaccount);

				//交运完成
				TrackResultModel trackResultModel = needtotracknumber123Service.runStep2(trackModel);
				needtotracknumber123.setIslabel(trackResultModel.getFlag());
				needtotracknumber123.setLabeldescr(trackResultModel.getDescr());
				needtotracknumber123Service.saveOrUpdate(needtotracknumber123);

				if("3".equals(trackResultModel.getFlag())){
					DbOrderlabel orderlabel = new DbOrderlabel();
					orderlabel.setCompanyId(needtotracknumber123.getCompanyid());
					orderlabel.setPlatformOrderId(order0.getPlatformorderid());
					ResulstCodeWeb<List<DbOrderlabel>> resultOrderlabel = restTemplateUtil.getDataListForPost(DbOrderlabel.class, orderlabel, "", ConstantConfig.LogisticsUrl + "/syncDbOrderLabel");
				}
			}
		}

		log.info(String.format(" Ubi下载面单 org.baifei.modules.job.ubi.UbiLabelJob  !  执行数:"+list.size()+" 时间:" + DateUtils.getTimestamp()));
	}

}
