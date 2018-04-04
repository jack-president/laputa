package ${basepackage}.converter;

import java.util.List;


/**
 * <p>
 * ${sysEntity.cname} Model to Entity Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on ${createTime} .
*/
public class ${modelName}To${className}EntityConverter implements org.springframework.cglib.core.Converter {

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public Object convert(Object value, Class target, Object context) {
        <#list sysEntity.sysFieldCollection as sysField>
        <#if sysField.columnTypeCode == "ONE_TO_MANY" >
        if (context.toString().equals("set${sysField.fieldName?cap_first}")) {
            java.util.List<java.lang.Long> ${sysField.fieldName}IdList = (java.util.List<java.lang.Long>) value;
            if (${sysField.fieldName}IdList != null && ${sysField.fieldName}IdList.size() > 0) {
                <#if sysField.clazzName == "java.util.Set">
                java.util.Set<<#if sysField.nestClazzName??>${sysField.nestClazzName}<#else >${sysField.relationSysEntity.clazzName}</#if>> ${sysField.fieldName} = new java.util.HashSet<>(${sysField.fieldName}IdList.size());
                <#elseif sysField.clazzName == "java.util.LinkedHashSet" >
                java.util.LinkedHashSet<<#if sysField.nestClazzName??>${sysField.nestClazzName}<#else >${sysField.relationSysEntity.clazzName}</#if>> ${sysField.fieldName} = new java.util.LinkedHashSet<>(${sysField.fieldName}IdList.size());
                <#elseif sysField.clazzName == "java.util.List" >
                java.util.List<<#if sysField.nestClazzName??>${sysField.nestClazzName}<#else >${sysField.relationSysEntity.clazzName}</#if>> ${sysField.fieldName} = new java.util.ArrayList<>(${sysField.fieldName}IdList.size());
                </#if>
                for (Long ${sysField.fieldName}Id : ${sysField.fieldName}IdList) {
                    <#if sysField.nestClazzName??>${sysField.nestClazzName}<#else >${sysField.relationSysEntity.clazzName}</#if> relation${sysField.fieldName?cap_first} = new <#if sysField.nestClazzName??>${sysField.nestClazzName}<#else >${sysField.relationSysEntity.clazzName}</#if>();
                    <#if sysField.columnTypeCode == "ONE_TO_MANY">
                    relation${sysField.fieldName?cap_first}.set<#if sysField.nestClazzName??>${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)}</#if>Id(${sysField.fieldName}Id);
                    <#else >
                    <#--relation${sysField.relationSysEntity.className}.set${sysField.relationSysEntity.className}Id(${sysField.relationSysEntity.className?uncap_first}Id);-->
                    </#if>
                    ${sysField.fieldName}.add(relation${sysField.fieldName?cap_first});
                }
                return ${sysField.fieldName};
            } else {
                return null;
            }
        }
        </#if>
        </#list>
        return value;
    }
}