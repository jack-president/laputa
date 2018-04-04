package com.laputa.foundation.web.generate.service;

import com.laputa.foundation.persistence.util.IdEntityDiffUtil;
import com.laputa.foundation.web.generate.entity.SysField;
import com.laputa.foundation.web.generate.dao.SysFieldJpaRepository;
import com.laputa.foundation.web.generate.entity.SysEntity;
import com.laputa.foundation.web.generate.dao.SysEntityJpaRepository;
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
* <p>
* 实体表 Service<br>
* /p>
* Created by JiangDongPing CodeGnerate on 2016-11-30T17:23:51+08:00 .
*/
@Transactional
@Service("sysFieldService")
public class SysFieldService {

    @Resource
    private SysFieldJpaRepository sysFieldJpaRepository;


    @Resource
    private SysEntityJpaRepository sysEntityJpaRepository;

    @Transactional
    public SysField create(SysField sysField) {

        SysField createSysField = new SysField();

        createSysField.setCname(sysField.getCname());
        if( sysField.getRelationSysEntityId() != null ){
            SysEntity relationSysEntity = sysEntityJpaRepository.findById(sysField.getRelationSysEntityId()).get();
            if(relationSysEntity == null){
                throw new RuntimeException("字段关联实体" + sysField.getRelationSysEntityId() + " 无法配置 因为无此记录 可能已经被删除");
            }
            createSysField.setRelationSysEntity(relationSysEntity);
        }


        createSysField = sysFieldJpaRepository.save(createSysField);


        return createSysField;
    }

    @Transactional
    public void destory(SysField sysField) {
        SysField destorySysField = sysFieldJpaRepository.findById(sysField.getId()).get();
        if( destorySysField != null ){
            sysFieldJpaRepository.delete(destorySysField);
        }else{
            throw new RuntimeException("实体表 无法删除 因为无此记录 可能已经被删除");
        }
    }


    @Transactional
    public SysField update(SysField sysField) {
        SysField updateSysField = sysFieldJpaRepository.findById(sysField.getId()).get();
        if( updateSysField == null ){
            throw new RuntimeException("实体表 无法编辑 因为无此记录 可能已经被删除");
        }

        updateSysField.setCname(sysField.getCname());
        if( sysField.getRelationSysEntityId() != null ){
            if(!sysField.getRelationSysEntityId().equals(updateSysField.getRelationSysEntityId()) ){
                    SysEntity relationSysEntity = sysEntityJpaRepository.findById(sysField.getRelationSysEntityId()).get();
                    if(relationSysEntity == null){
                        throw new RuntimeException("实体表 字段关联实体 实体表" + sysField.getRelationSysEntityId() + " 无法更新 因为无此记录 可能已经被删除");
                    }
                    updateSysField.setRelationSysEntity(relationSysEntity);
            }
        }else{
            updateSysField.setRelationSysEntity(null);
        }


        sysFieldJpaRepository.save(updateSysField);


        return updateSysField;
    }


    @Transactional
    public List<SysField> read() {
        List<SysField> sysFieldList = sysFieldJpaRepository.findAll();
        return sysFieldList;
    }

    @Transactional
    public List<SysField> readEager() {
        List<SysField> sysFieldList = sysFieldJpaRepository.findAll();
        if( sysFieldList != null && sysFieldList.size() > 0 ){
            for( SysField sysField :  sysFieldList ){
            }
        }
        return sysFieldList;
    }


    @Transactional
    public Page<SysField> readDataSource(DataSourceRequest dataSourceRequest) {
        LaputaKendoSpecification<SysField> laputaKendoSpecification = new LaputaKendoSpecification<>(dataSourceRequest);
        Page<SysField> sysFieldPage = sysFieldJpaRepository.findAll(laputaKendoSpecification, laputaKendoSpecification.pageable());
        if (sysFieldPage != null && sysFieldPage.getContent() != null && sysFieldPage.getContent().size() > 0) {
            for (SysField sysField : sysFieldPage.getContent()) {
            }
        }
        return sysFieldPage;
    }


}