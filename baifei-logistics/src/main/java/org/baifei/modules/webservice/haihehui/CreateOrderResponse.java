/**
 * CreateOrderResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.baifei.modules.webservice.haihehui;

public class CreateOrderResponse  implements java.io.Serializable {
    private HopHopError error;

    private Long id;

    private Boolean success;

    private String trackingNo;

    public CreateOrderResponse() {
    }

    public CreateOrderResponse(
           HopHopError error,
           Long id,
           Boolean success,
           String trackingNo) {
           this.error = error;
           this.id = id;
           this.success = success;
           this.trackingNo = trackingNo;
    }


    /**
     * Gets the error value for this CreateOrderResponse.
     * 
     * @return error
     */
    public HopHopError getError() {
        return error;
    }


    /**
     * Sets the error value for this CreateOrderResponse.
     * 
     * @param error
     */
    public void setError(HopHopError error) {
        this.error = error;
    }


    /**
     * Gets the id value for this CreateOrderResponse.
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }


    /**
     * Sets the id value for this CreateOrderResponse.
     * 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * Gets the success value for this CreateOrderResponse.
     * 
     * @return success
     */
    public Boolean getSuccess() {
        return success;
    }


    /**
     * Sets the success value for this CreateOrderResponse.
     * 
     * @param success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }


    /**
     * Gets the trackingNo value for this CreateOrderResponse.
     * 
     * @return trackingNo
     */
    public String getTrackingNo() {
        return trackingNo;
    }


    /**
     * Sets the trackingNo value for this CreateOrderResponse.
     * 
     * @param trackingNo
     */
    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof CreateOrderResponse)) return false;
        CreateOrderResponse other = (CreateOrderResponse) obj;
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
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.success==null && other.getSuccess()==null) || 
             (this.success!=null &&
              this.success.equals(other.getSuccess()))) &&
            ((this.trackingNo==null && other.getTrackingNo()==null) || 
             (this.trackingNo!=null &&
              this.trackingNo.equals(other.getTrackingNo())));
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getSuccess() != null) {
            _hashCode += getSuccess().hashCode();
        }
        if (getTrackingNo() != null) {
            _hashCode += getTrackingNo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CreateOrderResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.hop.service.ws.hlt.com/", "createOrderResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("error");
        elemField.setXmlName(new javax.xml.namespace.QName("", "error"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.hop.service.ws.hlt.com/", "HopHopError"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
        elemField.setFieldName("trackingNo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trackingNo"));
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
