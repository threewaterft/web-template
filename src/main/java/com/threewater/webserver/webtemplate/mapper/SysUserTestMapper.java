package com.threewater.webserver.webtemplate.mapper;

import com.threewater.webserver.webtemplate.vo.SysUserVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface SysUserTestMapper {
    @Select("SELECT * FROM sys_user WHERE id = #{id}")
    SysUserVo selectById(Integer id);

    @Select("SELECT * FROM sys_user WHERE name = #{name}")
    SysUserVo selectByName(String name);
    @Insert("INSERT INTO sys_user(NAME,PASSWORD) VALUES(#{name},#{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(SysUserVo sysUserVo);
}
