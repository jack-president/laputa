package com.laputa.foundation.web.rbac.dao;

import java.util.List;

import com.laputa.foundation.web.rbac.entity.SysRole;
import com.laputa.foundation.web.rbac.entity.SysUserGroup;
import com.laputa.foundation.web.rbac.entity.SysUser;
import com.laputa.foundation.web.rbac.entity.SysPermission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * <p>
 * 用户组 Jpa Repository<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-08-15T09:50:30+08:00 .
 */
public interface SysUserGroupJpaRepository extends JpaRepository<SysUserGroup, java.lang.Long>, JpaSpecificationExecutor<SysUserGroup> {

    @Query("SELECT sysUserGroup FROM SysUserGroup sysUserGroup WHERE sysUserGroup.code=:code")
    SysUserGroup selectByCode(@Param(value = "code") String code);

    @Query("SELECT sysUserGroupCanhaveRelationSysPermission.sysPermission FROM SysUserGroupCanhaveRelationSysPermission sysUserGroupCanhaveRelationSysPermission where sysUserGroupCanhaveRelationSysPermission.sysUserGroupId=:sysUserGroupId")
    List<SysPermission> selectSysPermissionListBySysUserGroupId(@Param(value = "sysUserGroupId") Long sysUserGroupId);

    @Query("SELECT sysPermission FROM SysPermission sysPermission  WHERE sysPermission.id NOT IN (SELECT sysUserGroupCanhaveRelationSysPermission.sysPermissionId FROM SysUserGroupCanhaveRelationSysPermission sysUserGroupCanhaveRelationSysPermission where sysUserGroupCanhaveRelationSysPermission.sysUserGroupId =:sysUserGroupId) ")
    List<SysPermission> selectOtherSysPermissionListBySysUserGroupId(@Param(value = "sysUserGroupId") Long sysUserGroupId);


    @Query("SELECT sysUserBelongtoRelationSysUserGroup.sysUser FROM SysUserBelongtoRelationSysUserGroup sysUserBelongtoRelationSysUserGroup where sysUserBelongtoRelationSysUserGroup.sysUserGroupId=:sysUserGroupId")
    List<SysUser> selectSysUserListBySysUserGroupId(@Param(value = "sysUserGroupId") Long sysUserGroupId);

    @Modifying
    @Query("DELETE FROM SysPermissionBelongtoRelationSysRole  " +
            "WHERE sysRoleId IN" +
            " ( SELECT id FROM SysRole WHERE parentSysUserGroupId = :sysUserGroupId) " +
            "AND " +
            "sysPermissionId NOT IN " +
            "( SELECT sysPermissionId FROM SysUserGroupCanhaveRelationSysPermission WHERE sysUserGroupId = :sysUserGroupId)")
    Integer clearOwnSysRoleCollectionRole(@Param(value = "sysUserGroupId") Long sysUserGroupId);


    @Query("SELECT COUNT(id) FROM SysUserBelongtoRelationSysUserGroup WHERE sysUserGroupId =:sysUserGroupId")
    Long selectHaveSysUserCount(@Param(value = "sysUserGroupId") Long sysUserGroupId);
}