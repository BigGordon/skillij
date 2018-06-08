package com.zyc.cloud.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created on 2018/5/3.
 * Author: Gordon
 * Email: biggordon@163.com
 */
@Entity
@Table(name = "skillij_permission")
public class SkillijPermission implements Serializable {
    @Id
    @GeneratedValue
    private Long id;//主键.
    private String permission;//名称.
    private Boolean available = Boolean.TRUE;// 是否可用,如果不可用将不会添加给用户

    @ManyToMany
    @JoinTable(name="SkillijRolePermission",joinColumns={@JoinColumn(name="permissionId")},inverseJoinColumns={@JoinColumn(name="roleId")})
    private List<SkillijRole> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<SkillijRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SkillijRole> roles) {
        this.roles = roles;
    }
}
