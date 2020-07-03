package org.baifei.modules.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.baifei.modules.api.CallApiJLTZ;
import org.baifei.modules.api.CallApiSMWL;
import org.baifei.modules.entity.Needtotracknumber189;
import org.baifei.modules.mapper.Needtotracknumber189Mapper;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber189Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 物流交运
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
@Service
public class Needtotracknumber189ServiceImpl extends ServiceImpl<Needtotracknumber189Mapper, Needtotracknumber189> implements INeedtotracknumber189Service {

    @Autowired
    private CallApiJLTZ callApiJLTZ;
    @Override
    public TrackResultModel runStep1(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiJLTZ.runStep1(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel runStep2(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiJLTZ.runStep2(trackModel);
        return trackResultModel;
    }
}
