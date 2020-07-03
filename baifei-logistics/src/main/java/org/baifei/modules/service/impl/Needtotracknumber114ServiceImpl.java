package org.baifei.modules.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.baifei.modules.api.CallApiHaihehui;
import org.baifei.modules.api.CallApiQufahuo;
import org.baifei.modules.entity.Needtotracknumber114;
import org.baifei.modules.mapper.Needtotracknumber114Mapper;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber114Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 物流交运
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
@Service
public class Needtotracknumber114ServiceImpl extends ServiceImpl<Needtotracknumber114Mapper, Needtotracknumber114> implements INeedtotracknumber114Service {

    @Autowired
    private CallApiQufahuo callApiQufahuo;
    @Override
    public TrackResultModel runStep1(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiQufahuo.runStep1(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel runStep2(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiQufahuo.runStep2(trackModel);
        return trackResultModel;
    }
    @Override
    public TrackResultModel runStep3(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiQufahuo.runStep3(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel getChannels(String token) {
        TrackResultModel trackResultModel=callApiQufahuo.getChannels(token);
        return trackResultModel;
    }
}
