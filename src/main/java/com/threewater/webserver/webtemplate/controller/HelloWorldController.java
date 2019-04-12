package com.threewater.webserver.webtemplate.controller;

import com.threewater.webserver.webtemplate.exception.CommonException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {
    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }

    @RequestMapping("/hellouser")
    @Secured(value = {"ROLE_USER"})
    public String helloUser(@RequestBody Map<String, Object> jsonData){ return "hello user" + jsonData.get("msg");}

    @RequestMapping("/helloguest")
    @Secured(value = {"ROLE_GUEST"})
    public String helloGuest(@RequestBody Map<String, Object> jsonData){ return "hello guest" + jsonData.get("msg");}

    @RequestMapping("/error")
    public Object error(){
        throw new CommonException("TWFT0004","测试错误");
    }
}
