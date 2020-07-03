package org.baifei.modules.entity.request.weishi;

import com.itextpdf.text.pdf.PRAcroForm;
import lombok.Data;

@Data
public class WeishiConsignee {
    private String consignee_province;
    private String consignee_city;
    private String consignee_street;
    private String consignee_postcode;
    private String consignee_name;
    private String consignee_mobile;
    private String consignee_telephone;
    private String consignee_email;

}
