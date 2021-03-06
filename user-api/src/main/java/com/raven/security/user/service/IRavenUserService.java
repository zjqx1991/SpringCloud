package com.raven.security.user.service;

import com.raven.security.user.pojo.RavenUserInfo;

import java.util.List;

public interface IRavenUserService {

    RavenUserInfo create(RavenUserInfo userInfo);

    RavenUserInfo update(RavenUserInfo userInfo);

    void delete(Long id);

    RavenUserInfo get(Long id);

    RavenUserInfo getByName(String name);

    List<RavenUserInfo> query(String name);

    RavenUserInfo login(RavenUserInfo userInfo);
}
