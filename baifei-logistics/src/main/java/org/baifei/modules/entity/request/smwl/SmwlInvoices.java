package org.baifei.modules.entity.request.smwl;

import lombok.Data;

@Data
public class SmwlInvoices {
    private String name_cn;

    private String name_en;

    private double weight;

    private double price;

    private String currency;

    private int quantity;

    private String unit;

    private String origin;

    private String tax;

}
