/**
 * Order.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.baifei.modules.webservice.haihehui;

public class Order  implements java.io.Serializable {
    private String auditTime;

    private Double balanceWeight;

    private String cargoCode;

    private String city;

    private String consigneeCompanyName;

    private String consigneeCountry;

    private String consigneeMobile;

    private String consigneeName;

    private String consigneePostcode;

    private String consigneeTelephone;

    private String createTime;

    private String currency;

    private DeclareItem[] declareItems;

    private String destinationCountryCode;

    private String goodsCategory;

    private String goodsDescription;

    private String hawbCode;

    private Double height;

    private String insured;

    private String issue;

    private String issueDesc;

    private Double length;

    private String memo;

    private Integer orderId;

    private String orderNo;

    private String originCountryCode;

    private Double otherFee;

    private Integer pieces;

    private String province;

    private Double realVolWeight;

    private Double realWeight;

    private String receiveTime;

    private String shipTime;

    private String shipperAddress;

    private String shipperCompanyName;

    private String shipperMobile;

    private String shipperName;

    private String shipperPostcode;

    private String shipperTelephone;

    private String status;

    private String statusDescription;

    private String street;

    private String taxReturnNo;

    private Double totalFee;

    private String trackingNo;

    private Double transportFee;

    private String transportWayCode;

    private String transportWayName;

    private String vweightList;

    private Double weight;

    private Double width;

    public Order() {
    }

    public Order(
           String auditTime,
           Double balanceWeight,
           String cargoCode,
           String city,
           String consigneeCompanyName,
           String consigneeCountry,
           String consigneeMobile,
           String consigneeName,
           String consigneePostcode,
           String consigneeTelephone,
           String createTime,
           String currency,
           DeclareItem[] declareItems,
           String destinationCountryCode,
           String goodsCategory,
           String goodsDescription,
           String hawbCode,
           Double height,
           String insured,
           String issue,
           String issueDesc,
           Double length,
           String memo,
           Integer orderId,
           String orderNo,
           String originCountryCode,
           Double otherFee,
           Integer pieces,
           String province,
           Double realVolWeight,
           Double realWeight,
           String receiveTime,
           String shipTime,
           String shipperAddress,
           String shipperCompanyName,
           String shipperMobile,
           String shipperName,
           String shipperPostcode,
           String shipperTelephone,
           String status,
           String statusDescription,
           String street,
           String taxReturnNo,
           Double totalFee,
           String trackingNo,
           Double transportFee,
           String transportWayCode,
           String transportWayName,
           String vweightList,
           Double weight,
           Double width) {
           this.auditTime = auditTime;
           this.balanceWeight = balanceWeight;
           this.cargoCode = cargoCode;
           this.city = city;
           this.consigneeCompanyName = consigneeCompanyName;
           this.consigneeCountry = consigneeCountry;
           this.consigneeMobile = consigneeMobile;
           this.consigneeName = consigneeName;
           this.consigneePostcode = consigneePostcode;
           this.consigneeTelephone = consigneeTelephone;
           this.createTime = createTime;
           this.currency = currency;
           this.declareItems = declareItems;
           this.destinationCountryCode = destinationCountryCode;
           this.goodsCategory = goodsCategory;
           this.goodsDescription = goodsDescription;
           this.hawbCode = hawbCode;
           this.height = height;
           this.insured = insured;
           this.issue = issue;
           this.issueDesc = issueDesc;
           this.length = length;
           this.memo = memo;
           this.orderId = orderId;
           this.orderNo = orderNo;
           this.originCountryCode = originCountryCode;
           this.otherFee = otherFee;
           this.pieces = pieces;
           this.province = province;
           this.realVolWeight = realVolWeight;
           this.realWeight = realWeight;
           this.receiveTime = receiveTime;
           this.shipTime = shipTime;
           this.shipperAddress = shipperAddress;
           this.shipperCompanyName = shipperCompanyName;
           this.shipperMobile = shipperMobile;
           this.shipperName = shipperName;
           this.shipperPostcode = shipperPostcode;
           this.shipperTelephone = shipperTelephone;
           this.status = status;
           this.statusDescription = statusDescription;
           this.street = street;
           this.taxReturnNo = taxReturnNo;
           this.totalFee = totalFee;
           this.trackingNo = trackingNo;
           this.transportFee = transportFee;
           this.transportWayCode = transportWayCode;
           this.transportWayName = transportWayName;
           this.vweightList = vweightList;
           this.weight = weight;
           this.width = width;
    }


    /**
     * Gets the auditTime value for this Order.
     * 
     * @return auditTime
     */
    public String getAuditTime() {
        return auditTime;
    }


    /**
     * Sets the auditTime value for this Order.
     * 
     * @param auditTime
     */
    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }


    /**
     * Gets the balanceWeight value for this Order.
     * 
     * @return balanceWeight
     */
    public Double getBalanceWeight() {
        return balanceWeight;
    }


    /**
     * Sets the balanceWeight value for this Order.
     * 
     * @param balanceWeight
     */
    public void setBalanceWeight(Double balanceWeight) {
        this.balanceWeight = balanceWeight;
    }


    /**
     * Gets the cargoCode value for this Order.
     * 
     * @return cargoCode
     */
    public String getCargoCode() {
        return cargoCode;
    }


    /**
     * Sets the cargoCode value for this Order.
     * 
     * @param cargoCode
     */
    public void setCargoCode(String cargoCode) {
        this.cargoCode = cargoCode;
    }


    /**
     * Gets the city value for this Order.
     * 
     * @return city
     */
    public String getCity() {
        return city;
    }


    /**
     * Sets the city value for this Order.
     * 
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }


    /**
     * Gets the consigneeCompanyName value for this Order.
     * 
     * @return consigneeCompanyName
     */
    public String getConsigneeCompanyName() {
        return consigneeCompanyName;
    }


    /**
     * Sets the consigneeCompanyName value for this Order.
     * 
     * @param consigneeCompanyName
     */
    public void setConsigneeCompanyName(String consigneeCompanyName) {
        this.consigneeCompanyName = consigneeCompanyName;
    }


    /**
     * Gets the consigneeCountry value for this Order.
     * 
     * @return consigneeCountry
     */
    public String getConsigneeCountry() {
        return consigneeCountry;
    }


    /**
     * Sets the consigneeCountry value for this Order.
     * 
     * @param consigneeCountry
     */
    public void setConsigneeCountry(String consigneeCountry) {
        this.consigneeCountry = consigneeCountry;
    }


    /**
     * Gets the consigneeMobile value for this Order.
     * 
     * @return consigneeMobile
     */
    public String getConsigneeMobile() {
        return consigneeMobile;
    }


    /**
     * Sets the consigneeMobile value for this Order.
     * 
     * @param consigneeMobile
     */
    public void setConsigneeMobile(String consigneeMobile) {
        this.consigneeMobile = consigneeMobile;
    }


    /**
     * Gets the consigneeName value for this Order.
     * 
     * @return consigneeName
     */
    public String getConsigneeName() {
        return consigneeName;
    }


    /**
     * Sets the consigneeName value for this Order.
     * 
     * @param consigneeName
     */
    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }


    /**
     * Gets the consigneePostcode value for this Order.
     * 
     * @return consigneePostcode
     */
    public String getConsigneePostcode() {
        return consigneePostcode;
    }


    /**
     * Sets the consigneePostcode value for this Order.
     * 
     * @param consigneePostcode
     */
    public void setConsigneePostcode(String consigneePostcode) {
        this.consigneePostcode = consigneePostcode;
    }


    /**
     * Gets the consigneeTelephone value for this Order.
     * 
     * @return consigneeTelephone
     */
    public String getConsigneeTelephone() {
        return consigneeTelephone;
    }


    /**
     * Sets the consigneeTelephone value for this Order.
     * 
     * @param consigneeTelephone
     */
    public void setConsigneeTelephone(String consigneeTelephone) {
        this.consigneeTelephone = consigneeTelephone;
    }


    /**
     * Gets the createTime value for this Order.
     * 
     * @return createTime
     */
    public String getCreateTime() {
        return createTime;
    }


    /**
     * Sets the createTime value for this Order.
     * 
     * @param createTime
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    /**
     * Gets the currency value for this Order.
     * 
     * @return currency
     */
    public String getCurrency() {
        return currency;
    }


    /**
     * Sets the currency value for this Order.
     * 
     * @param currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }


    /**
     * Gets the declareItems value for this Order.
     * 
     * @return declareItems
     */
    public DeclareItem[] getDeclareItems() {
        return declareItems;
    }


    /**
     * Sets the declareItems value for this Order.
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
     * Gets the destinationCountryCode value for this Order.
     * 
     * @return destinationCountryCode
     */
    public String getDestinationCountryCode() {
        return destinationCountryCode;
    }


    /**
     * Sets the destinationCountryCode value for this Order.
     * 
     * @param destinationCountryCode
     */
    public void setDestinationCountryCode(String destinationCountryCode) {
        this.destinationCountryCode = destinationCountryCode;
    }


    /**
     * Gets the goodsCategory value for this Order.
     * 
     * @return goodsCategory
     */
    public String getGoodsCategory() {
        return goodsCategory;
    }


    /**
     * Sets the goodsCategory value for this Order.
     * 
     * @param goodsCategory
     */
    public void setGoodsCategory(String goodsCategory) {
        this.goodsCategory = goodsCategory;
    }


    /**
     * Gets the goodsDescription value for this Order.
     * 
     * @return goodsDescription
     */
    public String getGoodsDescription() {
        return goodsDescription;
    }


    /**
     * Sets the goodsDescription value for this Order.
     * 
     * @param goodsDescription
     */
    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
    }


    /**
     * Gets the hawbCode value for this Order.
     * 
     * @return hawbCode
     */
    public String getHawbCode() {
        return hawbCode;
    }


    /**
     * Sets the hawbCode value for this Order.
     * 
     * @param hawbCode
     */
    public void setHawbCode(String hawbCode) {
        this.hawbCode = hawbCode;
    }


    /**
     * Gets the height value for this Order.
     * 
     * @return height
     */
    public Double getHeight() {
        return height;
    }


    /**
     * Sets the height value for this Order.
     * 
     * @param height
     */
    public void setHeight(Double height) {
        this.height = height;
    }


    /**
     * Gets the insured value for this Order.
     * 
     * @return insured
     */
    public String getInsured() {
        return insured;
    }


    /**
     * Sets the insured value for this Order.
     * 
     * @param insured
     */
    public void setInsured(String insured) {
        this.insured = insured;
    }


    /**
     * Gets the issue value for this Order.
     * 
     * @return issue
     */
    public String getIssue() {
        return issue;
    }


    /**
     * Sets the issue value for this Order.
     * 
     * @param issue
     */
    public void setIssue(String issue) {
        this.issue = issue;
    }


    /**
     * Gets the issueDesc value for this Order.
     * 
     * @return issueDesc
     */
    public String getIssueDesc() {
        return issueDesc;
    }


    /**
     * Sets the issueDesc value for this Order.
     * 
     * @param issueDesc
     */
    public void setIssueDesc(String issueDesc) {
        this.issueDesc = issueDesc;
    }


    /**
     * Gets the length value for this Order.
     * 
     * @return length
     */
    public Double getLength() {
        return length;
    }


    /**
     * Sets the length value for this Order.
     * 
     * @param length
     */
    public void setLength(Double length) {
        this.length = length;
    }


    /**
     * Gets the memo value for this Order.
     * 
     * @return memo
     */
    public String getMemo() {
        return memo;
    }


    /**
     * Sets the memo value for this Order.
     * 
     * @param memo
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }


    /**
     * Gets the orderId value for this Order.
     * 
     * @return orderId
     */
    public Integer getOrderId() {
        return orderId;
    }


    /**
     * Sets the orderId value for this Order.
     * 
     * @param orderId
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }


    /**
     * Gets the orderNo value for this Order.
     * 
     * @return orderNo
     */
    public String getOrderNo() {
        return orderNo;
    }


    /**
     * Sets the orderNo value for this Order.
     * 
     * @param orderNo
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }


    /**
     * Gets the originCountryCode value for this Order.
     * 
     * @return originCountryCode
     */
    public String getOriginCountryCode() {
        return originCountryCode;
    }


    /**
     * Sets the originCountryCode value for this Order.
     * 
     * @param originCountryCode
     */
    public void setOriginCountryCode(String originCountryCode) {
        this.originCountryCode = originCountryCode;
    }


    /**
     * Gets the otherFee value for this Order.
     * 
     * @return otherFee
     */
    public Double getOtherFee() {
        return otherFee;
    }


    /**
     * Sets the otherFee value for this Order.
     * 
     * @param otherFee
     */
    public void setOtherFee(Double otherFee) {
        this.otherFee = otherFee;
    }


    /**
     * Gets the pieces value for this Order.
     * 
     * @return pieces
     */
    public Integer getPieces() {
        return pieces;
    }


    /**
     * Sets the pieces value for this Order.
     * 
     * @param pieces
     */
    public void setPieces(Integer pieces) {
        this.pieces = pieces;
    }


    /**
     * Gets the province value for this Order.
     * 
     * @return province
     */
    public String getProvince() {
        return province;
    }


    /**
     * Sets the province value for this Order.
     * 
     * @param province
     */
    public void setProvince(String province) {
        this.province = province;
    }


    /**
     * Gets the realVolWeight value for this Order.
     * 
     * @return realVolWeight
     */
    public Double getRealVolWeight() {
        return realVolWeight;
    }


    /**
     * Sets the realVolWeight value for this Order.
     * 
     * @param realVolWeight
     */
    public void setRealVolWeight(Double realVolWeight) {
        this.realVolWeight = realVolWeight;
    }


    /**
     * Gets the realWeight value for this Order.
     * 
     * @return realWeight
     */
    public Double getRealWeight() {
        return realWeight;
    }


    /**
     * Sets the realWeight value for this Order.
     * 
     * @param realWeight
     */
    public void setRealWeight(Double realWeight) {
        this.realWeight = realWeight;
    }


    /**
     * Gets the receiveTime value for this Order.
     * 
     * @return receiveTime
     */
    public String getReceiveTime() {
        return receiveTime;
    }


    /**
     * Sets the receiveTime value for this Order.
     * 
     * @param receiveTime
     */
    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }


    /**
     * Gets the shipTime value for this Order.
     * 
     * @return shipTime
     */
    public String getShipTime() {
        return shipTime;
    }


    /**
     * Sets the shipTime value for this Order.
     * 
     * @param shipTime
     */
    public void setShipTime(String shipTime) {
        this.shipTime = shipTime;
    }


    /**
     * Gets the shipperAddress value for this Order.
     * 
     * @return shipperAddress
     */
    public String getShipperAddress() {
        return shipperAddress;
    }


    /**
     * Sets the shipperAddress value for this Order.
     * 
     * @param shipperAddress
     */
    public void setShipperAddress(String shipperAddress) {
        this.shipperAddress = shipperAddress;
    }


    /**
     * Gets the shipperCompanyName value for this Order.
     * 
     * @return shipperCompanyName
     */
    public String getShipperCompanyName() {
        return shipperCompanyName;
    }


    /**
     * Sets the shipperCompanyName value for this Order.
     * 
     * @param shipperCompanyName
     */
    public void setShipperCompanyName(String shipperCompanyName) {
        this.shipperCompanyName = shipperCompanyName;
    }


    /**
     * Gets the shipperMobile value for this Order.
     * 
     * @return shipperMobile
     */
    public String getShipperMobile() {
        return shipperMobile;
    }


    /**
     * Sets the shipperMobile value for this Order.
     * 
     * @param shipperMobile
     */
    public void setShipperMobile(String shipperMobile) {
        this.shipperMobile = shipperMobile;
    }


    /**
     * Gets the shipperName value for this Order.
     * 
     * @return shipperName
     */
    public String getShipperName() {
        return shipperName;
    }


    /**
     * Sets the shipperName value for this Order.
     * 
     * @param shipperName
     */
    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }


    /**
     * Gets the shipperPostcode value for this Order.
     * 
     * @return shipperPostcode
     */
    public String getShipperPostcode() {
        return shipperPostcode;
    }


    /**
     * Sets the shipperPostcode value for this Order.
     * 
     * @param shipperPostcode
     */
    public void setShipperPostcode(String shipperPostcode) {
        this.shipperPostcode = shipperPostcode;
    }


    /**
     * Gets the shipperTelephone value for this Order.
     * 
     * @return shipperTelephone
     */
    public String getShipperTelephone() {
        return shipperTelephone;
    }


    /**
     * Sets the shipperTelephone value for this Order.
     * 
     * @param shipperTelephone
     */
    public void setShipperTelephone(String shipperTelephone) {
        this.shipperTelephone = shipperTelephone;
    }


    /**
     * Gets the status value for this Order.
     * 
     * @return status
     */
    public String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this Order.
     * 
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * Gets the statusDescription value for this Order.
     * 
     * @return statusDescription
     */
    public String getStatusDescription() {
        return statusDescription;
    }


    /**
     * Sets the statusDescription value for this Order.
     * 
     * @param statusDescription
     */
    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }


    /**
     * Gets the street value for this Order.
     * 
     * @return street
     */
    public String getStreet() {
        return street;
    }


    /**
     * Sets the street value for this Order.
     * 
     * @param street
     */
    public void setStreet(String street) {
        this.street = street;
    }


    /**
     * Gets the taxReturnNo value for this Order.
     * 
     * @return taxReturnNo
     */
    public String getTaxReturnNo() {
        return taxReturnNo;
    }


    /**
     * Sets the taxReturnNo value for this Order.
     * 
     * @param taxReturnNo
     */
    public void setTaxReturnNo(String taxReturnNo) {
        this.taxReturnNo = taxReturnNo;
    }


    /**
     * Gets the totalFee value for this Order.
     * 
     * @return totalFee
     */
    public Double getTotalFee() {
        return totalFee;
    }


    /**
     * Sets the totalFee value for this Order.
     * 
     * @param totalFee
     */
    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }


    /**
     * Gets the trackingNo value for this Order.
     * 
     * @return trackingNo
     */
    public String getTrackingNo() {
        return trackingNo;
    }


    /**
     * Sets the trackingNo value for this Order.
     * 
     * @param trackingNo
     */
    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }


    /**
     * Gets the transportFee value for this Order.
     * 
     * @return transportFee
     */
    public Double getTransportFee() {
        return transportFee;
    }


    /**
     * Sets the transportFee value for this Order.
     * 
     * @param transportFee
     */
    public void setTransportFee(Double transportFee) {
        this.transportFee = transportFee;
    }


    /**
     * Gets the transportWayCode value for this Order.
     * 
     * @return transportWayCode
     */
    public String getTransportWayCode() {
        return transportWayCode;
    }


    /**
     * Sets the transportWayCode value for this Order.
     * 
     * @param transportWayCode
     */
    public void setTransportWayCode(String transportWayCode) {
        this.transportWayCode = transportWayCode;
    }


    /**
     * Gets the transportWayName value for this Order.
     * 
     * @return transportWayName
     */
    public String getTransportWayName() {
        return transportWayName;
    }


    /**
     * Sets the transportWayName value for this Order.
     * 
     * @param transportWayName
     */
    public void setTransportWayName(String transportWayName) {
        this.transportWayName = transportWayName;
    }


    /**
     * Gets the vweightList value for this Order.
     * 
     * @return vweightList
     */
    public String getVweightList() {
        return vweightList;
    }


    /**
     * Sets the vweightList value for this Order.
     * 
     * @param vweightList
     */
    public void setVweightList(String vweightList) {
        this.vweightList = vweightList;
    }


    /**
     * Gets the weight value for this Order.
     * 
     * @return weight
     */
    public Double getWeight() {
        return weight;
    }


    /**
     * Sets the weight value for this Order.
     * 
     * @param weight
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }


    /**
     * Gets the width value for this Order.
     * 
     * @return width
     */
    public Double getWidth() {
        return width;
    }


    /**
     * Sets the width value for this Order.
     * 
     * @param width
     */
    public void setWidth(Double width) {
        this.width = width;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Order)) return false;
        Order other = (Order) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.auditTime==null && other.getAuditTime()==null) || 
             (this.auditTime!=null &&
              this.auditTime.equals(other.getAuditTime()))) &&
            ((this.balanceWeight==null && other.getBalanceWeight()==null) || 
             (this.balanceWeight!=null &&
              this.balanceWeight.equals(other.getBalanceWeight()))) &&
            ((this.cargoCode==null && other.getCargoCode()==null) || 
             (this.cargoCode!=null &&
              this.cargoCode.equals(other.getCargoCode()))) &&
            ((this.city==null && other.getCity()==null) || 
             (this.city!=null &&
              this.city.equals(other.getCity()))) &&
            ((this.consigneeCompanyName==null && other.getConsigneeCompanyName()==null) || 
             (this.consigneeCompanyName!=null &&
              this.consigneeCompanyName.equals(other.getConsigneeCompanyName()))) &&
            ((this.consigneeCountry==null && other.getConsigneeCountry()==null) || 
             (this.consigneeCountry!=null &&
              this.consigneeCountry.equals(other.getConsigneeCountry()))) &&
            ((this.consigneeMobile==null && other.getConsigneeMobile()==null) || 
             (this.consigneeMobile!=null &&
              this.consigneeMobile.equals(other.getConsigneeMobile()))) &&
            ((this.consigneeName==null && other.getConsigneeName()==null) || 
             (this.consigneeName!=null &&
              this.consigneeName.equals(other.getConsigneeName()))) &&
            ((this.consigneePostcode==null && other.getConsigneePostcode()==null) || 
             (this.consigneePostcode!=null &&
              this.consigneePostcode.equals(other.getConsigneePostcode()))) &&
            ((this.consigneeTelephone==null && other.getConsigneeTelephone()==null) || 
             (this.consigneeTelephone!=null &&
              this.consigneeTelephone.equals(other.getConsigneeTelephone()))) &&
            ((this.createTime==null && other.getCreateTime()==null) || 
             (this.createTime!=null &&
              this.createTime.equals(other.getCreateTime()))) &&
            ((this.currency==null && other.getCurrency()==null) || 
             (this.currency!=null &&
              this.currency.equals(other.getCurrency()))) &&
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
            ((this.hawbCode==null && other.getHawbCode()==null) || 
             (this.hawbCode!=null &&
              this.hawbCode.equals(other.getHawbCode()))) &&
            ((this.height==null && other.getHeight()==null) || 
             (this.height!=null &&
              this.height.equals(other.getHeight()))) &&
            ((this.insured==null && other.getInsured()==null) || 
             (this.insured!=null &&
              this.insured.equals(other.getInsured()))) &&
            ((this.issue==null && other.getIssue()==null) || 
             (this.issue!=null &&
              this.issue.equals(other.getIssue()))) &&
            ((this.issueDesc==null && other.getIssueDesc()==null) || 
             (this.issueDesc!=null &&
              this.issueDesc.equals(other.getIssueDesc()))) &&
            ((this.length==null && other.getLength()==null) || 
             (this.length!=null &&
              this.length.equals(other.getLength()))) &&
            ((this.memo==null && other.getMemo()==null) || 
             (this.memo!=null &&
              this.memo.equals(other.getMemo()))) &&
            ((this.orderId==null && other.getOrderId()==null) || 
             (this.orderId!=null &&
              this.orderId.equals(other.getOrderId()))) &&
            ((this.orderNo==null && other.getOrderNo()==null) || 
             (this.orderNo!=null &&
              this.orderNo.equals(other.getOrderNo()))) &&
            ((this.originCountryCode==null && other.getOriginCountryCode()==null) || 
             (this.originCountryCode!=null &&
              this.originCountryCode.equals(other.getOriginCountryCode()))) &&
            ((this.otherFee==null && other.getOtherFee()==null) || 
             (this.otherFee!=null &&
              this.otherFee.equals(other.getOtherFee()))) &&
            ((this.pieces==null && other.getPieces()==null) || 
             (this.pieces!=null &&
              this.pieces.equals(other.getPieces()))) &&
            ((this.province==null && other.getProvince()==null) || 
             (this.province!=null &&
              this.province.equals(other.getProvince()))) &&
            ((this.realVolWeight==null && other.getRealVolWeight()==null) || 
             (this.realVolWeight!=null &&
              this.realVolWeight.equals(other.getRealVolWeight()))) &&
            ((this.realWeight==null && other.getRealWeight()==null) || 
             (this.realWeight!=null &&
              this.realWeight.equals(other.getRealWeight()))) &&
            ((this.receiveTime==null && other.getReceiveTime()==null) || 
             (this.receiveTime!=null &&
              this.receiveTime.equals(other.getReceiveTime()))) &&
            ((this.shipTime==null && other.getShipTime()==null) || 
             (this.shipTime!=null &&
              this.shipTime.equals(other.getShipTime()))) &&
            ((this.shipperAddress==null && other.getShipperAddress()==null) || 
             (this.shipperAddress!=null &&
              this.shipperAddress.equals(other.getShipperAddress()))) &&
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
            ((this.shipperTelephone==null && other.getShipperTelephone()==null) || 
             (this.shipperTelephone!=null &&
              this.shipperTelephone.equals(other.getShipperTelephone()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.statusDescription==null && other.getStatusDescription()==null) || 
             (this.statusDescription!=null &&
              this.statusDescription.equals(other.getStatusDescription()))) &&
            ((this.street==null && other.getStreet()==null) || 
             (this.street!=null &&
              this.street.equals(other.getStreet()))) &&
            ((this.taxReturnNo==null && other.getTaxReturnNo()==null) || 
             (this.taxReturnNo!=null &&
              this.taxReturnNo.equals(other.getTaxReturnNo()))) &&
            ((this.totalFee==null && other.getTotalFee()==null) || 
             (this.totalFee!=null &&
              this.totalFee.equals(other.getTotalFee()))) &&
            ((this.trackingNo==null && other.getTrackingNo()==null) || 
             (this.trackingNo!=null &&
              this.trackingNo.equals(other.getTrackingNo()))) &&
            ((this.transportFee==null && other.getTransportFee()==null) || 
             (this.transportFee!=null &&
              this.transportFee.equals(other.getTransportFee()))) &&
            ((this.transportWayCode==null && other.getTransportWayCode()==null) || 
             (this.transportWayCode!=null &&
              this.transportWayCode.equals(other.getTransportWayCode()))) &&
            ((this.transportWayName==null && other.getTransportWayName()==null) || 
             (this.transportWayName!=null &&
              this.transportWayName.equals(other.getTransportWayName()))) &&
            ((this.vweightList==null && other.getVweightList()==null) || 
             (this.vweightList!=null &&
              this.vweightList.equals(other.getVweightList()))) &&
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
        if (getAuditTime() != null) {
            _hashCode += getAuditTime().hashCode();
        }
        if (getBalanceWeight() != null) {
            _hashCode += getBalanceWeight().hashCode();
        }
        if (getCargoCode() != null) {
            _hashCode += getCargoCode().hashCode();
        }
        if (getCity() != null) {
            _hashCode += getCity().hashCode();
        }
        if (getConsigneeCompanyName() != null) {
            _hashCode += getConsigneeCompanyName().hashCode();
        }
        if (getConsigneeCountry() != null) {
            _hashCode += getConsigneeCountry().hashCode();
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
        if (getConsigneeTelephone() != null) {
            _hashCode += getConsigneeTelephone().hashCode();
        }
        if (getCreateTime() != null) {
            _hashCode += getCreateTime().hashCode();
        }
        if (getCurrency() != null) {
            _hashCode += getCurrency().hashCode();
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
        if (getHawbCode() != null) {
            _hashCode += getHawbCode().hashCode();
        }
        if (getHeight() != null) {
            _hashCode += getHeight().hashCode();
        }
        if (getInsured() != null) {
            _hashCode += getInsured().hashCode();
        }
        if (getIssue() != null) {
            _hashCode += getIssue().hashCode();
        }
        if (getIssueDesc() != null) {
            _hashCode += getIssueDesc().hashCode();
        }
        if (getLength() != null) {
            _hashCode += getLength().hashCode();
        }
        if (getMemo() != null) {
            _hashCode += getMemo().hashCode();
        }
        if (getOrderId() != null) {
            _hashCode += getOrderId().hashCode();
        }
        if (getOrderNo() != null) {
            _hashCode += getOrderNo().hashCode();
        }
        if (getOriginCountryCode() != null) {
            _hashCode += getOriginCountryCode().hashCode();
        }
        if (getOtherFee() != null) {
            _hashCode += getOtherFee().hashCode();
        }
        if (getPieces() != null) {
            _hashCode += getPieces().hashCode();
        }
        if (getProvince() != null) {
            _hashCode += getProvince().hashCode();
        }
        if (getRealVolWeight() != null) {
            _hashCode += getRealVolWeight().hashCode();
        }
        if (getRealWeight() != null) {
            _hashCode += getRealWeight().hashCode();
        }
        if (getReceiveTime() != null) {
            _hashCode += getReceiveTime().hashCode();
        }
        if (getShipTime() != null) {
            _hashCode += getShipTime().hashCode();
        }
        if (getShipperAddress() != null) {
            _hashCode += getShipperAddress().hashCode();
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
        if (getShipperTelephone() != null) {
            _hashCode += getShipperTelephone().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getStatusDescription() != null) {
            _hashCode += getStatusDescription().hashCode();
        }
        if (getStreet() != null) {
            _hashCode += getStreet().hashCode();
        }
        if (getTaxReturnNo() != null) {
            _hashCode += getTaxReturnNo().hashCode();
        }
        if (getTotalFee() != null) {
            _hashCode += getTotalFee().hashCode();
        }
        if (getTrackingNo() != null) {
            _hashCode += getTrackingNo().hashCode();
        }
        if (getTransportFee() != null) {
            _hashCode += getTransportFee().hashCode();
        }
        if (getTransportWayCode() != null) {
            _hashCode += getTransportWayCode().hashCode();
        }
        if (getTransportWayName() != null) {
            _hashCode += getTransportWayName().hashCode();
        }
        if (getVweightList() != null) {
            _hashCode += getVweightList().hashCode();
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
        new org.apache.axis.description.TypeDesc(Order.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.hop.service.ws.hlt.com/", "order"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("auditTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "auditTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("balanceWeight");
        elemField.setXmlName(new javax.xml.namespace.QName("", "balanceWeight"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
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
        elemField.setFieldName("city");
        elemField.setXmlName(new javax.xml.namespace.QName("", "city"));
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
        elemField.setFieldName("consigneeCountry");
        elemField.setXmlName(new javax.xml.namespace.QName("", "consigneeCountry"));
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
        elemField.setFieldName("consigneeTelephone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "consigneeTelephone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "createTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currency");
        elemField.setXmlName(new javax.xml.namespace.QName("", "currency"));
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
        elemField.setFieldName("hawbCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "hawbCode"));
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
        elemField.setFieldName("issue");
        elemField.setXmlName(new javax.xml.namespace.QName("", "issue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("issueDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "issueDesc"));
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
        elemField.setFieldName("orderId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orderId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
        elemField.setFieldName("otherFee");
        elemField.setXmlName(new javax.xml.namespace.QName("", "otherFee"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pieces");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pieces"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("province");
        elemField.setXmlName(new javax.xml.namespace.QName("", "province"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("realVolWeight");
        elemField.setXmlName(new javax.xml.namespace.QName("", "realVolWeight"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("realWeight");
        elemField.setXmlName(new javax.xml.namespace.QName("", "realWeight"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receiveTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "receiveTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipTime"));
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
        elemField.setFieldName("shipperTelephone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipperTelephone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "statusDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("street");
        elemField.setXmlName(new javax.xml.namespace.QName("", "street"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxReturnNo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "taxReturnNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalFee");
        elemField.setXmlName(new javax.xml.namespace.QName("", "totalFee"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
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
        elemField.setFieldName("transportFee");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transportFee"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
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
        elemField.setFieldName("transportWayName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transportWayName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vweightList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "vweightList"));
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
