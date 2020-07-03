package org.baifei.modules.entity.request.jltz;

import lombok.Data;

@Data
public class JltzItems {
    private String No;

    private String NameCN;

    private String NameEN;

    private String CategoryNameCN;

    private String CategoryNameEN;

    private String Length;

    private String Width;

    private String Height;

    private double Weight;

    private int Quantity;

    private double UnitPrice;

    private String HasBattery;

    private String HsCode;
}
