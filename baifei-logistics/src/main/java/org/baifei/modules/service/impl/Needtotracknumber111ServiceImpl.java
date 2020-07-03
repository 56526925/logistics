package org.baifei.modules.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.baifei.modules.api.CallApiHaihehui;
import org.baifei.modules.api.CallApiWeishi;
import org.baifei.modules.entity.Needtotracknumber111;
import org.baifei.modules.mapper.Needtotracknumber111Mapper;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber111Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 物流交运
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
@Service
public class Needtotracknumber111ServiceImpl extends ServiceImpl<Needtotracknumber111Mapper, Needtotracknumber111> implements INeedtotracknumber111Service {

    @Autowired
    private CallApiHaihehui callApiHaihehui;
    @Override
    public TrackResultModel runStep1(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiHaihehui.runStep1(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel runStep2(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiHaihehui.runStep2(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel getChannels(String token) {
        TrackResultModel trackResultModel=callApiHaihehui.getChannels(token);
        return trackResultModel;
    }
}
