package com.threewater.webserver.webtemplate.exception;

import org.springframework.beans.factory.InitializingBean;

import java.util.Locale;

public class CommunicationStaticsParam implements InitializingBean {
    /**default Locale*/
    public static Locale defaultLocal = new Locale("zh", "CN");

    public String  language = "zh";

    public String region = "CN";

    public CommunicationStaticsParam() {
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void afterPropertiesSet() throws Exception {
        defaultLocal = new Locale(this.language, this.region);
    }
}
