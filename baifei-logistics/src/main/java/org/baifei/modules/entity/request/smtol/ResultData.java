/**
  * Copyright 2018 bejson.com 
  */
package org.baifei.modules.entity.request.smtol;

/**
 * Auto-generated: 2018-10-25 15:31:29
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class ResultData {

    private int code;

    private String expressid;//物流单号
    private String out_order_code;
    private String logistics_order_id;
    private String channel_code;
    private String warehouseOrderId;//物流单号
    private String orderid;//代理单号
    private String message;//描述

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getExpressid() {
        return expressid;
    }

    public void setExpressid(String expressid) {
        this.expressid = expressid;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getWarehouseOrderId() {
        return warehouseOrderId;
    }

    public void setWarehouseOrderId(String warehouseOrderId) {
        this.warehouseOrderId = warehouseOrderId;
    }

    public String getOut_order_code() {
        return out_order_code;
    }

    public void setOut_order_code(String out_order_code) {
        this.out_order_code = out_order_code;
    }

    public String getLogistics_order_id() {
        return logistics_order_id;
    }

    public void setLogistics_order_id(String logistics_order_id) {
        this.logistics_order_id = logistics_order_id;
    }

    public String getChannel_code() {
        return channel_code;
    }

    public void setChannel_code(String channel_code) {
        this.channel_code = channel_code;
    }
}