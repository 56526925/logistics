/**
 * Copyright 2020 bejson.com
 */
package org.baifei.modules.entity.response.chinapost.step1;
import java.util.List;

/**
 * Auto-generated: 2020-06-05 14:14:49
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class ChinapostResponseStep1 {

    private String return_success;
    private String return_reason;
    private String return_msg;
    private List<BarCodeList> barCodeList;

    public void setReturn_success(String return_success) {
        this.return_success = return_success;
    }
    public String getReturn_success() {
        return return_success;
    }

    public void setBarCodeList(List<BarCodeList> barCodeList) {
        this.barCodeList = barCodeList;
    }
    public List<BarCodeList> getBarCodeList() {
        return barCodeList;
    }

    public String getReturn_reason() {
        return return_reason;
    }

    public void setReturn_reason(String return_reason) {
        this.return_reason = return_reason;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }
}