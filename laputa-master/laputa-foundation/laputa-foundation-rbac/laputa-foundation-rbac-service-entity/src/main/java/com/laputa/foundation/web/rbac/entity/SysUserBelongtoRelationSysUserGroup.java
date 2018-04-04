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
@Table(name = "sys_user_belongto_relation_sys_user_group", uniqueConstraints = {
        @UniqueConstraint(name = "UK_sys_user_id_union_sys_user_group_id", columnNames = {
                "sys_user_id", "sys_user_group_id"
        })
})
public class SysUserBelongtoRelationSysUserGroup extends AuditingIdEntity {

    private Long sysUserId;

    private SysUser sysUser;

    private Long sysUserGroupId;

    private SysUserGroup sysUserGroup;

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
