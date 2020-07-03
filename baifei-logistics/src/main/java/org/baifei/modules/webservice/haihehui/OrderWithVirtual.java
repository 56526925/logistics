/**
 * OrderWithVirtual.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.baifei.modules.webservice.haihehui;

public class OrderWithVirtual  extends Order  implements java.io.Serializable {
    private String virtualWno;

    public OrderWithVirtual() {
    }

    public OrderWithVirtual(
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
           String virtualWno) {
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
        this.virtualWno = virtualWno;
    }


    /**
     * Gets the virtualWno value for this OrderWithVirtual.
     * 
     * @return virtualWno
     */
    public String getVirtualWno() {
        return virtualWno;
    }


    /**
     * Sets the virtualWno value for this OrderWithVirtual.
     * 
     * @param virtualWno
     */
    public void setVirtualWno(String virtualWno) {
        this.virtualWno = virtualWno;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof OrderWithVirtual)) return false;
        OrderWithVirtual other = (OrderWithVirtual) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.virtualWno==null && other.getVirtualWno()==null) || 
             (this.virtualWno!=null &&
              this.virtualWno.equals(other.getVirtualWno())));
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
        if (getVirtualWno() != null) {
            _hashCode += getVirtualWno().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OrderWithVirtual.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.hop.service.ws.hlt.com/", "orderWithVirtual"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("virtualWno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "virtualWno"));
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
