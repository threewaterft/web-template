package com.threewater.webserver.webtemplate.controller;

import com.threewater.webserver.webtemplate.exception.CommonException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {
    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }

    @RequestMapping("/error")
    public Object error(){
        throw new CommonException("TWFT0004","测试错误");
    }
}
