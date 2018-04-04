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
@Table(name = "sys_operation_belongto_relation_sys_permission", uniqueConstraints = {
        @UniqueConstraint(name = "UK_sys_operation_id_union_sys_permission_id", columnNames = {
                "sys_operation_id", "sys_permission_id"
        })
})
public class SysOperationBelongtoRelationSysPermission extends AuditingIdEntity {

    private Long sysOperationId;

    private SysOperation sysOperation;

    private Long sysPermissionId;

    private SysPermission sysPermission;

    @Column(name = "sys_operation_id", insertable = false, updatable = false)
    public Long getSysOperationId() {
        return sysOperationId;
    }

    public void setSysOperationId(Long sysOperationId) {
        this.sysOperationId = sysOperationId;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name = "sys_operation_id", updatable = false)
    public SysOperation getSysOperation() {
        return sysOperation;
    }

    public void setSysOperation(SysOperation sysOperation) {
        this.sysOperation = sysOperation;
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
