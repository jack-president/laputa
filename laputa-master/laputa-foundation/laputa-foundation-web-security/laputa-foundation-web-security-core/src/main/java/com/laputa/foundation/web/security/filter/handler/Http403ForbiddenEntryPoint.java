package com.laputa.foundation.web.security.filter.handler;

import com.laputa.foundation.web.security.util.HttpServletRequestDataWrap;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jiangdongping on 2017/3/30 0030.
 */
public class Http403ForbiddenEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        HttpServletRequestDataWrap.putWrapAttr(request,authException);
        request.getRequestDispatcher("/security/show/accessdenied").forward(request, response);
    }
}
