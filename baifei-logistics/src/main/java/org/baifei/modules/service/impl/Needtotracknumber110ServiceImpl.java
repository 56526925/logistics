package org.baifei.modules.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.baifei.modules.api.CallApiWeishi;
import org.baifei.modules.api.CallApiYanWen;
import org.baifei.modules.entity.Needtotracknumber110;
import org.baifei.modules.mapper.Needtotracknumber110Mapper;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber110Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 物流交运
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
@Service
public class Needtotracknumber110ServiceImpl extends ServiceImpl<Needtotracknumber110Mapper, Needtotracknumber110> implements INeedtotracknumber110Service {

    @Autowired
    private CallApiWeishi callApiWeishi;
    @Override
    public TrackResultModel runStep1(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiWeishi.runStep1(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel runStep2(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiWeishi.runStep2(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel getChannels() {
        TrackResultModel trackResultModel=callApiWeishi.getChannels();
        return trackResultModel;
    }
}
