package com.threewater.webserver.webtemplate.po;


public class PayInfo {

  private String payId;
  private String payType;
  private String payDesc;
  private String accountNo;
  private String payee;


  public String getPayId() {
    return payId;
  }

  public void setPayId(String payId) {
    this.payId = payId;
  }


  public String getPayType() {
    return payType;
  }

  public void setPayType(String payType) {
    this.payType = payType;
  }


  public String getPayDesc() {
    return payDesc;
  }

  public void setPayDesc(String payDesc) {
    this.payDesc = payDesc;
  }


  public String getAccountNo() {
    return accountNo;
  }

  public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
  }


  public String getPayee() {
    return payee;
  }

  public void setPayee(String payee) {
    this.payee = payee;
  }

}
