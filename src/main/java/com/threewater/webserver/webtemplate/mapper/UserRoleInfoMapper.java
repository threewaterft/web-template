package com.threewater.webserver.webtemplate.mapper;

import com.threewater.webserver.webtemplate.po.RoleInfo;
import com.threewater.webserver.webtemplate.po.UserRoleInfo;

public interface UserRoleInfoMapper {
    int deleteByPrimaryKey(String userRoleId);

    int insert(UserRoleInfo record);

    int insertSelective(UserRoleInfo record);

    UserRoleInfo selectByPrimaryKey(String userRoleId);

    int updateByPrimaryKeySelective(UserRoleInfo record);

    int updateByPrimaryKey(UserRoleInfo record);

    RoleInfo queryRoleInfoByUserId(String userId);
}