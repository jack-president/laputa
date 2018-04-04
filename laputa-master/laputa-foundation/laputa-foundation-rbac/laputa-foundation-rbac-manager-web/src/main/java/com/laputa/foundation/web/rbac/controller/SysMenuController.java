package com.laputa.foundation.web.rbac.controller;

import com.laputa.foundation.web.rbac.entity.SysMenu;
import com.laputa.foundation.web.rbac.model.SysMenuModel;
import com.laputa.foundation.web.kendo.model.DataSourceRequest;
import com.laputa.foundation.web.kendo.model.DataSourceResult;
import com.laputa.foundation.web.rbac.service.SysMenuService;
import com.laputa.foundation.web.rbac.converter.SysMenuBeanCopier;


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
 * 菜单 Controller<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-02-03T16:53:41+08:00 .
*/
@Controller
@RequestMapping("/rbac/sysmenu")
public class SysMenuController {

    @Resource
    private SysMenuService sysMenuService;

    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    public String treeList() {
        return "/rbac/sysmenu/treeList";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "/rbac/sysmenu/list";
    }

    @ResponseBody
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public List<SysMenuModel> read() {
        List<SysMenu> sysMenuList = sysMenuService.read();
        List<SysMenuModel> sysMenuModelList = SysMenuBeanCopier.sysMenuEntityToSysMenuModel(sysMenuList);
        return sysMenuModelList != null ? sysMenuModelList : Collections.<SysMenuModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readEager", method = {RequestMethod.GET,RequestMethod.POST})
    public List<SysMenuModel> readEager() {
        List<SysMenu> sysMenuList = sysMenuService.readEager();
        List<SysMenuModel> sysMenuModelList = SysMenuBeanCopier.sysMenuEntityToSysMenuModelEager(sysMenuList);
        return sysMenuModelList != null ? sysMenuModelList : Collections.<SysMenuModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readDataSource", method = RequestMethod.POST)
    public DataSourceResult readDataSource(@RequestBody DataSourceRequest dataSourceRequest) {
        Page<SysMenu> sysMenuPage = sysMenuService.readDataSource(dataSourceRequest);
        List<SysMenuModel> sysMenuModelList = SysMenuBeanCopier.sysMenuEntityToSysMenuModelEager(sysMenuPage.getContent());
        DataSourceResult dataSourceResult = new DataSourceResult(sysMenuModelList, sysMenuPage.getTotalElements());
        return dataSourceResult;
    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public SysMenuModel create(@RequestBody SysMenuModel sysMenuModel) {
        SysMenu sysMenu = SysMenuBeanCopier.sysMenuModelToSysMenuEntity(sysMenuModel);
        SysMenu createSysMenu = sysMenuService.create(sysMenu);
        sysMenuModel.setId(createSysMenu.getId());
        return sysMenuModel;
    }

    @ResponseBody
    @RequestMapping(value = "/destory", method = RequestMethod.POST)
    public SysMenuModel destory(@RequestBody SysMenuModel sysMenuModel) {
        SysMenu sysMenu = SysMenuBeanCopier.sysMenuModelToSysMenuEntity(sysMenuModel);
        sysMenuService.destory(sysMenu);
        return sysMenuModel;
    }


    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public SysMenuModel update(@RequestBody SysMenuModel sysMenuModel) {
        SysMenu sysMenu = SysMenuBeanCopier.sysMenuModelToSysMenuEntity(sysMenuModel);
        sysMenuService.update(sysMenu);
        return sysMenuModel;
    }
}