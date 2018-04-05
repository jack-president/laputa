<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/rbac/sysusergroup/create" var="sysUserGroupCreateUrl"/>
<c:url value="/rbac/sysusergroup/readEager" var="sysUserGroupReadEagerUrl"/>
<c:url value="/rbac/sysusergroup/update" var="sysUserGroupUpdateUrl"/>
<c:url value="/rbac/sysusergroup/destory" var="sysUserGroupDestroyUrl"/>


<c:url value="/rbac/sysrole/read" var="sysRoleReadUrl"/>
<c:url value="/rbac/syspermission/read" var="sysPermissionReadUrl"/>
<c:url value="/rbac/sysuser/read" var="sysUserReadUrl"/>

<!DOCTYPE html>
<html>
<head>
    <title>用户组列表</title>
    <meta charset="UTF-8">
    <link href="<c:url value='/static/kendo/third/LaputaTreeViewCheckboxes/style/laputa.treeview.checkboxes.css'/>"
          rel="stylesheet">
    <jsp:include page="/WEB-INF/views/include/style/kendoStyle.jsp"/>
    <jsp:include page="/WEB-INF/views/include/js/requirejs.jsp"/>
</head>
<body>

<div id="sysUserGroupTreeList"/>

<script>
    laputaKendoRequire(["kendo/js/kendo.treelist", "kendo/third/LaputaTreeViewCheckboxes/laputa.treeview.checkboxes", "kendo/js/kendo.datetimepicker"], function () {

        var sysRoleDataSource = kendo.laputa.util.buildReadDataSource("${sysRoleReadUrl}");
        var sysPermissionDataSource = kendo.laputa.util.buildReadDataSource("${sysPermissionReadUrl}");
        var sysUserDataSource = kendo.laputa.util.buildReadDataSource("${sysUserReadUrl}");

        var initFun = function () {

            var sysUserGroupDataSource = new kendo.data.TreeListDataSource({
                error: kendo.laputa.util.dataSourceErrorHandle,
                transport: {
                    read: kendo.laputa.util.transportUrlBuild("${sysUserGroupReadEagerUrl}"),
                    update: kendo.laputa.util.transportUrlBuild("${sysUserGroupUpdateUrl}"),
                    destroy: kendo.laputa.util.transportUrlBuild("${sysUserGroupDestroyUrl}"),
                    create: kendo.laputa.util.transportUrlBuild("${sysUserGroupCreateUrl}"),
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
                            ownSysRoleCollection: {defaultValue: null},
                            canhaveRelationSysPermissionCollection: {defaultValue: null},
                            ownSysUserCollection: {defaultValue: null},
                            createdBy: {editable: false, nullable: false},
                            createdDate: {editable: false, nullable: false, type: "date"},
                            lastModifiedBy: {editable: false},
                            lastModifiedDate: {editable: false, type: "date"}
                        }
                    }
                }
            });

            $("#sysUserGroupTreeList").kendoTreeList({
                dataSource: sysUserGroupDataSource,
                toolbar: ["create"],
                columns: [
                    {field: "id", title: "主键", width: 80},
                    {field: "cname", title: "名称", width: 180},
                    {field: "code", title: "编码", width: 180},
                    {field: "descript", title: "描述", width: 180},
                    {
                        field: "ownSysRoleCollection",
                        title: "拥有角色",
                        width: 180,
                        sortable: false,
                        template: kendo.laputa.util.buildEntityJoinTemplate("ownSysRoleCollection", sysRoleDataSource.data()),
                        editor: kendo.laputa.util.buildHierarchicaEditor(sysRoleDataSource.data())
                    },
                    {
                        field: "canhaveRelationSysPermissionCollection",
                        title: "部门权限",
                        width: 180,
                        sortable: false,
                        template: kendo.laputa.util.buildEntityJoinTemplate("canhaveRelationSysPermissionCollection", sysPermissionDataSource.data()),
                        editor: kendo.laputa.util.buildHierarchicaEditor(sysPermissionDataSource.data())
                    },
                    {
                        field: "ownSysUserCollection",
                        title: "拥有用户",
                        width: 180,
                        sortable: false,
                        template: kendo.laputa.util.buildEntityJoinTemplate("ownSysUserCollection", sysUserDataSource.data()),
                        editor: kendo.laputa.util.buildHierarchicaEditor(sysUserDataSource.data())
                    },
                    {field: "createdBy", title: "创建用户", width: 180},
                    {
                        field: "createdDate",
                        title: "创建时间",
                        width: 180,
                        format: "{0:yyyy-MM-dd HH:mm:ss tt}",
                        editor: kendo.laputa.util.dateTimePickerEditorBuild()
                    },
                    {field: "lastModifiedBy", title: "最后修改用户", width: 180},
                    {
                        field: "lastModifiedDate",
                        title: "最后修改时间",
                        width: 180,
                        format: "{0:yyyy-MM-dd HH:mm:ss tt}",
                        editor: kendo.laputa.util.dateTimePickerEditorBuild()
                    },
                    {command: ["edit", "destroy"], width: 220}
                ],
                editable: "inline"
            });

        }

        kendo.laputa.util.dataSourceFetchQuen([sysRoleDataSource, sysPermissionDataSource, sysUserDataSource], initFun);

    });

</script>

</body>
</html>
