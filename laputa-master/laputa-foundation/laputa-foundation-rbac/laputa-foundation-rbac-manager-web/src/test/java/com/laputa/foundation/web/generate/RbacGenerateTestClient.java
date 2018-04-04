package com.laputa.foundation.web.generate;

import java.io.IOException;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.laputa.foundation.web.generate.entity.SysEntity;
import com.laputa.foundation.web.generate.service.CodeFileService;
import com.laputa.foundation.web.generate.service.GenerateSercive;
import com.laputa.foundation.web.rbac.entity.SysElement;
import com.laputa.foundation.web.rbac.entity.SysFile;
import com.laputa.foundation.web.rbac.entity.SysMenu;
import com.laputa.foundation.web.rbac.entity.SysOperation;
import com.laputa.foundation.web.rbac.entity.SysPermission;
import com.laputa.foundation.web.rbac.entity.SysRole;
import com.laputa.foundation.web.rbac.entity.SysUser;
import com.laputa.foundation.web.rbac.entity.SysUserGroup;

/**
 * Created by JiangDongPing on 2016/11/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:/laputa-config/springcontext/applicationcontext-foundation-persistence.xml",
        "classpath*:/laputa-config/springcontext/applicationcontext-foundation-web-generate-dao.xml",
        "classpath*:/laputa-config/springcontext/applicationcontext-foundation-web-generate-service.xml",
        "classpath*:/laputa-config/springcontext/applicationcontext-foundation-web-generate-rbac-entity-*.xml"
})
@Transactional
public class RbacGenerateTestClient {
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
        codeFileService.codeGenerate(SysElement.class.getName());
        codeFileService.codeGenerate(SysFile.class.getName());
        codeFileService.codeGenerate(SysMenu.class.getName());
        codeFileService.codeGenerate(SysOperation.class.getName());
        codeFileService.codeGenerate(SysPermission.class.getName());
        codeFileService.codeGenerate(SysRole.class.getName());
        codeFileService.codeGenerate(SysUser.class.getName());
        codeFileService.codeGenerate(SysUserGroup.class.getName());
    }
}
