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
        Long id = accountRepository.getIdByUsername(user);
        List<UserSkill> userSkills = skillRepository.findUserSkillsByUserId(id);

        List<EditNodesDto> result = new ArrayList<>();
        for (UserSkill userSkill: userSkills) {
            EditNodesDto node = new EditNodesDto();
            List<String> childrenName = skillRepository.findSkillNamesByParentId(userSkill.getId());
            String parentName = skillRepository.findSkillNameById(userSkill.getParentId());
            if (parentName == null && userSkill.getLevel() == 0) {
                parentName = "root";
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
                    return "父节点\"" + editNodesDto.getParentSkillName() + "\"不存在";
                }
                parentId = skill.getId();
                level = skill.getLevel() + 1;
            }
            //校验重复的技能名
            if (isSkillNameDuplicated(editNodesDto.getSkillName(), userId)) {
                return "技能名已存在";
            }
            skillRepository.updateUserSkillById(editNodesDto.getSkillId(), editNodesDto.getSkillName(),
                    editNodesDto.getProficiency(), editNodesDto.getSkillDescrip(), parentId, level);
        }
        return "修改成功";
    }

    /**
     * 根据id列表删除用户技能
     * @param ids
     */
    @Override
    @Transactional
    public void deleteSkillByIds(List<Long> ids) {
        //删除本身
        skillRepository.deleteUserSkillsByIds(ids);//这个必须得打包一起删除，防止父类和子类同时被选中删除时出问题
        //查找出子类并删除
        for (Long id: ids) {
            List<Long> childrenIds = skillRepository.findIdsByParentId(id);
            //递归删除
            if (childrenIds.size() > 0) {
                deleteSkillByIds(childrenIds);
            }
        }
    }

    /**
     * 新建技能
     * @param editNodesDto
     * @param user
     * @return
     */
    @Override
    @Transactional
    public String newSkill(EditNodesDto editNodesDto, String user) {
        Long userId = accountRepository.getIdByUsername(user);
        Long parentId = 0L;
        Long level = 0L;
        //当父节点不是根节点时
        if (!editNodesDto.getParentSkillName().equals("root")) {
            UserSkill skill = skillRepository.findUserSkillBySkillNameAndUserId(editNodesDto.getParentSkillName(), userId);
            if (skill == null) {
                return "父节点\"" + editNodesDto.getParentSkillName() + "\"不存在";
            }
            parentId = skill.getId();
            level = skill.getLevel() + 1;
        }
        //校验重复的技能名
        if (isSkillNameDuplicated(editNodesDto.getSkillName(), userId)) {
            return "技能名已存在";
        }
        UserSkill newSkill = new UserSkill();
        newSkill.setSkillName(editNodesDto.getSkillName());
        newSkill.setParentId(parentId);
        newSkill.setLevel(level);
        newSkill.setDescription(editNodesDto.getSkillDescrip());
        newSkill.setProficiency(editNodesDto.getProficiency());
        newSkill.setUserId(userId);
        skillRepository.saveAndFlush(newSkill);
        return "新建成功";
    }

    private boolean isSkillNameDuplicated(String skillName, Long userId) {
        UserSkill userSkill = skillRepository.findUserSkillBySkillNameAndUserId(skillName, userId);
        return userSkill != null;
    }
}
