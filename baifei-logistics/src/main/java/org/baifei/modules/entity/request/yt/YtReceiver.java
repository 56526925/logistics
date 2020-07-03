package org.baifei.modules.entity.request.yt;

import lombok.Data;

@Data
public class YtReceiver {
    private String CountryCode;
    private String FirstName;
    private String LastName;
    private String Company;
    private String Street;
    private String City;
    private String State;
    private String Zip;
    private String Phone;
    private String HouseNumber;
}
