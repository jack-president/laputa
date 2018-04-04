package com.laputa.foundation.web.generate.controller;

import com.laputa.foundation.web.generate.entity.SysHelloMenu;
import com.laputa.foundation.web.generate.model.SysHelloMenuModel;
import com.laputa.foundation.web.kendo.model.DataSourceRequest;
import com.laputa.foundation.web.kendo.model.DataSourceResult;
import com.laputa.foundation.web.generate.service.SysHelloMenuService;
import com.laputa.foundation.web.generate.converter.SysHelloMenuBeanCopier;


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
 * Hi菜单 Controller<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-01-10T18:13:30+08:00 .
*/
@Controller
@RequestMapping("/generate/syshellomenu")
public class SysHelloMenuController {

    @Resource
    private SysHelloMenuService sysHelloMenuService;

    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    public String treeList() {
        return "/generate/syshellomenu/treeList";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "/generate/syshellomenu/list";
    }

    @ResponseBody
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public List<SysHelloMenuModel> read() {
        List<SysHelloMenu> sysHelloMenuList = sysHelloMenuService.read();
        List<SysHelloMenuModel> sysHelloMenuModelList = SysHelloMenuBeanCopier.sysHelloMenuEntityToSysHelloMenuModel(sysHelloMenuList);
        return sysHelloMenuModelList != null ? sysHelloMenuModelList : Collections.<SysHelloMenuModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readEager", method = {RequestMethod.GET,RequestMethod.POST})
    public List<SysHelloMenuModel> readEager() {
        List<SysHelloMenu> sysHelloMenuList = sysHelloMenuService.readEager();
        List<SysHelloMenuModel> sysHelloMenuModelList = SysHelloMenuBeanCopier.sysHelloMenuEntityToSysHelloMenuModelEager(sysHelloMenuList);
        return sysHelloMenuModelList != null ? sysHelloMenuModelList : Collections.<SysHelloMenuModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readDataSource", method = RequestMethod.POST)
    public DataSourceResult readDataSource(@RequestBody DataSourceRequest dataSourceRequest) {
        Page<SysHelloMenu> sysHelloMenuPage = sysHelloMenuService.readDataSource(dataSourceRequest);
        List<SysHelloMenuModel> sysHelloMenuModelList = SysHelloMenuBeanCopier.sysHelloMenuEntityToSysHelloMenuModelEager(sysHelloMenuPage.getContent());
        DataSourceResult dataSourceResult = new DataSourceResult(sysHelloMenuModelList, sysHelloMenuPage.getTotalElements());
        return dataSourceResult;
    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public SysHelloMenuModel create(@RequestBody SysHelloMenuModel sysHelloMenuModel) {
        SysHelloMenu sysHelloMenu = SysHelloMenuBeanCopier.sysHelloMenuModelToSysHelloMenuEntity(sysHelloMenuModel);
        SysHelloMenu createSysHelloMenu = sysHelloMenuService.create(sysHelloMenu);
        sysHelloMenuModel.setId(createSysHelloMenu.getId());
        return sysHelloMenuModel;
    }

    @ResponseBody
    @RequestMapping(value = "/destory", method = RequestMethod.POST)
    public SysHelloMenuModel destory(@RequestBody SysHelloMenuModel sysHelloMenuModel) {
        SysHelloMenu sysHelloMenu = SysHelloMenuBeanCopier.sysHelloMenuModelToSysHelloMenuEntity(sysHelloMenuModel);
        sysHelloMenuService.destory(sysHelloMenu);
        return sysHelloMenuModel;
    }


    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public SysHelloMenuModel update(@RequestBody SysHelloMenuModel sysHelloMenuModel) {
        SysHelloMenu sysHelloMenu = SysHelloMenuBeanCopier.sysHelloMenuModelToSysHelloMenuEntity(sysHelloMenuModel);
        sysHelloMenuService.update(sysHelloMenu);
        return sysHelloMenuModel;
    }
}