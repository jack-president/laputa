package com.laputa.fates.web.dao;


import com.laputa.fates.web.entity.LaputaAplication;
import com.laputa.foundation.persistence.entity.IdEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;


/**
 * <p>
 * Laputa项目 Jpa Repository<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-04-06T10:15:10+08:00 .
 */
public interface ConfigByApplicationJpaRepository extends Repository<IdEntity, Long> {

    @Query("SELECT laputaAplication FROM  LaputaAplication laputaAplication where laputaAplication.code =:laputaAplicationCode")
    LaputaAplication selectLaputaAplicationBylaputaAplicationCode(@Param("laputaAplicationCode") String laputaAplicationCode);
}