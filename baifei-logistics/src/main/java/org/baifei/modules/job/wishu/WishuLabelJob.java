package org.baifei.modules.job.wishu;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.baifei.common.util.ConstantConfig;
import org.baifei.common.util.DateUtils;
import org.baifei.common.util.SSLClient;
import org.baifei.modules.entity.Needtotracknumber105;
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
public class WishuLabelJob implements Job {
	@Autowired
	private INeedtotracknumber105Service needtotracknumber105Service;
	@Autowired
	private IOrder0Service order0Service;
	@Autowired
	private RestTemplateUtil restTemplateUtil;


	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {


		LambdaQueryWrapper<Needtotracknumber105> trackQuery = new LambdaQueryWrapper<Needtotracknumber105>();
		trackQuery.eq(Needtotracknumber105::getErrorflag,3);
		trackQuery.eq(Needtotracknumber105::getIslabel,0);
		trackQuery.last("limit 100");
		List<Needtotracknumber105> list = needtotracknumber105Service.list(trackQuery);
		for(Needtotracknumber105 needtotracknumber105:list){
			//开始交运
			needtotracknumber105.setIslabel(1);
			needtotracknumber105.setLabeldescr("正在下载订单...");
			needtotracknumber105Service.saveOrUpdate(needtotracknumber105);
			//订单
			LambdaQueryWrapper<Order0> orderQuery = new LambdaQueryWrapper<Order0>();
			orderQuery.eq(Order0::getId,needtotracknumber105.getOrderid());
			Order0 order0 = order0Service.getOne(orderQuery);
			if(order0==null){
				needtotracknumber105.setIslabel(98);
				needtotracknumber105.setLabeldescr("订单不存在!");
				needtotracknumber105Service.saveOrUpdate(needtotracknumber105);
				return;
			}
			RequestJson requestJson =null;
			if(needtotracknumber105.getRequestjson()!=null){
				requestJson = JSONObject.parseObject(needtotracknumber105.getRequestjson(),RequestJson.class);
			}
			//查询物流账号
			DbMylogisticsaccount mylogisticsaccount = null;
			if(requestJson!=null){
				FindMylogisticsAccount findMylogisticsAccount = new FindMylogisticsAccount();
				findMylogisticsAccount.setCompanyId(needtotracknumber105.getCompanyid());
				findMylogisticsAccount.setMyLogisticsAccountId(requestJson.getAccountId());

				ResulstCodeWeb<List<DbMylogisticsaccount>> resultDbMylogisticsaccount = restTemplateUtil.getDataListForPost(DbMylogisticsaccount.class,findMylogisticsAccount,"", ConstantConfig.LogisticsUrl+"/findMylogisticsAccount");

				if (resultDbMylogisticsaccount.getAck()!=1000 && resultDbMylogisticsaccount.getData()!=null){
					mylogisticsaccount = resultDbMylogisticsaccount.getData().get(0);
				}else{
					needtotracknumber105.setIslabel(98);
					needtotracknumber105.setLabeldescr("物流账号不存在!");
					needtotracknumber105Service.saveOrUpdate(needtotracknumber105);
					return;
				}
			}else{
				needtotracknumber105.setIslabel(98);
				needtotracknumber105.setLabeldescr("请设置物流信息!");
				needtotracknumber105Service.saveOrUpdate(needtotracknumber105);
				return;
			}


			TrackModel trackModel = new TrackModel();
			trackModel.setOrder0(order0);
			trackModel.setMylogisticsaccount(mylogisticsaccount);

			String tracknumber = needtotracknumber105.getTracknumber();

			TrackResultModel trackResultModel = needtotracknumber105Service.runStep3(trackModel,tracknumber);
			needtotracknumber105.setIslabel(trackResultModel.getFlag());
			needtotracknumber105.setLabeldescr(trackResultModel.getDescr());

			needtotracknumber105Service.saveOrUpdate(needtotracknumber105);

			if("3".equals(trackResultModel.getFlag())){
				DbOrderlabel orderlabel = new DbOrderlabel();
				orderlabel.setCompanyId(needtotracknumber105.getCompanyid());
				orderlabel.setPlatformOrderId(order0.getPlatformorderid());
				ResulstCodeWeb<List<DbOrderlabel>> resultOrderlabel = restTemplateUtil.getDataListForPost(DbOrderlabel.class, orderlabel, "", ConstantConfig.LogisticsUrl + "/syncDbOrderLabel");
			}

		}
		log.info(String.format(" 下载订单 org.baifei.modules.job.wishu.WishuLabelJob ! 执行数:"+list.size()+" 时间:" + DateUtils.getTimestamp()));
	}
}
