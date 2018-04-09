package com.laputa.logger.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by JiangDongPing on 2016/12/21.
 */
@Configuration
@ComponentScan(basePackages = {"com.laputa.logger.web.controller", "com.laputa.logger.web.service"})
public class LoggerManagerConfiguration {

}
