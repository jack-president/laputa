<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/web/laputaconfig/create" var="laputaConfigCreateUrl"/>
<c:url value="/web/laputaconfig/readDataSource" var="laputaConfigReadDataSourceUrl"/>
<c:url value="/web/laputaconfig/update" var="laputaConfigUpdateUrl"/>
<c:url value="/web/laputaconfig/destory" var="laputaConfigDestroyUrl"/>

<c:url value="/web/laputaaplication/read" var="laputaAplicationReadUrl"/>
<c:url value="/web/laputaconfighistory/read" var="laputaConfigHistoryReadUrl"/>

<!DOCTYPE html>
<html>
<head>
    <title>Laputa项目列表</title>
    <meta charset="UTF-8">
    <link href="<c:url value='/static/kendo/third/LaputaTreeViewCheckboxes/style/laputa.treeview.checkboxes.css'/>" rel="stylesheet">
    <jsp:include page="/WEB-INF/views/include/style/kendoStyle.jsp"/>
    <jsp:include page="/WEB-INF/views/include/js/requirejs.jsp"/>
</head>
<body>

<div id="laputaConfigGrid"/>

<script>
    laputaKendoRequire([ "kendo/js/kendo.grid" ,"kendo/third/dropdowntreeview/laputa.dropdown.treelist","kendo/third/LaputaTreeViewCheckboxes/laputa.treeview.checkboxes","kendo/js/kendo.datetimepicker" ], function () {


        var laputaAplicationDataSource = kendo.laputa.util.buildReadDataSource("${laputaAplicationReadUrl}");
        var laputaConfigHistoryDataSource = kendo.laputa.util.buildReadDataSource("${laputaConfigHistoryReadUrl}");

        var initFun = function() {

            var laputaConfigFilterDataSource = new kendo.data.DataSource({
                page: 1,
                pageSize: 20,
                serverPaging: true,
                serverSorting: true,
                serverFiltering: true,
                error: kendo.laputa.util.dataSourceErrorHandle,
                transport: {
                    read: kendo.laputa.util.transportUrlBuild("${laputaConfigReadDataSourceUrl}"),
                    update: kendo.laputa.util.transportUrlBuild("${laputaConfigUpdateUrl}"),
                    destroy: kendo.laputa.util.transportUrlBuild("${laputaConfigDestroyUrl}"),
                    create: kendo.laputa.util.transportUrlBuild("${laputaConfigCreateUrl}"),
                    parameterMap: kendo.laputa.util.parameterMap
                },
                schema: {
                    data: kendo.laputa.util.schemaData,
                    total: kendo.laputa.util.schemaTotal,
                    model: {
                        id: "id",
                        fields: {
                            id: {editable:  false  ,nullable: false,defaultValue: null},
                            cname: {editable:  true  ,nullable: false},
                            code: {editable:  true  ,nullable: false},
                            parentLaputaAplicationId:{defaultValue: null, },
                            configValue: {editable:  true  ,nullable: false},
                            configHistoryList: {defaultValue: null},
                            descript: {editable:  true  },
                            createdBy: {editable:  false  ,nullable: false},
                            createdDate: {editable:  false  ,nullable: false,type: "date"},
                            lastModifiedBy: {editable:  false  },
                            lastModifiedDate: {editable:  false  ,type: "date"}
                        }
                    }
                }
            });

            $("#laputaConfigGrid").kendoGrid({
                dataSource: laputaConfigFilterDataSource,
                toolbar: ["create"],
                columns: [
                            {field: "id", title: "主键", width: 180},
                            {field: "cname", title: "名称", width: 180},
                            {field: "code", title: "编码KEY", width: 180},
                        {field: "parentLaputaAplicationId", title: "所属项目", width: 180, filterable: kendo.laputa.util.filterableBuild(laputaAplicationDataSource.data()),template: kendo.laputa.util.buildEntityJoinTemplate("parentLaputaAplicationId",laputaAplicationDataSource.data()),editor:kendo.laputa.util.buildHierarchicaSelectInputEditor(laputaAplicationDataSource.data())},
                            {field: "configValue", title: "配置值", width: 180},
                            {field: "configHistoryList", title: "配置值历史", width: 180, sortable: false, filterable: kendo.laputa.util.multiFilterableBuild("configHistoryList",laputaConfigHistoryDataSource.data()), template: kendo.laputa.util.buildEntityJoinTemplate("configHistoryList",laputaConfigHistoryDataSource.data()),editor:kendo.laputa.util.buildHierarchicaEditor(laputaConfigHistoryDataSource.data())},
                            {field: "descript", title: "描述", width: 180},
                            {field: "createdBy", title: "创建用户", width: 180},
                            {field: "createdDate", title: "创建时间", width: 180,format: "{0:yyyy-MM-dd HH:mm:ss tt}",editor:kendo.laputa.util.dateTimePickerEditorBuild(),filterable: kendo.laputa.util.dateTimePickerFilterableBuild()},
                            {field: "lastModifiedBy", title: "最后修改用户", width: 180},
                            {field: "lastModifiedDate", title: "最后修改时间", width: 180,format: "{0:yyyy-MM-dd HH:mm:ss tt}",editor:kendo.laputa.util.dateTimePickerEditorBuild(),filterable: kendo.laputa.util.dateTimePickerFilterableBuild()},
                    {command: ["edit", "destroy"], width: 220}
                ],
                pageable: {
                    refresh: true,
                    pageSizes: true,
                    buttonCount: 5
                },
                filterable: {
                    multi: true
                },
                sortable: {
                    multi: true
                },

                editable: "inline"
            });

        }

        kendo.laputa.util.dataSourceFetchQuen([laputaAplicationDataSource,laputaConfigHistoryDataSource], initFun);

    });

</script>

</body>
</html>