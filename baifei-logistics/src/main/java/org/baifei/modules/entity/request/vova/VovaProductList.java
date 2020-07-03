package org.baifei.modules.entity.request.vova;

import lombok.Data;

@Data
public class VovaProductList {
    private String order_goods_sn;
    private String goods_sku;
    private String description_en;
    private String description_local;
    private String category_en;
    private String category_local;
    private String country_of_origin;
    private String country_of_origin_code;
    private double declare_value;
    private String price_unit;
    private String price_currency;
    private double weight;
    private String weight_unit;
    private int quantity;
    private boolean has_battery;
    private boolean has_metal;
    private boolean is_flammable;
    private boolean is_powder;
    private boolean is_liquid;
    private String hs_code;
    private int length;
    private int width;
    private int height;
    private String dimension_unit;
    private String image_url;
    private String product_url;
}
