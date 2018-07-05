package com.zyc.skillijcommon.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

    @Column(name = "mail", columnDefinition = "VARCHAR(200) NOT NULL COMMENT '用户邮箱'")
    private String mail;

//    @Column(name = "salt", columnDefinition = "VARCHAR(200) NOT NULL COMMENT '加密密码的盐'")
//    private String salt;//加密密码的盐

//    @Column(name = "state", columnDefinition = "INT(4) NOT NULL DEFAULT '1' " +
//            "COMMENT '用户账户状态，0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定'")
//    private Integer state;

    @ManyToMany(fetch= FetchType.EAGER)//立即从数据库中进行加载数据;
    @JoinTable(name = "SkillijUserRole", joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns ={@JoinColumn(name = "roleId") })
    private List<SkillijRole> roles;// 一个用户具有多个角色

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMail() { return mail; }

    public void setMail(String mail) { this.mail = mail; }

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

//    public Integer getState() {
//        return state;
//    }
//
//    public void setState(Integer state) {
//        this.state = state;
//    }

//    public String getSalt() {
//        return salt;
//    }
//
//    public void setSalt(String salt) {
//        this.salt = salt;
//    }

    public List<SkillijRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SkillijRole> roles) {
        this.roles = roles;
    }
}
