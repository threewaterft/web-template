package com.threewater.webserver.webtemplate.exception;

import com.threewater.webserver.webtemplate.util.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.NoSuchMessageException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class CommonException extends RuntimeException {

    private static final long serialVersionUID = 8130867442394786976L;
    private static final Logger logger = LoggerFactory.getLogger(CommonException.class);
    public static final String log001 = "无法找到异常信息，code={0}，locale={1}";
    private String code;
    private Object[] params;
    private List<Locale> locales;

    private static final I18n i18n = (I18n) SpringContextUtil.getBean("customI18n");

    public CommonException(String code, Throwable e, List<Locale> locales, Object... params) {
        super(e);
        this.code = code;
        this.params = params;
        this.locales = locales;
    }

    public CommonException(String code, Throwable e, Object... params) {
        this(code, e, null, params);
    }

    public CommonException(String code, Object... params) {
        this(code, null, params);
    }

    public CommonException(Throwable e) {
        this(null, e);
    }

    public CommonException() {
    }

    public String getMessage() {
        return this.assembleMessage(this.getLogMessage());
    }

    public String getLocalizedMessage() {
        if (CollectionUtils.isEmpty(this.locales)) {
            return this.getLocalizedExceptionMessage(CommunicationStaticsParam.defaultLocal);
        } else {
            Iterator var2 = this.locales.iterator();

            while (var2.hasNext()) {
                Locale locale = (Locale) var2.next();
                if (locale != null) {
                    String exceptionMessage = this.getLocalizedExceptionMessage(locale);
                    if (StringUtils.hasText(exceptionMessage)) {
                        return this.assembleMessage(exceptionMessage);
                    }
                }
            }
            return this.getMessage();
        }
    }

    private String getLocalizedExceptionMessage(Locale locale) {
        if (!StringUtils.hasText(this.getCode())) {
            return null;
        } else {
            try {
                return i18n.getMessage(this.getCode(), this.getParameters(), locale);
            } catch (NoSuchMessageException var4) {
                logger.error("log001", var4, new Object[]{this.getCode(), locale});
            }
            return null;
        }
    }

    private String assembleMessage(String exceptionMessage) {
        return "[ERRORCODE=" + this.getCode() + "] [" + (StringUtils.hasText(exceptionMessage) ? exceptionMessage : "") + ']';
    }

    private String getLogMessage() {
        return i18n.getMessage(this.getCode(), this.params);
    }

    public String getCode() {
        return this.code;
    }

    public Object[] getParameters() {
        return this.params;
    }

    public List<Locale> getLocales() {
        return this.locales;
    }

    public void setLocales(List<Locale> locales) {
        this.locales = locales;
    }

    public String toString() {
        String s = this.getClass().getName();
        String message = this.getLogMessage();
        StringBuilder sb = (new StringBuilder(s)).append("[ERRORCODE=").append(this.getCode()).append("] [").append(message == null ? "" : message).append("]");
        return sb.toString();
    }
}
