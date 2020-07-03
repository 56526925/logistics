package org.baifei.modules.job.vova;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.baifei.common.util.ConstantConfig;
import org.baifei.common.util.DateUtils;
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
public class VovaTrackJob2 implements Job {
	@Autowired
	private INeedtotracknumber124Service needtotracknumber124Service;
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


		LambdaQueryWrapper<Needtotracknumber124> trackQuery = new LambdaQueryWrapper<Needtotracknumber124>();
		trackQuery.eq(Needtotracknumber124::getErrorflag,2);
		trackQuery.last("limit 100");
		List<Needtotracknumber124> list = needtotracknumber124Service.list(trackQuery);
		for(Needtotracknumber124 needtotracknumber124:list){
			//开始交运
			needtotracknumber124.setErrorflag(1);
			needtotracknumber124.setErrordescr("正在获取订单号...");
			needtotracknumber124Service.saveOrUpdate(needtotracknumber124);
			//订单
			LambdaQueryWrapper<Order0> orderQuery = new LambdaQueryWrapper<Order0>();
			orderQuery.eq(Order0::getId,needtotracknumber124.getOrderid());
			Order0 order0 = order0Service.getOne(orderQuery);
			if(order0==null){
				needtotracknumber124.setErrorflag(98);
				needtotracknumber124.setErrordescr("订单不存在!");
				needtotracknumber124Service.saveOrUpdate(needtotracknumber124);
				return;
			}


			//店铺信息
			FindShop findShop = new FindShop();
			findShop.setShopId(needtotracknumber124.getShopid());
			findShop.setCompanyId(needtotracknumber124.getCompanyid());

			ResulstCodeWeb<List<DbShop>> resultShop = restTemplateUtil.getDataListForPost(DbShop.class,findShop,"", ConstantConfig.LogisticsUrl+"/findShopList");
			DbShop shop = null;
			if (resultShop.getAck()!=1000 && resultShop.getData()!=null){
				shop = resultShop.getData().get(0);
			}else{
				needtotracknumber124.setErrorflag(98);
				needtotracknumber124.setErrordescr("店铺信息不存在!");
				needtotracknumber124Service.saveOrUpdate(needtotracknumber124);
				return;
			}


//			//交运属性
//			PropertyJson propertyJson = null;
//			if(needtotracknumber124.getPropertyjson()!=null){
//				propertyJson = JSONObject.parseObject(needtotracknumber124.getPropertyjson(), PropertyJson.class);
//			}
//			RequestJson requestJson = null;
//			if(needtotracknumber124.getRequestjson()!=null){
//				requestJson = JSONObject.parseObject(needtotracknumber124.getRequestjson(), RequestJson.class);
//			}
//			//物流账号
//			DbMylogisticsaccount mylogisticsaccount = null;
//			if(requestJson!=null) {
//				FindMylogisticsAccount findMylogisticsAccount = new FindMylogisticsAccount();
//				findMylogisticsAccount.setCompanyId(needtotracknumber124.getCompanyid());
//				findMylogisticsAccount.setMyLogisticsAccountId(requestJson.getAccountId());
//
//				ResulstCodeWeb<List<DbMylogisticsaccount>> resultDbMylogisticsaccount = restTemplateUtil.getDataListForPost(DbMylogisticsaccount.class, findMylogisticsAccount, "", ConstantConfig.LogisticsUrl + "/findMylogisticsAccount");
//
//				if (resultDbMylogisticsaccount.getAck() != 1000 && resultDbMylogisticsaccount.getData() != null) {
//					mylogisticsaccount = resultDbMylogisticsaccount.getData().get(0);
//				} else {
//					needtotracknumber124.setErrorflag(98);
//					needtotracknumber124.setErrordescr("物流账号不存在!");
//					needtotracknumber124Service.saveOrUpdate(needtotracknumber124);
//					return;
//				}
//			}

			TrackModel trackModel = new TrackModel();
			trackModel.setOrder0(order0);
//			trackModel.setMylogisticsaccount(mylogisticsaccount);
			trackModel.setShop(shop);

			TrackResultModel trackResultModel = needtotracknumber124Service.runStep2(trackModel);
			needtotracknumber124.setErrorflag(trackResultModel.getFlag());
			needtotracknumber124.setErrordescr(trackResultModel.getDescr());
			if(trackResultModel.getFlag()==3  ){
				needtotracknumber124.setTracknumber(trackResultModel.getTrackNumber());
				order0.setTracknumber(trackResultModel.getTrackNumber());
				order0.setSynclogisticsstatus(3);
				order0.setIssynclogisticsdescr("获取订单成功！");
				order0Service.saveOrUpdate(order0);
			}

			needtotracknumber124Service.saveOrUpdate(needtotracknumber124);

		}

		log.info(String.format(" Vova获取订单号step2 org.baifei.modules.job.vova.VovaTrackJob2 ! 执行数:"+list.size()+" 时间:" + DateUtils.getTimestamp()));
	}
}
