/**
 * Trace.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.baifei.modules.webservice.haihehui;

public class Trace  implements java.io.Serializable {
    private String enpathInfo;

    private String pathAddr;

    private String pathInfo;

    private java.util.Calendar pathTime;

    private String rcountry;

    private Integer status;

    private String tno;

    private TracePath[] rPaths;

    private TracePath[] sPaths;

    public Trace() {
    }

    public Trace(
           String enpathInfo,
           String pathAddr,
           String pathInfo,
           java.util.Calendar pathTime,
           String rcountry,
           Integer status,
           String tno,
           TracePath[] rPaths,
           TracePath[] sPaths) {
           this.enpathInfo = enpathInfo;
           this.pathAddr = pathAddr;
           this.pathInfo = pathInfo;
           this.pathTime = pathTime;
           this.rcountry = rcountry;
           this.status = status;
           this.tno = tno;
           this.rPaths = rPaths;
           this.sPaths = sPaths;
    }


    /**
     * Gets the enpathInfo value for this Trace.
     * 
     * @return enpathInfo
     */
    public String getEnpathInfo() {
        return enpathInfo;
    }


    /**
     * Sets the enpathInfo value for this Trace.
     * 
     * @param enpathInfo
     */
    public void setEnpathInfo(String enpathInfo) {
        this.enpathInfo = enpathInfo;
    }


    /**
     * Gets the pathAddr value for this Trace.
     * 
     * @return pathAddr
     */
    public String getPathAddr() {
        return pathAddr;
    }


    /**
     * Sets the pathAddr value for this Trace.
     * 
     * @param pathAddr
     */
    public void setPathAddr(String pathAddr) {
        this.pathAddr = pathAddr;
    }


    /**
     * Gets the pathInfo value for this Trace.
     * 
     * @return pathInfo
     */
    public String getPathInfo() {
        return pathInfo;
    }


    /**
     * Sets the pathInfo value for this Trace.
     * 
     * @param pathInfo
     */
    public void setPathInfo(String pathInfo) {
        this.pathInfo = pathInfo;
    }


    /**
     * Gets the pathTime value for this Trace.
     * 
     * @return pathTime
     */
    public java.util.Calendar getPathTime() {
        return pathTime;
    }


    /**
     * Sets the pathTime value for this Trace.
     * 
     * @param pathTime
     */
    public void setPathTime(java.util.Calendar pathTime) {
        this.pathTime = pathTime;
    }


    /**
     * Gets the rcountry value for this Trace.
     * 
     * @return rcountry
     */
    public String getRcountry() {
        return rcountry;
    }


    /**
     * Sets the rcountry value for this Trace.
     * 
     * @param rcountry
     */
    public void setRcountry(String rcountry) {
        this.rcountry = rcountry;
    }


    /**
     * Gets the status value for this Trace.
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }


    /**
     * Sets the status value for this Trace.
     * 
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }


    /**
     * Gets the tno value for this Trace.
     * 
     * @return tno
     */
    public String getTno() {
        return tno;
    }


    /**
     * Sets the tno value for this Trace.
     * 
     * @param tno
     */
    public void setTno(String tno) {
        this.tno = tno;
    }


    /**
     * Gets the rPaths value for this Trace.
     * 
     * @return rPaths
     */
    public TracePath[] getRPaths() {
        return rPaths;
    }


    /**
     * Sets the rPaths value for this Trace.
     * 
     * @param rPaths
     */
    public void setRPaths(TracePath[] rPaths) {
        this.rPaths = rPaths;
    }

    public TracePath getRPaths(int i) {
        return this.rPaths[i];
    }

    public void setRPaths(int i, TracePath _value) {
        this.rPaths[i] = _value;
    }


    /**
     * Gets the sPaths value for this Trace.
     * 
     * @return sPaths
     */
    public TracePath[] getSPaths() {
        return sPaths;
    }


    /**
     * Sets the sPaths value for this Trace.
     * 
     * @param sPaths
     */
    public void setSPaths(TracePath[] sPaths) {
        this.sPaths = sPaths;
    }

    public TracePath getSPaths(int i) {
        return this.sPaths[i];
    }

    public void setSPaths(int i, TracePath _value) {
        this.sPaths[i] = _value;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Trace)) return false;
        Trace other = (Trace) obj;
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
            ((this.rcountry==null && other.getRcountry()==null) || 
             (this.rcountry!=null &&
              this.rcountry.equals(other.getRcountry()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.tno==null && other.getTno()==null) || 
             (this.tno!=null &&
              this.tno.equals(other.getTno()))) &&
            ((this.rPaths==null && other.getRPaths()==null) || 
             (this.rPaths!=null &&
              java.util.Arrays.equals(this.rPaths, other.getRPaths()))) &&
            ((this.sPaths==null && other.getSPaths()==null) || 
             (this.sPaths!=null &&
              java.util.Arrays.equals(this.sPaths, other.getSPaths())));
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
        if (getRcountry() != null) {
            _hashCode += getRcountry().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getTno() != null) {
            _hashCode += getTno().hashCode();
        }
        if (getRPaths() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRPaths());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getRPaths(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSPaths() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSPaths());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getSPaths(), i);
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
        new org.apache.axis.description.TypeDesc(Trace.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.hop.service.ws.hlt.com/", "trace"));
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
        elemField.setFieldName("rcountry");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rcountry"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RPaths");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rPaths"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.hop.service.ws.hlt.com/", "tracePath"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SPaths");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sPaths"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.hop.service.ws.hlt.com/", "tracePath"));
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
