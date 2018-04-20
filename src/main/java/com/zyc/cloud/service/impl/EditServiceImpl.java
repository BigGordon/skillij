package com.zyc.cloud.service.impl;

import com.zyc.cloud.controller.AccountController;
import com.zyc.cloud.domain.UserSkill;
import com.zyc.cloud.dto.EditNodesDto;
import com.zyc.cloud.repository.AccountRepository;
import com.zyc.cloud.repository.SkillRepository;
import com.zyc.cloud.service.EditService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
}
