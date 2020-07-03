package org.baifei.modules.entity.request.smtol;

import java.util.List;

public class ChannelBean {
    private String error_code;
    private String error_desc;
    private String is_success;
    private List<ChannelData> result_list;


    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_desc() {
        return error_desc;
    }

    public void setError_desc(String error_desc) {
        this.error_desc = error_desc;
    }

    public String getIs_success() {
        return is_success;
    }

    public void setIs_success(String is_success) {
        this.is_success = is_success;
    }

    public List<ChannelData> getResult_list() {
        return result_list;
    }

    public void setResult_list(List<ChannelData> result_list) {
        this.result_list = result_list;
    }
}
