package com.laputa.foundation.web.rbac.model;

import java.io.Serializable;

/**
 * <p>
 * 权限 Model<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-02-03T16:53:42+08:00 .
 */
public class SysPermissionModel implements Serializable {

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
     * 拥有元素
     */
    private java.util.List<java.lang.Long> ownSysElementCollection;


    /**
     * 拥有文件
     */
    private java.util.List<java.lang.Long> ownSysFileCollection;


    /**
     * 拥有菜单
     */
    private java.util.List<java.lang.Long> ownSysMenuCollection;


    /**
     * 拥有操作
     */
    private java.util.List<java.lang.Long> ownSysOperationCollection;


    /**
     * 所属角色
     */
    private java.util.List<java.lang.Long> belongtoSysRoleCollection;


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

    public java.util.List<java.lang.Long> getOwnSysElementCollection() {
        return this.ownSysElementCollection;
    }

    public void setOwnSysElementCollection(java.util.List<java.lang.Long> ownSysElementCollection) {
        this.ownSysElementCollection = ownSysElementCollection;
    }

    public java.util.List<java.lang.Long> getOwnSysFileCollection() {
        return this.ownSysFileCollection;
    }

    public void setOwnSysFileCollection(java.util.List<java.lang.Long> ownSysFileCollection) {
        this.ownSysFileCollection = ownSysFileCollection;
    }

    public java.util.List<java.lang.Long> getOwnSysMenuCollection() {
        return this.ownSysMenuCollection;
    }

    public void setOwnSysMenuCollection(java.util.List<java.lang.Long> ownSysMenuCollection) {
        this.ownSysMenuCollection = ownSysMenuCollection;
    }

    public java.util.List<java.lang.Long> getOwnSysOperationCollection() {
        return this.ownSysOperationCollection;
    }

    public void setOwnSysOperationCollection(java.util.List<java.lang.Long> ownSysOperationCollection) {
        this.ownSysOperationCollection = ownSysOperationCollection;
    }

    public java.util.List<java.lang.Long> getBelongtoSysRoleCollection() {
        return this.belongtoSysRoleCollection;
    }

    public void setBelongtoSysRoleCollection(java.util.List<java.lang.Long> belongtoSysRoleCollection) {
        this.belongtoSysRoleCollection = belongtoSysRoleCollection;
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