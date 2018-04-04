package com.laputa.foundation.web.constant;

import org.springframework.core.Ordered;

/**
 * Created by jiangdongping on 2017/9/11 0011.
 */
public class LaputaWebApplicationInitializerConstantOrder {
    public final static int RBAC = Ordered.HIGHEST_PRECEDENCE + 1;
    public final static int WEIXINBASE = Ordered.HIGHEST_PRECEDENCE + 2;
}
