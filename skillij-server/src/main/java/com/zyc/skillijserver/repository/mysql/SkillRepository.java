package com.zyc.skillijserver.repository.mysql;

import com.zyc.skillijcommon.domain.mysql.UserSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created on 2018/3/28.
 * Author: Gordon
 * Email: biggordon@163.com
 */
public interface SkillRepository extends JpaRepository<UserSkill, Long>, JpaSpecificationExecutor<UserSkill> {

    /**
     * 根据用户ID找到用户技能
     * @param userId
     * @param treeId
     * @return
     */
    @Query("select u from UserSkill u where u.userId = ?1 and u.treeId = ?2")
    List<UserSkill> findUserSkillsByUserIdAndTreeId(Long userId, Long treeId);

    /**
     * 根据技能ID找到技能名
     * @param id
     * @return
     */
    @Query("select u.skillName from UserSkill u where u.id = ?1")
    String findSkillNameById(Long id);

    /**
     * 根据父节点id找到下一级子节点的所有节点名
     * @param parentId
     * @return
     */
    @Query("select u.skillName from UserSkill u where u.parentId = ?1")
    List<String> findSkillNamesByParentId(Long parentId);

    /**
     * 根据节点名查询节点id
     * @param skillName
     * @return
     */
    @Query("select u from UserSkill u where u.skillName = ?1 and u.userId = ?2 and u.treeId = ?3")
    UserSkill findUserSkillBySkillNameAndUserIdAndTreeId(String skillName, Long userId, Long treeId);

    /**
     * 根据节点id修改技能节点
     * @param id
     * @param skillName
     * @param proficiency
     * @param description
     * @param parentId
     * @param level
     */
    @Modifying(clearAutomatically = true)
    @Query("update UserSkill u set u.skillName = ?2, u.proficiency = ?3, u.description = ?4, u.parentId = ?5, u.level = ?6 " +
            "where u.id = ?1")
    void updateUserSkillById(Long id, String skillName, Integer proficiency, String description, Long parentId, Long level);

    /**
     *  根据id列表删除用户技能
     * @param ids
     */
    @Modifying(clearAutomatically = true)
    void deleteUserSkillsByIds(List<Long> ids);

    /**
     * 根据父id找出下一级子id
     * @return
     */
    @Query("select u.id from UserSkill u where u.parentId = ?1")
    List<Long> findIdsByParentId(Long id);
}
