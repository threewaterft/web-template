package com.threewater.webserver.webtemplate.vo;

public class LoginUserVo {
    private String username;
    private String password;
//    private Integer rememberMe;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRememberMe() {
        return 1;
//        return rememberMe;
    }

    public void setRememberMe(Integer rememberMe) {
//        this.rememberMe = rememberMe;
    }

    public LoginUserVo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginUserVo() {
    }
}
