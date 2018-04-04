package com.laputa.foundation.web;


import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultBackground;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.impl.DefaultNoise;
import com.google.code.kaptcha.impl.WaterRipple;
import com.google.code.kaptcha.servlet.KaptchaServlet;
import com.google.code.kaptcha.text.impl.DefaultTextCreator;
import com.google.code.kaptcha.text.impl.DefaultWordRenderer;
import com.laputa.foundation.web.security.SpringSecurityConfiguration;
import org.springframework.core.Ordered;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * Created by JiangDongPing on 2016/12/20.
 */
@org.springframework.core.annotation.Order(Ordered.HIGHEST_PRECEDENCE)
public class PluginSecurityWebApplicationInitializer extends AbstractLaputaPluginWebApplicationInitializer {

    @Override
    protected Class<?>[] getPluginConfigClasses() {
        return new Class[]{SpringSecurityConfiguration.class};
    }

    //    @Override
    //    protected List<Filter> getPluginFilterList() {
    //        Filter springSecurityFilterChain = new DelegatingFilterProxy("springSecurityFilterChain");
    //
    //        return Arrays.asList(springSecurityFilterChain);
    //    }

    @Override
    protected void registerServlet(ServletContext servletContext) {
        registerKaptchaServlet(servletContext);
        registerHttpSessionEventPublisher(servletContext);
    }

    private void registerHttpSessionEventPublisher(ServletContext servletContext) {
        HttpSessionEventPublisher httpSessionEventPublisher = new HttpSessionEventPublisher();
        servletContext.addListener(httpSessionEventPublisher);

        /**
         * Bean声明: {@link WebSecurityConfiguration#springSecurityFilterChain() }
         * 实现类 :{@link  org.springframework.security.web.FilterChainProxy }
         * 配置相关 : {@link org.springframework.security.config.BeanIds.SPRING_SECURITY_FILTER_CHAIN =  "springSecurityFilterChain" }
         * 通用配置一般的用
         * {@link SecurityConfiguration#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity) }
         * 足以应付, 无需自己声明 FilterChainProxy
         */
        Filter springSecurityFilterChain = new DelegatingFilterProxy(BeanIds.SPRING_SECURITY_FILTER_CHAIN);
        FilterRegistration.Dynamic filterRegistration = servletContext.addFilter(BeanIds.SPRING_SECURITY_FILTER_CHAIN, springSecurityFilterChain);
        filterRegistration.addMappingForUrlPatterns(null, false, "/*");
    }


    private void registerKaptchaServlet(ServletContext servletContext) {
        String servletName = KaptchaServlet.class.getSimpleName();
        KaptchaServlet kaptchaServlet = new KaptchaServlet();
        ServletRegistration.Dynamic registration = servletContext.addServlet(servletName, kaptchaServlet);

        /**
         * 图片边框，合法值：yes , no
         * */
        registration.setInitParameter(Constants.KAPTCHA_BORDER, "yes");

        /**
         * 边框颜色，合法值： r,g,b (and optional alpha) 或者 white,black,blue.
         * java.awt.Color.BLACK
         */
        registration.setInitParameter(Constants.KAPTCHA_BORDER_COLOR, "229,230,231");

        /**
         * 边框厚度，合法值：>0
         */
        registration.setInitParameter(Constants.KAPTCHA_BORDER_THICKNESS, "1");

        /**
         * 图片宽 150
         */
        registration.setInitParameter(Constants.KAPTCHA_IMAGE_WIDTH, "100");

        /**
         * 图片高 50
         */
        registration.setInitParameter(Constants.KAPTCHA_IMAGE_HEIGHT, "54");

        /**
         * 图片实现类
         */
        registration.setInitParameter(Constants.KAPTCHA_PRODUCER_IMPL, DefaultKaptcha.class.getName());

        /**
         * 文本实现类
         */
        registration.setInitParameter(Constants.KAPTCHA_TEXTPRODUCER_IMPL, DefaultTextCreator.class.getName());

        /**
         * 文本集合，验证码值从此集合中获取
         */
        registration.setInitParameter(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING, "2345689");

        /**
         * 验证码长度 4
         */
        registration.setInitParameter(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");

        /**
         * 字体 Arial, Courier
         * new Font("Arial", Font.BOLD, fontSize), new Font("Courier", Font.BOLD, fontSize)
         */
        registration.setInitParameter(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, "Arial,Courier");

        /**
         * 字体大小 40px.
         */
        registration.setInitParameter(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "31");

        /**
         * 字体颜色，合法值： r,g,b 或者 white,black,blue.
         */
        registration.setInitParameter(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "051,153,255");

        /**
         * 文字间隔 2
         */
        registration.setInitParameter(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE, "2");

        /**
         * 干扰实现类
         * com.google.code.kaptcha.impl.NoNoise
         * com.google.code.kaptcha.impl.DefaultNoise
         */
        registration.setInitParameter(Constants.KAPTCHA_NOISE_IMPL, DefaultNoise.class.getName());

        /**
         * 干扰颜色，合法值： r,g,b 或者 white,black,blue.
         */
        registration.setInitParameter(Constants.KAPTCHA_NOISE_COLOR, "102,102,102");

        /**
         * 图片样式：
         *      水纹   com.google.code.kaptcha.impl.WaterRipple
         *      鱼眼   com.google.code.kaptcha.impl.FishEyeGimpy
         *      阴影   com.google.code.kaptcha.impl.ShadowGimpy
         */
        registration.setInitParameter(Constants.KAPTCHA_OBSCURIFICATOR_IMPL, WaterRipple.class.getName());

        /**
         * 背景实现类
         *  kaptcha.background.impl
         */
        registration.setInitParameter(Constants.KAPTCHA_BACKGROUND_IMPL, DefaultBackground.class.getName());

        /**
         * 背景颜色渐变，开始颜色
         */
        registration.setInitParameter(Constants.KAPTCHA_BACKGROUND_CLR_FROM, "255,255,255");

        /**
         * 背景颜色渐变，结束颜色
         */
        registration.setInitParameter(Constants.KAPTCHA_BACKGROUND_CLR_TO, "255,255,255");

        /**
         * 文字渲染器
         */
        registration.setInitParameter(Constants.KAPTCHA_WORDRENDERER_IMPL, DefaultWordRenderer.class.getName());

        /**
         * session中存放验证码的key键
         */
        registration.setInitParameter(Constants.KAPTCHA_SESSION_CONFIG_KEY, Constants.KAPTCHA_SESSION_KEY);

        /**
         * The date the kaptcha is generated is put into the
         * HttpSession. This is the key value for that item in the
         * session.
         */
        registration.setInitParameter(Constants.KAPTCHA_SESSION_CONFIG_DATE, Constants.KAPTCHA_SESSION_DATE);

        registration.setLoadOnStartup(1);
        registration.addMapping("/captcha");
        registration.setAsyncSupported(true);
    }

}
