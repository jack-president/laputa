<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/rbac/sysmenu/create" var="sysMenuCreateUrl"/>
<c:url value="/rbac/sysmenu/readDataSource" var="sysMenuReadDataSourceUrl"/>
<c:url value="/rbac/sysmenu/update" var="sysMenuUpdateUrl"/>
<c:url value="/rbac/sysmenu/destory" var="sysMenuDestroyUrl"/>

<c:url value="/rbac/sysmenu/read" var="sysMenuReadUrl"/>
<c:url value="/rbac/syspermission/read" var="sysPermissionReadUrl"/>

<!DOCTYPE html>
<html>
<head>
    <title>菜单列表</title>
    <meta charset="UTF-8">
    <link href="<c:url value='/static/kendo/third/LaputaTreeViewCheckboxes/style/laputa.treeview.checkboxes.css'/>"
          rel="stylesheet">
    <jsp:include page="/WEB-INF/views/include/style/kendoStyle.jsp"/>
    <jsp:include page="/WEB-INF/views/include/js/requirejs.jsp"/>
</head>
<body>

<div id="sysMenuGrid"/>

<script>
    laputaKendoRequire(["kendo/js/kendo.grid", "kendo/third/dropdowntreeview/laputa.dropdown.treelist", "kendo/third/LaputaTreeViewCheckboxes/laputa.treeview.checkboxes", "kendo/js/kendo.datetimepicker"], function () {


        var sysMenuDataSource = kendo.laputa.util.buildReadDataSource("${sysMenuReadUrl}");
        var sysPermissionDataSource = kendo.laputa.util.buildReadDataSource("${sysPermissionReadUrl}");

        var initFun = function () {

            var sysMenuFilterDataSource = new kendo.data.DataSource({
                page: 1,
                pageSize: 20,
                serverPaging: true,
                serverSorting: true,
                serverFiltering: true,
                error: kendo.laputa.util.dataSourceErrorHandle,
                transport: {
                    read: kendo.laputa.util.transportUrlBuild("${sysMenuReadDataSourceUrl}"),
                    update: kendo.laputa.util.transportUrlBuild("${sysMenuUpdateUrl}"),
                    destroy: kendo.laputa.util.transportUrlBuild("${sysMenuDestroyUrl}"),
                    create: kendo.laputa.util.transportUrlBuild("${sysMenuCreateUrl}"),
                    parameterMap: kendo.laputa.util.parameterMap
                },
                schema: {
                    data: kendo.laputa.util.schemaData,
                    total: kendo.laputa.util.schemaTotal,
                    model: {
                        id: "id",
                        fields: {
                            id: {editable: false, nullable: false, defaultValue: null},
                            cname: {editable: true, nullable: false},
                            code: {editable: true, nullable: false},
                            iconClass: {editable: true},
                            resources: {editable: true},
                            descript: {editable: true},
                            orderValue: {editable: true},
                            parentId: {defaultValue: null,},
                            children: {defaultValue: null},
                            belongtoSysPermissionCollection: {defaultValue: null},
                            createdBy: {editable: false, nullable: false},
                            createdDate: {editable: false, nullable: false, type: "date"},
                            lastModifiedBy: {editable: false},
                            lastModifiedDate: {editable: false, type: "date"}
                        }
                    }
                }
            });

            $("#sysMenuGrid").kendoGrid({
                dataSource: sysMenuFilterDataSource,
                toolbar: ["create"],
                columns: [
                    {field: "id", title: "主键", width: 180},
                    {field: "cname", title: "名称", width: 180},
                    {field: "code", title: "编码", width: 180},
                    {field: "iconClass", title: "图标", width: 180},
                    {field: "resources", title: "资源地址", width: 180},
                    {field: "descript", title: "描述", width: 180},
                    {field: "orderValue", title: "排序", width: 180},
                    {
                        field: "parentId",
                        title: "父菜单",
                        width: 180,
                        filterable: kendo.laputa.util.filterableBuild(sysMenuDataSource.data()),
                        template: kendo.laputa.util.buildEntityJoinTemplate("parentId", sysMenuDataSource.data()),
                        editor: kendo.laputa.util.buildHierarchicaSelectInputEditor(sysMenuDataSource.data())
                    },
                    {
                        field: "children",
                        title: "子菜单",
                        width: 180,
                        sortable: false,
                        filterable: kendo.laputa.util.multiFilterableBuild("children", sysMenuDataSource.data()),
                        template: kendo.laputa.util.buildEntityJoinTemplate("children", sysMenuDataSource.data()),
                        editor: kendo.laputa.util.buildHierarchicaEditor(sysMenuDataSource.data())
                    },
                    {
                        field: "belongtoSysPermissionCollection",
                        title: "所属权限",
                        width: 180,
                        sortable: false,
                        filterable: kendo.laputa.util.multiFilterableBuild("belongtoSysPermissionCollection", sysPermissionDataSource.data()),
                        template: kendo.laputa.util.buildEntityJoinTemplate("belongtoSysPermissionCollection", sysPermissionDataSource.data()),
                        editor: kendo.laputa.util.buildHierarchicaEditor(sysPermissionDataSource.data())
                    },
//                    {field: "createdBy", title: "创建用户", width: 180},
//                    {
//                        field: "createdDate",
//                        title: "创建时间",
//                        width: 180,
//                        format: "{0:yyyy-MM-dd HH:mm:ss tt}",
//                        editor: kendo.laputa.util.dateTimePickerEditorBuild(),
//                        filterable: kendo.laputa.util.dateTimePickerFilterableBuild()
//                    },
//                    {field: "lastModifiedBy", title: "最后修改用户", width: 180},
//                    {
//                        field: "lastModifiedDate",
//                        title: "最后修改时间",
//                        width: 180,
//                        format: "{0:yyyy-MM-dd HH:mm:ss tt}",
//                        editor: kendo.laputa.util.dateTimePickerEditorBuild(),
//                        filterable: kendo.laputa.util.dateTimePickerFilterableBuild()
//                    },
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

        kendo.laputa.util.dataSourceFetchQuen([sysMenuDataSource, sysPermissionDataSource], initFun);

    });

</script>

</body>
</html>