package org.baifei.modules.entity.response.dhl;

import lombok.Data;

@Data
public class DhlHdrresp {
    private String messageType;

    private String messageDateTime;

    private String messageVersion;

    private String messageLanguage;
}
