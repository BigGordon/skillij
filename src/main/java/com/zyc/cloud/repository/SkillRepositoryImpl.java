package com.zyc.cloud.repository;

import com.zyc.cloud.domain.UserSkill;
import com.zyc.cloud.dto.EditNodesDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 2018/4/19.
 * Author: Gordon
 * Email: biggordon@163.com
 */
public class SkillRepositoryImpl {
    @PersistenceContext
    private EntityManager em;

    /**
     * 根据id列表删除用户技能
     * @param ids
     */
    void deleteUserSkillsByIds(List<Long> ids) {
        Map<Integer, Long> params = new HashMap<>();
        StringBuilder sql = new StringBuilder("DELETE FROM user_skill");
        sql.append(" WHERE id in (");

        //参数部分
        int paramIndex = 1;
        for (Long id : ids) {
            if (paramIndex > 1) {
                sql.append(",");
            }
            sql.append("?" + paramIndex);
            params.put(paramIndex, Long.valueOf(id));
            ++paramIndex;
        }

        sql.append(")");

        Query query = em.createNativeQuery(sql.toString(), UserSkill.class);
        Collection<Long> values = params.values();
        paramIndex = 1;
        for (Long id : values) {
            query.setParameter(paramIndex++, id);
        }
        int resurlt= query.executeUpdate();
    }
}
