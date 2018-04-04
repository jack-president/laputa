package com.laputa.foundation.web.generate.controller;

import com.laputa.foundation.web.generate.entity.SysEntity;
import com.laputa.foundation.web.generate.model.SysEntityModel;
import com.laputa.foundation.web.kendo.model.DataSourceRequest;
import com.laputa.foundation.web.kendo.model.DataSourceResult;
import com.laputa.foundation.web.generate.service.SysEntityService;
import com.laputa.foundation.web.generate.converter.SysEntityBeanCopier;


import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 实体表 Controller<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2016-11-30T17:00:24+08:00 .
*/
@Controller
@RequestMapping("/generate/sysentity")
public class SysEntityController {

    @Resource
    private SysEntityService sysEntityService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "/generate/sysentity/list";
    }

    @ResponseBody
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public List<SysEntityModel> read() {
        List<SysEntity> sysEntityList = sysEntityService.read();
        List<SysEntityModel> sysEntityModelList = SysEntityBeanCopier.sysEntityEntityToSysEntityModel(sysEntityList);
        return sysEntityModelList;
    }

    @ResponseBody
    @RequestMapping(value = "/readEager", method = RequestMethod.GET)
    public List<SysEntityModel> readEager() {
        List<SysEntity> sysEntityList = sysEntityService.readEager();
        List<SysEntityModel> sysEntityModelList = SysEntityBeanCopier.sysEntityEntityToSysEntityModelEager(sysEntityList);
        return sysEntityModelList;
    }

    @ResponseBody
    @RequestMapping(value = "/readDataSource", method = RequestMethod.POST)
    public DataSourceResult readDataSource(@RequestBody DataSourceRequest dataSourceRequest) {
        Page<SysEntity> sysEntityPage = sysEntityService.readDataSource(dataSourceRequest);
        List<SysEntityModel> sysEntityModelList = SysEntityBeanCopier.sysEntityEntityToSysEntityModelEager(sysEntityPage.getContent());
        DataSourceResult dataSourceResult = new DataSourceResult(sysEntityModelList, sysEntityPage.getTotalElements());
        return dataSourceResult;
    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public SysEntityModel create(@RequestBody SysEntityModel sysEntityModel) {
        SysEntity sysEntity = SysEntityBeanCopier.sysEntityModelToSysEntityEntity(sysEntityModel);
        SysEntity createSysEntity = sysEntityService.create(sysEntity);
        sysEntityModel.setId(createSysEntity.getId());
        return sysEntityModel;
    }

    @ResponseBody
    @RequestMapping(value = "/destory", method = RequestMethod.POST)
    public SysEntityModel destory(@RequestBody SysEntityModel sysEntityModel) {
        SysEntity sysEntity = SysEntityBeanCopier.sysEntityModelToSysEntityEntity(sysEntityModel);
        sysEntityService.destory(sysEntity);
        return sysEntityModel;
    }


    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public SysEntityModel update(@RequestBody SysEntityModel sysEntityModel) {
        SysEntity sysEntity = SysEntityBeanCopier.sysEntityModelToSysEntityEntity(sysEntityModel);
        sysEntityService.update(sysEntity);
        return sysEntityModel;
    }
}