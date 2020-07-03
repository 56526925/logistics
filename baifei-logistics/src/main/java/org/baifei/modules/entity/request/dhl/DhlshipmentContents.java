package org.baifei.modules.entity.request.dhl;

import lombok.Data;

@Data
public class DhlshipmentContents {
    private String contentIndicator;

    private String countryOfOrigin;

    private String description;

    private String descriptionExport;

    private String descriptionImport;

    private int grossWeight;

    private String weightUOM;

    private String hsCode;

    private int itemQuantity;

    private double itemValue;

    private String skuNumber;
}
