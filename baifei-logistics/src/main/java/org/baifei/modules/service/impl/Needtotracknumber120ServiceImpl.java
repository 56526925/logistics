package org.baifei.modules.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.baifei.modules.api.CallApiFT;
import org.baifei.modules.api.CallApiLwe;
import org.baifei.modules.entity.Needtotracknumber120;
import org.baifei.modules.mapper.Needtotracknumber120Mapper;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber120Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 物流交运
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
@Service
public class Needtotracknumber120ServiceImpl extends ServiceImpl<Needtotracknumber120Mapper, Needtotracknumber120> implements INeedtotracknumber120Service {

    @Autowired
    private CallApiLwe callApiLwe;
    @Override
    public TrackResultModel runStep1(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiLwe.runStep1(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel runStep2(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiLwe.runStep2(trackModel);
        return trackResultModel;
    }


    @Override
    public TrackResultModel flushToken(Integer accountId) {
        TrackResultModel trackResultModel=callApiLwe.flushToken(accountId);
        return trackResultModel;
    }


}
