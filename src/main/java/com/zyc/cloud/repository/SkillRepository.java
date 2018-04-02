package com.zyc.cloud.repository;

import com.zyc.cloud.domain.UserSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
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
     * @return
     */
    @Query("select u from UserSkill u where u.userId = ?1")
    List<UserSkill> findUserSkillsByUserId(Long userId);


}
