package org.baifei.modules.job.lwe;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.baifei.common.util.ConstantConfig;
import org.baifei.common.util.DateUtils;
import org.baifei.modules.entity.Needtotracknumber120;
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
public class LweLabelJob implements Job {
	@Autowired
	private INeedtotracknumber120Service needtotracknumber120Service;
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


		LambdaQueryWrapper<Needtotracknumber120> trackQuery = new LambdaQueryWrapper<Needtotracknumber120>();
		trackQuery.eq(Needtotracknumber120::getErrorflag,3);
		trackQuery.eq(Needtotracknumber120::getIslabel,0);
		trackQuery.last("limit 100");
		List<Needtotracknumber120> list = needtotracknumber120Service.list(trackQuery);
		for(Needtotracknumber120 needtotracknumber120:list){
			Integer compantId = needtotracknumber120.getCompanyid();
            if(true){
				//开始交运
				needtotracknumber120.setIslabel(1);
				needtotracknumber120.setLabeldescr("开始下载单面...");
				needtotracknumber120Service.saveOrUpdate(needtotracknumber120);

				//订单
				LambdaQueryWrapper<Order0> orderQuery = new LambdaQueryWrapper<Order0>();
				orderQuery.eq(Order0::getId,needtotracknumber120.getOrderid());
				Order0 order0 = order0Service.getOne(orderQuery);
				if(order0==null){
					needtotracknumber120.setIslabel(98);
					needtotracknumber120.setLabeldescr("订单不存在!");
					needtotracknumber120Service.saveOrUpdate(needtotracknumber120);
					return;
				}

				RequestJson requestJson = null;
				if(needtotracknumber120.getRequestjson()!=null){
					requestJson = JSONObject.parseObject(needtotracknumber120.getRequestjson(), RequestJson.class);
				}
				//物流账号
				DbMylogisticsaccount mylogisticsaccount = null;
				if(requestJson!=null) {
					FindMylogisticsAccount findMylogisticsAccount = new FindMylogisticsAccount();
					findMylogisticsAccount.setCompanyId(needtotracknumber120.getCompanyid());
					findMylogisticsAccount.setMyLogisticsAccountId(requestJson.getAccountId());

					ResulstCodeWeb<List<DbMylogisticsaccount>> resultDbMylogisticsaccount = restTemplateUtil.getDataListForPost(DbMylogisticsaccount.class, findMylogisticsAccount, "", ConstantConfig.LogisticsUrl + "/findMylogisticsAccount");

					if (resultDbMylogisticsaccount.getAck() != 1000 && resultDbMylogisticsaccount.getData() != null) {
						mylogisticsaccount = resultDbMylogisticsaccount.getData().get(0);
					} else {
						needtotracknumber120.setIslabel(98);
						needtotracknumber120.setLabeldescr("物流账号不存在!");
						needtotracknumber120Service.saveOrUpdate(needtotracknumber120);
						return;
					}
				}


				TrackModel trackModel = new TrackModel();
				trackModel.setOrder0(order0);
				trackModel.setMylogisticsaccount(mylogisticsaccount);


				//交运完成
				TrackResultModel trackResultModel = needtotracknumber120Service.runStep2(trackModel);
				needtotracknumber120.setIslabel(trackResultModel.getFlag());
				needtotracknumber120.setLabeldescr(trackResultModel.getDescr());
				needtotracknumber120Service.saveOrUpdate(needtotracknumber120);

				if("3".equals(trackResultModel.getFlag())){
					DbOrderlabel orderlabel = new DbOrderlabel();
					orderlabel.setCompanyId(needtotracknumber120.getCompanyid());
					orderlabel.setPlatformOrderId(order0.getPlatformorderid());
					ResulstCodeWeb<List<DbOrderlabel>> resultOrderlabel = restTemplateUtil.getDataListForPost(DbOrderlabel.class, orderlabel, "", ConstantConfig.LogisticsUrl + "/syncDbOrderLabel");
				}
			}
		}

		log.info(String.format(" lwe下载订单 org.baifei.modules.job.lwe.LweLabelJob  !  执行数:"+list.size()+" 时间:" + DateUtils.getTimestamp()));
	}
}
