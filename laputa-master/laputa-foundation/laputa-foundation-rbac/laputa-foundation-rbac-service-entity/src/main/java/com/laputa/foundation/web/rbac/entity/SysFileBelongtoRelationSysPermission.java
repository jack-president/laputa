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
@Table(name = "sys_file_belongto_relation_sys_permission", uniqueConstraints = {
        @UniqueConstraint(name = "UK_sys_file_id_union_sys_permission_id", columnNames = {
                "sys_file_id", "sys_permission_id"
        })
})
public class SysFileBelongtoRelationSysPermission extends AuditingIdEntity {
    private Long sysFileId;

    private SysFile sysFile;

    private Long sysPermissionId;

    private SysPermission sysPermission;

    @Column(name = "sys_file_id", insertable = false, updatable = false)
    public Long getSysFileId() {
        return sysFileId;
    }

    public void setSysFileId(Long sysFileId) {
        this.sysFileId = sysFileId;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name = "sys_file_id", updatable = false)
    public SysFile getSysFile() {
        return sysFile;
    }

    public void setSysFile(SysFile sysFile) {
        this.sysFile = sysFile;
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
