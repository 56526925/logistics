package org.baifei.modules.entity.request.ykd;

import lombok.Data;

import java.util.List;

@Data
public class YkdRequestStep1 {
    private String reference_no;
    private String shipping_method;
    private String warehouse_code;
    private String country_code;
    private String province;
    private String city;
    private String address1;
    private String zipcode;
    private String name;
    private String phone;
    private List<YkdItem> items;

}
