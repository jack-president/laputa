package ${basepackage}.dao;

<#list nestClazzNameMap?keys as key>
import java.util.List;
<#break >
</#list>

import ${basepackage}.entity.${className};
<#list nestClazzNameMap?keys as key>
import ${nestClazzNameMap[key].clazzName};
</#list>

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * <p>
 * ${sysEntity.cname} Jpa Repository<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on ${createTime} .
 */
public interface ${className}JpaRepository extends JpaRepository<${className}, java.lang.Long>, JpaSpecificationExecutor<${className}> {
	<#if sysEntity.codeAble >

	@Query("SELECT ${className?uncap_first} FROM ${className} ${className?uncap_first} WHERE ${className?uncap_first}.code=:code")
	${className} selectByCode(@Param(value = "code") String code);

	</#if>
	<#list sysEntity.sysFieldCollection as sysField>
	<#if sysField.nestClazzName??>
	@Query("SELECT ${CodeFileService.takeSimpleClassName(sysField.nestClazzName)?uncap_first}.${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)?uncap_first} FROM ${CodeFileService.takeSimpleClassName(sysField.nestClazzName)} ${CodeFileService.takeSimpleClassName(sysField.nestClazzName)?uncap_first} where ${CodeFileService.takeSimpleClassName(sysField.nestClazzName)?uncap_first}.${className?uncap_first}Id=:${className?uncap_first}Id")
	List<${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)}> select${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)}ListBy${className}Id(@Param(value = "${className?uncap_first}Id") Long ${className?uncap_first}Id);
	</#if>
	</#list>
}