package org.baifei.modules.job.smtol;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.baifei.common.util.ConstantConfig;
import org.baifei.common.util.DateUtils;
import org.baifei.common.util.SSLClient;
import org.baifei.modules.entity.*;
import org.baifei.modules.entity.request.common.*;
import org.baifei.modules.entity.response.common.DbMylogisticsaccount;
import org.baifei.modules.entity.response.common.DbMylogisticschannel;
import org.baifei.modules.entity.response.common.DbMylogisticsreturnaddress;
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
public class SmtolLabelJob implements Job {
	@Autowired
	private INeedtotracknumber103Service needtotracknumber103Service;
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


		LambdaQueryWrapper<Needtotracknumber103> trackQuery = new LambdaQueryWrapper<Needtotracknumber103>();
		trackQuery.eq(Needtotracknumber103::getIslabel,0);
		trackQuery.eq(Needtotracknumber103::getErrorflag,3);
		trackQuery.last("limit 100");
		List<Needtotracknumber103> list = needtotracknumber103Service.list(trackQuery);
		for(Needtotracknumber103 needtotracknumber103:list){
			//开始交运
			needtotracknumber103.setIslabel(1);
			needtotracknumber103.setLabeldescr("正在下载单面...");
			needtotracknumber103Service.saveOrUpdate(needtotracknumber103);
			//订单
			LambdaQueryWrapper<Order0> orderQuery = new LambdaQueryWrapper<Order0>();
			orderQuery.eq(Order0::getId,needtotracknumber103.getOrderid());
			Order0 order0 = order0Service.getOne(orderQuery);
			if(order0==null){
				needtotracknumber103.setIslabel(98);
				needtotracknumber103.setLabeldescr("订单不存在!");
				needtotracknumber103Service.saveOrUpdate(needtotracknumber103);
				return;
			}

			//店铺信息
			FindShop findShop = new FindShop();
			findShop.setShopId(needtotracknumber103.getShopid());
			findShop.setCompanyId(needtotracknumber103.getCompanyid());

			ResulstCodeWeb<List<DbShop>> resultShop = restTemplateUtil.getDataListForPost(DbShop.class,findShop,"", ConstantConfig.LogisticsUrl+"/findShopList");
			DbShop shop = null;
			if (resultShop.getAck()!=1000 && resultShop.getData()!=null){
				shop = resultShop.getData().get(0);
			}else{
				needtotracknumber103.setIslabel(98);
				needtotracknumber103.setLabeldescr("店铺信息不存在!");
				needtotracknumber103Service.saveOrUpdate(needtotracknumber103);
				return;
			}

			TrackModel trackModel = new TrackModel();
			trackModel.setShop(shop);
			trackModel.setOrder0(order0);


			TrackResultModel trackResultModel = needtotracknumber103Service.runStep2(trackModel);
			needtotracknumber103.setIslabel(trackResultModel.getFlag());
			needtotracknumber103.setLabeldescr(trackResultModel.getDescr());

			needtotracknumber103Service.saveOrUpdate(needtotracknumber103);

			if("3".equals(trackResultModel.getFlag())){
				DbOrderlabel orderlabel = new DbOrderlabel();
				orderlabel.setCompanyId(needtotracknumber103.getCompanyid());
				orderlabel.setPlatformOrderId(order0.getPlatformorderid());
				ResulstCodeWeb<List<DbOrderlabel>> resultOrderlabel = restTemplateUtil.getDataListForPost(DbOrderlabel.class, orderlabel, "", ConstantConfig.LogisticsUrl + "/syncDbOrderLabel");
			}

		}

		log.info(String.format(" smtol获取订单号step2 org.baifei.modules.job.smtol.SmtolLabelJob ! 执行数:"+list.size()+" 时间:" + DateUtils.getTimestamp()));
	}
}
