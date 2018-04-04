package ${basepackage}.controller;

import ${basepackage}.entity.${className};
import ${basepackage}.model.${modelName};
import com.laputa.foundation.web.kendo.model.DataSourceRequest;
import com.laputa.foundation.web.kendo.model.DataSourceResult;
import ${basepackage}.service.${className}Service;
import ${basepackage}.converter.${className}BeanCopier;


import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;


/**
 * <p>
 * ${sysEntity.cname} Controller<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on ${createTime} .
*/
@Controller
@RequestMapping("/${project}/${className?lower_case}")
public class ${className}Controller {

    @Resource
    private ${className}Service ${classNameUncap}Service;

    <#if sysEntity.treeAble>
    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    public String treeList() {
        return "/${project}/${className?lower_case}/treeList";
    }
    </#if>

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "/${project}/${className?lower_case}/list";
    }

    @ResponseBody
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public List<${modelName}> read() {
        List<${className}> ${className?uncap_first}List = ${className?uncap_first}Service.read();
        List<${modelName}> ${modelName?uncap_first}List = ${className}BeanCopier.${className?uncap_first}EntityTo${modelName}(${className?uncap_first}List);
        return ${modelName?uncap_first}List != null ? ${modelName?uncap_first}List : Collections.<${modelName}>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readEager", method = {RequestMethod.GET,RequestMethod.POST})
    public List<${modelName}> readEager() {
        List<${className}> ${className?uncap_first}List = ${className?uncap_first}Service.readEager();
        List<${modelName}> ${modelName?uncap_first}List = ${className}BeanCopier.${className?uncap_first}EntityTo${modelName}Eager(${className?uncap_first}List);
        return ${modelName?uncap_first}List != null ? ${modelName?uncap_first}List : Collections.<${modelName}>emptyList();
    }

    @ResponseBody
    @RequestMapping(value = "/readDataSource", method = RequestMethod.POST)
    public DataSourceResult readDataSource(@RequestBody DataSourceRequest dataSourceRequest) {
        Page<${className}> ${className?uncap_first}Page = ${className?uncap_first}Service.readDataSource(dataSourceRequest);
        List<${modelName}> ${modelName?uncap_first}List = ${className}BeanCopier.${className?uncap_first}EntityTo${modelName}Eager(${className?uncap_first}Page.getContent());
        DataSourceResult dataSourceResult = new DataSourceResult(${modelName?uncap_first}List, ${className?uncap_first}Page.getTotalElements());
        return dataSourceResult;
    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ${modelName} create(@RequestBody ${modelName} ${modelName?uncap_first}) {
        ${className} ${className?uncap_first} = ${className}BeanCopier.${modelName?uncap_first}To${className}Entity(${modelName?uncap_first});
        ${className} create${className?cap_first} = ${className?uncap_first}Service.create(${className?uncap_first});
        ${modelName?uncap_first}.setId(create${className?cap_first}.getId());
        return ${modelName?uncap_first};
    }

    @ResponseBody
    @RequestMapping(value = "/destory", method = RequestMethod.POST)
    public ${modelName} destory(@RequestBody ${modelName} ${modelName?uncap_first}) {
        ${className} ${className?uncap_first} = ${className}BeanCopier.${modelName?uncap_first}To${className}Entity(${modelName?uncap_first});
        ${className?uncap_first}Service.destory(${className?uncap_first});
        return ${modelName?uncap_first};
    }


    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ${modelName} update(@RequestBody ${modelName} ${modelName?uncap_first}) {
        ${className} ${className?uncap_first} = ${className}BeanCopier.${modelName?uncap_first}To${className}Entity(${modelName?uncap_first});
        ${className?uncap_first}Service.update(${className?uncap_first});
        return ${modelName?uncap_first};
    }
}