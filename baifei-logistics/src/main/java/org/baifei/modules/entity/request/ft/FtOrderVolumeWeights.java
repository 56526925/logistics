package org.baifei.modules.entity.request.ft;

import lombok.Data;

@Data
public class FtOrderVolumeWeights {
    private String OrderId;
    private String PackageCode;
    private double Length;
    private double Width;
    private double Height;
    private double WeighingWeight;
    private String FbaShipmentId;
    private String TraceId;
    private int OrderType;
}
