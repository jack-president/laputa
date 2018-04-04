package com.laputa.foundation.web.generate.model;

/**
 * <p>
 * Hi时间 Model<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2016-12-07T16:50:06+08:00 .
 */
public class SysHelloDateModel {

    /**
     * 主键
     */
    private java.lang.Long id;


    /**
     * 名字
     */
    private java.lang.String cname;


    /**
     * 闹钟
     */
    private java.util.Date wakeup;


    /**
     * 生日
     */
    private java.util.Date birthDate;


    /**
     * 激活时间
     */
    private java.util.Date acTime;


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

    public java.util.Date getWakeup(){
        return this.wakeup;
    }

    public void setWakeup(java.util.Date wakeup) {
        this.wakeup= wakeup;
    }

    public java.util.Date getBirthDate(){
        return this.birthDate;
    }

    public void setBirthDate(java.util.Date birthDate) {
        this.birthDate= birthDate;
    }

    public java.util.Date getAcTime(){
        return this.acTime;
    }

    public void setAcTime(java.util.Date acTime) {
        this.acTime= acTime;
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