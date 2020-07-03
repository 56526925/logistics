/**
 * Copyright 2020 bejson.com
 */
package org.baifei.modules.entity.request.wishu;
import lombok.Data;

import java.util.Date;

/**
 * Auto-generated: 2020-06-11 14:10:9
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class WishuOrder {

    private String guid;
    private String otype;
    private String from;
    private String sender_addres;
    private String sender_province;
    private String sender_city;
    private String sender_district;
    private String sender_phone;
    private String sender_zipcode;
    private String to;
    private String recipient_addres;
    private String recipient_country;
    private String recipient_country_short;
    private String recipient_province;
    private String recipient_city;
    private String recipient_district;
    private String recipient_postcode;
    private String recipient_phone;
    private String to_local;
    private String recipient_country_local;
    private String recipient_province_local;
    private String recipient_city_local;
    private String recipient_district_local;
    private String recipient_addres_local;
    private String type_no;
    private String unit_measurement;
    private String unit_measurement_chinese;
    private String user_desc;
    private double trade_amount;
    private String receive_from;
    private String receive_province;
    private String receive_city;
    private String receive_district;
    private String receive_addres;
    private String receive_phone;
    private String doorpickup;
    private String pickup_service;
    private String pickup_from_local;
    private String pickup_phone;
    private String pickup_zip_code;
    private String pickup_addres_local;
    private String pickup_province_local;
    private String pickup_city_local;
    private String pickup_district_local;
    private String pickup_from;
    private String pickup_addres;
    private String pickup_province;
    private String pickup_city;
    private String pickup_district;
    private String pickup_country_local;
    private String pickup_country;
    private String pickup_country_code;
    private String warehouse_code;
    private WishuProductItems product_items;


}