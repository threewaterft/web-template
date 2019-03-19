package com.threewater.webserver.webtemplate.util;

import com.threewater.webserver.webtemplate.exception.CommonException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
//@ConfigurationProperties(prefix = "custom.jwt")
public class JwtUtil {
    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${custom.jwt.secret:secret-three}")
    private String secret;
    @Value("${custom.jwt.expire:3600}")
    private long expire;

    /**
     * 生成jwt token
     */
    public String generateToken(Map<String, ?> infoMap) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);

        Map<String, Object> map = new HashMap();

        map.put("data", infoMap);
        String userId = (String)infoMap.get("userId");

        return "Bearer " + Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(map)//一定要放在最前面
                .setSubject(userId)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims getClaimByToken(String token) {
        Claims body = null;
        try {
            body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token.replace("Bearer ",""))
                    .getBody();
        }catch (Exception e){
            log.debug("validate is token error ", e);
            throw new CommonException("TWFT0003", "token解析异常", e);
        }
        return body;
    }

    /**
     * token是否过期
     * @return  true：过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }

    public static void main(String[] args){
        JwtUtil jwt = new JwtUtil();
        Map<String, Object> map = new HashMap<>();
        map.put("userId", "testUser");
        map.put("testData", "data");
        String str = jwt.generateToken(map);
        System.out.println(str);
        System.out.println(jwt.getClaimByToken(str));
        System.out.println(jwt.isTokenExpired(jwt.getClaimByToken(str).getExpiration()));
    }

}
