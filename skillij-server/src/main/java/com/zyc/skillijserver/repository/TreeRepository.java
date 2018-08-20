package com.zyc.skillijserver.repository;

import com.zyc.skillijcommon.domain.mysql.UserTree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface TreeRepository extends JpaRepository<UserTree, Long>, JpaSpecificationExecutor<UserTree> {

    /**
     * 根据用户ID找到用户的所有技能树ID
     * @param userId
     * @return
     */
    @Query("select u.treeId from UserTree u where u.userId = ?1")
    List<Long> getTreeIdByUserId(Long userId);

    /**
     * 根据技能树ID找到技能树名称
     * @param treeId
     * @return
     */
    @Query("select u.treeName from UserTree u where u.treeId = ?1")
    String getTreeNameByTreeId(Long treeId);

    /**
     * 根据树名与用户ID查找到一颗技能树
     * @param treeName
     * @param userId
     * @return
     */
    @Query("select u from UserTree u where u.treeName = ?1 and u.userId = ?2")
    UserTree getUserTreeByTreeNameAndUserId (String treeName, Long userId);

    /**
     * 根据技能树ID删除用户的一颗技能树
     * @param treeId
     */
    @Transactional
    @Modifying
    @Query("delete from UserTree u where u.treeId = ?1")
    void deleteUserTreeByTreeId(Long treeId);

    /**
     * 根据技能树ID查找到一颗技能树
     * @param treeId
     * @return
     */
    @Query("select u from UserTree u where u.treeId = ?1")
    UserTree getUserTreeByTreeId(Long treeId);

}
