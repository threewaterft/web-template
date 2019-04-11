package com.threewater.webserver.webtemplate.service.impl;

import com.threewater.webserver.webtemplate.mapper.SysRoleTestMapper;
import com.threewater.webserver.webtemplate.service.SysRoleService;
import com.threewater.webserver.webtemplate.vo.SysRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleTestMapper roleMapper;
    @Override
    public SysRoleVo selectById(Integer id) {
        return roleMapper.selectById(id);
    }
}
