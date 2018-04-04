package com.laputa.foundation.web.security.util;

import com.laputa.foundation.web.security.api.LaputaAccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;

/**
 * IOC 顺序控制对象, 无任何业务意义
 * Created by jiangdongping on 2017/9/14 0014.
 */
public final class IocUtilLaputaAccessDecisionVoter implements LaputaAccessDecisionVoter {
    @Override
    public String getName() {
        throw new RuntimeException("禁止引用");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        throw new RuntimeException("禁止引用");
    }

    @Override
    public boolean supports(Class<?> clazz) {
        throw new RuntimeException("禁止引用");
    }

    @Override
    public int vote(Authentication authentication, FilterInvocation object, Collection<ConfigAttribute> attributes) {
        throw new RuntimeException("禁止引用");
    }
}
