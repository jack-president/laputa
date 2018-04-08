package com.laputa.fates.web.dao;


import com.laputa.fates.web.entity.LaputaConfig;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * <p>
 * Laputa项目 Jpa Repository<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-04-06T10:15:10+08:00 .
 */
public interface LaputaConfigJpaRepository extends JpaRepository<LaputaConfig, java.lang.Long>, JpaSpecificationExecutor<LaputaConfig> {

	@Query("SELECT laputaConfig FROM LaputaConfig laputaConfig WHERE laputaConfig.code=:code")
	LaputaConfig selectByCode(@Param(value = "code") String code);

}