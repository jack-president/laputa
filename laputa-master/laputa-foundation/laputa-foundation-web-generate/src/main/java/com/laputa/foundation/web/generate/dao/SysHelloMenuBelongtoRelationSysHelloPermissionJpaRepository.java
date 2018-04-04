package com.laputa.foundation.web.generate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.laputa.foundation.web.generate.entity.SysHelloMenuBelongtoRelationSysHelloPermission;

/**
 * Created by JiangDongPing on 2016/12/5.
 */
public interface SysHelloMenuBelongtoRelationSysHelloPermissionJpaRepository
        extends JpaRepository<SysHelloMenuBelongtoRelationSysHelloPermission, Long>,

                JpaSpecificationExecutor<SysHelloMenuBelongtoRelationSysHelloPermission> {
}
