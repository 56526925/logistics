package org.baifei.modules.entity.request.ft;

import lombok.Data;

import java.util.List;

@Data
public class FtOrderList {
    private String ReceiverName;
    private String Address1;
    private String County;
    private String City;
    private String CiId;
    private String Zip;
    private String PtId;
    private int PackType;
    private String Phone;
    private String Email;
    private String ApiOrderId;
    private String BuyerId;
    private String OnlineShopName;
    private int SalesPlatformFlag;
    private String SyncPlatformFlag;
    private String Remark;
    private int MultiPackageQuantity;
    private String ExtendData1;
    private List<FtOrderDetailList> OrderDetailList;
    private List<FtHaikwanDetialList> HaikwanDetialList;
    private List<FtOrderVolumeWeights> OrderVolumeWeights;
}
