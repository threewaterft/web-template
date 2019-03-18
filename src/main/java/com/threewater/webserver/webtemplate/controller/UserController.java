package com.threewater.webserver.webtemplate.controller;

import com.threewater.webserver.webtemplate.service.SysUserService;
import com.threewater.webserver.webtemplate.util.ResultBean;
import com.threewater.webserver.webtemplate.vo.SysUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/hello")
    public String hello() {
        logger.info("come in hello");
        return "hello";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping("/test1")
    public String test1() {
        return "test1";
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping("/test2")
    public String test2() {
        return "test2";
    }

    @PostMapping("/register")
    public ResultBean signUp(@RequestBody SysUserVo user) {
        logger.info(user.toString());
        if(sysUserService.insertUser(user))
            return ResultBean.getSuccessRes(user.getName());
        else
            return ResultBean.getDefaultFailRes();
    }

}