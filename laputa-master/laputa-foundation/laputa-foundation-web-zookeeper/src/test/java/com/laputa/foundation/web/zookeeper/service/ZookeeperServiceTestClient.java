package com.laputa.foundation.web.zookeeper.service;


import com.laputa.foundation.spring.config.LaputaRootConfig;
import com.laputa.foundation.web.zookeeper.ZookeeperConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@Import(value = {ZookeeperConfiguration.class,LaputaRootConfig.class})
@ContextConfiguration(classes = ZookeeperServiceTestClient.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
public class ZookeeperServiceTestClient {

    @Autowired
    private ZookeeperService zookeeperService;

    @Test
    public void testDemo() {
        zookeeperService.demo();
    }

}
