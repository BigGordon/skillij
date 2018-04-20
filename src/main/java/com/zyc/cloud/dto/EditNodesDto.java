package com.zyc.cloud.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created on 2018/4/18.
 * Author: Gordon
 * Email: biggordon@163.com
 */
public class EditNodesDto implements Serializable {

    /**
     * 节点技能id
     */
    private Long skillId;

    /**
     * 节点名
     */
    private String skillName;

    /**
     * 熟练度
     */
    private Integer proficiency;

    /**
     * 父节点名
     */
    private String parentSkillName;
    /**
     * 下一层所有子节点
     */
    private List<String> childrenName;
    /**
     * 节点说明
     */
    private String skillDescrip;

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public List<String> getChildrenName() {
        return childrenName;
    }

    public void setChildrenName(List<String> childrenName) {
        this.childrenName = childrenName;
    }

    public String getParentSkillName() {
        return parentSkillName;
    }

    public void setParentSkillName(String parentSkillName) {
        this.parentSkillName = parentSkillName;
    }

    public String getSkillDescrip() {
        return skillDescrip;
    }

    public void setSkillDescrip(String skillDescrip) {
        this.skillDescrip = skillDescrip;
    }

    public Integer getProficiency() {
        return proficiency;
    }

    public void setProficiency(Integer proficiency) {
        this.proficiency = proficiency;
    }
}
