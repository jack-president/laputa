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
@Table(name = "sys_user_belongto_relation_sys_role", uniqueConstraints = {
        @UniqueConstraint(name = "UK_sys_user_id_union_sys_role_id", columnNames = {
                "sys_user_id", "sys_role_id"
        })
})
public class SysUserBelongtoRelationSysRole extends AuditingIdEntity {

    private Long sysUserId;

    private SysUser sysUser;

    private Long sysRoleId;

    private SysRole sysRole;

    @Column(name = "sys_user_id", insertable = false, updatable = false)
    public Long getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name = "sys_user_id", updatable = false)
    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

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
}
