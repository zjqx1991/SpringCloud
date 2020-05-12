package com.raven.security.user.web.interceptor;

import com.raven.security.user.pojo.RavenAuditLog;
import com.raven.security.user.pojo.RavenUser;
import com.raven.security.user.service.IRavenAuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 日志拦截器
 */
@Component
public class RavenAuditLogInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private IRavenAuditLogService auditLogService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        RavenAuditLog log = new RavenAuditLog();
        log.setMethod(request.getMethod());
        log.setPath(request.getRequestURI());

        RavenUser user = (RavenUser) request.getAttribute("user");
        if (user != null) {
            log.setUsername(user.getUsername());
        }

        // 保存日志
        this.auditLogService.save(log);

        request.setAttribute("auditLogId", log.getId());

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        Long auditLogId = (Long) request.getAttribute("auditLogId");
        RavenAuditLog auditLog = this.auditLogService.selectById(auditLogId);
        auditLog.setStatus(response.getStatus());
        // 保存日志
        this.auditLogService.save(auditLog);
    }
}
