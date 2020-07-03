package org.baifei.modules.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.baifei.modules.api.CallApiFPX;
import org.baifei.modules.api.CallApiWISHU;
import org.baifei.modules.entity.Needtotracknumber105;
import org.baifei.modules.mapper.Needtotracknumber105Mapper;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber105Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 物流交运
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
@Service
public class Needtotracknumber105ServiceImpl extends ServiceImpl<Needtotracknumber105Mapper, Needtotracknumber105> implements INeedtotracknumber105Service {

    @Autowired
    private CallApiWISHU callApiWISHU;
    @Override
    public TrackResultModel runStep1(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiWISHU.runStep1(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel runStep2(TrackModel trackModel,String tracknumber1) {
        TrackResultModel trackResultModel=callApiWISHU.runStep2(trackModel,tracknumber1);
        return trackResultModel;
    }
    @Override
    public TrackResultModel runStep3(TrackModel trackModel,String tracknumber) {
        TrackResultModel trackResultModel=callApiWISHU.runStep3(trackModel,tracknumber);
        return trackResultModel;
    }

    @Override
    public TrackResultModel getChannels(String token) {
        TrackResultModel trackResultModel=callApiWISHU.getChannels(token);
        return trackResultModel;
    }

}
