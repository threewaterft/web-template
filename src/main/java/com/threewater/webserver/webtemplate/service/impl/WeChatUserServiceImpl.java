package com.threewater.webserver.webtemplate.service.impl;

import com.threewater.webserver.webtemplate.mapper.*;
import com.threewater.webserver.webtemplate.po.RoleInfo;
import com.threewater.webserver.webtemplate.po.UserInfo;
import com.threewater.webserver.webtemplate.po.UserRoleInfo;
import com.threewater.webserver.webtemplate.service.UserService;
import com.threewater.webserver.webtemplate.util.sequence.twitter.SnowFlakeSequence;
import com.threewater.webserver.webtemplate.vo.SysRoleVo;
import com.threewater.webserver.webtemplate.vo.SysUserRoleVo;
import com.threewater.webserver.webtemplate.vo.UserRoleInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class WeChatUserServiceImpl implements UserService {
    /**
     * 默认只给游客权限
     */
    private static final String DEFAULT_ROLE_ID = "0";
    @Autowired
    LoginUserInfoMapper loginUserInfoMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    UserRoleInfoMapper userRoleInfoMapper;
    @Autowired
    UserRoleInfoVoMapper userRoleInfoVoMapper;

    @Override
    public UserInfo queryUserById(String id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }
    @Transactional
    @Override
    public int rgstUser(UserInfo userInfo) {
        //此处添加事务
        if(userInfoMapper.insert(userInfo)>0) {
            //获取用户id后为其新增权限，默认只有ROLE_GUEST用户
            if(userRoleInfoMapper.insert(new UserRoleInfo(SnowFlakeSequence.genPKId(),userInfo.getUserId() , DEFAULT_ROLE_ID))>0){
                //插入角色和用户成功
                return 1;
            };
            //TO-DO 回滚
        }
        return 0;
    }

    @Override
    public UserRoleInfoVo queryUserRoleInfoById(String id) {
        return userRoleInfoVoMapper.queryRoleInfoByUserId(id);
    }


}
