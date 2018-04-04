package ${basepackage}.service;

<#list sysEntity.sysFieldCollection as sysField>
<#if sysField.columnTypeCode == "ONE_TO_MANY" && !sysField.nestClazzName?? >
import com.laputa.foundation.persistence.util.IdEntityDiffUtil;
<#break >
</#if>
</#list>
<#list sysEntity.sysFieldCollection as sysField>
<#if sysField.relationSysEntityId?? && sysEntity.id == sysField.relationSysEntityId >
import com.laputa.foundation.persistence.util.ParentAbleIdEntityCheckCircularReferenceUtil;
<#break >
</#if>
</#list>
import ${basepackage}.entity.${className};
import ${basepackage}.dao.${className}JpaRepository;
<#list referenceSysEntityList as referenceSysEntity>
<#if referenceSysEntity.id != sysEntity.id>
import ${referenceSysEntity.clazzName};
import ${CodeFileService.takeBasepackage(referenceSysEntity.clazzName)}.dao.${CodeFileService.takeSimpleClassName(referenceSysEntity.clazzName)}JpaRepository;
</#if>
<#list sysEntity.sysFieldCollection as sysField>
<#if sysField.nestClazzName??>
import ${CodeFileService.takeBasepackage(sysField.nestClazzName)}.dao.${CodeFileService.takeSimpleClassName(sysField.nestClazzName)}JpaRepository;
</#if>
</#list>
</#list>
<#list nestClazzNameMap?keys as key>
import ${key};
</#list>
<#list nestClazzNameMap?keys as key>

import com.laputa.foundation.common.CollectionDiffUtil;
<#break >
</#list>
import com.laputa.foundation.web.exception.LaputaWebOperationException;
import com.laputa.foundation.web.kendo.model.DataSourceRequest;
import com.laputa.foundation.web.kendo.specification.LaputaKendoSpecification;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

<#list nestClazzNameMap?keys as key>
import java.util.Collection;
<#break >
</#list>
import java.util.List;

/**
* <p>
* ${sysEntity.cname} Service<br>
* /p>
* Created by JiangDongPing CodeGnerate on ${createTime} .
*/
@Transactional
@Service("${classNameUncap}Service")
public class ${className}Service {

    @Resource
    private ${className}JpaRepository ${classNameUncap}JpaRepository;

    <#list referenceSysEntityList as referenceSysEntity>
    <#if referenceSysEntity.id != sysEntity.id>

    @Resource
    private ${CodeFileService.takeSimpleClassName(referenceSysEntity.clazzName)}JpaRepository ${CodeFileService.takeSimpleClassName(referenceSysEntity.clazzName)?uncapFirst}JpaRepository;
    </#if>
    </#list>
    <#list sysEntity.sysFieldCollection as sysField>
    <#if sysField.nestClazzName??>

    @Resource
    private ${CodeFileService.takeSimpleClassName(sysField.nestClazzName)}JpaRepository ${CodeFileService.takeSimpleClassName(sysField.nestClazzName)?uncapFirst}JpaRepository;
    </#if>
    </#list>

    @Transactional
    public ${className} create(${className} ${classNameUncap}) {

        ${className} create${className} = new ${className}();

        <#list sysEntity.sysFieldCollection as sysField>
        <#if sysField.columnTypeCode == "COLUMN" && sysField.insertable && !sysField.audit>
        create${className}.set${sysField.fieldName?capFirst}(${classNameUncap}.get${sysField.fieldName?capFirst}());
        <#elseIf sysField.columnTypeCode == "MANY_TO_ONE">
        <#if sysField.tree >
        if( ${classNameUncap}.getParentId() != null ){
        ${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)} ${sysField.fieldName} = ${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)?uncapFirst}JpaRepository.findById(${classNameUncap}.getParentId())
                .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_PARENT_NONEXISTENT
                    .generateException("[ ${sysField.cname}{0} ] 无法配置 因为无此记录 可能已经被删除", ${classNameUncap}.getParentId()));
            create${className}.set${sysField.fieldName?capFirst}(${sysField.fieldName});
        }
        <#else >
        if( ${classNameUncap}.get${sysField.fieldName?capFirst}Id() != null ){
            ${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)} ${sysField.fieldName} = ${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)?uncapFirst}JpaRepository.findById(${classNameUncap}.get${sysField.fieldName?capFirst}Id())
                    .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                        .generateException("[ ${sysField.cname}{0} ] 无法配置 因为无此记录 可能已经被删除", ${classNameUncap}.get${sysField.fieldName?capFirst}Id()));
            create${className}.set${sysField.fieldName?capFirst}(${sysField.fieldName});
        }
        </#if>
        <#if !sysField.nullable >else{
            throw LaputaWebOperationException.ExceptionEnum.RELATION_FIELD_MUST_EXIS.generateException("${sysEntity.cname} ${sysField.cname} ${sysField.relationSysEntity.cname} 需要被配置");
        }</#if>

        </#if>
        </#list>

        create${className} = ${classNameUncap}JpaRepository.save(create${className});

        <#list sysEntity.sysFieldCollection as sysField>
        <#if sysField.columnTypeCode == "ONE_TO_MANY">
        processOneToMany${sysField.fieldName?capFirst}(${classNameUncap},create${className});
        </#if>
        </#list>

        return create${className};
    }

    @Transactional
    public void destory(${className} ${classNameUncap}) {
        ${className} destory${className} = ${classNameUncap}JpaRepository.findById(${classNameUncap}.getId())
            .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.DESTORY_TARGET_NONEXISTENT
                .generateException("${sysEntity.cname} [ {0} ] 无法删除 因为无此记录 可能已经被删除", ${classNameUncap}.getId()));
        ${classNameUncap}JpaRepository.delete(destory${className});
    }


    @Transactional
    public ${className} update(${className} ${classNameUncap}) {
        ${className} update${className} = ${classNameUncap}JpaRepository.findById(${classNameUncap}.getId())
            .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.UPDATE_TARGET_NONEXISTENT
                .generateException("${sysEntity.cname} [ {0} ] 无法编辑 因为无此记录 可能已经被删除", ${classNameUncap}.getId()));

        <#list sysEntity.sysFieldCollection as sysField>
        <#if sysField.columnTypeCode == "COLUMN" && sysField.updatable && !sysField.audit>
        update${className}.set${sysField.fieldName?capFirst}(${classNameUncap}.get${sysField.fieldName?capFirst}());
        <#elseIf sysField.columnTypeCode == "MANY_TO_ONE">
        <#if sysField.tree >
        if( ${classNameUncap}.getParentId() != null ){
            if(!${classNameUncap}.getParentId().equals(update${className}.getParentId()) ){
                ${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)} ${sysField.fieldName} = ${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)?uncapFirst}JpaRepository.findById(${classNameUncap}.getParentId())
                    .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_PARENT_NONEXISTENT
                        .generateException("${sysEntity.cname} ${sysField.cname} ${sysField.relationSysEntity.cname} [ {0} ] 无法更新 因为无此记录 可能已经被删除", ${classNameUncap}.getParentId()));

                if(ParentAbleIdEntityCheckCircularReferenceUtil.checkCircularReference(${classNameUncap}.getId(),${sysField.fieldName})){
                    throw LaputaWebOperationException.ExceptionEnum.RELATION_EXIS_CIRCLE.generateException( "{0}  同 {1} 存在嵌套关系",update${className}.getCname(),${sysField.fieldName}.getCname());
                }
                update${className}.set${sysField.fieldName?capFirst}(${sysField.fieldName});
        }
        <#else >
        if( ${classNameUncap}.get${sysField.fieldName?capFirst}Id() != null ){
            if(!${classNameUncap}.get${sysField.fieldName?capFirst}Id().equals(update${className}.get${sysField.fieldName?capFirst}Id()) ){
                ${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)} ${sysField.fieldName} = ${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)?uncapFirst}JpaRepository.findById(${classNameUncap}.get${sysField.fieldName?capFirst}Id())
                    .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                        .generateException("${sysEntity.cname} ${sysField.cname} ${sysField.relationSysEntity.cname} [ {0} ] 无法更新 因为无此记录 可能已经被删除", ${classNameUncap}.get${sysField.fieldName?capFirst}Id()));
                update${className}.set${sysField.fieldName?capFirst}(${sysField.fieldName});
        }
        </#if>
        }else{
            <#if sysField.nullable >
            update${className}.set${sysField.fieldName?capFirst}(null);
            <#else>
            throw new DefaultLaputaException("${sysField.cname} 需要被配置");
            </#if>
        }

        </#if>
        </#list>

        ${classNameUncap}JpaRepository.save(update${className});

        <#list sysEntity.sysFieldCollection as sysField>
        <#if sysField.columnTypeCode == "ONE_TO_MANY">
        processOneToMany${sysField.fieldName?capFirst}(${classNameUncap},update${className});
        </#if>
        </#list>

        return update${className};
    }


    @Transactional
    public List<${className}> read() {
        List<${className}> ${classNameUncap}List = ${classNameUncap}JpaRepository.findAll();
        return ${classNameUncap}List;
    }

    @Transactional
    public List<${className}> readEager() {
        List<${className}> ${classNameUncap}List = ${classNameUncap}JpaRepository.findAll();
        if( ${classNameUncap}List != null && ${classNameUncap}List.size() > 0 ){
            for( ${className} ${classNameUncap} :  ${classNameUncap}List ){
                <#list sysEntity.sysFieldCollection as sysField>
                <#if sysField.columnTypeCode == "ONE_TO_MANY" >
                ${classNameUncap}.get${sysField.fieldName?capFirst}().size();
                </#if>
                </#list>
            }
        }
        return ${classNameUncap}List;
    }


    @Transactional
    public Page<${className}> readDataSource(DataSourceRequest dataSourceRequest) {
        LaputaKendoSpecification<${className}> laputaKendoSpecification = new LaputaKendoSpecification<>(dataSourceRequest);
        Page<${className}> ${classNameUncap}Page = ${classNameUncap}JpaRepository.findAll(laputaKendoSpecification, laputaKendoSpecification.pageable());
        if (${classNameUncap}Page != null && ${classNameUncap}Page.getContent() != null && ${classNameUncap}Page.getContent().size() > 0) {
            for (${className} ${classNameUncap} : ${classNameUncap}Page.getContent()) {
                <#list sysEntity.sysFieldCollection as sysField>
                <#if sysField.columnTypeCode == "ONE_TO_MANY" >
                ${classNameUncap}.get${sysField.fieldName?capFirst}().size();
                </#if>
                </#list>
            }
        }
        return ${classNameUncap}Page;
    }

    <#list nestClazzNameMap?keys as key>
    CollectionDiffUtil.Compare<${CodeFileService.takeSimpleClassName(key)}> ${CodeFileService.takeSimpleClassName(key)?uncapFirst}Compare = new CollectionDiffUtil.Compare<${CodeFileService.takeSimpleClassName(key)}>() {
            @Override
            public Boolean equal(${CodeFileService.takeSimpleClassName(key)} left, ${CodeFileService.takeSimpleClassName(key)} right) {
                return left.get${CodeFileService.takeSimpleClassName(nestClazzNameMap[key].clazzName)}Id().equals(right.get${CodeFileService.takeSimpleClassName(nestClazzNameMap[key].clazzName)}Id());
            }
        };
    </#list>


    <#list sysEntity.sysFieldCollection as sysField>
    <#if sysField.columnTypeCode == "ONE_TO_MANY">
    @Transactional
    private void processOneToMany${sysField.fieldName?capFirst}(${className} ${classNameUncap}, ${className} persisted${className}) {
        <#if sysField.nestClazzName?? >
        Collection<${CodeFileService.takeSimpleClassName(sysField.nestClazzName)}> needClear${sysField.fieldName} = CollectionDiffUtil.inLeftButNotInRiht(persisted${className}.get${sysField.fieldName?capFirst}(),${classNameUncap}.get${sysField.fieldName?capFirst}(),${CodeFileService.takeSimpleClassName(sysField.nestClazzName)?uncapFirst}Compare);
        <#else >
        Collection<${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)}> needClear${sysField.fieldName} = IdEntityDiffUtil.inLeftButNotInRiht(persisted${className}.get${sysField.fieldName?capFirst}(),${classNameUncap}.get${sysField.fieldName?capFirst}());
        </#if>
        if (needClear${sysField.fieldName} != null && needClear${sysField.fieldName}.size() > 0) {
            <#if sysField.nestClazzName?? >
            ${CodeFileService.takeSimpleClassName(sysField.nestClazzName)?uncapFirst}JpaRepository.deleteAll(needClear${sysField.fieldName});
            <#else >
            for(${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)} persisted${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)?uncapFirst} : needClear${sysField.fieldName} ){
                persisted${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)?uncapFirst}.set${sysField.mappedBy?capFirst}(null);
            }
            </#if>
            persisted${className}.get${sysField.fieldName?capFirst}().removeAll(needClear${sysField.fieldName});
        }

        <#if sysField.nestClazzName?? >
        Collection<${CodeFileService.takeSimpleClassName(sysField.nestClazzName)}> needProcess${sysField.fieldName?capFirst} = CollectionDiffUtil.inLeftButNotInRiht(${classNameUncap}.get${sysField.fieldName?capFirst}(),persisted${className}.get${sysField.fieldName?capFirst}(),${CodeFileService.takeSimpleClassName(sysField.nestClazzName)?uncapFirst}Compare);
        <#else >
        Collection<${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)}> needProcess${sysField.fieldName?capFirst} = IdEntityDiffUtil.inLeftButNotInRiht(${classNameUncap}.get${sysField.fieldName?capFirst}(),persisted${className}.get${sysField.fieldName?capFirst}());
        </#if>
        if(needProcess${sysField.fieldName?capFirst} != null && needProcess${sysField.fieldName?capFirst}.size() > 0){
            <#if sysField.nestClazzName?? >
            for( ${CodeFileService.takeSimpleClassName(sysField.nestClazzName)} needProcess${CodeFileService.takeSimpleClassName(sysField.nestClazzName)} :  needProcess${sysField.fieldName?capFirst}){
                ${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)} persistedNeedProcess${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)} = ${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)?uncapFirst}JpaRepository.findById(needProcess${CodeFileService.takeSimpleClassName(sysField.nestClazzName)}.get${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)}Id())
                    .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                        .generateException("${sysField.relationSysEntity.cname} [ {0} ] 不存在", needProcess${CodeFileService.takeSimpleClassName(sysField.nestClazzName)}.get${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)}Id()));

                    ${CodeFileService.takeSimpleClassName(sysField.nestClazzName)} ${CodeFileService.takeSimpleClassName(sysField.nestClazzName)?uncapFirst} = new ${CodeFileService.takeSimpleClassName(sysField.nestClazzName)}();
                    ${CodeFileService.takeSimpleClassName(sysField.nestClazzName)?uncapFirst}.set${className}(persisted${className});
                    ${CodeFileService.takeSimpleClassName(sysField.nestClazzName)?uncapFirst}.set${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)}(persistedNeedProcess${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)});
                    ${CodeFileService.takeSimpleClassName(sysField.nestClazzName)?uncapFirst}JpaRepository.save(${CodeFileService.takeSimpleClassName(sysField.nestClazzName)?uncapFirst});
            }
            <#else >
            for( ${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)} needProcess${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)} :  needProcess${sysField.fieldName?capFirst}){
                ${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)} persistedNeedProcess${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)} = ${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)?uncapFirst}JpaRepository.findById(needProcess${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)}.getId())
                    .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                        .generateException("${sysField.relationSysEntity.cname} [ {0} ] 不存在", needProcess${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)}.getId()));
                <#if sysField.tree>
                if(ParentAbleIdEntityCheckCircularReferenceUtil.checkCircularReference(persistedNeedProcess${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)}.getId(),persisted${className})){
                    throw LaputaWebOperationException.ExceptionEnum.RELATION_EXIS_CIRCLE.generateException("{0}  同 {1} 存在嵌套关系", persistedNeedProcess${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)}.getCname(),persisted${className}.getCname());
                }
                </#if>
                persistedNeedProcess${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)}.set${sysField.mappedBy?capFirst}(persisted${className});
            }
            </#if>
        }
    }
    </#if>
    </#list>
}