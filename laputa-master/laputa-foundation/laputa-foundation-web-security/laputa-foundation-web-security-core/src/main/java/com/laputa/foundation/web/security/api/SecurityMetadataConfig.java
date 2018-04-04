package com.laputa.foundation.web.security.api;

import com.laputa.foundation.web.security.service.LaputaSecurityMetadataSourceService;

import java.util.List;

/**
 * security 元数据配置接口
 * Created by jiangdongping on 2017/9/11 0011.
 */
public interface SecurityMetadataConfig {

    /**
     * 配置名称
     *
     * @return
     */
    String name();

    /**
     * 配置地址为任何用户可以访问
     *
     * @return
     * @see LaputaSecurityMetadataSourceService
     */
    List<String> configAntPathAnyoneAccess();

    /**
     * 配置资源ConfigAttribute
     *
     * @return
     */
    List<? extends LaputaConfigAttribute> configAntConfigAttribute();
}
