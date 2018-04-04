package com.laputa.foundation.web.generate.service;

import com.laputa.foundation.persistence.util.IdEntityDiffUtil;
import com.laputa.foundation.web.generate.entity.SysEntity;
import com.laputa.foundation.web.generate.dao.SysEntityJpaRepository;
import com.laputa.foundation.web.generate.entity.SysField;
import com.laputa.foundation.web.generate.dao.SysFieldJpaRepository;
import com.laputa.foundation.web.kendo.model.DataSourceRequest;
import com.laputa.foundation.web.kendo.specification.LaputaKendoSpecification;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.RuntimeException;
import java.util.Collection;
import java.util.List;

/**
 * <p/>
 * 实体表 Service<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2016-11-30T17:13:59+08:00 .
 */
@Transactional
@Service("sysEntityService")
public class SysEntityService {

    @Resource
    private SysEntityJpaRepository sysEntityJpaRepository;

    @Resource
    private SysFieldJpaRepository sysFieldJpaRepository;

    @Transactional
    public SysEntity create(SysEntity sysEntity) {

        SysEntity createSysEntity = new SysEntity();

        createSysEntity.setCname(sysEntity.getCname());
        createSysEntity.setClazzName(sysEntity.getClazzName());

        createSysEntity = sysEntityJpaRepository.save(createSysEntity);

        processOneToManySysFieldCollection(sysEntity, createSysEntity);

        return createSysEntity;
    }

    @Transactional
    public void destory(SysEntity sysEntity) {
        SysEntity destorySysEntity = sysEntityJpaRepository.findById(sysEntity.getId()).get();
        if (destorySysEntity != null) {
            sysEntityJpaRepository.delete(destorySysEntity);
        } else {
            throw new RuntimeException("实体表 无法删除 因为无此记录 可能已经被删除");
        }
    }

    @Transactional
    public SysEntity update(SysEntity sysEntity) {
        SysEntity updateSysEntity = sysEntityJpaRepository.findById(sysEntity.getId()).get();
        if (updateSysEntity == null) {
            throw new RuntimeException("实体表 无法编辑 因为无此记录 可能已经被删除");
        }

        updateSysEntity.setCname(sysEntity.getCname());
        updateSysEntity.setClazzName(sysEntity.getClazzName());

        sysEntityJpaRepository.save(updateSysEntity);

        processOneToManySysFieldCollection(sysEntity, updateSysEntity);

        return updateSysEntity;
    }

    @Transactional
    public List<SysEntity> read() {
        List<SysEntity> sysEntityList = sysEntityJpaRepository.findAll();
        return sysEntityList;
    }

    @Transactional
    public List<SysEntity> readEager() {
        List<SysEntity> sysEntityList = sysEntityJpaRepository.findAll();
        if (sysEntityList != null && sysEntityList.size() > 0) {
            for (SysEntity sysEntity : sysEntityList) {
                sysEntity.getSysFieldCollection().size();
            }
        }
        return sysEntityList;
    }

    @Transactional
    public Page<SysEntity> readDataSource(DataSourceRequest dataSourceRequest) {
        LaputaKendoSpecification<SysEntity> laputaKendoSpecification = new LaputaKendoSpecification<>(dataSourceRequest);
        Page<SysEntity> sysEntityPage =
                sysEntityJpaRepository.findAll(laputaKendoSpecification, laputaKendoSpecification.pageable());
        if (sysEntityPage != null && sysEntityPage.getContent() != null && sysEntityPage.getContent().size() > 0) {
            for (SysEntity sysEntity : sysEntityPage.getContent()) {
                sysEntity.getSysFieldCollection().size();
            }
        }
        return sysEntityPage;
    }

    @Transactional
    private void processOneToManySysFieldCollection(SysEntity sysEntity, SysEntity persistedSysEntity) {
        Collection<SysField> needClearsysFieldCollection = IdEntityDiffUtil
                .inLeftButNotInRiht(persistedSysEntity.getSysFieldCollection(), sysEntity.getSysFieldCollection());
        if (needClearsysFieldCollection != null && needClearsysFieldCollection.size() > 0) {
            for (SysField persistedsysField : needClearsysFieldCollection) {
                persistedsysField.setSysEntity(null);
            }
            persistedSysEntity.getSysFieldCollection().removeAll(needClearsysFieldCollection);
        }

        Collection<SysField> needpProcesssysFieldCollection = IdEntityDiffUtil
                .inLeftButNotInRiht(sysEntity.getSysFieldCollection(), persistedSysEntity.getSysFieldCollection());
        if (needpProcesssysFieldCollection != null && needpProcesssysFieldCollection.size() > 0) {
            for (SysField needpProcesssysField : needpProcesssysFieldCollection) {
                SysField persistedSysField = sysFieldJpaRepository.findById(needpProcesssysField.getId()).get();
                if (persistedSysField != null) {
                    persistedSysField.setSysEntity(persistedSysEntity);
                } else {
                    throw new RuntimeException("实体表 " + needpProcesssysField.getId() + "不存在");
                }
            }
        }
    }
}