package org.baifei.modules.entity.request.vova;

import lombok.Data;

@Data
public class VovaAddressLocal {
    private String name;
    private String country;
    private String province;
    private String city;
    private String district;
    private String street;
    private String street_address1;
    private String street_address2;
}
