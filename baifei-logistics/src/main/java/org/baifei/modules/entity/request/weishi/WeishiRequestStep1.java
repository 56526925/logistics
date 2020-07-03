package org.baifei.modules.entity.request.weishi;

import lombok.Data;

import java.util.List;

@Data
public class WeishiRequestStep1 {
    private String reference_no;
    private String shipper_hawbcode;
    private String shipping_method;
    private double order_weight;
    private String country_code;
    private WeishiConsignee consignee;
    private WeishiShipper shipper;
    private List<WeishiItemArr> itemArr;
}
