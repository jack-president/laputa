<#if sysEntity.treeAble  >
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/${project}/${className?lower_case}/create" var="${className?uncap_first}CreateUrl"/>
<c:url value="/${project}/${className?lower_case}/readEager" var="${className?uncap_first}ReadEagerUrl"/>
<c:url value="/${project}/${className?lower_case}/update" var="${className?uncap_first}UpdateUrl"/>
<c:url value="/${project}/${className?lower_case}/destory" var="${className?uncap_first}DestroyUrl"/>


<#list referenceSysEntityList as referenceSysEntity>
<#if sysEntity.id != referenceSysEntity.id >
<c:url value="/${CodeFileService.takeProject(referenceSysEntity.clazzName)}/${CodeFileService.takeSimpleClassName(referenceSysEntity.clazzName)?lower_case}/read" var="${CodeFileService.takeSimpleClassName(referenceSysEntity.clazzName)?uncap_first}ReadUrl"/>
</#if>
</#list>

<!DOCTYPE html>
<html>
<head>
    <title>${sysEntity.cname}列表</title>
    <meta charset="UTF-8">
    <#list sysEntity.sysFieldCollection as sysField>
    <#if sysField.columnTypeCode == "ONE_TO_MANY"  >
    <link href="<c:url value='/static/kendo/third/LaputaTreeViewCheckboxes/style/laputa.treeview.checkboxes.css'/>" rel="stylesheet">
    <#break >
    </#if>
    </#list>
    <jsp:include page="/WEB-INF/views/include/style/kendoStyle.jsp"/>
    <jsp:include page="/WEB-INF/views/include/js/requirejs.jsp"/>
</head>
<body>

<div id="${classNameUncap}TreeList"/>

<script>
    laputaKendoRequire(["kendo/js/kendo.treelist" ${laputaKendoRequire} ], function () {

        <#list referenceSysEntityList as referenceSysEntity>
        <#if sysEntity.id != referenceSysEntity.id >
        var ${CodeFileService.takeSimpleClassName(referenceSysEntity.clazzName)?uncap_first}DataSource = kendo.laputa.util.buildReadDataSource("${'$'}{${CodeFileService.takeSimpleClassName(referenceSysEntity.clazzName)?uncap_first}ReadUrl}");
        </#if>
        </#list>

        var initFun = function() {

            var ${classNameUncap}DataSource = new kendo.data.TreeListDataSource({
                error: kendo.laputa.util.dataSourceErrorHandle,
                transport: {
                    read: kendo.laputa.util.transportUrlBuild("${'$'}{${className?uncap_first}ReadEagerUrl}"),
                    update: kendo.laputa.util.transportUrlBuild("${'$'}{${className?uncap_first}UpdateUrl}"),
                    destroy: kendo.laputa.util.transportUrlBuild("${'$'}{${className?uncap_first}DestroyUrl}"),
                    create: kendo.laputa.util.transportUrlBuild("${'$'}{${className?uncap_first}CreateUrl}"),
                    parameterMap: kendo.laputa.util.parameterMap
                },
                schema: {
                    parse: kendo.laputa.util.treeResponseExpandedParse,
                    model: {
                        id: "id",
                        fields: {
                            <#list sysEntity.sysFieldCollection as sysField>
                            <#if sysField.columnTypeCode == "COLUMN">
                            ${sysField.fieldName}: {editable: <#if sysField.updatable>true<#else>false</#if><#if !sysField.nullable>,nullable: false</#if><#if sysField.fieldName == "id" >,defaultValue: null</#if><#if sysField.temporalType?? >,type: "date"</#if>}<#if sysField_has_next>,</#if>
                            <#elseif sysField.columnTypeCode == "ONE_TO_MANY">
                            ${sysField.fieldName}: {defaultValue: null}<#if sysField_has_next>,</#if>
                            <#elseif sysField.columnTypeCode == "MANY_TO_ONE">
                            <#if sysField.tree>
                            parentId: {type: "number", nullable: true}<#if sysField_has_next>,</#if>
                            <#else>
                            ${sysField.fieldName}Id: {<#if !sysField.updatable>editable: false</#if> <#if !sysField.nullable>,nullable: false</#if>}<#if sysField_has_next>,</#if>
                            </#if>
                            </#if>
                            </#list>
                        }
                    }
                }
            });

            $("#${className?uncap_first}TreeList").kendoTreeList({
                dataSource: ${classNameUncap}DataSource,
                toolbar: ["create"],
                columns: [
                    <#list sysEntity.sysFieldCollection as sysField>
                    <#if sysField.showable >
                    <#if sysField.columnTypeCode == "COLUMN">
                            {field: "${sysField.fieldName}", title: "${sysField.cname}", width: ${sysField.showwidth}<#if sysField.temporalType?? ><#if sysField.temporalType == "TIME" >,format: "{0:HH:mm:ss tt}",editor:kendo.laputa.util.timePickerEditorBuild()<#elseif sysField.temporalType == "DATE" >,format: "{0:yyyy-MM-dd}",editor:kendo.laputa.util.datePickerEditorBuild()<#elseif sysField.temporalType == "TIMESTAMP" >,format: "{0:yyyy-MM-dd HH:mm:ss tt}",editor:kendo.laputa.util.dateTimePickerEditorBuild()</#if></#if>},
                    <#elseif sysField.columnTypeCode == "ONE_TO_MANY">
                            {field: "${sysField.fieldName}", title: "${sysField.cname}", width: ${sysField.showwidth}, sortable: false, template: kendo.laputa.util.buildEntityJoinTemplate("${sysField.fieldName}",${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)?uncap_first}DataSource<#if sysEntity.id != sysField.relationSysEntityId >.data()</#if>),editor:kendo.laputa.util.buildHierarchicaEditor(${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)?uncap_first}DataSource<#if sysEntity.id != sysField.relationSysEntityId >.data()</#if>)},
                    <#elseif sysField.columnTypeCode == "MANY_TO_ONE">
                        {field: "<#if sysField.tree>parentId<#else>${sysField.fieldName}Id</#if>", title: "${sysField.cname}", width: ${sysField.showwidth}, template: kendo.laputa.util.buildEntityJoinTemplate("<#if sysField.tree>parentId<#else>${sysField.fieldName}Id</#if>",${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)?uncap_first}DataSource<#if sysEntity.id != sysField.relationSysEntityId >.data()</#if>),editor:kendo.laputa.util.buildHierarchicaSelectInputEditor(${CodeFileService.takeSimpleClassName(sysField.relationSysEntity.clazzName)?uncap_first}DataSource<#if sysEntity.id != sysField.relationSysEntityId >.data()</#if>)},
                    </#if>
                    </#if>
                    </#list>
                    {command: ["edit", "destroy"], width: 220}
                ],
                editable: "inline"
            });

        }

        kendo.laputa.util.dataSourceFetchQuen([<#list referenceSysEntityList as referenceSysEntity><#if sysEntity.id != referenceSysEntity.id >${CodeFileService.takeSimpleClassName(referenceSysEntity.clazzName)?uncap_first}DataSource<#if referenceSysEntity_has_next>,</#if></#if></#list>], initFun);

    });

</script>

</body>
</html>
</#if>