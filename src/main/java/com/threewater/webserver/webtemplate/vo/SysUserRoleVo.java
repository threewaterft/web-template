package com.threewater.webserver.webtemplate.vo;

import java.io.Serializable;

public class SysUserRoleVo implements Serializable {
    static final long serialVersionUID = 1L;
    private Integer userId;
    private Integer roleId;
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}