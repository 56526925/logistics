package org.baifei.modules.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.baifei.modules.api.CallApiHaihehui;
import org.baifei.modules.api.CallApiUBI;
import org.baifei.modules.entity.Needtotracknumber123;
import org.baifei.modules.mapper.Needtotracknumber123Mapper;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber123Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 物流交运
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
@Service
public class Needtotracknumber123ServiceImpl extends ServiceImpl<Needtotracknumber123Mapper, Needtotracknumber123> implements INeedtotracknumber123Service {

    @Autowired
    private CallApiUBI callApiUBI;
    @Override
    public TrackResultModel runStep1(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiUBI.runStep1(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel runStep2(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiUBI.runStep2(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel getChannels(String key,String token) {
        TrackResultModel trackResultModel=callApiUBI.getChannels(key,token);
        return trackResultModel;
    }
}
