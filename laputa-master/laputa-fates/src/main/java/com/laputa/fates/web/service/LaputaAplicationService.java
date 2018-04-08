package com.laputa.fates.web.service;

import com.laputa.foundation.persistence.util.IdEntityDiffUtil;
import com.laputa.fates.web.entity.LaputaAplication;
import com.laputa.fates.web.dao.LaputaAplicationJpaRepository;
import com.laputa.fates.web.entity.LaputaConfig;
import com.laputa.fates.web.dao.LaputaConfigJpaRepository;
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
* Laputa项目 Service<br>
* /p>
* Created by JiangDongPing CodeGnerate on 2018-04-06T10:15:08+08:00 .
*/
@Transactional
@Service("laputaAplicationService")
public class LaputaAplicationService {

    @Resource
    private LaputaAplicationJpaRepository laputaAplicationJpaRepository;


    @Resource
    private LaputaConfigJpaRepository laputaConfigJpaRepository;

    @Transactional
    public LaputaAplication create(LaputaAplication laputaAplication) {

        LaputaAplication createLaputaAplication = new LaputaAplication();

        createLaputaAplication.setCname(laputaAplication.getCname());
        createLaputaAplication.setCode(laputaAplication.getCode());
        createLaputaAplication.setDescript(laputaAplication.getDescript());

        createLaputaAplication = laputaAplicationJpaRepository.save(createLaputaAplication);

        processOneToManyConfigList(laputaAplication,createLaputaAplication);

        return createLaputaAplication;
    }

    @Transactional
    public void destory(LaputaAplication laputaAplication) {
        LaputaAplication destoryLaputaAplication = laputaAplicationJpaRepository.findById(laputaAplication.getId())
            .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.DESTORY_TARGET_NONEXISTENT
                .generateException("Laputa项目 [ {0} ] 无法删除 因为无此记录 可能已经被删除", laputaAplication.getId()));
        laputaAplicationJpaRepository.delete(destoryLaputaAplication);
    }


    @Transactional
    public LaputaAplication update(LaputaAplication laputaAplication) {
        LaputaAplication updateLaputaAplication = laputaAplicationJpaRepository.findById(laputaAplication.getId())
            .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.UPDATE_TARGET_NONEXISTENT
                .generateException("Laputa项目 [ {0} ] 无法编辑 因为无此记录 可能已经被删除", laputaAplication.getId()));

        updateLaputaAplication.setCname(laputaAplication.getCname());
        updateLaputaAplication.setCode(laputaAplication.getCode());
        updateLaputaAplication.setDescript(laputaAplication.getDescript());

        laputaAplicationJpaRepository.save(updateLaputaAplication);

        processOneToManyConfigList(laputaAplication,updateLaputaAplication);

        return updateLaputaAplication;
    }


    @Transactional
    public List<LaputaAplication> read() {
        List<LaputaAplication> laputaAplicationList = laputaAplicationJpaRepository.findAll();
        return laputaAplicationList;
    }

    @Transactional
    public List<LaputaAplication> readEager() {
        List<LaputaAplication> laputaAplicationList = laputaAplicationJpaRepository.findAll();
        if( laputaAplicationList != null && laputaAplicationList.size() > 0 ){
            for( LaputaAplication laputaAplication :  laputaAplicationList ){
                laputaAplication.getConfigList().size();
            }
        }
        return laputaAplicationList;
    }


    @Transactional
    public Page<LaputaAplication> readDataSource(DataSourceRequest dataSourceRequest) {
        LaputaKendoSpecification<LaputaAplication> laputaKendoSpecification = new LaputaKendoSpecification<>(dataSourceRequest);
        Page<LaputaAplication> laputaAplicationPage = laputaAplicationJpaRepository.findAll(laputaKendoSpecification, laputaKendoSpecification.pageable());
        if (laputaAplicationPage != null && laputaAplicationPage.getContent() != null && laputaAplicationPage.getContent().size() > 0) {
            for (LaputaAplication laputaAplication : laputaAplicationPage.getContent()) {
                laputaAplication.getConfigList().size();
            }
        }
        return laputaAplicationPage;
    }



    @Transactional
    private void processOneToManyConfigList(LaputaAplication laputaAplication, LaputaAplication persistedLaputaAplication) {
        Collection<LaputaConfig> needClearconfigList = IdEntityDiffUtil.inLeftButNotInRiht(persistedLaputaAplication.getConfigList(),laputaAplication.getConfigList());
        if (needClearconfigList != null && needClearconfigList.size() > 0) {
            for(LaputaConfig persistedlaputaConfig : needClearconfigList ){
                persistedlaputaConfig.setParentLaputaAplication(null);
            }
            persistedLaputaAplication.getConfigList().removeAll(needClearconfigList);
        }

        Collection<LaputaConfig> needProcessConfigList = IdEntityDiffUtil.inLeftButNotInRiht(laputaAplication.getConfigList(),persistedLaputaAplication.getConfigList());
        if(needProcessConfigList != null && needProcessConfigList.size() > 0){
            for( LaputaConfig needProcessLaputaConfig :  needProcessConfigList){
                LaputaConfig persistedNeedProcessLaputaConfig = laputaConfigJpaRepository.findById(needProcessLaputaConfig.getId())
                    .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                        .generateException("Laputa项目 [ {0} ] 不存在", needProcessLaputaConfig.getId()));
                persistedNeedProcessLaputaConfig.setParentLaputaAplication(persistedLaputaAplication);
            }
        }
    }
}