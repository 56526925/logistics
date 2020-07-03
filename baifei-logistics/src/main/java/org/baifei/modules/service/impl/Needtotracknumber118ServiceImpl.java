package org.baifei.modules.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.baifei.modules.api.CallApiFPX;
import org.baifei.modules.api.CallApiYKD;
import org.baifei.modules.entity.Needtotracknumber118;
import org.baifei.modules.mapper.Needtotracknumber118Mapper;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber118Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 物流交运
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
@Service
public class Needtotracknumber118ServiceImpl extends ServiceImpl<Needtotracknumber118Mapper, Needtotracknumber118> implements INeedtotracknumber118Service {

    @Autowired
    private CallApiYKD callApiYKD;
    @Override
    public TrackResultModel runStep1(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiYKD.runStep1(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel runStep2(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiYKD.runStep2(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel getWarehouse(String appkey ,String appSecret) {
        TrackResultModel trackResultModel=callApiYKD.getWarehouse(appkey,appSecret);
        return trackResultModel;
    }

    @Override
    public TrackResultModel getChannels(String appkey, String appSecret, String wareHouseCode) {
        TrackResultModel trackResultModel=callApiYKD.getChannels(appkey,appSecret,wareHouseCode);
        return trackResultModel;
    }

}
