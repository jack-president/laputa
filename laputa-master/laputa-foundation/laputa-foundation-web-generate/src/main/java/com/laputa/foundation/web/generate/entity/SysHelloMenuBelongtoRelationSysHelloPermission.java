package com.laputa.foundation.web.generate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.laputa.foundation.persistence.entity.IdEntity;

/**
 * Created by JiangDongPing on 2016/12/2.
 */
@Entity
@Table(name = "sys_hello_menu_belong_relation_sys_hello_permission", uniqueConstraints = {
        @UniqueConstraint(name = "UK_sys_hello_permission_id_union_sys_hello_menu_id", columnNames = {
                "sys_hello_permission_id", "sys_hello_menu_id"
        })
})
public class SysHelloMenuBelongtoRelationSysHelloPermission extends IdEntity {
    private SysHelloPermission sysHelloPermission;

    private Long sysHelloPermissionId;

    private SysHelloMenu sysHelloMenu;

    private Long sysHelloMenuId;

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name = "sys_hello_permission_id", updatable = false)
    public SysHelloPermission getSysHelloPermission() {
        return sysHelloPermission;
    }

    public void setSysHelloPermission(SysHelloPermission sysHelloPermission) {
        this.sysHelloPermission = sysHelloPermission;
    }

    @Column(name = "sys_hello_permission_id", insertable = false, updatable = false)
    public Long getSysHelloPermissionId() {
        return sysHelloPermissionId;
    }

    public void setSysHelloPermissionId(Long sysHelloPermissionId) {
        this.sysHelloPermissionId = sysHelloPermissionId;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name = "sys_hello_menu_id", updatable = false)
    public SysHelloMenu getSysHelloMenu() {
        return sysHelloMenu;
    }

    public void setSysHelloMenu(SysHelloMenu sysHelloMenu) {
        this.sysHelloMenu = sysHelloMenu;
    }

    @Column(name = "sys_hello_menu_id", insertable = false, updatable = false)
    public Long getSysHelloMenuId() {
        return sysHelloMenuId;
    }

    public void setSysHelloMenuId(Long sysHelloMenuId) {
        this.sysHelloMenuId = sysHelloMenuId;
    }
}
