<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/web/laputaaplication/create" var="laputaAplicationCreateUrl"/>
<c:url value="/web/laputaaplication/readDataSource" var="laputaAplicationReadDataSourceUrl"/>
<c:url value="/web/laputaaplication/update" var="laputaAplicationUpdateUrl"/>
<c:url value="/web/laputaaplication/destory" var="laputaAplicationDestroyUrl"/>

<c:url value="/web/laputaconfig/read" var="laputaConfigReadUrl"/>

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

<div id="laputaAplicationGrid"/>

<script>
    laputaKendoRequire([ "kendo/js/kendo.grid" ,"kendo/third/LaputaTreeViewCheckboxes/laputa.treeview.checkboxes","kendo/js/kendo.datetimepicker" ], function () {


        var laputaConfigDataSource = kendo.laputa.util.buildReadDataSource("${laputaConfigReadUrl}");

        var initFun = function() {

            var laputaAplicationFilterDataSource = new kendo.data.DataSource({
                page: 1,
                pageSize: 20,
                serverPaging: true,
                serverSorting: true,
                serverFiltering: true,
                error: kendo.laputa.util.dataSourceErrorHandle,
                transport: {
                    read: kendo.laputa.util.transportUrlBuild("${laputaAplicationReadDataSourceUrl}"),
                    update: kendo.laputa.util.transportUrlBuild("${laputaAplicationUpdateUrl}"),
                    destroy: kendo.laputa.util.transportUrlBuild("${laputaAplicationDestroyUrl}"),
                    create: kendo.laputa.util.transportUrlBuild("${laputaAplicationCreateUrl}"),
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
                            descript: {editable:  true  },
                            configList: {defaultValue: null},
                            createdBy: {editable:  false  ,nullable: false},
                            createdDate: {editable:  false  ,nullable: false,type: "date"},
                            lastModifiedBy: {editable:  false  },
                            lastModifiedDate: {editable:  false  ,type: "date"}
                        }
                    }
                }
            });

            $("#laputaAplicationGrid").kendoGrid({
                dataSource: laputaAplicationFilterDataSource,
                toolbar: ["create"],
                columns: [
                            {field: "id", title: "主键", width: 180},
                            {field: "cname", title: "名称", width: 180},
                            {field: "code", title: "编码", width: 180},
                            {field: "descript", title: "描述", width: 180},
                            {field: "configList", title: "配置", width: 180, sortable: false, filterable: kendo.laputa.util.multiFilterableBuild("configList",laputaConfigDataSource.data()), template: kendo.laputa.util.buildEntityJoinTemplate("configList",laputaConfigDataSource.data()),editor:kendo.laputa.util.buildHierarchicaEditor(laputaConfigDataSource.data())},
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