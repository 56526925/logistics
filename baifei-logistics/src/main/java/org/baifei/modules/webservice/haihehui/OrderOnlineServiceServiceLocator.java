/**
 * OrderOnlineServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.baifei.modules.webservice.haihehui;

public class OrderOnlineServiceServiceLocator extends org.apache.axis.client.Service implements OrderOnlineServiceService {

    public OrderOnlineServiceServiceLocator() {
    }


    public OrderOnlineServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public OrderOnlineServiceServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for OrderOnlineServicePort
    private String OrderOnlineServicePort_address = "http://120.78.72.68:8086/xms/services/order";

    public String getOrderOnlineServicePortAddress() {
        return OrderOnlineServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private String OrderOnlineServicePortWSDDServiceName = "OrderOnlineServicePort";

    public String getOrderOnlineServicePortWSDDServiceName() {
        return OrderOnlineServicePortWSDDServiceName;
    }

    public void setOrderOnlineServicePortWSDDServiceName(String name) {
        OrderOnlineServicePortWSDDServiceName = name;
    }

    public OrderOnlineService getOrderOnlineServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(OrderOnlineServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getOrderOnlineServicePort(endpoint);
    }

    public OrderOnlineService getOrderOnlineServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            OrderOnlineServiceServiceSoapBindingStub _stub = new OrderOnlineServiceServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getOrderOnlineServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setOrderOnlineServicePortEndpointAddress(String address) {
        OrderOnlineServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (OrderOnlineService.class.isAssignableFrom(serviceEndpointInterface)) {
                OrderOnlineServiceServiceSoapBindingStub _stub = new OrderOnlineServiceServiceSoapBindingStub(new java.net.URL(OrderOnlineServicePort_address), this);
                _stub.setPortName(getOrderOnlineServicePortWSDDServiceName());
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
        if ("OrderOnlineServicePort".equals(inputPortName)) {
            return getOrderOnlineServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.hop.service.ws.hlt.com/", "OrderOnlineServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.hop.service.ws.hlt.com/", "OrderOnlineServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {
        
if ("OrderOnlineServicePort".equals(portName)) {
            setOrderOnlineServicePortEndpointAddress(address);
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
