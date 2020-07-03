package org.baifei.modules.job.sls;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.baifei.common.util.ConstantConfig;
import org.baifei.common.util.DateUtils;
import org.baifei.modules.entity.Needtotracknumber125;
import org.baifei.modules.entity.Order0;
import org.baifei.modules.entity.request.common.DbOrderlabel;
import org.baifei.modules.entity.request.common.FindMylogisticsAccount;
import org.baifei.modules.entity.request.common.FindShop;
import org.baifei.modules.entity.request.common.RequestJson;
import org.baifei.modules.entity.response.common.DbMylogisticsaccount;
import org.baifei.modules.entity.response.common.DbShop;
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
public class SlsLabelJob implements Job {
	@Autowired
	private INeedtotracknumber125Service needtotracknumber125Service;
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


		LambdaQueryWrapper<Needtotracknumber125> trackQuery = new LambdaQueryWrapper<Needtotracknumber125>();
		trackQuery.eq(Needtotracknumber125::getErrorflag,3);
		trackQuery.eq(Needtotracknumber125::getIslabel,0);
		trackQuery.last("limit 100");
		List<Needtotracknumber125> list = needtotracknumber125Service.list(trackQuery);
		for(Needtotracknumber125 needtotracknumber125:list){
			Integer compantId = needtotracknumber125.getCompanyid();
            if(true){
				//开始交运
				needtotracknumber125.setIslabel(1);
				needtotracknumber125.setLabeldescr("开始下载单面...");
				needtotracknumber125Service.saveOrUpdate(needtotracknumber125);

				//订单
				LambdaQueryWrapper<Order0> orderQuery = new LambdaQueryWrapper<Order0>();
				orderQuery.eq(Order0::getId,needtotracknumber125.getOrderid());
				Order0 order0 = order0Service.getOne(orderQuery);
				if(order0==null){
					needtotracknumber125.setIslabel(98);
					needtotracknumber125.setLabeldescr("订单不存在!");
					needtotracknumber125Service.saveOrUpdate(needtotracknumber125);
					return;
				}
				//店铺信息
				FindShop findShop = new FindShop();
				findShop.setShopId(needtotracknumber125.getShopid());
				findShop.setCompanyId(needtotracknumber125.getCompanyid());

				ResulstCodeWeb<List<DbShop>> resultShop = restTemplateUtil.getDataListForPost(DbShop.class,findShop,"", ConstantConfig.LogisticsUrl+"/findShopList");
				DbShop shop = null;
				if (resultShop.getAck()!=1000 && resultShop.getData()!=null){
					shop = resultShop.getData().get(0);
				}else{
					needtotracknumber125.setErrorflag(98);
					needtotracknumber125.setErrordescr("店铺信息不存在!");
					needtotracknumber125Service.saveOrUpdate(needtotracknumber125);
					return;
				}


				TrackModel trackModel = new TrackModel();
				trackModel.setOrder0(order0);
				trackModel.setShop(shop);

				//交运完成
				TrackResultModel trackResultModel = needtotracknumber125Service.runStep2(trackModel);
				needtotracknumber125.setIslabel(trackResultModel.getFlag());
				needtotracknumber125.setLabeldescr(trackResultModel.getDescr());
				needtotracknumber125Service.saveOrUpdate(needtotracknumber125);

				if("3".equals(trackResultModel.getFlag())){
					DbOrderlabel orderlabel = new DbOrderlabel();
					orderlabel.setCompanyId(needtotracknumber125.getCompanyid());
					orderlabel.setPlatformOrderId(order0.getPlatformorderid());
					ResulstCodeWeb<List<DbOrderlabel>> resultOrderlabel = restTemplateUtil.getDataListForPost(DbOrderlabel.class, orderlabel, "", ConstantConfig.LogisticsUrl + "/syncDbOrderLabel");
				}
			}
		}

		log.info(String.format(" SLS下载面单 org.baifei.modules.job.sls.SlsLabelJob  !  执行数:"+list.size()+" 时间:" + DateUtils.getTimestamp()));
	}

}
