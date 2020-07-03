package org.baifei.modules.entity.request.dhl;

import lombok.Data;

@Data
public class DhlConsigneeAddress {
    private String name;

    private String address1;

    private String address2;

    private String city;

    private String state;

    private String district;

    private String country;

    private String postCode;

    private String phone;

    private String email;

    private String idNumber;

    private String idType;
}
