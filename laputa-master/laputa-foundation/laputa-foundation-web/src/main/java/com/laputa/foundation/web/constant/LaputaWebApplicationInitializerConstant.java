package com.laputa.foundation.web.constant;

import org.springframework.core.Ordered;

/**
 * 新 web模块尽可能从该处注册
 * Created by jiangdongping on 2017/9/11 0011.
 */
public enum LaputaWebApplicationInitializerConstant {

    RBAC("角色权限控制", LaputaWebApplicationInitializerConstantOrder.RBAC),
    WEIXINBASE("微信基础", LaputaWebApplicationInitializerConstantOrder.WEIXINBASE);

    LaputaWebApplicationInitializerConstant(String cname, final Integer order) {
        this.cname = cname;
        this.order = order - Ordered.HIGHEST_PRECEDENCE;
    }

    private final String cname;
    private final Integer order;

    public Integer getOrder() {
        return order;
    }

    public String getCname() {
        return cname;
    }
}
