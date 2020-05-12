package com.raven.security.user.service.impl;

import com.raven.security.user.pojo.RavenUser;
import com.raven.security.user.pojo.RavenUserInfo;
import com.raven.security.user.repository.IRavenUserRepository;
import com.raven.security.user.service.IRavenUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
        return null;
    }

    public List<RavenUserInfo> query(String name) {
        return null;
    }
}
