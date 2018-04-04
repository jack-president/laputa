package com.laputa.foundation.web.generate.service;

import com.laputa.foundation.persistence.util.IdEntityDiffUtil;
import com.laputa.foundation.web.generate.entity.SysHelloDate;
import com.laputa.foundation.web.generate.dao.SysHelloDateJpaRepository;
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
* Hi时间 Service<br>
* /p>
* Created by JiangDongPing CodeGnerate on 2016-12-07T16:50:06+08:00 .
*/
@Transactional
@Service("sysHelloDateService")
public class SysHelloDateService {

    @Resource
    private SysHelloDateJpaRepository sysHelloDateJpaRepository;


    @Transactional
    public SysHelloDate create(SysHelloDate sysHelloDate) {

        SysHelloDate createSysHelloDate = new SysHelloDate();

        createSysHelloDate.setCname(sysHelloDate.getCname());
        createSysHelloDate.setWakeup(sysHelloDate.getWakeup());
        createSysHelloDate.setBirthDate(sysHelloDate.getBirthDate());
        createSysHelloDate.setAcTime(sysHelloDate.getAcTime());

        createSysHelloDate = sysHelloDateJpaRepository.save(createSysHelloDate);


        return createSysHelloDate;
    }

    @Transactional
    public void destory(SysHelloDate sysHelloDate) {
        SysHelloDate destorySysHelloDate = sysHelloDateJpaRepository.findById(sysHelloDate.getId()).get();
        if( destorySysHelloDate != null ){
            sysHelloDateJpaRepository.delete(destorySysHelloDate);
        }else{
            throw new RuntimeException("Hi时间 无法删除 因为无此记录 可能已经被删除");
        }
    }


    @Transactional
    public SysHelloDate update(SysHelloDate sysHelloDate) {
        SysHelloDate updateSysHelloDate = sysHelloDateJpaRepository.findById(sysHelloDate.getId()).get();
        if( updateSysHelloDate == null ){
            throw new RuntimeException("Hi时间 无法编辑 因为无此记录 可能已经被删除");
        }

        updateSysHelloDate.setCname(sysHelloDate.getCname());
        updateSysHelloDate.setWakeup(sysHelloDate.getWakeup());
        updateSysHelloDate.setBirthDate(sysHelloDate.getBirthDate());
        updateSysHelloDate.setAcTime(sysHelloDate.getAcTime());

        sysHelloDateJpaRepository.save(updateSysHelloDate);


        return updateSysHelloDate;
    }


    @Transactional
    public List<SysHelloDate> read() {
        List<SysHelloDate> sysHelloDateList = sysHelloDateJpaRepository.findAll();
        return sysHelloDateList;
    }

    @Transactional
    public List<SysHelloDate> readEager() {
        List<SysHelloDate> sysHelloDateList = sysHelloDateJpaRepository.findAll();
        if( sysHelloDateList != null && sysHelloDateList.size() > 0 ){
            for( SysHelloDate sysHelloDate :  sysHelloDateList ){
            }
        }
        return sysHelloDateList;
    }


    @Transactional
    public Page<SysHelloDate> readDataSource(DataSourceRequest dataSourceRequest) {
        LaputaKendoSpecification<SysHelloDate> laputaKendoSpecification = new LaputaKendoSpecification<>(dataSourceRequest);
        Page<SysHelloDate> sysHelloDatePage = sysHelloDateJpaRepository.findAll(laputaKendoSpecification, laputaKendoSpecification.pageable());
        if (sysHelloDatePage != null && sysHelloDatePage.getContent() != null && sysHelloDatePage.getContent().size() > 0) {
            for (SysHelloDate sysHelloDate : sysHelloDatePage.getContent()) {
            }
        }
        return sysHelloDatePage;
    }




}