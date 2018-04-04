package com.laputa.foundation.web.rbac;

import com.laputa.foundation.web.rbac.model.RbacDTO;
import com.laputa.foundation.web.rbac.service.LaputRbacService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by JiangDongPing on 2018/04/02.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RbacServiceTestClient.class})
//@Import(value = {LaputaElasticsearchConfiguration.class})
@ImportResource(value = {"classpath*:/laputa-config/springcontext/applicationcontext-foundation-*.xml"})
public class RbacServiceTestClient {

    private static Logger logger = LoggerFactory.getLogger(RbacServiceTestClient.class);

    @Autowired
    LaputRbacService laputRbacService;

    @Test
    public void testAll() {
        RbacDTO rbacDTO = laputRbacService.getAllRbacMetaData();

        logger.info("{}", rbacDTO);
    }


}
