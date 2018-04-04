package com.laputa.foundation.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by JiangDongPing on 2016/12/16.
 */
@Configuration
@ImportResource("classpath*:laputa-config/springcontext/applicationcontext-*.xml")
public class LaputaRootConfig {
}
