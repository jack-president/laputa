package com.laputa.foundation.web.rbac.model;

import com.laputa.foundation.web.rbac.entity.SysRole;
import com.laputa.foundation.web.rbac.entity.SysUserGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置用户关联部门下角色数据模型
 * var idName = "id"
 * var textName = "cname"
 * var parentIdName = "pid";
 * var childName = "items";
 * var isRootName = "isRoot";
 * Created by jiangdongping on 2017/8/18 0018.
 */
public class ConfigSysRoleDataModel {
    private Long id;
    private String cname;
    private String icon;
    private Boolean expanded;
    private List<ConfigSysRoleDataModel> items;

    public ConfigSysRoleDataModel(SysUserGroup sysUserGroup) {
        id = null;
        cname = sysUserGroup.getCname();
        icon = "k-laputa-treeview-checkboxes-rootfolder";
        if (sysUserGroup.getOwnSysRoleCollection() != null && sysUserGroup.getOwnSysRoleCollection().size() > 0) {
            expanded = true;
        } else {
            expanded = false;
        }
        items = new ArrayList<>();
    }

    public ConfigSysRoleDataModel(SysRole sysRole) {
        id = sysRole.getId();
        cname = sysRole.getCname();
        icon = "k-laputa-treeview-checkboxes-html";
        expanded = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<ConfigSysRoleDataModel> getItems() {
        return items;
    }

    public void setItems(List<ConfigSysRoleDataModel> items) {
        this.items = items;
    }

    public Boolean getExpanded() {
        return expanded;
    }

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }
}
