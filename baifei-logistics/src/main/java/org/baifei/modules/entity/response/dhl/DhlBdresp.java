package org.baifei.modules.entity.response.dhl;

import lombok.Data;
import org.baifei.modules.entity.request.dhl.DhlShipmentItems;
import org.baifei.modules.entity.request.dhl.DhlshipmentContents;

import java.util.List;

@Data
public class DhlBdresp {
    private List<DhlLabels> labels;

    private List<DhlShipmentItemsresp> shipmentItems;

    private DhlResponseStatus responseStatus;

}
