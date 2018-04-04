package com.laputa.foundation.web.weixin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * <p>
 * 微信公众号基础配置 Controller<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-09-13T16:56:42+08:00 .
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping(value = "/demo2", method = RequestMethod.GET)
    public void list(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getOutputStream().println("hello");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}