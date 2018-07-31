package com.zyc.skillijserver.dto;

import java.io.Serializable;
/**
 * 描述:
 * 技能树标题栏树名列表传输对象
 *
 * @author Van
 * @create 2018-07-17 下午1:19
 */

public class EditTreeTitleDto implements Serializable {

    /**
     * 技能树ID
     */
    private Long treeId;

    /**
     * 技能树名称
     */
    private String treeName;

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }
}

