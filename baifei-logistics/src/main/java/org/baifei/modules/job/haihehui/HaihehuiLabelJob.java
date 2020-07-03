package org.baifei.modules.job.haihehui;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.baifei.common.util.ConstantConfig;
import org.baifei.common.util.DateUtils;
import org.baifei.modules.entity.Needtotracknumber111;
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
public class HaihehuiLabelJob implements Job {
	@Autowired
	private INeedtotracknumber111Service needtotracknumber111Service;
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


		LambdaQueryWrapper<Needtotracknumber111> trackQuery = new LambdaQueryWrapper<Needtotracknumber111>();
		trackQuery.eq(Needtotracknumber111::getErrorflag,3);
		trackQuery.eq(Needtotracknumber111::getIslabel,0);
		trackQuery.last("limit 100");
		List<Needtotracknumber111> list = needtotracknumber111Service.list(trackQuery);
		for(Needtotracknumber111 needtotracknumber111:list){
			Integer compantId = needtotracknumber111.getCompanyid();
            if(true){
				//开始交运
				needtotracknumber111.setIslabel(1);
				needtotracknumber111.setLabeldescr("开始下载单面...");
				needtotracknumber111Service.saveOrUpdate(needtotracknumber111);

				//订单
				LambdaQueryWrapper<Order0> orderQuery = new LambdaQueryWrapper<Order0>();
				orderQuery.eq(Order0::getId,needtotracknumber111.getOrderid());
				Order0 order0 = order0Service.getOne(orderQuery);
				if(order0==null){
					needtotracknumber111.setIslabel(98);
					needtotracknumber111.setLabeldescr("订单不存在!");
					needtotracknumber111Service.saveOrUpdate(needtotracknumber111);
					return;
				}
				RequestJson requestJson = null;
				if(needtotracknumber111.getRequestjson()!=null){
					requestJson = JSONObject.parseObject(needtotracknumber111.getRequestjson(), RequestJson.class);
				}
				DbMylogisticsaccount mylogisticsaccount = null;
				if(requestJson!=null) {
					FindMylogisticsAccount findMylogisticsAccount = new FindMylogisticsAccount();
					findMylogisticsAccount.setCompanyId(needtotracknumber111.getCompanyid());
					findMylogisticsAccount.setMyLogisticsAccountId(requestJson.getAccountId());

					ResulstCodeWeb<List<DbMylogisticsaccount>> resultDbMylogisticsaccount = restTemplateUtil.getDataListForPost(DbMylogisticsaccount.class, findMylogisticsAccount, "", ConstantConfig.LogisticsUrl + "/findMylogisticsAccount");

					if (resultDbMylogisticsaccount.getAck() != 1000 && resultDbMylogisticsaccount.getData() != null) {
						mylogisticsaccount = resultDbMylogisticsaccount.getData().get(0);
					} else {
						needtotracknumber111.setIslabel(98);
						needtotracknumber111.setLabeldescr("物流账号不存在!");
						needtotracknumber111Service.saveOrUpdate(needtotracknumber111);
						return;
					}
				}


				TrackModel trackModel = new TrackModel();
				trackModel.setOrder0(order0);
				trackModel.setMylogisticsaccount(mylogisticsaccount);

				//交运完成
				TrackResultModel trackResultModel = needtotracknumber111Service.runStep2(trackModel);
				needtotracknumber111.setIslabel(trackResultModel.getFlag());
				needtotracknumber111.setLabeldescr(trackResultModel.getDescr());
				needtotracknumber111Service.saveOrUpdate(needtotracknumber111);

				if("3".equals(trackResultModel.getFlag())){
					DbOrderlabel orderlabel = new DbOrderlabel();
					orderlabel.setCompanyId(needtotracknumber111.getCompanyid());
					orderlabel.setPlatformOrderId(order0.getPlatformorderid());
					ResulstCodeWeb<List<DbOrderlabel>> resultOrderlabel = restTemplateUtil.getDataListForPost(DbOrderlabel.class, orderlabel, "", ConstantConfig.LogisticsUrl + "/syncDbOrderLabel");
				}
			}
		}

		log.info(String.format(" 海河汇下载面单 org.baifei.modules.job.haihehui.HaihehuiLabelJob  !  执行数:"+list.size()+" 时间:" + DateUtils.getTimestamp()));
	}

}
