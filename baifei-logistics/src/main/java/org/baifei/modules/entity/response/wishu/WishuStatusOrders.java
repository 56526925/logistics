/**
 * Copyright 2020 bejson.com
 */
package org.baifei.modules.entity.response.wishu;
import lombok.Data;

import java.util.List;

/**
 * Auto-generated: 2020-06-12 15:8:43
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class WishuStatusOrders {

    private String wish_standard_tracking_id;
    private String logistics_order_code;
    private List<String> wish_transaction_ids;
    private String state_name;
    private int state_code;
    private String cancel_reason_code;
    private String cancel_fail_reason;
    private String message;
    private String reference_qrcode;
    private String PDF_10_LCL_URL;
    private String PDF_10_EN_URL;
    private int dest_warehouse_code;
    private String dest_warehouse_name;
    private String hub_warehouse_code;

}