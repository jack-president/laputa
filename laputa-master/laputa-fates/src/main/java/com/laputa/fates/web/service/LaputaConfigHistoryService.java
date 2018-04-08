package com.laputa.fates.web.service;

import com.laputa.fates.web.entity.LaputaConfigHistory;
import com.laputa.fates.web.dao.LaputaConfigHistoryJpaRepository;
import com.laputa.fates.web.entity.LaputaConfig;
import com.laputa.fates.web.dao.LaputaConfigJpaRepository;
import com.laputa.foundation.web.exception.LaputaWebOperationException;
import com.laputa.foundation.web.kendo.model.DataSourceRequest;
import com.laputa.foundation.web.kendo.specification.LaputaKendoSpecification;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* <p>
* Laputa项目 Service<br>
* /p>
* Created by JiangDongPing CodeGnerate on 2018-04-06T10:15:11+08:00 .
*/
@Transactional
@Service("laputaConfigHistoryService")
public class LaputaConfigHistoryService {

    @Resource
    private LaputaConfigHistoryJpaRepository laputaConfigHistoryJpaRepository;


    @Resource
    private LaputaConfigJpaRepository laputaConfigJpaRepository;

    @Transactional
    public LaputaConfigHistory create(LaputaConfigHistory laputaConfigHistory) {

        LaputaConfigHistory createLaputaConfigHistory = new LaputaConfigHistory();

        createLaputaConfigHistory.setConfigValue(laputaConfigHistory.getConfigValue());
        createLaputaConfigHistory.setDescript(laputaConfigHistory.getDescript());
        if( laputaConfigHistory.getParentLaputaConfigId() != null ){
            LaputaConfig parentLaputaConfig = laputaConfigJpaRepository.findById(laputaConfigHistory.getParentLaputaConfigId())
                    .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                        .generateException("[ 配置{0} ] 无法配置 因为无此记录 可能已经被删除", laputaConfigHistory.getParentLaputaConfigId()));
            createLaputaConfigHistory.setParentLaputaConfig(parentLaputaConfig);
        }
        


        createLaputaConfigHistory = laputaConfigHistoryJpaRepository.save(createLaputaConfigHistory);


        return createLaputaConfigHistory;
    }

    @Transactional
    public void destory(LaputaConfigHistory laputaConfigHistory) {
        LaputaConfigHistory destoryLaputaConfigHistory = laputaConfigHistoryJpaRepository.findById(laputaConfigHistory.getId())
            .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.DESTORY_TARGET_NONEXISTENT
                .generateException("Laputa项目 [ {0} ] 无法删除 因为无此记录 可能已经被删除", laputaConfigHistory.getId()));
        laputaConfigHistoryJpaRepository.delete(destoryLaputaConfigHistory);
    }


    @Transactional
    public LaputaConfigHistory update(LaputaConfigHistory laputaConfigHistory) {
        LaputaConfigHistory updateLaputaConfigHistory = laputaConfigHistoryJpaRepository.findById(laputaConfigHistory.getId())
            .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.UPDATE_TARGET_NONEXISTENT
                .generateException("Laputa项目 [ {0} ] 无法编辑 因为无此记录 可能已经被删除", laputaConfigHistory.getId()));

        updateLaputaConfigHistory.setConfigValue(laputaConfigHistory.getConfigValue());
        updateLaputaConfigHistory.setDescript(laputaConfigHistory.getDescript());
        if( laputaConfigHistory.getParentLaputaConfigId() != null ){
            if(!laputaConfigHistory.getParentLaputaConfigId().equals(updateLaputaConfigHistory.getParentLaputaConfigId()) ){
                LaputaConfig parentLaputaConfig = laputaConfigJpaRepository.findById(laputaConfigHistory.getParentLaputaConfigId())
                    .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                        .generateException("Laputa项目 配置 Laputa项目 [ {0} ] 无法更新 因为无此记录 可能已经被删除", laputaConfigHistory.getParentLaputaConfigId()));
                updateLaputaConfigHistory.setParentLaputaConfig(parentLaputaConfig);
        }
        }else{
            updateLaputaConfigHistory.setParentLaputaConfig(null);
        }


        laputaConfigHistoryJpaRepository.save(updateLaputaConfigHistory);


        return updateLaputaConfigHistory;
    }


    @Transactional
    public List<LaputaConfigHistory> read() {
        List<LaputaConfigHistory> laputaConfigHistoryList = laputaConfigHistoryJpaRepository.findAll();
        return laputaConfigHistoryList;
    }

    @Transactional
    public List<LaputaConfigHistory> readEager() {
        List<LaputaConfigHistory> laputaConfigHistoryList = laputaConfigHistoryJpaRepository.findAll();
        if( laputaConfigHistoryList != null && laputaConfigHistoryList.size() > 0 ){
            for( LaputaConfigHistory laputaConfigHistory :  laputaConfigHistoryList ){
            }
        }
        return laputaConfigHistoryList;
    }


    @Transactional
    public Page<LaputaConfigHistory> readDataSource(DataSourceRequest dataSourceRequest) {
        LaputaKendoSpecification<LaputaConfigHistory> laputaKendoSpecification = new LaputaKendoSpecification<>(dataSourceRequest);
        Page<LaputaConfigHistory> laputaConfigHistoryPage = laputaConfigHistoryJpaRepository.findAll(laputaKendoSpecification, laputaKendoSpecification.pageable());
        if (laputaConfigHistoryPage != null && laputaConfigHistoryPage.getContent() != null && laputaConfigHistoryPage.getContent().size() > 0) {
            for (LaputaConfigHistory laputaConfigHistory : laputaConfigHistoryPage.getContent()) {
            }
        }
        return laputaConfigHistoryPage;
    }



}