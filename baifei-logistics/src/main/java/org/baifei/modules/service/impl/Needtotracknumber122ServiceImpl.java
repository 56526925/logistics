package org.baifei.modules.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.baifei.modules.api.CallApiYKD;
import org.baifei.modules.api.CallApiZYHW;
import org.baifei.modules.entity.Needtotracknumber122;
import org.baifei.modules.mapper.Needtotracknumber122Mapper;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber122Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 物流交运
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
@Service
public class Needtotracknumber122ServiceImpl extends ServiceImpl<Needtotracknumber122Mapper, Needtotracknumber122> implements INeedtotracknumber122Service {

    @Autowired
    private CallApiZYHW callApiZYHW;
    @Override
    public TrackResultModel runStep1(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiZYHW.runStep1(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel runStep2(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiZYHW.runStep2(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel getWarehouse(String appkey ,String appSecret) {
        TrackResultModel trackResultModel=callApiZYHW.getWarehouse(appkey,appSecret);
        return trackResultModel;
    }

    @Override
    public TrackResultModel getChannels(String appkey, String appSecret, String wareHouseCode) {
        TrackResultModel trackResultModel=callApiZYHW.getChannels(appkey,appSecret,wareHouseCode);
        return trackResultModel;
    }

}
