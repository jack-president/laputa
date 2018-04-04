package com.laputa.foundation.web.security.filter.handler;

import com.laputa.foundation.web.security.util.HttpServletRequestDataWrap;
import org.springframework.security.access.AccessDeniedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jiangdongping on 2017/3/29 0029.
 */
public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        HttpServletRequestDataWrap.putWrapAttr(request, accessDeniedException);
        request.getRequestDispatcher("/security/show/accessdenied").forward(request, response);
    }
}
