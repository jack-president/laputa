package com.laputa.foundation.web.generate.entity;

import javax.persistence.*;

import com.laputa.foundation.persistence.entity.IdEntity;

import java.util.Set;

/**
 * Created by JiangDongPing on 2016/11/10.
 */
@Entity
@Table(name = "sys_entity")
public class SysEntity extends IdEntity {
    private String cname;

    private String clazzName;

    private String tableName;

    private Boolean treeAble;

    private Boolean codeAble;

    private Boolean nameAble;

    private Integer pageSize;

    private String descript;

    private Set<SysField> sysFieldCollection;

    @Column(name = "cname")
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Column(name = "clazz_name")
    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    @Column(name = "table_name")
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Column(name = "tree_able")
    public Boolean getTreeAble() {
        return treeAble;
    }

    public void setTreeAble(Boolean treeAble) {
        this.treeAble = treeAble;
    }

    @Column(name = "code_able")
    public Boolean getCodeAble() {
        return codeAble;
    }

    public void setCodeAble(Boolean codeAble) {
        this.codeAble = codeAble;
    }

    @Column(name = "name_able")
    public Boolean getNameAble() {
        return nameAble;
    }

    public void setNameAble(Boolean nameAble) {
        this.nameAble = nameAble;
    }

    @Column(name = "page_size")
    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Column(name = "descript")
    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    @OrderBy("indexNumber ASC, id asc")
    @OneToMany(mappedBy = "sysEntity", cascade = {CascadeType.ALL}, orphanRemoval = false, fetch = FetchType.LAZY)
    public Set<SysField> getSysFieldCollection() {
        return sysFieldCollection;
    }

    public void setSysFieldCollection(Set<SysField> sysFieldCollection) {
        this.sysFieldCollection = sysFieldCollection;
    }
}
