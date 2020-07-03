package org.baifei.modules.job.weishi;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.baifei.common.util.ConstantConfig;
import org.baifei.common.util.DateUtils;
import org.baifei.modules.entity.Needtotracknumber110;
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
public class WeishiLabelJob implements Job {
	@Autowired
	private INeedtotracknumber110Service needtotracknumber110Service;
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


		LambdaQueryWrapper<Needtotracknumber110> trackQuery = new LambdaQueryWrapper<Needtotracknumber110>();
		trackQuery.eq(Needtotracknumber110::getErrorflag,3);
		trackQuery.eq(Needtotracknumber110::getIslabel,0);
		trackQuery.last("limit 100");
		List<Needtotracknumber110> list = needtotracknumber110Service.list(trackQuery);
		for(Needtotracknumber110 needtotracknumber110:list){
			//开始交运
			needtotracknumber110.setIslabel(1);
			needtotracknumber110.setLabeldescr("正在下载面单...");
			needtotracknumber110Service.saveOrUpdate(needtotracknumber110);
			//订单
			LambdaQueryWrapper<Order0> orderQuery = new LambdaQueryWrapper<Order0>();
			orderQuery.eq(Order0::getId,needtotracknumber110.getOrderid());
			Order0 order0 = order0Service.getOne(orderQuery);
			if(order0==null){
				needtotracknumber110.setIslabel(98);
				needtotracknumber110.setLabeldescr("订单不存在!");
				needtotracknumber110Service.saveOrUpdate(needtotracknumber110);
				return;
			}

			TrackModel trackModel = new TrackModel();
			trackModel.setOrder0(order0);

			TrackResultModel trackResultModel = needtotracknumber110Service.runStep2(trackModel);
			needtotracknumber110.setIslabel(trackResultModel.getFlag());
			needtotracknumber110.setLabeldescr(trackResultModel.getDescr());

			needtotracknumber110Service.saveOrUpdate(needtotracknumber110);

			if("3".equals(trackResultModel.getFlag())){
				DbOrderlabel orderlabel = new DbOrderlabel();
				orderlabel.setCompanyId(needtotracknumber110.getCompanyid());
				orderlabel.setPlatformOrderId(order0.getPlatformorderid());
				ResulstCodeWeb<List<DbOrderlabel>> resultOrderlabel = restTemplateUtil.getDataListForPost(DbOrderlabel.class, orderlabel, "", ConstantConfig.LogisticsUrl + "/syncDbOrderLabel");
			}

		}

		log.info(String.format(" 纬狮下载订单 org.baifei.modules.job.weishi.WeishiLabelJob ! 执行数:"+list.size()+" 时间:" + DateUtils.getTimestamp()));
	}
}
