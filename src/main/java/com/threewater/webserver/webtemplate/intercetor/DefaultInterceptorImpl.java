package com.threewater.webserver.webtemplate.intercetor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DefaultInterceptorImpl implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(DefaultInterceptorImpl.class);
    /**
00     * 进入controller方法之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        log.info("LoginIntercepter------->preHandle");

        String token = request.getHeader("access_token");

//		response.getWriter().print("fail");

//        return false;
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    /**
     * 调用完controller之后，视图渲染之前。如果控制器Controller出现了异常，则不会执行此方法
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        log.info("LoginIntercepter------->postHandle");

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 整个完成之后，不管有没有异常，这个afterCompletion都会被调用，通常用于资源清理
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        log.info("LoginIntercepter------->afterCompletion");

        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
