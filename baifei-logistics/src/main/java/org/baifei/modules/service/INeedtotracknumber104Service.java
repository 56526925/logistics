package org.baifei.modules.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.baifei.modules.entity.Needtotracknumber104;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;

/**
 * @Description: 物流交运
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
public interface INeedtotracknumber104Service extends IService<Needtotracknumber104> {
    //获取物流跟踪号1
    TrackResultModel runStep1(TrackModel trackModel);
    //获取仓库
    TrackResultModel getWarehouse(String appkey ,String appSecret);
    //获取渠道
    TrackResultModel getChannels(String appkey ,String appSecret,String wareHouseCode);
}
