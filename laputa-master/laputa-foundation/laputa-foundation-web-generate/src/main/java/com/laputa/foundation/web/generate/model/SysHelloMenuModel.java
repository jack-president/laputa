package com.laputa.foundation.web.generate.model;

/**
 * <p>
 * Hi菜单 Model<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-01-10T18:13:30+08:00 .
 */
public class SysHelloMenuModel {

    /**
     * 主键
     */
    private java.lang.Long id;


    /**
     * 名字
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
    private java.util.List<java.lang.Long> belongSysHelloPermissionCollection;



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

    public java.lang.String getIconClass(){
        return this.iconClass;
    }

    public void setIconClass(java.lang.String iconClass) {
        this.iconClass= iconClass;
    }
    public java.lang.Long getParentId(){
        return this.parentId;
    }

    public void setParentId(java.lang.Long parentId) {
        this.parentId= parentId;
    }

    public java.util.List<java.lang.Long> getChildren(){
        return this.children;
    }

    public void setChildren(java.util.List<java.lang.Long> children) {
        this.children= children;
    }

    public java.util.List<java.lang.Long> getBelongSysHelloPermissionCollection(){
        return this.belongSysHelloPermissionCollection;
    }

    public void setBelongSysHelloPermissionCollection(java.util.List<java.lang.Long> belongSysHelloPermissionCollection) {
        this.belongSysHelloPermissionCollection= belongSysHelloPermissionCollection;
    }
}