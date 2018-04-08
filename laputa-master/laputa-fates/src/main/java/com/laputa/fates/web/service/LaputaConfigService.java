package com.laputa.fates.web.service;

import com.laputa.foundation.persistence.util.IdEntityDiffUtil;
import com.laputa.fates.web.entity.LaputaConfig;
import com.laputa.fates.web.dao.LaputaConfigJpaRepository;
import com.laputa.fates.web.entity.LaputaAplication;
import com.laputa.fates.web.dao.LaputaAplicationJpaRepository;
import com.laputa.fates.web.entity.LaputaConfigHistory;
import com.laputa.fates.web.dao.LaputaConfigHistoryJpaRepository;
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
* Created by JiangDongPing CodeGnerate on 2018-04-06T10:15:10+08:00 .
*/
@Transactional
@Service("laputaConfigService")
public class LaputaConfigService {

    @Resource
    private LaputaConfigJpaRepository laputaConfigJpaRepository;


    @Resource
    private LaputaAplicationJpaRepository laputaAplicationJpaRepository;

    @Resource
    private LaputaConfigHistoryJpaRepository laputaConfigHistoryJpaRepository;

    @Transactional
    public LaputaConfig create(LaputaConfig laputaConfig) {

        LaputaConfig createLaputaConfig = new LaputaConfig();

        createLaputaConfig.setCname(laputaConfig.getCname());
        createLaputaConfig.setCode(laputaConfig.getCode());
        if( laputaConfig.getParentLaputaAplicationId() != null ){
            LaputaAplication parentLaputaAplication = laputaAplicationJpaRepository.findById(laputaConfig.getParentLaputaAplicationId())
                    .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                        .generateException("[ 所属项目{0} ] 无法配置 因为无此记录 可能已经被删除", laputaConfig.getParentLaputaAplicationId()));
            createLaputaConfig.setParentLaputaAplication(parentLaputaAplication);
        }
        

        createLaputaConfig.setConfigValue(laputaConfig.getConfigValue());
        createLaputaConfig.setDescript(laputaConfig.getDescript());

        createLaputaConfig = laputaConfigJpaRepository.save(createLaputaConfig);

        processOneToManyConfigHistoryList(laputaConfig,createLaputaConfig);

        return createLaputaConfig;
    }

    @Transactional
    public void destory(LaputaConfig laputaConfig) {
        LaputaConfig destoryLaputaConfig = laputaConfigJpaRepository.findById(laputaConfig.getId())
            .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.DESTORY_TARGET_NONEXISTENT
                .generateException("Laputa项目 [ {0} ] 无法删除 因为无此记录 可能已经被删除", laputaConfig.getId()));
        laputaConfigJpaRepository.delete(destoryLaputaConfig);
    }


    @Transactional
    public LaputaConfig update(LaputaConfig laputaConfig) {
        LaputaConfig updateLaputaConfig = laputaConfigJpaRepository.findById(laputaConfig.getId())
            .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.UPDATE_TARGET_NONEXISTENT
                .generateException("Laputa项目 [ {0} ] 无法编辑 因为无此记录 可能已经被删除", laputaConfig.getId()));

        updateLaputaConfig.setCname(laputaConfig.getCname());
        updateLaputaConfig.setCode(laputaConfig.getCode());
        if( laputaConfig.getParentLaputaAplicationId() != null ){
            if(!laputaConfig.getParentLaputaAplicationId().equals(updateLaputaConfig.getParentLaputaAplicationId()) ){
                LaputaAplication parentLaputaAplication = laputaAplicationJpaRepository.findById(laputaConfig.getParentLaputaAplicationId())
                    .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                        .generateException("Laputa项目 所属项目 Laputa项目 [ {0} ] 无法更新 因为无此记录 可能已经被删除", laputaConfig.getParentLaputaAplicationId()));
                updateLaputaConfig.setParentLaputaAplication(parentLaputaAplication);
        }
        }else{
            updateLaputaConfig.setParentLaputaAplication(null);
        }

        updateLaputaConfig.setConfigValue(laputaConfig.getConfigValue());
        updateLaputaConfig.setDescript(laputaConfig.getDescript());

        laputaConfigJpaRepository.save(updateLaputaConfig);

        processOneToManyConfigHistoryList(laputaConfig,updateLaputaConfig);

        return updateLaputaConfig;
    }


    @Transactional
    public List<LaputaConfig> read() {
        List<LaputaConfig> laputaConfigList = laputaConfigJpaRepository.findAll();
        return laputaConfigList;
    }

    @Transactional
    public List<LaputaConfig> readEager() {
        List<LaputaConfig> laputaConfigList = laputaConfigJpaRepository.findAll();
        if( laputaConfigList != null && laputaConfigList.size() > 0 ){
            for( LaputaConfig laputaConfig :  laputaConfigList ){
                laputaConfig.getConfigHistoryList().size();
            }
        }
        return laputaConfigList;
    }


    @Transactional
    public Page<LaputaConfig> readDataSource(DataSourceRequest dataSourceRequest) {
        LaputaKendoSpecification<LaputaConfig> laputaKendoSpecification = new LaputaKendoSpecification<>(dataSourceRequest);
        Page<LaputaConfig> laputaConfigPage = laputaConfigJpaRepository.findAll(laputaKendoSpecification, laputaKendoSpecification.pageable());
        if (laputaConfigPage != null && laputaConfigPage.getContent() != null && laputaConfigPage.getContent().size() > 0) {
            for (LaputaConfig laputaConfig : laputaConfigPage.getContent()) {
                laputaConfig.getConfigHistoryList().size();
            }
        }
        return laputaConfigPage;
    }



    @Transactional
    private void processOneToManyConfigHistoryList(LaputaConfig laputaConfig, LaputaConfig persistedLaputaConfig) {
        Collection<LaputaConfigHistory> needClearconfigHistoryList = IdEntityDiffUtil.inLeftButNotInRiht(persistedLaputaConfig.getConfigHistoryList(),laputaConfig.getConfigHistoryList());
        if (needClearconfigHistoryList != null && needClearconfigHistoryList.size() > 0) {
            for(LaputaConfigHistory persistedlaputaConfigHistory : needClearconfigHistoryList ){
                persistedlaputaConfigHistory.setParentLaputaConfig(null);
            }
            persistedLaputaConfig.getConfigHistoryList().removeAll(needClearconfigHistoryList);
        }

        Collection<LaputaConfigHistory> needProcessConfigHistoryList = IdEntityDiffUtil.inLeftButNotInRiht(laputaConfig.getConfigHistoryList(),persistedLaputaConfig.getConfigHistoryList());
        if(needProcessConfigHistoryList != null && needProcessConfigHistoryList.size() > 0){
            for( LaputaConfigHistory needProcessLaputaConfigHistory :  needProcessConfigHistoryList){
                LaputaConfigHistory persistedNeedProcessLaputaConfigHistory = laputaConfigHistoryJpaRepository.findById(needProcessLaputaConfigHistory.getId())
                    .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                        .generateException("Laputa项目 [ {0} ] 不存在", needProcessLaputaConfigHistory.getId()));
                persistedNeedProcessLaputaConfigHistory.setParentLaputaConfig(persistedLaputaConfig);
            }
        }
    }
}