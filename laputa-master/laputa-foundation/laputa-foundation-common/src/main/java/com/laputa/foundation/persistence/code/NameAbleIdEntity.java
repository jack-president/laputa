package com.laputa.foundation.persistence.code;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.laputa.foundation.persistence.audit.AuditingIdEntity;

/**
 * Created by JiangDongPing on 2016/12/28.
 */
@MappedSuperclass
public abstract class NameAbleIdEntity extends AuditingIdEntity {
    private String cname;

    private String descript;

    @Column(name = "cname", nullable = false)
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Column(name = "descript")
    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }
}
