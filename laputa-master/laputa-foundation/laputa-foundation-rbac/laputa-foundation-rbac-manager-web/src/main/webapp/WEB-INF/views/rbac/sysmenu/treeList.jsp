<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/rbac/sysmenu/create" var="sysMenuCreateUrl"/>
<c:url value="/rbac/sysmenu/readEager" var="sysMenuReadEagerUrl"/>
<c:url value="/rbac/sysmenu/update" var="sysMenuUpdateUrl"/>
<c:url value="/rbac/sysmenu/destory" var="sysMenuDestroyUrl"/>


<c:url value="/rbac/syspermission/read" var="sysPermissionReadUrl"/>

<!DOCTYPE html>
<html>
<head>
    <title>菜单列表</title>
    <meta charset="UTF-8">
    <link href="<c:url value='/static/kendo/third/LaputaTreeViewCheckboxes/style/laputa.treeview.checkboxes.css'/>" rel="stylesheet">
    <jsp:include page="/WEB-INF/views/include/style/kendoStyle.jsp"/>
    <jsp:include page="/WEB-INF/views/include/js/requirejs.jsp"/>
</head>
<body>

<div id="sysMenuTreeList"/>

<script>
    laputaKendoRequire(["kendo/js/kendo.treelist", "kendo/third/dropdowntreeview/laputa.dropdown.treelist", "kendo/third/LaputaTreeViewCheckboxes/laputa.treeview.checkboxes", "kendo/js/kendo.datetimepicker"], function () {

        var sysPermissionDataSource = kendo.laputa.util.buildReadDataSource("${sysPermissionReadUrl}");

        var initFun = function () {

            var sysMenuDataSource = new kendo.data.TreeListDataSource({
                error: kendo.laputa.util.dataSourceErrorHandle,
                transport: {
                    read: kendo.laputa.util.transportUrlBuild("${sysMenuReadEagerUrl}"),
                    update: kendo.laputa.util.transportUrlBuild("${sysMenuUpdateUrl}"),
                    destroy: kendo.laputa.util.transportUrlBuild("${sysMenuDestroyUrl}"),
                    create: kendo.laputa.util.transportUrlBuild("${sysMenuCreateUrl}"),
                    parameterMap: kendo.laputa.util.parameterMap
                },
                schema: {
                    parse: kendo.laputa.util.treeResponseExpandedParse,
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
                            parentId: {type: "number", nullable: true},
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

            $("#sysMenuTreeList").kendoTreeList({
                dataSource: sysMenuDataSource,
                toolbar: ["create", kendo.laputa.util.buildTreeListRefreshToolbar()],
                height: window.innerHeight,
                columns: [
                    //{field: "id", title: "主键", width: 180},
                    {field: "cname", title: "名称", width: 120},
                    {field: "code", title: "编码", width: 180},
                    {field: "iconClass", title: "图标", width: 80},
                    {field: "resources", title: "资源地址", width: 180},
                    {field: "descript", title: "描述", width: 120},
                    {field: "orderValue", title: "排序", width: 60},
                    {
                        field: "parentId",
                        title: "父菜单",
                        width: 120,
                        template: kendo.laputa.util.buildEntityJoinTemplate("parentId", sysMenuDataSource),
                        editor: kendo.laputa.util.buildHierarchicaSelectInputEditor(sysMenuDataSource)
                    },
//                    {
//                        field: "children",
//                        title: "子菜单",
//                        width: 180,
//                        sortable: false,
//                        template: kendo.laputa.util.buildEntityJoinTemplate("children", sysMenuDataSource),
//                        editor: kendo.laputa.util.buildHierarchicaEditor(sysMenuDataSource)
//                    },
//                    {
//                        field: "belongtoSysPermissionCollection",
//                        title: "所属权限",
//                        width: 180,
//                        sortable: false,
//                        template: kendo.laputa.util.buildEntityJoinTemplate("belongtoSysPermissionCollection", sysPermissionDataSource.data()),
//                        editor: kendo.laputa.util.buildHierarchicaEditor(sysPermissionDataSource.data())
//                    },
//                    {field: "createdBy", title: "创建用户", width: 180},
//                    {
//                        field: "createdDate",
//                        title: "创建时间",
//                        width: 180,
//                        format: "{0:yyyy-MM-dd HH:mm:ss tt}",
//                        editor: kendo.laputa.util.dateTimePickerEditorBuild()
//                    },
//                    {field: "lastModifiedBy", title: "最后修改用户", width: 120},
//                    {
//                        field: "lastModifiedDate",
//                        title: "最后修改时间",
//                        width: 180,
//                        format: "{0:yyyy-MM-dd HH:mm:ss tt}",
//                        editor: kendo.laputa.util.dateTimePickerEditorBuild()
//                    },
                    {command: ["edit", kendo.laputa.util.buildTreeListConfirmDestroyCommand(), "createChild"], width: 260}
                ],
                editable: "inline",
                messages: {
                    noRows: "菜单暂时为空",
                    commands: {
                        create: "新增菜单",
                        createchild: "子级菜单",
                    }
                }
            });

        }

        kendo.laputa.util.dataSourceFetchQuen([sysPermissionDataSource], initFun);

    });

</script>

</body>
</html>
