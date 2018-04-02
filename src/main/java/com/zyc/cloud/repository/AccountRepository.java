package com.zyc.cloud.repository;

import com.zyc.cloud.domain.SkillijUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created on 2018/2/6.
 * Author: Gordon
 * Email: biggordon@163.com
 */
public interface AccountRepository extends JpaRepository<SkillijUser, Long>, JpaSpecificationExecutor<SkillijUser> {

    /**
     * 根据用户名找到密码
     * @param user
     * @return
     */
    @Query("select u.password from SkillijUser u where u.username = ?1")
    String findPasswdByUser(String user);

    /**
     * 根据id列表找到用户名
     * @param ids
     * @return
     */
    List<SkillijUser> getSkillijUserByIds(List<Long> ids);

    /**
     * 根据用户名找到id
     * @param username
     * @return
     */
    @Query("select u.id from SkillijUser u where u.username = ?1")
    Long getIdByUsername(String username);

}
