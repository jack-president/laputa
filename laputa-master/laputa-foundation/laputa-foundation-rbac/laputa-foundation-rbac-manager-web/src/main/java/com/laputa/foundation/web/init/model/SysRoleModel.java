package com.laputa.foundation.web.init.model;

import java.io.Serializable;
import java.util.List;

import com.laputa.foundation.web.rbac.entity.SysRole;

/**
 * Created by JiangDongPing on 2017/1/18.
 */
public class SysRoleModel implements Serializable {
    private SysRole sysRole;

    private List<String> permissionList;

    public SysRole getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }

    public List<String> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<String> permissionList) {
        this.permissionList = permissionList;
    }
}
