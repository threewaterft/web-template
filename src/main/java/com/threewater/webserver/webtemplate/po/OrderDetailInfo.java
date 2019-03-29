package com.threewater.webserver.webtemplate.po;


public class OrderDetailInfo {

  private String orderDetailId;
  private String orderId;
  private String prodId;
  private long amount;
  private double curPrice;


  public String getOrderDetailId() {
    return orderDetailId;
  }

  public void setOrderDetailId(String orderDetailId) {
    this.orderDetailId = orderDetailId;
  }


  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }


  public String getProdId() {
    return prodId;
  }

  public void setProdId(String prodId) {
    this.prodId = prodId;
  }


  public long getAmount() {
    return amount;
  }

  public void setAmount(long amount) {
    this.amount = amount;
  }


  public double getCurPrice() {
    return curPrice;
  }

  public void setCurPrice(double curPrice) {
    this.curPrice = curPrice;
  }

}
