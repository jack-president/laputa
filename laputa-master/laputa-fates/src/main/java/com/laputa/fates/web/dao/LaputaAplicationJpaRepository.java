package com.laputa.fates.web.dao;


import com.laputa.fates.web.entity.LaputaAplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * <p>
 * Laputa项目 Jpa Repository<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-04-06T10:15:08+08:00 .
 */
public interface LaputaAplicationJpaRepository extends JpaRepository<LaputaAplication, java.lang.Long>, JpaSpecificationExecutor<LaputaAplication> {

	@Query("SELECT laputaAplication FROM LaputaAplication laputaAplication WHERE laputaAplication.code=:code")
	LaputaAplication selectByCode(@Param(value = "code") String code);

}