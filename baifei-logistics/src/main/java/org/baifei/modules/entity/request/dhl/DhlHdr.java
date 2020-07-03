package org.baifei.modules.entity.request.dhl;

import lombok.Data;

@Data
public class DhlHdr {
    private String messageType;

    private String messageDateTime;

    private String accessToken;

    private String messageVersion;

    private String messageLanguage;
}
