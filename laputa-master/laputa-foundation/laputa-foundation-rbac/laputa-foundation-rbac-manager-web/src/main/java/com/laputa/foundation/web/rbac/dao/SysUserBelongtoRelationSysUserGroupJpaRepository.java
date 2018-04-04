package com.laputa.foundation.web.rbac.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysRole;
import com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysUserGroup;

/**
 * Created by JiangDongPing on 2016/12/23.
 */
public interface SysUserBelongtoRelationSysUserGroupJpaRepository
        extends JpaRepository<SysUserBelongtoRelationSysUserGroup, Long>, JpaSpecificationExecutor<SysUserBelongtoRelationSysUserGroup> {
}
