package com.laputa.foundation.web;

import java.util.List;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.core.Ordered;
import org.springframework.web.WebApplicationInitializer;

/**
 * Created by JiangDongPing on 2016/12/20.
 */
@org.springframework.core.annotation.Order(Ordered.HIGHEST_PRECEDENCE)
public abstract class AbstractLaputaPluginWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public final void onStartup(ServletContext servletContext) throws ServletException {
        pluginConfig();
        pluginFilter();
        registerServlet(servletContext);
    }

    protected Class<?>[] getPluginConfigClasses() {
        return null;
    }

    protected List<Filter> getPluginFilterList() {
        return null;
    }

    private void pluginConfig() {
        Class<?>[] pluginConfigClasses = getPluginConfigClasses();
        if (pluginConfigClasses != null && pluginConfigClasses.length > 0) {
            for (Class<?> pluginConfigClass : pluginConfigClasses) {
                LaputaWebApplicationInitializer.pluginConfig(pluginConfigClass);
            }
        }
    }

    private void pluginFilter() {
        List<Filter> pluginFilterList = getPluginFilterList();
        if (pluginFilterList != null && pluginFilterList.size() > 0) {
            for (Filter pluginFilter : pluginFilterList) {
                LaputaWebApplicationInitializer.pluginFilter(pluginFilter);
            }
        }
    }

    protected void registerServlet(ServletContext servletContext) {

    }
}
