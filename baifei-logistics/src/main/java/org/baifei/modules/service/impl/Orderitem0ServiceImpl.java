package org.baifei.modules.service.impl;

import org.apache.ibatis.annotations.Mapper;
import org.baifei.modules.entity.Orderitem0;
import org.baifei.modules.mapper.Orderitem0Mapper;
import org.baifei.modules.service.IOrderitem0Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 订单详情
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
@Service
public class Orderitem0ServiceImpl extends ServiceImpl<Orderitem0Mapper, Orderitem0> implements IOrderitem0Service {

    @Autowired
    private Orderitem0Mapper orderitem0Mapper;
    @Override
    public List<Orderitem0> getItemsProduct(Integer orderId) {
        return orderitem0Mapper.getItemsProduct(orderId);
    }
}
