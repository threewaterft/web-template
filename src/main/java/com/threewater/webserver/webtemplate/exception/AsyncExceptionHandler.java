package com.threewater.webserver.webtemplate.exception;

import com.threewater.webserver.webtemplate.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(AsyncExceptionHandler.class);
    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        log.info("Async method: {} has uncaught exception,params:{}", method.getName(), JsonUtil.obj2str(params));
        log.error(ex.getMessage());
    }
}
