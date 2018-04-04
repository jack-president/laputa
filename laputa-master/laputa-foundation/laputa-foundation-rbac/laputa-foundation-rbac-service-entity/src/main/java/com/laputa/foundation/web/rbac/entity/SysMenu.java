package com.laputa.foundation.web.rbac.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.laputa.foundation.persistence.code.CodeAbleIdEntity;
import com.laputa.foundation.persistence.code.ParentAbleIdEntity;

/**
 * 菜单
 * Created by JiangDongPing on 2016/12/23.
 */
@Entity
@Table(name = "sys_menu")
public class SysMenu extends ParentAbleIdEntity<SysMenu> {

    private String iconClass;

    private String resources;

    private Integer orderValue;

    private List<SysMenuBelongtoRelationSysPermission> belongtoSysPermissionCollection;

    @Column(name = "icon_class")
    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    @Column(name = "resources")
    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    @Column(name = "order_value")
    public Integer getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(Integer orderValue) {
        this.orderValue = orderValue;
    }

    @OneToMany(mappedBy = "sysMenu", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<SysMenuBelongtoRelationSysPermission> getBelongtoSysPermissionCollection() {
        return belongtoSysPermissionCollection;
    }

    public void setBelongtoSysPermissionCollection(
            List<SysMenuBelongtoRelationSysPermission> belongtoSysPermissionCollection) {
        this.belongtoSysPermissionCollection = belongtoSysPermissionCollection;
    }
}
