/**
 * CalculateChargeResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.baifei.modules.webservice.haihehui;

public class CalculateChargeResponse  implements java.io.Serializable {
    private HopHopError error;

    private Boolean success;

    private TransportFee[] transportFee;

    public CalculateChargeResponse() {
    }

    public CalculateChargeResponse(
           HopHopError error,
           Boolean success,
           TransportFee[] transportFee) {
           this.error = error;
           this.success = success;
           this.transportFee = transportFee;
    }


    /**
     * Gets the error value for this CalculateChargeResponse.
     * 
     * @return error
     */
    public HopHopError getError() {
        return error;
    }


    /**
     * Sets the error value for this CalculateChargeResponse.
     * 
     * @param error
     */
    public void setError(HopHopError error) {
        this.error = error;
    }


    /**
     * Gets the success value for this CalculateChargeResponse.
     * 
     * @return success
     */
    public Boolean getSuccess() {
        return success;
    }


    /**
     * Sets the success value for this CalculateChargeResponse.
     * 
     * @param success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }


    /**
     * Gets the transportFee value for this CalculateChargeResponse.
     * 
     * @return transportFee
     */
    public TransportFee[] getTransportFee() {
        return transportFee;
    }


    /**
     * Sets the transportFee value for this CalculateChargeResponse.
     * 
     * @param transportFee
     */
    public void setTransportFee(TransportFee[] transportFee) {
        this.transportFee = transportFee;
    }

    public TransportFee getTransportFee(int i) {
        return this.transportFee[i];
    }

    public void setTransportFee(int i, TransportFee _value) {
        this.transportFee[i] = _value;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof CalculateChargeResponse)) return false;
        CalculateChargeResponse other = (CalculateChargeResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.error==null && other.getError()==null) || 
             (this.error!=null &&
              this.error.equals(other.getError()))) &&
            ((this.success==null && other.getSuccess()==null) || 
             (this.success!=null &&
              this.success.equals(other.getSuccess()))) &&
            ((this.transportFee==null && other.getTransportFee()==null) || 
             (this.transportFee!=null &&
              java.util.Arrays.equals(this.transportFee, other.getTransportFee())));
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
        if (getError() != null) {
            _hashCode += getError().hashCode();
        }
        if (getSuccess() != null) {
            _hashCode += getSuccess().hashCode();
        }
        if (getTransportFee() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTransportFee());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getTransportFee(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CalculateChargeResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.hop.service.ws.hlt.com/", "calculateChargeResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("error");
        elemField.setXmlName(new javax.xml.namespace.QName("", "error"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.hop.service.ws.hlt.com/", "HopHopError"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("success");
        elemField.setXmlName(new javax.xml.namespace.QName("", "success"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transportFee");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transportFee"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.hop.service.ws.hlt.com/", "transportFee"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
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
