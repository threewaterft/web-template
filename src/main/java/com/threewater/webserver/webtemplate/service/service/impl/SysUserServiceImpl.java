package com.threewater.webserver.webtemplate.service.service.impl;


import com.threewater.webserver.webtemplate.mapper.SysUserMapper;
import com.threewater.webserver.webtemplate.service.SysUserService;
import com.threewater.webserver.webtemplate.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper userMapper;
    @Override
    public SysUserVo selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public SysUserVo selectByName(String name) {
        return userMapper.selectByName(name);
    }
}
