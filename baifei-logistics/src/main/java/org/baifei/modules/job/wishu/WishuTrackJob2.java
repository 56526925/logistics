package org.baifei.modules.job.wishu;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.baifei.common.util.ConstantConfig;
import org.baifei.common.util.DateUtils;
import org.baifei.common.util.SSLClient;
import org.baifei.modules.entity.*;
import org.baifei.modules.entity.request.common.*;
import org.baifei.modules.entity.response.common.*;
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
public class WishuTrackJob2 implements Job {
	@Autowired
	private INeedtotracknumber105Service needtotracknumber105Service;
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


		LambdaQueryWrapper<Needtotracknumber105> trackQuery = new LambdaQueryWrapper<Needtotracknumber105>();
		trackQuery.eq(Needtotracknumber105::getErrorflag,2);
		trackQuery.last("limit 100");
		List<Needtotracknumber105> list = needtotracknumber105Service.list(trackQuery);
		for(Needtotracknumber105 needtotracknumber105:list){
			//订单
			LambdaQueryWrapper<Order0> orderQuery = new LambdaQueryWrapper<Order0>();
			orderQuery.eq(Order0::getId,needtotracknumber105.getOrderid());
			Order0 order0 = order0Service.getOne(orderQuery);
			if(order0==null){
				needtotracknumber105.setErrorflag(98);
				needtotracknumber105.setErrordescr("订单不存在!");
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
					needtotracknumber105.setErrorflag(98);
					needtotracknumber105.setErrordescr("物流账号不存在!");
					needtotracknumber105Service.saveOrUpdate(needtotracknumber105);
					return;
				}
			}else{
				needtotracknumber105.setErrorflag(98);
				needtotracknumber105.setErrordescr("请设置物流信息!");
				needtotracknumber105Service.saveOrUpdate(needtotracknumber105);
				return;
			}


			TrackModel trackModel = new TrackModel();
			trackModel.setMylogisticsaccount(mylogisticsaccount);

			String tracknumber1 = needtotracknumber105.getTracknumber1();

			TrackResultModel trackResultModel = needtotracknumber105Service.runStep2(trackModel,tracknumber1);
			needtotracknumber105.setErrorflag(trackResultModel.getFlag());
			needtotracknumber105.setErrordescr(trackResultModel.getDescr());
			if(trackResultModel.getFlag()==3 ){
				needtotracknumber105.setTracknumber(trackResultModel.getTrackNumber());
				order0.setTracknumber(trackResultModel.getTrackNumber());
				order0.setSynclogisticsstatus(3);
				order0.setIssynclogisticsdescr("获取订单成功！");
				order0Service.saveOrUpdate(order0);
			}

			needtotracknumber105Service.saveOrUpdate(needtotracknumber105);

		}

		log.info(String.format(" 获取订单号step2 org.baifei.modules.job.wishu.WishuTrackJob2 ! 执行数:"+list.size()+" 时间:" + DateUtils.getTimestamp()));
	}
}
