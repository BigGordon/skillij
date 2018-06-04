package com.zyc.cloud.repository;

import com.zyc.cloud.domain.SkillijUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created on 2018/2/6.
 * Author: Gordon
 * Email: biggordon@163.com
 */
public interface AccountRepository extends JpaRepository<SkillijUser, Integer>{

    @Query("select u.password from SkillijUser u where u.username = ?1")
    String  findPasswdByUser(String user);


    @Query("select u from SkillijUser u where u.mail = ?1")
    SkillijUser  findSkillijUserByMail(String mail);

    @Query("select u from SkillijUser u where u.username = ?1")
    SkillijUser  findSkillijUserByUserName(String userName);

}
