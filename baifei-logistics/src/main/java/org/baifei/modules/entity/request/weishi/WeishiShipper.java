package org.baifei.modules.entity.request.weishi;

import lombok.Data;

@Data
public class WeishiShipper {
    private String shipper_countrycode;
    private String shipper_province;
    private String shipper_city;
    private String shipper_street;
    private String shipper_postcode;
    private String shipper_name;
    private String shipper_telephone;
    private String shipper_mobile;
}
