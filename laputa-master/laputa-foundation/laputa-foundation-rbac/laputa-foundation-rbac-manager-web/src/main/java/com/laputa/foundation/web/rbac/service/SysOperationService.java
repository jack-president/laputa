package com.laputa.foundation.web.rbac.service;

import com.laputa.foundation.persistence.util.IdEntityDiffUtil;
import com.laputa.foundation.persistence.util.ParentAbleIdEntityCheckCircularReferenceUtil;
import com.laputa.foundation.web.rbac.entity.SysOperation;
import com.laputa.foundation.web.rbac.dao.SysOperationJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysOperationBelongtoRelationSysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.entity.SysPermission;
import com.laputa.foundation.web.rbac.dao.SysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysOperationBelongtoRelationSysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.entity.SysOperationBelongtoRelationSysPermission;

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
* 功能操作 Service<br>
* /p>
* Created by JiangDongPing CodeGnerate on 2018-03-07T16:26:11+08:00 .
*/
@Transactional
@Service("sysOperationService")
public class SysOperationService {

    @Resource
    private SysOperationJpaRepository sysOperationJpaRepository;


    @Resource
    private SysPermissionJpaRepository sysPermissionJpaRepository;

    @Resource
    private SysOperationBelongtoRelationSysPermissionJpaRepository sysOperationBelongtoRelationSysPermissionJpaRepository;

    @Transactional
    public SysOperation create(SysOperation sysOperation) {

        SysOperation createSysOperation = new SysOperation();

        createSysOperation.setCname(sysOperation.getCname());
        createSysOperation.setCode(sysOperation.getCode());
        createSysOperation.setDescript(sysOperation.getDescript());
        createSysOperation.setPrefixUrl(sysOperation.getPrefixUrl());
        if( sysOperation.getParentId() != null ){
        SysOperation parent = sysOperationJpaRepository.findById(sysOperation.getParentId())
                .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_PARENT_NONEXISTENT
                    .generateException("[ 父操作{0} ] 无法配置 因为无此记录 可能已经被删除", sysOperation.getParentId()));
            createSysOperation.setParent(parent);
        }
        


        createSysOperation = sysOperationJpaRepository.save(createSysOperation);

        processOneToManyChildren(sysOperation,createSysOperation);
        processOneToManyBelongtoSysPermissionCollection(sysOperation,createSysOperation);

        return createSysOperation;
    }

    @Transactional
    public void destory(SysOperation sysOperation) {
        SysOperation destorySysOperation = sysOperationJpaRepository.findById(sysOperation.getId())
            .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.DESTORY_TARGET_NONEXISTENT
                .generateException("功能操作 [ {0} ] 无法删除 因为无此记录 可能已经被删除", sysOperation.getId()));
        sysOperationJpaRepository.delete(destorySysOperation);
    }


    @Transactional
    public SysOperation update(SysOperation sysOperation) {
        SysOperation updateSysOperation = sysOperationJpaRepository.findById(sysOperation.getId())
            .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.UPDATE_TARGET_NONEXISTENT
                .generateException("功能操作 [ {0} ] 无法编辑 因为无此记录 可能已经被删除", sysOperation.getId()));

        updateSysOperation.setCname(sysOperation.getCname());
        updateSysOperation.setCode(sysOperation.getCode());
        updateSysOperation.setDescript(sysOperation.getDescript());
        updateSysOperation.setPrefixUrl(sysOperation.getPrefixUrl());
        if( sysOperation.getParentId() != null ){
            if(!sysOperation.getParentId().equals(updateSysOperation.getParentId()) ){
                SysOperation parent = sysOperationJpaRepository.findById(sysOperation.getParentId())
                    .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_PARENT_NONEXISTENT
                        .generateException("功能操作 父操作 功能操作 [ {0} ] 无法更新 因为无此记录 可能已经被删除", sysOperation.getParentId()));

                if(ParentAbleIdEntityCheckCircularReferenceUtil.checkCircularReference(sysOperation.getId(),parent)){
                    throw LaputaWebOperationException.ExceptionEnum.RELATION_EXIS_CIRCLE.generateException( "{0}  同 {1} 存在嵌套关系",updateSysOperation.getCname(),parent.getCname());
                }
                updateSysOperation.setParent(parent);
        }
        }else{
            updateSysOperation.setParent(null);
        }


        sysOperationJpaRepository.save(updateSysOperation);

        processOneToManyChildren(sysOperation,updateSysOperation);
        processOneToManyBelongtoSysPermissionCollection(sysOperation,updateSysOperation);

        return updateSysOperation;
    }


    @Transactional
    public List<SysOperation> read() {
        List<SysOperation> sysOperationList = sysOperationJpaRepository.findAll();
        return sysOperationList;
    }

    @Transactional
    public List<SysOperation> readEager() {
        List<SysOperation> sysOperationList = sysOperationJpaRepository.findAll();
        if( sysOperationList != null && sysOperationList.size() > 0 ){
            for( SysOperation sysOperation :  sysOperationList ){
                sysOperation.getChildren().size();
                sysOperation.getBelongtoSysPermissionCollection().size();
            }
        }
        return sysOperationList;
    }


    @Transactional
    public Page<SysOperation> readDataSource(DataSourceRequest dataSourceRequest) {
        LaputaKendoSpecification<SysOperation> laputaKendoSpecification = new LaputaKendoSpecification<>(dataSourceRequest);
        Page<SysOperation> sysOperationPage = sysOperationJpaRepository.findAll(laputaKendoSpecification, laputaKendoSpecification.pageable());
        if (sysOperationPage != null && sysOperationPage.getContent() != null && sysOperationPage.getContent().size() > 0) {
            for (SysOperation sysOperation : sysOperationPage.getContent()) {
                sysOperation.getChildren().size();
                sysOperation.getBelongtoSysPermissionCollection().size();
            }
        }
        return sysOperationPage;
    }

    CollectionDiffUtil.Compare<SysOperationBelongtoRelationSysPermission> sysOperationBelongtoRelationSysPermissionCompare = new CollectionDiffUtil.Compare<SysOperationBelongtoRelationSysPermission>() {
            @Override
            public Boolean equal(SysOperationBelongtoRelationSysPermission left, SysOperationBelongtoRelationSysPermission right) {
                return left.getSysPermissionId().equals(right.getSysPermissionId());
            }
        };


    @Transactional
    private void processOneToManyChildren(SysOperation sysOperation, SysOperation persistedSysOperation) {
        Collection<SysOperation> needClearchildren = IdEntityDiffUtil.inLeftButNotInRiht(persistedSysOperation.getChildren(),sysOperation.getChildren());
        if (needClearchildren != null && needClearchildren.size() > 0) {
            for(SysOperation persistedsysOperation : needClearchildren ){
                persistedsysOperation.setParent(null);
            }
            persistedSysOperation.getChildren().removeAll(needClearchildren);
        }

        Collection<SysOperation> needProcessChildren = IdEntityDiffUtil.inLeftButNotInRiht(sysOperation.getChildren(),persistedSysOperation.getChildren());
        if(needProcessChildren != null && needProcessChildren.size() > 0){
            for( SysOperation needProcessSysOperation :  needProcessChildren){
                SysOperation persistedNeedProcessSysOperation = sysOperationJpaRepository.findById(needProcessSysOperation.getId())
                    .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                        .generateException("功能操作 [ {0} ] 不存在", needProcessSysOperation.getId()));
                if(ParentAbleIdEntityCheckCircularReferenceUtil.checkCircularReference(persistedNeedProcessSysOperation.getId(),persistedSysOperation)){
                    throw LaputaWebOperationException.ExceptionEnum.RELATION_EXIS_CIRCLE.generateException("{0}  同 {1} 存在嵌套关系", persistedNeedProcessSysOperation.getCname(),persistedSysOperation.getCname());
                }
                persistedNeedProcessSysOperation.setParent(persistedSysOperation);
            }
        }
    }
    @Transactional
    private void processOneToManyBelongtoSysPermissionCollection(SysOperation sysOperation, SysOperation persistedSysOperation) {
        Collection<SysOperationBelongtoRelationSysPermission> needClearbelongtoSysPermissionCollection = CollectionDiffUtil.inLeftButNotInRiht(persistedSysOperation.getBelongtoSysPermissionCollection(),sysOperation.getBelongtoSysPermissionCollection(),sysOperationBelongtoRelationSysPermissionCompare);
        if (needClearbelongtoSysPermissionCollection != null && needClearbelongtoSysPermissionCollection.size() > 0) {
            sysOperationBelongtoRelationSysPermissionJpaRepository.deleteAll(needClearbelongtoSysPermissionCollection);
            persistedSysOperation.getBelongtoSysPermissionCollection().removeAll(needClearbelongtoSysPermissionCollection);
        }

        Collection<SysOperationBelongtoRelationSysPermission> needProcessBelongtoSysPermissionCollection = CollectionDiffUtil.inLeftButNotInRiht(sysOperation.getBelongtoSysPermissionCollection(),persistedSysOperation.getBelongtoSysPermissionCollection(),sysOperationBelongtoRelationSysPermissionCompare);
        if(needProcessBelongtoSysPermissionCollection != null && needProcessBelongtoSysPermissionCollection.size() > 0){
            for( SysOperationBelongtoRelationSysPermission needProcessSysOperationBelongtoRelationSysPermission :  needProcessBelongtoSysPermissionCollection){
                SysPermission persistedNeedProcessSysPermission = sysPermissionJpaRepository.findById(needProcessSysOperationBelongtoRelationSysPermission.getSysPermissionId())
                    .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                        .generateException("权限 [ {0} ] 不存在", needProcessSysOperationBelongtoRelationSysPermission.getSysPermissionId()));

                    SysOperationBelongtoRelationSysPermission sysOperationBelongtoRelationSysPermission = new SysOperationBelongtoRelationSysPermission();
                    sysOperationBelongtoRelationSysPermission.setSysOperation(persistedSysOperation);
                    sysOperationBelongtoRelationSysPermission.setSysPermission(persistedNeedProcessSysPermission);
                    sysOperationBelongtoRelationSysPermissionJpaRepository.save(sysOperationBelongtoRelationSysPermission);
            }
        }
    }
}