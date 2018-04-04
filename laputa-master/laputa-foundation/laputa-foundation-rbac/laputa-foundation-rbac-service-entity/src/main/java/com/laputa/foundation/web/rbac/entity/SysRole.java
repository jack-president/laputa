package com.laputa.foundation.web.rbac.entity;

import java.util.List;

import javax.persistence.*;

import com.laputa.foundation.persistence.code.CodeAbleIdEntity;

/**
 * 角色
 * Created by JiangDongPing on 2016/12/23.
 */
@Entity
@Table(name = "sys_role")
public class SysRole extends CodeAbleIdEntity {

    private SysUserGroup parentSysUserGroup;

    private Long parentSysUserGroupId;

    private Boolean inverted;

    private List<SysPermissionBelongtoRelationSysRole> ownSysPermissionCollection;

    private List<SysUserBelongtoRelationSysRole> ownSysUserCollection;

    //private List<SysRoleBelongtoRelationSysUserGroup> belongtoSysUserGroupCollection;

    @Column(name = "inverted", nullable = false, updatable = false)
    public Boolean getInverted() {
        return inverted;
    }

    public void setInverted(Boolean inverted) {
        this.inverted = inverted;
    }

    @OneToMany(mappedBy = "sysRole", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<SysPermissionBelongtoRelationSysRole> getOwnSysPermissionCollection() {
        return ownSysPermissionCollection;
    }

    public void setOwnSysPermissionCollection(
            List<SysPermissionBelongtoRelationSysRole> ownSysPermissionCollection) {
        this.ownSysPermissionCollection = ownSysPermissionCollection;
    }

    @OneToMany(mappedBy = "sysRole", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<SysUserBelongtoRelationSysRole> getOwnSysUserCollection() {
        return ownSysUserCollection;
    }

    public void setOwnSysUserCollection(List<SysUserBelongtoRelationSysRole> ownSysUserCollection) {
        this.ownSysUserCollection = ownSysUserCollection;
    }

//    @OneToMany(mappedBy = "sysRole", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    public List<SysRoleBelongtoRelationSysUserGroup> getBelongtoSysUserGroupCollection() {
//        return belongtoSysUserGroupCollection;
//    }
//
//    public void setBelongtoSysUserGroupCollection(
//            List<SysRoleBelongtoRelationSysUserGroup> belongtoSysUserGroupCollection) {
//        this.belongtoSysUserGroupCollection = belongtoSysUserGroupCollection;
//    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_sys_user_group_id", updatable = false)
    public SysUserGroup getParentSysUserGroup() {
        return parentSysUserGroup;
    }

    public void setParentSysUserGroup(SysUserGroup parentSysUserGroup) {
        this.parentSysUserGroup = parentSysUserGroup;
    }


    @Column(name = "parent_sys_user_group_id", insertable = false, updatable = false)
    public Long getParentSysUserGroupId() {
        return parentSysUserGroupId;
    }

    public void setParentSysUserGroupId(Long parentSysUserGroupId) {
        this.parentSysUserGroupId = parentSysUserGroupId;
    }
}
