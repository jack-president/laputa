package com.laputa.fates.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by JiangDongPing on 2016/12/21.
 */
@Configuration
@ComponentScan(basePackages = {"com.laputa.fates.web.controller", "com.laputa.fates.web.service"})
public class FatesConfiguration {

}
