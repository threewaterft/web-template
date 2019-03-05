package com.threewater.webserver.webtemplate.mapper;

import com.threewater.webserver.webtemplate.vo.SysUserVo;
import org.apache.ibatis.annotations.Select;

public interface SysUserMapper {
    @Select("SELECT * FROM sys_user WHERE id = #{id}")
    SysUserVo selectById(Integer id);

    @Select("SELECT * FROM sys_user WHERE name = #{name}")
    SysUserVo selectByName(String name);
}
