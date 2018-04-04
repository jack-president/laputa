package com.laputa.foundation.web.weixin.controller;

import com.laputa.foundation.web.weixin.entity.WeixinBaseConfig;
import com.laputa.foundation.web.weixin.model.WeixinBaseConfigModel;
import com.laputa.foundation.web.kendo.model.DataSourceRequest;
import com.laputa.foundation.web.kendo.model.DataSourceResult;
import com.laputa.foundation.web.weixin.service.WeixinBaseConfigService;
import com.laputa.foundation.web.weixin.converter.WeixinBaseConfigBeanCopier;


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
 * 微信公众号基础配置 Controller<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-09-13T16:56:42+08:00 .
*/
@Controller
@RequestMapping("/weixin/weixinbaseconfig")
public class WeixinBaseConfigController {

    @Resource
    private WeixinBaseConfigService weixinBaseConfigService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "/weixin/weixinbaseconfig/list";
    }

    @ResponseBody
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public List<WeixinBaseConfigModel> read() {
        List<WeixinBaseConfig> weixinBaseConfigList = weixinBaseConfigService.read();
        List<WeixinBaseConfigModel> weixinBaseConfigModelList = WeixinBaseConfigBeanCopier.weixinBaseConfigEntityToWeixinBaseConfigModel(weixinBaseConfigList);
        return weixinBaseConfigModelList != null ? weixinBaseConfigModelList : Collections.<WeixinBaseConfigModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readEager", method = {RequestMethod.GET,RequestMethod.POST})
    public List<WeixinBaseConfigModel> readEager() {
        List<WeixinBaseConfig> weixinBaseConfigList = weixinBaseConfigService.readEager();
        List<WeixinBaseConfigModel> weixinBaseConfigModelList = WeixinBaseConfigBeanCopier.weixinBaseConfigEntityToWeixinBaseConfigModelEager(weixinBaseConfigList);
        return weixinBaseConfigModelList != null ? weixinBaseConfigModelList : Collections.<WeixinBaseConfigModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readDataSource", method = RequestMethod.POST)
    public DataSourceResult readDataSource(@RequestBody DataSourceRequest dataSourceRequest) {
        Page<WeixinBaseConfig> weixinBaseConfigPage = weixinBaseConfigService.readDataSource(dataSourceRequest);
        List<WeixinBaseConfigModel> weixinBaseConfigModelList = WeixinBaseConfigBeanCopier.weixinBaseConfigEntityToWeixinBaseConfigModelEager(weixinBaseConfigPage.getContent());
        DataSourceResult dataSourceResult = new DataSourceResult(weixinBaseConfigModelList, weixinBaseConfigPage.getTotalElements());
        return dataSourceResult;
    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public WeixinBaseConfigModel create(@RequestBody WeixinBaseConfigModel weixinBaseConfigModel) {
        WeixinBaseConfig weixinBaseConfig = WeixinBaseConfigBeanCopier.weixinBaseConfigModelToWeixinBaseConfigEntity(weixinBaseConfigModel);
        WeixinBaseConfig createWeixinBaseConfig = weixinBaseConfigService.create(weixinBaseConfig);
        weixinBaseConfigModel.setId(createWeixinBaseConfig.getId());
        return weixinBaseConfigModel;
    }

    @ResponseBody
    @RequestMapping(value = "/destory", method = RequestMethod.POST)
    public WeixinBaseConfigModel destory(@RequestBody WeixinBaseConfigModel weixinBaseConfigModel) {
        WeixinBaseConfig weixinBaseConfig = WeixinBaseConfigBeanCopier.weixinBaseConfigModelToWeixinBaseConfigEntity(weixinBaseConfigModel);
        weixinBaseConfigService.destory(weixinBaseConfig);
        return weixinBaseConfigModel;
    }


    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public WeixinBaseConfigModel update(@RequestBody WeixinBaseConfigModel weixinBaseConfigModel) {
        WeixinBaseConfig weixinBaseConfig = WeixinBaseConfigBeanCopier.weixinBaseConfigModelToWeixinBaseConfigEntity(weixinBaseConfigModel);
        weixinBaseConfigService.update(weixinBaseConfig);
        return weixinBaseConfigModel;
    }
}