package com.laputa.foundation.web.generate.dao;

import org.springframework.data.repository.CrudRepository;

import com.laputa.foundation.web.generate.entity.SysEntity;

/**
 * Created by JiangDongPing on 2016/11/10.
 */
public interface SysEntityRepository extends CrudRepository<SysEntity, Long> {
    SysEntity findByClazzName(String clazzName);
}
