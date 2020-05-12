package com.raven.security.user.pojo;

import lombok.Data;
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
    private String username;
    private String password;

    public RavenUserInfo builderUserInfo() {
        RavenUserInfo userInfo = new RavenUserInfo();
        BeanUtils.copyProperties(this, userInfo);
        return userInfo;
    }
}
