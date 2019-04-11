package com.threewater.webserver.webtemplate.po;

public class OrderDetailInfo {
    private String orderDetailId;

    private String orderId;

    private String prodId;

    private Integer amount;

    private Double curPrice;

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId == null ? null : orderDetailId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getCurPrice() {
        return curPrice;
    }

    public void setCurPrice(Double curPrice) {
        this.curPrice = curPrice;
    }
}