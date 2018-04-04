package com.laputa.foundation.web.weixin.controller;

import com.alibaba.fastjson.JSON;
import com.laputa.foundation.web.security.SecurityConfiguration;
import com.laputa.foundation.web.security.core.LaputaSecurityContext;
import com.laputa.foundation.web.weixin.entity.WeixinBaseConfig;
import com.laputa.foundation.web.weixin.entity.WeixinUser;
import com.laputa.foundation.web.weixin.security.WeixinAuthentication;
import com.laputa.foundation.web.weixin.service.WeixinBaseConfigService;
import com.laputa.foundation.web.weixin.service.WeixinMainService;
import com.laputa.foundation.web.weixin.service.WeixinUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import weixin.popular.api.SnsAPI;
import weixin.popular.bean.sns.SnsToken;
import weixin.popular.bean.user.User;
import weixin.popular.util.JsonUtil;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Date;

/**
 * 微信token认证相关接口
 * Created by jiangdongping on 2017/9/13 0013.
 */
@Controller
@RequestMapping("/weixinapi")
public class WeixinApiController {

    private final Logger log = LoggerFactory.getLogger(WeixinApiController.class);

    @Resource
    private WeixinBaseConfigService weixinBaseConfigService;

    @Resource
    private WeixinMainService weixinMainService;

    @Resource
    private WeixinUserService weixinUserService;

    @Resource
    private SecurityConfiguration securityConfiguration;


    @RequestMapping(value = "/tokencheck")
    public void tokencheck(HttpServletRequest request, HttpServletResponse response) {
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String echostr = request.getParameter("echostr");

            log.info("微信验证开始");

            WeixinBaseConfig weixinBaseConfig = weixinMainService.signatureCheck(signature, timestamp, nonce);
            if (weixinBaseConfig == null) {
                log.warn("微信签名匹配失败");
                return;
            }

            //首次请求申请验证,返回echostr
            if (echostr != null) {
                log.info("首次请求申请验证,返回echostr {}", echostr);
                outputStreamWrite(outputStream, echostr);
                return;
            }
        } catch (Exception e) {
            log.error("微信认证异常 {} {}", e.getMessage(), e);
        }
    }


    @RequestMapping(value = "/snsapibase/{weixincode}")
    public void snsapibase(@PathVariable("weixincode") String weixincode,
                           @RequestParam(name = "state") String state,
                           @RequestParam(name = "code") String code,
                           HttpServletRequest request, HttpServletResponse response) {

        WeixinAuthentication authentication = getWeixinAuthentication();
        if (authentication == null) {
            WeixinBaseConfig weixinBaseConfig = weixinMainService.getByCode(weixincode);
            if (weixinBaseConfig == null) {
                log.info("未配置微信代码 {} {}", weixincode, state);
                return;
            }
            SnsToken snsToken = SnsAPI.oauth2AccessToken(weixinBaseConfig.getAppid(), weixinBaseConfig.getAppsecret(), code);

            WeixinUser weixinUser = weixinMainService.queryWeixinUser(weixincode, snsToken.getUnionid());
            if (weixinUser == null) {
                log.info("{} - {}初次访问 {}, 自动注册", snsToken.getOpenid(), snsToken.getUnionid(), weixincode);
                weixinUser = new WeixinUser();
                weixinUser.setWeixinCode(weixincode);
                weixinUser.setOpenid(snsToken.getOpenid());
                weixinUser.setUnionid(snsToken.getUnionid());
                weixinUser = weixinUserService.create(weixinUser);
            }

            authentication = new WeixinAuthentication();
            authentication.setWeixinUser(weixinUser);
            authentication.setSnsUserinfo(Boolean.FALSE);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        try {
            request.getRequestDispatcher(state).forward(request, response);
        } catch (ServletException e) {
            log.error("ServletException {} {}", e.getMessage(), e);
        } catch (IOException e) {
            log.error("IOException {} {}", e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/snsapiuserinfo/{weixincode}")
    public void snsapiuserinfo(@PathVariable("weixincode") String weixincode,
                               @RequestParam(name = "state") String state,
                               @RequestParam(name = "code") String code,
                               HttpServletRequest request, HttpServletResponse response) {
        WeixinBaseConfig weixinBaseConfig = weixinMainService.getByCode(weixincode);
        if (weixinBaseConfig == null) {
            log.info("未配置微信代码 {} {}", weixincode, state);
            return;
        }
        SnsToken snsToken = SnsAPI.oauth2AccessToken(weixinBaseConfig.getAppid(), weixinBaseConfig.getAppsecret(), code);
        User user = SnsAPI.userinfo(snsToken.getAccess_token(), snsToken.getOpenid(), "zh_CN", 3);

        WeixinUser weixinUser = weixinMainService.queryWeixinUser(weixincode, snsToken.getUnionid());
        if (weixinUser == null) {
            log.info("{} 初次登录 {}, 自动注册用户信息", user.getNickname(), weixincode);
            weixinUser = new WeixinUser();
            weixinUser.setWeixinCode(weixincode);
            refreshToWeixinUser(weixinUser, user);
            weixinUser = weixinUserService.create(weixinUser);
        } else {
            log.info("{} 非初次登录 {}, 自动刷新用户信息", user.getNickname(), weixincode);
            refreshToWeixinUser(weixinUser, user);
            weixinUser = weixinUserService.update(weixinUser);
        }

        log.debug("刷新微信权限认证对象");
        WeixinAuthentication weixinAuthentication = getWeixinAuthentication();
        if (weixinAuthentication == null) {
            weixinAuthentication = new WeixinAuthentication();
            SecurityContextHolder.getContext().setAuthentication(weixinAuthentication);
        }
        weixinAuthentication.setWeixinUser(weixinUser);
        weixinAuthentication.setSnsUserinfo(Boolean.TRUE);
        weixinAuthentication.setSnsUserinfoTime(new Date());

        try {
            request.getRequestDispatcher(state).forward(request, response);
        } catch (ServletException e) {
            log.error("ServletException {} {}", e.getMessage(), e);
        } catch (IOException e) {
            log.error("IOException {} {}", e.getMessage(), e);
        }
    }

    private void refreshToWeixinUser(WeixinUser weixinUser, User user) {
        weixinUser.setSubscribe(user.getSubscribe());
        weixinUser.setOpenid(user.getOpenid());
        weixinUser.setNickname(user.getNickname());
        weixinUser.setNicknameEmoji(user.getNickname_emoji());
        weixinUser.setSex(user.getSex());
        weixinUser.setLanguage(user.getLanguage());
        weixinUser.setCity(user.getCity());
        weixinUser.setProvince(user.getProvince());
        weixinUser.setCountry(user.getCountry());
        weixinUser.setHeadimgurl(user.getHeadimgurl());
        weixinUser.setSubscribeTime(user.getSubscribe_time());
        weixinUser.setPrivilege(JSON.toJSONString(user.getPrivilege()));
        weixinUser.setUnionid(user.getUnionid());
        weixinUser.setGroupid(user.getGroupid());
        weixinUser.setRemark(user.getRemark());
        weixinUser.setTagidList(JSON.toJSONString(user.getTagid_list()));
    }

    private WeixinAuthentication getWeixinAuthentication() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext == null) {
            return null;
        }
        if (!(securityContext instanceof LaputaSecurityContext)) {
            return null;
        }
        Authentication authentication = securityContext.getAuthentication();
        if (authentication == null) {
            return null;
        }
        if (authentication instanceof WeixinAuthentication) {
            return (WeixinAuthentication) authentication;
        } else {
            return null;
        }
    }

    private void addCookie(HttpServletResponse response, String key, String value) {
        Cookie cook = new Cookie(key, value);
        cook.setPath("/");
        cook.setMaxAge(99999);
        response.addCookie(cook);
    }


    /**
     * 数据流输出
     *
     * @param outputStream
     * @param text
     * @return
     */
    private boolean outputStreamWrite(OutputStream outputStream, String text) {
        try {
            outputStream.write(text.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
