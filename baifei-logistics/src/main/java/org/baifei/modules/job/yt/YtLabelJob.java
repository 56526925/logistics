package org.baifei.modules.job.yt;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.baifei.common.util.ConstantConfig;
import org.baifei.common.util.DateUtils;
import org.baifei.modules.entity.Needtotracknumber121;
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
public class YtLabelJob implements Job {
	@Autowired
	private INeedtotracknumber121Service needtotracknumber121Service;
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


		LambdaQueryWrapper<Needtotracknumber121> trackQuery = new LambdaQueryWrapper<Needtotracknumber121>();
		trackQuery.eq(Needtotracknumber121::getErrorflag,3);
		trackQuery.eq(Needtotracknumber121::getIslabel,0);
		trackQuery.last("limit 100");
		List<Needtotracknumber121> list = needtotracknumber121Service.list(trackQuery);
		for(Needtotracknumber121 needtotracknumber121:list){
			//开始交运
			needtotracknumber121.setIslabel(1);
			needtotracknumber121.setLabeldescr("正在下载面单...");
			needtotracknumber121Service.saveOrUpdate(needtotracknumber121);
			//订单
			LambdaQueryWrapper<Order0> orderQuery = new LambdaQueryWrapper<Order0>();
			orderQuery.eq(Order0::getId,needtotracknumber121.getOrderid());
			Order0 order0 = order0Service.getOne(orderQuery);
			if(order0==null){
				needtotracknumber121.setIslabel(98);
				needtotracknumber121.setLabeldescr("订单不存在!");
				needtotracknumber121Service.saveOrUpdate(needtotracknumber121);
				return;
			}
			//交运属性
			RequestJson requestJson = null;
			if(needtotracknumber121.getRequestjson()!=null){
				requestJson = JSONObject.parseObject(needtotracknumber121.getRequestjson(), RequestJson.class);
			}
			DbMylogisticsaccount mylogisticsaccount = null;
			if(requestJson!=null) {
				FindMylogisticsAccount findMylogisticsAccount = new FindMylogisticsAccount();
				findMylogisticsAccount.setCompanyId(needtotracknumber121.getCompanyid());
				findMylogisticsAccount.setMyLogisticsAccountId(requestJson.getAccountId());

				ResulstCodeWeb<List<DbMylogisticsaccount>> resultDbMylogisticsaccount = restTemplateUtil.getDataListForPost(DbMylogisticsaccount.class, findMylogisticsAccount, "", ConstantConfig.LogisticsUrl + "/findMylogisticsAccount");

				if (resultDbMylogisticsaccount.getAck() != 1000 && resultDbMylogisticsaccount.getData() != null) {
					mylogisticsaccount = resultDbMylogisticsaccount.getData().get(0);
				} else {
					needtotracknumber121.setIslabel(98);
					needtotracknumber121.setLabeldescr("物流账号不存在!");
					needtotracknumber121Service.saveOrUpdate(needtotracknumber121);
					return;
				}
			}

			TrackModel trackModel = new TrackModel();
			trackModel.setOrder0(order0);
			trackModel.setMylogisticsaccount(mylogisticsaccount);


			TrackResultModel trackResultModel = needtotracknumber121Service.runStep2(trackModel);
			needtotracknumber121.setIslabel(trackResultModel.getFlag());
			needtotracknumber121.setLabeldescr(trackResultModel.getDescr());

			needtotracknumber121Service.saveOrUpdate(needtotracknumber121);

			if("3".equals(trackResultModel.getFlag())){
				DbOrderlabel orderlabel = new DbOrderlabel();
				orderlabel.setCompanyId(needtotracknumber121.getCompanyid());
				orderlabel.setPlatformOrderId(order0.getPlatformorderid());
				ResulstCodeWeb<List<DbOrderlabel>> resultOrderlabel = restTemplateUtil.getDataListForPost(DbOrderlabel.class, orderlabel, "", ConstantConfig.LogisticsUrl + "/syncDbOrderLabel");
			}

		}

		log.info(String.format(" 云途下载订单 org.baifei.modules.job.yt.YtLabelJob ! 执行数:"+list.size()+" 时间:" + DateUtils.getTimestamp()));
	}
}
