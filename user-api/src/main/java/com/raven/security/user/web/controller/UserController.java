package com.raven.security.user.web.controller;

import com.raven.security.user.pojo.RavenUserInfo;
import com.raven.security.user.service.IRavenUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IRavenUserService userService;

    @PostMapping("/save")
    public RavenUserInfo create(@RequestBody RavenUserInfo userInfo) {
        RavenUserInfo user = this.userService.create(userInfo);
        return user;
    }

    @GetMapping("/test")
    public void test() {
        log.info("测试请求- test");
    }
}
