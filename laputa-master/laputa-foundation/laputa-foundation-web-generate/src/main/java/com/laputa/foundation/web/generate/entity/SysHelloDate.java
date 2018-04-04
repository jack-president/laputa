package com.laputa.foundation.web.generate.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.laputa.foundation.persistence.audit.AuditingIdEntity;

/**
 * Created by JiangDongPing on 2016/12/7.
 */
@Entity
@Table(name = "sys_hello_date")
public class SysHelloDate extends AuditingIdEntity {
    private String cname;

    private Date wakeup;

    private Date birthDate;

    private Date acTime;

    @Column(name = "cname")
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Column(name = "wakeup")
    @Temporal(TemporalType.TIME)
    public Date getWakeup() {
        return wakeup;
    }

    public void setWakeup(Date wakeup) {
        this.wakeup = wakeup;
    }

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Column(name = "ac_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getAcTime() {
        return acTime;
    }

    public void setAcTime(Date acTime) {
        this.acTime = acTime;
    }
}
