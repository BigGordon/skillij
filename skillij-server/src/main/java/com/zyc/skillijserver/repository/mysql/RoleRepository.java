package com.zyc.skillijserver.repository.mysql;

import com.zyc.skillijcommon.domain.mysql.SkillijRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created on 2018/5/3.
 * Author: Gordon
 * Email: biggordon@163.com
 */
public interface RoleRepository extends JpaRepository<SkillijRole, Long>, JpaSpecificationExecutor<SkillijRole> {
}
