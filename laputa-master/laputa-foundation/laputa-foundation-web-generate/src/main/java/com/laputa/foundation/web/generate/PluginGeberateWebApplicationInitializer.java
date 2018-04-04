package com.laputa.foundation.web.generate;

import org.springframework.core.Ordered;

import com.laputa.foundation.web.AbstractLaputaPluginWebApplicationInitializer;

/**
 * Created by JiangDongPing on 2016/12/20.
 */
@org.springframework.core.annotation.Order(Ordered.HIGHEST_PRECEDENCE)
public class PluginGeberateWebApplicationInitializer extends AbstractLaputaPluginWebApplicationInitializer {

    @Override
    protected Class<?>[] getPluginConfigClasses() {
        return new Class[] {GenerateConfiguration.class};
    }
}
