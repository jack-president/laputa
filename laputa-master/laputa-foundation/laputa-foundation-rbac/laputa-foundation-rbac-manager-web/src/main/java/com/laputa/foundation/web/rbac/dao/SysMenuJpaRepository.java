package com.laputa.foundation.web.rbac.dao;

import java.util.List;

import com.laputa.foundation.web.rbac.entity.SysMenu;
import com.laputa.foundation.web.rbac.entity.SysPermission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * <p>
 * 菜单 Jpa Repository<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-02-03T16:53:41+08:00 .
 */
public interface SysMenuJpaRepository extends JpaRepository<SysMenu, java.lang.Long>, JpaSpecificationExecutor<SysMenu> {

	@Query("SELECT sysMenu FROM SysMenu sysMenu WHERE sysMenu.code=:code")
	SysMenu selectByCode(@Param(value = "code") String code);

	@Query("SELECT sysMenuBelongtoRelationSysPermission.sysPermission FROM SysMenuBelongtoRelationSysPermission sysMenuBelongtoRelationSysPermission where sysMenuBelongtoRelationSysPermission.sysMenuId=:sysMenuId")
	List<SysPermission> selectSysPermissionListBySysMenuId(@Param(value = "sysMenuId") Long sysMenuId);
}