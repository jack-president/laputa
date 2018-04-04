package com.laputa.foundation.web.rbac.controller;

import com.laputa.foundation.web.rbac.entity.SysPermission;
import com.laputa.foundation.web.rbac.model.SysPermissionModel;
import com.laputa.foundation.web.kendo.model.DataSourceRequest;
import com.laputa.foundation.web.kendo.model.DataSourceResult;
import com.laputa.foundation.web.rbac.service.SysPermissionService;
import com.laputa.foundation.web.rbac.converter.SysPermissionBeanCopier;


import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


/**
 * <p>
 * 权限 Controller<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-02-03T16:53:42+08:00 .
 */
@Controller
@RequestMapping("/rbac/syspermission")
public class SysPermissionController {

    @Resource
    private SysPermissionService sysPermissionService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "/rbac/syspermission/list";
    }

    @RequestMapping(value = "/configOwnSysFileCollection/{sysPermissionId}", method = RequestMethod.GET)
    public String configOwnSysFileCollection(@PathVariable("sysPermissionId") Long sysPermissionId, Model model) {
        model.addAttribute("sysPermissionId", sysPermissionId);
        return "/rbac/syspermission/configOwnSysFileCollection";
    }

    @ResponseBody
    @RequestMapping(value = "/updateOwnSysFileCollection", method = RequestMethod.POST)
    public SysPermissionModel updateOwnSysFileCollection(@RequestBody SysPermissionModel sysPermissionModel) {
        SysPermission sysPermission = SysPermissionBeanCopier.sysPermissionModelToSysPermissionEntity(sysPermissionModel);
        sysPermissionService.updateOwnSysFileCollection(sysPermission);
        return sysPermissionModel;
    }


    @RequestMapping(value = "/configOwnSysMenuCollection/{sysPermissionId}", method = RequestMethod.GET)
    public String configOwnSysMenuCollection(@PathVariable("sysPermissionId") Long sysPermissionId, Model model) {
        model.addAttribute("sysPermissionId", sysPermissionId);
        return "/rbac/syspermission/configOwnSysMenuCollection";
    }

    @ResponseBody
    @RequestMapping(value = "/updateOwnSysMenuCollection", method = RequestMethod.POST)
    public SysPermissionModel updateOwnSysMenuCollection(@RequestBody SysPermissionModel sysPermissionModel) {
        SysPermission sysPermission = SysPermissionBeanCopier.sysPermissionModelToSysPermissionEntity(sysPermissionModel);
        sysPermissionService.updateOwnSysMenuCollection(sysPermission);
        return sysPermissionModel;
    }



    @RequestMapping(value = "/configOwnSysOperationCollection/{sysPermissionId}", method = RequestMethod.GET)
    public String configOwnSysOperationCollection(@PathVariable("sysPermissionId") Long sysPermissionId, Model model) {
        model.addAttribute("sysPermissionId", sysPermissionId);
        return "/rbac/syspermission/configOwnSysOperationCollection";
    }

    @ResponseBody
    @RequestMapping(value = "/updateOwnSysOperationCollection", method = RequestMethod.POST)
    public SysPermissionModel updateOwnSysOperationCollection(@RequestBody SysPermissionModel sysPermissionModel) {
        SysPermission sysPermission = SysPermissionBeanCopier.sysPermissionModelToSysPermissionEntity(sysPermissionModel);
        sysPermissionService.updateOwnSysOperationCollection(sysPermission);
        return sysPermissionModel;
    }

    @ResponseBody
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public List<SysPermissionModel> read() {
        List<SysPermission> sysPermissionList = sysPermissionService.read();
        List<SysPermissionModel> sysPermissionModelList = SysPermissionBeanCopier.sysPermissionEntityToSysPermissionModel(sysPermissionList);
        return sysPermissionModelList != null ? sysPermissionModelList : Collections.<SysPermissionModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readEager", method = {RequestMethod.GET, RequestMethod.POST})
    public List<SysPermissionModel> readEager() {
        List<SysPermission> sysPermissionList = sysPermissionService.readEager();
        List<SysPermissionModel> sysPermissionModelList = SysPermissionBeanCopier.sysPermissionEntityToSysPermissionModelEager(sysPermissionList);
        return sysPermissionModelList != null ? sysPermissionModelList : Collections.<SysPermissionModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readDataSource", method = RequestMethod.POST)
    public DataSourceResult readDataSource(@RequestBody DataSourceRequest dataSourceRequest) {
        Page<SysPermission> sysPermissionPage = sysPermissionService.readDataSource(dataSourceRequest);
        List<SysPermissionModel> sysPermissionModelList = SysPermissionBeanCopier.sysPermissionEntityToSysPermissionModelEager(sysPermissionPage.getContent());
        DataSourceResult dataSourceResult = new DataSourceResult(sysPermissionModelList, sysPermissionPage.getTotalElements());
        return dataSourceResult;
    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public SysPermissionModel create(@RequestBody SysPermissionModel sysPermissionModel) {
        SysPermission sysPermission = SysPermissionBeanCopier.sysPermissionModelToSysPermissionEntity(sysPermissionModel);
        SysPermission createSysPermission = sysPermissionService.create(sysPermission);
        sysPermissionModel.setId(createSysPermission.getId());
        return sysPermissionModel;
    }

    @ResponseBody
    @RequestMapping(value = "/destory", method = RequestMethod.POST)
    public SysPermissionModel destory(@RequestBody SysPermissionModel sysPermissionModel) {
        SysPermission sysPermission = SysPermissionBeanCopier.sysPermissionModelToSysPermissionEntity(sysPermissionModel);
        sysPermissionService.destory(sysPermission);
        return sysPermissionModel;
    }


    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public SysPermissionModel update(@RequestBody SysPermissionModel sysPermissionModel) {
        SysPermission sysPermission = SysPermissionBeanCopier.sysPermissionModelToSysPermissionEntity(sysPermissionModel);
        sysPermissionService.update(sysPermission);
        return sysPermissionModel;
    }
}