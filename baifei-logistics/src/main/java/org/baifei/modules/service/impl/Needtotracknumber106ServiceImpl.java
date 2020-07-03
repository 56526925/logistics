package org.baifei.modules.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.baifei.modules.api.CallApiPOST;
import org.baifei.modules.api.CallApiSF;
import org.baifei.modules.entity.Needtotracknumber106;
import org.baifei.modules.mapper.Needtotracknumber106Mapper;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber106Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 物流交运
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
@Service
public class Needtotracknumber106ServiceImpl extends ServiceImpl<Needtotracknumber106Mapper, Needtotracknumber106> implements INeedtotracknumber106Service {

    @Autowired
    private CallApiSF callApiSF;
    @Override
    public TrackResultModel runStep1(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiSF.runStep1(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel runStep2(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiSF.runStep2(trackModel);
        return trackResultModel;
    }
}
