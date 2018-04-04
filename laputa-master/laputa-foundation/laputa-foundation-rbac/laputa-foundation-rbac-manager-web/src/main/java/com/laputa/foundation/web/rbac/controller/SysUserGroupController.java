package com.laputa.foundation.web.rbac.controller;

import com.laputa.foundation.web.rbac.converter.SysPermissionBeanCopier;
import com.laputa.foundation.web.rbac.entity.SysPermission;
import com.laputa.foundation.web.rbac.entity.SysUserGroup;
import com.laputa.foundation.web.rbac.model.SysPermissionModel;
import com.laputa.foundation.web.rbac.model.SysUserGroupModel;
import com.laputa.foundation.web.kendo.model.DataSourceRequest;
import com.laputa.foundation.web.kendo.model.DataSourceResult;
import com.laputa.foundation.web.rbac.service.SysUserGroupService;
import com.laputa.foundation.web.rbac.converter.SysUserGroupBeanCopier;


import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


/**
 * <p>
 * 用户组 Controller<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-08-15T09:50:30+08:00 .
 */
@Controller
@RequestMapping("/rbac/sysusergroup")
public class SysUserGroupController {

    @Resource
    private SysUserGroupService sysUserGroupService;

    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    public String treeList() {
        return "/rbac/sysusergroup/treeList";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "/rbac/sysusergroup/list";
    }


    @RequestMapping(value = "/configCanhaveRelationSysPermissionCollection/{sysUserGroupId}", method = RequestMethod.GET)
    public String configCanhaveRelationSysPermissionCollection(@PathVariable("sysUserGroupId") Long sysUserGroupId, Model model) {
        model.addAttribute("sysUserGroupId", sysUserGroupId);
        return "/rbac/sysusergroup/configCanhaveRelationSysPermissionCollection";
    }

    @ResponseBody
    @RequestMapping(value = "/readNotHaveSysPermission/{sysUserGroupId}", method = RequestMethod.GET)
    public List<SysPermissionModel> readNotHaveSysPermission(@PathVariable("sysUserGroupId") Long sysUserGroupId) {
        List<SysPermission> sysPermissionList = sysUserGroupService.readNotHaveSysPermission(sysUserGroupId);
        List<SysPermissionModel> sysPermissionModelList = SysPermissionBeanCopier.sysPermissionEntityToSysPermissionModel(sysPermissionList);
        return sysPermissionModelList != null ? sysPermissionModelList : Collections.<SysPermissionModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readHaveSysPermission/{sysUserGroupId}", method = RequestMethod.GET)
    public List<SysPermissionModel> readHaveSysPermission(@PathVariable("sysUserGroupId") Long sysUserGroupId) {
        List<SysPermission> sysPermissionList = sysUserGroupService.readHaveSysPermission(sysUserGroupId);
        List<SysPermissionModel> sysPermissionModelList = SysPermissionBeanCopier.sysPermissionEntityToSysPermissionModel(sysPermissionList);
        return sysPermissionModelList != null ? sysPermissionModelList : Collections.<SysPermissionModel>emptyList();
    }


    @ResponseBody
    @RequestMapping(value = "/updateHaveSysPermission", method = RequestMethod.POST)
    public SysUserGroup updateHaveSysPermission(@RequestBody SysUserGroupModel sysUserGroupModel) {
        SysUserGroup sysUserGroup = SysUserGroupBeanCopier.sysUserGroupModelToSysUserGroupEntity(sysUserGroupModel);
        sysUserGroupService.updateHaveSysPermission(sysUserGroup);
        return sysUserGroup;
    }


    @ResponseBody
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public List<SysUserGroupModel> read() {
        List<SysUserGroup> sysUserGroupList = sysUserGroupService.read();
        List<SysUserGroupModel> sysUserGroupModelList = SysUserGroupBeanCopier.sysUserGroupEntityToSysUserGroupModel(sysUserGroupList);
        return sysUserGroupModelList != null ? sysUserGroupModelList : Collections.<SysUserGroupModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readEager", method = {RequestMethod.GET, RequestMethod.POST})
    public List<SysUserGroupModel> readEager() {
        List<SysUserGroup> sysUserGroupList = sysUserGroupService.readEager();
        List<SysUserGroupModel> sysUserGroupModelList = SysUserGroupBeanCopier.sysUserGroupEntityToSysUserGroupModelEager(sysUserGroupList);
        return sysUserGroupModelList != null ? sysUserGroupModelList : Collections.<SysUserGroupModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readDataSource", method = RequestMethod.POST)
    public DataSourceResult readDataSource(@RequestBody DataSourceRequest dataSourceRequest) {
        Page<SysUserGroup> sysUserGroupPage = sysUserGroupService.readDataSource(dataSourceRequest);
        List<SysUserGroupModel> sysUserGroupModelList = SysUserGroupBeanCopier.sysUserGroupEntityToSysUserGroupModelEager(sysUserGroupPage.getContent());
        DataSourceResult dataSourceResult = new DataSourceResult(sysUserGroupModelList, sysUserGroupPage.getTotalElements());
        return dataSourceResult;
    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public SysUserGroupModel create(@RequestBody SysUserGroupModel sysUserGroupModel) {
        SysUserGroup sysUserGroup = SysUserGroupBeanCopier.sysUserGroupModelToSysUserGroupEntity(sysUserGroupModel);
        SysUserGroup createSysUserGroup = sysUserGroupService.create(sysUserGroup);
        sysUserGroupModel.setId(createSysUserGroup.getId());
        return sysUserGroupModel;
    }

    @ResponseBody
    @RequestMapping(value = "/destory", method = RequestMethod.POST)
    public SysUserGroupModel destory(@RequestBody SysUserGroupModel sysUserGroupModel) {
        SysUserGroup sysUserGroup = SysUserGroupBeanCopier.sysUserGroupModelToSysUserGroupEntity(sysUserGroupModel);
        sysUserGroupService.destory(sysUserGroup);
        return sysUserGroupModel;
    }


    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public SysUserGroupModel update(@RequestBody SysUserGroupModel sysUserGroupModel) {
        SysUserGroup sysUserGroup = SysUserGroupBeanCopier.sysUserGroupModelToSysUserGroupEntity(sysUserGroupModel);
        sysUserGroupService.update(sysUserGroup);
        return sysUserGroupModel;
    }
}