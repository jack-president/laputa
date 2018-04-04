package com.laputa.foundation.web.weixin.dao;


import com.laputa.foundation.web.weixin.entity.WeixinBaseConfig;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * <p>
 * 微信公众号基础配置 Jpa Repository<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-09-13T16:56:42+08:00 .
 */
public interface WeixinBaseConfigJpaRepository extends JpaRepository<WeixinBaseConfig, java.lang.Long>, JpaSpecificationExecutor<WeixinBaseConfig> {

	@Query("SELECT weixinBaseConfig FROM WeixinBaseConfig weixinBaseConfig WHERE weixinBaseConfig.code=:code")
	WeixinBaseConfig selectByCode(@Param(value = "code") String code);

}