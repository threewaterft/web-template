package com.threewater.webserver.webtemplate.exception;

import com.threewater.webserver.webtemplate.util.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);

    private static final String SYS_TX_STATUS_FAIL = "01";

    @ExceptionHandler(value = CommonException.class)
    public ResponseEntity<ResultBean> handleServiceError(CommonException ex) {
        logger.info("服务异常，ex:" + ex.getCode());
        return new ResponseEntity<>(ResultBean.getFailRes(SYS_TX_STATUS_FAIL, ex.getCode(), ex.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ResultBean> handleError(Exception ex) {
        ex.printStackTrace();
        System.out.println(ex.getMessage());
        logger.info("服务异常，ex:" + ex.getLocalizedMessage());
        return new ResponseEntity<>(ResultBean.getDefaultFailRes(), HttpStatus.BAD_REQUEST);
    }
}