package ${basepackage}.converter;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * ${sysEntity.cname} Entity to Model Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on ${createTime} .
*/
public class ${className}EntityTo${modelName}Converter implements org.springframework.cglib.core.Converter {

	private Boolean eager;

	public ${className}EntityTo${modelName}Converter(Boolean eager){
		this.eager = eager;
	}

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public Object convert(Object value, Class target, Object context) {
        <#list sysEntity.sysFieldCollection as sysField>
        <#if sysField.columnTypeCode == "ONE_TO_MANY" >
        if (context.toString().equals("set${sysField.fieldName?cap_first}")) {
            if(eager){
	           java.util.Collection<<#if sysField.nestClazzName??>${sysField.nestClazzName}<#else >${sysField.relationSysEntity.clazzName}</#if>> ${sysField.fieldName} = (java.util.Collection<<#if sysField.nestClazzName??>${sysField.nestClazzName}<#else >${sysField.relationSysEntity.clazzName}</#if>>) value;
	           if (${sysField.fieldName} != null && ${sysField.fieldName}.size() > 0) {
	               java.util.List<java.lang.Long> ${sysField.fieldName}IdList = new java.util.ArrayList<java.lang.Long>(${sysField.fieldName}.size());
	               for (<#if sysField.nestClazzName??>${sysField.nestClazzName}<#else >${sysField.relationSysEntity.clazzName}</#if> relation${sysField.fieldName?cap_first}: ${sysField.fieldName}) {
	                   if (relation${sysField.fieldName?cap_first} != null) {
	                       ${sysField.fieldName}IdList.add(relation${sysField.fieldName?cap_first}.get<#if sysField.nestClazzName??>${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)}</#if>Id());
	                   }
	               }
	               return ${sysField.fieldName}IdList;
	           } else {
	               return null;
	           }
            }else{
            	return null;
            }
        }
        </#if>
        </#list>
        return value;
    }
}