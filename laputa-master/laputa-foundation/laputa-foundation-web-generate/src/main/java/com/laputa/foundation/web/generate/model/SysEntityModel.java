package com.laputa.foundation.web.generate.model;

/**
 * <p>
 * 实体表55SSS是 Model<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2016-11-17T16:41:09+08:00 .
 */
public class SysEntityModel {

    /**
     * 主键
     */
    private Long id;


    /**
     * 名字
     */
    private String cname;



    /**
     * 类名
     */
    private String clazzName;


    /**
     * 字段集合
     */
    private java.util.List<Long> sysFieldCollection;



    public Long getId(){
        return this.id;
    }

    public void setId(Long id) {
        this.id= id;
    }

    public String getCname(){
        return this.cname;
    }

    public void setCname(String cname) {
        this.cname= cname;
    }

    public String getClazzName(){
        return this.clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName= clazzName;
    }

    public java.util.List<Long> getSysFieldCollection(){
        return this.sysFieldCollection;
    }

    public void setSysFieldCollection(java.util.List<Long> sysFieldCollection) {
        this.sysFieldCollection= sysFieldCollection;
    }
}