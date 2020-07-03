package org.baifei.modules.entity.request.jltz;

import lombok.Data;

import java.util.List;

@Data
public class JltzReceiver {
    private String Name;

    private String Tel;

    private String Mobile;

    private String Email;

    private String Country;

    private String Province;

    private String City;

    private List<String> District;

    private String Address1;

    private List<String> Address2;

    private String ZipCode;
}
