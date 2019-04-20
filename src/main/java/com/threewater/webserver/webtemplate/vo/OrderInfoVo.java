package com.threewater.webserver.webtemplate.vo;

import com.threewater.webserver.webtemplate.po.OrderDetailInfo;

import java.util.Date;
import java.util.List;

public class OrderInfoVo {
    private String orderId;

    private Date orderDate;

    private String userId;

    private String customerName;

    private Double totalPrice;

    private String payType;

    private Date payDate;

    private String customerTel;

    private String state;

    private String orderDesc;

    private List<OrderDetailInfo> orderDetailInfoList;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
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

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
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

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public List<OrderDetailInfo> getOrderDetailInfoList() {
        return orderDetailInfoList;
    }

    public void setOrderDetailInfoList(List<OrderDetailInfo> orderDetailInfoList) {
        this.orderDetailInfoList = orderDetailInfoList;
    }
}
