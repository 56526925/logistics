/**
 * DeclareItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.baifei.modules.webservice.haihehui;

public class DeclareItem  implements java.io.Serializable {
    private String cnName;

    private String customsNo;

    private String name;

    private Double netWeight;

    private Long pieces;

    private String productMemo;

    private Double unitPrice;

    public DeclareItem() {
    }

    public DeclareItem(
           String cnName,
           String customsNo,
           String name,
           Double netWeight,
           Long pieces,
           String productMemo,
           Double unitPrice) {
           this.cnName = cnName;
           this.customsNo = customsNo;
           this.name = name;
           this.netWeight = netWeight;
           this.pieces = pieces;
           this.productMemo = productMemo;
           this.unitPrice = unitPrice;
    }


    /**
     * Gets the cnName value for this DeclareItem.
     * 
     * @return cnName
     */
    public String getCnName() {
        return cnName;
    }


    /**
     * Sets the cnName value for this DeclareItem.
     * 
     * @param cnName
     */
    public void setCnName(String cnName) {
        this.cnName = cnName;
    }


    /**
     * Gets the customsNo value for this DeclareItem.
     * 
     * @return customsNo
     */
    public String getCustomsNo() {
        return customsNo;
    }


    /**
     * Sets the customsNo value for this DeclareItem.
     * 
     * @param customsNo
     */
    public void setCustomsNo(String customsNo) {
        this.customsNo = customsNo;
    }


    /**
     * Gets the name value for this DeclareItem.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name value for this DeclareItem.
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the netWeight value for this DeclareItem.
     * 
     * @return netWeight
     */
    public Double getNetWeight() {
        return netWeight;
    }


    /**
     * Sets the netWeight value for this DeclareItem.
     * 
     * @param netWeight
     */
    public void setNetWeight(Double netWeight) {
        this.netWeight = netWeight;
    }


    /**
     * Gets the pieces value for this DeclareItem.
     * 
     * @return pieces
     */
    public Long getPieces() {
        return pieces;
    }


    /**
     * Sets the pieces value for this DeclareItem.
     * 
     * @param pieces
     */
    public void setPieces(Long pieces) {
        this.pieces = pieces;
    }


    /**
     * Gets the productMemo value for this DeclareItem.
     * 
     * @return productMemo
     */
    public String getProductMemo() {
        return productMemo;
    }


    /**
     * Sets the productMemo value for this DeclareItem.
     * 
     * @param productMemo
     */
    public void setProductMemo(String productMemo) {
        this.productMemo = productMemo;
    }


    /**
     * Gets the unitPrice value for this DeclareItem.
     * 
     * @return unitPrice
     */
    public Double getUnitPrice() {
        return unitPrice;
    }


    /**
     * Sets the unitPrice value for this DeclareItem.
     * 
     * @param unitPrice
     */
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof DeclareItem)) return false;
        DeclareItem other = (DeclareItem) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cnName==null && other.getCnName()==null) || 
             (this.cnName!=null &&
              this.cnName.equals(other.getCnName()))) &&
            ((this.customsNo==null && other.getCustomsNo()==null) || 
             (this.customsNo!=null &&
              this.customsNo.equals(other.getCustomsNo()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.netWeight==null && other.getNetWeight()==null) || 
             (this.netWeight!=null &&
              this.netWeight.equals(other.getNetWeight()))) &&
            ((this.pieces==null && other.getPieces()==null) || 
             (this.pieces!=null &&
              this.pieces.equals(other.getPieces()))) &&
            ((this.productMemo==null && other.getProductMemo()==null) || 
             (this.productMemo!=null &&
              this.productMemo.equals(other.getProductMemo()))) &&
            ((this.unitPrice==null && other.getUnitPrice()==null) || 
             (this.unitPrice!=null &&
              this.unitPrice.equals(other.getUnitPrice())));
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
        if (getCnName() != null) {
            _hashCode += getCnName().hashCode();
        }
        if (getCustomsNo() != null) {
            _hashCode += getCustomsNo().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getNetWeight() != null) {
            _hashCode += getNetWeight().hashCode();
        }
        if (getPieces() != null) {
            _hashCode += getPieces().hashCode();
        }
        if (getProductMemo() != null) {
            _hashCode += getProductMemo().hashCode();
        }
        if (getUnitPrice() != null) {
            _hashCode += getUnitPrice().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DeclareItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.hop.service.ws.hlt.com/", "declareItem"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cnName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cnName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customsNo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customsNo"));
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
        elemField.setFieldName("netWeight");
        elemField.setXmlName(new javax.xml.namespace.QName("", "netWeight"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pieces");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pieces"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productMemo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "productMemo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unitPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "unitPrice"));
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
