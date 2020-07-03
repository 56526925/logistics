package org.baifei.modules.service.impl;

import org.baifei.modules.api.CallApiPOST;
import org.baifei.modules.entity.Needtotracknumber101;
import org.baifei.modules.mapper.Needtotracknumber101Mapper;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber101Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 物流交运
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
@Service
public class Needtotracknumber101ServiceImpl extends ServiceImpl<Needtotracknumber101Mapper, Needtotracknumber101> implements INeedtotracknumber101Service {

    @Autowired
    private CallApiPOST callApiPOST;
    @Override
    public TrackResultModel runStep1(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiPOST.runStep1(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel runStep2(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiPOST.runStep2(trackModel);
        return trackResultModel;
    }
    @Override
    public TrackResultModel runStep3(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiPOST.runStep3(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel getChannels() {
        TrackResultModel trackResultModel=callApiPOST.getChannels();
        return trackResultModel;
    }
}
