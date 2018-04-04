<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/rbac/syselement/create" var="sysElementCreateUrl"/>
<c:url value="/rbac/syselement/readDataSource" var="sysElementReadDataSourceUrl"/>
<c:url value="/rbac/syselement/update" var="sysElementUpdateUrl"/>
<c:url value="/rbac/syselement/destory" var="sysElementDestroyUrl"/>

<c:url value="/rbac/syspermission/read" var="sysPermissionReadUrl"/>

<!DOCTYPE html>
<html>
<head>
    <title>系统元素列表</title>
    <meta charset="UTF-8">
    <link href="<c:url value='/static/kendo/third/LaputaTreeViewCheckboxes/style/laputa.treeview.checkboxes.css'/>" rel="stylesheet">
    <jsp:include page="/WEB-INF/views/include/style/kendoStyle.jsp"/>
    <jsp:include page="/WEB-INF/views/include/js/requirejs.jsp"/>
</head>
<body>

<div id="sysElementGrid"/>

<script>
    laputaKendoRequire(["kendo/js/kendo.grid", "kendo/third/LaputaTreeViewCheckboxes/laputa.treeview.checkboxes", "kendo/js/kendo.datetimepicker"], function () {


        var sysPermissionDataSource = kendo.laputa.util.buildReadDataSource("${sysPermissionReadUrl}");

        var initFun = function () {

            var sysElementFilterDataSource = new kendo.data.DataSource({
                page: 1,
                pageSize: 20,
                serverPaging: true,
                serverSorting: true,
                serverFiltering: true,
                error: kendo.laputa.util.dataSourceErrorHandle,
                transport: {
                    read: kendo.laputa.util.transportUrlBuild("${sysElementReadDataSourceUrl}"),
                    update: kendo.laputa.util.transportUrlBuild("${sysElementUpdateUrl}"),
                    destroy: kendo.laputa.util.transportUrlBuild("${sysElementDestroyUrl}"),
                    create: kendo.laputa.util.transportUrlBuild("${sysElementCreateUrl}"),
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
                            descript: {editable: true},
                            belongtoSysPermissionCollection: {defaultValue: null},
                            createdBy: {editable: false, nullable: false},
                            createdDate: {editable: false, nullable: false, type: "date"},
                            lastModifiedBy: {editable: false},
                            lastModifiedDate: {editable: false, type: "date"}
                        }
                    }
                }
            });

            $("#sysElementGrid").kendoGrid({
                dataSource: sysElementFilterDataSource,
                toolbar: ["create"],
                height: window.innerHeight,
                columns: [
                    //{field: "id", title: "主键", width: 180},
                    {field: "cname", title: "名称", width: 180},
                    {field: "code", title: "编码", width: 180},
                    {field: "descript", title: "描述", width: 180},
//                    {
//                        field: "belongtoSysPermissionCollection",
//                        title: "所属权限",
//                        width: 180,
//                        sortable: false,
//                        filterable: kendo.laputa.util.multiFilterableBuild("belongtoSysPermissionCollection", sysPermissionDataSource.data()),
//                        template: kendo.laputa.util.buildEntityJoinTemplate("belongtoSysPermissionCollection", sysPermissionDataSource.data()),
//                        editor: kendo.laputa.util.buildHierarchicaEditor(sysPermissionDataSource.data())
//                    },
                    {field: "createdBy", title: "创建用户", width: 180},
                    {
                        field: "createdDate",
                        title: "创建时间",
                        width: 180,
                        format: "{0:yyyy-MM-dd HH:mm:ss tt}",
                        editor: kendo.laputa.util.dateTimePickerEditorBuild(),
                        filterable: kendo.laputa.util.dateTimePickerFilterableBuild()
                    },
                    {field: "lastModifiedBy", title: "最后修改用户", width: 180},
                    {
                        field: "lastModifiedDate",
                        title: "最后修改时间",
                        width: 180,
                        format: "{0:yyyy-MM-dd HH:mm:ss tt}",
                        editor: kendo.laputa.util.dateTimePickerEditorBuild(),
                        filterable: kendo.laputa.util.dateTimePickerFilterableBuild()
                    },
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

        kendo.laputa.util.dataSourceFetchQuen([sysPermissionDataSource], initFun);

    });

</script>

</body>
</html>