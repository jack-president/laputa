package com.laputa.foundation.web.rbac.controller;

import com.laputa.foundation.web.rbac.entity.SysElement;
import com.laputa.foundation.web.rbac.model.SysElementModel;
import com.laputa.foundation.web.kendo.model.DataSourceRequest;
import com.laputa.foundation.web.kendo.model.DataSourceResult;
import com.laputa.foundation.web.rbac.service.SysElementService;
import com.laputa.foundation.web.rbac.converter.SysElementBeanCopier;


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
 * 系统元素 Controller<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-02-03T16:53:40+08:00 .
 */
@Controller
@RequestMapping("/rbac/syselement")
public class SysElementController {

    @Resource
    private SysElementService sysElementService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "/rbac/syselement/list";
    }

    @ResponseBody
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public List<SysElementModel> read() {
        List<SysElement> sysElementList = sysElementService.read();
        List<SysElementModel> sysElementModelList = SysElementBeanCopier.sysElementEntityToSysElementModel(sysElementList);
        return sysElementModelList != null ? sysElementModelList : Collections.<SysElementModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readEager", method = {RequestMethod.GET, RequestMethod.POST})
    public List<SysElementModel> readEager() {
        List<SysElement> sysElementList = sysElementService.readEager();
        List<SysElementModel> sysElementModelList = SysElementBeanCopier.sysElementEntityToSysElementModelEager(sysElementList);
        return sysElementModelList != null ? sysElementModelList : Collections.<SysElementModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readDataSource", method = RequestMethod.POST)
    public DataSourceResult readDataSource(@RequestBody DataSourceRequest dataSourceRequest) {
        Page<SysElement> sysElementPage = sysElementService.readDataSource(dataSourceRequest);
        List<SysElementModel> sysElementModelList = SysElementBeanCopier.sysElementEntityToSysElementModelEager(sysElementPage.getContent());
        DataSourceResult dataSourceResult = new DataSourceResult(sysElementModelList, sysElementPage.getTotalElements());
        return dataSourceResult;
    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public SysElementModel create(@RequestBody SysElementModel sysElementModel) {
        SysElement sysElement = SysElementBeanCopier.sysElementModelToSysElementEntity(sysElementModel);
        SysElement createSysElement = sysElementService.create(sysElement);
        sysElementModel.setId(createSysElement.getId());
        return sysElementModel;
    }

    @ResponseBody
    @RequestMapping(value = "/destory", method = RequestMethod.POST)
    public SysElementModel destory(@RequestBody SysElementModel sysElementModel) {
        SysElement sysElement = SysElementBeanCopier.sysElementModelToSysElementEntity(sysElementModel);
        sysElementService.destory(sysElement);
        return sysElementModel;
    }


    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public SysElementModel update(@RequestBody SysElementModel sysElementModel) {
        SysElement sysElement = SysElementBeanCopier.sysElementModelToSysElementEntity(sysElementModel);
        sysElementService.update(sysElement);
        return sysElementModel;
    }
}