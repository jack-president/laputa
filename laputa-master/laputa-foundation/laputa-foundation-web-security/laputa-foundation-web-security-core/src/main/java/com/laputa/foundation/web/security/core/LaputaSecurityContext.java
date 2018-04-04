package com.laputa.foundation.web.security.core;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

/**
 * Created by jiangdongping on 2017/4/17 0017.
 */
public class LaputaSecurityContext implements SecurityContext {
    private Authentication authentication;


    @Override
    public Authentication getAuthentication() {
        return authentication;
    }

    @Override
    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }
}
