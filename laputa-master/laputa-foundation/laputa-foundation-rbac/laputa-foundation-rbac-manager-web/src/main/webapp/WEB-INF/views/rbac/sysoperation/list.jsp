<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/rbac/sysoperation/create" var="sysOperationCreateUrl"/>
<c:url value="/rbac/sysoperation/readDataSource" var="sysOperationReadDataSourceUrl"/>
<c:url value="/rbac/sysoperation/update" var="sysOperationUpdateUrl"/>
<c:url value="/rbac/sysoperation/destory" var="sysOperationDestroyUrl"/>

<c:url value="/rbac/sysoperation/read" var="sysOperationReadUrl"/>
<c:url value="/rbac/syspermission/read" var="sysPermissionReadUrl"/>

<!DOCTYPE html>
<html>
<head>
    <title>功能操作列表</title>
    <meta charset="UTF-8">
    <link href="<c:url value='/static/kendo/third/LaputaTreeViewCheckboxes/style/laputa.treeview.checkboxes.css'/>" rel="stylesheet">
    <jsp:include page="/WEB-INF/views/include/style/kendoStyle.jsp"/>
    <jsp:include page="/WEB-INF/views/include/js/requirejs.jsp"/>
</head>
<body>

<div id="sysOperationGrid"/>

<script>
    laputaKendoRequire([ "kendo/js/kendo.grid" ,"kendo/third/dropdowntreeview/laputa.dropdown.treelist","kendo/third/LaputaTreeViewCheckboxes/laputa.treeview.checkboxes","kendo/js/kendo.datetimepicker" ], function () {


        var sysOperationDataSource = kendo.laputa.util.buildReadDataSource("${sysOperationReadUrl}");
        var sysPermissionDataSource = kendo.laputa.util.buildReadDataSource("${sysPermissionReadUrl}");

        var initFun = function() {

            var sysOperationFilterDataSource = new kendo.data.DataSource({
                page: 1,
                pageSize: 20,
                serverPaging: true,
                serverSorting: true,
                serverFiltering: true,
                error: kendo.laputa.util.dataSourceErrorHandle,
                transport: {
                    read: kendo.laputa.util.transportUrlBuild("${sysOperationReadDataSourceUrl}"),
                    update: kendo.laputa.util.transportUrlBuild("${sysOperationUpdateUrl}"),
                    destroy: kendo.laputa.util.transportUrlBuild("${sysOperationDestroyUrl}"),
                    create: kendo.laputa.util.transportUrlBuild("${sysOperationCreateUrl}"),
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
                            prefixUrl: {editable:  true  },
                            parentId:{defaultValue: null, },
                            children: {defaultValue: null},
                            belongtoSysPermissionCollection: {defaultValue: null},
                            createdBy: {editable:  false  ,nullable: false},
                            createdDate: {editable:  false  ,nullable: false,type: "date"},
                            lastModifiedBy: {editable:  false  },
                            lastModifiedDate: {editable:  false  ,type: "date"}
                        }
                    }
                }
            });

            $("#sysOperationGrid").kendoGrid({
                dataSource: sysOperationFilterDataSource,
                toolbar: ["create"],
                columns: [
                            {field: "id", title: "主键", width: 180},
                            {field: "cname", title: "名称", width: 180},
                            {field: "code", title: "编码", width: 180},
                            {field: "descript", title: "描述", width: 180},
                            {field: "prefixUrl", title: "拦截URL前缀", width: 180},
                        {field: "parentId", title: "父操作", width: 180, filterable: kendo.laputa.util.filterableBuild(sysOperationDataSource.data()),template: kendo.laputa.util.buildEntityJoinTemplate("parentId",sysOperationDataSource.data()),editor:kendo.laputa.util.buildHierarchicaSelectInputEditor(sysOperationDataSource.data())},
                            {field: "children", title: "子操作", width: 180, sortable: false, filterable: kendo.laputa.util.multiFilterableBuild("children",sysOperationDataSource.data()), template: kendo.laputa.util.buildEntityJoinTemplate("children",sysOperationDataSource.data()),editor:kendo.laputa.util.buildHierarchicaEditor(sysOperationDataSource.data())},
                            {field: "belongtoSysPermissionCollection", title: "所属权限", width: 180, sortable: false, filterable: kendo.laputa.util.multiFilterableBuild("belongtoSysPermissionCollection",sysPermissionDataSource.data()), template: kendo.laputa.util.buildEntityJoinTemplate("belongtoSysPermissionCollection",sysPermissionDataSource.data()),editor:kendo.laputa.util.buildHierarchicaEditor(sysPermissionDataSource.data())},
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

        kendo.laputa.util.dataSourceFetchQuen([sysOperationDataSource,sysPermissionDataSource], initFun);

    });

</script>

</body>
</html>