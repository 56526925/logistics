/**
 * Copyright 2020 bejson.com
 */
package org.baifei.modules.entity.request.vova;

import lombok.Data;

/**
 * Auto-generated: 2020-06-22 17:20:39
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class VovaSender {

    private String company;
    private String phone;
    private String email;
    private String country_code;
    private String chinapost_province_code;
    private String chinapost_city_code;
    private String chinapost_county_code;
    private String zipcode;
    private VovaAddressLocal address_local;
    private VovaAddressEn address_en;

}