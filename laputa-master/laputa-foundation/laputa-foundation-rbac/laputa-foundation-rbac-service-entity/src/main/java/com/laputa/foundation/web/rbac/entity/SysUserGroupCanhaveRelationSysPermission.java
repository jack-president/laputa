package com.laputa.foundation.web.rbac.entity;

import com.laputa.foundation.persistence.audit.AuditingIdEntity;

import javax.persistence.*;

/**
 * Created by jiangdongping on 2017/8/14 0014.
 */
@Entity
@Table(name = "sys_user_group_canhave_relation_sys_permission", uniqueConstraints = {
        @UniqueConstraint(name = "UK_sys_user_group_id_union_sys_permission_id", columnNames = {
                "sys_user_group_id", "sys_permission_id"
        })
})
public class SysUserGroupCanhaveRelationSysPermission extends AuditingIdEntity {

    private Long sysUserGroupId;

    private SysUserGroup sysUserGroup;

    private Long sysPermissionId;

    private SysPermission sysPermission;


    @Column(name = "sys_user_group_id", insertable = false, updatable = false)
    public Long getSysUserGroupId() {
        return sysUserGroupId;
    }

    public void setSysUserGroupId(Long sysUserGroupId) {
        this.sysUserGroupId = sysUserGroupId;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name = "sys_user_group_id", updatable = false)
    public SysUserGroup getSysUserGroup() {
        return sysUserGroup;
    }

    public void setSysUserGroup(SysUserGroup sysUserGroup) {
        this.sysUserGroup = sysUserGroup;
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
