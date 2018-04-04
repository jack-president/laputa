<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/generate/syshellomenu/create" var="sysHelloMenuCreateUrl"/>
<c:url value="/generate/syshellomenu/readDataSource" var="sysHelloMenuReadDataSourceUrl"/>
<c:url value="/generate/syshellomenu/update" var="sysHelloMenuUpdateUrl"/>
<c:url value="/generate/syshellomenu/destory" var="sysHelloMenuDestroyUrl"/>

<c:url value="/generate/syshellomenu/read" var="sysHelloMenuReadUrl"/>
<c:url value="/generate/syshellopermission/read" var="sysHelloPermissionReadUrl"/>

<!DOCTYPE html>
<html>
<head>
    <title>Hi菜单列表</title>
    <meta charset="UTF-8">
    <link href="<c:url value='/static/kendo/third/LaputaTreeViewCheckboxes/style/laputa.treeview.checkboxes.css'/>" rel="stylesheet">
    <jsp:include page="/WEB-INF/views/include/style/kendoStyle.jsp"/>
    <jsp:include page="/WEB-INF/views/include/js/requirejs.jsp"/>
</head>
<body>

<div id="sysHelloMenuGrid"/>

<script>
    laputaKendoRequire([ "kendo/js/kendo.grid" ,"kendo/third/dropdowntreeview/laputa.dropdown.treelist","kendo/third/LaputaTreeViewCheckboxes/laputa.treeview.checkboxes" ], function () {


        var sysHelloMenuDataSource = kendo.laputa.util.buildReadDataSource("${sysHelloMenuReadUrl}");
        var sysHelloPermissionDataSource = kendo.laputa.util.buildReadDataSource("${sysHelloPermissionReadUrl}");

        var initFun = function() {

            var sysHelloMenuFilterDataSource = new kendo.data.DataSource({
                page: 1,
                pageSize: 20,
                serverPaging: true,
                serverSorting: true,
                serverFiltering: true,
                transport: {
                    read: kendo.laputa.util.transportUrlBuild("${sysHelloMenuReadDataSourceUrl}"),
                    update: kendo.laputa.util.transportUrlBuild("${sysHelloMenuUpdateUrl}"),
                    destroy: kendo.laputa.util.transportUrlBuild("${sysHelloMenuDestroyUrl}"),
                    create: kendo.laputa.util.transportUrlBuild("${sysHelloMenuCreateUrl}"),
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
                            iconClass: {editable:  true  },
                            parentId:{defaultValue: null, },
                            children: {defaultValue: null},
                            belongSysHelloPermissionCollection: {defaultValue: null}
                        }
                    }
                }
            });

            $("#sysHelloMenuGrid").kendoGrid({
                dataSource: sysHelloMenuFilterDataSource,
                toolbar: ["create"],
                columns: [
                            {field: "id", title: "主键", width: 180},
                            {field: "cname", title: "名字", width: 180},
                            {field: "code", title: "编码", width: 180},
                            {field: "iconClass", title: "图标", width: 180},
                        {field: "parentId", title: "父菜单", width: 180, filterable: kendo.laputa.util.filterableBuild(sysHelloMenuDataSource.data()),template: kendo.laputa.util.buildEntityJoinTemplate("parentId",sysHelloMenuDataSource.data()),editor:kendo.laputa.util.buildHierarchicaSelectInputEditor(sysHelloMenuDataSource.data())},
                            {field: "children", title: "子菜单", width: 180, sortable: false, filterable: kendo.laputa.util.multiFilterableBuild("children",sysHelloMenuDataSource.data()), template: kendo.laputa.util.buildEntityJoinTemplate("children",sysHelloMenuDataSource.data()),editor:kendo.laputa.util.buildHierarchicaEditor(sysHelloMenuDataSource.data())},
                            {field: "belongSysHelloPermissionCollection", title: "所属权限", width: 180, sortable: false, filterable: kendo.laputa.util.multiFilterableBuild("belongSysHelloPermissionCollection",sysHelloPermissionDataSource.data()), template: kendo.laputa.util.buildEntityJoinTemplate("belongSysHelloPermissionCollection",sysHelloPermissionDataSource.data()),editor:kendo.laputa.util.buildHierarchicaEditor(sysHelloPermissionDataSource.data())},
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

        kendo.laputa.util.dataSourceFetchQuen([sysHelloMenuDataSource,sysHelloPermissionDataSource], initFun);

    });

</script>

</body>
</html>