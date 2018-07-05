package com.zyc.skillijserver.repository;

import com.zyc.skillijcommon.domain.SkillijPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created on 2018/5/3.
 * Author: Gordon
 * Email: biggordon@163.com
 */
public interface PermissionRepository extends JpaRepository<SkillijPermission, Long>, JpaSpecificationExecutor<SkillijPermission> {
}
