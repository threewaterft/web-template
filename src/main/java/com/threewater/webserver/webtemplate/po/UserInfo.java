package com.threewater.webserver.webtemplate.po;


public class UserInfo {

  private String userId;
  private String name;
  private String openId;
  private String email;
  private String telNo;
  private String address;
  private String payId;
  private String state;
  private java.sql.Date validDate;


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getOpenId() {
    return openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getTelNo() {
    return telNo;
  }

  public void setTelNo(String telNo) {
    this.telNo = telNo;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getPayId() {
    return payId;
  }

  public void setPayId(String payId) {
    this.payId = payId;
  }


  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }


  public java.sql.Date getValidDate() {
    return validDate;
  }

  public void setValidDate(java.sql.Date validDate) {
    this.validDate = validDate;
  }

}
