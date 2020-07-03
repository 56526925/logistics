package org.baifei.modules.entity.request.ft;

import lombok.Data;

@Data
public class FtHaikwanDetialList {
    private String ItemEnName;
    private String ItemCnName;
    private int Quantities;
    private double UnitPrice;
    private double Weight;
    private String Model;
    private String Brand;
    private String Purpose;
    private String Material;
    private String ProducingArea;
    private String CCode;
    private String FbaNumber;
}
