package org.baifei.modules.entity.response.dhl;

import lombok.Data;

@Data
public class DhlLabels {
    private String shipmentID;

    private String deliveryConfirmationNo;

    private String labelURL;

    private String content;

    private DhlResponseStatus responseStatus;
}
