package com.laputa.foundation.web.security.api;

import org.springframework.security.access.ConfigAttribute;

/**
 * Created by jiangdongping on 2017/9/14 0014.
 */
public interface LaputaConfigAttribute extends ConfigAttribute {
    String antPrefix();
}
