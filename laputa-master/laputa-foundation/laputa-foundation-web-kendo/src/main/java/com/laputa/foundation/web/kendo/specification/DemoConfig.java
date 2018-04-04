package com.laputa.foundation.web.kendo.specification;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by JiangDongPing on 2016/12/20.
 */
@Configuration
@ImportResource({"classpath*:laputa-springmvc/spring-mvsssc-*.xml"})
public class DemoConfig {

    @PostConstruct
    public void init(){
        DemoConfig.class.getSimpleName();
    }
}
