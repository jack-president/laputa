package com.laputa.foundation.web.weixin.controller;

import com.laputa.foundation.web.weixin.entity.WeixinUser;
import com.laputa.foundation.web.weixin.model.WeixinUserModel;
import com.laputa.foundation.web.kendo.model.DataSourceRequest;
import com.laputa.foundation.web.kendo.model.DataSourceResult;
import com.laputa.foundation.web.weixin.service.WeixinUserService;
import com.laputa.foundation.web.weixin.converter.WeixinUserBeanCopier;


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
 * 微信用户 Controller<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-09-15T17:24:50+08:00 .
*/
@Controller
@RequestMapping("/weixin/weixinuser")
public class WeixinUserController {

    @Resource
    private WeixinUserService weixinUserService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "/weixin/weixinuser/list";
    }

    @ResponseBody
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public List<WeixinUserModel> read() {
        List<WeixinUser> weixinUserList = weixinUserService.read();
        List<WeixinUserModel> weixinUserModelList = WeixinUserBeanCopier.weixinUserEntityToWeixinUserModel(weixinUserList);
        return weixinUserModelList != null ? weixinUserModelList : Collections.<WeixinUserModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readEager", method = {RequestMethod.GET,RequestMethod.POST})
    public List<WeixinUserModel> readEager() {
        List<WeixinUser> weixinUserList = weixinUserService.readEager();
        List<WeixinUserModel> weixinUserModelList = WeixinUserBeanCopier.weixinUserEntityToWeixinUserModelEager(weixinUserList);
        return weixinUserModelList != null ? weixinUserModelList : Collections.<WeixinUserModel>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readDataSource", method = RequestMethod.POST)
    public DataSourceResult readDataSource(@RequestBody DataSourceRequest dataSourceRequest) {
        Page<WeixinUser> weixinUserPage = weixinUserService.readDataSource(dataSourceRequest);
        List<WeixinUserModel> weixinUserModelList = WeixinUserBeanCopier.weixinUserEntityToWeixinUserModelEager(weixinUserPage.getContent());
        DataSourceResult dataSourceResult = new DataSourceResult(weixinUserModelList, weixinUserPage.getTotalElements());
        return dataSourceResult;
    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public WeixinUserModel create(@RequestBody WeixinUserModel weixinUserModel) {
        WeixinUser weixinUser = WeixinUserBeanCopier.weixinUserModelToWeixinUserEntity(weixinUserModel);
        WeixinUser createWeixinUser = weixinUserService.create(weixinUser);
        weixinUserModel.setId(createWeixinUser.getId());
        return weixinUserModel;
    }

    @ResponseBody
    @RequestMapping(value = "/destory", method = RequestMethod.POST)
    public WeixinUserModel destory(@RequestBody WeixinUserModel weixinUserModel) {
        WeixinUser weixinUser = WeixinUserBeanCopier.weixinUserModelToWeixinUserEntity(weixinUserModel);
        weixinUserService.destory(weixinUser);
        return weixinUserModel;
    }


    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public WeixinUserModel update(@RequestBody WeixinUserModel weixinUserModel) {
        WeixinUser weixinUser = WeixinUserBeanCopier.weixinUserModelToWeixinUserEntity(weixinUserModel);
        weixinUserService.update(weixinUser);
        return weixinUserModel;
    }
}