package com.threewater.webserver.webtemplate.po;


public class ProductStoreInfo {

  private String prodStoreId;
  private String whId;
  private String prodPos;
  private String userId;
  private long prodAmount;
  private String unit;


  public String getProdStoreId() {
    return prodStoreId;
  }

  public void setProdStoreId(String prodStoreId) {
    this.prodStoreId = prodStoreId;
  }


  public String getWhId() {
    return whId;
  }

  public void setWhId(String whId) {
    this.whId = whId;
  }


  public String getProdPos() {
    return prodPos;
  }

  public void setProdPos(String prodPos) {
    this.prodPos = prodPos;
  }


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public long getProdAmount() {
    return prodAmount;
  }

  public void setProdAmount(long prodAmount) {
    this.prodAmount = prodAmount;
  }


  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

}
