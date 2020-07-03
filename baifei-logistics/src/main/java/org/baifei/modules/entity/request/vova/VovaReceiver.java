package org.baifei.modules.entity.request.vova;

import lombok.Data;

@Data
public class VovaReceiver {
    private String company;
    private String phone;
    private String email;
    private String country_code;
    private String zipcode;
    private String tax_number;
    private VovaAddress address;
}
