package com.zyc.cloud.service.impl;

import com.zyc.cloud.controller.AccountController;
import com.zyc.cloud.domain.UserSkill;
import com.zyc.cloud.dto.EditNodesDto;
import com.zyc.cloud.repository.AccountRepository;
import com.zyc.cloud.repository.SkillRepository;
import com.zyc.cloud.service.EditService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 2018/4/18.
 * Author: Gordon
 * Email: biggordon@163.com
 */
@Service
public class EditServiceImpl implements EditService {

    @Resource
    private AccountRepository accountRepository;

    @Resource
    private SkillRepository skillRepository;

    /**
     * 获取用于编辑的技能树节点
     * @param user
     * @return
     */
    @Override
    public List<EditNodesDto> getNodes(String user) {
        List<EditNodesDto> editNodes = new ArrayList<>();
        Long id = accountRepository.getIdByUsername(user);
        List<UserSkill> userSkills = skillRepository.findUserSkillsByUserId(id);

        List<EditNodesDto> result = new ArrayList<>();
        for (UserSkill userSkill: userSkills) {
            EditNodesDto node = new EditNodesDto();
            List<String> childrenName = skillRepository.findSkillNamesByParentId(userSkill.getId());
            String parentName = skillRepository.findSkillNameById(userSkill.getParentId());
            if (parentName == null) {
                parentName = "";
            }

            node.setSkillId(userSkill.getId());
            node.setSkillName(userSkill.getSkillName());
            node.setSkillDescrip(userSkill.getDescription());
            node.setChildrenName(childrenName);
            node.setParentSkillName(parentName);
            node.setProficiency(userSkill.getProficiency());
            result.add(node);
        }
        return result;
    }

    /**
     * 修改用户技能
     * @param editNodesDtos
     */
    @Override
    @Transactional
    public String reviseSkill(List<EditNodesDto> editNodesDtos, String user) {
        Long userId = accountRepository.getIdByUsername(user);
        //将节点更新结果存进数据库
        for (EditNodesDto editNodesDto: editNodesDtos) {
            //默认挂载在根节点上
            Long parentId = 0L;
            Long level = 0L;
            //当父节点不是根节点时
            if (!editNodesDto.getParentSkillName().equals("root")) {
                UserSkill skill = skillRepository.findUserSkillBySkillNameAndUserId(editNodesDto.getParentSkillName(), userId);
                if (skill == null) {
                    return "父节点不存在";
                }
                parentId = skill.getId();
                level = skill.getLevel() + 1;
            }

            skillRepository.updateUserSkillById(editNodesDto.getSkillId(), editNodesDto.getSkillName(),
                    editNodesDto.getProficiency(), editNodesDto.getSkillDescrip(), parentId, level);
        }
        return "修改成功";
    }
}
