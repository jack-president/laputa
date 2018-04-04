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
@Table(name = "sys_menu_belongto_relation_sys_permission", uniqueConstraints = {
        @UniqueConstraint(name = "UK_sys_menu_id_union_sys_permission_id", columnNames = {
                "sys_menu_id", "sys_permission_id"
        })
})
public class SysMenuBelongtoRelationSysPermission extends AuditingIdEntity {

    private Long sysMenuId;

    private SysMenu sysMenu;

    private Long sysPermissionId;

    private SysPermission sysPermission;

    @Column(name = "sys_menu_id", insertable = false, updatable = false)
    public Long getSysMenuId() {
        return sysMenuId;
    }

    public void setSysMenuId(Long sysMenuId) {
        this.sysMenuId = sysMenuId;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name = "sys_menu_id", updatable = false)
    public SysMenu getSysMenu() {
        return sysMenu;
    }

    public void setSysMenu(SysMenu sysMenu) {
        this.sysMenu = sysMenu;
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
