package com.threewater.webserver.webtemplate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(scanBasePackages = "com.threewater.webserver.webtemplate")
@MapperScan("com.threewater.webserver.webtemplate.mapper")
@ServletComponentScan
public class WebTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebTemplateApplication.class, args);
    }

}
