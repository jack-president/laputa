package com.laputa.foundation.web;


import com.laputa.foundation.web.ueditor.UeditorConfiguration;
import org.springframework.core.Ordered;

import javax.servlet.ServletContext;

/**
 * Created by JiangDongPing on 2016/12/20.
 */
@org.springframework.core.annotation.Order(Ordered.HIGHEST_PRECEDENCE)
public class PluginUeditorWebApplicationInitializer extends AbstractLaputaPluginWebApplicationInitializer {

    @Override
    protected Class<?>[] getPluginConfigClasses() {
        return new Class[]{UeditorConfiguration.class};
    }


    @Override
    protected void registerServlet(ServletContext servletContext) {

    }
}
