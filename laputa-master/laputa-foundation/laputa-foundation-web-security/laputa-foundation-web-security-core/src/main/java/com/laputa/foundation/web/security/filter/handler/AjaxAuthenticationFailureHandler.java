package com.laputa.foundation.web.security.filter.handler;

import com.laputa.foundation.web.security.service.CaptchaAuthenticationException;
import com.laputa.foundation.web.security.service.UserNotExisException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jiangdongping on 2017/3/27 0027.
 */
public class AjaxAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String forwardUrl = "/security/show/loginfail";
        if (exception instanceof CaptchaAuthenticationException) {
            forwardUrl = "/security/show/captchafail";
        } else if (exception instanceof InternalAuthenticationServiceException) {
            Throwable causeException = exception.getCause();
            if (causeException instanceof UserNotExisException) {
                forwardUrl = "/security/show/usernamenotfound";
            }
        }
        request.getRequestDispatcher(forwardUrl).forward(request, response);
    }
}

