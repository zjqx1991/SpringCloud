package com.raven.security.user.repository;

import com.raven.security.user.pojo.RavenAuditLog;
import com.raven.security.user.pojo.RavenUser;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface IRavenAuditLogRepository extends JpaSpecificationExecutor<RavenAuditLog>, CrudRepository<RavenAuditLog, Long> {

}
