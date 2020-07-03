package org.baifei.modules.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.baifei.modules.api.CallApiSunYou;
import org.baifei.modules.api.CallApiYanWen;
import org.baifei.modules.entity.Needtotracknumber109;
import org.baifei.modules.mapper.Needtotracknumber109Mapper;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber109Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 物流交运
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
@Service
public class Needtotracknumber109ServiceImpl extends ServiceImpl<Needtotracknumber109Mapper, Needtotracknumber109> implements INeedtotracknumber109Service {

    @Autowired
    private CallApiYanWen callApiYanWen;
    @Override
    public TrackResultModel runStep1(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiYanWen.runStep1(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel runStep2(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiYanWen.runStep2(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel getChannels(String userId,String apiToken) {
        TrackResultModel trackResultModel=callApiYanWen.getChannels(userId,apiToken);
        return trackResultModel;
    }
}
