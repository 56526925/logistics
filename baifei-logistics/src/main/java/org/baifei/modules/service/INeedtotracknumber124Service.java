package org.baifei.modules.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.baifei.modules.entity.Needtotracknumber124;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;

/**
 * @Description: 物流交运
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
public interface INeedtotracknumber124Service extends IService<Needtotracknumber124> {
    //获取物流跟踪号1
    TrackResultModel runStep1(TrackModel trackModel);
    //获取物流跟踪号12
    TrackResultModel runStep2(TrackModel trackModel);
    //获取标签
    TrackResultModel runStep3(TrackModel trackModel);
    //获取渠道
    TrackResultModel getChannels(String token);
}
