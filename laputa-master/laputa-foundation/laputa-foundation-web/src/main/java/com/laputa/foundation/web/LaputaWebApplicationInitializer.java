package com.laputa.foundation.web;

import com.laputa.foundation.spring.config.LaputaRootConfig;
import com.laputa.foundation.web.config.LaputaServletConfig;
import com.laputa.foundation.web.filter.LaputaTransIdFilter;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.ArrayList;

/**
 * 该app 最后初始化, 其他高于该级别的往该对象注册 config filter
 * Created by JiangDongPing on 2016/12/16.
 */
@org.springframework.core.annotation.Order(Ordered.LOWEST_PRECEDENCE)
public class LaputaWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static ArrayList<Class<?>> rootConfigClassList = new ArrayList<>();

    private static ArrayList<Filter> pluginFilter = new ArrayList<>();

    static {
        rootConfigClassList.add(LaputaRootConfig.class);
        rootConfigClassList.add(LaputaServletConfig.class);
    }

    public static void pluginConfig(Class<?> configClazz) {
        for (Class clazz : rootConfigClassList) {
            if (clazz.equals(configClazz)) {
                return;
            }
        }
        rootConfigClassList.add(configClazz);
    }

    public static void pluginFilter(Filter filter) {
        if (pluginFilter.size() > 0) {
            for (Filter repeat : pluginFilter) {
                if (filter == repeat) {
                    return;
                }
            }
        }
        pluginFilter.add(filter);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnsiOutput.setEnabled(AnsiOutput.Enabled.ALWAYS);
        super.onStartup(servletContext);
    }

    @Override
    protected Filter[] getServletFilters() {
        /* 确保事务ID生成过滤器在最外层,保证所有web请求拥有事务ID */
        pluginFilter.add(new LaputaTransIdFilter());

        return pluginFilter.toArray(new Filter[pluginFilter.size()]);
    }

    /**
     * Specify {@link Configuration @Configuration}
     * and/or {@link Component @Component} classes to be
     * provided to the {@linkplain #createRootApplicationContext() root application context}.
     *
     * @return the configuration classes for the root application context, or {@code null}
     * if creation and registration of a root context is not desired
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return rootConfigClassList.toArray(new Class<?>[rootConfigClassList.size()]);
    }

    /**
     * Specify {@link Configuration @Configuration}
     * and/or {@link Component @Component} classes to be
     * provided to the {@linkplain #createServletApplicationContext() dispatcher servlet
     * application context}.
     *
     * @return the configuration classes for the dispatcher servlet application context or
     * {@code null} if all configuration is specified through root config classes.
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
