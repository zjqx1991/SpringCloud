package com.raven.security.user.web.interceptor;

import com.raven.security.user.config.RavenAllowsUrlConfig;
import com.raven.security.user.pojo.RavenUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * ACL权限拦截器
 */
@Component
public class RavenAclInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RavenAllowsUrlConfig allowsUrlConfig;

    /**
     * 请求前缀拦截处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = true;

        if (this.allowsUrlConfig.getAllowList().contains(request.getRequestURI())) {
            return result;
        }

        // 获取用户信息
        RavenUserInfo userInfo = (RavenUserInfo) request.getSession().getAttribute("user");

        if (null == userInfo) {
            // 用户未认证
            response.setContentType("text/plain");
            response.getWriter().write("need authentication");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            result = false;
        }
        else {
            // 用户没有权限
            String method = request.getMethod();
            if (!userInfo.hasPermission(method)) {
                response.setContentType("text/plain");
                response.getWriter().write("forbidden");
                response.setStatus(HttpStatus.FORBIDDEN.value());
                result = false;
            }
        }
        return result;
    }
}
