package com.laputa.foundation.web.security.service;

import org.springframework.security.core.AuthenticationException;

/**
 * This exception is throw in case of a not activated user trying to authenticate.
 */
public class UserNotExisException extends AuthenticationException {

    public UserNotExisException(String message) {
        super(message);
    }

}
