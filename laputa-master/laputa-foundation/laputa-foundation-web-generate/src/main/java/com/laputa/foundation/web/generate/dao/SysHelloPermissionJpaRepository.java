package com.laputa.foundation.web.generate.dao;

import com.laputa.foundation.web.generate.entity.SysHelloPermission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <p>
 * Hi权限 Jpa Repository<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2016-12-05T10:32:57+08:00 .
 */
public interface SysHelloPermissionJpaRepository extends JpaRepository<SysHelloPermission, Long>, JpaSpecificationExecutor<SysHelloPermission> {
}