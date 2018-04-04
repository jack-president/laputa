package com.laputa.foundation.web.weixin.dao;


import com.laputa.foundation.web.weixin.entity.WeixinUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * <p>
 * 微信用户 Jpa Repository<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-09-15T17:24:50+08:00 .
 */
public interface WeixinUserJpaRepository extends JpaRepository<WeixinUser, java.lang.Long>, JpaSpecificationExecutor<WeixinUser> {
}