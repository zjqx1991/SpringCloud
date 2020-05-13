package com.raven.security.user.pojo;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

/**
 * 负责与数据库对应
 */
@Data
@Entity
@Table(name = "t_user")
public class RavenUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String username;
    private String password;

    private String permissions;

    public RavenUserInfo builderUserInfo() {
        RavenUserInfo userInfo = new RavenUserInfo();
        BeanUtils.copyProperties(this, userInfo);
        return userInfo;
    }

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
