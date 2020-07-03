package org.baifei.modules.entity.response.sls;

import lombok.Data;

@Data
public class SlsResponseStep1 {
    private int code;
    private String message;
    private String tracking_number;//转单号
    private String request_id;
}
