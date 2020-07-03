package org.baifei.modules.job.joom;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.baifei.common.util.ConstantConfig;
import org.baifei.common.util.DateUtils;
import org.baifei.modules.entity.Needtotracknumber119;
import org.baifei.modules.entity.Order0;
import org.baifei.modules.entity.request.common.DbOrderlabel;
import org.baifei.modules.entity.request.common.FindShop;
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
public class JoomLabelJob implements Job {
	@Autowired
	private INeedtotracknumber119Service needtotracknumber119Service;
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


		LambdaQueryWrapper<Needtotracknumber119> trackQuery = new LambdaQueryWrapper<Needtotracknumber119>();
		trackQuery.eq(Needtotracknumber119::getErrorflag,3);
		trackQuery.eq(Needtotracknumber119::getIslabel,0);
		trackQuery.last("limit 100");
		List<Needtotracknumber119> list = needtotracknumber119Service.list(trackQuery);
		for(Needtotracknumber119 needtotracknumber119:list){
			//开始交运
			needtotracknumber119.setIslabel(1);
			needtotracknumber119.setLabeldescr("正在下载面单...");
			needtotracknumber119Service.saveOrUpdate(needtotracknumber119);
			//订单
			LambdaQueryWrapper<Order0> orderQuery = new LambdaQueryWrapper<Order0>();
			orderQuery.eq(Order0::getId,needtotracknumber119.getOrderid());
			Order0 order0 = order0Service.getOne(orderQuery);
			if(order0==null){
				needtotracknumber119.setIslabel(98);
				needtotracknumber119.setLabeldescr("订单不存在!");
				needtotracknumber119Service.saveOrUpdate(needtotracknumber119);
				return;
			}
			//店铺信息
			FindShop findShop = new FindShop();
			findShop.setShopId(needtotracknumber119.getShopid());
			findShop.setCompanyId(needtotracknumber119.getCompanyid());

			ResulstCodeWeb<List<DbShop>> resultShop = restTemplateUtil.getDataListForPost(DbShop.class,findShop,"", ConstantConfig.LogisticsUrl+"/findShopList");
			DbShop shop = null;
			if (resultShop.getAck()!=1000 && resultShop.getData()!=null){
				shop = resultShop.getData().get(0);
			}else{
				needtotracknumber119.setIslabel(98);
				needtotracknumber119.setLabeldescr("店铺信息不存在!");
				needtotracknumber119Service.saveOrUpdate(needtotracknumber119);
				return;
			}

			TrackModel trackModel = new TrackModel();
			trackModel.setOrder0(order0);
			trackModel.setShop(shop);

			TrackResultModel trackResultModel = needtotracknumber119Service.runStep2(trackModel);
			needtotracknumber119.setIslabel(trackResultModel.getFlag());
			needtotracknumber119.setLabeldescr(trackResultModel.getDescr());

			needtotracknumber119Service.saveOrUpdate(needtotracknumber119);

			if("3".equals(trackResultModel.getFlag())){
				DbOrderlabel orderlabel = new DbOrderlabel();
				orderlabel.setCompanyId(needtotracknumber119.getCompanyid());
				orderlabel.setPlatformOrderId(order0.getPlatformorderid());
				ResulstCodeWeb<List<DbOrderlabel>> resultOrderlabel = restTemplateUtil.getDataListForPost(DbOrderlabel.class, orderlabel, "", ConstantConfig.LogisticsUrl + "/syncDbOrderLabel");
			}

		}

		log.info(String.format(" Joom下载订单 org.baifei.modules.job.joom.JoomLabelJob ! 执行数:"+list.size()+" 时间:" + DateUtils.getTimestamp()));
	}
}
