package com.laputa.fates.web.model;

/**
 * <p>
 * Laputa项目 Model<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-04-06T10:15:11+08:00 .
 */
public class LaputaConfigHistoryModel {

    /**
     * 主键
     */
    private java.lang.Long id;


    /**
     * 配置值
     */
    private java.lang.String configValue;


    /**
     * 描述
     */
    private java.lang.String descript;


    /**
     * 配置
     */
    private java.lang.Long parentLaputaConfigId;

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

    public java.lang.String getConfigValue(){
        return this.configValue;
    }

    public void setConfigValue(java.lang.String configValue) {
        this.configValue= configValue;
    }

    public java.lang.String getDescript(){
        return this.descript;
    }

    public void setDescript(java.lang.String descript) {
        this.descript= descript;
    }
    public java.lang.Long getParentLaputaConfigId(){
        return this.parentLaputaConfigId;
    }

    public void setParentLaputaConfigId(java.lang.Long parentLaputaConfigId) {
        this.parentLaputaConfigId= parentLaputaConfigId;
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