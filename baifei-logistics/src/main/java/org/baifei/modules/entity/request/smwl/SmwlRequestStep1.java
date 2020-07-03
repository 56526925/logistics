package org.baifei.modules.entity.request.smwl;

import lombok.Data;

import java.util.List;

@Data
public class SmwlRequestStep1 {
    private String ref_no;

    private String channel_code;

    private int type;

    private String receiver;

    private String country;

    private String province;

    private String city;

    private String address;

    private String postcode;

    private String phone;

    private double weight;

    private int source;

    private int counts;

    private double support_value;

    private String barcode;

    private List<SmwlInvoices> invoices;
}
