package com.threewater.webserver.webtemplate.filter.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.threewater.webserver.webtemplate.service.TokenAuthService;
import com.threewater.webserver.webtemplate.util.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class WeChatAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(WeChatAuthenticationSuccessHandler.class);

    @Autowired
    private TokenAuthService tokenAuthService;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws ServletException, IOException {
//        super.onAuthenticationSuccess(request, response, auth);
        String token = tokenAuthService.createToken(auth,false);
        response.addHeader("Authorization", token);
        //添加交易成功标志
        response.getWriter().write(objectMapper.writeValueAsString(ResultBean.getSuccessRes("登录成功！")));

    }
}
