package com.laputa.foundation.web.rbac.entity;

import com.laputa.foundation.persistence.code.ParentAbleIdEntity;

import javax.persistence.*;
import java.util.List;

/**
 * 用户组
 * Created by JiangDongPing on 2016/12/23.
 */
@Entity
@Table(name = "sys_user_group")
public class SysUserGroup extends ParentAbleIdEntity<SysUserGroup> {

    private List<SysUserBelongtoRelationSysUserGroup> ownSysUserCollection;


    private List<SysUserGroupCanhaveRelationSysPermission> canhaveRelationSysPermissionCollection;

    private List<SysRole> ownSysRoleCollection;

    @OneToMany(mappedBy = "sysUserGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<SysUserBelongtoRelationSysUserGroup> getOwnSysUserCollection() {
        return ownSysUserCollection;
    }

    public void setOwnSysUserCollection(List<SysUserBelongtoRelationSysUserGroup> ownSysUserCollection) {
        this.ownSysUserCollection = ownSysUserCollection;
    }

    @OneToMany(mappedBy = "parentSysUserGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<SysRole> getOwnSysRoleCollection() {
        return ownSysRoleCollection;
    }

    public void setOwnSysRoleCollection(List<SysRole> ownSysRoleCollection) {
        this.ownSysRoleCollection = ownSysRoleCollection;
    }

    @OneToMany(mappedBy = "sysUserGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<SysUserGroupCanhaveRelationSysPermission> getCanhaveRelationSysPermissionCollection() {
        return canhaveRelationSysPermissionCollection;
    }

    public void setCanhaveRelationSysPermissionCollection(List<SysUserGroupCanhaveRelationSysPermission> canhaveRelationSysPermissionCollection) {
        this.canhaveRelationSysPermissionCollection = canhaveRelationSysPermissionCollection;
    }
}
