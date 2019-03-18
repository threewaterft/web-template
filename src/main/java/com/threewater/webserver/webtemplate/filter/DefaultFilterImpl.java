package com.threewater.webserver.webtemplate.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DefaultFilterImpl implements Filter {
    private static final Logger log = LoggerFactory.getLogger(DefaultFilterImpl.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init DefaultFilter...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        log.info("doFilter...");
//        HttpServletRequest req = (HttpServletRequest)servletRequest;
//        HttpServletResponse res = (HttpServletResponse)servletResponse;
//
//        String token = req.getHeader("access_token");
//
//        log.info("access_token is "+token);
//
//        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        log.info("destroy default Filter...");
    }

}
