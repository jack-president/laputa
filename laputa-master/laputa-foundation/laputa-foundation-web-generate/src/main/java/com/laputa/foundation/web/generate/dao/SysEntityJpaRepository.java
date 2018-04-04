package com.laputa.foundation.web.generate.dao;

import com.laputa.foundation.web.generate.entity.SysEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <p/>
 * 实体表 Jpa Repository<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2016-11-30T17:05:35+08:00 .
 */
public interface SysEntityJpaRepository extends JpaRepository<SysEntity, Long>, JpaSpecificationExecutor<SysEntity> {
}