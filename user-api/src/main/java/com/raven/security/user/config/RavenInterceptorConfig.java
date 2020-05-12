package com.raven.security.user.config;


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

    @Autowired
    private RavenAuditLogInterceptor auditLogInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * registry.addInterceptor(this.auditLogInterceptor).addPathPatterns("/user");
         * 拦截器指定拦截的url
         */
        registry.addInterceptor(this.auditLogInterceptor);
    }
}
