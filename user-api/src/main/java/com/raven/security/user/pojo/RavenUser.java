package com.raven.security.user.pojo;

import lombok.Data;

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
}
