<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/rbac/sysoperation/create" var="sysOperationCreateUrl"/>
<c:url value="/rbac/sysoperation/readEager" var="sysOperationReadEagerUrl"/>
<c:url value="/rbac/sysoperation/update" var="sysOperationUpdateUrl"/>
<c:url value="/rbac/sysoperation/destory" var="sysOperationDestroyUrl"/>


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

<div id="sysOperationTreeList"/>

<script>
    laputaKendoRequire(["kendo/js/kendo.treelist", "kendo/third/dropdowntreeview/laputa.dropdown.treelist", "kendo/third/LaputaTreeViewCheckboxes/laputa.treeview.checkboxes", "kendo/js/kendo.datetimepicker"], function () {

        var sysPermissionDataSource = kendo.laputa.util.buildReadDataSource("${sysPermissionReadUrl}");

        var initFun = function () {

            var sysOperationDataSource = new kendo.data.TreeListDataSource({
                error: kendo.laputa.util.dataSourceErrorHandle,
                transport: {
                    read: kendo.laputa.util.transportUrlBuild("${sysOperationReadEagerUrl}"),
                    update: kendo.laputa.util.transportUrlBuild("${sysOperationUpdateUrl}"),
                    destroy: kendo.laputa.util.transportUrlBuild("${sysOperationDestroyUrl}"),
                    create: kendo.laputa.util.transportUrlBuild("${sysOperationCreateUrl}"),
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
                            descript: {editable: true},
                            prefixUrl: {editable: true},
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

            $("#sysOperationTreeList").kendoTreeList({
                dataSource: sysOperationDataSource,
                toolbar: ["create", kendo.laputa.util.buildTreeListRefreshToolbar()],
                height: window.innerHeight,
                columns: [
                    //{field: "id", title: "主键", width: 180},
                    {field: "cname", title: "名称", width: 120},
                    {field: "code", title: "编码", width: 160},
                    {field: "descript", title: "描述", width: 80},
                    {field: "prefixUrl", title: "拦截URL前缀", width: 180},
//                    {
//                        field: "parentId",
//                        title: "父操作",
//                        width: 120,
//                        template: kendo.laputa.util.buildEntityJoinTemplate("parentId", sysOperationDataSource),
//                        editor: kendo.laputa.util.buildHierarchicaSelectInputEditor(sysOperationDataSource)
//                    },
//                    {
//                        field: "children",
//                        title: "子操作",
//                        width: 180,
//                        sortable: false,
//                        template: kendo.laputa.util.buildEntityJoinTemplate("children", sysOperationDataSource),
//                        editor: kendo.laputa.util.buildHierarchicaEditor(sysOperationDataSource)
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
                    {field: "lastModifiedBy", title: "最后修改用户", width: 80},
                    {
                        field: "lastModifiedDate",
                        title: "最后修改时间",
                        width: 180,
                        format: "{0:yyyy-MM-dd HH:mm:ss tt}",
                        editor: kendo.laputa.util.dateTimePickerEditorBuild()
                    },
                    {command: ["edit",  kendo.laputa.util.buildTreeListConfirmDestroyCommand(), "createChild"], width: 260}
                ],
                editable: "inline",
                messages: {
                    noRows: "操作暂时为空",
                    commands: {
                        create: "新增操作",
                        createchild: "子级操作",
                    }
                }
            });

        }

        kendo.laputa.util.dataSourceFetchQuen([sysPermissionDataSource], initFun);

    });

</script>

</body>
</html>
