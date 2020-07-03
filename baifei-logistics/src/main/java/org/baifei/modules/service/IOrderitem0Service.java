package org.baifei.modules.service;

import org.baifei.modules.entity.Orderitem0;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 订单详情
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
public interface IOrderitem0Service extends IService<Orderitem0> {
    public List<Orderitem0> getItemsProduct(Integer orderId);
}
