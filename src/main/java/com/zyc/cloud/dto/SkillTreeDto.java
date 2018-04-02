package com.zyc.cloud.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created on 2018/4/2.
 * Author: Gordon
 * Email: biggordon@163.com
 */
public class SkillTreeDto implements Serializable {

    /**
     * 该技能树节点的名字
     */
    private String name;

    /**
     * 该技能树节点的父节点id
     */
    private Long parentId;

    /**
     * 该节点的子类
     */
    private List<SkillTreeDto> children;

    public SkillTreeDto(String nodeName) {
        name = nodeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SkillTreeDto> getChildren() {
        return children;
    }

    public void setChildren(List<SkillTreeDto> children) {
        this.children = children;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
