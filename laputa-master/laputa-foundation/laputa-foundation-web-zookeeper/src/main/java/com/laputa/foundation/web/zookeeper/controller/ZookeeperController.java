package com.laputa.foundation.web.zookeeper.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jiangdongping on 2017/12/26 0026.
 */
@Controller
@RequestMapping("/zookeeper")
public class ZookeeperController {

    private static final Logger logger = LoggerFactory.getLogger(ZookeeperController.class);


    @RequestMapping("/index")
    public String index() {
        return "/zookeeper/index";
    }
}
