package com.laputa.foundation.web.filter;

import com.laputa.foundation.logging.mdc.MdcContext;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by jiangdongping on 2017/8/28 0028.
 */
public class LaputaTransIdFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        MdcContext.logTransId();
        chain.doFilter(request, response);
        MdcContext.clear();
    }

    @Override
    public void destroy() {

    }
}
