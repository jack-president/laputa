package com.laputa.foundation.web.rbac.service;

import com.laputa.foundation.web.rbac.entity.SysElement;
import com.laputa.foundation.web.rbac.dao.SysElementJpaRepository;
import com.laputa.foundation.web.rbac.entity.SysPermission;
import com.laputa.foundation.web.rbac.dao.SysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysElementBelongtoRelationSysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.entity.SysElementBelongtoRelationSysPermission;

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
* 系统元素 Service<br>
* /p>
* Created by JiangDongPing CodeGnerate on 2018-03-07T16:26:10+08:00 .
*/
@Transactional
@Service("sysElementService")
public class SysElementService {

    @Resource
    private SysElementJpaRepository sysElementJpaRepository;


    @Resource
    private SysPermissionJpaRepository sysPermissionJpaRepository;

    @Resource
    private SysElementBelongtoRelationSysPermissionJpaRepository sysElementBelongtoRelationSysPermissionJpaRepository;

    @Transactional
    public SysElement create(SysElement sysElement) {

        SysElement createSysElement = new SysElement();

        createSysElement.setCname(sysElement.getCname());
        createSysElement.setCode(sysElement.getCode());
        createSysElement.setDescript(sysElement.getDescript());

        createSysElement = sysElementJpaRepository.save(createSysElement);

        processOneToManyBelongtoSysPermissionCollection(sysElement,createSysElement);

        return createSysElement;
    }

    @Transactional
    public void destory(SysElement sysElement) {
        SysElement destorySysElement = sysElementJpaRepository.findById(sysElement.getId())
            .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.DESTORY_TARGET_NONEXISTENT
                .generateException("系统元素 [ {0} ] 无法删除 因为无此记录 可能已经被删除", sysElement.getId()));
        sysElementJpaRepository.delete(destorySysElement);
    }


    @Transactional
    public SysElement update(SysElement sysElement) {
        SysElement updateSysElement = sysElementJpaRepository.findById(sysElement.getId())
            .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.UPDATE_TARGET_NONEXISTENT
                .generateException("系统元素 [ {0} ] 无法编辑 因为无此记录 可能已经被删除", sysElement.getId()));

        updateSysElement.setCname(sysElement.getCname());
        updateSysElement.setCode(sysElement.getCode());
        updateSysElement.setDescript(sysElement.getDescript());

        sysElementJpaRepository.save(updateSysElement);

        processOneToManyBelongtoSysPermissionCollection(sysElement,updateSysElement);

        return updateSysElement;
    }


    @Transactional
    public List<SysElement> read() {
        List<SysElement> sysElementList = sysElementJpaRepository.findAll();
        return sysElementList;
    }

    @Transactional
    public List<SysElement> readEager() {
        List<SysElement> sysElementList = sysElementJpaRepository.findAll();
        if( sysElementList != null && sysElementList.size() > 0 ){
            for( SysElement sysElement :  sysElementList ){
                sysElement.getBelongtoSysPermissionCollection().size();
            }
        }
        return sysElementList;
    }


    @Transactional
    public Page<SysElement> readDataSource(DataSourceRequest dataSourceRequest) {
        LaputaKendoSpecification<SysElement> laputaKendoSpecification = new LaputaKendoSpecification<>(dataSourceRequest);
        Page<SysElement> sysElementPage = sysElementJpaRepository.findAll(laputaKendoSpecification, laputaKendoSpecification.pageable());
        if (sysElementPage != null && sysElementPage.getContent() != null && sysElementPage.getContent().size() > 0) {
            for (SysElement sysElement : sysElementPage.getContent()) {
                sysElement.getBelongtoSysPermissionCollection().size();
            }
        }
        return sysElementPage;
    }

    CollectionDiffUtil.Compare<SysElementBelongtoRelationSysPermission> sysElementBelongtoRelationSysPermissionCompare = new CollectionDiffUtil.Compare<SysElementBelongtoRelationSysPermission>() {
            @Override
            public Boolean equal(SysElementBelongtoRelationSysPermission left, SysElementBelongtoRelationSysPermission right) {
                return left.getSysPermissionId().equals(right.getSysPermissionId());
            }
        };


    @Transactional
    private void processOneToManyBelongtoSysPermissionCollection(SysElement sysElement, SysElement persistedSysElement) {
        Collection<SysElementBelongtoRelationSysPermission> needClearbelongtoSysPermissionCollection = CollectionDiffUtil.inLeftButNotInRiht(persistedSysElement.getBelongtoSysPermissionCollection(),sysElement.getBelongtoSysPermissionCollection(),sysElementBelongtoRelationSysPermissionCompare);
        if (needClearbelongtoSysPermissionCollection != null && needClearbelongtoSysPermissionCollection.size() > 0) {
            sysElementBelongtoRelationSysPermissionJpaRepository.deleteAll(needClearbelongtoSysPermissionCollection);
            persistedSysElement.getBelongtoSysPermissionCollection().removeAll(needClearbelongtoSysPermissionCollection);
        }

        Collection<SysElementBelongtoRelationSysPermission> needProcessBelongtoSysPermissionCollection = CollectionDiffUtil.inLeftButNotInRiht(sysElement.getBelongtoSysPermissionCollection(),persistedSysElement.getBelongtoSysPermissionCollection(),sysElementBelongtoRelationSysPermissionCompare);
        if(needProcessBelongtoSysPermissionCollection != null && needProcessBelongtoSysPermissionCollection.size() > 0){
            for( SysElementBelongtoRelationSysPermission needProcessSysElementBelongtoRelationSysPermission :  needProcessBelongtoSysPermissionCollection){
                SysPermission persistedNeedProcessSysPermission = sysPermissionJpaRepository.findById(needProcessSysElementBelongtoRelationSysPermission.getSysPermissionId())
                    .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                        .generateException("权限 [ {0} ] 不存在", needProcessSysElementBelongtoRelationSysPermission.getSysPermissionId()));

                    SysElementBelongtoRelationSysPermission sysElementBelongtoRelationSysPermission = new SysElementBelongtoRelationSysPermission();
                    sysElementBelongtoRelationSysPermission.setSysElement(persistedSysElement);
                    sysElementBelongtoRelationSysPermission.setSysPermission(persistedNeedProcessSysPermission);
                    sysElementBelongtoRelationSysPermissionJpaRepository.save(sysElementBelongtoRelationSysPermission);
            }
        }
    }
}