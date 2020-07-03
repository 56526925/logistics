package org.baifei.modules.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.baifei.modules.api.CallApiDHL;
import org.baifei.modules.api.CallApiHaihehui;
import org.baifei.modules.entity.Needtotracknumber112;
import org.baifei.modules.mapper.Needtotracknumber112Mapper;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber112Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 物流交运
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
@Service
public class Needtotracknumber112ServiceImpl extends ServiceImpl<Needtotracknumber112Mapper, Needtotracknumber112> implements INeedtotracknumber112Service {

    @Autowired
    private CallApiDHL callApiDHL;
    @Override
    public TrackResultModel runStep1(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiDHL.runStep1(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel runStep2(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiDHL.runStep2(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel flushToken(Integer accountId) {
        TrackResultModel trackResultModel=callApiDHL.flushToken(accountId);
        return trackResultModel;
    }

}
