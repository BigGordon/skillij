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
@Table(name = "skillij_role")
public class SkillijRole implements Serializable {

    @Id
    @GeneratedValue
    private Long id; // 编号

    private String role; // 角色标识程序中判断使用,如"admin",这个是唯一的:

    private String description; // 角色描述,UI界面显示使用

    private Boolean available = Boolean.TRUE; // 是否可用,如果不可用将不会添加给用户

    //角色 -- 权限关系：多对多关系;
    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name="SkillijRolePermission",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="permissionId")})
    private List<SkillijPermission> permissions;

    // 用户 - 角色关系定义;
    @ManyToMany
    @JoinTable(name="SkillijUserRole",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="userId")})
    private List<SkillijUser> users;// 一个角色对应多个用户

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<SkillijPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SkillijPermission> permissions) {
        this.permissions = permissions;
    }

    public List<SkillijUser> getUsers() {
        return users;
    }

    public void setUsers(List<SkillijUser> users) {
        this.users = users;
    }
}
