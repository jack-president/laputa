package com.laputa.foundation.web.rbac.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.laputa.foundation.persistence.audit.AuditingIdEntity;
import com.laputa.foundation.persistence.code.CodeAbleIdEntity;
import com.laputa.foundation.persistence.entity.IdEntity;

/**
 * 页面元素
 * <p>
 * Created by JiangDongPing on 2016/1/13.
 */
@Entity
@Table(name = "sys_element")
public class SysElement extends CodeAbleIdEntity {

    private List<SysElementBelongtoRelationSysPermission> belongtoSysPermissionCollection;

    @OneToMany(mappedBy = "sysElement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<SysElementBelongtoRelationSysPermission> getBelongtoSysPermissionCollection() {
        return belongtoSysPermissionCollection;
    }

    public void setBelongtoSysPermissionCollection(
            List<SysElementBelongtoRelationSysPermission> belongtoSysPermissionCollection) {
        this.belongtoSysPermissionCollection = belongtoSysPermissionCollection;
    }
}
