package com.laputa.foundation.web.rbac.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.laputa.foundation.persistence.audit.AuditingIdEntity;
import com.laputa.foundation.persistence.code.NameAbleIdEntity;

/**
 * 用户
 * Created by JiangDongPing on 2016/12/23.
 */
@Entity
@Table(name = "sys_user")
public class SysUser extends NameAbleIdEntity {

    private String username;

    private String password;

    private Boolean locked;

    private List<SysUserBelongtoRelationSysRole> belongtoSysRoleCollection;

    private List<SysUserBelongtoRelationSysUserGroup> belongtoSysUserGroupCollection;

    @Column(name = "username", unique = true, nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "locked", nullable = false)
    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    @OneToMany(mappedBy = "sysUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<SysUserBelongtoRelationSysRole> getBelongtoSysRoleCollection() {
        return belongtoSysRoleCollection;
    }

    public void setBelongtoSysRoleCollection(List<SysUserBelongtoRelationSysRole> belongtoSysRoleCollection) {
        this.belongtoSysRoleCollection = belongtoSysRoleCollection;
    }

    @OneToMany(mappedBy = "sysUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<SysUserBelongtoRelationSysUserGroup> getBelongtoSysUserGroupCollection() {
        return belongtoSysUserGroupCollection;
    }

    public void setBelongtoSysUserGroupCollection(
            List<SysUserBelongtoRelationSysUserGroup> belongtoSysUserGroupCollection) {
        this.belongtoSysUserGroupCollection = belongtoSysUserGroupCollection;
    }
}
