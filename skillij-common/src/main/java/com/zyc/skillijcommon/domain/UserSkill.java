package com.zyc.skillijcommon.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created on 2018/3/27.
 * Author: Gordon
 * Email: biggordon@163.com
 */
@Entity
@Table(name = "user_skill")
public class UserSkill implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "skill_name", columnDefinition = "VARCHAR(200) NOT NULL COMMENT '技能名称'")
    private String skillName;

    @Column(name = "proficiency", columnDefinition = "INT(4) NOT NULL DEFAULT '0' " +
            "COMMENT '技能熟练度，0没听过，1听过，2了解，3熟悉，4掌握，5精通，6有创新'")
    private Integer proficiency;

    @Column(name = "description", columnDefinition = "VARCHAR(200) DEFAULT NULL COMMENT '技能掌握描述'")
    private String description;

    @Column(name = "user_id", columnDefinition = "BIGINT(20) NOT NULL COMMENT '技能的掌握人id'")
    private Long userId;

    @Column(name = "tree_id", columnDefinition = "BIGINT(20) NOT NULL COMMENT '所属技能树id'")
    private Long treeId;

    @Column(name = "parent_id", columnDefinition = "BIGINT(20) NOT NULL COMMENT '父技能的id，无为0'")
    private Long parentId;

    @Column(name = "level", columnDefinition = "BIGINT(20) NOT NULL COMMENT '该技能位于树的级层'")
    private Long level;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getProficiency() {
        return proficiency;
    }

    public void setProficiency(Integer proficiency) {
        this.proficiency = proficiency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }
}
