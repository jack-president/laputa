package com.laputa.foundation.web.generate.dao;

import java.util.List;

import com.laputa.foundation.web.generate.entity.SysHelloMenu;
import com.laputa.foundation.web.generate.entity.SysHelloPermission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * <p>
 * Hi菜单 Jpa Repository<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-01-10T18:13:30+08:00 .
 */
public interface SysHelloMenuJpaRepository extends JpaRepository<SysHelloMenu, java.lang.Long>, JpaSpecificationExecutor<SysHelloMenu> {

	@Query("SELECT sysHelloMenuBelongtoRelationSysHelloPermission.sysHelloPermission FROM SysHelloMenuBelongtoRelationSysHelloPermission sysHelloMenuBelongtoRelationSysHelloPermission where sysHelloMenuBelongtoRelationSysHelloPermission.sysHelloMenuId=:sysHelloMenuId")
	List<SysHelloPermission> selectSysHelloPermissionListBySysHelloMenuId(@Param(value = "sysHelloMenuId") Long sysHelloMenuId);
}