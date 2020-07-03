package org.baifei.modules.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.baifei.modules.api.CallApiSunYou;
import org.baifei.modules.entity.Needtotracknumber108;
import org.baifei.modules.mapper.Needtotracknumber108Mapper;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber108Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 物流交运
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
@Service
public class Needtotracknumber108ServiceImpl extends ServiceImpl<Needtotracknumber108Mapper, Needtotracknumber108> implements INeedtotracknumber108Service {

    @Autowired
    private CallApiSunYou callApiShunYou;
    @Override
    public TrackResultModel runStep1(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiShunYou.runStep1(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel runStep2(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiShunYou.runStep2(trackModel);
        return trackResultModel;
    }
    @Override
    public TrackResultModel runStep3(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiShunYou.runStep3(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel getChannels(String token) {
        TrackResultModel trackResultModel=callApiShunYou.getChannels(token);
        return trackResultModel;
    }
}
