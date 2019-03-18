package com.threewater.webserver.webtemplate.service;

import org.springframework.security.core.Authentication;

public interface TokenAuthService {
    /**
     * 创建token
     * @param authentication
     * @param rememberMe
     * @return
     */
    public String createToken(Authentication authentication, Boolean rememberMe);

    /**
     * 获取用户权限
     * @param token
     * @return
     */
    public Authentication getAuthentication(String token);

    /**
     * 验证token
     * @param token
     * @return
     */
    public boolean validateToken(String token);
}
