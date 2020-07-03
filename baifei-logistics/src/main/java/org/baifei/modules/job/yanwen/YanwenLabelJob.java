package org.baifei.modules.job.yanwen;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.baifei.common.util.ConstantConfig;
import org.baifei.common.util.DateUtils;
import org.baifei.modules.entity.Needtotracknumber109;
import org.baifei.modules.entity.Order0;
import org.baifei.modules.entity.Orderitem0;
import org.baifei.modules.entity.Orderplus0;
import org.baifei.modules.entity.request.common.*;
import org.baifei.modules.entity.response.common.DbLogisticschannel;
import org.baifei.modules.entity.response.common.DbMylogisticsaccount;
import org.baifei.modules.entity.response.common.DbMylogisticschannel;
import org.baifei.modules.entity.response.common.DbMylogisticsreturnaddress;
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
public class YanwenLabelJob implements Job {
	@Autowired
	private INeedtotracknumber109Service needtotracknumber109Service;
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


		LambdaQueryWrapper<Needtotracknumber109> trackQuery = new LambdaQueryWrapper<Needtotracknumber109>();
		trackQuery.eq(Needtotracknumber109::getErrorflag,3);
		trackQuery.eq(Needtotracknumber109::getIslabel,0);
		trackQuery.last("limit 100");
		List<Needtotracknumber109> list = needtotracknumber109Service.list(trackQuery);
		for(Needtotracknumber109 needtotracknumber109:list){
			//开始交运
			needtotracknumber109.setIslabel(1);
			needtotracknumber109.setLabeldescr("正在下载面单...");
			needtotracknumber109Service.saveOrUpdate(needtotracknumber109);
			//订单
			LambdaQueryWrapper<Order0> orderQuery = new LambdaQueryWrapper<Order0>();
			orderQuery.eq(Order0::getId,needtotracknumber109.getOrderid());
			Order0 order0 = order0Service.getOne(orderQuery);
			if(order0==null){
				needtotracknumber109.setIslabel(98);
				needtotracknumber109.setLabeldescr("订单不存在!");
				needtotracknumber109Service.saveOrUpdate(needtotracknumber109);
				return;
			}
			//交运属性
			RequestJson requestJson = null;
			if(needtotracknumber109.getRequestjson()!=null){
				requestJson = JSONObject.parseObject(needtotracknumber109.getRequestjson(), RequestJson.class);
			}
			DbMylogisticsaccount mylogisticsaccount = null;
			if(requestJson!=null) {
				FindMylogisticsAccount findMylogisticsAccount = new FindMylogisticsAccount();
				findMylogisticsAccount.setCompanyId(needtotracknumber109.getCompanyid());
				findMylogisticsAccount.setMyLogisticsAccountId(requestJson.getAccountId());

				ResulstCodeWeb<List<DbMylogisticsaccount>> resultDbMylogisticsaccount = restTemplateUtil.getDataListForPost(DbMylogisticsaccount.class, findMylogisticsAccount, "", ConstantConfig.LogisticsUrl + "/findMylogisticsAccount");

				if (resultDbMylogisticsaccount.getAck() != 1000 && resultDbMylogisticsaccount.getData() != null) {
					mylogisticsaccount = resultDbMylogisticsaccount.getData().get(0);
				} else {
					needtotracknumber109.setIslabel(98);
					needtotracknumber109.setLabeldescr("物流账号不存在!");
					needtotracknumber109Service.saveOrUpdate(needtotracknumber109);
					return;
				}
			}

			TrackModel trackModel = new TrackModel();
			trackModel.setOrder0(order0);
			trackModel.setMylogisticsaccount(mylogisticsaccount);


			TrackResultModel trackResultModel = needtotracknumber109Service.runStep2(trackModel);
			needtotracknumber109.setIslabel(trackResultModel.getFlag());
			needtotracknumber109.setLabeldescr(trackResultModel.getDescr());

			needtotracknumber109Service.saveOrUpdate(needtotracknumber109);

			if("3".equals(trackResultModel.getFlag())){
				DbOrderlabel orderlabel = new DbOrderlabel();
				orderlabel.setCompanyId(needtotracknumber109.getCompanyid());
				orderlabel.setPlatformOrderId(order0.getPlatformorderid());
				ResulstCodeWeb<List<DbOrderlabel>> resultOrderlabel = restTemplateUtil.getDataListForPost(DbOrderlabel.class, orderlabel, "", ConstantConfig.LogisticsUrl + "/syncDbOrderLabel");
			}

		}

		log.info(String.format(" 燕文下载订单 org.baifei.modules.job.yanwen.YanwenLabelJob ! 执行数:"+list.size()+" 时间:" + DateUtils.getTimestamp()));
	}
}
