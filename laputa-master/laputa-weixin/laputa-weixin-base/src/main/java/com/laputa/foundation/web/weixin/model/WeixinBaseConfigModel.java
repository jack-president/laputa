package com.laputa.foundation.web.weixin.model;

/**
 * <p>
 * 微信公众号基础配置 Model<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-09-13T16:56:42+08:00 .
 */
public class WeixinBaseConfigModel {

    /**
     * 主键
     */
    private java.lang.Long id;


    /**
     * 名称
     */
    private java.lang.String cname;


    /**
     * 编码
     */
    private java.lang.String code;


    /**
     * 描述
     */
    private java.lang.String descript;


    /**
     * appid
     */
    private java.lang.String appid;


    /**
     * appsecret
     */
    private java.lang.String appsecret;


    /**
     * token
     */
    private java.lang.String token;


    /**
     * 创建用户
     */
    private java.lang.String createdBy;


    /**
     * 创建时间
     */
    private java.util.Date createdDate;


    /**
     * 最后修改用户
     */
    private java.lang.String lastModifiedBy;


    /**
     * 最后修改时间
     */
    private java.util.Date lastModifiedDate;



    public java.lang.Long getId(){
        return this.id;
    }

    public void setId(java.lang.Long id) {
        this.id= id;
    }

    public java.lang.String getCname(){
        return this.cname;
    }

    public void setCname(java.lang.String cname) {
        this.cname= cname;
    }

    public java.lang.String getCode(){
        return this.code;
    }

    public void setCode(java.lang.String code) {
        this.code= code;
    }

    public java.lang.String getDescript(){
        return this.descript;
    }

    public void setDescript(java.lang.String descript) {
        this.descript= descript;
    }

    public java.lang.String getAppid(){
        return this.appid;
    }

    public void setAppid(java.lang.String appid) {
        this.appid= appid;
    }

    public java.lang.String getAppsecret(){
        return this.appsecret;
    }

    public void setAppsecret(java.lang.String appsecret) {
        this.appsecret= appsecret;
    }

    public java.lang.String getToken(){
        return this.token;
    }

    public void setToken(java.lang.String token) {
        this.token= token;
    }

    public java.lang.String getCreatedBy(){
        return this.createdBy;
    }

    public void setCreatedBy(java.lang.String createdBy) {
        this.createdBy= createdBy;
    }

    public java.util.Date getCreatedDate(){
        return this.createdDate;
    }

    public void setCreatedDate(java.util.Date createdDate) {
        this.createdDate= createdDate;
    }

    public java.lang.String getLastModifiedBy(){
        return this.lastModifiedBy;
    }

    public void setLastModifiedBy(java.lang.String lastModifiedBy) {
        this.lastModifiedBy= lastModifiedBy;
    }

    public java.util.Date getLastModifiedDate(){
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(java.util.Date lastModifiedDate) {
        this.lastModifiedDate= lastModifiedDate;
    }
}