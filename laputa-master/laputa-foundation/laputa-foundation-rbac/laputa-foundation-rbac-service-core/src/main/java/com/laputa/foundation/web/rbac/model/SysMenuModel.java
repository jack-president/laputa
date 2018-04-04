package com.laputa.foundation.web.rbac.model;

import java.io.Serializable;

/**
 * <p>
 * 菜单 Model<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-02-03T16:53:41+08:00 .
 */
public class SysMenuModel implements Serializable {

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
     * 图标
     */
    private java.lang.String iconClass;


    /**
     * 资源地址
     */
    private java.lang.String resources;


    /**
     * 描述
     */
    private java.lang.String descript;


    /**
     * 排序
     */
    private java.lang.Integer orderValue;


    /**
     * 父菜单
     */
    private java.lang.Long parentId;

    /**
     * 子菜单
     */
    private java.util.List<java.lang.Long> children;


    /**
     * 所属权限
     */
    private java.util.List<java.lang.Long> belongtoSysPermissionCollection;


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

    public java.lang.String getIconClass() {
        return this.iconClass;
    }

    public void setIconClass(java.lang.String iconClass) {
        this.iconClass = iconClass;
    }

    public java.lang.String getResources() {
        return this.resources;
    }

    public void setResources(java.lang.String resources) {
        this.resources = resources;
    }

    public java.lang.String getDescript() {
        return this.descript;
    }

    public void setDescript(java.lang.String descript) {
        this.descript = descript;
    }

    public java.lang.Integer getOrderValue() {
        return this.orderValue;
    }

    public void setOrderValue(java.lang.Integer orderValue) {
        this.orderValue = orderValue;
    }

    public java.lang.Long getParentId() {
        return this.parentId;
    }

    public void setParentId(java.lang.Long parentId) {
        this.parentId = parentId;
    }

    public java.util.List<java.lang.Long> getChildren() {
        return this.children;
    }

    public void setChildren(java.util.List<java.lang.Long> children) {
        this.children = children;
    }

    public java.util.List<java.lang.Long> getBelongtoSysPermissionCollection() {
        return this.belongtoSysPermissionCollection;
    }

    public void setBelongtoSysPermissionCollection(java.util.List<java.lang.Long> belongtoSysPermissionCollection) {
        this.belongtoSysPermissionCollection = belongtoSysPermissionCollection;
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