package org.baifei.modules.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.baifei.modules.api.CallApiHaihehui;
import org.baifei.modules.api.CallApiSLS;
import org.baifei.modules.entity.Needtotracknumber125;
import org.baifei.modules.mapper.Needtotracknumber125Mapper;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber125Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 物流交运
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
@Service
public class Needtotracknumber125ServiceImpl extends ServiceImpl<Needtotracknumber125Mapper, Needtotracknumber125> implements INeedtotracknumber125Service {

    @Autowired
    private CallApiSLS callApiSLS;
    @Override
    public TrackResultModel runStep1(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiSLS.runStep1(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel runStep2(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiSLS.runStep2(trackModel);
        return trackResultModel;
    }
}
