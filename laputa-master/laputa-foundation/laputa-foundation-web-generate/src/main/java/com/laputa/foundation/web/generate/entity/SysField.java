package com.laputa.foundation.web.generate.entity;

import com.laputa.foundation.persistence.entity.IdEntity;

import javax.persistence.*;

/**
 * Created by JiangDongPing on 2016/11/10.
 */
@Entity
@Table(name = "sys_field")
public class SysField extends IdEntity {

    private String cname;

    private String fieldName;

    private String clazzName;

    private String columnName;

    private Boolean uniqueValue;

    private Boolean nullable;

    private Boolean insertable;

    private Boolean updatable;

    private Integer lengthValue;

    private Integer precisionValue;

    private Integer scale;

    private String mappedBy;

    private Boolean tree;

    private String columnTypeCode;

    private String descript;

    private Integer indexNumber;

    private Boolean showable;

    private Integer showwidth;

    private String temporalType;

    private Boolean audit;

    private SysEntity sysEntity;

    private Long sysEntityId;

    private Long relationSysEntityId;

    private SysEntity relationSysEntity;

    private String nestClazzName;

    //    private Long nestRelationSysEntityId;
    //
    //    private SysEntity nestRelationSysEntity;

    @Column(name = "cname")
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Column(name = "field_name")
    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Column(name = "clazz_name")
    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    @Column(name = "column_name")
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Column(name = "unique_value")
    public Boolean getUniqueValue() {
        return uniqueValue;
    }

    public void setUniqueValue(Boolean uniqueValue) {
        this.uniqueValue = uniqueValue;
    }

    @Column(name = "nullable")
    public Boolean getNullable() {
        return nullable;
    }

    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }

    @Column(name = "insertable")
    public Boolean getInsertable() {
        return insertable;
    }

    public void setInsertable(Boolean insertable) {
        this.insertable = insertable;
    }

    @Column(name = "updatable")
    public Boolean getUpdatable() {
        return updatable;
    }

    public void setUpdatable(Boolean updatable) {
        this.updatable = updatable;
    }

    @Column(name = "lengthValue")
    public Integer getLengthValue() {
        return lengthValue;
    }

    public void setLengthValue(Integer lengthValue) {
        this.lengthValue = lengthValue;
    }

    @Column(name = "precisionValue")
    public Integer getPrecisionValue() {
        return precisionValue;
    }

    public void setPrecisionValue(Integer precisionValue) {
        this.precisionValue = precisionValue;
    }

    @Column(name = "scale")
    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    @Column(name = "mapped_by")
    public String getMappedBy() {
        return mappedBy;
    }

    public void setMappedBy(String mappedBy) {
        this.mappedBy = mappedBy;
    }

    @Column(name = "tree")
    public Boolean getTree() {
        return tree;
    }

    public void setTree(Boolean tree) {
        this.tree = tree;
    }

    @Column(name = "audit")
    public Boolean getAudit() {
        return audit;
    }

    public void setAudit(Boolean audit) {
        this.audit = audit;
    }

    @Column(name = "column_type_code")
    public String getColumnTypeCode() {
        return columnTypeCode;
    }

    public void setColumnTypeCode(String columnTypeCode) {
        this.columnTypeCode = columnTypeCode;
    }

    @Column(name = "descript")
    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    @Column(name = "index_number")
    public Integer getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(Integer indexNumber) {
        this.indexNumber = indexNumber;
    }

    @Column(name = "showable")
    public Boolean getShowable() {
        return showable;
    }

    public void setShowable(Boolean showable) {
        this.showable = showable;
    }

    @Column(name = "showwidth")
    public Integer getShowwidth() {
        return showwidth;
    }


    @Column(name = "temporal_type")
    public String getTemporalType() {
        return temporalType;
    }

    public void setTemporalType(String temporalType) {
        this.temporalType = temporalType;
    }

    public void setShowwidth(Integer showwidth) {
        this.showwidth = showwidth;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sys_entity_id", updatable = true)
    public SysEntity getSysEntity() {
        return sysEntity;
    }

    public void setSysEntity(SysEntity sysEntity) {
        this.sysEntity = sysEntity;
    }

    @Column(name = "sys_entity_id", insertable = false, updatable = false)
    public Long getSysEntityId() {
        return sysEntityId;
    }

    public void setSysEntityId(Long sysEntityId) {
        this.sysEntityId = sysEntityId;
    }

    @Column(name = "relation_sys_entity_id", insertable = false, updatable = false)
    public Long getRelationSysEntityId() {
        return relationSysEntityId;
    }

    public void setRelationSysEntityId(Long relationSysEntityId) {
        this.relationSysEntityId = relationSysEntityId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "relation_sys_entity_id", updatable = true)
    public SysEntity getRelationSysEntity() {
        return relationSysEntity;
    }

    public void setRelationSysEntity(SysEntity relationSysEntity) {
        this.relationSysEntity = relationSysEntity;
    }

    @Column(name = "nest_clazz_name")
    public String getNestClazzName() {
        return nestClazzName;
    }

    public void setNestClazzName(String nestClazzName) {
        this.nestClazzName = nestClazzName;
    }
    //    @Column(name = "nest_relation_sys_entity_id", insertable = false, updatable = false)
    //    public Long getNestRelationSysEntityId() {
    //        return nestRelationSysEntityId;
    //    }
    //
    //    public void setNestRelationSysEntityId(Long nestRelationSysEntityId) {
    //        this.nestRelationSysEntityId = nestRelationSysEntityId;
    //    }
    //
    //    @ManyToOne(fetch = FetchType.LAZY)
    //    @JoinColumn(name = "nest_relation_sys_entity_id", updatable = true)
    //    public SysEntity getNestRelationSysEntity() {
    //        return nestRelationSysEntity;
    //    }
    //
    //    public void setNestRelationSysEntity(SysEntity nestRelationSysEntity) {
    //        this.nestRelationSysEntity = nestRelationSysEntity;
    //    }
}
