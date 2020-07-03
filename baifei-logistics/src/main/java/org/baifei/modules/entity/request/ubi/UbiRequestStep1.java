package org.baifei.modules.entity.request.ubi;

import lombok.Data;

import java.util.List;

@Data
public class UbiRequestStep1 {
    private String trackingNo;

    private String consignmentId;

    private String referenceNo;

    private String addressLine1;

    private String addressLine2;

    private String addressLine3;

    private String city;

    private String country;

    private String description;

    private String nativeDescription;

    private String email;

    private String facility;

    private String instruction;

    private String invoiceCurrency;

    private double invoiceValue;

    private String phone;

    private String platform;

    private String postcode;

    private String recipientCompany;

    private String recipientName;

    private String serviceCode;

    private String serviceOption;

    private String sku;

    private String state;

    private String weightUnit;

    private double weight;

    private String dimensionUnit;

//    private int length;
//
//    private int width;
//
//    private int height;
//
//    private int volume;

    private String shipperName;

    private String shipperAddressLine1;

    private String shipperAddressLine2;

    private String shipperAddressLine3;

    private String shipperCity;

    private String shipperState;

    private String shipperPostcode;

    private String shipperCountry;

    private String shipperPhone;

    private String returnName;

    private String returnAddressLine1;

    private String returnAddressLine2;

    private String returnAddressLine3;

    private String returnCity;

    private String returnState;

    private String returnPostcode;

    private String returnCountry;

    private String returnOption;

    private String recipientTaxId;

    private String authorityToLeave;

    private String incoterm;

    private String lockerService;

    private List<UbiOrderItems> orderItems ;
}
