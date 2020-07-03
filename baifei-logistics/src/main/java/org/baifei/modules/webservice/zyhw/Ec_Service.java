/**
 * Ec_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.baifei.modules.webservice.zyhw;

public interface Ec_Service extends javax.xml.rpc.Service {
    public String getEcSOAPAddress();

    public Ec_PortType getEcSOAP() throws javax.xml.rpc.ServiceException;

    public Ec_PortType getEcSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
