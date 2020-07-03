/**
 * TransportWay.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.baifei.modules.webservice.haihehui;

public class TransportWay  implements java.io.Serializable {
    private String autoFetchTrackingNo;

    private String code;

    private String name;

    private String trackingNoRuleMemo;

    private String trackingNoRuleRegex;

    private String used;

    public TransportWay() {
    }

    public TransportWay(
           String autoFetchTrackingNo,
           String code,
           String name,
           String trackingNoRuleMemo,
           String trackingNoRuleRegex,
           String used) {
           this.autoFetchTrackingNo = autoFetchTrackingNo;
           this.code = code;
           this.name = name;
           this.trackingNoRuleMemo = trackingNoRuleMemo;
           this.trackingNoRuleRegex = trackingNoRuleRegex;
           this.used = used;
    }


    /**
     * Gets the autoFetchTrackingNo value for this TransportWay.
     * 
     * @return autoFetchTrackingNo
     */
    public String getAutoFetchTrackingNo() {
        return autoFetchTrackingNo;
    }


    /**
     * Sets the autoFetchTrackingNo value for this TransportWay.
     * 
     * @param autoFetchTrackingNo
     */
    public void setAutoFetchTrackingNo(String autoFetchTrackingNo) {
        this.autoFetchTrackingNo = autoFetchTrackingNo;
    }


    /**
     * Gets the code value for this TransportWay.
     * 
     * @return code
     */
    public String getCode() {
        return code;
    }


    /**
     * Sets the code value for this TransportWay.
     * 
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }


    /**
     * Gets the name value for this TransportWay.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name value for this TransportWay.
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the trackingNoRuleMemo value for this TransportWay.
     * 
     * @return trackingNoRuleMemo
     */
    public String getTrackingNoRuleMemo() {
        return trackingNoRuleMemo;
    }


    /**
     * Sets the trackingNoRuleMemo value for this TransportWay.
     * 
     * @param trackingNoRuleMemo
     */
    public void setTrackingNoRuleMemo(String trackingNoRuleMemo) {
        this.trackingNoRuleMemo = trackingNoRuleMemo;
    }


    /**
     * Gets the trackingNoRuleRegex value for this TransportWay.
     * 
     * @return trackingNoRuleRegex
     */
    public String getTrackingNoRuleRegex() {
        return trackingNoRuleRegex;
    }


    /**
     * Sets the trackingNoRuleRegex value for this TransportWay.
     * 
     * @param trackingNoRuleRegex
     */
    public void setTrackingNoRuleRegex(String trackingNoRuleRegex) {
        this.trackingNoRuleRegex = trackingNoRuleRegex;
    }


    /**
     * Gets the used value for this TransportWay.
     * 
     * @return used
     */
    public String getUsed() {
        return used;
    }


    /**
     * Sets the used value for this TransportWay.
     * 
     * @param used
     */
    public void setUsed(String used) {
        this.used = used;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof TransportWay)) return false;
        TransportWay other = (TransportWay) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.autoFetchTrackingNo==null && other.getAutoFetchTrackingNo()==null) || 
             (this.autoFetchTrackingNo!=null &&
              this.autoFetchTrackingNo.equals(other.getAutoFetchTrackingNo()))) &&
            ((this.code==null && other.getCode()==null) || 
             (this.code!=null &&
              this.code.equals(other.getCode()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.trackingNoRuleMemo==null && other.getTrackingNoRuleMemo()==null) || 
             (this.trackingNoRuleMemo!=null &&
              this.trackingNoRuleMemo.equals(other.getTrackingNoRuleMemo()))) &&
            ((this.trackingNoRuleRegex==null && other.getTrackingNoRuleRegex()==null) || 
             (this.trackingNoRuleRegex!=null &&
              this.trackingNoRuleRegex.equals(other.getTrackingNoRuleRegex()))) &&
            ((this.used==null && other.getUsed()==null) || 
             (this.used!=null &&
              this.used.equals(other.getUsed())));
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
        if (getAutoFetchTrackingNo() != null) {
            _hashCode += getAutoFetchTrackingNo().hashCode();
        }
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getTrackingNoRuleMemo() != null) {
            _hashCode += getTrackingNoRuleMemo().hashCode();
        }
        if (getTrackingNoRuleRegex() != null) {
            _hashCode += getTrackingNoRuleRegex().hashCode();
        }
        if (getUsed() != null) {
            _hashCode += getUsed().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TransportWay.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.hop.service.ws.hlt.com/", "transportWay"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autoFetchTrackingNo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "autoFetchTrackingNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("", "code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trackingNoRuleMemo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trackingNoRuleMemo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trackingNoRuleRegex");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trackingNoRuleRegex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("used");
        elemField.setXmlName(new javax.xml.namespace.QName("", "used"));
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
