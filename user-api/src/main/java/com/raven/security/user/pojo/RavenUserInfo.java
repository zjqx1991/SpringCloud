package com.raven.security.user.pojo;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

/**
 * 是RavenUser 对外的封装
 * 负责外界服务
 */
@Data
public class RavenUserInfo {
    private Long id;
    private String name;
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    private String permissions;

    public boolean hasPermission(String method) {
        boolean result = false;
        if (StringUtils.equalsIgnoreCase("get", method)) {
            // GET请求的用户必须要有 "r" 权限
            result = StringUtils.contains(this.permissions, "r");
        }
        else {
            // GET外其他请求的用户必须要有 "w" 权限w
            result = StringUtils.contains(this.permissions, "w");
        }
        return result;
    }
}
