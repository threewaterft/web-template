package com.threewater.webserver.webtemplate.service.service.impl;

import com.threewater.webserver.webtemplate.mapper.SysRoleMapper;
import com.threewater.webserver.webtemplate.service.SysRoleService;
import com.threewater.webserver.webtemplate.vo.SysRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper roleMapper;
    @Override
    public SysRoleVo selectById(Integer id) {
        return roleMapper.selectById(id);
    }
}
