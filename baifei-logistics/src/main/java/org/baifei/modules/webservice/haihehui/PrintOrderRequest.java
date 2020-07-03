/**
 * PrintOrderRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.baifei.modules.webservice.haihehui;

public class PrintOrderRequest  implements java.io.Serializable {
    private String downloadPdf;

    private String oid;

    private String orderNo;

    private String pageSizeCode;

    private String printSelect;

    private String showCnoBarcode;

    private String showRecycleTags;

    private String trackingNo;

    public PrintOrderRequest() {
    }

    public PrintOrderRequest(
           String downloadPdf,
           String oid,
           String orderNo,
           String pageSizeCode,
           String printSelect,
           String showCnoBarcode,
           String showRecycleTags,
           String trackingNo) {
           this.downloadPdf = downloadPdf;
           this.oid = oid;
           this.orderNo = orderNo;
           this.pageSizeCode = pageSizeCode;
           this.printSelect = printSelect;
           this.showCnoBarcode = showCnoBarcode;
           this.showRecycleTags = showRecycleTags;
           this.trackingNo = trackingNo;
    }


    /**
     * Gets the downloadPdf value for this PrintOrderRequest.
     * 
     * @return downloadPdf
     */
    public String getDownloadPdf() {
        return downloadPdf;
    }


    /**
     * Sets the downloadPdf value for this PrintOrderRequest.
     * 
     * @param downloadPdf
     */
    public void setDownloadPdf(String downloadPdf) {
        this.downloadPdf = downloadPdf;
    }


    /**
     * Gets the oid value for this PrintOrderRequest.
     * 
     * @return oid
     */
    public String getOid() {
        return oid;
    }


    /**
     * Sets the oid value for this PrintOrderRequest.
     * 
     * @param oid
     */
    public void setOid(String oid) {
        this.oid = oid;
    }


    /**
     * Gets the orderNo value for this PrintOrderRequest.
     * 
     * @return orderNo
     */
    public String getOrderNo() {
        return orderNo;
    }


    /**
     * Sets the orderNo value for this PrintOrderRequest.
     * 
     * @param orderNo
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }


    /**
     * Gets the pageSizeCode value for this PrintOrderRequest.
     * 
     * @return pageSizeCode
     */
    public String getPageSizeCode() {
        return pageSizeCode;
    }


    /**
     * Sets the pageSizeCode value for this PrintOrderRequest.
     * 
     * @param pageSizeCode
     */
    public void setPageSizeCode(String pageSizeCode) {
        this.pageSizeCode = pageSizeCode;
    }


    /**
     * Gets the printSelect value for this PrintOrderRequest.
     * 
     * @return printSelect
     */
    public String getPrintSelect() {
        return printSelect;
    }


    /**
     * Sets the printSelect value for this PrintOrderRequest.
     * 
     * @param printSelect
     */
    public void setPrintSelect(String printSelect) {
        this.printSelect = printSelect;
    }


    /**
     * Gets the showCnoBarcode value for this PrintOrderRequest.
     * 
     * @return showCnoBarcode
     */
    public String getShowCnoBarcode() {
        return showCnoBarcode;
    }


    /**
     * Sets the showCnoBarcode value for this PrintOrderRequest.
     * 
     * @param showCnoBarcode
     */
    public void setShowCnoBarcode(String showCnoBarcode) {
        this.showCnoBarcode = showCnoBarcode;
    }


    /**
     * Gets the showRecycleTags value for this PrintOrderRequest.
     * 
     * @return showRecycleTags
     */
    public String getShowRecycleTags() {
        return showRecycleTags;
    }


    /**
     * Sets the showRecycleTags value for this PrintOrderRequest.
     * 
     * @param showRecycleTags
     */
    public void setShowRecycleTags(String showRecycleTags) {
        this.showRecycleTags = showRecycleTags;
    }


    /**
     * Gets the trackingNo value for this PrintOrderRequest.
     * 
     * @return trackingNo
     */
    public String getTrackingNo() {
        return trackingNo;
    }


    /**
     * Sets the trackingNo value for this PrintOrderRequest.
     * 
     * @param trackingNo
     */
    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof PrintOrderRequest)) return false;
        PrintOrderRequest other = (PrintOrderRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.downloadPdf==null && other.getDownloadPdf()==null) || 
             (this.downloadPdf!=null &&
              this.downloadPdf.equals(other.getDownloadPdf()))) &&
            ((this.oid==null && other.getOid()==null) || 
             (this.oid!=null &&
              this.oid.equals(other.getOid()))) &&
            ((this.orderNo==null && other.getOrderNo()==null) || 
             (this.orderNo!=null &&
              this.orderNo.equals(other.getOrderNo()))) &&
            ((this.pageSizeCode==null && other.getPageSizeCode()==null) || 
             (this.pageSizeCode!=null &&
              this.pageSizeCode.equals(other.getPageSizeCode()))) &&
            ((this.printSelect==null && other.getPrintSelect()==null) || 
             (this.printSelect!=null &&
              this.printSelect.equals(other.getPrintSelect()))) &&
            ((this.showCnoBarcode==null && other.getShowCnoBarcode()==null) || 
             (this.showCnoBarcode!=null &&
              this.showCnoBarcode.equals(other.getShowCnoBarcode()))) &&
            ((this.showRecycleTags==null && other.getShowRecycleTags()==null) || 
             (this.showRecycleTags!=null &&
              this.showRecycleTags.equals(other.getShowRecycleTags()))) &&
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
        if (getDownloadPdf() != null) {
            _hashCode += getDownloadPdf().hashCode();
        }
        if (getOid() != null) {
            _hashCode += getOid().hashCode();
        }
        if (getOrderNo() != null) {
            _hashCode += getOrderNo().hashCode();
        }
        if (getPageSizeCode() != null) {
            _hashCode += getPageSizeCode().hashCode();
        }
        if (getPrintSelect() != null) {
            _hashCode += getPrintSelect().hashCode();
        }
        if (getShowCnoBarcode() != null) {
            _hashCode += getShowCnoBarcode().hashCode();
        }
        if (getShowRecycleTags() != null) {
            _hashCode += getShowRecycleTags().hashCode();
        }
        if (getTrackingNo() != null) {
            _hashCode += getTrackingNo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PrintOrderRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.hop.service.ws.hlt.com/", "printOrderRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("downloadPdf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "downloadPdf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("oid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "oid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderNo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orderNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pageSizeCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pageSizeCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("printSelect");
        elemField.setXmlName(new javax.xml.namespace.QName("", "printSelect"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("showCnoBarcode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "showCnoBarcode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("showRecycleTags");
        elemField.setXmlName(new javax.xml.namespace.QName("", "showRecycleTags"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
