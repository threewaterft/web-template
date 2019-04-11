package com.threewater.webserver.webtemplate.filter.auth;

import com.threewater.webserver.webtemplate.service.TokenAuthService;
import com.threewater.webserver.webtemplate.util.HttpClientUtil;
import com.threewater.webserver.webtemplate.util.JsonUtil;
import org.omg.CORBA.WCharSeqHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class WeChatLoginFilter extends AbstractAuthenticationProcessingFilter {
    private final static String CODE = "code";
    private final static String USERINFO = "userInfo";
    private TokenAuthService tokenAuthService;

    private static final Logger logger = LoggerFactory.getLogger(WeChatLoginFilter.class);

    /**
     * grant_type 由腾讯提供
     */
    private final static String grantType = "authorization_code";

    /**
     * app_id 由腾讯提供
     */
    static final String appId = "wx748d2c3c8cc8f692";

    /**
     * app_secrectKey 由腾讯提供
     */
    private final static String appSecretKey = "4d405c2c8f48919a7e1f0e12d6d12500";

    /**
     * 获取 OpenID 的 API 地址
     */
    private final static String openIdUri = "https://api.weixin.qq.com/sns/jscode2session";

    /**
     * 获取 token 的地址拼接
     */
    private final static String TOKEN_ACCESS_API = "%s?appid=%s&secret=%s&js_code=%s&grant_type=%s";

    /**
     * 获取 openid所在的栏位
     */
    public final static String OPEN_ID = "openid";

    /**
     * 获取session_key所在的栏位
     */
    public final static String SESSION_KEY = "session_key";


    public WeChatLoginFilter() {
        super(new AntPathRequestMatcher("/login", "POST"));
    }

    public WeChatLoginFilter(AuthenticationManager authenticationManager,TokenAuthService tokenAuthService){
        super(new AntPathRequestMatcher("/login", "POST"));
        this.setAuthenticationManager(authenticationManager);
        this.tokenAuthService = tokenAuthService;
    }

    public WeChatLoginFilter(AuthenticationManager authenticationManager){
        super(new AntPathRequestMatcher("/login", "POST"));
        this.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String code = request.getParameter(CODE);
        String userInfo = request.getParameter(USERINFO);

        logger.debug("the request param is code: {}, userInfo: {}", code, userInfo);
        String sessionAccessApi = String.format(TOKEN_ACCESS_API, openIdUri, appId, appSecretKey, code, grantType);
        String credentials = getSessionKey(sessionAccessApi);
        Map<String, Object> weChatMap= JsonUtil.str2obj(credentials, Map.class);
        logger.debug("the weChatMap is {}", weChatMap);
        if (weChatMap != null){
            String openId = (String) weChatMap.get(OPEN_ID);
            String session_key = (String) weChatMap.get(SESSION_KEY);
            if (openId != null){
                // 生成验证 authenticationToken
                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userInfo, credentials);
                // 返回验证结果
                return this.getAuthenticationManager().authenticate(authRequest);
            }
        }
        return null;
    }

    private Map<String, Object> getSession(String sessionAccessApi) throws IOException{
        logger.debug(sessionAccessApi);
        String result = HttpClientUtil.httpSendGet(sessionAccessApi);
        Map<String, Object> resMap = JsonUtil.str2obj(result, Map.class);
        return resMap;
    }

    public String getSessionKey(String sessionAccessApi){
        logger.debug(sessionAccessApi);
        return HttpClientUtil.httpSendGet(sessionAccessApi);
    }

//    // 用户成功登录后，这个方法会被调用，我们在这个方法里生成token
//    @Override
//    protected void successfulAuthentication(HttpServletRequest req,
//                                            HttpServletResponse res,
//                                            FilterChain chain,
//                                            Authentication auth) throws IOException, ServletException {
//
//
//    }
//
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
//        super.unsuccessfulAuthentication(request, response, failed);
//    }
}
