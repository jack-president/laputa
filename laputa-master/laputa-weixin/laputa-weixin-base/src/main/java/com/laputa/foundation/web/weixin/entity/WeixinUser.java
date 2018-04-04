package com.laputa.foundation.web.weixin.entity;

import com.laputa.foundation.persistence.audit.AuditingIdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by jiangdongping on 2017/9/15 0015.
 */
@Entity
@Table(name = "weixin_user")
public class WeixinUser extends AuditingIdEntity {
    /**
     * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     */
    private Integer subscribe;

    /**
     * 用户的标识，对当前公众号唯一
     */
    private String openid;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 昵称 表情转义
     */
    private String nicknameEmoji;

    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    private Integer sex;

    /**
     * 语言
     */
    private String language;

    /**
     * 普通用户个人资料填写的城市
     */
    private String city;

    /**
     * 用户个人资料填写的省份
     */
    private String province;

    /**
     * 国家，如中国为CN
     */
    private String country;

    /**
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），
     * 用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     */
    private String headimgurl;

    /**
     * subscribeTime
     */
    private Integer subscribeTime;

    /**
     * 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
     */
    private String privilege;

    /**
     * 多个公众号之间用户帐号互通UnionID机制
     */
    private String unionid;

    /**
     * groupid
     */
    private Integer groupid;

    /**
     * 公众号运营者对粉丝的备注，
     * 公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     */
    private String remark;

    /**
     * 用户被打上的标签ID列表
     */
    private String tagidList;

    /**
     * 所属微信编码
     */
    private String weixinCode;

    @Column(name = "weixin_code")
    public String getWeixinCode() {
        return weixinCode;
    }

    public void setWeixinCode(String weixinCode) {
        this.weixinCode = weixinCode;
    }

    @Column(name = "subscribe")
    public Integer getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    @Column(name = "openid")
    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Column(name = "nickname_emoji")
    public String getNicknameEmoji() {
        return nicknameEmoji;
    }

    public void setNicknameEmoji(String nicknameEmoji) {
        this.nicknameEmoji = nicknameEmoji;
    }

    @Column(name = "sex")
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Column(name = "language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "headimgurl")
    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    @Column(name = "subscribe_time")
    public Integer getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Integer subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    @Column(name = "privilege")
    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    @Column(name = "unionid")
    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Column(name = "groupid")
    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "tagid_list")
    public String getTagidList() {
        return tagidList;
    }

    public void setTagidList(String tagidList) {
        this.tagidList = tagidList;
    }
}
