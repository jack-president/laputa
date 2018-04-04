package com.laputa.foundation.web.rbac.controller;

import com.laputa.foundation.web.rbac.converter.SysPermissionBeanCopier;
import com.laputa.foundation.web.rbac.entity.SysPermission;
import com.laputa.foundation.web.rbac.entity.SysRole;
import com.laputa.foundation.web.rbac.model.SysPermissionModel;
import com.laputa.foundation.web.rbac.model.SysRoleModel;
import com.laputa.foundation.web.kendo.model.DataSourceRequest;
import com.laputa.foundation.web.kendo.model.DataSourceResult;
import com.laputa.foundation.web.rbac.service.SysRoleService;
import com.laputa.foundation.web.rbac.converter.SysRoleBeanCopier;


import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


/**
 * <p>
 * 角色 Controller<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-08-15T09:50:29+08:00 .
 */
@Controller
@RequestMapping("/rbac/sysrole")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "/rbac/sysrole/list";
    }


    @RequestMapping(value = "/configOwnSysPermissionCollection/{sysRoleId}", method = RequestMethod.GET)
    public String configOwnSysPermissionCollection(@PathVariable("sysRoleId") Long sysRoleId, Model model) {
        model.addAttribute("sysRoleId", sysRoleId);
        return "/rbac/sysrole/configOwnSysPermissionCollection";
    }

    @ResponseBody
    @RequestMapping(value = "/readNotHaveOwnSysPermission/{sysRoleId}", method = RequestMethod.GET)
    public List<SysPermissionModel> readNotHaveOwnSysPermission(@PathVariable("sysRoleId") Long sysRoleId) {
        List<SysPermission> sysPermissionList = sysRoleService.readNotHaveOwnSysPermission(sysRoleId);
        List<SysPermissionModel> sysPermissionModelList = SysPermissionBeanCopier.sysPermissionEntityToSysPermissionModel(sysPermissionList);
        return sysPermissionModelList != null ? sysPermissionModelList : Collections.<SysPermissionModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readHaveOwnSysPermission/{sysRoleId}", method = RequestMethod.GET)
    public List<SysPermissionModel> readHaveOwnSysPermission(@PathVariable("sysRoleId") Long sysRoleId) {
        List<SysPermission> sysPermissionList = sysRoleService.readHaveOwnSysPermission(sysRoleId);
        List<SysPermissionModel> sysPermissionModelList = SysPermissionBeanCopier.sysPermissionEntityToSysPermissionModel(sysPermissionList);
        return sysPermissionModelList != null ? sysPermissionModelList : Collections.<SysPermissionModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/updateHaveOwnSysPermission", method = RequestMethod.POST)
    public SysRoleModel updateHaveOwnSysPermission(@RequestBody SysRoleModel sysRoleModel) {
        SysRole sysRole = SysRoleBeanCopier.sysRoleModelToSysRoleEntity(sysRoleModel);
        sysRoleService.updateHaveOwnSysPermission(sysRole);
        return sysRoleModel;
    }

    @ResponseBody
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public List<SysRoleModel> read() {
        List<SysRole> sysRoleList = sysRoleService.read();
        List<SysRoleModel> sysRoleModelList = SysRoleBeanCopier.sysRoleEntityToSysRoleModel(sysRoleList);
        return sysRoleModelList != null ? sysRoleModelList : Collections.<SysRoleModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readEager", method = {RequestMethod.GET, RequestMethod.POST})
    public List<SysRoleModel> readEager() {
        List<SysRole> sysRoleList = sysRoleService.readEager();
        List<SysRoleModel> sysRoleModelList = SysRoleBeanCopier.sysRoleEntityToSysRoleModelEager(sysRoleList);
        return sysRoleModelList != null ? sysRoleModelList : Collections.<SysRoleModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readDataSource", method = RequestMethod.POST)
    public DataSourceResult readDataSource(@RequestBody DataSourceRequest dataSourceRequest) {
        Page<SysRole> sysRolePage = sysRoleService.readDataSource(dataSourceRequest);
        List<SysRoleModel> sysRoleModelList = SysRoleBeanCopier.sysRoleEntityToSysRoleModelEager(sysRolePage.getContent());
        DataSourceResult dataSourceResult = new DataSourceResult(sysRoleModelList, sysRolePage.getTotalElements());
        return dataSourceResult;
    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public SysRoleModel create(@RequestBody SysRoleModel sysRoleModel) {
        SysRole sysRole = SysRoleBeanCopier.sysRoleModelToSysRoleEntity(sysRoleModel);
        SysRole createSysRole = sysRoleService.create(sysRole);
        sysRoleModel.setId(createSysRole.getId());
        return sysRoleModel;
    }

    @ResponseBody
    @RequestMapping(value = "/destory", method = RequestMethod.POST)
    public SysRoleModel destory(@RequestBody SysRoleModel sysRoleModel) {
        SysRole sysRole = SysRoleBeanCopier.sysRoleModelToSysRoleEntity(sysRoleModel);
        sysRoleService.destory(sysRole);
        return sysRoleModel;
    }


    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public SysRoleModel update(@RequestBody SysRoleModel sysRoleModel) {
        SysRole sysRole = SysRoleBeanCopier.sysRoleModelToSysRoleEntity(sysRoleModel);
        sysRoleService.update(sysRole);
        return sysRoleModel;
    }

}