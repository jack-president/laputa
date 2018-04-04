<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<c:url value="/rbac/sysfile/create" var="sysFileCreateUrl"/>--%>
<%--<c:url value="/rbac/sysfile/readDataSource" var="sysFileReadDataSourceUrl"/>--%>
<%--<c:url value="/rbac/sysfile/update" var="sysFileUpdateUrl"/>--%>
<%--<c:url value="/rbac/sysfile/destory" var="sysFileDestroyUrl"/>--%>

<%--<c:url value="/rbac/syspermission/read" var="sysPermissionReadUrl"/>--%>

<!DOCTYPE html>
<html>
<head>
    <title>Zookeeper 列表</title>
    <meta charset="UTF-8">
    <link href="<c:url value='/static/kendo/third/LaputaTreeViewCheckboxes/style/laputa.treeview.checkboxes.css'/>"
          rel="stylesheet">
    <jsp:include page="/WEB-INF/views/include/style/kendoStyle.jsp"/>
    <jsp:include page="/WEB-INF/views/include/js/requirejs.jsp"/>
</head>
<body>

<div id="sysFileGrid"/>

<script>
    laputaKendoRequire(["kendo/js/kendo.grid",
        "kendo/third/LaputaTreeViewCheckboxes/laputa.treeview.checkboxes",
        "kendo/js/kendo.datetimepicker"], function () {


        var sysPermissionDataSource = kendo.laputa.util.buildReadDataSource("${sysPermissionReadUrl}");

        var initFun = function () {

            var sysFileFilterDataSource = new kendo.data.DataSource({
                page: 1,
                pageSize: 20,
                serverPaging: true,
                serverSorting: true,
                serverFiltering: true,
                error: kendo.laputa.util.dataSourceErrorHandle,
                transport: {
                    read: kendo.laputa.util.transportUrlBuild("${sysFileReadDataSourceUrl}"),
                    update: kendo.laputa.util.transportUrlBuild("${sysFileUpdateUrl}"),
                    destroy: kendo.laputa.util.transportUrlBuild("${sysFileDestroyUrl}"),
                    create: kendo.laputa.util.transportUrlBuild("${sysFileCreateUrl}"),
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
                            path: {editable: true},
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

            $("#sysFileGrid").kendoGrid({
                dataSource: sysFileFilterDataSource,
                toolbar: ["create"],
                height: window.innerHeight,
                columns: [
                    //{field: "id", title: "主键", width: 180},
                    {field: "cname", title: "名称", width: 100},
                    {field: "path", title: "路径", width: 100},
                    {field: "descript", title: "描述", width: 120},
//                    {
//                        field: "belongtoSysPermissionCollection",
//                        title: "所属权限",
//                        width: 180,
//                        sortable: false,
//                        filterable: kendo.laputa.util.multiFilterableBuild("belongtoSysPermissionCollection", sysPermissionDataSource.data()),
//                        template: kendo.laputa.util.buildEntityJoinTemplate("belongtoSysPermissionCollection", sysPermissionDataSource.data()),
//                        editor: kendo.laputa.util.buildHierarchicaEditor(sysPermissionDataSource.data())
//                    },
                    {field: "createdBy", title: "创建用户", width: 80},
                    {
                        field: "createdDate",
                        title: "创建时间",
                        width: 180,
                        format: "{0:yyyy-MM-dd HH:mm:ss tt}",
                        editor: kendo.laputa.util.dateTimePickerEditorBuild(),
                        filterable: kendo.laputa.util.dateTimePickerFilterableBuild()
                    },
                    {field: "lastModifiedBy", title: "最后修改用户", width: 80},
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