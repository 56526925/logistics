/**
 * CalculateChargeRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.baifei.modules.webservice.haihehui;

public class CalculateChargeRequest  implements java.io.Serializable {
    private String cargoCode;

    private String countryCode;

    private Double height;

    private Double length;

    private String postcode;

    private String[] transportWayCode;

    private Double weight;

    private Double width;

    public CalculateChargeRequest() {
    }

    public CalculateChargeRequest(
           String cargoCode,
           String countryCode,
           Double height,
           Double length,
           String postcode,
           String[] transportWayCode,
           Double weight,
           Double width) {
           this.cargoCode = cargoCode;
           this.countryCode = countryCode;
           this.height = height;
           this.length = length;
           this.postcode = postcode;
           this.transportWayCode = transportWayCode;
           this.weight = weight;
           this.width = width;
    }


    /**
     * Gets the cargoCode value for this CalculateChargeRequest.
     * 
     * @return cargoCode
     */
    public String getCargoCode() {
        return cargoCode;
    }


    /**
     * Sets the cargoCode value for this CalculateChargeRequest.
     * 
     * @param cargoCode
     */
    public void setCargoCode(String cargoCode) {
        this.cargoCode = cargoCode;
    }


    /**
     * Gets the countryCode value for this CalculateChargeRequest.
     * 
     * @return countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }


    /**
     * Sets the countryCode value for this CalculateChargeRequest.
     * 
     * @param countryCode
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }


    /**
     * Gets the height value for this CalculateChargeRequest.
     * 
     * @return height
     */
    public Double getHeight() {
        return height;
    }


    /**
     * Sets the height value for this CalculateChargeRequest.
     * 
     * @param height
     */
    public void setHeight(Double height) {
        this.height = height;
    }


    /**
     * Gets the length value for this CalculateChargeRequest.
     * 
     * @return length
     */
    public Double getLength() {
        return length;
    }


    /**
     * Sets the length value for this CalculateChargeRequest.
     * 
     * @param length
     */
    public void setLength(Double length) {
        this.length = length;
    }


    /**
     * Gets the postcode value for this CalculateChargeRequest.
     * 
     * @return postcode
     */
    public String getPostcode() {
        return postcode;
    }


    /**
     * Sets the postcode value for this CalculateChargeRequest.
     * 
     * @param postcode
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }


    /**
     * Gets the transportWayCode value for this CalculateChargeRequest.
     * 
     * @return transportWayCode
     */
    public String[] getTransportWayCode() {
        return transportWayCode;
    }


    /**
     * Sets the transportWayCode value for this CalculateChargeRequest.
     * 
     * @param transportWayCode
     */
    public void setTransportWayCode(String[] transportWayCode) {
        this.transportWayCode = transportWayCode;
    }

    public String getTransportWayCode(int i) {
        return this.transportWayCode[i];
    }

    public void setTransportWayCode(int i, String _value) {
        this.transportWayCode[i] = _value;
    }


    /**
     * Gets the weight value for this CalculateChargeRequest.
     * 
     * @return weight
     */
    public Double getWeight() {
        return weight;
    }


    /**
     * Sets the weight value for this CalculateChargeRequest.
     * 
     * @param weight
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }


    /**
     * Gets the width value for this CalculateChargeRequest.
     * 
     * @return width
     */
    public Double getWidth() {
        return width;
    }


    /**
     * Sets the width value for this CalculateChargeRequest.
     * 
     * @param width
     */
    public void setWidth(Double width) {
        this.width = width;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof CalculateChargeRequest)) return false;
        CalculateChargeRequest other = (CalculateChargeRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cargoCode==null && other.getCargoCode()==null) || 
             (this.cargoCode!=null &&
              this.cargoCode.equals(other.getCargoCode()))) &&
            ((this.countryCode==null && other.getCountryCode()==null) || 
             (this.countryCode!=null &&
              this.countryCode.equals(other.getCountryCode()))) &&
            ((this.height==null && other.getHeight()==null) || 
             (this.height!=null &&
              this.height.equals(other.getHeight()))) &&
            ((this.length==null && other.getLength()==null) || 
             (this.length!=null &&
              this.length.equals(other.getLength()))) &&
            ((this.postcode==null && other.getPostcode()==null) || 
             (this.postcode!=null &&
              this.postcode.equals(other.getPostcode()))) &&
            ((this.transportWayCode==null && other.getTransportWayCode()==null) || 
             (this.transportWayCode!=null &&
              java.util.Arrays.equals(this.transportWayCode, other.getTransportWayCode()))) &&
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
        if (getCargoCode() != null) {
            _hashCode += getCargoCode().hashCode();
        }
        if (getCountryCode() != null) {
            _hashCode += getCountryCode().hashCode();
        }
        if (getHeight() != null) {
            _hashCode += getHeight().hashCode();
        }
        if (getLength() != null) {
            _hashCode += getLength().hashCode();
        }
        if (getPostcode() != null) {
            _hashCode += getPostcode().hashCode();
        }
        if (getTransportWayCode() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTransportWayCode());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getTransportWayCode(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
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
        new org.apache.axis.description.TypeDesc(CalculateChargeRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.hop.service.ws.hlt.com/", "calculateChargeRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cargoCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cargoCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("countryCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "countryCode"));
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
        elemField.setFieldName("length");
        elemField.setXmlName(new javax.xml.namespace.QName("", "length"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postcode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "postcode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transportWayCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transportWayCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
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
