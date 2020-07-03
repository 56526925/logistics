/**
 * LookupOrderInfoLoadResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.baifei.modules.webservice.haihehui;

public class LookupOrderInfoLoadResponse  implements java.io.Serializable {
    private HopHopError error;

    private OrderWithInfoLoad order;

    private Boolean success;

    public LookupOrderInfoLoadResponse() {
    }

    public LookupOrderInfoLoadResponse(
           HopHopError error,
           OrderWithInfoLoad order,
           Boolean success) {
           this.error = error;
           this.order = order;
           this.success = success;
    }


    /**
     * Gets the error value for this LookupOrderInfoLoadResponse.
     * 
     * @return error
     */
    public HopHopError getError() {
        return error;
    }


    /**
     * Sets the error value for this LookupOrderInfoLoadResponse.
     * 
     * @param error
     */
    public void setError(HopHopError error) {
        this.error = error;
    }


    /**
     * Gets the order value for this LookupOrderInfoLoadResponse.
     * 
     * @return order
     */
    public OrderWithInfoLoad getOrder() {
        return order;
    }


    /**
     * Sets the order value for this LookupOrderInfoLoadResponse.
     * 
     * @param order
     */
    public void setOrder(OrderWithInfoLoad order) {
        this.order = order;
    }


    /**
     * Gets the success value for this LookupOrderInfoLoadResponse.
     * 
     * @return success
     */
    public Boolean getSuccess() {
        return success;
    }


    /**
     * Sets the success value for this LookupOrderInfoLoadResponse.
     * 
     * @param success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof LookupOrderInfoLoadResponse)) return false;
        LookupOrderInfoLoadResponse other = (LookupOrderInfoLoadResponse) obj;
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
            ((this.order==null && other.getOrder()==null) || 
             (this.order!=null &&
              this.order.equals(other.getOrder()))) &&
            ((this.success==null && other.getSuccess()==null) || 
             (this.success!=null &&
              this.success.equals(other.getSuccess())));
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
        if (getOrder() != null) {
            _hashCode += getOrder().hashCode();
        }
        if (getSuccess() != null) {
            _hashCode += getSuccess().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LookupOrderInfoLoadResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.hop.service.ws.hlt.com/", "lookupOrderInfoLoadResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("error");
        elemField.setXmlName(new javax.xml.namespace.QName("", "error"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.hop.service.ws.hlt.com/", "HopHopError"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("order");
        elemField.setXmlName(new javax.xml.namespace.QName("", "order"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.hop.service.ws.hlt.com/", "orderWithInfoLoad"));
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
