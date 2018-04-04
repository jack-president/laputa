package com.laputa.foundation.web.rbac.dao;

import java.util.List;

import com.laputa.foundation.web.rbac.entity.SysElement;
import com.laputa.foundation.web.rbac.entity.SysPermission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * <p>
 * 系统元素 Jpa Repository<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-02-03T16:53:40+08:00 .
 */
public interface SysElementJpaRepository extends JpaRepository<SysElement, java.lang.Long>, JpaSpecificationExecutor<SysElement> {

	@Query("SELECT sysElement FROM SysElement sysElement WHERE sysElement.code=:code")
	SysElement selectByCode(@Param(value = "code") String code);

	@Query("SELECT sysElementBelongtoRelationSysPermission.sysPermission FROM SysElementBelongtoRelationSysPermission sysElementBelongtoRelationSysPermission where sysElementBelongtoRelationSysPermission.sysElementId=:sysElementId")
	List<SysPermission> selectSysPermissionListBySysElementId(@Param(value = "sysElementId") Long sysElementId);
}