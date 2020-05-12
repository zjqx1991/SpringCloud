package com.raven.security.user.service.impl;

import com.raven.security.user.pojo.RavenAuditLog;
import com.raven.security.user.pojo.RavenUser;
import com.raven.security.user.pojo.RavenUserInfo;
import com.raven.security.user.repository.IRavenAuditLogRepository;
import com.raven.security.user.repository.IRavenUserRepository;
import com.raven.security.user.service.IRavenAuditLogService;
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
public class RavenAuditLogServiceImpl implements IRavenAuditLogService {

    @Autowired
    private IRavenAuditLogRepository auditLogRepository;

    @Override
    public void save(RavenAuditLog auditLog) {
        if (auditLog == null) {
            log.info("保存对象不能为 null");
            throw new RuntimeException("保存对象不能为 null");
        }
        this.auditLogRepository.save(auditLog);
    }

    @Override
    public RavenAuditLog selectById(Long id) {
        if (StringUtils.isBlank(id.toString())) {
            log.info("日志id错误：" + id);
            throw new RuntimeException("日志id错误：" + id);
        }
        Optional<RavenAuditLog> auditLog = this.auditLogRepository.findById(id);
        return auditLog.get();
    }

}
