package com.raven.security.user.config;


import com.raven.security.user.web.interceptor.RavenAclInterceptor;
import com.raven.security.user.web.interceptor.RavenAuditLogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 拦截器配置类
 */
@Configuration
@EnableJpaAuditing
public class RavenInterceptorConfig implements WebMvcConfigurer {

    /**
     * 审核日志拦截器
     */
    @Autowired
    private RavenAuditLogInterceptor auditLogInterceptor;
    /**
     * ACL权限拦截器
     */
    @Autowired
    private RavenAclInterceptor aclInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * registry.addInterceptor(this.auditLogInterceptor).addPathPatterns("/user");
         * 拦截器指定拦截的url
         *
         * 拦截器的添加顺序决定了拦截的先后顺序
         */
        registry.addInterceptor(this.auditLogInterceptor);
        registry.addInterceptor(this.aclInterceptor);
    }
}
