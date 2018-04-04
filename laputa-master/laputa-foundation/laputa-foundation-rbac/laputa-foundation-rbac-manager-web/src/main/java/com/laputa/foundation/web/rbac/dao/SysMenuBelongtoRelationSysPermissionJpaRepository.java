package com.laputa.foundation.web.rbac.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.laputa.foundation.web.rbac.entity.SysFileBelongtoRelationSysPermission;
import com.laputa.foundation.web.rbac.entity.SysMenuBelongtoRelationSysPermission;

/**
 * Created by JiangDongPing on 2016/12/23.
 */
public interface SysMenuBelongtoRelationSysPermissionJpaRepository
        extends JpaRepository<SysMenuBelongtoRelationSysPermission, Long>, JpaSpecificationExecutor<SysMenuBelongtoRelationSysPermission> {
}
