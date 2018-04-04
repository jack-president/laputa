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
@Table(name = "sys_role_belongto_relation_sys_user_group", uniqueConstraints = {
        @UniqueConstraint(name = "UK_sys_role_id_union_sys_user_group_id", columnNames = {
                "sys_role_id", "sys_user_group_id"
        })
})
public class SysRoleBelongtoRelationSysUserGroup extends AuditingIdEntity {

    private Long sysRoleId;

    private SysRole sysRole;

    private Long sysUserGroupId;

    private SysUserGroup sysUserGroup;

    @Column(name = "sys_role_id", insertable = false, updatable = false)
    public Long getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(Long sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name = "sys_role_id", updatable = false)
    public SysRole getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }

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
}
