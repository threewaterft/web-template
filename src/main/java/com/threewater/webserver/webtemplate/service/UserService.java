package com.threewater.webserver.webtemplate.service;

import com.threewater.webserver.webtemplate.po.UserInfo;
import com.threewater.webserver.webtemplate.vo.UserRoleInfoVo;

public interface UserService {
    /**
     * 通过openid查询用户信息
     * @param id
     * @return
     */
    public UserInfo queryUserById(String id);

    /**
     * 新用户注册
     * @param userInfo
     * @return
     */
    public int rgstUser(UserInfo userInfo);

    /**
     * 通过openid查询带有权限信息的用户信息
     * @param id
     * @return
     */
    public UserRoleInfoVo queryUserRoleInfoById(String id);
}
