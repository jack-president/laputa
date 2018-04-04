package com.laputa.foundation.web.zookeeper;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by JiangDongPing on 2016/12/21.
 */
@Configuration
@ComponentScan(basePackages = {
        "com.laputa.foundation.web.zookeeper.controller",
        "com.laputa.foundation.web.zookeeper.service"})
public class ZookeeperConfiguration {
}
