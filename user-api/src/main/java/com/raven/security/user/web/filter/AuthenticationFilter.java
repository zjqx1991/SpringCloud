package com.raven.security.user.web.filter;

import com.raven.security.user.pojo.RavenUser;
import com.raven.security.user.repository.IRavenUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 请求认证过滤器
 */
@Slf4j
@Order(2)
@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private IRavenUserRepository userRepository;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("2-认证过滤器");
        String header = request.getHeader("Authorization");

        if (StringUtils.isNotBlank(header)) {
            String token64 = StringUtils.substringAfter(header, "Basic ");
            String token = new String(Base64Utils.decodeFromString(token64));
            String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(token, ":");

            if (items.length != 2) {
                log.info("用户身份认证错误！！！");
                throw new RuntimeException("用户身份认证错误！！！");
            }
            String username = items[0];
            String password = items[1];
            RavenUser user = this.userRepository.findByName(username);
            if (user != null && user.getPassword().equals(password)) {
                request.setAttribute("user", user);
            }
        }
        filterChain.doFilter(request, response);
    }
}
