package org.baifei.modules.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.baifei.modules.api.CallApiJoom;
import org.baifei.modules.api.CallApiQufahuo;
import org.baifei.modules.entity.Needtotracknumber119;
import org.baifei.modules.mapper.Needtotracknumber119Mapper;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber119Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 物流交运
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
@Service
public class Needtotracknumber119ServiceImpl extends ServiceImpl<Needtotracknumber119Mapper, Needtotracknumber119> implements INeedtotracknumber119Service {

    @Autowired
    private CallApiJoom callApiJoom;
    @Override
    public TrackResultModel runStep1(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiJoom.runStep1(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel runStep2(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiJoom.runStep2(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel getChannels(String token) {
        TrackResultModel trackResultModel=callApiJoom.getChannels(token);
        return trackResultModel;
    }
}
