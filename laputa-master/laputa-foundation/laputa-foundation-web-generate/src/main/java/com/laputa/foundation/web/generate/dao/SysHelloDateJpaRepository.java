package com.laputa.foundation.web.generate.dao;


import com.laputa.foundation.web.generate.entity.SysHelloDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <p>
 * Hi时间 Jpa Repository<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2016-12-07T16:50:06+08:00 .
 */
public interface SysHelloDateJpaRepository extends JpaRepository<SysHelloDate, java.lang.Long>, JpaSpecificationExecutor<SysHelloDate> {

}