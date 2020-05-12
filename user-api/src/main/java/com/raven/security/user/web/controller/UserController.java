package com.raven.security.user.web.controller;

import com.raven.security.user.pojo.RavenUser;
import com.raven.security.user.pojo.RavenUserInfo;
import com.raven.security.user.service.IRavenUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IRavenUserService userService;

    @PostMapping("/save")
    public RavenUserInfo create(@RequestBody @Validated RavenUserInfo userInfo) {
        RavenUserInfo user = this.userService.create(userInfo);
        return user;
    }

    @GetMapping("/{id}")
    public RavenUserInfo getUserInfo(@PathVariable("id") Long id, HttpServletRequest request) {
        RavenUserInfo userInfo = this.userService.get(id);
        RavenUser user = (RavenUser) request.getAttribute("user");

        if(user == null || !user.getId().equals(id)) {
            throw new RuntimeException("身份认证信息异常，获取用户信息失败");
        }
        log.info("测试请求- " + userInfo);
        return userInfo;
    }

    @GetMapping("/test")
    public void test() {
        log.info("测试请求- test");
    }
}
