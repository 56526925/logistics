package org.baifei.modules.entity.request.ykd;

import lombok.Data;

@Data
public class YkdItem {
    private String product_sku;
    private int quantity;
    private String fba_product_code;
}
