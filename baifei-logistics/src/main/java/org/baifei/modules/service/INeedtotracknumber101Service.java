package org.baifei.modules.service;

import org.baifei.modules.entity.Needtotracknumber101;
import com.baomidou.mybatisplus.extension.service.IService;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;

/**
 * @Description: 物流交运
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
public interface INeedtotracknumber101Service extends IService<Needtotracknumber101> {
    //获取物流跟踪号1
    TrackResultModel runStep1(TrackModel trackModel);
    //获取物流跟踪号2
    TrackResultModel runStep2(TrackModel trackModel);
    //获取标签
    TrackResultModel runStep3(TrackModel trackModel);
    //获取渠道
    TrackResultModel getChannels();
}
