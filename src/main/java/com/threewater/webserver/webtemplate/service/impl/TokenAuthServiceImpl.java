package com.threewater.webserver.webtemplate.service.impl;

import com.threewater.webserver.webtemplate.service.TokenAuthService;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class TokenAuthServiceImpl implements TokenAuthService {
    private static final Logger logger = LoggerFactory.getLogger(TokenAuthServiceImpl.class);
    private static final String AUTHORITIES_KEY = "auth";
    private static final String USERID = "userId";
    private String secretKey;//签名密钥
    private long tokenValidityInMilliseconds;//失效日期
    private long tokenValidityInMillisecondsForRememberMe;//（记住我）失效日期
    private String tokenPrefix;
    @PostConstruct
    public void init() {
        this.secretKey = "threewaterdotcom";
        int secondIn1day = 1000 * 60 * 60 * 24;//一天
        this.tokenValidityInMilliseconds = secondIn1day / 24;//一小时
        this.tokenValidityInMillisecondsForRememberMe = secondIn1day * 7L;//7天
        this.tokenPrefix="Bearer ";
    }
    @Override
    public String createToken(Authentication authentication, Boolean rememberMe) {
        String authorities = authentication.getAuthorities().stream()//获取用户的权限字符串，如 USER,ADMIN
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();//获取当前时间戳
        Date validity;//存放过期时间
        if (rememberMe){
            validity = new Date(now + this.tokenValidityInMilliseconds);
        }else {
            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
        }

        return tokenPrefix+Jwts.builder()                                   //创建Token令牌
                .setSubject(authentication.getName())           //设置面向用户
                .claim(AUTHORITIES_KEY,authorities)             //添加权限属性
                .claim(USERID, ((User)authentication.getPrincipal()).getPassword())
                .setExpiration(validity)                        //设置失效时间
                .signWith(SignatureAlgorithm.HS512,secretKey)   //生成签名
                .compact();
    }

    @Override
    public Authentication getAuthentication(String token) {
        token = token.replace(tokenPrefix,"");
        logger.debug("token:"+token);
        Claims claims = Jwts.parser()//解析Token的payload
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))//获取用户权限字符串
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());//将元素转换为GrantedAuthority接口集合
        User principal = new User(claims.getSubject(), "", authorities);
        String userId = claims.get(USERID).toString();
        return new UsernamePasswordAuthenticationToken(principal, userId, authorities);
    }

    @Override
    public boolean validateToken(String token) {
        token = token.replace(tokenPrefix,"");
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);   //通过密钥验证Token
            return true;
        }catch (SignatureException e) {                                     //签名异常
            logger.info("Invalid JWT signature.");
            logger.trace("Invalid JWT signature trace: {}", e);
        } catch (MalformedJwtException e) {                                 //JWT格式错误
            logger.info("Invalid JWT token.");
            logger.trace("Invalid JWT token trace: {}", e);
        } catch (ExpiredJwtException e) {                                   //JWT过期
            logger.info("Expired JWT token.");
            logger.trace("Expired JWT token trace: {}", e);
        } catch (UnsupportedJwtException e) {                               //不支持该JWT
            logger.info("Unsupported JWT token.");
            logger.trace("Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e) {                              //参数错误异常
            logger.info("JWT token compact of handler are invalid.");
            logger.trace("JWT token compact of handler are invalid trace: {}", e);
        }
        return false;
    }
}
