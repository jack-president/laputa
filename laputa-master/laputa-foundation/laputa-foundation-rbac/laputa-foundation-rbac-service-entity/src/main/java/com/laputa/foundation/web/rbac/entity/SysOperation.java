package com.laputa.foundation.web.rbac.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.laputa.foundation.persistence.code.CodeAbleIdEntity;
import com.laputa.foundation.persistence.code.ParentAbleIdEntity;

/**
 * Created by JiangDongPing on 2016/12/23.
 */
@Entity
@Table(name = "sys_operation")
public class SysOperation extends ParentAbleIdEntity<SysOperation> {

    /**
     * 拦截URL前缀
     */
    private String prefixUrl;

    private List<SysOperationBelongtoRelationSysPermission> belongtoSysPermissionCollection;

    @Column(name = "prefix_url")
    public String getPrefixUrl() {
        return prefixUrl;
    }

    public void setPrefixUrl(String prefixUrl) {
        this.prefixUrl = prefixUrl;
    }

    @OneToMany(mappedBy = "sysOperation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<SysOperationBelongtoRelationSysPermission> getBelongtoSysPermissionCollection() {
        return belongtoSysPermissionCollection;
    }

    public void setBelongtoSysPermissionCollection(
            List<SysOperationBelongtoRelationSysPermission> belongtoSysPermissionCollection) {
        this.belongtoSysPermissionCollection = belongtoSysPermissionCollection;
    }
}
