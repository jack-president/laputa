package com.laputa.foundation.web.security.controller;

import com.laputa.foundation.web.rbac.model.SysMenuModel;
import com.laputa.foundation.web.security.core.LaputaUserDetails;
import com.laputa.foundation.web.security.exception.LaputaWebOperationSecurityException;
import com.laputa.foundation.web.security.util.HttpServletRequestDataWrap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.laputa.foundation.web.rbac.converter.SysMenuBeanCopier;
//import com.laputa.foundation.web.rbac.model.SysMenuModel;

/**
 * Created by JiangDongPing on 2016/11/9.
 */
@Controller
@RequestMapping("/")
public class SecurityController {

    @Value("${default.rbac.title:@中台}")
    private String title;

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("title", title);
        return "index";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @ResponseBody
    @RequestMapping("/menu")
    public List<SysMenuModel> menu() {
        LaputaUserDetails laputaUserDetails =
                (LaputaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<SysMenuModel> sysMenuModelList = laputaUserDetails.getSysMenuList();
        return sysMenuModelList;
    }

    @RequestMapping(path = "/loginform", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "/security/show/loginsuccess")
    public void loginsuccess() {
    }


    @ResponseBody
    @RequestMapping(value = "/security/show/accessdenied")
    public Map<String, Object> accessDenied(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap();
        AccessDeniedException accessDeniedException =
                HttpServletRequestDataWrap.getWrapAttr(request, AccessDeniedException.class);
        if (accessDeniedException != null) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            result.put("msg", accessDeniedException.getMessage());
        }

        InsufficientAuthenticationException insufficientAuthenticationException =
                HttpServletRequestDataWrap.getWrapAttr(request, InsufficientAuthenticationException.class);
        if (insufficientAuthenticationException != null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            result.put("msg", insufficientAuthenticationException.getMessage());
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/security/show/loginfail")
    public void loginfail() {
        throw LaputaWebOperationSecurityException.ExceptionEnum.LOGIN_FAIL.generateException();
    }

    @ResponseBody
    @RequestMapping(value = "/security/show/usernamenotfound")
    public void usernamenotfound() {
        throw LaputaWebOperationSecurityException.ExceptionEnum.USERNAMENOTFOUND.generateException();
    }

    @ResponseBody
    @RequestMapping(value = "/security/show/logoutsuccess")
    public void logoutsuccess() {
        //throw LaputaWebOperationSecurityException.ExceptionEnum.CAPTCHA_FAIL.generateException();
    }

    @ResponseBody
    @RequestMapping(value = "/security/show/captchafail")
    public void captchafail() {
        throw LaputaWebOperationSecurityException.ExceptionEnum.CAPTCHA_FAIL.generateException();
    }

}
