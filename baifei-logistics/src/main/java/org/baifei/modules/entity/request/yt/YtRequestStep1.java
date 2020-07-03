package org.baifei.modules.entity.request.yt;

import lombok.Data;

import java.util.List;

@Data
public class YtRequestStep1 {
    private String CustomerOrderNumber;
    private String ShippingMethodCode;
    private String TrackingNumber;
    private String TransactionNumber;
    private int Height;
    private int Length;
    private int Width;
    private int PackageCount;
    private double Weight;
    private int ApplicationType;
    private int ReturnOption;
    private int TariffPrepay;
    private int InsuranceOption;
    private String SourceCode;
    private YtReceiver Receiver;
    private YtSender Sender;
    private List<YtParcels> Parcels;
    private List<YtChildOrders> ChildOrders;
}
