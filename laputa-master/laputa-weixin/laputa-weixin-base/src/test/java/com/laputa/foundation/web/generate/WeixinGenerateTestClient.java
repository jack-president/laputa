package com.laputa.foundation.web.generate;

/**
 * Created by jiangdongping on 2017/9/13 0013.
 */

import com.laputa.foundation.web.generate.service.CodeFileService;
import com.laputa.foundation.web.generate.service.GenerateSercive;
import com.laputa.foundation.web.rbac.entity.SysRole;
import com.laputa.foundation.web.rbac.entity.SysUserGroup;
import com.laputa.foundation.web.weixin.entity.WeixinBaseConfig;
import com.laputa.foundation.web.weixin.entity.WeixinUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:/laputa-config/springcontext/applicationcontext-foundation-persistence.xml",
        "classpath*:/laputa-config/springcontext/applicationcontext-foundation-web-generate-dao.xml",
        "classpath*:/laputa-config/springcontext/applicationcontext-foundation-web-generate-service.xml",
        "classpath*:/laputa-config/springcontext/applicationcontext-foundation-web-generate-weixin-entity-*.xml"
})
@Transactional
public class WeixinGenerateTestClient {
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
        codeFileService.codeGenerate(WeixinBaseConfig.class.getName());
        codeFileService.codeGenerate(WeixinUser.class.getName());
    }
}
