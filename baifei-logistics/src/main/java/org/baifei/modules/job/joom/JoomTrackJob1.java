package org.baifei.modules.job.joom;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.baifei.common.util.ConstantConfig;
import org.baifei.common.util.DateUtils;
import org.baifei.modules.entity.Needtotracknumber119;
import org.baifei.modules.entity.Order0;
import org.baifei.modules.entity.Orderitem0;
import org.baifei.modules.entity.Orderplus0;
import org.baifei.modules.entity.request.common.*;
import org.baifei.modules.entity.response.common.*;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber119Service;
import org.baifei.modules.service.IOrder0Service;
import org.baifei.modules.service.IOrderitem0Service;
import org.baifei.modules.service.IOrderplus0Service;
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
public class JoomTrackJob1 implements Job {
	@Autowired
	private INeedtotracknumber119Service needtotracknumber119Service;
	@Autowired
	private IOrder0Service order0Service;
	@Autowired
	private IOrderitem0Service orderitem0Service;
	@Autowired
	private IOrderplus0Service orderplus0Service;
	@Autowired
	private RestTemplateUtil restTemplateUtil;


	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {


		LambdaQueryWrapper<Needtotracknumber119> trackQuery = new LambdaQueryWrapper<Needtotracknumber119>();
		trackQuery.eq(Needtotracknumber119::getErrorflag,0);
		trackQuery.last("limit 100");
		List<Needtotracknumber119> list = needtotracknumber119Service.list(trackQuery);
		for(Needtotracknumber119 needtotracknumber119:list){
			//开始交运
			needtotracknumber119.setErrorflag(1);
			needtotracknumber119.setErrordescr("正在获取订单号...");
			needtotracknumber119Service.saveOrUpdate(needtotracknumber119);
			//订单
			LambdaQueryWrapper<Order0> orderQuery = new LambdaQueryWrapper<Order0>();
			orderQuery.eq(Order0::getId,needtotracknumber119.getOrderid());
			Order0 order0 = order0Service.getOne(orderQuery);
			if(order0==null){
				needtotracknumber119.setErrorflag(98);
				needtotracknumber119.setErrordescr("订单不存在!");
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
				needtotracknumber119.setErrorflag(98);
				needtotracknumber119.setErrordescr("店铺信息不存在!");
				needtotracknumber119Service.saveOrUpdate(needtotracknumber119);
				return;
			}

			//交运属性
			PropertyJson propertyJson = null;
			if(needtotracknumber119.getPropertyjson()!=null){
				propertyJson = JSONObject.parseObject(needtotracknumber119.getPropertyjson(), PropertyJson.class);
			}
			RequestJson requestJson = null;
			if(needtotracknumber119.getRequestjson()!=null){
				requestJson = JSONObject.parseObject(needtotracknumber119.getRequestjson(), RequestJson.class);
			}

			TrackModel trackModel = new TrackModel();
			trackModel.setOrder0(order0);
			trackModel.setShop(shop);
			trackModel.setRequestJson(requestJson);
			trackModel.setPropertyJson(propertyJson);


			TrackResultModel trackResultModel = needtotracknumber119Service.runStep1(trackModel);
			needtotracknumber119.setErrorflag(trackResultModel.getFlag());
			needtotracknumber119.setErrordescr(trackResultModel.getDescr());
			if(trackResultModel.getFlag()==3){
				needtotracknumber119.setTracknumber(trackResultModel.getTrackNumber());
				order0.setTracknumber(trackResultModel.getTrackNumber());
				order0.setSynclogisticsstatus(3);
				order0.setIssynclogisticsdescr("获取订单成功！");
				order0Service.saveOrUpdate(order0);
			}

			needtotracknumber119Service.saveOrUpdate(needtotracknumber119);

		}

		log.info(String.format(" Joom获取订单号step1 org.baifei.modules.job.joom.JoomTrackJob1 ! 执行数:"+list.size()+" 时间:" + DateUtils.getTimestamp()));
	}
}
