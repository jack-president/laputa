package com.laputa.foundation.web.generate.controller;

import com.laputa.foundation.web.generate.entity.SysHelloDate;
import com.laputa.foundation.web.generate.model.SysHelloDateModel;
import com.laputa.foundation.web.kendo.model.DataSourceRequest;
import com.laputa.foundation.web.kendo.model.DataSourceResult;
import com.laputa.foundation.web.generate.service.SysHelloDateService;
import com.laputa.foundation.web.generate.converter.SysHelloDateBeanCopier;


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
 * Hi时间 Controller<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2016-12-07T16:50:06+08:00 .
*/
@Controller
@RequestMapping("/generate/syshellodate")
public class SysHelloDateController {

    @Resource
    private SysHelloDateService sysHelloDateService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "/generate/syshellodate/list";
    }

    @ResponseBody
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public List<SysHelloDateModel> read() {
        List<SysHelloDate> sysHelloDateList = sysHelloDateService.read();
        List<SysHelloDateModel> sysHelloDateModelList = SysHelloDateBeanCopier.sysHelloDateEntityToSysHelloDateModel(sysHelloDateList);
        return sysHelloDateModelList != null ? sysHelloDateModelList : Collections.<SysHelloDateModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readEager", method = {RequestMethod.GET,RequestMethod.POST})
    public List<SysHelloDateModel> readEager() {
        List<SysHelloDate> sysHelloDateList = sysHelloDateService.readEager();
        List<SysHelloDateModel> sysHelloDateModelList = SysHelloDateBeanCopier.sysHelloDateEntityToSysHelloDateModelEager(sysHelloDateList);
        return sysHelloDateModelList != null ? sysHelloDateModelList : Collections.<SysHelloDateModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readDataSource", method = RequestMethod.POST)
    public DataSourceResult readDataSource(@RequestBody DataSourceRequest dataSourceRequest) {
        Page<SysHelloDate> sysHelloDatePage = sysHelloDateService.readDataSource(dataSourceRequest);
        List<SysHelloDateModel> sysHelloDateModelList = SysHelloDateBeanCopier.sysHelloDateEntityToSysHelloDateModelEager(sysHelloDatePage.getContent());
        DataSourceResult dataSourceResult = new DataSourceResult(sysHelloDateModelList, sysHelloDatePage.getTotalElements());
        return dataSourceResult;
    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public SysHelloDateModel create(@RequestBody SysHelloDateModel sysHelloDateModel) {
        SysHelloDate sysHelloDate = SysHelloDateBeanCopier.sysHelloDateModelToSysHelloDateEntity(sysHelloDateModel);
        SysHelloDate createSysHelloDate = sysHelloDateService.create(sysHelloDate);
        sysHelloDateModel.setId(createSysHelloDate.getId());
        return sysHelloDateModel;
    }

    @ResponseBody
    @RequestMapping(value = "/destory", method = RequestMethod.POST)
    public SysHelloDateModel destory(@RequestBody SysHelloDateModel sysHelloDateModel) {
        SysHelloDate sysHelloDate = SysHelloDateBeanCopier.sysHelloDateModelToSysHelloDateEntity(sysHelloDateModel);
        sysHelloDateService.destory(sysHelloDate);
        return sysHelloDateModel;
    }


    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public SysHelloDateModel update(@RequestBody SysHelloDateModel sysHelloDateModel) {
        SysHelloDate sysHelloDate = SysHelloDateBeanCopier.sysHelloDateModelToSysHelloDateEntity(sysHelloDateModel);
        sysHelloDateService.update(sysHelloDate);
        return sysHelloDateModel;
    }
}