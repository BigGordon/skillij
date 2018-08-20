package com.zyc.skillijserver.service.impl;

import com.zyc.skillijcommon.domain.mysql.UserSkill;
import com.zyc.skillijcommon.domain.mysql.UserTree;
import com.zyc.skillijserver.dto.EditNodesDto;
import com.zyc.skillijserver.dto.EditTreeTitleDto;
import com.zyc.skillijserver.repository.mysql.AccountRepository;
import com.zyc.skillijserver.repository.mysql.SkillRepository;
import com.zyc.skillijserver.repository.TreeRepository;
import com.zyc.skillijserver.service.EditService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
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

    @Resource
    private TreeRepository treeRepository;


    /**
     * 修改技能树名称
     * @param newTreeName
     * @param userName
     * @param treeId
     * @return
     */
    @Override
    public String editTree(String newTreeName, String userName, Long treeId) {
        Long userId = accountRepository.getIdByUsername(userName);
        UserTree existedTreeName = treeRepository.getUserTreeByTreeNameAndUserId(newTreeName, userId);
        if(existedTreeName != null) {
            return "该技能树名已使用";
        }

        UserTree editUserTree = treeRepository.getUserTreeByTreeId(treeId);
        editUserTree.setTreeName(newTreeName);
        treeRepository.saveAndFlush(editUserTree);
        return "修改技能树名称成功";
    }


    /**
     * 删除技能树
     * @param treeId
     * @return
     */
    @Override
    public void deleteTreeByTreeId(Long treeId) {
        treeRepository.deleteUserTreeByTreeId(treeId);
    }


    /**
     * 新增技能树
     * @param newTreeName
     * @param user
     * @return
     */
    @Override
    public String newTree(String newTreeName, String user) {
        Long userId = accountRepository.getIdByUsername(user);
        UserTree existedTreeName = treeRepository.getUserTreeByTreeNameAndUserId(newTreeName, userId);
        if(existedTreeName != null) {
            return "技能树已存在";
        }

        UserTree newUserTree = new UserTree();
        newUserTree.setTreeName(newTreeName);
        newUserTree.setUserId(userId);
        treeRepository.saveAndFlush(newUserTree);
        return "新建技能树成功";
    }

    /**
     * 获取用户所有的技能树名称与ID
     * @param user
     * @return
     */
    @Override
    public List<EditTreeTitleDto> getTitles(String user) {
        Long userId = accountRepository.getIdByUsername(user);
        List<Long> treeIds = treeRepository.getTreeIdByUserId(userId);

        List<EditTreeTitleDto> result = new ArrayList<>();
        for(Long treeId: treeIds) {
            EditTreeTitleDto title = new EditTreeTitleDto();
            String treeName = treeRepository.getTreeNameByTreeId(treeId);
            title.setTreeId(treeId);
            title.setTreeName(treeName);
            result.add(title);
        }
        return result;
    }

    /**
     * 获取用于编辑的技能树节点
     * @param user
     * @return
     */
    @Override
    public List<EditNodesDto> getNodes(String user, Long treeId) {
        Long id = accountRepository.getIdByUsername(user);
        List<UserSkill> userSkills = skillRepository.findUserSkillsByUserIdAndTreeId(id, treeId);

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
     * @param user
     * @param treeId
     */
    @Override
    @Transactional
    public String reviseSkill(List<EditNodesDto> editNodesDtos, String user, Long treeId) {
        Long userId = accountRepository.getIdByUsername(user);
        //将节点更新结果存进数据库
        for (EditNodesDto editNodesDto: editNodesDtos) {
            //默认挂载在根节点上
            Long parentId = 0L;
            Long level = 0L;
            //当父节点不是根节点时
            if (!editNodesDto.getParentSkillName().equals("root")) {
                UserSkill skill = skillRepository.findUserSkillBySkillNameAndUserIdAndTreeId(
                        editNodesDto.getParentSkillName(), userId, treeId);
                if (skill == null) {
                    return "父节点\"" + editNodesDto.getParentSkillName() + "\"不存在";
                }
                parentId = skill.getId();
                level = skill.getLevel() + 1;
            }
            //校验重复的技能名
            UserSkill userSkill = skillRepository.findUserSkillBySkillNameAndUserIdAndTreeId(
                    editNodesDto.getSkillName(), userId, treeId);
            if (userSkill != null && !userSkill.getId().equals(editNodesDto.getSkillId())) {//有同名且不是自身
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
     * @param treeId
     * @return
     */
    @Override
    @Transactional
    public String newSkill(EditNodesDto editNodesDto, String user, Long treeId) {
        Long userId = accountRepository.getIdByUsername(user);
        Long parentId = 0L;
        Long level = 0L;
        //当父节点不是根节点时
        if (!editNodesDto.getParentSkillName().equals("root")) {
            UserSkill skill = skillRepository.findUserSkillBySkillNameAndUserIdAndTreeId(
                    editNodesDto.getParentSkillName(), userId, treeId);
            if (skill == null) {
                return "父节点\"" + editNodesDto.getParentSkillName() + "\"不存在";
            }
            parentId = skill.getId();
            level = skill.getLevel() + 1;
        }
        //校验重复的技能名
        if (isSkillNameDuplicated(editNodesDto.getSkillName(), userId, treeId)) {
            return "技能名已存在";
        }
        UserSkill newSkill = new UserSkill();
        newSkill.setSkillName(editNodesDto.getSkillName());
        newSkill.setParentId(parentId);
        newSkill.setLevel(level);
        newSkill.setDescription(editNodesDto.getSkillDescrip());
        newSkill.setProficiency(editNodesDto.getProficiency());
        newSkill.setUserId(userId);
        newSkill.setTreeId(treeId);
        skillRepository.saveAndFlush(newSkill);
        return "新建成功";
    }

    private boolean isSkillNameDuplicated(String skillName, Long userId, Long treeId) {
        UserSkill userSkill = skillRepository.findUserSkillBySkillNameAndUserIdAndTreeId(skillName, userId, treeId);
        return userSkill != null;
    }
}
