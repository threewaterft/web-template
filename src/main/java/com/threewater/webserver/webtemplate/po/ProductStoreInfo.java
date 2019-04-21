package com.threewater.webserver.webtemplate.po;

public class ProductStoreInfo {
    private String prodStoreId;

    private String whId;

    private String prodPos;

    private String userId;

    private Integer prodAmount;

    private String unit;

    private String prodId;

    public ProductStoreInfo() {
    }

    public ProductStoreInfo(String prodStoreId, String whId, String prodPos, String userId, Integer prodAmount, String unit, String prodId) {
        this.prodStoreId = prodStoreId;
        this.whId = whId;
        this.prodPos = prodPos;
        this.userId = userId;
        this.prodAmount = prodAmount;
        this.unit = unit;
        this.prodId = prodId;
    }

    public String getProdStoreId() {
        return prodStoreId;
    }

    public void setProdStoreId(String prodStoreId) {
        this.prodStoreId = prodStoreId == null ? null : prodStoreId.trim();
    }

    public String getWhId() {
        return whId;
    }

    public void setWhId(String whId) {
        this.whId = whId == null ? null : whId.trim();
    }

    public String getProdPos() {
        return prodPos;
    }

    public void setProdPos(String prodPos) {
        this.prodPos = prodPos == null ? null : prodPos.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getProdAmount() {
        return prodAmount;
    }

    public void setProdAmount(Integer prodAmount) {
        this.prodAmount = prodAmount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }
}