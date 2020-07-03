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
public class ResultBean {

    private int code;

    private String expressid;//物流单号
    private String out_order_id;//外补单号
    private String lp_number;//lp号
    private String online_logistics_id;
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

    public String getLp_number() {
        return lp_number;
    }

    public void setLp_number(String lp_number) {
        this.lp_number = lp_number;
    }

    public String getOnline_logistics_id() {
        return online_logistics_id;
    }

    public void setOnline_logistics_id(String online_logistics_id) {
        this.online_logistics_id = online_logistics_id;
    }

    public String getChannel_code() {
        return channel_code;
    }

    public void setChannel_code(String channel_code) {
        this.channel_code = channel_code;
    }

    public String getOut_order_id() {
        return out_order_id;
    }

    public void setOut_order_id(String out_order_id) {
        this.out_order_id = out_order_id;
    }
}