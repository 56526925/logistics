package org.baifei.modules.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.baifei.modules.api.CallApiJoom;
import org.baifei.modules.api.CallApiMyMall;
import org.baifei.modules.api.CallApiVOVA;
import org.baifei.modules.entity.Needtotracknumber124;
import org.baifei.modules.mapper.Needtotracknumber124Mapper;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber124Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 物流交运
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
@Service
public class Needtotracknumber124ServiceImpl extends ServiceImpl<Needtotracknumber124Mapper, Needtotracknumber124> implements INeedtotracknumber124Service {

    @Autowired
    private CallApiVOVA callApiVova;
    @Override
    public TrackResultModel runStep1(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiVova.runStep1(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel runStep2(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiVova.runStep2(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel runStep3(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiVova.runStep3(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel getChannels(String token) {
        TrackResultModel trackResultModel=callApiVova.getChannels(token);
        return trackResultModel;
    }
}
