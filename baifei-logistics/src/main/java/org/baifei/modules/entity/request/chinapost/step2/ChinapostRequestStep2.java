/**
 * Copyright 2020 bejson.com
 */
package org.baifei.modules.entity.request.chinapost.step2;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Auto-generated: 2020-06-05 14:49:54
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class ChinapostRequestStep2 {

    private String created_time;
    private String sender_no;
    private String wh_code;
    private String mailType;
    private String logistics_order_no;
    private String batch_no;
    private String waybill_no;
    private String biz_product_no;
    private BigDecimal weight;
    private String volume;
    private String length;
    private String width;
    private String height;
    private String postage_total;
    private String postage_currency;
    private double contents_total_weight;
    private double contents_total_value;
    private String transfer_type;
    private String battery_flag;
    private String pickup_notes;
    private String insurance_flag;
    private String insurance_amount;
    private String undelivery_option;
    private String valuable_flag;
    private String declare_source;
    private String declare_type;
    private String declare_curr_code;
    private String printcode;
    private String barcode;
    private String forecastshut;
    private String mail_sign;
    private Date tax_id;
    private Sender sender;
    private Receiver receiver;
    private List<Items> items;

}