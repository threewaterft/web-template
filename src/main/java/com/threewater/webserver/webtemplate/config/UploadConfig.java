package com.threewater.webserver.webtemplate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Configuration
public class UploadConfig {
    @Value("${custom.upload.maxFileSize: 10}")
    private long maxFileSize;
    @Value("${custom.upload.maxReqSize: 100}")
    private long maxReqSize;
    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //设置单个文件最大值
        factory.setMaxFileSize(DataSize.ofMegabytes(maxFileSize));
        //设置一次性上传文件总大小
        factory.setMaxRequestSize(DataSize.ofMegabytes(maxReqSize));
        return factory.createMultipartConfig();
    }
}
