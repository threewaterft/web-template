package com.threewater.webserver.webtemplate.service.service.impl;

import com.threewater.webserver.webtemplate.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service("logService")
public class DefaultLogServiceImpl implements LogService {
    private static final Logger log = LoggerFactory.getLogger(DefaultLogServiceImpl.class);

    @Async
    public void insertLog(HttpServletRequest request) {
        log.debug("交易进入时插入数据库记录");
    }

    @Async
    public void updateLog(HttpServletRequest request) {
        log.debug("交易完成后更新数据库记录");
    }

}
