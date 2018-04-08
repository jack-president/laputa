<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/web/laputaconfighistory/create" var="laputaConfigHistoryCreateUrl"/>
<c:url value="/web/laputaconfighistory/readDataSource" var="laputaConfigHistoryReadDataSourceUrl"/>
<c:url value="/web/laputaconfighistory/update" var="laputaConfigHistoryUpdateUrl"/>
<c:url value="/web/laputaconfighistory/destory" var="laputaConfigHistoryDestroyUrl"/>

<c:url value="/web/laputaconfig/read" var="laputaConfigReadUrl"/>

<!DOCTYPE html>
<html>
<head>
    <title>Laputa项目列表</title>
    <meta charset="UTF-8">
    <jsp:include page="/WEB-INF/views/include/style/kendoStyle.jsp"/>
    <jsp:include page="/WEB-INF/views/include/js/requirejs.jsp"/>
</head>
<body>

<div id="laputaConfigHistoryGrid"/>

<script>
    laputaKendoRequire([ "kendo/js/kendo.grid" ,"kendo/third/dropdowntreeview/laputa.dropdown.treelist","kendo/js/kendo.datetimepicker" ], function () {


        var laputaConfigDataSource = kendo.laputa.util.buildReadDataSource("${laputaConfigReadUrl}");

        var initFun = function() {

            var laputaConfigHistoryFilterDataSource = new kendo.data.DataSource({
                page: 1,
                pageSize: 20,
                serverPaging: true,
                serverSorting: true,
                serverFiltering: true,
                error: kendo.laputa.util.dataSourceErrorHandle,
                transport: {
                    read: kendo.laputa.util.transportUrlBuild("${laputaConfigHistoryReadDataSourceUrl}"),
                    update: kendo.laputa.util.transportUrlBuild("${laputaConfigHistoryUpdateUrl}"),
                    destroy: kendo.laputa.util.transportUrlBuild("${laputaConfigHistoryDestroyUrl}"),
                    create: kendo.laputa.util.transportUrlBuild("${laputaConfigHistoryCreateUrl}"),
                    parameterMap: kendo.laputa.util.parameterMap
                },
                schema: {
                    data: kendo.laputa.util.schemaData,
                    total: kendo.laputa.util.schemaTotal,
                    model: {
                        id: "id",
                        fields: {
                            id: {editable:  false  ,nullable: false,defaultValue: null},
                            configValue: {editable:  true  },
                            descript: {editable:  true  },
                            parentLaputaConfigId:{defaultValue: null, },
                            createdBy: {editable:  false  ,nullable: false},
                            createdDate: {editable:  false  ,nullable: false,type: "date"},
                            lastModifiedBy: {editable:  false  },
                            lastModifiedDate: {editable:  false  ,type: "date"}
                        }
                    }
                }
            });

            $("#laputaConfigHistoryGrid").kendoGrid({
                dataSource: laputaConfigHistoryFilterDataSource,
                toolbar: ["create"],
                columns: [
                            {field: "id", title: "主键", width: 180},
                            {field: "configValue", title: "配置值", width: 180},
                            {field: "descript", title: "描述", width: 180},
                        {field: "parentLaputaConfigId", title: "配置", width: 180, filterable: kendo.laputa.util.filterableBuild(laputaConfigDataSource.data()),template: kendo.laputa.util.buildEntityJoinTemplate("parentLaputaConfigId",laputaConfigDataSource.data()),editor:kendo.laputa.util.buildHierarchicaSelectInputEditor(laputaConfigDataSource.data())},
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

        kendo.laputa.util.dataSourceFetchQuen([laputaConfigDataSource], initFun);

    });

</script>

</body>
</html>