package com.laputa.foundation.web.generate.service;

import com.laputa.foundation.web.generate.GenerateConfiguration;
import com.laputa.foundation.web.generate.entity.SysHelloMenu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jiangdongping on 2018/3/7 0007.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ContextConfiguration(classes = {SysHelloMenuServiceTestClient.class, GenerateConfiguration.class})
@ImportResource(value = {"classpath*:/laputa-config/springcontext/applicationcontext-foundation-persistence.xml",
        "classpath*:/laputa-config/springcontext/applicationcontext-foundation-web-generate-dao.xml"})
@Transactional
public class SysHelloMenuServiceTestClient {

    // GenerateConfiguration

    @Autowired
    SysHelloMenuService sysHelloMenuService;


    @Test
    public void testVvi() {

        SysHelloMenu sysHelloMenu = new SysHelloMenu();

        sysHelloMenu.setCode("sss");
        sysHelloMenu.setParentId(1222L);

        try {

            sysHelloMenuService.create(sysHelloMenu);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
