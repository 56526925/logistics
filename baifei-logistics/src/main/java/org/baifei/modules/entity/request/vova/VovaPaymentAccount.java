package org.baifei.modules.entity.request.vova;

import lombok.Data;

@Data
public class VovaPaymentAccount {
    private String payer_id;
    private String payer_name;
    private String payer_email;
    private String payer_phone;
    private String payer_mobile;
    private String payer_contact_name;
}
