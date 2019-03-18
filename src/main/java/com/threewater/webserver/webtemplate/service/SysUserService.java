package com.threewater.webserver.webtemplate.service;


import com.threewater.webserver.webtemplate.vo.SysUserVo;

public interface SysUserService {
    public SysUserVo selectById(Integer id);
    public SysUserVo selectByName(String name);
    public boolean insertUser(SysUserVo sysUserVo);
}
