package com.laputa.foundation.web.rbac;

import com.laputa.foundation.web.rbac.security.LaputaBCryptPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by JiangDongPing on 2016/12/21.
 */
@Configuration
@ComponentScan(basePackages = {"com.laputa.foundation.web.rbac.controller", "com.laputa.foundation.web.rbac.service"})
public class RbacConfiguration {
    @Bean
    public LaputaBCryptPasswordEncoder passwordEncoder() {
        LaputaBCryptPasswordEncoder bCryptPasswordEncoder = new LaputaBCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

}
