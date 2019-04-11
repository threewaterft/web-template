package com.threewater.webserver.webtemplate.po;

public class ProdTypeInfo {
    private String prodTypeId;

    private String supProdTypeId;

    private Integer prodTypeLevel;

    private String prodTypeName;

    private String prodTypeDesc;

    public String getProdTypeId() {
        return prodTypeId;
    }

    public void setProdTypeId(String prodTypeId) {
        this.prodTypeId = prodTypeId == null ? null : prodTypeId.trim();
    }

    public String getSupProdTypeId() {
        return supProdTypeId;
    }

    public void setSupProdTypeId(String supProdTypeId) {
        this.supProdTypeId = supProdTypeId == null ? null : supProdTypeId.trim();
    }

    public Integer getProdTypeLevel() {
        return prodTypeLevel;
    }

    public void setProdTypeLevel(Integer prodTypeLevel) {
        this.prodTypeLevel = prodTypeLevel;
    }

    public String getProdTypeName() {
        return prodTypeName;
    }

    public void setProdTypeName(String prodTypeName) {
        this.prodTypeName = prodTypeName == null ? null : prodTypeName.trim();
    }

    public String getProdTypeDesc() {
        return prodTypeDesc;
    }

    public void setProdTypeDesc(String prodTypeDesc) {
        this.prodTypeDesc = prodTypeDesc == null ? null : prodTypeDesc.trim();
    }
}