package com.laputa.foundation.web.rbac.dao;

import java.util.List;

import com.laputa.foundation.web.rbac.entity.SysOperation;
import com.laputa.foundation.web.rbac.entity.SysPermission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * <p>
 * 功能操作 Jpa Repository<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-02-03T16:53:42+08:00 .
 */
public interface SysOperationJpaRepository extends JpaRepository<SysOperation, java.lang.Long>, JpaSpecificationExecutor<SysOperation> {

	@Query("SELECT sysOperation FROM SysOperation sysOperation WHERE sysOperation.code=:code")
	SysOperation selectByCode(@Param(value = "code") String code);

	@Query("SELECT sysOperationBelongtoRelationSysPermission.sysPermission FROM SysOperationBelongtoRelationSysPermission sysOperationBelongtoRelationSysPermission where sysOperationBelongtoRelationSysPermission.sysOperationId=:sysOperationId")
	List<SysPermission> selectSysPermissionListBySysOperationId(@Param(value = "sysOperationId") Long sysOperationId);
}