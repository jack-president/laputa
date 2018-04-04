package com.laputa.foundation.web.generate.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.laputa.foundation.web.generate.entity.SysEntity;

/**
 * Created by JiangDongPing on 2016/11/10.
 */
public class SysEntityDao {
    @PersistenceContext
    private EntityManager entityManager;

    public SysEntity insert(SysEntity sysEntity) {
        entityManager.persist(sysEntity);
        return sysEntity;
    }

    public SysEntity test(SysEntity sysEntity) {
        entityManager.createNativeQuery("SELECT  * FROM sys_entity WHERE 1 =1").getResultList();
        return sysEntity;
    }
}
