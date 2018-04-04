package com.laputa.foundation.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.laputa.foundation.persistence.audit.AuditingIdEntity;

/**
 * Created by jiangdongping on 2016/10/29 0029.
 */
@Entity
@Table(name = "sys_au_element")
public class SysAuElement extends AuditingIdEntity {

    private String code;

    private String cname;

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "cname")
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
