package com.laputa.foundation.web.security.service;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by jiangdongping on 2018/2/25 0025.
 */
public class CaptchaAuthenticationException extends AuthenticationException {
    public CaptchaAuthenticationException() {
        super("");
    }
}
