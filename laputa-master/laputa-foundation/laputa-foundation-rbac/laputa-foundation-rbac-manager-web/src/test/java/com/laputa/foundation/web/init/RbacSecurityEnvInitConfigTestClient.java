package com.laputa.foundation.web.init;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.laputa.foundation.web.rbac.dao.SysOperationJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysUserJpaRepository;

/**
 * Created by JiangDongPing on 2017/1/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RbacSecurityEnvInitConfig.class})
@Rollback(value = false)
public class RbacSecurityEnvInitConfigTestClient {

    @Inject
    RbacSecurityEnvInitConfig rbacSecurityEnvInitConfig;

    @Inject
    SysOperationJpaRepository sysOperationJpaRepository;

    @Inject
    SysUserJpaRepository sysUserJpaRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    public void testInitEnv() throws Exception {

        rbacSecurityEnvInitConfig.clear();
        rbacSecurityEnvInitConfig.initEnv();
        rbacSecurityEnvInitConfig.initPermission();
        rbacSecurityEnvInitConfig.initRole();
        rbacSecurityEnvInitConfig.initUser();
    }
}