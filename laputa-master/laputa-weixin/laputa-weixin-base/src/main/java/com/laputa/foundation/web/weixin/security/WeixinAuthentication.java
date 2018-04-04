package com.laputa.foundation.web.weixin.security;

import com.laputa.foundation.web.weixin.entity.WeixinUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;

/**
 * Created by jiangdongping on 2017/9/14 0014.
 */
public class WeixinAuthentication implements Authentication {


    private WeixinUser weixinUser;

    /**
     * 用户在 sns进行过 userinfo级别认证
     */
    private Boolean snsUserinfo = Boolean.FALSE;

    /**
     * 用户在 sns进行过 userinfo级别认证时间
     */
    private Date snsUserinfoTime = null;

    public WeixinUser getWeixinUser() {
        return weixinUser;
    }

    public void setWeixinUser(WeixinUser weixinUser) {
        this.weixinUser = weixinUser;
    }

    public Boolean getSnsUserinfo() {
        return snsUserinfo;
    }

    public void setSnsUserinfo(Boolean snsUserinfo) {
        this.snsUserinfo = snsUserinfo;
    }

    public Date getSnsUserinfoTime() {
        return snsUserinfoTime;
    }

    public void setSnsUserinfoTime(Date snsUserinfoTime) {
        this.snsUserinfoTime = snsUserinfoTime;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
