package com.laputa.fates.web.service;

import com.laputa.fates.web.dao.ConfigByApplicationJpaRepository;
import com.laputa.fates.web.dao.LaputaAplicationJpaRepository;
import com.laputa.fates.web.dao.LaputaConfigHistoryJpaRepository;
import com.laputa.fates.web.dao.LaputaConfigJpaRepository;
import com.laputa.fates.web.entity.LaputaAplication;
import com.laputa.fates.web.entity.LaputaConfig;
import com.laputa.fates.web.entity.LaputaConfigHistory;
import com.laputa.foundation.persistence.util.IdEntityDiffUtil;
import com.laputa.foundation.web.exception.LaputaWebOperationException;
import com.laputa.foundation.web.kendo.model.DataSourceRequest;
import com.laputa.foundation.web.kendo.specification.LaputaKendoSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * Laputa项目 Service<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-04-06T10:15:10+08:00 .
 */
@Transactional
@Service("configByApplicationService")
public class ConfigByApplicationService {

    @Resource
    private ConfigByApplicationJpaRepository configByApplicationJpaRepository;

    @Resource
    private LaputaAplicationJpaRepository laputaAplicationJpaRepository;

    @Resource
    private LaputaConfigHistoryJpaRepository laputaConfigHistoryJpaRepository;


    @Resource
    private LaputaConfigJpaRepository laputaConfigJpaRepository;


    public LaputaAplication getLaputaAplicationBylaputaAplicationCode(String laputaAplicationCode) {
        LaputaAplication laputaAplication = configByApplicationJpaRepository.selectLaputaAplicationBylaputaAplicationCode(laputaAplicationCode);
        if (laputaAplication == null) {
            throw LaputaWebOperationException.ExceptionEnum.NORMAL_OPERATION_FAIL.generateException("{0} 项目不在", laputaAplicationCode);
        }
        return laputaAplication;
    }

    public Page<LaputaConfig> readDataSource(String laputaAplicationCode, DataSourceRequest dataSourceRequest) {

        DataSourceRequest.FilterDescriptor filterDescriptor = new DataSourceRequest.FilterDescriptor();
        filterDescriptor.setLogic("and");
        dataSourceRequest.setFilter(filterDescriptor);

        DataSourceRequest.FilterDescriptor parentLaputaAplicationIdFilterDescriptor = new DataSourceRequest.FilterDescriptor();
        parentLaputaAplicationIdFilterDescriptor.setField("parentLaputaAplicationId");
        parentLaputaAplicationIdFilterDescriptor.setOperator("eq");
        parentLaputaAplicationIdFilterDescriptor.setValue(getLaputaAplicationBylaputaAplicationCode(laputaAplicationCode).getId().toString());

        filterDescriptor.getFilters().add(parentLaputaAplicationIdFilterDescriptor);
        if (dataSourceRequest.getFilter() != null
                && !CollectionUtils.isEmpty(dataSourceRequest.getFilter().getFilters())) {
            filterDescriptor.getFilters().addAll(dataSourceRequest.getFilter().getFilters());
        }


        LaputaKendoSpecification<LaputaConfig> laputaKendoSpecification = new LaputaKendoSpecification<>(dataSourceRequest);
        Page<LaputaConfig> laputaConfigPage = laputaConfigJpaRepository.findAll(laputaKendoSpecification, laputaKendoSpecification.pageable());
        if (laputaConfigPage != null && laputaConfigPage.getContent() != null && laputaConfigPage.getContent().size() > 0) {
            for (LaputaConfig laputaConfig : laputaConfigPage.getContent()) {
                laputaConfig.getConfigHistoryList().size();
            }
        }
        return laputaConfigPage;
    }


    public Page<LaputaConfigHistory> readHistoryDataSource(String laputaAplicationCode, Long laputaConfigId, DataSourceRequest dataSourceRequest) {
        getLaputaAplicationBylaputaAplicationCode(laputaAplicationCode);

        DataSourceRequest.FilterDescriptor filterDescriptor = new DataSourceRequest.FilterDescriptor();
        filterDescriptor.setLogic("and");
        DataSourceRequest.FilterDescriptor parentLaputaAplicationIdFilterDescriptor = new DataSourceRequest.FilterDescriptor();
        parentLaputaAplicationIdFilterDescriptor.setField("parentLaputaConfigId");
        parentLaputaAplicationIdFilterDescriptor.setOperator("eq");
        parentLaputaAplicationIdFilterDescriptor.setValue(laputaConfigId);

        filterDescriptor.getFilters().add(parentLaputaAplicationIdFilterDescriptor);
        if (dataSourceRequest.getFilter() != null
                && !CollectionUtils.isEmpty(dataSourceRequest.getFilter().getFilters())) {
            filterDescriptor.getFilters().addAll(dataSourceRequest.getFilter().getFilters());
        }
        dataSourceRequest.setFilter(filterDescriptor);

        if (CollectionUtils.isEmpty(dataSourceRequest.getSort())) {
            DataSourceRequest.SortDescriptor sortDescriptor = new DataSourceRequest.GroupDescriptor();
            sortDescriptor.setDir("DESC");
            sortDescriptor.setField("lastModifiedDate");
            dataSourceRequest.setSort(Arrays.asList(sortDescriptor));
        }

        LaputaKendoSpecification<LaputaConfigHistory> laputaKendoSpecification = new LaputaKendoSpecification<>(dataSourceRequest);
        Page<LaputaConfigHistory> laputaConfigHistoryPage = laputaConfigHistoryJpaRepository.findAll(laputaKendoSpecification, laputaKendoSpecification.pageable());

        return laputaConfigHistoryPage;
    }


    @Transactional
    public LaputaConfig create(String laputaAplicationCode, LaputaConfig laputaConfig) {


        LaputaConfig createLaputaConfig = new LaputaConfig();

        createLaputaConfig.setCname(laputaConfig.getCname());
        createLaputaConfig.setCode(laputaConfig.getCode());
        createLaputaConfig.setParentLaputaAplicationId(getLaputaAplicationBylaputaAplicationCode(laputaAplicationCode).getId());


        LaputaAplication parentLaputaAplication = laputaAplicationJpaRepository.findById(laputaConfig.getParentLaputaAplicationId())
                .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                        .generateException("[ 所属项目{0} ] 无法配置 因为无此记录 可能已经被删除", laputaConfig.getParentLaputaAplicationId()));
        createLaputaConfig.setParentLaputaAplication(parentLaputaAplication);


        createLaputaConfig.setConfigValue(laputaConfig.getConfigValue());
        createLaputaConfig.setDescript(laputaConfig.getDescript());

        createLaputaConfig = laputaConfigJpaRepository.save(createLaputaConfig);

        generateLaputaConfigHistory(createLaputaConfig, "初始化");

        return createLaputaConfig;
    }


    @Transactional
    public LaputaConfig update(String laputaAplicationCode, Long laputaConfigId, String configValue, String causeDescript) {
        getLaputaAplicationBylaputaAplicationCode(laputaAplicationCode);

        LaputaConfig laputaConfig = laputaConfigJpaRepository.findById(laputaConfigId)
                .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                        .generateException("{0} 配置 {1} 不存在", laputaAplicationCode, laputaConfigId));

        laputaConfig.setConfigValue(configValue);

        laputaConfigJpaRepository.save(laputaConfig);

        generateLaputaConfigHistory(laputaConfig, causeDescript);

        return laputaConfig;
    }

    private LaputaConfigHistory generateLaputaConfigHistory(LaputaConfig laputaConfig, String causeDescript) {
        LaputaConfigHistory laputaConfigHistory = new LaputaConfigHistory();
        laputaConfigHistory.setParentLaputaConfig(laputaConfig);
        laputaConfigHistory.setConfigValue(laputaConfig.getConfigValue());
        laputaConfigHistory.setDescript(causeDescript);
        return laputaConfigHistoryJpaRepository.save(laputaConfigHistory);
    }


}