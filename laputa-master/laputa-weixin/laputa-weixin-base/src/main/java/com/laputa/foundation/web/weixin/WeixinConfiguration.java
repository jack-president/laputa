package com.laputa.foundation.web.weixin;

import com.laputa.foundation.web.constant.LaputaWebApplicationInitializerConstant;
import com.laputa.foundation.web.security.api.LaputaConfigAttribute;
import com.laputa.foundation.web.security.api.SecurityMetadataConfig;
import com.laputa.foundation.web.weixin.security.LaputaWeixinAccessDecisionVoter;
import com.laputa.foundation.web.weixin.security.LaputaWeixinAuthenticationProvider;
import com.laputa.foundation.web.weixin.security.WeixinConfigAttribute;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * Created by JiangDongPing on 2016/12/16.
 */
@Configuration
@ComponentScan(basePackages = {"com.laputa.foundation.web.weixin.controller", "com.laputa.foundation.web.weixin.service"})
public class WeixinConfiguration {


    @Bean
    public LaputaWeixinAccessDecisionVoter laputaWeixinAccessDecisionVoter() {
        return new LaputaWeixinAccessDecisionVoter();
    }

    @Bean
    public LaputaWeixinAuthenticationProvider laputaWeixinAuthenticationProvider() {
        return new LaputaWeixinAuthenticationProvider();
    }

    @Bean
    public SecurityMetadataConfig weixinSecurityMetadataConfig() {
        return new SecurityMetadataConfig() {
            @Override
            public String name() {
                return LaputaWebApplicationInitializerConstant.WEIXINBASE.getCname();
            }

            @Override
            public List<String> configAntPathAnyoneAccess() {
                return Arrays.asList("/weixinapi/**");
            }

            @Override
            public List<? extends LaputaConfigAttribute> configAntConfigAttribute() {
                WeixinConfigAttribute test = new WeixinConfigAttribute("/demo/**", "WX_DEMO");
                return Arrays.asList(test);
            }
        };
    }

}
