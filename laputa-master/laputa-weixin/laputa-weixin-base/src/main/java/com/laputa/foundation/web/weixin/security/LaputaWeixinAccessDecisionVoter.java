package com.laputa.foundation.web.weixin.security;

import com.laputa.foundation.web.security.api.LaputaAccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;

/**
 * Created by jiangdongping on 2017/9/14 0014.
 */
public class LaputaWeixinAccessDecisionVoter implements LaputaAccessDecisionVoter {
    @Override
    public int vote(Authentication authentication, FilterInvocation object, Collection<ConfigAttribute> attributes) {
        if (authentication == null) {
            return ACCESS_DENIED;
        }
        return ACCESS_ABSTAIN;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        if (attribute instanceof WeixinConfigAttribute) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getName() {
        return "微信访问控制投票器";
    }
}
