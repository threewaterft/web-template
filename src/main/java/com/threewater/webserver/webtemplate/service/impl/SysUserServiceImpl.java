package com.threewater.webserver.webtemplate.service.impl;


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

    @Override
    public boolean insertUser(SysUserVo sysUserVo) {
        if(userMapper.insertUser(sysUserVo)>0)
            return true;
        else
            return false;
    }
}
