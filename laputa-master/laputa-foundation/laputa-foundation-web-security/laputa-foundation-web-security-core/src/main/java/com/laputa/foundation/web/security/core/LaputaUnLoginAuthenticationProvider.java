package com.laputa.foundation.web.security.core;

import com.laputa.foundation.web.security.api.LaputaAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Created by jiangdongping on 2017/8/30 0030.
 */
public class LaputaUnLoginAuthenticationProvider implements LaputaAuthenticationProvider {

    private static LaputaUnLoginAuthenticationProvider laputaUnLoginAuthenticationProvider
            = new LaputaUnLoginAuthenticationProvider();

    public static LaputaUnLoginAuthenticationProvider getInstance() {
        return laputaUnLoginAuthenticationProvider;
    }

    private LaputaUnLoginAuthenticationProvider() {
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (LaputaUnLoginAuthentication.class.equals(authentication));
    }

    @Override
    public String getName() {
        return "未登录用户鉴权服务";
    }
}
