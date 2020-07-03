/**
 * Copyright 2020 bejson.com
 */
package org.baifei.modules.entity.request.vova;
import lombok.Data;

import java.util.Date;

/**
 * Auto-generated: 2020-06-22 17:20:39
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class VovaRequestStep1 {

    private String access_token;
    private String vova_platform_id;
    private String wh_token;
    private String timestamp;
    private String pickup_type;
    private int chinapost_return_type;
    private String chinapost_mail_kind;
    private String chinapost_channel;
    private String chinapost_printcode;
    private String chinapost_whcode;
    private String incoterm;
    private String logistics_product_id;
    private boolean paid_with_vova;
    private VovaPaymentAccount payment_account;
    private VovaSender sender;
    private VovaChinapostPickup chinapost_pickup;
    private VovaReceiver receiver;
    private VovaParcel parcel;


}