package com.raven.security.user.service;

import com.raven.security.user.pojo.RavenAuditLog;
import com.raven.security.user.pojo.RavenUserInfo;

import java.util.List;

public interface IRavenAuditLogService {

    void save(RavenAuditLog auditLog);

    RavenAuditLog selectById(Long id);

}
