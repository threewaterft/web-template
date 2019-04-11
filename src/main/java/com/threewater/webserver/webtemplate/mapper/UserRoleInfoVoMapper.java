package com.threewater.webserver.webtemplate.mapper;

import com.threewater.webserver.webtemplate.po.RoleInfo;
import com.threewater.webserver.webtemplate.vo.UserRoleInfoVo;

import java.util.List;

public interface UserRoleInfoVoMapper {
    UserRoleInfoVo queryRoleInfoByUserId(String userId);

    List<RoleInfo> findRolesByUserId(String userId);
}
