package com.laputa.foundation.web.rbac.dao;

import java.util.List;

import com.laputa.foundation.web.rbac.entity.SysFile;
import com.laputa.foundation.web.rbac.entity.SysPermission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * <p>
 * 系统文件 Jpa Repository<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-02-03T16:53:41+08:00 .
 */
public interface SysFileJpaRepository extends JpaRepository<SysFile, java.lang.Long>, JpaSpecificationExecutor<SysFile> {
	@Query("SELECT sysFileBelongtoRelationSysPermission.sysPermission FROM SysFileBelongtoRelationSysPermission sysFileBelongtoRelationSysPermission where sysFileBelongtoRelationSysPermission.sysFileId=:sysFileId")
	List<SysPermission> selectSysPermissionListBySysFileId(@Param(value = "sysFileId") Long sysFileId);
}