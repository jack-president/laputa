package com.laputa.foundation.web.init.model;

import java.util.List;

import com.laputa.foundation.web.rbac.entity.SysPermission;

/**
 * Created by JiangDongPing on 2017/1/11.
 */
public class SysPermissionModel {
    private SysPermission sysPermission;

    private List<String> sysOperationList;

    private List<String> sysFileList;

    private List<String> sysMenuList;

    private List<String> sysElementList;

    public SysPermission getSysPermission() {
        return sysPermission;
    }

    public void setSysPermission(SysPermission sysPermission) {
        this.sysPermission = sysPermission;
    }

    public List<String> getSysOperationList() {
        return sysOperationList;
    }

    public void setSysOperationList(List<String> sysOperationList) {
        this.sysOperationList = sysOperationList;
    }

    public List<String> getSysFileList() {
        return sysFileList;
    }

    public void setSysFileList(List<String> sysFileList) {
        this.sysFileList = sysFileList;
    }

    public List<String> getSysMenuList() {
        return sysMenuList;
    }

    public void setSysMenuList(List<String> sysMenuList) {
        this.sysMenuList = sysMenuList;
    }

    public List<String> getSysElementList() {
        return sysElementList;
    }

    public void setSysElementList(List<String> sysElementList) {
        this.sysElementList = sysElementList;
    }
}
