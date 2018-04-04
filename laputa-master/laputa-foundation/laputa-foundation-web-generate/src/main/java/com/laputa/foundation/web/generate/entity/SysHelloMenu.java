package com.laputa.foundation.web.generate.entity;

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

import com.laputa.foundation.persistence.code.ParentAbleIdEntity;
import com.laputa.foundation.persistence.entity.IdEntity;

@Entity
@Table(name = "sys_hello_menu")
public class SysHelloMenu extends ParentAbleIdEntity<SysHelloMenu> {

    private String iconClass;

    private String resources;

    private Integer orderValue;

    private Set<SysHelloMenuBelongtoRelationSysHelloPermission> belongSysHelloPermissionCollection;

    @OneToMany(mappedBy = "sysHelloMenu", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<SysHelloMenuBelongtoRelationSysHelloPermission> getBelongSysHelloPermissionCollection() {
        return belongSysHelloPermissionCollection;
    }

    public void setBelongSysHelloPermissionCollection(
            Set<SysHelloMenuBelongtoRelationSysHelloPermission> belongSysHelloPermissionCollection) {
        this.belongSysHelloPermissionCollection = belongSysHelloPermissionCollection;
    }

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
}
