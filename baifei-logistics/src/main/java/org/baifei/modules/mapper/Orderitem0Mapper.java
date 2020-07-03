package org.baifei.modules.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.baifei.modules.entity.Orderitem0;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 订单详情
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
public interface Orderitem0Mapper extends BaseMapper<Orderitem0> {
   public List<Orderitem0> getItemsProduct(@Param("orderId") Integer orderId);
}
