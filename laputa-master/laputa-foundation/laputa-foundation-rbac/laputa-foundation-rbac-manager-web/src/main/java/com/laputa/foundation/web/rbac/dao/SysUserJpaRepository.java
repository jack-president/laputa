package com.laputa.foundation.web.rbac.dao;

import java.util.List;

import com.laputa.foundation.web.rbac.entity.SysUser;
import com.laputa.foundation.web.rbac.entity.SysUserGroup;
import com.laputa.foundation.web.rbac.entity.SysRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * <p>
 * 用户 Jpa Repository<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-02-03T16:53:42+08:00 .
 */
public interface SysUserJpaRepository extends JpaRepository<SysUser, java.lang.Long>, JpaSpecificationExecutor<SysUser> {
    @Query("SELECT sysUserBelongtoRelationSysRole.sysRole FROM SysUserBelongtoRelationSysRole sysUserBelongtoRelationSysRole where sysUserBelongtoRelationSysRole.sysUserId=:sysUserId")
    List<SysRole> selectSysRoleListBySysUserId(@Param(value = "sysUserId") Long sysUserId);

    @Query("SELECT sysUserBelongtoRelationSysUserGroup.sysUserGroup FROM SysUserBelongtoRelationSysUserGroup sysUserBelongtoRelationSysUserGroup where sysUserBelongtoRelationSysUserGroup.sysUserId=:sysUserId")
    List<SysUserGroup> selectSysUserGroupListBySysUserId(@Param(value = "sysUserId") Long sysUserId);

    @Query("DELETE FROM SysUserBelongtoRelationSysRole " +
            "WHERE sysUserId =:sysUserId " +
            "AND sysRoleId NOT IN " +
            "( " +
            "SELECT id FROM SysRole " +
            "WHERE parentSysUserGroupId IN " +
            "(SELECT sysUserGroupId FROM SysUserBelongtoRelationSysUserGroup WHERE sysUserId =:sysUserId )" +
            " )")
    @Modifying
    Integer clearOwnSysRoleCollection(@Param(value = "sysUserId") Long sysUserId);
}