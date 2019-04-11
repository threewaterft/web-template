package com.threewater.webserver.webtemplate.vo;

import com.threewater.webserver.webtemplate.po.RoleInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserRoleInfoVo {
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

    private List<RoleInfo> roleInfoList = new ArrayList<>();

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
        this.wxCity = wxCity;
    }

    public String getWxAvatarUrl() {
        return wxAvatarUrl;
    }

    public void setWxAvatarUrl(String wxAvatarUrl) {
        this.wxAvatarUrl = wxAvatarUrl;
    }

    public String getWxGender() {
        return wxGender;
    }

    public void setWxGender(String wxGender) {
        this.wxGender = wxGender;
    }

    public String getWxCountry() {
        return wxCountry;
    }

    public void setWxCountry(String wxCountry) {
        this.wxCountry = wxCountry;
    }

    public String getWxNickname() {
        return wxNickname;
    }

    public void setWxNickname(String wxNickname) {
        this.wxNickname = wxNickname;
    }

    public String getWxLanguage() {
        return wxLanguage;
    }

    public void setWxLanguage(String wxLanguage) {
        this.wxLanguage = wxLanguage;
    }

    public String getWxProvince() {
        return wxProvince;
    }

    public void setWxProvince(String wxProvince) {
        this.wxProvince = wxProvince;
    }

    public Date getRgstDate() {
        return rgstDate;
    }

    public void setRgstDate(Date rgstDate) {
        this.rgstDate = rgstDate;
    }

    public List<RoleInfo> getRoleInfoList() {
        return roleInfoList;
    }

    public void setRoleInfoList(List<RoleInfo> roleInfoList) {
        this.roleInfoList = roleInfoList;
    }
}
