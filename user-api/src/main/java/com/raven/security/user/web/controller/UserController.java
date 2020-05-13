package com.raven.security.user.web.controller;

import com.raven.security.user.pojo.RavenUserInfo;
import com.raven.security.user.service.IRavenUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IRavenUserService userService;

    @GetMapping("/login")
    public void login(@Validated RavenUserInfo userInfo, HttpServletRequest request) {
        RavenUserInfo user = this.userService.login(userInfo);
        log.info(user.toString());
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 把已存在的session过期
            session.invalidate();
        }
        // 保证每一次登录都是一个新的session
        request.getSession(true).setAttribute("user", user);
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    @PostMapping("/save")
    public RavenUserInfo create(@RequestBody @Validated RavenUserInfo userInfo) {
        RavenUserInfo user = this.userService.create(userInfo);
        return user;
    }

    @GetMapping("/{id}")
    public RavenUserInfo getUserInfo(@PathVariable("id") Long id, HttpServletRequest request) {
        RavenUserInfo userInfo = this.userService.get(id);
        RavenUserInfo user = (RavenUserInfo) request.getSession().getAttribute("user");

        if(user == null || !user.getId().equals(id)) {
            log.info("身份认证信息异常，获取用户信息失败");
            throw new RuntimeException("身份认证信息异常，获取用户信息失败");
        }
        log.info("测试请求- " + userInfo);
        return userInfo;
    }

    @GetMapping("/test")
    public void test() {
        log.info("测试请求- test");
    }

    @PostMapping("/pst")
    public void test2() {
        log.info("测试请求- post");
    }
}
