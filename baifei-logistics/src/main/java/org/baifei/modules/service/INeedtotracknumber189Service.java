package org.baifei.modules.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.baifei.modules.entity.Needtotracknumber189;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;

/**
 * @Description: 物流交运
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
public interface INeedtotracknumber189Service extends IService<Needtotracknumber189> {
    //获取物流跟踪号1
    TrackResultModel runStep1(TrackModel trackModel);
    //获取标签
    TrackResultModel runStep2(TrackModel trackModel);
}
