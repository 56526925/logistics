package org.baifei.modules.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.baifei.modules.api.CallApiMyMall;
import org.baifei.modules.entity.Needtotracknumber126;
import org.baifei.modules.mapper.Needtotracknumber126Mapper;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber126Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 物流交运
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
@Service
public class Needtotracknumber126ServiceImpl extends ServiceImpl<Needtotracknumber126Mapper, Needtotracknumber126> implements INeedtotracknumber126Service {

    @Autowired
    private CallApiMyMall callApiMyMall;
    @Override
    public TrackResultModel runStep1(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiMyMall.runStep1(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel runStep2(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiMyMall.runStep2(trackModel);
        return trackResultModel;
    }
}
