package com.laputa.foundation.web.generate.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.laputa.foundation.persistence.audit.AuditingIdEntity;
import com.laputa.foundation.persistence.entity.IdEntity;

/**
 * Created by JiangDongPing on 2016/12/7.
 */
@Entity
@Table(name = "sys_hello_element")
public class SysHelloElement extends IdEntity {
    private String cname;

    private String code;

    @Column(name = "cname")
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
