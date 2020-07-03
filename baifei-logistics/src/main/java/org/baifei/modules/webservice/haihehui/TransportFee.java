/**
 * TransportFee.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.baifei.modules.webservice.haihehui;

public class TransportFee  implements java.io.Serializable {
    private String currency;

    private String deliveryPeriod;

    private String description;

    private Double discount;

    private String formula;

    private Double freight;

    private Double freightFaraway;

    private Double freightFuel;

    private Double sum;

    private String tracking;

    private String transportWayCName;

    private String transportWayCode;

    private String transportWayEName;

    public TransportFee() {
    }

    public TransportFee(
           String currency,
           String deliveryPeriod,
           String description,
           Double discount,
           String formula,
           Double freight,
           Double freightFaraway,
           Double freightFuel,
           Double sum,
           String tracking,
           String transportWayCName,
           String transportWayCode,
           String transportWayEName) {
           this.currency = currency;
           this.deliveryPeriod = deliveryPeriod;
           this.description = description;
           this.discount = discount;
           this.formula = formula;
           this.freight = freight;
           this.freightFaraway = freightFaraway;
           this.freightFuel = freightFuel;
           this.sum = sum;
           this.tracking = tracking;
           this.transportWayCName = transportWayCName;
           this.transportWayCode = transportWayCode;
           this.transportWayEName = transportWayEName;
    }


    /**
     * Gets the currency value for this TransportFee.
     * 
     * @return currency
     */
    public String getCurrency() {
        return currency;
    }


    /**
     * Sets the currency value for this TransportFee.
     * 
     * @param currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }


    /**
     * Gets the deliveryPeriod value for this TransportFee.
     * 
     * @return deliveryPeriod
     */
    public String getDeliveryPeriod() {
        return deliveryPeriod;
    }


    /**
     * Sets the deliveryPeriod value for this TransportFee.
     * 
     * @param deliveryPeriod
     */
    public void setDeliveryPeriod(String deliveryPeriod) {
        this.deliveryPeriod = deliveryPeriod;
    }


    /**
     * Gets the description value for this TransportFee.
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this TransportFee.
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Gets the discount value for this TransportFee.
     * 
     * @return discount
     */
    public Double getDiscount() {
        return discount;
    }


    /**
     * Sets the discount value for this TransportFee.
     * 
     * @param discount
     */
    public void setDiscount(Double discount) {
        this.discount = discount;
    }


    /**
     * Gets the formula value for this TransportFee.
     * 
     * @return formula
     */
    public String getFormula() {
        return formula;
    }


    /**
     * Sets the formula value for this TransportFee.
     * 
     * @param formula
     */
    public void setFormula(String formula) {
        this.formula = formula;
    }


    /**
     * Gets the freight value for this TransportFee.
     * 
     * @return freight
     */
    public Double getFreight() {
        return freight;
    }


    /**
     * Sets the freight value for this TransportFee.
     * 
     * @param freight
     */
    public void setFreight(Double freight) {
        this.freight = freight;
    }


    /**
     * Gets the freightFaraway value for this TransportFee.
     * 
     * @return freightFaraway
     */
    public Double getFreightFaraway() {
        return freightFaraway;
    }


    /**
     * Sets the freightFaraway value for this TransportFee.
     * 
     * @param freightFaraway
     */
    public void setFreightFaraway(Double freightFaraway) {
        this.freightFaraway = freightFaraway;
    }


    /**
     * Gets the freightFuel value for this TransportFee.
     * 
     * @return freightFuel
     */
    public Double getFreightFuel() {
        return freightFuel;
    }


    /**
     * Sets the freightFuel value for this TransportFee.
     * 
     * @param freightFuel
     */
    public void setFreightFuel(Double freightFuel) {
        this.freightFuel = freightFuel;
    }


    /**
     * Gets the sum value for this TransportFee.
     * 
     * @return sum
     */
    public Double getSum() {
        return sum;
    }


    /**
     * Sets the sum value for this TransportFee.
     * 
     * @param sum
     */
    public void setSum(Double sum) {
        this.sum = sum;
    }


    /**
     * Gets the tracking value for this TransportFee.
     * 
     * @return tracking
     */
    public String getTracking() {
        return tracking;
    }


    /**
     * Sets the tracking value for this TransportFee.
     * 
     * @param tracking
     */
    public void setTracking(String tracking) {
        this.tracking = tracking;
    }


    /**
     * Gets the transportWayCName value for this TransportFee.
     * 
     * @return transportWayCName
     */
    public String getTransportWayCName() {
        return transportWayCName;
    }


    /**
     * Sets the transportWayCName value for this TransportFee.
     * 
     * @param transportWayCName
     */
    public void setTransportWayCName(String transportWayCName) {
        this.transportWayCName = transportWayCName;
    }


    /**
     * Gets the transportWayCode value for this TransportFee.
     * 
     * @return transportWayCode
     */
    public String getTransportWayCode() {
        return transportWayCode;
    }


    /**
     * Sets the transportWayCode value for this TransportFee.
     * 
     * @param transportWayCode
     */
    public void setTransportWayCode(String transportWayCode) {
        this.transportWayCode = transportWayCode;
    }


    /**
     * Gets the transportWayEName value for this TransportFee.
     * 
     * @return transportWayEName
     */
    public String getTransportWayEName() {
        return transportWayEName;
    }


    /**
     * Sets the transportWayEName value for this TransportFee.
     * 
     * @param transportWayEName
     */
    public void setTransportWayEName(String transportWayEName) {
        this.transportWayEName = transportWayEName;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof TransportFee)) return false;
        TransportFee other = (TransportFee) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.currency==null && other.getCurrency()==null) || 
             (this.currency!=null &&
              this.currency.equals(other.getCurrency()))) &&
            ((this.deliveryPeriod==null && other.getDeliveryPeriod()==null) || 
             (this.deliveryPeriod!=null &&
              this.deliveryPeriod.equals(other.getDeliveryPeriod()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.discount==null && other.getDiscount()==null) || 
             (this.discount!=null &&
              this.discount.equals(other.getDiscount()))) &&
            ((this.formula==null && other.getFormula()==null) || 
             (this.formula!=null &&
              this.formula.equals(other.getFormula()))) &&
            ((this.freight==null && other.getFreight()==null) || 
             (this.freight!=null &&
              this.freight.equals(other.getFreight()))) &&
            ((this.freightFaraway==null && other.getFreightFaraway()==null) || 
             (this.freightFaraway!=null &&
              this.freightFaraway.equals(other.getFreightFaraway()))) &&
            ((this.freightFuel==null && other.getFreightFuel()==null) || 
             (this.freightFuel!=null &&
              this.freightFuel.equals(other.getFreightFuel()))) &&
            ((this.sum==null && other.getSum()==null) || 
             (this.sum!=null &&
              this.sum.equals(other.getSum()))) &&
            ((this.tracking==null && other.getTracking()==null) || 
             (this.tracking!=null &&
              this.tracking.equals(other.getTracking()))) &&
            ((this.transportWayCName==null && other.getTransportWayCName()==null) || 
             (this.transportWayCName!=null &&
              this.transportWayCName.equals(other.getTransportWayCName()))) &&
            ((this.transportWayCode==null && other.getTransportWayCode()==null) || 
             (this.transportWayCode!=null &&
              this.transportWayCode.equals(other.getTransportWayCode()))) &&
            ((this.transportWayEName==null && other.getTransportWayEName()==null) || 
             (this.transportWayEName!=null &&
              this.transportWayEName.equals(other.getTransportWayEName())));
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
        if (getCurrency() != null) {
            _hashCode += getCurrency().hashCode();
        }
        if (getDeliveryPeriod() != null) {
            _hashCode += getDeliveryPeriod().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getDiscount() != null) {
            _hashCode += getDiscount().hashCode();
        }
        if (getFormula() != null) {
            _hashCode += getFormula().hashCode();
        }
        if (getFreight() != null) {
            _hashCode += getFreight().hashCode();
        }
        if (getFreightFaraway() != null) {
            _hashCode += getFreightFaraway().hashCode();
        }
        if (getFreightFuel() != null) {
            _hashCode += getFreightFuel().hashCode();
        }
        if (getSum() != null) {
            _hashCode += getSum().hashCode();
        }
        if (getTracking() != null) {
            _hashCode += getTracking().hashCode();
        }
        if (getTransportWayCName() != null) {
            _hashCode += getTransportWayCName().hashCode();
        }
        if (getTransportWayCode() != null) {
            _hashCode += getTransportWayCode().hashCode();
        }
        if (getTransportWayEName() != null) {
            _hashCode += getTransportWayEName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TransportFee.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.hop.service.ws.hlt.com/", "transportFee"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currency");
        elemField.setXmlName(new javax.xml.namespace.QName("", "currency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryPeriod");
        elemField.setXmlName(new javax.xml.namespace.QName("", "deliveryPeriod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("", "description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("discount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "discount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formula");
        elemField.setXmlName(new javax.xml.namespace.QName("", "formula"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("freight");
        elemField.setXmlName(new javax.xml.namespace.QName("", "freight"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("freightFaraway");
        elemField.setXmlName(new javax.xml.namespace.QName("", "freightFaraway"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("freightFuel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "freightFuel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tracking");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tracking"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transportWayCName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transportWayCName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transportWayCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transportWayCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transportWayEName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transportWayEName"));
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
