package org.baifei.modules.entity.request.dhl;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class DhlShipmentItems {
    private DhlConsigneeAddress consigneeAddress;

    private DhlReturnAddress returnAddress;

    private String shipmentID;

    private String deliveryConfirmationNo;

    private String packageDesc;

    private int totalWeight;

    private String totalWeightUOM;

    private String dimensionUOM;

    private int height;

    private int length;

    private int width;

    private String customerReference1;

    private String customerReference2;

    private String productCode;

    private String incoterm;

    private String contentIndicator;

    private String codValue;

    private BigDecimal insuranceValue;

    private String freightCharge;

    private double totalValue;

    private String currency;

    private String remarks;

    private DhlValueAddedServices valueAddedServices;

    private String isMult;

    private String deliveryOption;

    private List<DhlshipmentContents> shipmentContents;

}
