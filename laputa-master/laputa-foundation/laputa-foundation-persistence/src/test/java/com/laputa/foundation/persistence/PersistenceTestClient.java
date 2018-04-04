package com.laputa.foundation.persistence;

import com.laputa.foundation.persistence.entity.SysAuElement;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by jiangdongping on 2016/10/28 0028.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:/laputa-config/springcontext/applicationcontext-foundation-persistence.xml"
})
@Transactional
@Rollback(value = false)
public class PersistenceTestClient {

    private final Logger log = LoggerFactory.getLogger(PersistenceTestClient.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    ComboPooledDataSource comboPooledDataSource;

    @Value("${MINPOOLSIZE}")
    Long x;

    @Before
    public void init() {
        log.debug("开启颜色支持 {}", getClass().toString());
    }

    @Test
    public void testDemo() {
        log.warn("开始测试 testDemo {}", getClass().toString());
        //        List<SysAuElement> s =
        //                entityManager.createNativeQuery("SELECT * FROM sys_element;", SysAuElement.class).getResultList();
        SysAuElement sysAuElement = new SysAuElement();
        sysAuElement.setCname("名字A");
        sysAuElement.setCode("CCCC");

        entityManager.persist(sysAuElement);

    }
}
