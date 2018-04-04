package com.laputa.foundation.web.rbac.model;

import java.io.Serializable;

/**
 * <p>
 * 用户 Model<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-02-03T16:53:42+08:00 .
 */
public class SysUserModel implements Serializable {

    /**
     * 主键
     */
    private java.lang.Long id;


    /**
     * 名称
     */
    private java.lang.String cname;


    /**
     * 用户名
     */
    private java.lang.String username;


    /**
     * 密码
     */
    private java.lang.String password;


    /**
     * 锁定
     */
    private java.lang.Boolean locked;


    /**
     * 描述
     */
    private java.lang.String descript;


    /**
     * 所属角色
     */
    private java.util.List<java.lang.Long> belongtoSysRoleCollection;


    /**
     * 所属用户组
     */
    private java.util.List<java.lang.Long> belongtoSysUserGroupCollection;


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


    public java.lang.Long getId() {
        return this.id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }

    public java.lang.String getCname() {
        return this.cname;
    }

    public void setCname(java.lang.String cname) {
        this.cname = cname;
    }

    public java.lang.String getUsername() {
        return this.username;
    }

    public void setUsername(java.lang.String username) {
        this.username = username;
    }

    public java.lang.String getPassword() {
        return this.password;
    }

    public void setPassword(java.lang.String password) {
        this.password = password;
    }

    public java.lang.Boolean getLocked() {
        return this.locked;
    }

    public void setLocked(java.lang.Boolean locked) {
        this.locked = locked;
    }

    public java.lang.String getDescript() {
        return this.descript;
    }

    public void setDescript(java.lang.String descript) {
        this.descript = descript;
    }

    public java.util.List<java.lang.Long> getBelongtoSysRoleCollection() {
        return this.belongtoSysRoleCollection;
    }

    public void setBelongtoSysRoleCollection(java.util.List<java.lang.Long> belongtoSysRoleCollection) {
        this.belongtoSysRoleCollection = belongtoSysRoleCollection;
    }

    public java.util.List<java.lang.Long> getBelongtoSysUserGroupCollection() {
        return this.belongtoSysUserGroupCollection;
    }

    public void setBelongtoSysUserGroupCollection(java.util.List<java.lang.Long> belongtoSysUserGroupCollection) {
        this.belongtoSysUserGroupCollection = belongtoSysUserGroupCollection;
    }

    public java.lang.String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(java.lang.String createdBy) {
        this.createdBy = createdBy;
    }

    public java.util.Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(java.util.Date createdDate) {
        this.createdDate = createdDate;
    }

    public java.lang.String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public void setLastModifiedBy(java.lang.String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public java.util.Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(java.util.Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}