package com.threewater.webserver.webtemplate.mapper;

import com.threewater.webserver.webtemplate.po.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginUserInfoMapper {
    @Select("SELECT * FROM user_info WHERE openId = #{openId}")
    UserInfo selectByOpenId(String openId);
}
