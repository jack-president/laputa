package com.laputa.foundation.web.security.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.laputa.foundation.persistence.entity.IdEntity;
import com.laputa.foundation.web.rbac.entity.SysElement;
import com.laputa.foundation.web.rbac.entity.SysFile;
import com.laputa.foundation.web.rbac.entity.SysMenu;
import com.laputa.foundation.web.rbac.entity.SysOperation;
import com.laputa.foundation.web.rbac.entity.SysPermission;
import com.laputa.foundation.web.rbac.entity.SysRole;
import com.laputa.foundation.web.rbac.entity.SysUser;
import com.laputa.foundation.web.rbac.entity.SysUserGroup;

/**
 * Created by JiangDongPing on 2016/12/27.
 */
public interface SecurityJpaRepository extends Repository<IdEntity, Long> {

    @Query("SELECT sysUser FROM SysUser sysUser WHERE sysUser.username=:username")
    SysUser selectSysUserByUserName(@Param("username") String username);

    @Query("SELECT sysUserGroup FROM SysUserGroup sysUserGroup  JOIN sysUserGroup.ownSysUserCollection r WHERE r.sysUserId=:sysUserId")
    List<SysUserGroup> selectSysUserGroupBySysUserId(@Param("sysUserId") Long sysUserId);

    /**
     * 查询用户角色
     *
     * @param sysUserId
     * @return
     */
    @Query("SELECT sysRole FROM SysRole sysRole WHERE  sysRole.inverted = false AND sysRole.id IN ( SELECT sysRoleId FROM SysUserBelongtoRelationSysRole WHERE sysUserId =:sysUserId )")
    List<SysRole> selectSysRoleBySysUserGroup(@Param("sysUserId") Long sysUserId);

    /**
     * 查询用户 反转角色
     *
     * @param sysUserId
     * @return
     */
    @Query("SELECT sysRole FROM SysRole sysRole WHERE sysRole.inverted = true AND sysRole.id IN ( SELECT sysRoleId FROM SysUserBelongtoRelationSysRole WHERE sysUserId =:sysUserId )")
    List<SysRole> selectInvertedSysRoleBySysUserGroup(@Param("sysUserId") Long sysUserId);

    @Query("SELECT DISTINCT sysRole FROM SysRole sysRole JOIN sysRole.ownSysUserCollection r WHERE  r.sysUserId = :sysUserId AND sysRole NOT IN :alreadyFind")
    List<SysRole> selectSysRoleBySysUserIdExcludeAlreadyFind(@Param("sysUserId") Long sysUserId, @Param("alreadyFind") List<SysRole> alreadyFind);

    @Query("SELECT DISTINCT sysRole FROM SysRole sysRole JOIN sysRole.ownSysUserCollection r WHERE  r.sysUserId = :sysUserId")
    List<SysRole> selectSysRoleBySysUserId(@Param("sysUserId") Long sysUserId);

    @Query("SELECT DISTINCT sysPermission FROM SysPermission sysPermission JOIN sysPermission.belongtoSysRoleCollection r WHERE r.sysRole IN :sysRoleList")
    List<SysPermission> selectSysPermissionBySysRole(@Param("sysRoleList") List<SysRole> sysRoleList);

    @Query("SELECT DISTINCT sysMenu FROM SysMenu sysMenu JOIN sysMenu.belongtoSysPermissionCollection r where  r.sysPermission IN :sysPermissionList ORDER BY sysMenu.orderValue DESC ")
    List<SysMenu> selectSysMenuBySysPermission(@Param("sysPermissionList") List<SysPermission> sysPermissionList);

    @Query("SELECT DISTINCT sysElement FROM SysElement sysElement JOIN sysElement.belongtoSysPermissionCollection r where  r.sysPermission IN :sysPermissionList")
    List<SysElement> selectSysElementBySysPermission(@Param("sysPermissionList") List<SysPermission> sysPermissionList);

    @Query("SELECT DISTINCT sysFile FROM SysFile sysFile JOIN sysFile.belongtoSysPermissionCollection r where  r.sysPermission IN :sysPermissionList")
    List<SysFile> selectSysFileBySysPermission(@Param("sysPermissionList") List<SysPermission> sysPermissionList);

    @Query("SELECT DISTINCT sysOperation FROM SysOperation sysOperation JOIN sysOperation.belongtoSysPermissionCollection r where  r.sysPermission IN :sysPermissionList")
    List<SysOperation> selectSysOperationBySysPermission(@Param("sysPermissionList") List<SysPermission> sysPermissionList);
}
