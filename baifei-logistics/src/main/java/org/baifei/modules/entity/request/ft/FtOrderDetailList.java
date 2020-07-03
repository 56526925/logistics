package org.baifei.modules.entity.request.ft;

import lombok.Data;

@Data
public class FtOrderDetailList {
    private double Price;
    private String ItemName;
    private String ItemTransactionId;
    private int Quantities;
    private String Sku;
    private String ItemId;
    private String OriginalPlatformOrderId;
    private String Remark;
}
