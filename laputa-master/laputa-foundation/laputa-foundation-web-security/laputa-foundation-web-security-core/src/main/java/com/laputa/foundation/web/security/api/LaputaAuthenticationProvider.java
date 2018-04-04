package com.laputa.foundation.web.security.api;

import org.springframework.security.authentication.AuthenticationProvider;

/**
 * Created by jiangdongping on 2017/9/14 0014.
 */
public interface LaputaAuthenticationProvider extends AuthenticationProvider {
    String getName();
}
