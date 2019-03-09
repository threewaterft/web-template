package com.threewater.webserver.webtemplate.service.impl;

import com.threewater.webserver.webtemplate.mapper.SysUserRoleMapper;
import com.threewater.webserver.webtemplate.service.SysUserRoleService;
import com.threewater.webserver.webtemplate.vo.SysUserRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Override
    public List<SysUserRoleVo> listByUserId(Integer userId) {
        return userRoleMapper.listByUserId(userId);
    }
}
