package com.laputa.foundation.web.generate.model;

/**
 * <p>
 * Hi菜单 Model<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2016-12-01T16:55:02+08:00 .
 */
public class SysElementModel {

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
}