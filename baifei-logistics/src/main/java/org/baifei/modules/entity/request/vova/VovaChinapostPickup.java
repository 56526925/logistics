package org.baifei.modules.entity.request.vova;

import lombok.Data;

@Data
public class VovaChinapostPickup {
    private String company;
    private String phone;
    private String email;
    private String country_code;
    private String province_code;
    private String city_code;
    private String county_code;
    private String zipcode;
    private VovaAddress address;
}
