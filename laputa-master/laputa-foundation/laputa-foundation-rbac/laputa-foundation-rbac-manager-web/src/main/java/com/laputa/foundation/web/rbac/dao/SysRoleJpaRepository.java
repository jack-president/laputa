package com.laputa.foundation.web.rbac.dao;

import java.util.List;

import com.laputa.foundation.web.rbac.entity.SysRole;
import com.laputa.foundation.web.rbac.entity.SysUser;
import com.laputa.foundation.web.rbac.entity.SysPermission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * <p>
 * 角色 Jpa Repository<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-08-15T09:50:29+08:00 .
 */
public interface SysRoleJpaRepository extends JpaRepository<SysRole, java.lang.Long>, JpaSpecificationExecutor<SysRole> {

    @Query("SELECT sysRole FROM SysRole sysRole WHERE sysRole.code=:code")
    SysRole selectByCode(@Param(value = "code") String code);

    @Query("SELECT sysPermissionBelongtoRelationSysRole.sysPermission FROM SysPermissionBelongtoRelationSysRole sysPermissionBelongtoRelationSysRole where sysPermissionBelongtoRelationSysRole.sysRoleId=:sysRoleId")
    List<SysPermission> selectSysPermissionListBySysRoleId(@Param(value = "sysRoleId") Long sysRoleId);

    @Query("SELECT sysUserBelongtoRelationSysRole.sysUser FROM SysUserBelongtoRelationSysRole sysUserBelongtoRelationSysRole where sysUserBelongtoRelationSysRole.sysRoleId=:sysRoleId")
    List<SysUser> selectSysUserListBySysRoleId(@Param(value = "sysRoleId") Long sysRoleId);

    @Query("SELECT groupCanHave.sysPermission FROM SysUserGroupCanhaveRelationSysPermission groupCanHave " +
            "WHERE groupCanHave.sysUserGroupId = ( SELECT parentSysUserGroupId FROM SysRole WHERE id = :sysRoleId) " +
            "AND groupCanHave.sysPermissionId NOT IN (SELECT sysPermissionId FROM SysPermissionBelongtoRelationSysRole WHERE sysRoleId = :sysRoleId)")
    List<SysPermission> selectNotHaveOwnSysPermission(@Param(value = "sysRoleId") Long sysRoleId);

    @Query("SELECT COUNT(id) FROM SysUserBelongtoRelationSysRole WHERE sysRoleId =:sysRoleId")
    Long countHaveSysUser(@Param(value = "sysRoleId") Long sysRoleId);
}