package com.laputa.foundation.web.rbac.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.laputa.foundation.persistence.audit.AuditingIdEntity;

/**
 * Created by JiangDongPing on 2016/12/23.
 */
@Entity
@Table(name = "sys_element_belongto_relation_sys_permission", uniqueConstraints = {
        @UniqueConstraint(name = "UK_sys_element_id_union_sys_permission_id", columnNames = {
                "sys_element_id", "sys_permission_id"
        })
})
public class SysElementBelongtoRelationSysPermission extends AuditingIdEntity {
    private Long sysElementId;

    private SysElement sysElement;

    private Long sysPermissionId;

    private SysPermission sysPermission;

    @Column(name = "sys_element_id", insertable = false, updatable = false)
    public Long getSysElementId() {
        return sysElementId;
    }

    public void setSysElementId(Long sysElementId) {
        this.sysElementId = sysElementId;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name = "sys_element_id", updatable = false)
    public SysElement getSysElement() {
        return sysElement;
    }

    public void setSysElement(SysElement sysElement) {
        this.sysElement = sysElement;
    }

    @Column(name = "sys_permission_id", insertable = false, updatable = false)
    public Long getSysPermissionId() {
        return sysPermissionId;
    }

    public void setSysPermissionId(Long sysPermissionId) {
        this.sysPermissionId = sysPermissionId;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name = "sys_permission_id", updatable = false)
    public SysPermission getSysPermission() {
        return sysPermission;
    }

    public void setSysPermission(SysPermission sysPermission) {
        this.sysPermission = sysPermission;
    }
}
