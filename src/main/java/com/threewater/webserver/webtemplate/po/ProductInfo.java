package com.threewater.webserver.webtemplate.po;


public class ProductInfo {

  private String prodId;
  private String prodName;
  private String prodType;
  private double singlePrice;
  private String thImg;
  private String img;
  private String prodDesc;
  private java.sql.Timestamp joinTime;


  public String getProdId() {
    return prodId;
  }

  public void setProdId(String prodId) {
    this.prodId = prodId;
  }


  public String getProdName() {
    return prodName;
  }

  public void setProdName(String prodName) {
    this.prodName = prodName;
  }


  public String getProdType() {
    return prodType;
  }

  public void setProdType(String prodType) {
    this.prodType = prodType;
  }


  public double getSinglePrice() {
    return singlePrice;
  }

  public void setSinglePrice(double singlePrice) {
    this.singlePrice = singlePrice;
  }


  public String getThImg() {
    return thImg;
  }

  public void setThImg(String thImg) {
    this.thImg = thImg;
  }


  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }


  public String getProdDesc() {
    return prodDesc;
  }

  public void setProdDesc(String prodDesc) {
    this.prodDesc = prodDesc;
  }


  public java.sql.Timestamp getJoinTime() {
    return joinTime;
  }

  public void setJoinTime(java.sql.Timestamp joinTime) {
    this.joinTime = joinTime;
  }

}
