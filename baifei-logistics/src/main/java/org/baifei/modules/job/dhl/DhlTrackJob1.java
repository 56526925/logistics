package org.baifei.modules.job.dhl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.baifei.common.util.ConstantConfig;
import org.baifei.common.util.DateUtils;
import org.baifei.modules.entity.Needtotracknumber112;
import org.baifei.modules.entity.Order0;
import org.baifei.modules.entity.Orderitem0;
import org.baifei.modules.entity.Orderplus0;
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
public class DhlTrackJob1 implements Job {
	@Autowired
	private INeedtotracknumber112Service needtotracknumber112Service;
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


		LambdaQueryWrapper<Needtotracknumber112> trackQuery = new LambdaQueryWrapper<Needtotracknumber112>();
		trackQuery.eq(Needtotracknumber112::getErrorflag,0);
		trackQuery.last("limit 100");
		List<Needtotracknumber112> list = needtotracknumber112Service.list(trackQuery);
		for(Needtotracknumber112 needtotracknumber112:list){
			//开始交运
			needtotracknumber112.setErrorflag(1);
			needtotracknumber112.setErrordescr("正在获取订单号...");
			needtotracknumber112Service.saveOrUpdate(needtotracknumber112);
			//订单
			LambdaQueryWrapper<Order0> orderQuery = new LambdaQueryWrapper<Order0>();
			orderQuery.eq(Order0::getId,needtotracknumber112.getOrderid());
			Order0 order0 = order0Service.getOne(orderQuery);
			if(order0==null){
				needtotracknumber112.setErrorflag(98);
				needtotracknumber112.setErrordescr("订单不存在!");
				needtotracknumber112Service.saveOrUpdate(needtotracknumber112);
				return;
			}
			//订单商品详情
			List<Orderitem0> orderItem0 = orderitem0Service.getItemsProduct(needtotracknumber112.getOrderid());
			if(orderItem0==null || orderItem0.size()<=0){
				needtotracknumber112.setErrorflag(98);
				needtotracknumber112.setErrordescr("订单商品详情不存在!");
				needtotracknumber112Service.saveOrUpdate(needtotracknumber112);
				return;
			}

			//订单买家信息
			LambdaQueryWrapper<Orderplus0> orderplus0Query = new LambdaQueryWrapper<Orderplus0>();
			orderplus0Query.eq(Orderplus0::getOrderid,needtotracknumber112.getOrderid());
			Orderplus0 orderplus0 = orderplus0Service.getOne(orderplus0Query);
			if(orderplus0==null ){
				needtotracknumber112.setErrorflag(98);
				needtotracknumber112.setErrordescr("订单买家信息不存在!");
				needtotracknumber112Service.saveOrUpdate(needtotracknumber112);
				return;
			}
			//物流渠道
			FindLogisticsChannel findLogisticsChannel = new FindLogisticsChannel();
			findLogisticsChannel.setLogisticsChannelId(needtotracknumber112.getLogisticschannelid());
			findLogisticsChannel.setLogisticsId(needtotracknumber112.getLogisticsid());

			ResulstCodeWeb<List<DbLogisticschannel>> resultLogisticschannel = restTemplateUtil.getDataListForPost(DbLogisticschannel.class,findLogisticsChannel,"",ConstantConfig.LogisticsUrl+"/findLogisticsChannel");
//			DbLogisticschannel logisticschannel = null;
//			if (resultLogisticschannel.getAck()!=1000 && resultLogisticschannel.getData()!=null){
//				logisticschannel = resultLogisticschannel.getData().get(0);
//			}else{
//				needtotracknumber112.setErrorflag(98);
//				needtotracknumber112.setErrordescr("物流渠道不存在!");
//				needtotracknumber112Service.saveOrUpdate(needtotracknumber112);
//				return;
//			}
			DbLogisticschannel logisticschannel = new DbLogisticschannel();

			//货运方式
			FindMyLogisticsChannel findMyLogisticsChannel = new FindMyLogisticsChannel();
			findMyLogisticsChannel.setCompanyId(needtotracknumber112.getCompanyid());
			findMyLogisticsChannel.setMyLogisticsChannelId(needtotracknumber112.getMylogisticschannelid());

			ResulstCodeWeb<List<DbMylogisticschannel>> resultMylogisticschannel = restTemplateUtil.getDataListForPost(DbMylogisticschannel.class,findMyLogisticsChannel,"", ConstantConfig.LogisticsUrl+"/findMylogisticsChannel");
			DbMylogisticschannel mylogisticschannel = null;
			if (resultMylogisticschannel.getAck()!=1000 && resultMylogisticschannel.getData()!=null){
				mylogisticschannel = resultMylogisticschannel.getData().get(0);
			}else{
				needtotracknumber112.setErrorflag(98);
				needtotracknumber112.setErrordescr("货运方式不存在!");
				needtotracknumber112Service.saveOrUpdate(needtotracknumber112);
				return;
			}

			//寄件信息 DbMylogisticschannel中 returnAddress1
			FindMyLogisticsReturnAddress findMyLogisticsReturnAddress = new FindMyLogisticsReturnAddress();
			findMyLogisticsReturnAddress.setCompanyId(needtotracknumber112.getCompanyid());
			findMyLogisticsReturnAddress.setLogisticsId(needtotracknumber112.getLogisticsid());
			findMyLogisticsReturnAddress.setMyLogisticsReturnAddressId(mylogisticschannel.getReturnAddress1());

			ResulstCodeWeb<List<DbMylogisticsreturnaddress>> resultDbMylogisticsreturnaddress = restTemplateUtil.getDataListForPost(DbMylogisticsreturnaddress.class,findMyLogisticsReturnAddress,"",ConstantConfig.LogisticsUrl+"/findMylogisticsReturnAddress");
			DbMylogisticsreturnaddress mylogisticsreturnaddress = null;
			if (resultDbMylogisticsreturnaddress.getAck()!=1000 && resultDbMylogisticsreturnaddress.getData()!=null){
				mylogisticsreturnaddress = resultDbMylogisticsreturnaddress.getData().get(0);
			}else{
				needtotracknumber112.setErrorflag(98);
				needtotracknumber112.setErrordescr("寄件信息不存在!");
				needtotracknumber112Service.saveOrUpdate(needtotracknumber112);
				return;
			}

			//交运属性
			PropertyJson propertyJson = null;
			if(needtotracknumber112.getPropertyjson()!=null){
				propertyJson = JSONObject.parseObject(needtotracknumber112.getPropertyjson(), PropertyJson.class);
			}
			RequestJson requestJson = null;
			if(needtotracknumber112.getRequestjson()!=null){
				requestJson = JSONObject.parseObject(needtotracknumber112.getRequestjson(), RequestJson.class);
			}
			//物流账号
			DbMylogisticsaccount mylogisticsaccount = null;
			//获得仓库
			DbMylogisticswarehouse dbMylogisticswarehouse =null;
			if(requestJson!=null){
				FindMylogisticsAccount findMylogisticsAccount = new FindMylogisticsAccount();
				findMylogisticsAccount.setCompanyId(needtotracknumber112.getCompanyid());
				findMylogisticsAccount.setMyLogisticsAccountId(requestJson.getAccountId());

				ResulstCodeWeb<List<DbMylogisticsaccount>> resultDbMylogisticsaccount = restTemplateUtil.getDataListForPost(DbMylogisticsaccount.class,findMylogisticsAccount,"",ConstantConfig.LogisticsUrl+"/findMylogisticsAccount");

				if (resultDbMylogisticsaccount.getAck()!=1000 && resultDbMylogisticsaccount.getData()!=null){
					mylogisticsaccount = resultDbMylogisticsaccount.getData().get(0);
				}else{
					needtotracknumber112.setErrorflag(98);
					needtotracknumber112.setErrordescr("物流账号不存在!");
					needtotracknumber112Service.saveOrUpdate(needtotracknumber112);
					return;
				}
				FindMyLogisticsWarehouse findMyLogisticsWarehouse = new FindMyLogisticsWarehouse();
				findMyLogisticsWarehouse.setCompanyId(needtotracknumber112.getCompanyid());
				findMyLogisticsWarehouse.setLogisticsId(needtotracknumber112.getLogisticsid());
				findMyLogisticsWarehouse.setMyLogisticsWarehouseCode(requestJson.getWarehouseCode());

				ResulstCodeWeb<List<DbMylogisticswarehouse>> resultDbMylogisticswarehouse = restTemplateUtil.getDataListForPost(DbMylogisticswarehouse.class,findMyLogisticsWarehouse,"",ConstantConfig.LogisticsUrl+"/findMylogisticsWarehouse");

				if (resultDbMylogisticswarehouse.getAck()!=1000 && resultDbMylogisticswarehouse.getData()!=null){
					dbMylogisticswarehouse = resultDbMylogisticswarehouse.getData().get(0);
				}else{
					needtotracknumber112.setErrorflag(98);
					needtotracknumber112.setErrordescr("揽收信息不存在!");
					needtotracknumber112Service.saveOrUpdate(needtotracknumber112);
					return;
				}

			}else{
				needtotracknumber112.setErrorflag(98);
				needtotracknumber112.setErrordescr("请设置物流信息!");
				needtotracknumber112Service.saveOrUpdate(needtotracknumber112);
				return;
			}

			TrackModel trackModel = new TrackModel();
			trackModel.setOrder0(order0);
			trackModel.setOrderitem0(orderItem0);
			trackModel.setOrderplus0(orderplus0);
			trackModel.setDbMylogisticschannel(mylogisticschannel);
			trackModel.setMylogisticsaccount(mylogisticsaccount);
			trackModel.setDbMylogisticsreturnaddress(mylogisticsreturnaddress);
			trackModel.setPropertyJson(propertyJson);
			trackModel.setDbMylogisticswarehouse(dbMylogisticswarehouse);
			trackModel.setDbLogisticschannel(logisticschannel);

			TrackResultModel trackResultModel = needtotracknumber112Service.runStep1(trackModel);
			needtotracknumber112.setErrorflag(trackResultModel.getFlag());
			needtotracknumber112.setErrordescr(trackResultModel.getDescr());
			if(trackResultModel.getFlag()==3 ){
				needtotracknumber112.setTracknumber(trackResultModel.getTrackNumber());
				order0.setTracknumber(trackResultModel.getTrackNumber());
				order0.setSynclogisticsstatus(3);
				order0.setIssynclogisticsdescr("获取订单成功！");
				order0Service.saveOrUpdate(order0);
			}

			needtotracknumber112Service.saveOrUpdate(needtotracknumber112);

		}

		log.info(String.format(" DHL获取订单号step1 org.baifei.modules.job.dhl.DhlTrackJob1 ! 执行数:"+list.size()+" 时间:" + DateUtils.getTimestamp()));
	}
}
