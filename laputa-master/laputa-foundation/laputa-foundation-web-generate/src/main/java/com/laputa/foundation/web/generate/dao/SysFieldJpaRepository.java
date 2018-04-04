package com.laputa.foundation.web.generate.dao;

import com.laputa.foundation.web.generate.entity.SysField;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <p>
 * 实体表 Jpa Repository<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2016-11-30T17:23:51+08:00 .
 */
public interface SysFieldJpaRepository extends JpaRepository<SysField, java.lang.Long>, JpaSpecificationExecutor<SysField> {
}