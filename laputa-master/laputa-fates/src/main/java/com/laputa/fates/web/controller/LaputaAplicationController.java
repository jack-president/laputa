package com.laputa.fates.web.controller;

import com.laputa.fates.web.entity.LaputaAplication;
import com.laputa.fates.web.model.LaputaAplicationModel;
import com.laputa.foundation.web.kendo.model.DataSourceRequest;
import com.laputa.foundation.web.kendo.model.DataSourceResult;
import com.laputa.fates.web.service.LaputaAplicationService;
import com.laputa.fates.web.converter.LaputaAplicationBeanCopier;


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
 * Created by JiangDongPing CodeGnerate on 2018-04-06T10:15:08+08:00 .
*/
@Controller
@RequestMapping("/web/laputaaplication")
public class LaputaAplicationController {

    @Resource
    private LaputaAplicationService laputaAplicationService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "/web/laputaaplication/list";
    }

    @ResponseBody
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public List<LaputaAplicationModel> read() {
        List<LaputaAplication> laputaAplicationList = laputaAplicationService.read();
        List<LaputaAplicationModel> laputaAplicationModelList = LaputaAplicationBeanCopier.laputaAplicationEntityToLaputaAplicationModel(laputaAplicationList);
        return laputaAplicationModelList != null ? laputaAplicationModelList : Collections.<LaputaAplicationModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readEager", method = {RequestMethod.GET,RequestMethod.POST})
    public List<LaputaAplicationModel> readEager() {
        List<LaputaAplication> laputaAplicationList = laputaAplicationService.readEager();
        List<LaputaAplicationModel> laputaAplicationModelList = LaputaAplicationBeanCopier.laputaAplicationEntityToLaputaAplicationModelEager(laputaAplicationList);
        return laputaAplicationModelList != null ? laputaAplicationModelList : Collections.<LaputaAplicationModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readDataSource", method = RequestMethod.POST)
    public DataSourceResult readDataSource(@RequestBody DataSourceRequest dataSourceRequest) {
        Page<LaputaAplication> laputaAplicationPage = laputaAplicationService.readDataSource(dataSourceRequest);
        List<LaputaAplicationModel> laputaAplicationModelList = LaputaAplicationBeanCopier.laputaAplicationEntityToLaputaAplicationModelEager(laputaAplicationPage.getContent());
        DataSourceResult dataSourceResult = new DataSourceResult(laputaAplicationModelList, laputaAplicationPage.getTotalElements());
        return dataSourceResult;
    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public LaputaAplicationModel create(@RequestBody LaputaAplicationModel laputaAplicationModel) {
        LaputaAplication laputaAplication = LaputaAplicationBeanCopier.laputaAplicationModelToLaputaAplicationEntity(laputaAplicationModel);
        LaputaAplication createLaputaAplication = laputaAplicationService.create(laputaAplication);
        laputaAplicationModel.setId(createLaputaAplication.getId());
        return laputaAplicationModel;
    }

    @ResponseBody
    @RequestMapping(value = "/destory", method = RequestMethod.POST)
    public LaputaAplicationModel destory(@RequestBody LaputaAplicationModel laputaAplicationModel) {
        LaputaAplication laputaAplication = LaputaAplicationBeanCopier.laputaAplicationModelToLaputaAplicationEntity(laputaAplicationModel);
        laputaAplicationService.destory(laputaAplication);
        return laputaAplicationModel;
    }


    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public LaputaAplicationModel update(@RequestBody LaputaAplicationModel laputaAplicationModel) {
        LaputaAplication laputaAplication = LaputaAplicationBeanCopier.laputaAplicationModelToLaputaAplicationEntity(laputaAplicationModel);
        laputaAplicationService.update(laputaAplication);
        return laputaAplicationModel;
    }
}