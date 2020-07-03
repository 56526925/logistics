package org.baifei.modules.entity.response.ubi;

import java.util.List;

public class UbiResponseStep1 {
    private String status;
    private String errors;
    private List<UbiResultData> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public List<UbiResultData> getData() {
        return data;
    }

    public void setData(List<UbiResultData> data) {
        this.data = data;
    }
}
