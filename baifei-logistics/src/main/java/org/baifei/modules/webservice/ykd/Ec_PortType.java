/**
 * Ec_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.baifei.modules.webservice.ykd;

public interface Ec_PortType extends java.rmi.Remote {
    public String callService(String paramsJson, String appToken, String appKey, String language, String service) throws java.rmi.RemoteException;
}
