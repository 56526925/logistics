/**
 * OrderInfoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.baifei.modules.webservice.haihehui;

public class OrderInfoResponse  implements java.io.Serializable {
    private HopHopError error;

    private Integer lockedCount;

    private Order[] orderList;

    private Boolean success;

    private Integer successCount;

    public OrderInfoResponse() {
    }

    public OrderInfoResponse(
           HopHopError error,
           Integer lockedCount,
           Order[] orderList,
           Boolean success,
           Integer successCount) {
           this.error = error;
           this.lockedCount = lockedCount;
           this.orderList = orderList;
           this.success = success;
           this.successCount = successCount;
    }


    /**
     * Gets the error value for this OrderInfoResponse.
     * 
     * @return error
     */
    public HopHopError getError() {
        return error;
    }


    /**
     * Sets the error value for this OrderInfoResponse.
     * 
     * @param error
     */
    public void setError(HopHopError error) {
        this.error = error;
    }


    /**
     * Gets the lockedCount value for this OrderInfoResponse.
     * 
     * @return lockedCount
     */
    public Integer getLockedCount() {
        return lockedCount;
    }


    /**
     * Sets the lockedCount value for this OrderInfoResponse.
     * 
     * @param lockedCount
     */
    public void setLockedCount(Integer lockedCount) {
        this.lockedCount = lockedCount;
    }


    /**
     * Gets the orderList value for this OrderInfoResponse.
     * 
     * @return orderList
     */
    public Order[] getOrderList() {
        return orderList;
    }


    /**
     * Sets the orderList value for this OrderInfoResponse.
     * 
     * @param orderList
     */
    public void setOrderList(Order[] orderList) {
        this.orderList = orderList;
    }

    public Order getOrderList(int i) {
        return this.orderList[i];
    }

    public void setOrderList(int i, Order _value) {
        this.orderList[i] = _value;
    }


    /**
     * Gets the success value for this OrderInfoResponse.
     * 
     * @return success
     */
    public Boolean getSuccess() {
        return success;
    }


    /**
     * Sets the success value for this OrderInfoResponse.
     * 
     * @param success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }


    /**
     * Gets the successCount value for this OrderInfoResponse.
     * 
     * @return successCount
     */
    public Integer getSuccessCount() {
        return successCount;
    }


    /**
     * Sets the successCount value for this OrderInfoResponse.
     * 
     * @param successCount
     */
    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof OrderInfoResponse)) return false;
        OrderInfoResponse other = (OrderInfoResponse) obj;
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
            ((this.lockedCount==null && other.getLockedCount()==null) || 
             (this.lockedCount!=null &&
              this.lockedCount.equals(other.getLockedCount()))) &&
            ((this.orderList==null && other.getOrderList()==null) || 
             (this.orderList!=null &&
              java.util.Arrays.equals(this.orderList, other.getOrderList()))) &&
            ((this.success==null && other.getSuccess()==null) || 
             (this.success!=null &&
              this.success.equals(other.getSuccess()))) &&
            ((this.successCount==null && other.getSuccessCount()==null) || 
             (this.successCount!=null &&
              this.successCount.equals(other.getSuccessCount())));
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
        if (getLockedCount() != null) {
            _hashCode += getLockedCount().hashCode();
        }
        if (getOrderList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOrderList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getOrderList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSuccess() != null) {
            _hashCode += getSuccess().hashCode();
        }
        if (getSuccessCount() != null) {
            _hashCode += getSuccessCount().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OrderInfoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.hop.service.ws.hlt.com/", "orderInfoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("error");
        elemField.setXmlName(new javax.xml.namespace.QName("", "error"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.hop.service.ws.hlt.com/", "HopHopError"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lockedCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lockedCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orderList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.hop.service.ws.hlt.com/", "order"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("success");
        elemField.setXmlName(new javax.xml.namespace.QName("", "success"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("successCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "successCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
