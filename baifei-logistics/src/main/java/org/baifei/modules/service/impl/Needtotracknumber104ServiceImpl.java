package org.baifei.modules.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.baifei.modules.api.CallApiFPX;
import org.baifei.modules.entity.Needtotracknumber104;
import org.baifei.modules.mapper.Needtotracknumber104Mapper;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber104Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 物流交运
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
@Service
public class Needtotracknumber104ServiceImpl extends ServiceImpl<Needtotracknumber104Mapper, Needtotracknumber104> implements INeedtotracknumber104Service {

    @Autowired
    private CallApiFPX callApiFPX;
    @Override
    public TrackResultModel runStep1(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiFPX.runStep1(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel getWarehouse(String appkey ,String appSecret) {
        TrackResultModel trackResultModel=callApiFPX.getWarehouse(appkey,appSecret);
        return trackResultModel;
    }

    @Override
    public TrackResultModel getChannels(String appkey, String appSecret, String wareHouseCode) {
        TrackResultModel trackResultModel=callApiFPX.getChannels(appkey,appSecret,wareHouseCode);
        return trackResultModel;
    }

}
