package org.baifei.modules.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.baifei.modules.api.CallApiSMWL;
import org.baifei.modules.api.CallApiUBI;
import org.baifei.modules.entity.Needtotracknumber127;
import org.baifei.modules.mapper.Needtotracknumber127Mapper;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber127Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 物流交运
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
@Service
public class Needtotracknumber127ServiceImpl extends ServiceImpl<Needtotracknumber127Mapper, Needtotracknumber127> implements INeedtotracknumber127Service {

    @Autowired
    private CallApiSMWL callApiSMWL;
    @Override
    public TrackResultModel runStep1(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiSMWL.runStep1(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel runStep2(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiSMWL.runStep2(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel getChannels(String token) {
        TrackResultModel trackResultModel=callApiSMWL.getChannels(token);
        return trackResultModel;
    }
}
