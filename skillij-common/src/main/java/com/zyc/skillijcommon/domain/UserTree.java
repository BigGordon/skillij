package com.zyc.skillijcommon.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 描述:
 * 用户技能树列表
 *
 * @author Van
 * @create 2018-07-12 下午7:26
 */
@Entity
@Table(name = "user_tree")
public class UserTree implements Serializable {

    @Id
    @GeneratedValue
    private Long treeId;

    @Column(name = "user_id", columnDefinition = "BIGINT(20) NOT NULL COMMENT '用户id'")
    private Long userId;

    @Column(name = "tree_name", columnDefinition = "VARCHAR(200) NOT NULL COMMENT '用户的技能树名'")
    private String treeName;


    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }
}

