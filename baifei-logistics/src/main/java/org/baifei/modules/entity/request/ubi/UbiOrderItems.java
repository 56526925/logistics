package org.baifei.modules.entity.request.ubi;

import lombok.Data;

@Data
public class UbiOrderItems {
    private String itemNo;

    private String sku;

    private String description;

    private String nativeDescription;

    private String hsCode;

    private String originCountry;

    private double unitValue;

    private int itemCount;

    private double weight;

    private String productURL;
}
