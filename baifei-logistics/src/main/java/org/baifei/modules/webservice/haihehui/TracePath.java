/**
 * TracePath.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.baifei.modules.webservice.haihehui;

public class TracePath  implements java.io.Serializable {
    private String enpathInfo;

    private String pathAddr;

    private String pathInfo;

    private java.util.Calendar pathTime;

    private Integer pathType;

    public TracePath() {
    }

    public TracePath(
           String enpathInfo,
           String pathAddr,
           String pathInfo,
           java.util.Calendar pathTime,
           Integer pathType) {
           this.enpathInfo = enpathInfo;
           this.pathAddr = pathAddr;
           this.pathInfo = pathInfo;
           this.pathTime = pathTime;
           this.pathType = pathType;
    }


    /**
     * Gets the enpathInfo value for this TracePath.
     * 
     * @return enpathInfo
     */
    public String getEnpathInfo() {
        return enpathInfo;
    }


    /**
     * Sets the enpathInfo value for this TracePath.
     * 
     * @param enpathInfo
     */
    public void setEnpathInfo(String enpathInfo) {
        this.enpathInfo = enpathInfo;
    }


    /**
     * Gets the pathAddr value for this TracePath.
     * 
     * @return pathAddr
     */
    public String getPathAddr() {
        return pathAddr;
    }


    /**
     * Sets the pathAddr value for this TracePath.
     * 
     * @param pathAddr
     */
    public void setPathAddr(String pathAddr) {
        this.pathAddr = pathAddr;
    }


    /**
     * Gets the pathInfo value for this TracePath.
     * 
     * @return pathInfo
     */
    public String getPathInfo() {
        return pathInfo;
    }


    /**
     * Sets the pathInfo value for this TracePath.
     * 
     * @param pathInfo
     */
    public void setPathInfo(String pathInfo) {
        this.pathInfo = pathInfo;
    }


    /**
     * Gets the pathTime value for this TracePath.
     * 
     * @return pathTime
     */
    public java.util.Calendar getPathTime() {
        return pathTime;
    }


    /**
     * Sets the pathTime value for this TracePath.
     * 
     * @param pathTime
     */
    public void setPathTime(java.util.Calendar pathTime) {
        this.pathTime = pathTime;
    }


    /**
     * Gets the pathType value for this TracePath.
     * 
     * @return pathType
     */
    public Integer getPathType() {
        return pathType;
    }


    /**
     * Sets the pathType value for this TracePath.
     * 
     * @param pathType
     */
    public void setPathType(Integer pathType) {
        this.pathType = pathType;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof TracePath)) return false;
        TracePath other = (TracePath) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.enpathInfo==null && other.getEnpathInfo()==null) || 
             (this.enpathInfo!=null &&
              this.enpathInfo.equals(other.getEnpathInfo()))) &&
            ((this.pathAddr==null && other.getPathAddr()==null) || 
             (this.pathAddr!=null &&
              this.pathAddr.equals(other.getPathAddr()))) &&
            ((this.pathInfo==null && other.getPathInfo()==null) || 
             (this.pathInfo!=null &&
              this.pathInfo.equals(other.getPathInfo()))) &&
            ((this.pathTime==null && other.getPathTime()==null) || 
             (this.pathTime!=null &&
              this.pathTime.equals(other.getPathTime()))) &&
            ((this.pathType==null && other.getPathType()==null) || 
             (this.pathType!=null &&
              this.pathType.equals(other.getPathType())));
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
        if (getEnpathInfo() != null) {
            _hashCode += getEnpathInfo().hashCode();
        }
        if (getPathAddr() != null) {
            _hashCode += getPathAddr().hashCode();
        }
        if (getPathInfo() != null) {
            _hashCode += getPathInfo().hashCode();
        }
        if (getPathTime() != null) {
            _hashCode += getPathTime().hashCode();
        }
        if (getPathType() != null) {
            _hashCode += getPathType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TracePath.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.hop.service.ws.hlt.com/", "tracePath"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enpathInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "enpathInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathAddr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pathAddr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pathInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pathTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pathType"));
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
