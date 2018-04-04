package com.laputa.foundation.web.generate.controller;

import com.laputa.foundation.web.generate.entity.SysField;
import com.laputa.foundation.web.generate.model.SysFieldModel;
import com.laputa.foundation.web.kendo.model.DataSourceRequest;
import com.laputa.foundation.web.kendo.model.DataSourceResult;
import com.laputa.foundation.web.generate.service.SysFieldService;
import com.laputa.foundation.web.generate.converter.SysFieldBeanCopier;


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
 * Created by JiangDongPing CodeGnerate on 2016-11-30T17:23:51+08:00 .
*/
@Controller
@RequestMapping("/generate/sysfield")
public class SysFieldController {

    @Resource
    private SysFieldService sysFieldService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "/generate/sysfield/list";
    }

    @ResponseBody
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public List<SysFieldModel> read() {
        List<SysField> sysFieldList = sysFieldService.read();
        List<SysFieldModel> sysFieldModelList = SysFieldBeanCopier.sysFieldEntityToSysFieldModel(sysFieldList);
        return sysFieldModelList;
    }

    @ResponseBody
    @RequestMapping(value = "/readEager", method = RequestMethod.GET)
    public List<SysFieldModel> readEager() {
        List<SysField> sysFieldList = sysFieldService.readEager();
        List<SysFieldModel> sysFieldModelList = SysFieldBeanCopier.sysFieldEntityToSysFieldModelEager(sysFieldList);
        return sysFieldModelList;
    }

    @ResponseBody
    @RequestMapping(value = "/readDataSource", method = RequestMethod.POST)
    public DataSourceResult readDataSource(@RequestBody DataSourceRequest dataSourceRequest) {
        Page<SysField> sysFieldPage = sysFieldService.readDataSource(dataSourceRequest);
        List<SysFieldModel> sysFieldModelList = SysFieldBeanCopier.sysFieldEntityToSysFieldModelEager(sysFieldPage.getContent());
        DataSourceResult dataSourceResult = new DataSourceResult(sysFieldModelList, sysFieldPage.getTotalElements());
        return dataSourceResult;
    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public SysFieldModel create(@RequestBody SysFieldModel sysFieldModel) {
        SysField sysField = SysFieldBeanCopier.sysFieldModelToSysFieldEntity(sysFieldModel);
        SysField createSysField = sysFieldService.create(sysField);
        sysFieldModel.setId(createSysField.getId());
        return sysFieldModel;
    }

    @ResponseBody
    @RequestMapping(value = "/destory", method = RequestMethod.POST)
    public SysFieldModel destory(@RequestBody SysFieldModel sysFieldModel) {
        SysField sysField = SysFieldBeanCopier.sysFieldModelToSysFieldEntity(sysFieldModel);
        sysFieldService.destory(sysField);
        return sysFieldModel;
    }


    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public SysFieldModel update(@RequestBody SysFieldModel sysFieldModel) {
        SysField sysField = SysFieldBeanCopier.sysFieldModelToSysFieldEntity(sysFieldModel);
        sysFieldService.update(sysField);
        return sysFieldModel;
    }
}