package com.laputa.foundation.web.security.filter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laputa.foundation.web.security.filter.handler.AjaxAuthenticationFailureHandler;
import com.laputa.foundation.web.security.service.CaptchaAuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.laputa.foundation.web.security.util.SecuritySessionUtil;

import java.io.IOException;

/**
 * 补充 UsernamePasswordAuthenticationFilter 支持验证码功能
 * Created by JiangDongPing on 2016/12/21.
 */
public class LaputaCaptchaAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final Logger log = LoggerFactory.getLogger(LaputaCaptchaAuthenticationFilter.class);

    public static final String SPRING_SECURITY_FORM_CAPTCHA_KEY = "captcha";

    private String captchaParameter = SPRING_SECURITY_FORM_CAPTCHA_KEY;

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String inputCode = this.obtainCaptcha(request);

        log.debug("尝试验证验证码 {}", inputCode);

        Boolean checkResult = SecuritySessionUtil.checkCaptchaAndClear(request.getSession(), inputCode);

        log.debug("验证结果 {}", checkResult);

        if (!checkResult) {
            throw new CaptchaAuthenticationException();
        }

        Authentication result = super.attemptAuthentication(request, response);
        return result;
    }

    /**
     * 重写 unsuccessfulAuthentication, 防止 loginFail onAuthenticationFailure 操作时 SecurityContextHolder已clear.
     *
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        if (logger.isDebugEnabled()) {
            logger.debug("Authentication request failed: " + failed.toString());
            logger.debug("Updated SecurityContextHolder to contain null Authentication");
            logger.debug("Delegating to authentication failure handler " + getFailureHandler());
        }

        /**
         * 记住我 暂时用不到
         */
        getRememberMeServices().loginFail(request, response);

        /**
         * @see AjaxAuthenticationFailureHandler
         *  登录 失败处理
         */
        getFailureHandler().onAuthenticationFailure(request, response, failed);

        SecurityContextHolder.clearContext();
    }

    protected String obtainCaptcha(HttpServletRequest request) {
        return request.getParameter(this.captchaParameter);
    }
}
