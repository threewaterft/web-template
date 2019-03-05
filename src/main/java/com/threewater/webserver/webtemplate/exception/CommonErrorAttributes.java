package com.threewater.webserver.webtemplate.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class CommonErrorAttributes extends DefaultErrorAttributes {
    private static final Logger logger = LoggerFactory.getLogger(CommonErrorAttributes.class);
    private static final String log001 = "路径{}调用异常，错误码:{}，错误信息：{}!";

    private static final String SYS_TX_STATUS_FAIL = "01";

    private static final String STATUS = "status";

    private static final String CODE = "code";

    private static final String MSG = "msg";

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        Throwable error = this.getError(webRequest);
        if (error != null) {
            while(true) {
                if (!(error instanceof CommonException) || error.getCause() == null) {
                    break;
                }
                error = error.getCause();
            }
            if(error instanceof CommonException){
                CommonException cex = (CommonException)error;
                this.addCustomerAttributes(errorAttributes, cex);
            }else{
                this.addCustomerAttributes(errorAttributes);
            }
        }else{
            this.addCustomerAttributes(errorAttributes);
        }

        return errorAttributes;
    }

    private void addCustomerAttributes(Map<String, Object> errorAttributes){
        this.addCustomerAttributes(errorAttributes, null);
    }

    private void addCustomerAttributes(Map<String, Object> errorAttributes, CommonException cex){
        if(cex == null){
            cex = new CommonException("TWFT0005");
        }
        logger.info(log001, errorAttributes.get("path"), cex.getCode(), cex.getLocalizedMessage());
        errorAttributes.put(CODE, cex.getCode());
        errorAttributes.put(STATUS, SYS_TX_STATUS_FAIL);
        errorAttributes.put(MSG, cex.getLocalizedMessage());
    }
}
