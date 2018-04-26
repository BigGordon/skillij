package com.zyc.cloud.service;

import com.zyc.cloud.dto.EditNodesDto;

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
     * @return
     */
    List<EditNodesDto> getNodes(String user);

    /**
     * 修改用户的技能
     * @param editNodesDtos
     */
    String reviseSkill(List<EditNodesDto> editNodesDtos, String user);

    /**
     * 根据id列表删除用户技能
     * @param ids
     */
    void deleteSkillByIds(List<Long> ids);
}
