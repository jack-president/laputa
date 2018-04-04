package com.laputa.foundation.web.generate.controller;

import com.laputa.foundation.web.generate.entity.SysHelloPermission;
import com.laputa.foundation.web.generate.model.SysHelloPermissionModel;
import com.laputa.foundation.web.kendo.model.DataSourceRequest;
import com.laputa.foundation.web.kendo.model.DataSourceResult;
import com.laputa.foundation.web.generate.service.SysHelloPermissionService;
import com.laputa.foundation.web.generate.converter.SysHelloPermissionBeanCopier;


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
 * Hi权限 Controller<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2016-12-05T11:02:51+08:00 .
*/
@Controller
@RequestMapping("/generate/syshellopermission")
public class SysHelloPermissionController {

    @Resource
    private SysHelloPermissionService sysHelloPermissionService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "/generate/syshellopermission/list";
    }

    @ResponseBody
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public List<SysHelloPermissionModel> read() {
        List<SysHelloPermission> sysHelloPermissionList = sysHelloPermissionService.read();
        List<SysHelloPermissionModel> sysHelloPermissionModelList = SysHelloPermissionBeanCopier.sysHelloPermissionEntityToSysHelloPermissionModel(sysHelloPermissionList);
        return sysHelloPermissionModelList != null ? sysHelloPermissionModelList : Collections.<SysHelloPermissionModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readEager", method = RequestMethod.GET)
    public List<SysHelloPermissionModel> readEager() {
        List<SysHelloPermission> sysHelloPermissionList = sysHelloPermissionService.readEager();
        List<SysHelloPermissionModel> sysHelloPermissionModelList = SysHelloPermissionBeanCopier.sysHelloPermissionEntityToSysHelloPermissionModelEager(sysHelloPermissionList);
        return sysHelloPermissionModelList;
    }

    @ResponseBody
    @RequestMapping(value = "/readDataSource", method = RequestMethod.POST)
    public DataSourceResult readDataSource(@RequestBody DataSourceRequest dataSourceRequest) {
        Page<SysHelloPermission> sysHelloPermissionPage = sysHelloPermissionService.readDataSource(dataSourceRequest);
        List<SysHelloPermissionModel> sysHelloPermissionModelList = SysHelloPermissionBeanCopier.sysHelloPermissionEntityToSysHelloPermissionModelEager(sysHelloPermissionPage.getContent());
        DataSourceResult dataSourceResult = new DataSourceResult(sysHelloPermissionModelList, sysHelloPermissionPage.getTotalElements());
        return dataSourceResult;
    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public SysHelloPermissionModel create(@RequestBody SysHelloPermissionModel sysHelloPermissionModel) {
        SysHelloPermission sysHelloPermission = SysHelloPermissionBeanCopier.sysHelloPermissionModelToSysHelloPermissionEntity(sysHelloPermissionModel);
        SysHelloPermission createSysHelloPermission = sysHelloPermissionService.create(sysHelloPermission);
        sysHelloPermissionModel.setId(createSysHelloPermission.getId());
        return sysHelloPermissionModel;
    }

    @ResponseBody
    @RequestMapping(value = "/destory", method = RequestMethod.POST)
    public SysHelloPermissionModel destory(@RequestBody SysHelloPermissionModel sysHelloPermissionModel) {
        SysHelloPermission sysHelloPermission = SysHelloPermissionBeanCopier.sysHelloPermissionModelToSysHelloPermissionEntity(sysHelloPermissionModel);
        sysHelloPermissionService.destory(sysHelloPermission);
        return sysHelloPermissionModel;
    }


    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public SysHelloPermissionModel update(@RequestBody SysHelloPermissionModel sysHelloPermissionModel) {
        SysHelloPermission sysHelloPermission = SysHelloPermissionBeanCopier.sysHelloPermissionModelToSysHelloPermissionEntity(sysHelloPermissionModel);
        sysHelloPermissionService.update(sysHelloPermission);
        return sysHelloPermissionModel;
    }
}