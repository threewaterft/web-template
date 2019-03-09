package com.threewater.webserver.webtemplate.service.impl;

import com.threewater.webserver.webtemplate.service.SysRoleService;
import com.threewater.webserver.webtemplate.service.SysUserRoleService;
import com.threewater.webserver.webtemplate.service.SysUserService;
import com.threewater.webserver.webtemplate.vo.SysRoleVo;
import com.threewater.webserver.webtemplate.vo.SysUserRoleVo;
import com.threewater.webserver.webtemplate.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysUserRoleService userRoleService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 从数据库中取出用户信息
        SysUserVo user = userService.selectByName(username);
        // 判断用户是否存在
        if(user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        // 添加权限
        List<SysUserRoleVo> userRoles = userRoleService.listByUserId(2);

        for (SysUserRoleVo userRole : userRoles) {
            SysRoleVo role = roleService.selectById(userRole.getRoleId());
            System.out.println("角色是"+role.getName());
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        // 返回UserDetails实现类
        return new User(user.getName(), user.getPassword(), authorities);
    }
}
