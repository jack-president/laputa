package com.laputa.foundation.web.generate;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by JiangDongPing on 2016/12/21.
 */
@Configuration
@ComponentScan(basePackages = {"com.laputa.foundation.web.generate.controller", "com.laputa.foundation.web.generate.service"})
public class GenerateConfiguration {
}
