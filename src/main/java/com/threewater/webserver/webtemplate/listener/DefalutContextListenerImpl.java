package com.threewater.webserver.webtemplate.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DefalutContextListenerImpl implements ServletContextListener {
    private static final Logger log = LoggerFactory.getLogger(DefalutContextListenerImpl.class);

    public void contextInitialized(ServletContextEvent sce) {
        log.info("======contextInitialized Listener========");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        log.info("======contextDestroyed Listener========");
    }


}
