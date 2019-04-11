package com.threewater.webserver.webtemplate.vo;

import com.threewater.webserver.webtemplate.po.UserInfo;
import com.threewater.webserver.webtemplate.util.sequence.twitter.SnowFlakeSequence;

import java.util.Date;

public class WeChatLoginUserVo {
    private String openId;

    private String wxCity;

    private String wxAvatarUrl;

    private String wxGender;

    private String wxCountry;

    private String wxNickname;

    private String wxLanguage;

    private String wxProvince;

    private Date rgstDate;

    public WeChatLoginUserVo(String openId, String wxCity, String wxAvatarUrl, String wxGender, String wxCountry, String wxNickname, String wxLanguage, String wxProvince, Date rgstDate) {
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

    public WeChatLoginUserVo() {
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
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

    public UserInfo buildUserInfo(){
        return new UserInfo(SnowFlakeSequence.genPKId(),openId,wxCity,wxAvatarUrl,wxGender,wxCountry,wxNickname,wxLanguage,wxProvince,rgstDate);
    }
}
