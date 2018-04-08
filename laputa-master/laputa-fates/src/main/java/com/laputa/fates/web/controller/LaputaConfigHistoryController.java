package com.laputa.fates.web.controller;

import com.laputa.fates.web.entity.LaputaConfigHistory;
import com.laputa.fates.web.model.LaputaConfigHistoryModel;
import com.laputa.foundation.web.kendo.model.DataSourceRequest;
import com.laputa.foundation.web.kendo.model.DataSourceResult;
import com.laputa.fates.web.service.LaputaConfigHistoryService;
import com.laputa.fates.web.converter.LaputaConfigHistoryBeanCopier;


import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;


/**
 * <p>
 * Laputa项目 Controller<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-04-06T10:15:11+08:00 .
*/
@Controller
@RequestMapping("/web/laputaconfighistory")
public class LaputaConfigHistoryController {

    @Resource
    private LaputaConfigHistoryService laputaConfigHistoryService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "/web/laputaconfighistory/list";
    }

    @ResponseBody
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public List<LaputaConfigHistoryModel> read() {
        List<LaputaConfigHistory> laputaConfigHistoryList = laputaConfigHistoryService.read();
        List<LaputaConfigHistoryModel> laputaConfigHistoryModelList = LaputaConfigHistoryBeanCopier.laputaConfigHistoryEntityToLaputaConfigHistoryModel(laputaConfigHistoryList);
        return laputaConfigHistoryModelList != null ? laputaConfigHistoryModelList : Collections.<LaputaConfigHistoryModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readEager", method = {RequestMethod.GET,RequestMethod.POST})
    public List<LaputaConfigHistoryModel> readEager() {
        List<LaputaConfigHistory> laputaConfigHistoryList = laputaConfigHistoryService.readEager();
        List<LaputaConfigHistoryModel> laputaConfigHistoryModelList = LaputaConfigHistoryBeanCopier.laputaConfigHistoryEntityToLaputaConfigHistoryModelEager(laputaConfigHistoryList);
        return laputaConfigHistoryModelList != null ? laputaConfigHistoryModelList : Collections.<LaputaConfigHistoryModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readDataSource", method = RequestMethod.POST)
    public DataSourceResult readDataSource(@RequestBody DataSourceRequest dataSourceRequest) {
        Page<LaputaConfigHistory> laputaConfigHistoryPage = laputaConfigHistoryService.readDataSource(dataSourceRequest);
        List<LaputaConfigHistoryModel> laputaConfigHistoryModelList = LaputaConfigHistoryBeanCopier.laputaConfigHistoryEntityToLaputaConfigHistoryModelEager(laputaConfigHistoryPage.getContent());
        DataSourceResult dataSourceResult = new DataSourceResult(laputaConfigHistoryModelList, laputaConfigHistoryPage.getTotalElements());
        return dataSourceResult;
    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public LaputaConfigHistoryModel create(@RequestBody LaputaConfigHistoryModel laputaConfigHistoryModel) {
        LaputaConfigHistory laputaConfigHistory = LaputaConfigHistoryBeanCopier.laputaConfigHistoryModelToLaputaConfigHistoryEntity(laputaConfigHistoryModel);
        LaputaConfigHistory createLaputaConfigHistory = laputaConfigHistoryService.create(laputaConfigHistory);
        laputaConfigHistoryModel.setId(createLaputaConfigHistory.getId());
        return laputaConfigHistoryModel;
    }

    @ResponseBody
    @RequestMapping(value = "/destory", method = RequestMethod.POST)
    public LaputaConfigHistoryModel destory(@RequestBody LaputaConfigHistoryModel laputaConfigHistoryModel) {
        LaputaConfigHistory laputaConfigHistory = LaputaConfigHistoryBeanCopier.laputaConfigHistoryModelToLaputaConfigHistoryEntity(laputaConfigHistoryModel);
        laputaConfigHistoryService.destory(laputaConfigHistory);
        return laputaConfigHistoryModel;
    }


    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public LaputaConfigHistoryModel update(@RequestBody LaputaConfigHistoryModel laputaConfigHistoryModel) {
        LaputaConfigHistory laputaConfigHistory = LaputaConfigHistoryBeanCopier.laputaConfigHistoryModelToLaputaConfigHistoryEntity(laputaConfigHistoryModel);
        laputaConfigHistoryService.update(laputaConfigHistory);
        return laputaConfigHistoryModel;
    }
}