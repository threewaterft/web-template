package com.threewater.webserver.webtemplate.po;

import java.util.Date;

public class UserInfo {
    private String userId;

    private String name;

    private String openId;

    private String email;

    private String telNo;

    private String address;

    private String payId;

    private String state;

    private Date validDate;

    private String wxCity;

    private String wxAvatarUrl;

    private String wxGender;

    private String wxCountry;

    private String wxNickname;

    private String wxLanguage;

    private String wxProvince;

    private Date rgstDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo == null ? null : telNo.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId == null ? null : payId.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public String getWxCity() {
        return wxCity;
    }

    public void setWxCity(String wxCity) {
        this.wxCity = wxCity == null ? null : wxCity.trim();
    }

    public String getWxAvatarUrl() {
        return wxAvatarUrl;
    }

    public void setWxAvatarUrl(String wxAvatarUrl) {
        this.wxAvatarUrl = wxAvatarUrl == null ? null : wxAvatarUrl.trim();
    }

    public String getWxGender() {
        return wxGender;
    }

    public void setWxGender(String wxGender) {
        this.wxGender = wxGender == null ? null : wxGender.trim();
    }

    public String getWxCountry() {
        return wxCountry;
    }

    public void setWxCountry(String wxCountry) {
        this.wxCountry = wxCountry == null ? null : wxCountry.trim();
    }

    public String getWxNickname() {
        return wxNickname;
    }

    public void setWxNickname(String wxNickname) {
        this.wxNickname = wxNickname == null ? null : wxNickname.trim();
    }

    public String getWxLanguage() {
        return wxLanguage;
    }

    public void setWxLanguage(String wxLanguage) {
        this.wxLanguage = wxLanguage == null ? null : wxLanguage.trim();
    }

    public String getWxProvince() {
        return wxProvince;
    }

    public void setWxProvince(String wxProvince) {
        this.wxProvince = wxProvince == null ? null : wxProvince.trim();
    }

    public Date getRgstDate() {
        return rgstDate;
    }

    public void setRgstDate(Date rgstDate) {
        this.rgstDate = rgstDate;
    }

    public UserInfo(String userId, String name, String openId, String email, String telNo, String address, String payId, String state, Date validDate, String wxCity, String wxAvatarUrl, String wxGender, String wxCountry, String wxNickname, String wxLanguage, String wxProvince, Date rgstDate) {
        this.userId = userId;
        this.name = name;
        this.openId = openId;
        this.email = email;
        this.telNo = telNo;
        this.address = address;
        this.payId = payId;
        this.state = state;
        this.validDate = validDate;
        this.wxCity = wxCity;
        this.wxAvatarUrl = wxAvatarUrl;
        this.wxGender = wxGender;
        this.wxCountry = wxCountry;
        this.wxNickname = wxNickname;
        this.wxLanguage = wxLanguage;
        this.wxProvince = wxProvince;
        this.rgstDate = rgstDate;
    }

    public UserInfo() {
    }



    public UserInfo(String userId, String openId, String wxCity, String wxAvatarUrl, String wxGender, String wxCountry, String wxNickname, String wxLanguage, String wxProvince, Date rgstDate) {
        this.userId = userId;
        this.openId = openId;
        this.wxCity = wxCity;
        this.wxAvatarUrl = wxAvatarUrl;
        this.wxGender = wxGender;
        this.wxCountry = wxCountry;
        this.wxNickname = wxNickname;
        this.wxLanguage = wxLanguage;
        this.wxProvince = wxProvince;
        this.rgstDate = rgstDate;
    }
}