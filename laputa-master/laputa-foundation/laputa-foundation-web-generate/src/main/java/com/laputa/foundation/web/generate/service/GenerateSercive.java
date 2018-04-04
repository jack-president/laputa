package com.laputa.foundation.web.generate.service;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.laputa.foundation.persistence.code.CodeAbleIdEntity;
import com.laputa.foundation.persistence.code.NameAbleIdEntity;
import com.laputa.foundation.persistence.code.ParentAbleIdEntity;
import com.laputa.foundation.web.generate.annotae.Annotae;
import com.laputa.foundation.web.generate.annotae.AnnotaeEntity;
import com.laputa.foundation.web.generate.annotae.ColumnType;
import com.laputa.foundation.web.generate.dao.SysEntityDao;
import com.laputa.foundation.web.generate.dao.SysEntityRepository;
import com.laputa.foundation.web.generate.dao.SysFieldRepository;
import com.laputa.foundation.web.generate.entity.SysEntity;
import com.laputa.foundation.web.generate.entity.SysField;

/**
 * Created by JiangDongPing on 2016/11/10.
 */
@Service
@Transactional
@SuppressWarnings("unchecked")
public class GenerateSercive {

    @Resource
    SysEntityRepository sysEntityRepository;

    @Resource
    SysFieldRepository sysFieldRepository;

    @Autowired(required = false)
    List<AnnotaeEntity> annotaeEntityList;

    @PersistenceContext
    private EntityManager entityManager;

    public void init() {
        if (!CollectionUtils.isEmpty(annotaeEntityList)) {
            refreshSysEntity();
            clearSysFiled();
            refreshSysFiled();
        }
    }

    public void clear() {
        sysEntityRepository.deleteAll();
    }

    private void refreshSysEntity() {
        /**
         * 处理新增 SysEntity
         */
        for (AnnotaeEntity annotaeEntity : annotaeEntityList) {
            SysEntity sysEntity = sysEntityRepository.findByClazzName(annotaeEntity.getClazz().getName());
            if (sysEntity == null) {
                sysEntity = analyBaseSysEntity(annotaeEntity.getClazz());
                sysEntity.setCname(annotaeEntity.getName());
                sysEntity.setPageSize(annotaeEntity.getPageSize());
                sysEntity.setDescript(annotaeEntity.getDescript());
                sysEntityRepository.save(sysEntity);
            }
        }
    }

    private void refreshSysFiled() {
        for (AnnotaeEntity annotaeEntity : annotaeEntityList) {
            SysEntity sysEntity = sysEntityRepository.findByClazzName(annotaeEntity.getClazz().getName());
            if (!CollectionUtils.isEmpty(annotaeEntity.getFieldsAnnotaeMap())) {
                for (Map.Entry<String, Annotae> entry : annotaeEntity.getFieldsAnnotaeMap().entrySet()) {
                    SysField sysField = sysFieldRepository.findBySysEntityIdAndFieldName(sysEntity.getId(), entry.getKey());
                    if (sysField == null) {
                        sysField = analyBaseSysField(annotaeEntity.getClazz(), entry.getKey());
                        if (sysField.getTree()) {
                            sysEntity.setTreeAble(Boolean.TRUE);
                        }
                        sysField.setCname(entry.getValue().getCname());
                        sysField.setDescript(entry.getValue().getDescript());
                        sysField.setIndexNumber(entry.getValue().getIndexNumber());
                        sysField.setShowable(entry.getValue().getShowable() != null ? entry.getValue().getShowable() : Boolean.TRUE);
                        sysField.setShowwidth(entry.getValue().getShowwidth() != null ? entry.getValue().getShowwidth() : 180);
                        if (entry.getValue().getUpdatable() != null) {
                            sysField.setUpdatable(entry.getValue().getUpdatable());
                        }

                        sysField.setSysEntity(sysEntity);

                        /**
                         * 配置默认值
                         */
                        if (StringUtils.isEmpty(sysField.getCname())) {
                            sysField.setCname(sysField.getFieldName());
                        }
                        if (StringUtils.isEmpty(sysField.getDescript())) {
                            sysField.setDescript(sysField.getCname());
                        }
                        sysFieldRepository.save(sysField);
                    }
                }
            }
        }
    }

    public void refreshTreeAble() {
        for (AnnotaeEntity annotaeEntity : annotaeEntityList) {
            SysEntity sysEntity = sysEntityRepository.findByClazzName(annotaeEntity.getClazz().getName());
            SysField parentFiled = null;
            for (SysField sysField : sysEntity.getSysFieldCollection()) {
                if (sysField.getColumnName().equals("parentId") && ColumnType.MANY_TO_ONE.name().equals(sysField.getColumnTypeCode())) {
                    sysEntity.setTreeAble(Boolean.TRUE);
                    sysField.setTree(Boolean.TRUE);
                    parentFiled = sysField;
                    sysEntityRepository.save(sysEntity);
                    sysFieldRepository.save(sysField);
                    break;
                }
            }
            if (sysEntity.getTreeAble()) {
                for (SysField sysField : sysEntity.getSysFieldCollection()) {
                    if (parentFiled.getFieldName().equals(sysField.getMappedBy())) {
                        sysField.setTree(Boolean.TRUE);
                        sysFieldRepository.save(sysField);
                    }
                }
            }
        }
    }

    private void clearSysFiled() {
        for (AnnotaeEntity annotaeEntity : annotaeEntityList) {
            SysEntity sysEntity = sysEntityRepository.findByClazzName(annotaeEntity.getClazz().getName());
            if (!CollectionUtils.isEmpty(annotaeEntity.getFieldsAnnotaeMap())) {
                if (!CollectionUtils.isEmpty(sysEntity.getSysFieldCollection())) {
                    List<SysField> needRemove = new ArrayList<SysField>();
                    for (SysField sysField : sysEntity.getSysFieldCollection()) {
                        boolean sysFieldExist = false;
                        for (Map.Entry<String, Annotae> entry : annotaeEntity.getFieldsAnnotaeMap().entrySet()) {
                            if (sysField.getFieldName().equals(entry.getKey())) {
                                sysFieldExist = true;
                                break;
                            }
                        }
                        if (!sysFieldExist) {
                            needRemove.add(sysField);
                        }
                    }
                    sysFieldRepository.deleteAll(needRemove);
                    sysEntity.getSysFieldCollection().removeAll(needRemove);
                }
            } else {
                sysFieldRepository.deleteAll(sysEntity.getSysFieldCollection());
                sysEntity.getSysFieldCollection().clear();
            }
        }
    }

    /**
     * 分析字节码文件中能得到的描述信息
     *
     * @param clazz
     * @param filedName
     * @return
     */
    private SysField analyBaseSysField(Class clazz, String filedName) {
        SysField sysField = new SysField();
        sysField.setTree(Boolean.FALSE);
        sysField.setAudit(Boolean.FALSE);
        for (Method method : clazz.getMethods()) {
            if (method.getName().equalsIgnoreCase("get" + filedName)) {
                sysField.setFieldName(filedName);
                sysField.setClazzName(method.getReturnType().getName());

                Column column = method.getAnnotation(Column.class);
                OneToMany oneToMany = method.getAnnotation(OneToMany.class);
                ManyToOne manyToOne = method.getAnnotation(ManyToOne.class);
                JoinColumn joinColumn = method.getAnnotation(JoinColumn.class);
                Temporal temporal = method.getAnnotation(Temporal.class);
                CreatedBy createdBy = method.getAnnotation(CreatedBy.class);
                CreatedDate createdDate = method.getAnnotation(CreatedDate.class);
                LastModifiedBy lastModifiedBy = method.getAnnotation(LastModifiedBy.class);
                LastModifiedDate lastModifiedDate = method.getAnnotation(LastModifiedDate.class);

                if (column != null) {
                    sysField.setColumnName(column.name());
                    sysField.setUniqueValue(column.unique());
                    sysField.setNullable(column.nullable());
                    sysField.setInsertable(column.insertable());
                    sysField.setUpdatable(column.updatable());
                    sysField.setLengthValue(column.length());
                    sysField.setPrecisionValue(column.precision());
                    sysField.setScale(column.scale());
                    sysField.setColumnTypeCode(ColumnType.COLUMN.name());
                    if (method.getReturnType() == Date.class) {
                        if (temporal != null) {
                            sysField.setTemporalType(temporal.value().name());
                        } else {
                            sysField.setTemporalType(TemporalType.TIMESTAMP.name());
                        }
                    }
                    if (createdBy != null ||
                            createdDate != null ||
                            lastModifiedBy != null ||
                            lastModifiedDate != null) {
                        sysField.setAudit(Boolean.TRUE);
                    }

                } else if (oneToMany != null) {
                    sysField.setMappedBy(oneToMany.mappedBy());
                    sysField.setColumnTypeCode(ColumnType.ONE_TO_MANY.name());

                    if (ParentAbleIdEntity.class.isAssignableFrom(clazz) && method.getName().equals("getChildren")) {
                        sysField.setRelationSysEntity(sysEntityRepository.findByClazzName(clazz.getName()));
                        sysField.setTree(Boolean.TRUE);
                    } else {
                        sysField.setRelationSysEntity(sysEntityRepository.findByClazzName(takeReturnSetBaseType(method)));

                        if (takeReturnSetBaseType(method).indexOf("Relation") > 1) {
                            try {
                                Class nestClazz = Class.forName(takeReturnSetBaseType(method));
                                String[] nameArray = nestClazzSplit(nestClazz.getSimpleName());
                                String nestRelationName = nameArray[0];
                                if (nestRelationName.equals(clazz.getSimpleName())) {
                                    nestRelationName = nameArray[1];
                                }
                                Class nestRelationClazz = nestClazz.getMethod("get" + nestRelationName).getReturnType();
                                sysField.setNestClazzName(nestClazz.getName());
                                sysField.setRelationSysEntity(sysEntityRepository.findByClazzName(nestRelationClazz.getName()));
                            } catch (Exception e) {
                                e.printStackTrace();
                                //ignore
                            }
                        }
                    }

                } else if (manyToOne != null && joinColumn != null) {
                    sysField.setColumnName(joinColumn.name());
                    sysField.setUniqueValue(joinColumn.unique());
                    sysField.setNullable(joinColumn.nullable());
                    sysField.setInsertable(joinColumn.insertable());
                    sysField.setUpdatable(joinColumn.updatable());
                    if (ParentAbleIdEntity.class.isAssignableFrom(clazz) && joinColumn.name().equals("parent_id")) {
                        sysField.setTree(Boolean.TRUE);
                        sysField.setClazzName(clazz.getName());
                    }
                    sysField.setRelationSysEntity(sysEntityRepository.findByClazzName(sysField.getClazzName()));
                    sysField.setColumnTypeCode(ColumnType.MANY_TO_ONE.name());
                }

                break;
            }
        }

        return sysField;
    }

    public static String[] nestClazzSplit(String clazzName) {
        String sp[] = clazzName.split("Relation");
        int lastCap = 0;
        for (int i = 0; i < sp[0].length(); ++i) {
            if (sp[0].charAt(i) >= 'A' && sp[0].charAt(i) <= 'Z') {
                lastCap = i;
            }
        }
        return new String[]{sp[0].substring(0, lastCap), sp[1], sp[0].substring(lastCap, sp[0].length())};
    }

    private String takeReturnSetBaseType(Method method) {
        Type type = ((ParameterizedType) method.getGenericReturnType()).getActualTypeArguments()[0];
        String className = type.toString().substring("class ".length());
        return className;
    }

    /**
     * 分析字节码文件中能得到的描述信息
     *
     * @param clazz
     * @return
     */
    private SysEntity analyBaseSysEntity(Class clazz) {
        SysEntity sysEntity = new SysEntity();
        sysEntity.setClazzName(clazz.getName());
        Table table = (Table) clazz.getAnnotation(Table.class);
        sysEntity.setTableName(table.name());

        if (ParentAbleIdEntity.class.isAssignableFrom(clazz)) {
            sysEntity.setTreeAble(Boolean.TRUE);
        } else {
            sysEntity.setTreeAble(Boolean.FALSE);
        }

        if (NameAbleIdEntity.class.isAssignableFrom(clazz)) {
            sysEntity.setNameAble(Boolean.TRUE);
        } else {
            sysEntity.setNameAble(Boolean.FALSE);
        }

        if (CodeAbleIdEntity.class.isAssignableFrom(clazz)) {
            sysEntity.setCodeAble(Boolean.TRUE);
        } else {
            sysEntity.setCodeAble(Boolean.FALSE);
        }

        return sysEntity;
    }

    public void hello() {

    }

}
