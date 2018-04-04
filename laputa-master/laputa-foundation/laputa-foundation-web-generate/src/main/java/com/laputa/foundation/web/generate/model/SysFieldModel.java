package com.laputa.foundation.web.generate.model;

/**
 * <p>
 * 实体表 Model<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2016-11-30T17:23:51+08:00 .
 */
public class SysFieldModel {

    /**
     * 主键
     */
    private java.lang.Long id;


    /**
     * 名字
     */
    private java.lang.String cname;


    /**
     * 字段关联实体
     */
    private java.lang.Long relationSysEntityId;



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

    public java.lang.Long getRelationSysEntityId(){
        return this.relationSysEntityId;
    }

    public void setRelationSysEntityId(java.lang.Long relationSysEntityId) {
        this.relationSysEntityId= relationSysEntityId;
    }
}