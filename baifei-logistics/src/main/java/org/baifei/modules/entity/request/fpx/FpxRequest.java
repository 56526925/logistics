/**
 * Copyright 2020 bejson.com
 */
package org.baifei.modules.entity.request.fpx;
import lombok.Data;

import java.util.List;

/**
 * Auto-generated: 2020-06-10 10:7:45
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class FpxRequest {

    private String customer_code;
    private String ref_no;
    private String from_warehouse_code;
    private String consignment_type;
    private LogisticsServiceInfo logistics_service_info;
    private String oda;
    private String sales_platform;
    private String seller_id;
    private String sales_no;
    private String insure_services;
    private String currency;
    private int insure_value;
    private String ocustoms_service;
    private String icustoms_service;
    private String remark;
    private OconsignmentDesc oconsignment_desc;
    private IdentityInfo identity_info;
    private List<OconsignmentSku> oconsignment_sku;

}