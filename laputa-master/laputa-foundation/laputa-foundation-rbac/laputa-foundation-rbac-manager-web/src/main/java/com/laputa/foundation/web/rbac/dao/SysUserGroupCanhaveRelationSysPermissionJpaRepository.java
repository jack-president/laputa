package com.laputa.foundation.web.rbac.dao;

import com.laputa.foundation.web.rbac.entity.SysUserGroupCanhaveRelationSysPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by JiangDongPing on 2016/12/23.
 */
public interface SysUserGroupCanhaveRelationSysPermissionJpaRepository
        extends JpaRepository<SysUserGroupCanhaveRelationSysPermission, Long>, JpaSpecificationExecutor<SysUserGroupCanhaveRelationSysPermission> {
}
