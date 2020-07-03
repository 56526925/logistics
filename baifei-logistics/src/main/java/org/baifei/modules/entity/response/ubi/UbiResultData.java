package org.baifei.modules.entity.response.ubi;

import lombok.Data;

@Data
public class UbiResultData {
    private String status;
    private String errors;
    private String orderId;
    private String referenceNo;
    private String trackingNo;
}
