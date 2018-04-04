package com.laputa.foundation.web.rbac.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.laputa.foundation.web.rbac.entity.SysMenuBelongtoRelationSysPermission;
import com.laputa.foundation.web.rbac.entity.SysOperationBelongtoRelationSysPermission;

/**
 * Created by JiangDongPing on 2016/12/23.
 */
public interface SysOperationBelongtoRelationSysPermissionJpaRepository
        extends JpaRepository<SysOperationBelongtoRelationSysPermission, Long>, JpaSpecificationExecutor<SysOperationBelongtoRelationSysPermission> {
}
