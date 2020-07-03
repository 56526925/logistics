/**
 * SfKtsServiceImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.baifei.modules.webservice.sf;

import org.baifei.modules.webservice.sf.SfKtsService;
import org.baifei.modules.webservice.sf.SfKtsServiceImplServiceSoapBindingStub;

public class SfKtsServiceImplServiceLocator extends org.apache.axis.client.Service implements SfKtsServiceImplService {

    public SfKtsServiceImplServiceLocator() {
    }


    public SfKtsServiceImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SfKtsServiceImplServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SfKtsServiceImplPort
    private String SfKtsServiceImplPort_address = "http://sfapi.trackmeeasy.com/ruserver/webservice/sfexpressService";

    public String getSfKtsServiceImplPortAddress() {
        return SfKtsServiceImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private String SfKtsServiceImplPortWSDDServiceName = "SfKtsServiceImplPort";

    public String getSfKtsServiceImplPortWSDDServiceName() {
        return SfKtsServiceImplPortWSDDServiceName;
    }

    public void setSfKtsServiceImplPortWSDDServiceName(String name) {
        SfKtsServiceImplPortWSDDServiceName = name;
    }

    public SfKtsService getSfKtsServiceImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SfKtsServiceImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSfKtsServiceImplPort(endpoint);
    }

    public SfKtsService getSfKtsServiceImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            SfKtsServiceImplServiceSoapBindingStub _stub = new SfKtsServiceImplServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSfKtsServiceImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSfKtsServiceImplPortEndpointAddress(String address) {
        SfKtsServiceImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (SfKtsService.class.isAssignableFrom(serviceEndpointInterface)) {
                SfKtsServiceImplServiceSoapBindingStub _stub = new SfKtsServiceImplServiceSoapBindingStub(new java.net.URL(SfKtsServiceImplPort_address), this);
                _stub.setPortName(getSfKtsServiceImplPortWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("SfKtsServiceImplPort".equals(inputPortName)) {
            return getSfKtsServiceImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://impl.ktswebservice.main.sf.com/", "SfKtsServiceImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://impl.ktswebservice.main.sf.com/", "SfKtsServiceImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {
        
if ("SfKtsServiceImplPort".equals(portName)) {
            setSfKtsServiceImplPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
