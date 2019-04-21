package com.threewater.webserver.webtemplate.po;

import java.util.Date;

public class ProductInfo {
    private String prodId;

    private String prodName;

    private String prodTypeId;

    private Double singlePrice;

    private String thImg;

    private String img;

    private String prodDesc;

    private Date joinTime;

    public ProductInfo() {
    }

    public ProductInfo(String prodId, String prodName, String prodTypeId, Double singlePrice, String thImg, String img, String prodDesc, Date joinTime) {
        this.prodId = prodId;
        this.prodName = prodName;
        this.prodTypeId = prodTypeId;
        this.singlePrice = singlePrice;
        this.thImg = thImg;
        this.img = img;
        this.prodDesc = prodDesc;
        this.joinTime = joinTime;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    public String getProdTypeId() {
        return prodTypeId;
    }

    public void setProdTypeId(String prodTypeId) {
        this.prodTypeId = prodTypeId == null ? null : prodTypeId.trim();
    }

    public Double getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(Double singlePrice) {
        this.singlePrice = singlePrice;
    }

    public String getThImg() {
        return thImg;
    }

    public void setThImg(String thImg) {
        this.thImg = thImg == null ? null : thImg.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc == null ? null : prodDesc.trim();
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }
}