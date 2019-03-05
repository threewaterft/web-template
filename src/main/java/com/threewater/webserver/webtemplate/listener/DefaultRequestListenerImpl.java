package com.threewater.webserver.webtemplate.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DefaultRequestListenerImpl implements ServletRequestListener {
    private static final Logger log = LoggerFactory.getLogger(DefaultRequestListenerImpl.class);
    @Override
    public void requestDestroyed(ServletRequestEvent sre){
        log.info("requestDestroyed Listener");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        log.info("requestInitialized Listener");
    }

}
