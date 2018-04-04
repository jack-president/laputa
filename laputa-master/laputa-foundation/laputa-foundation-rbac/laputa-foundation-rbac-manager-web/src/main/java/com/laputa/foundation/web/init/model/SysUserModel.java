package com.laputa.foundation.web.init.model;

import java.util.List;

import com.laputa.foundation.web.rbac.entity.SysOperation;
import com.laputa.foundation.web.rbac.entity.SysUser;

/**
 * 初始化使用
 * Created by JiangDongPing on 2017/1/9.
 */
public class SysUserModel {
    private SysUser sysUser;

    private List<String> sysRoleList;

    private List<String> sysUserGroupList;

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public List<String> getSysRoleList() {
        return sysRoleList;
    }

    public void setSysRoleList(List<String> sysRoleList) {
        this.sysRoleList = sysRoleList;
    }

    public List<String> getSysUserGroupList() {
        return sysUserGroupList;
    }

    public void setSysUserGroupList(List<String> sysUserGroupList) {
        this.sysUserGroupList = sysUserGroupList;
    }
}
