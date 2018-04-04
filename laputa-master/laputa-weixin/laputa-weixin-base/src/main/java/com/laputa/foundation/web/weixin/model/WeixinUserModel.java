package com.laputa.foundation.web.weixin.model;

/**
 * <p>
 * 微信用户 Model<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-09-15T17:24:50+08:00 .
 */
public class WeixinUserModel {

    /**
     * 主键
     */
    private java.lang.Long id;


    /**
     * 是否订阅
     */
    private java.lang.Integer subscribe;


    /**
     * openid
     */
    private java.lang.String openid;


    /**
     * 昵称
     */
    private java.lang.String nickname;


    /**
     * 昵称表情
     */
    private java.lang.String nicknameEmoji;


    /**
     * 性别
     */
    private java.lang.Integer sex;


    /**
     * 语言
     */
    private java.lang.String language;


    /**
     * 城市
     */
    private java.lang.String city;


    /**
     * 省份
     */
    private java.lang.String province;


    /**
     * 国家
     */
    private java.lang.String country;


    /**
     * 头像
     */
    private java.lang.String headimgurl;


    /**
     * 订阅时间
     */
    private java.lang.Integer subscribeTime;


    /**
     * 特权
     */
    private java.lang.String privilege;


    /**
     * unionid
     */
    private java.lang.String unionid;


    /**
     * groupid
     */
    private java.lang.Integer groupid;


    /**
     * remark
     */
    private java.lang.String remark;


    /**
     * tagidList
     */
    private java.lang.String tagidList;


    /**
     * 所属微信编码
     */
    private java.lang.String weixinCode;


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

    public java.lang.Integer getSubscribe(){
        return this.subscribe;
    }

    public void setSubscribe(java.lang.Integer subscribe) {
        this.subscribe= subscribe;
    }

    public java.lang.String getOpenid(){
        return this.openid;
    }

    public void setOpenid(java.lang.String openid) {
        this.openid= openid;
    }

    public java.lang.String getNickname(){
        return this.nickname;
    }

    public void setNickname(java.lang.String nickname) {
        this.nickname= nickname;
    }

    public java.lang.String getNicknameEmoji(){
        return this.nicknameEmoji;
    }

    public void setNicknameEmoji(java.lang.String nicknameEmoji) {
        this.nicknameEmoji= nicknameEmoji;
    }

    public java.lang.Integer getSex(){
        return this.sex;
    }

    public void setSex(java.lang.Integer sex) {
        this.sex= sex;
    }

    public java.lang.String getLanguage(){
        return this.language;
    }

    public void setLanguage(java.lang.String language) {
        this.language= language;
    }

    public java.lang.String getCity(){
        return this.city;
    }

    public void setCity(java.lang.String city) {
        this.city= city;
    }

    public java.lang.String getProvince(){
        return this.province;
    }

    public void setProvince(java.lang.String province) {
        this.province= province;
    }

    public java.lang.String getCountry(){
        return this.country;
    }

    public void setCountry(java.lang.String country) {
        this.country= country;
    }

    public java.lang.String getHeadimgurl(){
        return this.headimgurl;
    }

    public void setHeadimgurl(java.lang.String headimgurl) {
        this.headimgurl= headimgurl;
    }

    public java.lang.Integer getSubscribeTime(){
        return this.subscribeTime;
    }

    public void setSubscribeTime(java.lang.Integer subscribeTime) {
        this.subscribeTime= subscribeTime;
    }

    public java.lang.String getPrivilege(){
        return this.privilege;
    }

    public void setPrivilege(java.lang.String privilege) {
        this.privilege= privilege;
    }

    public java.lang.String getUnionid(){
        return this.unionid;
    }

    public void setUnionid(java.lang.String unionid) {
        this.unionid= unionid;
    }

    public java.lang.Integer getGroupid(){
        return this.groupid;
    }

    public void setGroupid(java.lang.Integer groupid) {
        this.groupid= groupid;
    }

    public java.lang.String getRemark(){
        return this.remark;
    }

    public void setRemark(java.lang.String remark) {
        this.remark= remark;
    }

    public java.lang.String getTagidList(){
        return this.tagidList;
    }

    public void setTagidList(java.lang.String tagidList) {
        this.tagidList= tagidList;
    }

    public java.lang.String getWeixinCode(){
        return this.weixinCode;
    }

    public void setWeixinCode(java.lang.String weixinCode) {
        this.weixinCode= weixinCode;
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