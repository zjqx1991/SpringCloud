package com.raven.security.user.service.impl;

import com.raven.security.user.pojo.RavenUser;
import com.raven.security.user.pojo.RavenUserInfo;
import com.raven.security.user.repository.IRavenUserRepository;
import com.raven.security.user.service.IRavenUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RavenUserServiceImpl implements IRavenUserService {

    @Autowired
    private IRavenUserRepository userRepository;


    public RavenUserInfo create(RavenUserInfo userInfo) {
        RavenUser user = new RavenUser();
        BeanUtils.copyProperties(userInfo, user);
        this.userRepository.save(user);
        userInfo.setId(user.getId());
        return userInfo;
    }

    public RavenUserInfo update(RavenUserInfo userInfo) {
        return null;
    }

    public void delete(Long id) {

    }

    public RavenUserInfo get(Long id) {
        Optional<RavenUser> optional = this.userRepository.findById(id);
        RavenUserInfo userInfo = optional.get().builderUserInfo();
        return userInfo;
    }

    @Override
    public RavenUserInfo getByName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new RuntimeException("参数错误");
        }
        RavenUser user = this.userRepository.findByName(name);
        RavenUserInfo userInfo = new RavenUserInfo();
        BeanUtils.copyProperties(user, userInfo);
        return userInfo;
    }

    public List<RavenUserInfo> query(String name) {
        return null;
    }

    @Override
    public RavenUserInfo login(RavenUserInfo userInfo) {
        if (null == userInfo) {
            log.info("登录请求参数有问题");
            throw new RuntimeException("登录请求参数有问题");
        }
        RavenUser user = this.userRepository.findByName(userInfo.getUsername());
        if (user == null || !user.getPassword().equals(userInfo.getPassword())) {
            log.info("用户名或密码错误");
            throw new RuntimeException("用户名或密码错误");
        }
        return user.builderUserInfo();
    }
}
