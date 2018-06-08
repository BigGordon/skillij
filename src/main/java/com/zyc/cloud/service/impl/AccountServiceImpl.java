package com.zyc.cloud.service.impl;

import com.zyc.cloud.domain.SkillijUser;
import com.zyc.cloud.domain.UserSkill;
import com.zyc.cloud.dto.SkillTreeDto;
import com.zyc.cloud.repository.AccountRepository;
import com.zyc.cloud.repository.SkillRepository;
import com.zyc.cloud.service.AccountService;
import com.zyc.cloud.utils.JWTUtil;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 2018/2/6.
 * Author: Gordon
 * Email: biggordon@163.com
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountRepository accountRepository;

    @Resource
    private SkillRepository skillRepository;

    /**
     * 校验用户登录
     * @param user
     * @param passwd
     * @return
     */
    @Override
    public String getLoginResult(String user, String passwd) {
        String realPasswd = accountRepository.findPasswdByUser(user);
        if (realPasswd == null) {
            return "用户名不存在";
        }
        if (passwd.equals(realPasswd)) {
            return "登录成功";
        } else {
            return "密码错误";
        }
    }

    /**
     * 获取侧边栏账号
     * @return
     */
    @Override
    public List<String> getSideAccounts() {
        List<Long> ids = new ArrayList<>();

        //TODO:改成随机获取
        ids.add(1L);//gordon
        ids.add(2L);//traveller_ing
        ids.add(3L);//java

        List<SkillijUser> accounts = accountRepository.getSkillijUserByIds(ids);
        List<String> result = new ArrayList<>();
        for (SkillijUser skillijUser : accounts) {
            result.add(skillijUser.getUsername());
        }

        return result;
    }

    /**
     * 获取用户技能树
     * @param user
     * @return
     */
    @Override
    public SkillTreeDto getUserSkillTree(String user) {
        SkillTreeDto mainSkillTree = new SkillTreeDto(user);
        Long id = accountRepository.getIdByUsername(user);
        List<UserSkill> userSkills = skillRepository.findUserSkillsByUserId(id);
        //创建并装载各级技能树
        Map<Long, Map<Long, SkillTreeDto>> levelSkills = new HashMap<>();
        for (UserSkill userSkill: userSkills) {
            Long level = userSkill.getLevel();
            if (levelSkills.get(level) == null) {
                Map<Long, SkillTreeDto> mSkills = new HashMap<>();
                SkillTreeDto skillTreeDto = new SkillTreeDto(userSkill.getSkillName());
                skillTreeDto.setParentId(userSkill.getParentId());
                mSkills.put(userSkill.getId(), skillTreeDto);
                levelSkills.put(level, mSkills);
            } else {
                Map<Long, SkillTreeDto> mSkills = levelSkills.get(level);
                SkillTreeDto skillTreeDto = new SkillTreeDto(userSkill.getSkillName());
                skillTreeDto.setParentId(userSkill.getParentId());
                mSkills.put(userSkill.getId(), skillTreeDto);
                levelSkills.put(level, mSkills);
            }
        }
        //将各级技能树装载到根技能树上
        for (Integer j = levelSkills.size() - 1; j >= 0; j--) {
            Long i = j.longValue();
            for (SkillTreeDto aSkillTreeDto: levelSkills.get(i).values()) {
                Long parentId = aSkillTreeDto.getParentId();
                if (i != 0) {
                    SkillTreeDto parentDto = levelSkills.get(i - 1).get(parentId);
                    if (parentDto.getChildren() == null) {
                        List<SkillTreeDto> skillTreeDtos = new ArrayList<>();
                        skillTreeDtos.add(aSkillTreeDto);
                        parentDto.setChildren(skillTreeDtos);
                    } else {
                        List<SkillTreeDto> skillTreeDtos = parentDto.getChildren();
                        skillTreeDtos.add(aSkillTreeDto);
                        parentDto.setChildren(skillTreeDtos);
                    }
                    levelSkills.get(i-1).put(parentId, parentDto);
                } else {
                    if (mainSkillTree.getChildren() == null) {
                        List<SkillTreeDto> skillTreeDtos = new ArrayList<>();
                        skillTreeDtos.add(aSkillTreeDto);
                        mainSkillTree.setChildren(skillTreeDtos);
                    } else {
                        List<SkillTreeDto> skillTreeDtos = mainSkillTree.getChildren();
                        skillTreeDtos.add(aSkillTreeDto);
                        mainSkillTree.setChildren(skillTreeDtos);
                    }
                }
            }
        }

        return mainSkillTree;
    }

    /**
     * 根据用户名取得用户实例对象
     * @param username
     * @return
     */
    @Override
    public SkillijUser findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    /**
     * 校验token有效性
     * @param token
     * @return
     */
    @Override
    public Boolean getTokenValidity(String token) {
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        if (username == null) {
            return false;
        }

        SkillijUser userBean = findByUsername(username);
        if (userBean == null) {
            return false;
        }

        if (! JWTUtil.verify(token, username, userBean.getPassword())) {
            return false;
        }

        return true;
    }

    /**
     * 修改密码
     * @param username
     * @param oldPasswd
     * @param newPasswd
     * @return
     */
    @Override
    public String changePassword(String username, String oldPasswd, String newPasswd) {
        SkillijUser user = findByUsername(username);
        if (!user.getPassword().equals(oldPasswd)) {
            return "旧密码错误";
        } else {
            accountRepository.updatePasswordById(user.getId(), newPasswd);
            return "修改密码成功";
        }
    }
}
