package com.threewater.webserver.webtemplate.security.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.threewater.webserver.webtemplate.filter.auth.WeChatLoginFilter;
import com.threewater.webserver.webtemplate.mapper.LoginUserInfoMapper;
import com.threewater.webserver.webtemplate.po.RoleInfo;
import com.threewater.webserver.webtemplate.po.UserInfo;
import com.threewater.webserver.webtemplate.po.UserRoleInfo;
import com.threewater.webserver.webtemplate.service.UserService;
import com.threewater.webserver.webtemplate.util.JsonUtil;
import com.threewater.webserver.webtemplate.vo.UserRoleInfoVo;
import com.threewater.webserver.webtemplate.vo.WeChatLoginUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class WeChatAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    UserService weChatUserServiceImpl;
    /**
     * 微信url所在栏位
     */
    private final static String AVATAR_URL = "avatarUrl";
    /**
     * 微信昵称
     */
    private final static String NICK_NAME = "nickName";
    /**
     * 微信性别
     */
    private final static String GENDER = "gender";
    /**
     * 微信国家
     */
    private final static String COUNTRY = "country";
    /**
     * 微信省
     */
    private final static String PROVINCE = "province";
    /**
     * 微信市
     */
    private final static String CITY = "city";
    /**
     * 微信语言
     */
    private final static String LANGUAGE = "language";

    @Autowired
    ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(WeChatAuthenticationProvider.class);


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //TO-DO 是否需要和加密数据匹配一次
       String strUserinfo = authentication.getName();
       String strCredentials = (String)authentication.getCredentials();
       JSONObject userInfoMap =genUserInfoMap(strUserinfo,strCredentials);
//       logger.info("the userInfoMap is: {}", userInfoMap);
//        UserInfo userRoleInfo = weChatUserServiceImpl.queryUserById(userInfoMap.get(WeChatLoginFilter.OPEN_ID));
       UserRoleInfoVo userInfo = weChatUserServiceImpl.queryUserRoleInfoById((userInfoMap.getString(WeChatLoginFilter.OPEN_ID)));
       logger.info("the userInfo is: {}", userInfo);
       if(userInfo == null){
           //此为新用户，为其自动注册
           WeChatLoginUserVo loginUserVo = new WeChatLoginUserVo(userInfoMap.getString(WeChatLoginFilter.OPEN_ID),userInfoMap.getString(CITY),
                   userInfoMap.getString(AVATAR_URL),String.valueOf(userInfoMap.getIntValue(GENDER)),userInfoMap.getString(COUNTRY),userInfoMap.getString(NICK_NAME),
                   userInfoMap.getString(LANGUAGE),userInfoMap.getString(PROVINCE),new Date());
//           logger.info("+++{}",JsonUtil.obj2str(loginUserVo.buildUserInfo()));
           if(weChatUserServiceImpl.rgstUser(loginUserVo.buildUserInfo())>0){
               List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_GUEST"));
               User user = new User(loginUserVo.getWxNickname(),"",authorities);
               return new UsernamePasswordAuthenticationToken(user,authentication.getCredentials(),authorities);
           }else{
               //TO-DO 获取这个异常处理
               throw new UsernameNotFoundException("用户注册失败");
           }
       }else{
           //TO-DO 用户已经注册过，获取其相应的权限，构造一个UsernamePasswordAuthenticationToken
           List<GrantedAuthority> authorities = new ArrayList<>();
           List<RoleInfo> roleInfos = userInfo.getRoleInfoList();
           for(RoleInfo roleInfo: roleInfos){
               authorities.add(new SimpleGrantedAuthority(roleInfo.getRoleName()));
           }
           User user = new User(userInfo.getWxNickname(),"",authorities);
           return new UsernamePasswordAuthenticationToken(user,authentication.getCredentials(),authorities);
       }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass));
    }

    /**
     *
     * @param strUserInfo
     * @param strCredentials
     * @return
     */
    private JSONObject genUserInfoMap(String strUserInfo, String strCredentials){
        JSONObject userTotalInfoJb = JSON.parseObject(strUserInfo);
        JSONObject userInfoJb = userTotalInfoJb.getJSONObject("userInfo");
        JSONObject credentialsJb = JSON.parseObject(strCredentials);
        JSONObject userInfoMapJb = new JSONObject(userInfoJb);
        userInfoMapJb.putAll(credentialsJb);
        return userInfoJb;
    }
}
