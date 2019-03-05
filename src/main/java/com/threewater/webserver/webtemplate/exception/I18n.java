package com.threewater.webserver.webtemplate.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Component("customI18n")
@Lazy(false)
public class I18n implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(I18n.class);

    @Value("${spring.messages.basename}")
    private String basename;
    @Value("${spring.messages.cache-duration}")
    private long cacheMillis;
    @Value("${spring.messages.encoding}")
    private String encoding;

    private MessageSource messageSource;

    @PostConstruct
    public void init() {
        this.messageSource = this.initMessageSource();
    }

    private MessageSource initMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(basename);
        messageSource.setDefaultEncoding(encoding);
        messageSource.setCacheMillis(cacheMillis);
        return messageSource;
    }

    public  String getMessage(String code, Object[] params, Locale locale) {
        return messageSource.getMessage(code, params, locale);
    }

    public  String getMessage(String code, Object[] params, String defaultMessage, Locale locale) {
        return messageSource.getMessage(code, params, defaultMessage, locale);
    }

    public  String getMessage(MessageSourceResolvable resolvable, Locale locale) {
        return messageSource.getMessage(resolvable, locale);
    }

    public  String getMessage(String code, Object[] params) {
        try {
            Locale locale = LocaleContextHolder.getLocale();
            return messageSource.getMessage(code, params, locale);
        } catch (Exception e) {
            logger.error("parse message error! ", e);
        }
        return "";
    }

    @Override
    public void afterPropertiesSet() throws CommonException {
        if (messageSource == null) {
            throw new CommonException("XTLF302400AA", new Object[0]);
        }
    }
}
