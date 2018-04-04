package com.laputa.foundation.web.weixin.service;

import com.laputa.foundation.web.weixin.entity.WeixinBaseConfig;
import com.laputa.foundation.web.weixin.dao.WeixinBaseConfigJpaRepository;
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
* 微信公众号基础配置 Service<br>
* /p>
* Created by JiangDongPing CodeGnerate on 2018-03-07T16:55:09+08:00 .
*/
@Transactional
@Service("weixinBaseConfigService")
public class WeixinBaseConfigService {

    @Resource
    private WeixinBaseConfigJpaRepository weixinBaseConfigJpaRepository;


    @Transactional
    public WeixinBaseConfig create(WeixinBaseConfig weixinBaseConfig) {

        WeixinBaseConfig createWeixinBaseConfig = new WeixinBaseConfig();

        createWeixinBaseConfig.setCname(weixinBaseConfig.getCname());
        createWeixinBaseConfig.setCode(weixinBaseConfig.getCode());
        createWeixinBaseConfig.setDescript(weixinBaseConfig.getDescript());
        createWeixinBaseConfig.setAppid(weixinBaseConfig.getAppid());
        createWeixinBaseConfig.setAppsecret(weixinBaseConfig.getAppsecret());
        createWeixinBaseConfig.setToken(weixinBaseConfig.getToken());

        createWeixinBaseConfig = weixinBaseConfigJpaRepository.save(createWeixinBaseConfig);


        return createWeixinBaseConfig;
    }

    @Transactional
    public void destory(WeixinBaseConfig weixinBaseConfig) {
        WeixinBaseConfig destoryWeixinBaseConfig = weixinBaseConfigJpaRepository.findById(weixinBaseConfig.getId())
            .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.DESTORY_TARGET_NONEXISTENT
                .generateException("微信公众号基础配置 [ {0} ] 无法删除 因为无此记录 可能已经被删除", weixinBaseConfig.getId()));
        weixinBaseConfigJpaRepository.delete(destoryWeixinBaseConfig);
    }


    @Transactional
    public WeixinBaseConfig update(WeixinBaseConfig weixinBaseConfig) {
        WeixinBaseConfig updateWeixinBaseConfig = weixinBaseConfigJpaRepository.findById(weixinBaseConfig.getId())
            .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.UPDATE_TARGET_NONEXISTENT
                .generateException("微信公众号基础配置 [ {0} ] 无法编辑 因为无此记录 可能已经被删除", weixinBaseConfig.getId()));

        updateWeixinBaseConfig.setCname(weixinBaseConfig.getCname());
        updateWeixinBaseConfig.setCode(weixinBaseConfig.getCode());
        updateWeixinBaseConfig.setDescript(weixinBaseConfig.getDescript());
        updateWeixinBaseConfig.setAppid(weixinBaseConfig.getAppid());
        updateWeixinBaseConfig.setAppsecret(weixinBaseConfig.getAppsecret());
        updateWeixinBaseConfig.setToken(weixinBaseConfig.getToken());

        weixinBaseConfigJpaRepository.save(updateWeixinBaseConfig);


        return updateWeixinBaseConfig;
    }


    @Transactional
    public List<WeixinBaseConfig> read() {
        List<WeixinBaseConfig> weixinBaseConfigList = weixinBaseConfigJpaRepository.findAll();
        return weixinBaseConfigList;
    }

    @Transactional
    public List<WeixinBaseConfig> readEager() {
        List<WeixinBaseConfig> weixinBaseConfigList = weixinBaseConfigJpaRepository.findAll();
        if( weixinBaseConfigList != null && weixinBaseConfigList.size() > 0 ){
            for( WeixinBaseConfig weixinBaseConfig :  weixinBaseConfigList ){
            }
        }
        return weixinBaseConfigList;
    }


    @Transactional
    public Page<WeixinBaseConfig> readDataSource(DataSourceRequest dataSourceRequest) {
        LaputaKendoSpecification<WeixinBaseConfig> laputaKendoSpecification = new LaputaKendoSpecification<>(dataSourceRequest);
        Page<WeixinBaseConfig> weixinBaseConfigPage = weixinBaseConfigJpaRepository.findAll(laputaKendoSpecification, laputaKendoSpecification.pageable());
        if (weixinBaseConfigPage != null && weixinBaseConfigPage.getContent() != null && weixinBaseConfigPage.getContent().size() > 0) {
            for (WeixinBaseConfig weixinBaseConfig : weixinBaseConfigPage.getContent()) {
            }
        }
        return weixinBaseConfigPage;
    }



}