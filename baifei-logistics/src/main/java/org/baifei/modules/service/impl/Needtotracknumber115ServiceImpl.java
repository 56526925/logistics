package org.baifei.modules.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.baifei.modules.api.CallApiFT;
import org.baifei.modules.api.CallApiHaihehui;
import org.baifei.modules.entity.Needtotracknumber115;
import org.baifei.modules.mapper.Needtotracknumber115Mapper;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber115Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 物流交运
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
@Service
public class Needtotracknumber115ServiceImpl extends ServiceImpl<Needtotracknumber115Mapper, Needtotracknumber115> implements INeedtotracknumber115Service {

    @Autowired
    private CallApiFT callApiFT;
    @Override
    public TrackResultModel runStep1(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiFT.runStep1(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel runStep2(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiFT.runStep2(trackModel);
        return trackResultModel;
    }
    @Override
    public TrackResultModel runStep3(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiFT.runStep3(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel flushToken() {
        TrackResultModel trackResultModel=callApiFT.flushToken();
        return trackResultModel;
    }


    @Override
    public TrackResultModel getChannels() {
        TrackResultModel trackResultModel=callApiFT.getChannels();
        return trackResultModel;
    }
}
