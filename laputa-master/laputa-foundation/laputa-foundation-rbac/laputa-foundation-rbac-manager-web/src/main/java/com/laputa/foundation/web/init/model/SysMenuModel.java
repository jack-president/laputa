package com.laputa.foundation.web.init.model;

import java.io.Serializable;
import java.util.List;

import com.laputa.foundation.web.rbac.entity.SysMenu;
import com.laputa.foundation.web.rbac.entity.SysUser;

/**
 * 初始化使用
 * Created by JiangDongPing on 2017/1/9.
 */
public class SysMenuModel implements Serializable {
    SysMenu sysMenu;

    List<SysMenuModel> childSysMenuModelList;

    public SysMenu getSysMenu() {
        return sysMenu;
    }

    public void setSysMenu(SysMenu sysMenu) {
        this.sysMenu = sysMenu;
    }

    public List<SysMenuModel> getChildSysMenuModelList() {
        return childSysMenuModelList;
    }

    public void setChildSysMenuModelList(List<SysMenuModel> childSysMenuModelList) {
        this.childSysMenuModelList = childSysMenuModelList;
    }
}
