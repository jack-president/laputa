package com.laputa.foundation.web.generate.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.laputa.foundation.persistence.entity.IdEntity;

/**
 * Created by JiangDongPing on 2016/12/2.
 */
@Entity
@Table(name = "sys_hello_permission")
public class SysHelloPermission extends IdEntity {
    private String cname;

    private Set<SysHelloMenuBelongtoRelationSysHelloPermission> ownSysHelloMenuCollection;

    @Column(name = "cname")
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @OneToMany(mappedBy = "sysHelloPermission", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<SysHelloMenuBelongtoRelationSysHelloPermission> getOwnSysHelloMenuCollection() {
        return ownSysHelloMenuCollection;
    }

    public void setOwnSysHelloMenuCollection(
            Set<SysHelloMenuBelongtoRelationSysHelloPermission> ownSysHelloMenuCollection) {
        this.ownSysHelloMenuCollection = ownSysHelloMenuCollection;
    }
}
