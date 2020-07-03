package org.baifei.modules.job.qufahuo;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.baifei.common.util.ConstantConfig;
import org.baifei.common.util.DateUtils;
import org.baifei.modules.entity.Needtotracknumber114;
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
public class QufahuoLabelJob implements Job {
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
		trackQuery.eq(Needtotracknumber114::getErrorflag,3);
		trackQuery.eq(Needtotracknumber114::getIslabel,0);
		trackQuery.last("limit 100");
		List<Needtotracknumber114> list = needtotracknumber114Service.list(trackQuery);
		for(Needtotracknumber114 needtotracknumber114:list){
			Integer compantId = needtotracknumber114.getCompanyid();
            if(true){
				//开始交运
				needtotracknumber114.setIslabel(1);
				needtotracknumber114.setLabeldescr("开始下载单面...");
				needtotracknumber114Service.saveOrUpdate(needtotracknumber114);

				//订单
				LambdaQueryWrapper<Order0> orderQuery = new LambdaQueryWrapper<Order0>();
				orderQuery.eq(Order0::getId,needtotracknumber114.getOrderid());
				Order0 order0 = order0Service.getOne(orderQuery);
				if(order0==null){
					needtotracknumber114.setIslabel(98);
					needtotracknumber114.setLabeldescr("订单不存在!");
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
						needtotracknumber114.setIslabel(98);
						needtotracknumber114.setLabeldescr("物流账号不存在!");
						needtotracknumber114Service.saveOrUpdate(needtotracknumber114);
						return;
					}
				}


				TrackModel trackModel = new TrackModel();
				trackModel.setOrder0(order0);
				trackModel.setMylogisticsaccount(mylogisticsaccount);


				//交运完成
				TrackResultModel trackResultModel = needtotracknumber114Service.runStep3(trackModel);
				needtotracknumber114.setIslabel(trackResultModel.getFlag());
				needtotracknumber114.setLabeldescr(trackResultModel.getDescr());
				needtotracknumber114Service.saveOrUpdate(needtotracknumber114);

				if("3".equals(trackResultModel.getFlag())){
					DbOrderlabel orderlabel = new DbOrderlabel();
					orderlabel.setCompanyId(needtotracknumber114.getCompanyid());
					orderlabel.setPlatformOrderId(order0.getPlatformorderid());
					ResulstCodeWeb<List<DbOrderlabel>> resultOrderlabel = restTemplateUtil.getDataListForPost(DbOrderlabel.class, orderlabel, "", ConstantConfig.LogisticsUrl + "/syncDbOrderLabel");
				}
			}
		}

		log.info(String.format(" 去发货下载面单 org.baifei.modules.job.qufahuo.QufahuoLabelJob  !  执行数:"+list.size()+" 时间:" + DateUtils.getTimestamp()));
	}
}
