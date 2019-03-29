package com.threewater.webserver.webtemplate.po;


public class ProdTypeInfo {

  private String prodTypeId;
  private String supProdTypeId;
  private long prodTypeLevel;
  private String prodTypeName;
  private String prodTypeDesc;


  public String getProdTypeId() {
    return prodTypeId;
  }

  public void setProdTypeId(String prodTypeId) {
    this.prodTypeId = prodTypeId;
  }


  public String getSupProdTypeId() {
    return supProdTypeId;
  }

  public void setSupProdTypeId(String supProdTypeId) {
    this.supProdTypeId = supProdTypeId;
  }


  public long getProdTypeLevel() {
    return prodTypeLevel;
  }

  public void setProdTypeLevel(long prodTypeLevel) {
    this.prodTypeLevel = prodTypeLevel;
  }


  public String getProdTypeName() {
    return prodTypeName;
  }

  public void setProdTypeName(String prodTypeName) {
    this.prodTypeName = prodTypeName;
  }


  public String getProdTypeDesc() {
    return prodTypeDesc;
  }

  public void setProdTypeDesc(String prodTypeDesc) {
    this.prodTypeDesc = prodTypeDesc;
  }

}
