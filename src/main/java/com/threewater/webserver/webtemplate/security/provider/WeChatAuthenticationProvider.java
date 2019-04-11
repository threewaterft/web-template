package com.threewater.webserver.webtemplate.security.provider;

import com.threewater.webserver.webtemplate.filter.auth.WeChatLoginFilter;
import com.threewater.webserver.webtemplate.mapper.LoginUserInfoMapper;
import com.threewater.webserver.webtemplate.po.UserInfo;
import com.threewater.webserver.webtemplate.service.UserService;
import com.threewater.webserver.webtemplate.util.JsonUtil;
import com.threewater.webserver.webtemplate.vo.WeChatLoginUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

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

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //TO-DO 是否需要和加密数据匹配一次
       String strUserinfo = authentication.getName();
       String strCredentials = (String)authentication.getCredentials();
       Map<String,String> userInfoMap =genUserInfoMap(strUserinfo,strCredentials);
       UserInfo userInfo = weChatUserServiceImpl.queryUserById(userInfoMap.get(WeChatLoginFilter.OPEN_ID));
       if(userInfo == null){
           //此为新用户，为其自动注册
           WeChatLoginUserVo loginUserVo = new WeChatLoginUserVo(userInfoMap.get(WeChatLoginFilter.OPEN_ID),userInfoMap.get(CITY),
                   userInfoMap.get(AVATAR_URL),userInfoMap.get(GENDER),userInfoMap.get(COUNTRY),userInfoMap.get(NICK_NAME),
                   userInfoMap.get(LANGUAGE),userInfoMap.get(PROVINCE),new Date());
           if(weChatUserServiceImpl.rgstUser(loginUserVo.buildUserInfo())>0){
               List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_GUEST"));
               User user = new User(loginUserVo.getWxNickname(),"",authorities);
               return new UsernamePasswordAuthenticationToken(user,authentication.getCredentials(),authorities);
           }else{
               throw new UsernameNotFoundException("用户注册失败");
           }
       }else{
           //用户已经注册过，获取其相应的权限
       }

       return null;

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass));
    }

    /**
     *
     * @param strUserinfo
     * @param strCredentials
     * @return
     */
    private Map<String,String> genUserInfoMap(String strUserinfo,String strCredentials){
        Map<String,String> userInfo = JsonUtil.str2obj(strUserinfo, Map.class);
        Map<String,String> credentials = JsonUtil.str2obj(strCredentials, Map.class);
        Map<String,String> userInfoMap = new HashMap<>(userInfo);
        userInfoMap.putAll(credentials);
        return userInfoMap;
    }
}
