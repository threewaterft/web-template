package com.threewater.webserver.webtemplate.mapper;

import com.threewater.webserver.webtemplate.po.SysUserRoleKey;

public interface SysUserRoleMapper {
    int deleteByPrimaryKey(SysUserRoleKey key);

    int insert(SysUserRoleKey record);

    int insertSelective(SysUserRoleKey record);
}