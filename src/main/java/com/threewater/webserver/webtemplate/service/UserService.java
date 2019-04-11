package com.threewater.webserver.webtemplate.service;

import com.threewater.webserver.webtemplate.po.UserInfo;

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

}
