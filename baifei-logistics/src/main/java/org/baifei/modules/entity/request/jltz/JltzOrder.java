package org.baifei.modules.entity.request.jltz;

import lombok.Data;

import java.util.List;

@Data
public class JltzOrder {
    private String ECommerceNo;

    private String WarehouseCode;

    private String ExpressCode;

    private String Length;

    private String Width;

    private String Height;

    private double Weight;

    private String Remark;

    private String Paid;

    private JltzSender Sender;

    private JltzReceiver Receiver;

    private List<JltzItems> Items;
}
