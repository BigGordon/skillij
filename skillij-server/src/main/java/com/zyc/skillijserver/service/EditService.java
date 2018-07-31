package com.zyc.skillijserver.service;

import com.zyc.skillijserver.dto.EditNodesDto;
import com.zyc.skillijserver.dto.EditTreeTitleDto;

import java.util.List;

/**
 * Created on 2018/4/18.
 * Author: Gordon
 * Email: biggordon@163.com
 */
public interface EditService {
    /**
     * 获取用户的技能树节点信息
     * @param user
     * @param treeId
     * @return
     */
    List<EditNodesDto> getNodes(String user, Long treeId);

    /**
     * 修改用户的技能
     * @param editNodesDtos
     * @param user
     * @param treeId
     */
    String reviseSkill(List<EditNodesDto> editNodesDtos, String user, Long treeId);

    /**
     * 根据id列表删除用户技能
     * @param ids
     */
    void deleteSkillByIds(List<Long> ids);

    /**
     * 新建技能
     * @param editNodesDto
     * @param user
     * @param treeId
     * @return
     */
    String newSkill(EditNodesDto editNodesDto, String user, Long treeId);

    /**
     * 获取用户所有的技能树名称与ID
     * @param user
     * @return
     */
    List<EditTreeTitleDto> getTitles(String user);

    /**
     * 新增技能树
     * @param newTreeName
     * @param user
     * @return
     */
    String newTree(String newTreeName, String user);

    /**
     * 删除技能树
     * @param treeId
     * @return
     */
    void deleteTreeByTreeId(Long treeId);

    /**
     * 修改技能树
     * @param newTreeName
     * @param userName
     * @param treeId
     * @return
     */
    String editTree(String newTreeName, String userName, Long treeId);
}
