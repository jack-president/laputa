<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/generate/syshellomenu/create" var="sysHelloMenuCreateUrl"/>
<c:url value="/generate/syshellomenu/readEager" var="sysHelloMenuReadEagerUrl"/>
<c:url value="/generate/syshellomenu/update" var="sysHelloMenuUpdateUrl"/>
<c:url value="/generate/syshellomenu/destory" var="sysHelloMenuDestroyUrl"/>


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

<div id="sysHelloMenuTreeList"/>

<script>
    laputaKendoRequire(["kendo/js/kendo.treelist" ,"kendo/third/dropdowntreeview/laputa.dropdown.treelist","kendo/third/LaputaTreeViewCheckboxes/laputa.treeview.checkboxes" ], function () {

        var sysHelloPermissionDataSource = kendo.laputa.util.buildReadDataSource("${sysHelloPermissionReadUrl}");

        var initFun = function() {

            var sysHelloMenuDataSource = new kendo.data.TreeListDataSource({
                transport: {
                    read: kendo.laputa.util.transportUrlBuild("${sysHelloMenuReadEagerUrl}"),
                    update: kendo.laputa.util.transportUrlBuild("${sysHelloMenuUpdateUrl}"),
                    destroy: kendo.laputa.util.transportUrlBuild("${sysHelloMenuDestroyUrl}"),
                    create: kendo.laputa.util.transportUrlBuild("${sysHelloMenuCreateUrl}"),
                    parameterMap: kendo.laputa.util.parameterMap
                },
                schema: {
                    parse: kendo.laputa.util.treeResponseExpandedParse,
                    model: {
                        id: "id",
                        fields: {
                            id: {editable: false,nullable: false,defaultValue: null},
                            cname: {editable: true,nullable: false},
                            code: {editable: true,nullable: false},
                            iconClass: {editable: true},
                            parentId: {type: "number", nullable: true},
                            children: {defaultValue: null},
                            belongSysHelloPermissionCollection: {defaultValue: null}
                        }
                    }
                }
            });

            $("#sysHelloMenuTreeList").kendoTreeList({
                dataSource: sysHelloMenuDataSource,
                toolbar: ["create"],
                columns: [
                            {field: "id", title: "主键", width: 180},
                            {field: "cname", title: "名字", width: 180},
                            {field: "code", title: "编码", width: 180},
                            {field: "iconClass", title: "图标", width: 180},
                        {field: "parentId", title: "父菜单", width: 180, template: kendo.laputa.util.buildEntityJoinTemplate("parentId",sysHelloMenuDataSource),editor:kendo.laputa.util.buildHierarchicaSelectInputEditor(sysHelloMenuDataSource)},
                            {field: "children", title: "子菜单", width: 180, sortable: false, template: kendo.laputa.util.buildEntityJoinTemplate("children",sysHelloMenuDataSource),editor:kendo.laputa.util.buildHierarchicaEditor(sysHelloMenuDataSource)},
                            {field: "belongSysHelloPermissionCollection", title: "所属权限", width: 180, sortable: false, template: kendo.laputa.util.buildEntityJoinTemplate("belongSysHelloPermissionCollection",sysHelloPermissionDataSource.data()),editor:kendo.laputa.util.buildHierarchicaEditor(sysHelloPermissionDataSource.data())},
                    {command: ["edit", "destroy"], width: 220}
                ],
                editable: "inline"
            });

        }

        kendo.laputa.util.dataSourceFetchQuen([sysHelloPermissionDataSource], initFun);

    });

</script>

</body>
</html>
