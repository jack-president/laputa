package com.laputa.foundation.web.rbac.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.laputa.foundation.persistence.code.CodeAbleIdEntity;

/**
 * 权限
 * Created by JiangDongPing on 2016/12/23.
 */
@Entity
@Table(name = "sys_permission")
public class SysPermission extends CodeAbleIdEntity {

    private List<SysElementBelongtoRelationSysPermission> ownSysElementCollection;

    private List<SysFileBelongtoRelationSysPermission> ownSysFileCollection;

    private List<SysMenuBelongtoRelationSysPermission> ownSysMenuCollection;

    private List<SysOperationBelongtoRelationSysPermission> ownSysOperationCollection;

    private List<SysPermissionBelongtoRelationSysRole> belongtoSysRoleCollection;
    
    @OneToMany(mappedBy = "sysPermission", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<SysElementBelongtoRelationSysPermission> getOwnSysElementCollection() {
        return ownSysElementCollection;
    }

    public void setOwnSysElementCollection(List<SysElementBelongtoRelationSysPermission> ownSysElementCollection) {
        this.ownSysElementCollection = ownSysElementCollection;
    }

    @OneToMany(mappedBy = "sysPermission", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<SysFileBelongtoRelationSysPermission> getOwnSysFileCollection() {
        return ownSysFileCollection;
    }

    public void setOwnSysFileCollection(List<SysFileBelongtoRelationSysPermission> ownSysFileCollection) {
        this.ownSysFileCollection = ownSysFileCollection;
    }

    @OneToMany(mappedBy = "sysPermission", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<SysMenuBelongtoRelationSysPermission> getOwnSysMenuCollection() {
        return ownSysMenuCollection;
    }

    public void setOwnSysMenuCollection(List<SysMenuBelongtoRelationSysPermission> ownSysMenuCollection) {
        this.ownSysMenuCollection = ownSysMenuCollection;
    }

    @OneToMany(mappedBy = "sysPermission", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<SysOperationBelongtoRelationSysPermission> getOwnSysOperationCollection() {
        return ownSysOperationCollection;
    }

    public void setOwnSysOperationCollection(
            List<SysOperationBelongtoRelationSysPermission> ownSysOperationCollection) {
        this.ownSysOperationCollection = ownSysOperationCollection;
    }

    @OneToMany(mappedBy = "sysPermission", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<SysPermissionBelongtoRelationSysRole> getBelongtoSysRoleCollection() {
        return belongtoSysRoleCollection;
    }

    public void setBelongtoSysRoleCollection(List<SysPermissionBelongtoRelationSysRole> belongtoSysRoleCollection) {
        this.belongtoSysRoleCollection = belongtoSysRoleCollection;
    }
}
