package com.zyc.skillijserver.repository;

import com.zyc.skillijcommon.domain.SkillijUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 2018/3/28.
 * Author: Gordon
 * Email: biggordon@163.com
 */
public class AccountRepositoryImpl {
    @PersistenceContext
    private EntityManager em;

    /**
     * 根据id列表找到用户名
     * @param ids
     * @return
     */
    List<SkillijUser> getSkillijUserByIds(List<Long> ids) {
        Map<Integer, Long> params = new HashMap<>();
        StringBuilder sql = new StringBuilder("select * from skillij_user");
        sql.append(" where id in (");

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

        Query query = em.createNativeQuery(sql.toString(), SkillijUser.class);
        Collection<Long> values = params.values();
        paramIndex = 1;
        for (Long id : values) {
            query.setParameter(paramIndex++, id);
        }

        return query.getResultList();
    }
}
