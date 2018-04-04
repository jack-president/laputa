package com.laputa.foundation.web.weixin.entity;

import com.laputa.foundation.persistence.code.CodeAbleIdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by jiangdongping on 2017/9/13 0013.
 */

/**
 * 微信公众号基础配置
 * <p>
 * Created by JiangDongPing on 2016/1/13.
 */
@Entity
@Table(name = "weixin_base_config")
public class WeixinBaseConfig extends CodeAbleIdEntity {

    private String appid;

    private String appsecret;

    private String token;

    @Column(name = "appid", nullable = false, unique = true)
    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    @Column(name = "appsecret", nullable = false)
    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    @Column(name = "token", nullable = false,unique = true)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
