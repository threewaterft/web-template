package com.threewater.webserver.webtemplate.mapper;

import com.threewater.webserver.webtemplate.vo.SysRoleVo;
import org.apache.ibatis.annotations.Select;

public interface SysRoleMapper {
    @Select("SELECT * FROM sys_role WHERE id = #{id}")
    SysRoleVo selectById(Integer id);
}