package org.baifei.modules.entity.request.vova;

import lombok.Data;

import java.util.List;

@Data
public class VovaParcel {
    private int length;
    private int width;
    private int height;
    private double parcel_weight;
    private String dimension_unit;
    private List<VovaProductList> product_list;
}
