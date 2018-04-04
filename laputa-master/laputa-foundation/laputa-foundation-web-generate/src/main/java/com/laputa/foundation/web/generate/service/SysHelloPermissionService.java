package com.laputa.foundation.web.generate.service;

import com.laputa.foundation.web.generate.entity.SysHelloPermission;
import com.laputa.foundation.web.generate.dao.SysHelloPermissionJpaRepository;
import com.laputa.foundation.web.kendo.model.DataSourceRequest;
import com.laputa.foundation.web.kendo.specification.LaputaKendoSpecification;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.RuntimeException;
import java.util.List;

/**
 * <p/>
 * Hi权限 Service<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2016-12-05T11:02:51+08:00 .
 */
@Transactional
@Service("sysHelloPermissionService")
public class SysHelloPermissionService {

    @Resource
    private SysHelloPermissionJpaRepository sysHelloPermissionJpaRepository;

    @Transactional
    public SysHelloPermission create(SysHelloPermission sysHelloPermission) {

        SysHelloPermission createSysHelloPermission = new SysHelloPermission();

        createSysHelloPermission.setCname(sysHelloPermission.getCname());

        createSysHelloPermission = sysHelloPermissionJpaRepository.save(createSysHelloPermission);

        return createSysHelloPermission;
    }

    @Transactional
    public void destory(SysHelloPermission sysHelloPermission) {
        SysHelloPermission destorySysHelloPermission = sysHelloPermissionJpaRepository.findById(sysHelloPermission.getId()).get();
        if (destorySysHelloPermission != null) {
            sysHelloPermissionJpaRepository.delete(destorySysHelloPermission);
        } else {
            throw new RuntimeException("Hi权限 无法删除 因为无此记录 可能已经被删除");
        }
    }

    @Transactional
    public SysHelloPermission update(SysHelloPermission sysHelloPermission) {
        SysHelloPermission updateSysHelloPermission = sysHelloPermissionJpaRepository.findById(sysHelloPermission.getId()).get();
        if (updateSysHelloPermission == null) {
            throw new RuntimeException("Hi权限 无法编辑 因为无此记录 可能已经被删除2");
        }

        updateSysHelloPermission.setCname(sysHelloPermission.getCname());

        sysHelloPermissionJpaRepository.save(updateSysHelloPermission);

        return updateSysHelloPermission;
    }

    @Transactional
    public List<SysHelloPermission> read() {
        List<SysHelloPermission> l = sysHelloPermissionJpaRepository.findAll();
        return l;
    }

    @Transactional
    public List<SysHelloPermission> readEager() {
        return sysHelloPermissionJpaRepository.findAll();
    }

    @Transactional
    public Page<SysHelloPermission> readDataSource(DataSourceRequest dataSourceRequest) {
        LaputaKendoSpecification<SysHelloPermission> laputaKendoSpecification = new LaputaKendoSpecification<>(dataSourceRequest);
        return sysHelloPermissionJpaRepository.findAll(laputaKendoSpecification, laputaKendoSpecification.pageable());
    }

}