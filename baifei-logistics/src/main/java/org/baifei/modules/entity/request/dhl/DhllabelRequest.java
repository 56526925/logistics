package org.baifei.modules.entity.request.dhl;

import lombok.Data;

@Data
public class DhllabelRequest {
    private DhlHdr hdr;

    private DhlBd bd;
}
