package com.laputa.foundation.web.weixin.security;

import com.laputa.foundation.web.security.api.LaputaAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Created by jiangdongping on 2017/9/14 0014.
 */
public class LaputaWeixinAuthenticationProvider implements LaputaAuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (WeixinAuthentication.class.equals(authentication));
    }

    @Override
    public String getName() {
        return "微信鉴权服务";
    }
}
