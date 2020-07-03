package org.baifei.modules.entity.request.dhl;

import lombok.Data;

import java.util.List;

@Data
public class DhlBd {
    private String inlineLabelReturn;

    private String customerAccountId;

    private String pickupAccountId;

    private String soldToAccountId;

    private String handoverMethod;

    private String pickupDateTime;

    private DhlPickupAddress pickupAddress;

    private DhlShipperAddress shipperAddress;

    private List<DhlShipmentItems> shipmentItems;

    private DhlLabel label;

}
