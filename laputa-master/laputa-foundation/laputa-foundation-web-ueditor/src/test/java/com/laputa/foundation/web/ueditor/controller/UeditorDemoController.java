package com.laputa.foundation.web.ueditor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jiangdongping on 2017/12/26 0026.
 */
@Controller
@RequestMapping("/ueditor/demo")
public class UeditorDemoController {

    @RequestMapping("/justdev")
    public String config() {

        return "/ueditor/";
    }
}
