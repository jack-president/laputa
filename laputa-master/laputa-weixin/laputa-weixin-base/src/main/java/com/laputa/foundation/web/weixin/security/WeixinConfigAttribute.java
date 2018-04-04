package com.laputa.foundation.web.weixin.security;

import com.laputa.foundation.web.security.api.LaputaConfigAttribute;

/**
 * Created by jiangdongping on 2017/9/14 0014.
 */
public class WeixinConfigAttribute implements LaputaConfigAttribute {

    private final String antPrefix;
    private final String attribute;

    public WeixinConfigAttribute(String antPrefix, String attribute) {
        this.antPrefix = antPrefix;
        this.attribute = attribute;
    }

    @Override
    public String antPrefix() {
        return antPrefix;
    }

    @Override
    public String getAttribute() {
        return attribute;
    }
}
