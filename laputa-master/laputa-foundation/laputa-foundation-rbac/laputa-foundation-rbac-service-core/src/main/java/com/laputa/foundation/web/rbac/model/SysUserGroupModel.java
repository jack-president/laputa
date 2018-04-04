package com.laputa.foundation.web.rbac.model;

import java.io.Serializable;

/**
 * <p>
 * 用户组 Model<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-08-15T09:50:30+08:00 .
 */
public class SysUserGroupModel implements Serializable {

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
     * 拥有角色
     */
    private java.util.List<java.lang.Long> ownSysRoleCollection;


    /**
     * 部门权限
     */
    private java.util.List<java.lang.Long> canhaveRelationSysPermissionCollection;


    /**
     * 拥有用户
     */
    private java.util.List<java.lang.Long> ownSysUserCollection;


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

    public java.lang.String getCode() {
        return this.code;
    }

    public void setCode(java.lang.String code) {
        this.code = code;
    }

    public java.lang.String getDescript() {
        return this.descript;
    }

    public void setDescript(java.lang.String descript) {
        this.descript = descript;
    }

    public java.util.List<java.lang.Long> getOwnSysRoleCollection() {
        return this.ownSysRoleCollection;
    }

    public void setOwnSysRoleCollection(java.util.List<java.lang.Long> ownSysRoleCollection) {
        this.ownSysRoleCollection = ownSysRoleCollection;
    }

    public java.util.List<java.lang.Long> getCanhaveRelationSysPermissionCollection() {
        return this.canhaveRelationSysPermissionCollection;
    }

    public void setCanhaveRelationSysPermissionCollection(java.util.List<java.lang.Long> canhaveRelationSysPermissionCollection) {
        this.canhaveRelationSysPermissionCollection = canhaveRelationSysPermissionCollection;
    }

    public java.util.List<java.lang.Long> getOwnSysUserCollection() {
        return this.ownSysUserCollection;
    }

    public void setOwnSysUserCollection(java.util.List<java.lang.Long> ownSysUserCollection) {
        this.ownSysUserCollection = ownSysUserCollection;
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