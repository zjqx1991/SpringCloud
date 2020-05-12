package com.raven.security.user.pojo;

import lombok.Data;

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
}
