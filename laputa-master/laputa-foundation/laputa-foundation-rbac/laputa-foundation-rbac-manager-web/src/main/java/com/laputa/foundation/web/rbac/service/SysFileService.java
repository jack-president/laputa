package com.laputa.foundation.web.rbac.service;

import com.laputa.foundation.web.rbac.entity.SysFile;
import com.laputa.foundation.web.rbac.dao.SysFileJpaRepository;
import com.laputa.foundation.web.rbac.entity.SysPermission;
import com.laputa.foundation.web.rbac.dao.SysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysFileBelongtoRelationSysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.entity.SysFileBelongtoRelationSysPermission;

import com.laputa.foundation.common.CollectionDiffUtil;
import com.laputa.foundation.web.exception.LaputaWebOperationException;
import com.laputa.foundation.web.kendo.model.DataSourceRequest;
import com.laputa.foundation.web.kendo.specification.LaputaKendoSpecification;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
* <p>
* 系统文件 Service<br>
* /p>
* Created by JiangDongPing CodeGnerate on 2018-03-07T16:26:11+08:00 .
*/
@Transactional
@Service("sysFileService")
public class SysFileService {

    @Resource
    private SysFileJpaRepository sysFileJpaRepository;


    @Resource
    private SysPermissionJpaRepository sysPermissionJpaRepository;

    @Resource
    private SysFileBelongtoRelationSysPermissionJpaRepository sysFileBelongtoRelationSysPermissionJpaRepository;

    @Transactional
    public SysFile create(SysFile sysFile) {

        SysFile createSysFile = new SysFile();

        createSysFile.setCname(sysFile.getCname());
        createSysFile.setPath(sysFile.getPath());
        createSysFile.setDescript(sysFile.getDescript());

        createSysFile = sysFileJpaRepository.save(createSysFile);

        processOneToManyBelongtoSysPermissionCollection(sysFile,createSysFile);

        return createSysFile;
    }

    @Transactional
    public void destory(SysFile sysFile) {
        SysFile destorySysFile = sysFileJpaRepository.findById(sysFile.getId())
            .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.DESTORY_TARGET_NONEXISTENT
                .generateException("系统文件 [ {0} ] 无法删除 因为无此记录 可能已经被删除", sysFile.getId()));
        sysFileJpaRepository.delete(destorySysFile);
    }


    @Transactional
    public SysFile update(SysFile sysFile) {
        SysFile updateSysFile = sysFileJpaRepository.findById(sysFile.getId())
            .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.UPDATE_TARGET_NONEXISTENT
                .generateException("系统文件 [ {0} ] 无法编辑 因为无此记录 可能已经被删除", sysFile.getId()));

        updateSysFile.setCname(sysFile.getCname());
        updateSysFile.setPath(sysFile.getPath());
        updateSysFile.setDescript(sysFile.getDescript());

        sysFileJpaRepository.save(updateSysFile);

        processOneToManyBelongtoSysPermissionCollection(sysFile,updateSysFile);

        return updateSysFile;
    }


    @Transactional
    public List<SysFile> read() {
        List<SysFile> sysFileList = sysFileJpaRepository.findAll();
        return sysFileList;
    }

    @Transactional
    public List<SysFile> readEager() {
        List<SysFile> sysFileList = sysFileJpaRepository.findAll();
        if( sysFileList != null && sysFileList.size() > 0 ){
            for( SysFile sysFile :  sysFileList ){
                sysFile.getBelongtoSysPermissionCollection().size();
            }
        }
        return sysFileList;
    }


    @Transactional
    public Page<SysFile> readDataSource(DataSourceRequest dataSourceRequest) {
        LaputaKendoSpecification<SysFile> laputaKendoSpecification = new LaputaKendoSpecification<>(dataSourceRequest);
        Page<SysFile> sysFilePage = sysFileJpaRepository.findAll(laputaKendoSpecification, laputaKendoSpecification.pageable());
        if (sysFilePage != null && sysFilePage.getContent() != null && sysFilePage.getContent().size() > 0) {
            for (SysFile sysFile : sysFilePage.getContent()) {
                sysFile.getBelongtoSysPermissionCollection().size();
            }
        }
        return sysFilePage;
    }

    CollectionDiffUtil.Compare<SysFileBelongtoRelationSysPermission> sysFileBelongtoRelationSysPermissionCompare = new CollectionDiffUtil.Compare<SysFileBelongtoRelationSysPermission>() {
            @Override
            public Boolean equal(SysFileBelongtoRelationSysPermission left, SysFileBelongtoRelationSysPermission right) {
                return left.getSysPermissionId().equals(right.getSysPermissionId());
            }
        };


    @Transactional
    private void processOneToManyBelongtoSysPermissionCollection(SysFile sysFile, SysFile persistedSysFile) {
        Collection<SysFileBelongtoRelationSysPermission> needClearbelongtoSysPermissionCollection = CollectionDiffUtil.inLeftButNotInRiht(persistedSysFile.getBelongtoSysPermissionCollection(),sysFile.getBelongtoSysPermissionCollection(),sysFileBelongtoRelationSysPermissionCompare);
        if (needClearbelongtoSysPermissionCollection != null && needClearbelongtoSysPermissionCollection.size() > 0) {
            sysFileBelongtoRelationSysPermissionJpaRepository.deleteAll(needClearbelongtoSysPermissionCollection);
            persistedSysFile.getBelongtoSysPermissionCollection().removeAll(needClearbelongtoSysPermissionCollection);
        }

        Collection<SysFileBelongtoRelationSysPermission> needProcessBelongtoSysPermissionCollection = CollectionDiffUtil.inLeftButNotInRiht(sysFile.getBelongtoSysPermissionCollection(),persistedSysFile.getBelongtoSysPermissionCollection(),sysFileBelongtoRelationSysPermissionCompare);
        if(needProcessBelongtoSysPermissionCollection != null && needProcessBelongtoSysPermissionCollection.size() > 0){
            for( SysFileBelongtoRelationSysPermission needProcessSysFileBelongtoRelationSysPermission :  needProcessBelongtoSysPermissionCollection){
                SysPermission persistedNeedProcessSysPermission = sysPermissionJpaRepository.findById(needProcessSysFileBelongtoRelationSysPermission.getSysPermissionId())
                    .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                        .generateException("权限 [ {0} ] 不存在", needProcessSysFileBelongtoRelationSysPermission.getSysPermissionId()));

                    SysFileBelongtoRelationSysPermission sysFileBelongtoRelationSysPermission = new SysFileBelongtoRelationSysPermission();
                    sysFileBelongtoRelationSysPermission.setSysFile(persistedSysFile);
                    sysFileBelongtoRelationSysPermission.setSysPermission(persistedNeedProcessSysPermission);
                    sysFileBelongtoRelationSysPermissionJpaRepository.save(sysFileBelongtoRelationSysPermission);
            }
        }
    }
}