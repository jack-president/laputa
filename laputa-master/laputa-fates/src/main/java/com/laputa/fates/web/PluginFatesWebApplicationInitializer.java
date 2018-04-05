package com.laputa.fates.web;


import com.laputa.foundation.web.AbstractLaputaPluginWebApplicationInitializer;
import org.springframework.core.Ordered;

import javax.servlet.ServletContext;

/**
 * Created by JiangDongPing on 2016/12/20.
 */
@org.springframework.core.annotation.Order(Ordered.HIGHEST_PRECEDENCE)
public class PluginFatesWebApplicationInitializer extends AbstractLaputaPluginWebApplicationInitializer {

    @Override
    protected Class<?>[] getPluginConfigClasses() {
        return new Class[]{FatesConfiguration.class};
    }

    @Override
    protected void registerServlet(ServletContext servletContext) {
    }

}
