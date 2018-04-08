package com.laputa.fates.web.controller;

import com.laputa.fates.web.entity.LaputaConfig;
import com.laputa.fates.web.model.LaputaConfigModel;
import com.laputa.fates.web.service.ConfigByApplicationService;
import com.laputa.foundation.web.kendo.model.DataSourceRequest;
import com.laputa.foundation.web.kendo.model.DataSourceResult;
import com.laputa.fates.web.service.LaputaConfigService;
import com.laputa.fates.web.converter.LaputaConfigBeanCopier;


import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


/**
 * <p>
 * Laputa项目 Controller<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-04-06T10:15:10+08:00 .
 */
@Controller
@RequestMapping("/web/laputaconfig")
public class LaputaConfigController {

    @Resource
    private LaputaConfigService laputaConfigService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "/web/laputaconfig/list";
    }

    @ResponseBody
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public List<LaputaConfigModel> read() {
        List<LaputaConfig> laputaConfigList = laputaConfigService.read();
        List<LaputaConfigModel> laputaConfigModelList = LaputaConfigBeanCopier.laputaConfigEntityToLaputaConfigModel(laputaConfigList);
        return laputaConfigModelList != null ? laputaConfigModelList : Collections.<LaputaConfigModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readEager", method = {RequestMethod.GET, RequestMethod.POST})
    public List<LaputaConfigModel> readEager() {
        List<LaputaConfig> laputaConfigList = laputaConfigService.readEager();
        List<LaputaConfigModel> laputaConfigModelList = LaputaConfigBeanCopier.laputaConfigEntityToLaputaConfigModelEager(laputaConfigList);
        return laputaConfigModelList != null ? laputaConfigModelList : Collections.<LaputaConfigModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readDataSource", method = RequestMethod.POST)
    public DataSourceResult readDataSource(@RequestBody DataSourceRequest dataSourceRequest) {
        Page<LaputaConfig> laputaConfigPage = laputaConfigService.readDataSource(dataSourceRequest);
        List<LaputaConfigModel> laputaConfigModelList = LaputaConfigBeanCopier.laputaConfigEntityToLaputaConfigModelEager(laputaConfigPage.getContent());
        DataSourceResult dataSourceResult = new DataSourceResult(laputaConfigModelList, laputaConfigPage.getTotalElements());
        return dataSourceResult;
    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public LaputaConfigModel create(@RequestBody LaputaConfigModel laputaConfigModel) {
        LaputaConfig laputaConfig = LaputaConfigBeanCopier.laputaConfigModelToLaputaConfigEntity(laputaConfigModel);
        LaputaConfig createLaputaConfig = laputaConfigService.create(laputaConfig);
        laputaConfigModel.setId(createLaputaConfig.getId());
        return laputaConfigModel;
    }

    @ResponseBody
    @RequestMapping(value = "/destory", method = RequestMethod.POST)
    public LaputaConfigModel destory(@RequestBody LaputaConfigModel laputaConfigModel) {
        LaputaConfig laputaConfig = LaputaConfigBeanCopier.laputaConfigModelToLaputaConfigEntity(laputaConfigModel);
        laputaConfigService.destory(laputaConfig);
        return laputaConfigModel;
    }


    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public LaputaConfigModel update(@RequestBody LaputaConfigModel laputaConfigModel) {
        LaputaConfig laputaConfig = LaputaConfigBeanCopier.laputaConfigModelToLaputaConfigEntity(laputaConfigModel);
        laputaConfigService.update(laputaConfig);
        return laputaConfigModel;
    }
}