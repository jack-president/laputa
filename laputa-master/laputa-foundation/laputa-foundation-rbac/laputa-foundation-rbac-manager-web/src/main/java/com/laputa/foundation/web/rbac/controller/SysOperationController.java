package com.laputa.foundation.web.rbac.controller;

import com.laputa.foundation.web.rbac.entity.SysOperation;
import com.laputa.foundation.web.rbac.model.SysOperationModel;
import com.laputa.foundation.web.kendo.model.DataSourceRequest;
import com.laputa.foundation.web.kendo.model.DataSourceResult;
import com.laputa.foundation.web.rbac.service.SysOperationService;
import com.laputa.foundation.web.rbac.converter.SysOperationBeanCopier;


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
 * 功能操作 Controller<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-02-03T16:53:42+08:00 .
*/
@Controller
@RequestMapping("/rbac/sysoperation")
public class SysOperationController {

    @Resource
    private SysOperationService sysOperationService;

    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    public String treeList() {
        return "/rbac/sysoperation/treeList";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "/rbac/sysoperation/list";
    }

    @ResponseBody
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public List<SysOperationModel> read() {
        List<SysOperation> sysOperationList = sysOperationService.read();
        List<SysOperationModel> sysOperationModelList = SysOperationBeanCopier.sysOperationEntityToSysOperationModel(sysOperationList);
        return sysOperationModelList != null ? sysOperationModelList : Collections.<SysOperationModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readEager", method = {RequestMethod.GET,RequestMethod.POST})
    public List<SysOperationModel> readEager() {
        List<SysOperation> sysOperationList = sysOperationService.readEager();
        List<SysOperationModel> sysOperationModelList = SysOperationBeanCopier.sysOperationEntityToSysOperationModelEager(sysOperationList);
        return sysOperationModelList != null ? sysOperationModelList : Collections.<SysOperationModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readDataSource", method = RequestMethod.POST)
    public DataSourceResult readDataSource(@RequestBody DataSourceRequest dataSourceRequest) {
        Page<SysOperation> sysOperationPage = sysOperationService.readDataSource(dataSourceRequest);
        List<SysOperationModel> sysOperationModelList = SysOperationBeanCopier.sysOperationEntityToSysOperationModelEager(sysOperationPage.getContent());
        DataSourceResult dataSourceResult = new DataSourceResult(sysOperationModelList, sysOperationPage.getTotalElements());
        return dataSourceResult;
    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public SysOperationModel create(@RequestBody SysOperationModel sysOperationModel) {
        SysOperation sysOperation = SysOperationBeanCopier.sysOperationModelToSysOperationEntity(sysOperationModel);
        SysOperation createSysOperation = sysOperationService.create(sysOperation);
        sysOperationModel.setId(createSysOperation.getId());
        return sysOperationModel;
    }

    @ResponseBody
    @RequestMapping(value = "/destory", method = RequestMethod.POST)
    public SysOperationModel destory(@RequestBody SysOperationModel sysOperationModel) {
        SysOperation sysOperation = SysOperationBeanCopier.sysOperationModelToSysOperationEntity(sysOperationModel);
        sysOperationService.destory(sysOperation);
        return sysOperationModel;
    }


    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public SysOperationModel update(@RequestBody SysOperationModel sysOperationModel) {
        SysOperation sysOperation = SysOperationBeanCopier.sysOperationModelToSysOperationEntity(sysOperationModel);
        sysOperationService.update(sysOperation);
        return sysOperationModel;
    }
}