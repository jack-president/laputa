package com.laputa.foundation.web.rbac.controller;

import com.laputa.foundation.web.rbac.entity.SysFile;
import com.laputa.foundation.web.rbac.model.SysFileModel;
import com.laputa.foundation.web.kendo.model.DataSourceRequest;
import com.laputa.foundation.web.kendo.model.DataSourceResult;
import com.laputa.foundation.web.rbac.service.SysFileService;
import com.laputa.foundation.web.rbac.converter.SysFileBeanCopier;


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
 * 系统文件 Controller<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-02-03T16:53:41+08:00 .
*/
@Controller
@RequestMapping("/rbac/sysfile")
public class SysFileController {

    @Resource
    private SysFileService sysFileService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "/rbac/sysfile/list";
    }

    @ResponseBody
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public List<SysFileModel> read() {
        List<SysFile> sysFileList = sysFileService.read();
        List<SysFileModel> sysFileModelList = SysFileBeanCopier.sysFileEntityToSysFileModel(sysFileList);
        return sysFileModelList != null ? sysFileModelList : Collections.<SysFileModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readEager", method = {RequestMethod.GET,RequestMethod.POST})
    public List<SysFileModel> readEager() {
        List<SysFile> sysFileList = sysFileService.readEager();
        List<SysFileModel> sysFileModelList = SysFileBeanCopier.sysFileEntityToSysFileModelEager(sysFileList);
        return sysFileModelList != null ? sysFileModelList : Collections.<SysFileModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readDataSource", method = RequestMethod.POST)
    public DataSourceResult readDataSource(@RequestBody DataSourceRequest dataSourceRequest) {
        Page<SysFile> sysFilePage = sysFileService.readDataSource(dataSourceRequest);
        List<SysFileModel> sysFileModelList = SysFileBeanCopier.sysFileEntityToSysFileModelEager(sysFilePage.getContent());
        DataSourceResult dataSourceResult = new DataSourceResult(sysFileModelList, sysFilePage.getTotalElements());
        return dataSourceResult;
    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public SysFileModel create(@RequestBody SysFileModel sysFileModel) {
        SysFile sysFile = SysFileBeanCopier.sysFileModelToSysFileEntity(sysFileModel);
        SysFile createSysFile = sysFileService.create(sysFile);
        sysFileModel.setId(createSysFile.getId());
        return sysFileModel;
    }

    @ResponseBody
    @RequestMapping(value = "/destory", method = RequestMethod.POST)
    public SysFileModel destory(@RequestBody SysFileModel sysFileModel) {
        SysFile sysFile = SysFileBeanCopier.sysFileModelToSysFileEntity(sysFileModel);
        sysFileService.destory(sysFile);
        return sysFileModel;
    }


    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public SysFileModel update(@RequestBody SysFileModel sysFileModel) {
        SysFile sysFile = SysFileBeanCopier.sysFileModelToSysFileEntity(sysFileModel);
        sysFileService.update(sysFile);
        return sysFileModel;
    }
}