package org.baifei.modules.entity.response.dhl;


import lombok.Data;

@Data
public class DhlLabelResponse {
    private DhlBdresp bd;
    private DhlHdrresp hdr;
}
