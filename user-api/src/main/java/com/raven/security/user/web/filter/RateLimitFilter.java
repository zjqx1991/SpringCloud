package com.raven.security.user.web.filter;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 限流过滤器
 */
@Slf4j
@Order(1)
@Component
public class RateLimitFilter extends OncePerRequestFilter {

    /**
     * 限制1秒只能通过1个请求
     */
    private RateLimiter rateLimiter = RateLimiter.create(1);

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        logger.info("1-限流过滤器");
        if (this.rateLimiter.tryAcquire()) {
            filterChain.doFilter(request, response);
        }
        else {
            // 限流
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("Too Many Request !!!");
            response.getWriter().flush();
            return;
        }
    }
}
