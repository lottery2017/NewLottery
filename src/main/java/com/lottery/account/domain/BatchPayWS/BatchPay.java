/**
 * BatchPay.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lottery.account.domain.BatchPayWS;

import com.lottery.account.domain.complatible.*;

public interface BatchPay extends java.rmi.Remote {
    public QueryResponseBean[] queryDeal(QueryRequestBean input, String username, String ip) throws java.rmi.RemoteException;
    public BankResponseBean[] bankPay(BankRequestBean[] input, String username, String ip) throws java.rmi.RemoteException;
    public SimpleResponseBean[] simplePay(SimpleRequestBean[] input, String username, String ip) throws java.rmi.RemoteException;
    public PostResponseBean[] postPay(PostRequestBean[] input, String username, String ip) throws java.rmi.RemoteException;
}
