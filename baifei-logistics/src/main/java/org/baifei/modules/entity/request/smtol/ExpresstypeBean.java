package org.baifei.modules.entity.request.smtol;

import java.util.List;

public class ExpresstypeBean {
    private String error_code;
    private String error_desc;
    private String result_success;
    private List<ExpresstypeData> result_list;


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

    public String getResult_success() {
        return result_success;
    }

    public void setResult_success(String result_success) {
        this.result_success = result_success;
    }

    public List<ExpresstypeData> getResult_list() {
        return result_list;
    }

    public void setResult_list(List<ExpresstypeData> result_list) {
        this.result_list = result_list;
    }
}
