package org.baifei.modules.entity.request.lwe;

import lombok.Data;

import java.util.List;

@Data
public class LweShipments {
    private LweShipper Shipper;

    private LweConsignee Consignee;

    private List<LweItems> Items;

    private List<LwePackages> Packages;

    private String PackageType;

    private String WeightType;

    private String ShipmentDate;

    private String TOSMode;

    private String CurrencyCode;

    private int CODValue;

    private String CODCurrency;

    private int InsuranceValue;

    private String InsuranceCurrency;
}
