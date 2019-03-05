package com.threewater.webserver.webtemplate.service;


import com.threewater.webserver.webtemplate.vo.SysUserRoleVo;

import java.util.List;

public interface SysUserRoleService {
    public List<SysUserRoleVo> listByUserId(Integer userId);
}
