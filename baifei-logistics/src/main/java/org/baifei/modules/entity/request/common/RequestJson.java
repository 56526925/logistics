package org.baifei.modules.entity.request.common;

import lombok.Data;

@Data
public class RequestJson {
    private Integer accountId;
    private String accountName;
    private String channelCode;
    private String warehouseCode;
}
