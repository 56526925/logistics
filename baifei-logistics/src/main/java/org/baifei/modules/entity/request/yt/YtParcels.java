package org.baifei.modules.entity.request.yt;

import lombok.Data;

@Data
public class YtParcels {
    private String EName;
    private String CName;
    private String HSCode;
    private int Quantity;
    private String SKU;
    private double UnitPrice;
    private double UnitWeight;
    private String Remark;
    private String InvoiceRemark;
    private String CurrencyCode;
}
