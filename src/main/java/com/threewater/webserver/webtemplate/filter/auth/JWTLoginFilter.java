package com.threewater.webserver.webtemplate.filter.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.threewater.webserver.webtemplate.service.TokenAuthService;
import com.threewater.webserver.webtemplate.vo.LoginUserVo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * * 验证用户名密码正确后，生成一个token，并将token返回给客户端
 *  该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法
 *  attemptAuthentication ：接收并解析用户凭证。
 *  successfulAuthentication ：用户成功登录后，这个方法会被调用，我们在这个方法里生成token。
 */
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private TokenAuthService tokenAuthService;

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public JWTLoginFilter(AuthenticationManager authenticationManager,TokenAuthService tokenAuthService) {
        this.authenticationManager = authenticationManager;
        this.tokenAuthService = tokenAuthService;
    }
    // 接收并解析用户凭证
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            LoginUserVo user = new ObjectMapper()
                    .readValue(req.getInputStream(), LoginUserVo.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 用户成功登录后，这个方法会被调用，我们在这个方法里生成token
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String token = tokenAuthService.createToken(auth,false);
        res.addHeader("Authorization", "Bearer " + token);
    }
}
