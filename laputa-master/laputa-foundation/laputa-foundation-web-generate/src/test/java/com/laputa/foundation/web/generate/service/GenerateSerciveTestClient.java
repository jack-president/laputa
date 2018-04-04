package com.laputa.foundation.web.generate.service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.laputa.foundation.web.generate.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.laputa.foundation.web.generate.dao.SysEntityJpaRepository;
import com.laputa.foundation.web.kendo.specification.LaputaKendoSpecification;

/**
 * Created by JiangDongPing on 2016/11/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:/laputa-config/springcontext/applicationcontext-foundation-persistence.xml",
        "classpath*:/laputa-config/springcontext/applicationcontext-foundation-web-generate-dao.xml",
        "classpath*:/laputa-config/springcontext/applicationcontext-foundation-web-generate-service.xml",
        "classpath*:/laputa-config/springcontext/applicationcontext-foundation-web-generate-entity-*.xml"
})
@Transactional
public class GenerateSerciveTestClient {

    @Resource
    GenerateSercive generateSercive;

    @Resource
    CodeFileService codeFileService;

    @Resource
    SysEntityJpaRepository sysEntityJpaRepository;

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
    @Rollback(value = false)
    public void testGenerate() throws Exception {
        codeFileService.codeGenerate(SysHelloMenu.class.getName());

//        codeFileService.codeGenerate(SysEntity.class.getName());
//        codeFileService.codeGenerate(SysField.class.getName());
//        codeFileService.codeGenerate(SysHelloPermission.class.getName());
//        codeFileService.codeGenerate(SysHelloElement.class.getName());
//        codeFileService.codeGenerate(SysHelloDate.class.getName());
    }

    @Test
    public void testSp() {
        Specification<SysEntity> sysEntitySpecification = new Specification<SysEntity>() {
            @Override
            public Predicate toPredicate(
                    Root<SysEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                root.get("cname");
                root.join("sysFieldCollection", JoinType.LEFT);
                return cb.notEqual(root.get("cname"), "s");
            }
        };

        Object o1 = sysEntityJpaRepository.findAll(new LaputaKendoSpecification<SysEntity>(null));

        Object o2 = sysEntityJpaRepository.findAll(sysEntitySpecification);
    }
}