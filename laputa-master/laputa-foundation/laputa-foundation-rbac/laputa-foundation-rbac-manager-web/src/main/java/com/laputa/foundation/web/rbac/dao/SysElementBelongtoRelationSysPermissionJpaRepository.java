package com.laputa.foundation.web.rbac.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.laputa.foundation.web.rbac.entity.SysElementBelongtoRelationSysPermission;

/**
 * Created by JiangDongPing on 2016/12/23.
 */
public interface SysElementBelongtoRelationSysPermissionJpaRepository
        extends JpaRepository<SysElementBelongtoRelationSysPermission, Long>, JpaSpecificationExecutor<SysElementBelongtoRelationSysPermission> {
}
