package org.baifei.modules.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.baifei.modules.api.CallApiPOST;
import org.baifei.modules.api.CallApiSMTOL;
import org.baifei.modules.entity.Needtotracknumber103;
import org.baifei.modules.mapper.Needtotracknumber103Mapper;
import org.baifei.modules.model.TrackModel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber103Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 物流交运
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
@Service
public class Needtotracknumber103ServiceImpl extends ServiceImpl<Needtotracknumber103Mapper, Needtotracknumber103> implements INeedtotracknumber103Service {

    @Autowired
    private CallApiSMTOL callApiSMTOL;
    @Override
    public TrackResultModel runStep1(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiSMTOL.runStep1(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel runStep2(TrackModel trackModel) {
        TrackResultModel trackResultModel=callApiSMTOL.runStep2(trackModel);
        return trackResultModel;
    }

    @Override
    public TrackResultModel getChannels(String orderId,String costomId) {
        TrackResultModel trackResultModel=callApiSMTOL.getChannels(orderId,costomId);
        return trackResultModel;
    }
}
