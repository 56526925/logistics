package org.baifei.modules.entity.request.zyhw;

import lombok.Data;

import java.util.List;

@Data
public class ZyhwRequestStep1 {
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
    private List<ZyhwItem> items;

}
