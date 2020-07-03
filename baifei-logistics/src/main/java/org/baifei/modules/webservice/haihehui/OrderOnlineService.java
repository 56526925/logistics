/**
 * OrderOnlineService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.baifei.modules.webservice.haihehui;

public interface OrderOnlineService extends java.rmi.Remote {
    public CreateOrderResponse createOrderV2(String userToken, CreateOrderRequestV2 createOrderRequestV2) throws java.rmi.RemoteException;
    public OrderInfoResponse orderInfoList(String apiToken, java.util.Calendar beginDate, java.util.Calendar endDate, int page) throws java.rmi.RemoteException;
    public LookupOrderVirtualWnoResponse lookupOrderVirtualWno(String userToken, LookupOrderRequest lookupOrderRequest) throws java.rmi.RemoteException;
    public PrintOrderResponse printOrder(String userToken, PrintOrderRequest printOrderRequest) throws java.rmi.RemoteException;
    public CreateOrderResponse createAndAuditOrderV2(String userToken, CreateOrderRequestV2 createOrderRequestV2) throws java.rmi.RemoteException;
    public CalculateChargeResponse calculateCharge(String userToken, CalculateChargeRequest calculateChargeRequest) throws java.rmi.RemoteException;
    public DeleteOrderResponse deleteOrder(String userToken, long orderId) throws java.rmi.RemoteException;
    public AuditOrderResponse auditOrder(String userToken, long orderId) throws java.rmi.RemoteException;
    public GetTrackResponse getTrack(String userToken, String trackingNo, String orderNo) throws java.rmi.RemoteException;
    public CreateOrderResponse createOrder(String userToken, CreateOrderRequest createOrderRequest) throws java.rmi.RemoteException;
    public LookupOrderResponse lookupOrder(String userToken, LookupOrderRequest lookupOrderRequest) throws java.rmi.RemoteException;
    public LookupOrderInfoLoadResponse lookupOrderInfoLoad(String userToken, LookupOrderRequest lookupOrderRequest) throws java.rmi.RemoteException;
    public CreateOrderResponse createAndAuditOrder(String userToken, CreateOrderRequest createOrderRequest) throws java.rmi.RemoteException;
    public GetTransportWayListResponse getTransportWayList(String userToken) throws java.rmi.RemoteException;
}
