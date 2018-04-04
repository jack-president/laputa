package com.laputa.foundation.persistence.code;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

/**
 * Created by JiangDongPing on 2017/1/10.
 */
@MappedSuperclass
public abstract class ParentAbleIdEntity<T extends ParentAbleIdEntity> extends CodeAbleIdEntity {

    private T parent;

    private List<T> children;

    private Long parentId;

    @Column(name = "parent_id", insertable = false, updatable = false)
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", insertable = true, updatable = true)
    public T getParent() {
        return parent;
    }

    public void setParent(T parent) {
        this.parent = parent;
    }

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }
}
