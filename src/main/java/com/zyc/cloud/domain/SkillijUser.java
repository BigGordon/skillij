package com.zyc.cloud.domain;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created on 2018/2/2.
 * Author: Gordon
 * Email: biggordon@163.com
 */
@Entity
@Table(name = "skillij_user")
public class SkillijUser implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "username", columnDefinition = "VARCHAR(200) NOT NULL COMMENT '用户名称'")
    private String username;

    @Column(name = "password", columnDefinition = "VARCHAR(200) NOT NULL COMMENT '用户密码'")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
