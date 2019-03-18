package com.threewater.webserver.webtemplate.filter.auth;

import com.threewater.webserver.webtemplate.exception.CommonException;
import com.threewater.webserver.webtemplate.service.TokenAuthService;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.naming.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 *  token的校验
 *  该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 *  从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 *  如果校验通过，就认为这是一个取得授权的合法请求
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {
    private TokenAuthService tokenAuthService;
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,TokenAuthService tokenAuthService) {
        super(authenticationManager);
        this.tokenAuthService = tokenAuthService;
    }

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        Authentication authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);

    }

    private Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            if(!tokenAuthService.validateToken(token))
                throw new CommonException("TWFT0006");
            return tokenAuthService.getAuthentication(token);
        }
        return null;
    }
}
