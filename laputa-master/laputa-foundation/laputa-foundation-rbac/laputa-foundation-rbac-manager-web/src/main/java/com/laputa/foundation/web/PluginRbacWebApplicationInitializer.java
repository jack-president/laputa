package com.laputa.foundation.web;


import com.laputa.foundation.web.constant.LaputaWebApplicationInitializerConstantOrder;
import com.laputa.foundation.web.rbac.RbacConfiguration;

import javax.servlet.ServletContext;

/**
 * Created by JiangDongPing on 2016/12/20.
 */
@org.springframework.core.annotation.Order(LaputaWebApplicationInitializerConstantOrder.RBAC)
public class PluginRbacWebApplicationInitializer extends AbstractLaputaPluginWebApplicationInitializer {

    @Override
    protected Class<?>[] getPluginConfigClasses() {
        return new Class[]{RbacConfiguration.class};
    }

    @Override
    protected void registerServlet(ServletContext servletContext) {
    }

}
