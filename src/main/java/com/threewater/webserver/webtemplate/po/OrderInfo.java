package com.threewater.webserver.webtemplate.po;


public class OrderInfo {

  private String orderId;
  private java.sql.Timestamp orderDate;
  private String userId;
  private String customerName;
  private double totalPrice;
  private String payType;
  private java.sql.Timestamp payDate;
  private String customerTel;
  private String state;
  private long orderDesc;


  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }


  public java.sql.Timestamp getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(java.sql.Timestamp orderDate) {
    this.orderDate = orderDate;
  }


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }


  public double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }


  public String getPayType() {
    return payType;
  }

  public void setPayType(String payType) {
    this.payType = payType;
  }


  public java.sql.Timestamp getPayDate() {
    return payDate;
  }

  public void setPayDate(java.sql.Timestamp payDate) {
    this.payDate = payDate;
  }


  public String getCustomerTel() {
    return customerTel;
  }

  public void setCustomerTel(String customerTel) {
    this.customerTel = customerTel;
  }


  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }


  public long getOrderDesc() {
    return orderDesc;
  }

  public void setOrderDesc(long orderDesc) {
    this.orderDesc = orderDesc;
  }

}
