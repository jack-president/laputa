package com.laputa.foundation.web.rbac.controller;

import com.laputa.foundation.web.kendo.model.DataSourceRequest;
import com.laputa.foundation.web.kendo.model.DataSourceResult;
import com.laputa.foundation.web.rbac.converter.SysUserBeanCopier;
import com.laputa.foundation.web.rbac.entity.SysRole;
import com.laputa.foundation.web.rbac.entity.SysUser;
import com.laputa.foundation.web.rbac.entity.SysUserGroup;
import com.laputa.foundation.web.rbac.model.ConfigSysRoleDataModel;
import com.laputa.foundation.web.rbac.model.SysUserModel;
import com.laputa.foundation.web.rbac.service.SysUserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * <p>
 * 用户 Controller<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-02-03T16:53:42+08:00 .
 */
@Controller
@RequestMapping("/rbac/sysuser")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "/rbac/sysuser/list";
    }

    @RequestMapping(value = "/configBelongtoSysRoleCollection/{sysUserId}", method = RequestMethod.GET)
    public String configBelongtoSysRoleCollection(@PathVariable("sysUserId") Long sysUserId, Model model) {
        model.addAttribute("sysUserId", sysUserId);
        return "/rbac/sysuser/configBelongtoSysRoleCollection";
    }

    @RequestMapping(value = "/configBelongtoSysUserGroupCollection/{sysUserId}", method = RequestMethod.GET)
    public String configBelongtoSysUserGroupCollection(@PathVariable("sysUserId") Long sysUserId, Model model) {
        model.addAttribute("sysUserId", sysUserId);
        return "/rbac/sysuser/configBelongtoSysUserGroupCollection";
    }


    @ResponseBody
    @RequestMapping(value = "/configSysRoleData/{sysUserId}", method = RequestMethod.GET)
    public List<ConfigSysRoleDataModel> configSysRoleData(@PathVariable("sysUserId") Long sysUserId) {
        List<ConfigSysRoleDataModel> modelList = new ArrayList<>();
        List<SysUserGroup> sysUserBelongtoUserGroupList = sysUserService.readSysUserBelongtoUserGroupList(sysUserId);
        if (sysUserBelongtoUserGroupList != null && sysUserBelongtoUserGroupList.size() > 0) {
            for (SysUserGroup sysUserGroup : sysUserBelongtoUserGroupList) {
                ConfigSysRoleDataModel root = new ConfigSysRoleDataModel(sysUserGroup);
                if (sysUserGroup.getOwnSysRoleCollection() != null && sysUserGroup.getOwnSysRoleCollection().size() > 0) {
                    for (SysRole sysRole : sysUserGroup.getOwnSysRoleCollection()) {
                        root.getItems().add(new ConfigSysRoleDataModel(sysRole));
                    }
                }
                modelList.add(root);
            }
        }
        return modelList;
    }


    @ResponseBody
    @RequestMapping(value = "/updateBelongtoSysUserGroupCollection", method = RequestMethod.POST)
    public SysUserModel updateBelongtoSysUserGroupCollection(@RequestBody SysUserModel sysUserModel) {
        SysUser sysUser = SysUserBeanCopier.sysUserModelToSysUserEntity(sysUserModel);
        sysUserService.updateBelongtoSysUserGroupCollection(sysUser);
        return sysUserModel;
    }

    @ResponseBody
    @RequestMapping(value = "/updateBelongtoSysRoleCollection", method = RequestMethod.POST)
    public SysUserModel updateBelongtoSysRoleCollection(@RequestBody SysUserModel sysUserModel) {
        SysUser sysUser = SysUserBeanCopier.sysUserModelToSysUserEntity(sysUserModel);
        sysUserService.updateBelongtoSysRoleCollection(sysUser);
        return sysUserModel;
    }


    @ResponseBody
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public SysUserModel updatePassword(@RequestBody SysUserModel sysUserModel) {
        SysUser sysUser = SysUserBeanCopier.sysUserModelToSysUserEntity(sysUserModel);
        sysUserService.updatePassword(sysUser);
        return sysUserModel;
    }

    @ResponseBody
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public List<SysUserModel> read() {
        List<SysUser> sysUserList = sysUserService.read();
        List<SysUserModel> sysUserModelList = SysUserBeanCopier.sysUserEntityToSysUserModel(sysUserList);
        return sysUserModelList != null ? sysUserModelList : Collections.<SysUserModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readEager", method = {RequestMethod.GET, RequestMethod.POST})
    public List<SysUserModel> readEager() {
        List<SysUser> sysUserList = sysUserService.readEager();
        List<SysUserModel> sysUserModelList = SysUserBeanCopier.sysUserEntityToSysUserModelEager(sysUserList);
        return sysUserModelList != null ? sysUserModelList : Collections.<SysUserModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readDataSource", method = RequestMethod.POST)
    public DataSourceResult readDataSource(@RequestBody DataSourceRequest dataSourceRequest) {
        Page<SysUser> sysUserPage = sysUserService.readDataSource(dataSourceRequest);
        List<SysUserModel> sysUserModelList = SysUserBeanCopier.sysUserEntityToSysUserModelEager(sysUserPage.getContent());
        DataSourceResult dataSourceResult = new DataSourceResult(sysUserModelList, sysUserPage.getTotalElements());
        return dataSourceResult;
    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public SysUserModel create(@RequestBody SysUserModel sysUserModel) {
        SysUser sysUser = SysUserBeanCopier.sysUserModelToSysUserEntity(sysUserModel);
        SysUser createSysUser = sysUserService.create(sysUser);
        sysUserModel.setId(createSysUser.getId());
        return sysUserModel;
    }

    @ResponseBody
    @RequestMapping(value = "/destory", method = RequestMethod.POST)
    public SysUserModel destory(@RequestBody SysUserModel sysUserModel) {
        SysUser sysUser = SysUserBeanCopier.sysUserModelToSysUserEntity(sysUserModel);
        sysUserService.destory(sysUser);
        return sysUserModel;
    }


    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public SysUserModel update(@RequestBody SysUserModel sysUserModel) {
        SysUser sysUser = SysUserBeanCopier.sysUserModelToSysUserEntity(sysUserModel);
        sysUserService.update(sysUser);
        return sysUserModel;
    }
}