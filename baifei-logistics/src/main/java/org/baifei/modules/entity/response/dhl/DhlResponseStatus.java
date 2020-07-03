package org.baifei.modules.entity.response.dhl;

import lombok.Data;

import java.util.List;

@Data
public class DhlResponseStatus {
    private String code;

    private String message;

    private List<DhlMessageDetails> messageDetails;
}
