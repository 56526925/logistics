package org.baifei.modules.entity.request.smtol;

public class ChannelData {
    private String delivery_address;
    private String logistics_service_id;
    private String logistics_service_name;
    private String logistics_timeliness;
    private String trial_result;
    private String warehouse_name;


    public String getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    public String getLogistics_service_id() {
        return logistics_service_id;
    }

    public void setLogistics_service_id(String logistics_service_id) {
        this.logistics_service_id = logistics_service_id;
    }

    public String getLogistics_service_name() {
        return logistics_service_name;
    }

    public void setLogistics_service_name(String logistics_service_name) {
        this.logistics_service_name = logistics_service_name;
    }

    public String getLogistics_timeliness() {
        return logistics_timeliness;
    }

    public void setLogistics_timeliness(String logistics_timeliness) {
        this.logistics_timeliness = logistics_timeliness;
    }

    public String getTrial_result() {
        return trial_result;
    }

    public void setTrial_result(String trial_result) {
        this.trial_result = trial_result;
    }

    public String getWarehouse_name() {
        return warehouse_name;
    }

    public void setWarehouse_name(String warehouse_name) {
        this.warehouse_name = warehouse_name;
    }
}
