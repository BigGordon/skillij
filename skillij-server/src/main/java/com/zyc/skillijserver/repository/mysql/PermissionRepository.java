package com.zyc.skillijserver.repository.mysql;

import com.zyc.skillijcommon.domain.mysql.SkillijPermission;
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
public interface PermissionRepository extends JpaRepository<SkillijPermission, Long>, JpaSpecificationExecutor<SkillijPermission> {
}
