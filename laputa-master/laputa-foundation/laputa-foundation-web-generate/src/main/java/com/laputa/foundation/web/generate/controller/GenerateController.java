package com.laputa.foundation.web.generate.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.laputa.foundation.web.generate.dao.SysEntityDao;
import com.laputa.foundation.web.generate.service.GenerateSercive;

/**
 * Created by JiangDongPing on 2016/11/10.
 */
@Controller
@RequestMapping(value = "/generate")
public class GenerateController {

    @Resource
    private GenerateSercive generateSercive;

    @Resource
    SysEntityDao sysEntityDao;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String list() {
        generateSercive.hello();
        return "generate/index";
    }
}
