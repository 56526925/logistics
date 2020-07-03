package org.baifei.modules.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.baifei.modules.api.CallApiJoom;
import org.baifei.modules.api.CallApiYT;
import org.baifei.modules.entity.Needtotracknumber121;
import org.baifei.modules.mapper.Needtotracknumber121Mapper;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber121Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 物流交运
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
@Service
public class Needtotracknumber121ServiceImpl extends ServiceImpl<Needtotracknumber121Mapper, Needtotracknumber121> implements INeedtotracknumber121Service {

    @Autowired
    private CallApiYT callApiYT;
    @Override
    public TrackResultModel runStep1(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiYT.runStep1(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel runStep2(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiYT.runStep2(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel getChannels(String token) {
        TrackResultModel trackResultModel=callApiYT.getChannels(token);
        return trackResultModel;
    }
}
