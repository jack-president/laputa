package com.laputa.fates.web.generate;

import com.laputa.fates.web.entity.LaputaAplication;
import com.laputa.fates.web.entity.LaputaConfig;
import com.laputa.fates.web.entity.LaputaConfigHistory;
import com.laputa.foundation.web.generate.service.CodeFileService;
import com.laputa.foundation.web.generate.service.GenerateSercive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by JiangDongPing on 2016/11/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:/laputa-config/springcontext/applicationcontext-foundation-persistence.xml",
        "classpath*:/laputa-config/springcontext/applicationcontext-foundation-web-generate-dao.xml",
        "classpath*:/laputa-config/springcontext/applicationcontext-foundation-web-generate-service.xml",
        "classpath*:/laputa-config/springcontext/applicationcontext-foundation-web-generate-fates-entity-*.xml"
})
@Transactional
public class FatesGenerateTestClient {
    @Resource
    GenerateSercive generateSercive;

    @Resource
    CodeFileService codeFileService;

    @Test
    @Rollback(value = false)
    public void testRefresh() throws Exception {
        generateSercive.clear();
        generateSercive.init();
    }

    @Test
    @Rollback(value = false)
    public void testClear() throws Exception {
        generateSercive.clear();
    }

    @Test
    public void code() throws Exception {
        codeFileService.codeGenerate(LaputaAplication.class.getName());
        codeFileService.codeGenerate(LaputaConfig.class.getName());
        codeFileService.codeGenerate(LaputaConfigHistory.class.getName());
    }
}
