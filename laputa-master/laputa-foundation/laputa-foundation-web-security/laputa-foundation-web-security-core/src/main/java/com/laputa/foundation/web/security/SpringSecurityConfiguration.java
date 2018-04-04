package com.laputa.foundation.web.security;

import com.laputa.foundation.web.constant.LaputaWebApplicationInitializerConstant;
import com.laputa.foundation.web.security.api.LaputaAccessDecisionVoter;
import com.laputa.foundation.web.security.api.LaputaAuthenticationProvider;
import com.laputa.foundation.web.security.api.LaputaConfigAttribute;
import com.laputa.foundation.web.security.api.SecurityMetadataConfig;
import com.laputa.foundation.web.security.audit.LaputaSpringSecurityAuditorAware;
import com.laputa.foundation.web.security.core.LaputaUnLoginAuthenticationProvider;
import com.laputa.foundation.web.security.filter.LaputaCaptchaAuthenticationFilter;
import com.laputa.foundation.web.security.filter.handler.AjaxAuthenticationFailureHandler;
import com.laputa.foundation.web.security.filter.handler.AjaxAuthenticationSuccessHandler;
import com.laputa.foundation.web.security.filter.handler.AjaxLogoutSuccessHandler;
import com.laputa.foundation.web.security.filter.handler.Http403ForbiddenEntryPoint;
import com.laputa.foundation.web.security.service.LaputaBCryptPasswordEncoder;
import com.laputa.foundation.web.security.service.LaputaSecurityMetadataSourceService;
import com.laputa.foundation.web.security.service.LaputaSessionSecurityContextRepository;
import com.laputa.foundation.web.security.service.UserDetailsService;
import com.laputa.foundation.web.security.util.IocUtilLaputaAccessDecisionVoter;
import com.laputa.foundation.web.security.voter.LaputaAccessDecisionManager;
import com.laputa.foundation.web.security.voter.LaputaAuthenticatedVoter;
import com.laputa.foundation.web.security.voter.LaputaSysFileAccessDecisionVoter;
import com.laputa.foundation.web.security.voter.LaputaSysOperationAccessDecisionVoter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AnonymousConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by JiangDongPing on 2016/12/16.
 */
@EnableWebSecurity
@Configuration
@ComponentScan(basePackages = {"com.laputa.foundation.web.security.controller", "com.laputa.foundation.web.security.service"})
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final Logger log = LoggerFactory.getLogger(SpringSecurityConfiguration.class);

    @Inject
    private UserDetailsService userDetailsService;

    @Inject
    private LaputaSessionSecurityContextRepository laputaSessionSecurityContextRepository;

    @Inject
    private LaputaSecurityMetadataSourceService laputaSecurityMetadataSourceService;

    @Inject
    private LaputaAccessDecisionManager laputaAccessDecisionManager;

    @Resource
    private AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;

    @Resource
    private AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;

    @Resource
    private AccessDeniedHandler accessDeniedHandler;

    @Resource
    private Http403ForbiddenEntryPoint http403ForbiddenEntryPoint;

    @Autowired
    private LaputaBCryptPasswordEncoder passwordEncoder;

    @Inject
    private AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;

    @Autowired(required = false)
    private List<LaputaAuthenticationProvider> laputaAuthenticationProviderList;


//    public AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        auth.authenticationProvider(LaputaUnLoginAuthenticationProvider.getInstance());
        if (laputaAuthenticationProviderList != null && laputaAuthenticationProviderList.size() > 0) {
            log.info("加载拓展{}个鉴权服务", laputaAuthenticationProviderList.size());
            for (LaputaAuthenticationProvider laputaAuthenticationProvider : laputaAuthenticationProviderList) {
                log.info("加载拓展鉴权服务 {}", laputaAuthenticationProvider.getName());
                auth.authenticationProvider(laputaAuthenticationProvider);
            }
        }
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //web.ignoring().antMatchers("/favicon.ico");
        //web.ignoring().antMatchers("/loginform");
        //web.ignoring().antMatchers("/captcha");
        //web.ignoring().antMatchers("/static/**");
        //web.ignoring().antMatchers("/security/show/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.logout().logoutUrl("/logout").logoutSuccessHandler(ajaxLogoutSuccessHandler);

        http.securityContext().securityContextRepository(laputaSessionSecurityContextRepository);
        //http.sessionManagement().disable();
        http.servletApi().disable();
        http.requestCache().disable();

        /**
         *  {@link org.springframework.security.config.annotation.web.builders.FilterComparator#FilterComparator()}
         */
        AuthenticationManager authenticationManager = super.authenticationManager();

        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor.setAccessDecisionManager(laputaAccessDecisionManager);
        filterSecurityInterceptor.setAuthenticationManager(authenticationManager);
        filterSecurityInterceptor.setSecurityMetadataSource(laputaSecurityMetadataSourceService);
        //filterSecurityInterceptor.setAlwaysReauthenticate(true);
        http.addFilterBefore(filterSecurityInterceptor, FilterSecurityInterceptor.class);


        LaputaCaptchaAuthenticationFilter laputaCaptchaAuthenticationFilter = new LaputaCaptchaAuthenticationFilter();
        laputaCaptchaAuthenticationFilter.setAuthenticationManager(authenticationManager);
        laputaCaptchaAuthenticationFilter.setFilterProcessesUrl("/login");
        laputaCaptchaAuthenticationFilter.setAuthenticationSuccessHandler(ajaxAuthenticationSuccessHandler);
        laputaCaptchaAuthenticationFilter.setAuthenticationFailureHandler(ajaxAuthenticationFailureHandler);
        http.addFilterBefore(laputaCaptchaAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.removeConfigurer(AnonymousConfigurer.class);
        http.removeConfigurer(FormLoginConfigurer.class);
        http.getConfigurer(ExceptionHandlingConfigurer.class)
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(http403ForbiddenEntryPoint);
    }

    @Bean
    public AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler() {
        return new AjaxAuthenticationSuccessHandler();
    }

    @Bean
    public AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler() {
        return new AjaxAuthenticationFailureHandler();
    }

    @Bean
    public AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler() {
        return new AjaxLogoutSuccessHandler();
    }

    @Bean
    public LaputaSpringSecurityAuditorAware laputaSpringSecurityAuditorAware() {
        return new LaputaSpringSecurityAuditorAware();
    }


    /**
     * AccessDecisionManager : 一票否决策略
     *
     * @param laputaAuthenticatedVoter              支持 已登录, 匿名 的功能 投票器
     * @param laputaSysOperationAccessDecisionVoter 系统操作 投票器
     * @return
     */
    @Bean
    public LaputaAccessDecisionManager laputaAccessDecisionManager(
            LaputaAuthenticatedVoter laputaAuthenticatedVoter,
            LaputaSysOperationAccessDecisionVoter laputaSysOperationAccessDecisionVoter,
            LaputaSysFileAccessDecisionVoter laputaSysFileAccessDecisionVoter,
            List<LaputaAccessDecisionVoter> laputaAccessDecisionVoterList
    ) {
        List<AccessDecisionVoter<?>> accessDecisionVoterList = new ArrayList<>();

        log.info("加载内建投票器", laputaAccessDecisionVoterList.size());
        accessDecisionVoterList.add(laputaAuthenticatedVoter);
        accessDecisionVoterList.add(laputaSysOperationAccessDecisionVoter);
        accessDecisionVoterList.add(laputaSysFileAccessDecisionVoter);

        if (laputaAccessDecisionVoterList != null && laputaAccessDecisionVoterList.size() > 1) {
            log.info("加载 {} 个拓展投票器", laputaAccessDecisionVoterList.size() - 1);
            for (LaputaAccessDecisionVoter voter : laputaAccessDecisionVoterList) {
                if (!(voter instanceof IocUtilLaputaAccessDecisionVoter)) {
                    log.info("加载拓展投票器 {}", voter.getName());
                    accessDecisionVoterList.add(voter);
                }
            }
        }
        return new LaputaAccessDecisionManager(accessDecisionVoterList);
    }

    @Bean
    public IocUtilLaputaAccessDecisionVoter iocUtilLaputaAccessDecisionVoter() {
        return new IocUtilLaputaAccessDecisionVoter();
    }


    /**
     * 系统操作 投票器
     *
     * @return
     */
    @Bean
    public LaputaSysOperationAccessDecisionVoter laputaSysOperationAccessDecisionVoter() {
        LaputaSysOperationAccessDecisionVoter laputaSysOperationAccessDecisionVoter =
                new LaputaSysOperationAccessDecisionVoter();
        return laputaSysOperationAccessDecisionVoter;
    }


    /**
     * 文件资源投票器
     *
     * @return
     */
    @Bean
    public LaputaSysFileAccessDecisionVoter laputaSysFileAccessDecisionVoter() {
        LaputaSysFileAccessDecisionVoter laputaSysFileAccessDecisionVoter = new LaputaSysFileAccessDecisionVoter();
        return laputaSysFileAccessDecisionVoter;
    }

    /**
     * 登录, 匿名 的功能 投票器
     *
     * @return
     */
    @Bean
    public LaputaAuthenticatedVoter laputaAuthenticatedVoter() {
        LaputaAuthenticatedVoter laputaAuthenticatedVoter = new LaputaAuthenticatedVoter();
        return laputaAuthenticatedVoter;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new com.laputa.foundation.web.security.filter.handler.AccessDeniedHandler();
    }

    @Bean
    public Http403ForbiddenEntryPoint http403ForbiddenEntryPoint() {
        return new Http403ForbiddenEntryPoint();
    }

    @Bean
    public SecurityMetadataConfig defaultSecurityMetadataConfig() {
        return new SecurityMetadataConfig() {
            @Override
            public String name() {
                return LaputaWebApplicationInitializerConstant.RBAC.getCname();
            }

            @Override
            public List<String> configAntPathAnyoneAccess() {
                return Arrays.asList("/favicon.ico", "/loginform", "/captcha", "/security/show/**", "/static/**");
            }

            @Override
            public List<? extends LaputaConfigAttribute> configAntConfigAttribute() {
                return null;
            }
        };
    }

}
