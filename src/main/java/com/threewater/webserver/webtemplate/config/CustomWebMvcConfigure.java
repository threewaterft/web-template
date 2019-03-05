package com.threewater.webserver.webtemplate.config;

import com.threewater.webserver.webtemplate.intercetor.DefaultInterceptorImpl;
import com.threewater.webserver.webtemplate.intercetor.LogInterceptor;
import com.threewater.webserver.webtemplate.util.SpringContextUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomWebMvcConfigure implements WebMvcConfigurer{

    @Bean("logInterceptor")
    public LogInterceptor getLogInterceptor(){
        return new LogInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //路径拦截用* 拦截路径最后需要用** 拦截器是先注册，先被拦截
//        registry.addInterceptor(new DefaultInterceptorImpl()).addPathPatterns("/hello/*/**");
        //.excludePathPatterns("/api2/xxx/**"); //拦截全部 /*/*/**

        /**
         * 注册日志记录拦截器
         */
        registry.addInterceptor(getLogInterceptor()).addPathPatterns("/hello/**");

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
