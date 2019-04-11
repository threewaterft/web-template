package com.threewater.webserver.webtemplate.mapper;

import com.threewater.webserver.webtemplate.vo.SysUserRoleVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysUserRoleTestMapper {
    @Select("SELECT * FROM sys_user_role WHERE user_id = #{userId}")
    List<SysUserRoleVo> listByUserId(Integer userId);
}
