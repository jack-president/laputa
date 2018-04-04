package com.laputa.foundation.web.security.api;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.web.FilterInvocation;

/**
 * Created by jiangdongping on 2017/9/14 0014.
 */
public interface LaputaAccessDecisionVoter extends AccessDecisionVoter<FilterInvocation> {
    String getName();
}
