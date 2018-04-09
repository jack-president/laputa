package com.laputa.logger.web.controller;

import com.laputa.logger.web.elasticsearch.repositories.type.Log;
import com.laputa.logger.web.service.LoggerElasticsearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * <p>
 * Laputa项目 Controller<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-04-06T10:15:10+08:00 .
 */
@Controller
@RequestMapping("/logger/manager")
public class LoggerManagerController {

    private static Logger logger = LoggerFactory.getLogger(LoggerManagerController.class);

    @Autowired
    private LoggerElasticsearchService loggerElasticsearchService;

    @RequestMapping("index")
    public String index() {
        return "/logger/LoggerManager";
    }


    @RequestMapping("query")
    @ResponseBody
    public List<Log> query(@RequestParam(name = "transId", required = false) String transId) {
        List<Log> logs = loggerElasticsearchService.query(transId);
        return logs;
    }

}