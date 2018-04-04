package com.laputa.foundation.web;


import com.laputa.foundation.web.constant.LaputaWebApplicationInitializerConstantOrder;
import com.laputa.foundation.web.servlet.WeixinSnsServlet;
import com.laputa.foundation.web.servlet.WeixinTokenCheckServlet;
import com.laputa.foundation.web.weixin.WeixinConfiguration;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * Created by JiangDongPing on 2016/12/20.
 */
@org.springframework.core.annotation.Order(LaputaWebApplicationInitializerConstantOrder.WEIXINBASE)
public class PluginWeixinWebApplicationInitializer extends AbstractLaputaPluginWebApplicationInitializer {

    @Override
    protected Class<?>[] getPluginConfigClasses() {
        return new Class[]{WeixinConfiguration.class};
    }


    @Override
    protected void registerServlet(ServletContext servletContext) {
        WeixinTokenCheckServlet weixinTokenCheckServlet = new WeixinTokenCheckServlet();
        ServletRegistration.Dynamic registration =
                servletContext.addServlet(WeixinTokenCheckServlet.class.getSimpleName(), weixinTokenCheckServlet);

        registration.setLoadOnStartup(1);
        registration.addMapping("/weixin/tokencheck");
        registration.setAsyncSupported(true);

        WeixinSnsServlet weixinSnsServlet = new WeixinSnsServlet();
        ServletRegistration.Dynamic weixinSnsServletRegistration =
                servletContext.addServlet(WeixinSnsServlet.class.getSimpleName(), weixinSnsServlet);
        weixinSnsServletRegistration.setLoadOnStartup(1);
        weixinSnsServletRegistration.addMapping("/weixinapi/sns");
        weixinSnsServletRegistration.setAsyncSupported(true);
    }
}