package com.laputa.fates.web.model;

/**
 * <p>
 * Laputa项目 Model<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-04-06T10:15:08+08:00 .
 */
public class LaputaAplicationModel {

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
     * 配置
     */
    private java.util.List<java.lang.Long> configList;


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

    public java.util.List<java.lang.Long> getConfigList(){
        return this.configList;
    }

    public void setConfigList(java.util.List<java.lang.Long> configList) {
        this.configList= configList;
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