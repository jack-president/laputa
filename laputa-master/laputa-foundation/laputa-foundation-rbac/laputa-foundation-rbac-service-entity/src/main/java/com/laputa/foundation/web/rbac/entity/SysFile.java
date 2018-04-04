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
 * 文件
 * Created by JiangDongPing on 2016/12/23.
 */
@Entity
@Table(name = "sys_file")
public class SysFile extends NameAbleIdEntity {

    private String path;

    private List<SysFileBelongtoRelationSysPermission> belongtoSysPermissionCollection;

    @Column(name = "path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @OneToMany(mappedBy = "sysFile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<SysFileBelongtoRelationSysPermission> getBelongtoSysPermissionCollection() {
        return belongtoSysPermissionCollection;
    }

    public void setBelongtoSysPermissionCollection(
            List<SysFileBelongtoRelationSysPermission> belongtoSysPermissionCollection) {
        this.belongtoSysPermissionCollection = belongtoSysPermissionCollection;
    }
}
