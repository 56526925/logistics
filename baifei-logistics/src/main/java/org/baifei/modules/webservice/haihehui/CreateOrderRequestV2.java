/**
 * CreateOrderRequestV2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.baifei.modules.webservice.haihehui;

public class CreateOrderRequestV2  implements java.io.Serializable {
    private String additionalJson;

    private String cargoCode;

    private String consigneeAddress;

    private String consigneeCity;

    private String consigneeCompanyName;

    private String consigneeMobile;

    private String consigneeName;

    private String consigneePostcode;

    private String consigneeProvince;

    private String consigneeStreet;

    private String consigneeStreetNo;

    private String consigneeTelephone;

    private DeclareItem[] declareItems;

    private String destinationCountryCode;

    private String goodsCategory;

    private String goodsDescription;

    private Double height;

    private String insured;

    private Double length;

    private String memo;

    private String orderNo;

    private String originCountryCode;

    private PackageItem[] packageItems;

    private Long pieces;

    private String platformNo;

    private String shipperAddress;

    private String shipperCity;

    private String shipperCompanyName;

    private String shipperMobile;

    private String shipperName;

    private String shipperPostcode;

    private String shipperProvince;

    private String shipperStreet;

    private String shipperStreetNo;

    private String shipperTelephone;

    private String trackingNo;

    private String transportWayCode;

    private Double weight;

    private Double width;

    public CreateOrderRequestV2() {
    }

    public CreateOrderRequestV2(
           String additionalJson,
           String cargoCode,
           String consigneeAddress,
           String consigneeCity,
           String consigneeCompanyName,
           String consigneeMobile,
           String consigneeName,
           String consigneePostcode,
           String consigneeProvince,
           String consigneeStreet,
           String consigneeStreetNo,
           String consigneeTelephone,
           DeclareItem[] declareItems,
           String destinationCountryCode,
           String goodsCategory,
           String goodsDescription,
           Double height,
           String insured,
           Double length,
           String memo,
           String orderNo,
           String originCountryCode,
           PackageItem[] packageItems,
           Long pieces,
           String platformNo,
           String shipperAddress,
           String shipperCity,
           String shipperCompanyName,
           String shipperMobile,
           String shipperName,
           String shipperPostcode,
           String shipperProvince,
           String shipperStreet,
           String shipperStreetNo,
           String shipperTelephone,
           String trackingNo,
           String transportWayCode,
           Double weight,
           Double width) {
           this.additionalJson = additionalJson;
           this.cargoCode = cargoCode;
           this.consigneeAddress = consigneeAddress;
           this.consigneeCity = consigneeCity;
           this.consigneeCompanyName = consigneeCompanyName;
           this.consigneeMobile = consigneeMobile;
           this.consigneeName = consigneeName;
           this.consigneePostcode = consigneePostcode;
           this.consigneeProvince = consigneeProvince;
           this.consigneeStreet = consigneeStreet;
           this.consigneeStreetNo = consigneeStreetNo;
           this.consigneeTelephone = consigneeTelephone;
           this.declareItems = declareItems;
           this.destinationCountryCode = destinationCountryCode;
           this.goodsCategory = goodsCategory;
           this.goodsDescription = goodsDescription;
           this.height = height;
           this.insured = insured;
           this.length = length;
           this.memo = memo;
           this.orderNo = orderNo;
           this.originCountryCode = originCountryCode;
           this.packageItems = packageItems;
           this.pieces = pieces;
           this.platformNo = platformNo;
           this.shipperAddress = shipperAddress;
           this.shipperCity = shipperCity;
           this.shipperCompanyName = shipperCompanyName;
           this.shipperMobile = shipperMobile;
           this.shipperName = shipperName;
           this.shipperPostcode = shipperPostcode;
           this.shipperProvince = shipperProvince;
           this.shipperStreet = shipperStreet;
           this.shipperStreetNo = shipperStreetNo;
           this.shipperTelephone = shipperTelephone;
           this.trackingNo = trackingNo;
           this.transportWayCode = transportWayCode;
           this.weight = weight;
           this.width = width;
    }


    /**
     * Gets the additionalJson value for this CreateOrderRequestV2.
     * 
     * @return additionalJson
     */
    public String getAdditionalJson() {
        return additionalJson;
    }


    /**
     * Sets the additionalJson value for this CreateOrderRequestV2.
     * 
     * @param additionalJson
     */
    public void setAdditionalJson(String additionalJson) {
        this.additionalJson = additionalJson;
    }


    /**
     * Gets the cargoCode value for this CreateOrderRequestV2.
     * 
     * @return cargoCode
     */
    public String getCargoCode() {
        return cargoCode;
    }


    /**
     * Sets the cargoCode value for this CreateOrderRequestV2.
     * 
     * @param cargoCode
     */
    public void setCargoCode(String cargoCode) {
        this.cargoCode = cargoCode;
    }


    /**
     * Gets the consigneeAddress value for this CreateOrderRequestV2.
     * 
     * @return consigneeAddress
     */
    public String getConsigneeAddress() {
        return consigneeAddress;
    }


    /**
     * Sets the consigneeAddress value for this CreateOrderRequestV2.
     * 
     * @param consigneeAddress
     */
    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }


    /**
     * Gets the consigneeCity value for this CreateOrderRequestV2.
     * 
     * @return consigneeCity
     */
    public String getConsigneeCity() {
        return consigneeCity;
    }


    /**
     * Sets the consigneeCity value for this CreateOrderRequestV2.
     * 
     * @param consigneeCity
     */
    public void setConsigneeCity(String consigneeCity) {
        this.consigneeCity = consigneeCity;
    }


    /**
     * Gets the consigneeCompanyName value for this CreateOrderRequestV2.
     * 
     * @return consigneeCompanyName
     */
    public String getConsigneeCompanyName() {
        return consigneeCompanyName;
    }


    /**
     * Sets the consigneeCompanyName value for this CreateOrderRequestV2.
     * 
     * @param consigneeCompanyName
     */
    public void setConsigneeCompanyName(String consigneeCompanyName) {
        this.consigneeCompanyName = consigneeCompanyName;
    }


    /**
     * Gets the consigneeMobile value for this CreateOrderRequestV2.
     * 
     * @return consigneeMobile
     */
    public String getConsigneeMobile() {
        return consigneeMobile;
    }


    /**
     * Sets the consigneeMobile value for this CreateOrderRequestV2.
     * 
     * @param consigneeMobile
     */
    public void setConsigneeMobile(String consigneeMobile) {
        this.consigneeMobile = consigneeMobile;
    }


    /**
     * Gets the consigneeName value for this CreateOrderRequestV2.
     * 
     * @return consigneeName
     */
    public String getConsigneeName() {
        return consigneeName;
    }


    /**
     * Sets the consigneeName value for this CreateOrderRequestV2.
     * 
     * @param consigneeName
     */
    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }


    /**
     * Gets the consigneePostcode value for this CreateOrderRequestV2.
     * 
     * @return consigneePostcode
     */
    public String getConsigneePostcode() {
        return consigneePostcode;
    }


    /**
     * Sets the consigneePostcode value for this CreateOrderRequestV2.
     * 
     * @param consigneePostcode
     */
    public void setConsigneePostcode(String consigneePostcode) {
        this.consigneePostcode = consigneePostcode;
    }


    /**
     * Gets the consigneeProvince value for this CreateOrderRequestV2.
     * 
     * @return consigneeProvince
     */
    public String getConsigneeProvince() {
        return consigneeProvince;
    }


    /**
     * Sets the consigneeProvince value for this CreateOrderRequestV2.
     * 
     * @param consigneeProvince
     */
    public void setConsigneeProvince(String consigneeProvince) {
        this.consigneeProvince = consigneeProvince;
    }


    /**
     * Gets the consigneeStreet value for this CreateOrderRequestV2.
     * 
     * @return consigneeStreet
     */
    public String getConsigneeStreet() {
        return consigneeStreet;
    }


    /**
     * Sets the consigneeStreet value for this CreateOrderRequestV2.
     * 
     * @param consigneeStreet
     */
    public void setConsigneeStreet(String consigneeStreet) {
        this.consigneeStreet = consigneeStreet;
    }


    /**
     * Gets the consigneeStreetNo value for this CreateOrderRequestV2.
     * 
     * @return consigneeStreetNo
     */
    public String getConsigneeStreetNo() {
        return consigneeStreetNo;
    }


    /**
     * Sets the consigneeStreetNo value for this CreateOrderRequestV2.
     * 
     * @param consigneeStreetNo
     */
    public void setConsigneeStreetNo(String consigneeStreetNo) {
        this.consigneeStreetNo = consigneeStreetNo;
    }


    /**
     * Gets the consigneeTelephone value for this CreateOrderRequestV2.
     * 
     * @return consigneeTelephone
     */
    public String getConsigneeTelephone() {
        return consigneeTelephone;
    }


    /**
     * Sets the consigneeTelephone value for this CreateOrderRequestV2.
     * 
     * @param consigneeTelephone
     */
    public void setConsigneeTelephone(String consigneeTelephone) {
        this.consigneeTelephone = consigneeTelephone;
    }


    /**
     * Gets the declareItems value for this CreateOrderRequestV2.
     * 
     * @return declareItems
     */
    public DeclareItem[] getDeclareItems() {
        return declareItems;
    }


    /**
     * Sets the declareItems value for this CreateOrderRequestV2.
     * 
     * @param declareItems
     */
    public void setDeclareItems(DeclareItem[] declareItems) {
        this.declareItems = declareItems;
    }

    public DeclareItem getDeclareItems(int i) {
        return this.declareItems[i];
    }

    public void setDeclareItems(int i, DeclareItem _value) {
        this.declareItems[i] = _value;
    }


    /**
     * Gets the destinationCountryCode value for this CreateOrderRequestV2.
     * 
     * @return destinationCountryCode
     */
    public String getDestinationCountryCode() {
        return destinationCountryCode;
    }


    /**
     * Sets the destinationCountryCode value for this CreateOrderRequestV2.
     * 
     * @param destinationCountryCode
     */
    public void setDestinationCountryCode(String destinationCountryCode) {
        this.destinationCountryCode = destinationCountryCode;
    }


    /**
     * Gets the goodsCategory value for this CreateOrderRequestV2.
     * 
     * @return goodsCategory
     */
    public String getGoodsCategory() {
        return goodsCategory;
    }


    /**
     * Sets the goodsCategory value for this CreateOrderRequestV2.
     * 
     * @param goodsCategory
     */
    public void setGoodsCategory(String goodsCategory) {
        this.goodsCategory = goodsCategory;
    }


    /**
     * Gets the goodsDescription value for this CreateOrderRequestV2.
     * 
     * @return goodsDescription
     */
    public String getGoodsDescription() {
        return goodsDescription;
    }


    /**
     * Sets the goodsDescription value for this CreateOrderRequestV2.
     * 
     * @param goodsDescription
     */
    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
    }


    /**
     * Gets the height value for this CreateOrderRequestV2.
     * 
     * @return height
     */
    public Double getHeight() {
        return height;
    }


    /**
     * Sets the height value for this CreateOrderRequestV2.
     * 
     * @param height
     */
    public void setHeight(Double height) {
        this.height = height;
    }


    /**
     * Gets the insured value for this CreateOrderRequestV2.
     * 
     * @return insured
     */
    public String getInsured() {
        return insured;
    }


    /**
     * Sets the insured value for this CreateOrderRequestV2.
     * 
     * @param insured
     */
    public void setInsured(String insured) {
        this.insured = insured;
    }


    /**
     * Gets the length value for this CreateOrderRequestV2.
     * 
     * @return length
     */
    public Double getLength() {
        return length;
    }


    /**
     * Sets the length value for this CreateOrderRequestV2.
     * 
     * @param length
     */
    public void setLength(Double length) {
        this.length = length;
    }


    /**
     * Gets the memo value for this CreateOrderRequestV2.
     * 
     * @return memo
     */
    public String getMemo() {
        return memo;
    }


    /**
     * Sets the memo value for this CreateOrderRequestV2.
     * 
     * @param memo
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }


    /**
     * Gets the orderNo value for this CreateOrderRequestV2.
     * 
     * @return orderNo
     */
    public String getOrderNo() {
        return orderNo;
    }


    /**
     * Sets the orderNo value for this CreateOrderRequestV2.
     * 
     * @param orderNo
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }


    /**
     * Gets the originCountryCode value for this CreateOrderRequestV2.
     * 
     * @return originCountryCode
     */
    public String getOriginCountryCode() {
        return originCountryCode;
    }


    /**
     * Sets the originCountryCode value for this CreateOrderRequestV2.
     * 
     * @param originCountryCode
     */
    public void setOriginCountryCode(String originCountryCode) {
        this.originCountryCode = originCountryCode;
    }


    /**
     * Gets the packageItems value for this CreateOrderRequestV2.
     * 
     * @return packageItems
     */
    public PackageItem[] getPackageItems() {
        return packageItems;
    }


    /**
     * Sets the packageItems value for this CreateOrderRequestV2.
     * 
     * @param packageItems
     */
    public void setPackageItems(PackageItem[] packageItems) {
        this.packageItems = packageItems;
    }

    public PackageItem getPackageItems(int i) {
        return this.packageItems[i];
    }

    public void setPackageItems(int i, PackageItem _value) {
        this.packageItems[i] = _value;
    }


    /**
     * Gets the pieces value for this CreateOrderRequestV2.
     * 
     * @return pieces
     */
    public Long getPieces() {
        return pieces;
    }


    /**
     * Sets the pieces value for this CreateOrderRequestV2.
     * 
     * @param pieces
     */
    public void setPieces(Long pieces) {
        this.pieces = pieces;
    }


    /**
     * Gets the platformNo value for this CreateOrderRequestV2.
     * 
     * @return platformNo
     */
    public String getPlatformNo() {
        return platformNo;
    }


    /**
     * Sets the platformNo value for this CreateOrderRequestV2.
     * 
     * @param platformNo
     */
    public void setPlatformNo(String platformNo) {
        this.platformNo = platformNo;
    }


    /**
     * Gets the shipperAddress value for this CreateOrderRequestV2.
     * 
     * @return shipperAddress
     */
    public String getShipperAddress() {
        return shipperAddress;
    }


    /**
     * Sets the shipperAddress value for this CreateOrderRequestV2.
     * 
     * @param shipperAddress
     */
    public void setShipperAddress(String shipperAddress) {
        this.shipperAddress = shipperAddress;
    }


    /**
     * Gets the shipperCity value for this CreateOrderRequestV2.
     * 
     * @return shipperCity
     */
    public String getShipperCity() {
        return shipperCity;
    }


    /**
     * Sets the shipperCity value for this CreateOrderRequestV2.
     * 
     * @param shipperCity
     */
    public void setShipperCity(String shipperCity) {
        this.shipperCity = shipperCity;
    }


    /**
     * Gets the shipperCompanyName value for this CreateOrderRequestV2.
     * 
     * @return shipperCompanyName
     */
    public String getShipperCompanyName() {
        return shipperCompanyName;
    }


    /**
     * Sets the shipperCompanyName value for this CreateOrderRequestV2.
     * 
     * @param shipperCompanyName
     */
    public void setShipperCompanyName(String shipperCompanyName) {
        this.shipperCompanyName = shipperCompanyName;
    }


    /**
     * Gets the shipperMobile value for this CreateOrderRequestV2.
     * 
     * @return shipperMobile
     */
    public String getShipperMobile() {
        return shipperMobile;
    }


    /**
     * Sets the shipperMobile value for this CreateOrderRequestV2.
     * 
     * @param shipperMobile
     */
    public void setShipperMobile(String shipperMobile) {
        this.shipperMobile = shipperMobile;
    }


    /**
     * Gets the shipperName value for this CreateOrderRequestV2.
     * 
     * @return shipperName
     */
    public String getShipperName() {
        return shipperName;
    }


    /**
     * Sets the shipperName value for this CreateOrderRequestV2.
     * 
     * @param shipperName
     */
    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }


    /**
     * Gets the shipperPostcode value for this CreateOrderRequestV2.
     * 
     * @return shipperPostcode
     */
    public String getShipperPostcode() {
        return shipperPostcode;
    }


    /**
     * Sets the shipperPostcode value for this CreateOrderRequestV2.
     * 
     * @param shipperPostcode
     */
    public void setShipperPostcode(String shipperPostcode) {
        this.shipperPostcode = shipperPostcode;
    }


    /**
     * Gets the shipperProvince value for this CreateOrderRequestV2.
     * 
     * @return shipperProvince
     */
    public String getShipperProvince() {
        return shipperProvince;
    }


    /**
     * Sets the shipperProvince value for this CreateOrderRequestV2.
     * 
     * @param shipperProvince
     */
    public void setShipperProvince(String shipperProvince) {
        this.shipperProvince = shipperProvince;
    }


    /**
     * Gets the shipperStreet value for this CreateOrderRequestV2.
     * 
     * @return shipperStreet
     */
    public String getShipperStreet() {
        return shipperStreet;
    }


    /**
     * Sets the shipperStreet value for this CreateOrderRequestV2.
     * 
     * @param shipperStreet
     */
    public void setShipperStreet(String shipperStreet) {
        this.shipperStreet = shipperStreet;
    }


    /**
     * Gets the shipperStreetNo value for this CreateOrderRequestV2.
     * 
     * @return shipperStreetNo
     */
    public String getShipperStreetNo() {
        return shipperStreetNo;
    }


    /**
     * Sets the shipperStreetNo value for this CreateOrderRequestV2.
     * 
     * @param shipperStreetNo
     */
    public void setShipperStreetNo(String shipperStreetNo) {
        this.shipperStreetNo = shipperStreetNo;
    }


    /**
     * Gets the shipperTelephone value for this CreateOrderRequestV2.
     * 
     * @return shipperTelephone
     */
    public String getShipperTelephone() {
        return shipperTelephone;
    }


    /**
     * Sets the shipperTelephone value for this CreateOrderRequestV2.
     * 
     * @param shipperTelephone
     */
    public void setShipperTelephone(String shipperTelephone) {
        this.shipperTelephone = shipperTelephone;
    }


    /**
     * Gets the trackingNo value for this CreateOrderRequestV2.
     * 
     * @return trackingNo
     */
    public String getTrackingNo() {
        return trackingNo;
    }


    /**
     * Sets the trackingNo value for this CreateOrderRequestV2.
     * 
     * @param trackingNo
     */
    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }


    /**
     * Gets the transportWayCode value for this CreateOrderRequestV2.
     * 
     * @return transportWayCode
     */
    public String getTransportWayCode() {
        return transportWayCode;
    }


    /**
     * Sets the transportWayCode value for this CreateOrderRequestV2.
     * 
     * @param transportWayCode
     */
    public void setTransportWayCode(String transportWayCode) {
        this.transportWayCode = transportWayCode;
    }


    /**
     * Gets the weight value for this CreateOrderRequestV2.
     * 
     * @return weight
     */
    public Double getWeight() {
        return weight;
    }


    /**
     * Sets the weight value for this CreateOrderRequestV2.
     * 
     * @param weight
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }


    /**
     * Gets the width value for this CreateOrderRequestV2.
     * 
     * @return width
     */
    public Double getWidth() {
        return width;
    }


    /**
     * Sets the width value for this CreateOrderRequestV2.
     * 
     * @param width
     */
    public void setWidth(Double width) {
        this.width = width;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof CreateOrderRequestV2)) return false;
        CreateOrderRequestV2 other = (CreateOrderRequestV2) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.additionalJson==null && other.getAdditionalJson()==null) || 
             (this.additionalJson!=null &&
              this.additionalJson.equals(other.getAdditionalJson()))) &&
            ((this.cargoCode==null && other.getCargoCode()==null) || 
             (this.cargoCode!=null &&
              this.cargoCode.equals(other.getCargoCode()))) &&
            ((this.consigneeAddress==null && other.getConsigneeAddress()==null) || 
             (this.consigneeAddress!=null &&
              this.consigneeAddress.equals(other.getConsigneeAddress()))) &&
            ((this.consigneeCity==null && other.getConsigneeCity()==null) || 
             (this.consigneeCity!=null &&
              this.consigneeCity.equals(other.getConsigneeCity()))) &&
            ((this.consigneeCompanyName==null && other.getConsigneeCompanyName()==null) || 
             (this.consigneeCompanyName!=null &&
              this.consigneeCompanyName.equals(other.getConsigneeCompanyName()))) &&
            ((this.consigneeMobile==null && other.getConsigneeMobile()==null) || 
             (this.consigneeMobile!=null &&
              this.consigneeMobile.equals(other.getConsigneeMobile()))) &&
            ((this.consigneeName==null && other.getConsigneeName()==null) || 
             (this.consigneeName!=null &&
              this.consigneeName.equals(other.getConsigneeName()))) &&
            ((this.consigneePostcode==null && other.getConsigneePostcode()==null) || 
             (this.consigneePostcode!=null &&
              this.consigneePostcode.equals(other.getConsigneePostcode()))) &&
            ((this.consigneeProvince==null && other.getConsigneeProvince()==null) || 
             (this.consigneeProvince!=null &&
              this.consigneeProvince.equals(other.getConsigneeProvince()))) &&
            ((this.consigneeStreet==null && other.getConsigneeStreet()==null) || 
             (this.consigneeStreet!=null &&
              this.consigneeStreet.equals(other.getConsigneeStreet()))) &&
            ((this.consigneeStreetNo==null && other.getConsigneeStreetNo()==null) || 
             (this.consigneeStreetNo!=null &&
              this.consigneeStreetNo.equals(other.getConsigneeStreetNo()))) &&
            ((this.consigneeTelephone==null && other.getConsigneeTelephone()==null) || 
             (this.consigneeTelephone!=null &&
              this.consigneeTelephone.equals(other.getConsigneeTelephone()))) &&
            ((this.declareItems==null && other.getDeclareItems()==null) || 
             (this.declareItems!=null &&
              java.util.Arrays.equals(this.declareItems, other.getDeclareItems()))) &&
            ((this.destinationCountryCode==null && other.getDestinationCountryCode()==null) || 
             (this.destinationCountryCode!=null &&
              this.destinationCountryCode.equals(other.getDestinationCountryCode()))) &&
            ((this.goodsCategory==null && other.getGoodsCategory()==null) || 
             (this.goodsCategory!=null &&
              this.goodsCategory.equals(other.getGoodsCategory()))) &&
            ((this.goodsDescription==null && other.getGoodsDescription()==null) || 
             (this.goodsDescription!=null &&
              this.goodsDescription.equals(other.getGoodsDescription()))) &&
            ((this.height==null && other.getHeight()==null) || 
             (this.height!=null &&
              this.height.equals(other.getHeight()))) &&
            ((this.insured==null && other.getInsured()==null) || 
             (this.insured!=null &&
              this.insured.equals(other.getInsured()))) &&
            ((this.length==null && other.getLength()==null) || 
             (this.length!=null &&
              this.length.equals(other.getLength()))) &&
            ((this.memo==null && other.getMemo()==null) || 
             (this.memo!=null &&
              this.memo.equals(other.getMemo()))) &&
            ((this.orderNo==null && other.getOrderNo()==null) || 
             (this.orderNo!=null &&
              this.orderNo.equals(other.getOrderNo()))) &&
            ((this.originCountryCode==null && other.getOriginCountryCode()==null) || 
             (this.originCountryCode!=null &&
              this.originCountryCode.equals(other.getOriginCountryCode()))) &&
            ((this.packageItems==null && other.getPackageItems()==null) || 
             (this.packageItems!=null &&
              java.util.Arrays.equals(this.packageItems, other.getPackageItems()))) &&
            ((this.pieces==null && other.getPieces()==null) || 
             (this.pieces!=null &&
              this.pieces.equals(other.getPieces()))) &&
            ((this.platformNo==null && other.getPlatformNo()==null) || 
             (this.platformNo!=null &&
              this.platformNo.equals(other.getPlatformNo()))) &&
            ((this.shipperAddress==null && other.getShipperAddress()==null) || 
             (this.shipperAddress!=null &&
              this.shipperAddress.equals(other.getShipperAddress()))) &&
            ((this.shipperCity==null && other.getShipperCity()==null) || 
             (this.shipperCity!=null &&
              this.shipperCity.equals(other.getShipperCity()))) &&
            ((this.shipperCompanyName==null && other.getShipperCompanyName()==null) || 
             (this.shipperCompanyName!=null &&
              this.shipperCompanyName.equals(other.getShipperCompanyName()))) &&
            ((this.shipperMobile==null && other.getShipperMobile()==null) || 
             (this.shipperMobile!=null &&
              this.shipperMobile.equals(other.getShipperMobile()))) &&
            ((this.shipperName==null && other.getShipperName()==null) || 
             (this.shipperName!=null &&
              this.shipperName.equals(other.getShipperName()))) &&
            ((this.shipperPostcode==null && other.getShipperPostcode()==null) || 
             (this.shipperPostcode!=null &&
              this.shipperPostcode.equals(other.getShipperPostcode()))) &&
            ((this.shipperProvince==null && other.getShipperProvince()==null) || 
             (this.shipperProvince!=null &&
              this.shipperProvince.equals(other.getShipperProvince()))) &&
            ((this.shipperStreet==null && other.getShipperStreet()==null) || 
             (this.shipperStreet!=null &&
              this.shipperStreet.equals(other.getShipperStreet()))) &&
            ((this.shipperStreetNo==null && other.getShipperStreetNo()==null) || 
             (this.shipperStreetNo!=null &&
              this.shipperStreetNo.equals(other.getShipperStreetNo()))) &&
            ((this.shipperTelephone==null && other.getShipperTelephone()==null) || 
             (this.shipperTelephone!=null &&
              this.shipperTelephone.equals(other.getShipperTelephone()))) &&
            ((this.trackingNo==null && other.getTrackingNo()==null) || 
             (this.trackingNo!=null &&
              this.trackingNo.equals(other.getTrackingNo()))) &&
            ((this.transportWayCode==null && other.getTransportWayCode()==null) || 
             (this.transportWayCode!=null &&
              this.transportWayCode.equals(other.getTransportWayCode()))) &&
            ((this.weight==null && other.getWeight()==null) || 
             (this.weight!=null &&
              this.weight.equals(other.getWeight()))) &&
            ((this.width==null && other.getWidth()==null) || 
             (this.width!=null &&
              this.width.equals(other.getWidth())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAdditionalJson() != null) {
            _hashCode += getAdditionalJson().hashCode();
        }
        if (getCargoCode() != null) {
            _hashCode += getCargoCode().hashCode();
        }
        if (getConsigneeAddress() != null) {
            _hashCode += getConsigneeAddress().hashCode();
        }
        if (getConsigneeCity() != null) {
            _hashCode += getConsigneeCity().hashCode();
        }
        if (getConsigneeCompanyName() != null) {
            _hashCode += getConsigneeCompanyName().hashCode();
        }
        if (getConsigneeMobile() != null) {
            _hashCode += getConsigneeMobile().hashCode();
        }
        if (getConsigneeName() != null) {
            _hashCode += getConsigneeName().hashCode();
        }
        if (getConsigneePostcode() != null) {
            _hashCode += getConsigneePostcode().hashCode();
        }
        if (getConsigneeProvince() != null) {
            _hashCode += getConsigneeProvince().hashCode();
        }
        if (getConsigneeStreet() != null) {
            _hashCode += getConsigneeStreet().hashCode();
        }
        if (getConsigneeStreetNo() != null) {
            _hashCode += getConsigneeStreetNo().hashCode();
        }
        if (getConsigneeTelephone() != null) {
            _hashCode += getConsigneeTelephone().hashCode();
        }
        if (getDeclareItems() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDeclareItems());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getDeclareItems(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDestinationCountryCode() != null) {
            _hashCode += getDestinationCountryCode().hashCode();
        }
        if (getGoodsCategory() != null) {
            _hashCode += getGoodsCategory().hashCode();
        }
        if (getGoodsDescription() != null) {
            _hashCode += getGoodsDescription().hashCode();
        }
        if (getHeight() != null) {
            _hashCode += getHeight().hashCode();
        }
        if (getInsured() != null) {
            _hashCode += getInsured().hashCode();
        }
        if (getLength() != null) {
            _hashCode += getLength().hashCode();
        }
        if (getMemo() != null) {
            _hashCode += getMemo().hashCode();
        }
        if (getOrderNo() != null) {
            _hashCode += getOrderNo().hashCode();
        }
        if (getOriginCountryCode() != null) {
            _hashCode += getOriginCountryCode().hashCode();
        }
        if (getPackageItems() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPackageItems());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getPackageItems(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPieces() != null) {
            _hashCode += getPieces().hashCode();
        }
        if (getPlatformNo() != null) {
            _hashCode += getPlatformNo().hashCode();
        }
        if (getShipperAddress() != null) {
            _hashCode += getShipperAddress().hashCode();
        }
        if (getShipperCity() != null) {
            _hashCode += getShipperCity().hashCode();
        }
        if (getShipperCompanyName() != null) {
            _hashCode += getShipperCompanyName().hashCode();
        }
        if (getShipperMobile() != null) {
            _hashCode += getShipperMobile().hashCode();
        }
        if (getShipperName() != null) {
            _hashCode += getShipperName().hashCode();
        }
        if (getShipperPostcode() != null) {
            _hashCode += getShipperPostcode().hashCode();
        }
        if (getShipperProvince() != null) {
            _hashCode += getShipperProvince().hashCode();
        }
        if (getShipperStreet() != null) {
            _hashCode += getShipperStreet().hashCode();
        }
        if (getShipperStreetNo() != null) {
            _hashCode += getShipperStreetNo().hashCode();
        }
        if (getShipperTelephone() != null) {
            _hashCode += getShipperTelephone().hashCode();
        }
        if (getTrackingNo() != null) {
            _hashCode += getTrackingNo().hashCode();
        }
        if (getTransportWayCode() != null) {
            _hashCode += getTransportWayCode().hashCode();
        }
        if (getWeight() != null) {
            _hashCode += getWeight().hashCode();
        }
        if (getWidth() != null) {
            _hashCode += getWidth().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CreateOrderRequestV2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.hop.service.ws.hlt.com/", "createOrderRequestV2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additionalJson");
        elemField.setXmlName(new javax.xml.namespace.QName("", "additionalJson"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cargoCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cargoCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consigneeAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "consigneeAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consigneeCity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "consigneeCity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consigneeCompanyName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "consigneeCompanyName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consigneeMobile");
        elemField.setXmlName(new javax.xml.namespace.QName("", "consigneeMobile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consigneeName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "consigneeName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consigneePostcode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "consigneePostcode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consigneeProvince");
        elemField.setXmlName(new javax.xml.namespace.QName("", "consigneeProvince"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consigneeStreet");
        elemField.setXmlName(new javax.xml.namespace.QName("", "consigneeStreet"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consigneeStreetNo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "consigneeStreetNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consigneeTelephone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "consigneeTelephone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("declareItems");
        elemField.setXmlName(new javax.xml.namespace.QName("", "declareItems"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.hop.service.ws.hlt.com/", "declareItem"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinationCountryCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "destinationCountryCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("goodsCategory");
        elemField.setXmlName(new javax.xml.namespace.QName("", "goodsCategory"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("goodsDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "goodsDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("height");
        elemField.setXmlName(new javax.xml.namespace.QName("", "height"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("insured");
        elemField.setXmlName(new javax.xml.namespace.QName("", "insured"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("length");
        elemField.setXmlName(new javax.xml.namespace.QName("", "length"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "memo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderNo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orderNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("originCountryCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "originCountryCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("packageItems");
        elemField.setXmlName(new javax.xml.namespace.QName("", "packageItems"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.hop.service.ws.hlt.com/", "packageItem"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pieces");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pieces"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("platformNo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "platformNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipperAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipperAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipperCity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipperCity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipperCompanyName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipperCompanyName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipperMobile");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipperMobile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipperName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipperName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipperPostcode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipperPostcode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipperProvince");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipperProvince"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipperStreet");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipperStreet"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipperStreetNo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipperStreetNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipperTelephone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipperTelephone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trackingNo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trackingNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transportWayCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transportWayCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("weight");
        elemField.setXmlName(new javax.xml.namespace.QName("", "weight"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("width");
        elemField.setXmlName(new javax.xml.namespace.QName("", "width"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           String mechType, 
           Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType, 
           Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
