package com.laputa.foundation.web.rbac.dao;

import java.util.List;

import com.laputa.foundation.web.rbac.entity.SysPermission;
import com.laputa.foundation.web.rbac.entity.SysRole;
import com.laputa.foundation.web.rbac.entity.SysFile;
import com.laputa.foundation.web.rbac.entity.SysElement;
import com.laputa.foundation.web.rbac.entity.SysMenu;
import com.laputa.foundation.web.rbac.entity.SysOperation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * <p>
 * 权限 Jpa Repository<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-02-03T16:53:42+08:00 .
 */
public interface SysPermissionJpaRepository extends JpaRepository<SysPermission, java.lang.Long>, JpaSpecificationExecutor<SysPermission> {

	@Query("SELECT sysPermission FROM SysPermission sysPermission WHERE sysPermission.code=:code")
	SysPermission selectByCode(@Param(value = "code") String code);

	@Query("SELECT sysElementBelongtoRelationSysPermission.sysElement FROM SysElementBelongtoRelationSysPermission sysElementBelongtoRelationSysPermission where sysElementBelongtoRelationSysPermission.sysPermissionId=:sysPermissionId")
	List<SysElement> selectSysElementListBySysPermissionId(@Param(value = "sysPermissionId") Long sysPermissionId);
	@Query("SELECT sysFileBelongtoRelationSysPermission.sysFile FROM SysFileBelongtoRelationSysPermission sysFileBelongtoRelationSysPermission where sysFileBelongtoRelationSysPermission.sysPermissionId=:sysPermissionId")
	List<SysFile> selectSysFileListBySysPermissionId(@Param(value = "sysPermissionId") Long sysPermissionId);
	@Query("SELECT sysMenuBelongtoRelationSysPermission.sysMenu FROM SysMenuBelongtoRelationSysPermission sysMenuBelongtoRelationSysPermission where sysMenuBelongtoRelationSysPermission.sysPermissionId=:sysPermissionId")
	List<SysMenu> selectSysMenuListBySysPermissionId(@Param(value = "sysPermissionId") Long sysPermissionId);
	@Query("SELECT sysOperationBelongtoRelationSysPermission.sysOperation FROM SysOperationBelongtoRelationSysPermission sysOperationBelongtoRelationSysPermission where sysOperationBelongtoRelationSysPermission.sysPermissionId=:sysPermissionId")
	List<SysOperation> selectSysOperationListBySysPermissionId(@Param(value = "sysPermissionId") Long sysPermissionId);
	@Query("SELECT sysPermissionBelongtoRelationSysRole.sysRole FROM SysPermissionBelongtoRelationSysRole sysPermissionBelongtoRelationSysRole where sysPermissionBelongtoRelationSysRole.sysPermissionId=:sysPermissionId")
	List<SysRole> selectSysRoleListBySysPermissionId(@Param(value = "sysPermissionId") Long sysPermissionId);
}