/**
 * OrderWithInfoLoad.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.baifei.modules.webservice.haihehui;

public class OrderWithInfoLoad  extends Order  implements java.io.Serializable {
    private String disPatchNo;

    private String loadTime;

    private String packageCode;

    private String station;

    public OrderWithInfoLoad() {
    }

    public OrderWithInfoLoad(
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
           Double width,
           String disPatchNo,
           String loadTime,
           String packageCode,
           String station) {
        super(
            auditTime,
            balanceWeight,
            cargoCode,
            city,
            consigneeCompanyName,
            consigneeCountry,
            consigneeMobile,
            consigneeName,
            consigneePostcode,
            consigneeTelephone,
            createTime,
            currency,
            declareItems,
            destinationCountryCode,
            goodsCategory,
            goodsDescription,
            hawbCode,
            height,
            insured,
            issue,
            issueDesc,
            length,
            memo,
            orderId,
            orderNo,
            originCountryCode,
            otherFee,
            pieces,
            province,
            realVolWeight,
            realWeight,
            receiveTime,
            shipTime,
            shipperAddress,
            shipperCompanyName,
            shipperMobile,
            shipperName,
            shipperPostcode,
            shipperTelephone,
            status,
            statusDescription,
            street,
            taxReturnNo,
            totalFee,
            trackingNo,
            transportFee,
            transportWayCode,
            transportWayName,
            vweightList,
            weight,
            width);
        this.disPatchNo = disPatchNo;
        this.loadTime = loadTime;
        this.packageCode = packageCode;
        this.station = station;
    }


    /**
     * Gets the disPatchNo value for this OrderWithInfoLoad.
     * 
     * @return disPatchNo
     */
    public String getDisPatchNo() {
        return disPatchNo;
    }


    /**
     * Sets the disPatchNo value for this OrderWithInfoLoad.
     * 
     * @param disPatchNo
     */
    public void setDisPatchNo(String disPatchNo) {
        this.disPatchNo = disPatchNo;
    }


    /**
     * Gets the loadTime value for this OrderWithInfoLoad.
     * 
     * @return loadTime
     */
    public String getLoadTime() {
        return loadTime;
    }


    /**
     * Sets the loadTime value for this OrderWithInfoLoad.
     * 
     * @param loadTime
     */
    public void setLoadTime(String loadTime) {
        this.loadTime = loadTime;
    }


    /**
     * Gets the packageCode value for this OrderWithInfoLoad.
     * 
     * @return packageCode
     */
    public String getPackageCode() {
        return packageCode;
    }


    /**
     * Sets the packageCode value for this OrderWithInfoLoad.
     * 
     * @param packageCode
     */
    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }


    /**
     * Gets the station value for this OrderWithInfoLoad.
     * 
     * @return station
     */
    public String getStation() {
        return station;
    }


    /**
     * Sets the station value for this OrderWithInfoLoad.
     * 
     * @param station
     */
    public void setStation(String station) {
        this.station = station;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof OrderWithInfoLoad)) return false;
        OrderWithInfoLoad other = (OrderWithInfoLoad) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.disPatchNo==null && other.getDisPatchNo()==null) || 
             (this.disPatchNo!=null &&
              this.disPatchNo.equals(other.getDisPatchNo()))) &&
            ((this.loadTime==null && other.getLoadTime()==null) || 
             (this.loadTime!=null &&
              this.loadTime.equals(other.getLoadTime()))) &&
            ((this.packageCode==null && other.getPackageCode()==null) || 
             (this.packageCode!=null &&
              this.packageCode.equals(other.getPackageCode()))) &&
            ((this.station==null && other.getStation()==null) || 
             (this.station!=null &&
              this.station.equals(other.getStation())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getDisPatchNo() != null) {
            _hashCode += getDisPatchNo().hashCode();
        }
        if (getLoadTime() != null) {
            _hashCode += getLoadTime().hashCode();
        }
        if (getPackageCode() != null) {
            _hashCode += getPackageCode().hashCode();
        }
        if (getStation() != null) {
            _hashCode += getStation().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OrderWithInfoLoad.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.hop.service.ws.hlt.com/", "orderWithInfoLoad"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("disPatchNo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "disPatchNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loadTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loadTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("packageCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "packageCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("station");
        elemField.setXmlName(new javax.xml.namespace.QName("", "station"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
