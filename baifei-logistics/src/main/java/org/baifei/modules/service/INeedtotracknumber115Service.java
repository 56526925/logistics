package org.baifei.modules.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.baifei.modules.entity.Needtotracknumber115;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;

/**
 * @Description: 物流交运
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
public interface INeedtotracknumber115Service extends IService<Needtotracknumber115> {
    //获取物流跟踪号1
    TrackResultModel runStep1(TrackModel trackModel);
    //获取标签
    TrackResultModel runStep2(TrackModel trackModel);
    //线上订单
    TrackResultModel runStep3(TrackModel trackModel);
    //刷新令牌
    TrackResultModel flushToken();
    //获取渠道
    TrackResultModel getChannels();
}
