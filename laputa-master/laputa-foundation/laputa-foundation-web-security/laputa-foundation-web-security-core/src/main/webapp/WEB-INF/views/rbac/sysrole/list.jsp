<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/rbac/sysrole/create" var="sysRoleCreateUrl"/>
<c:url value="/rbac/sysrole/readDataSource" var="sysRoleReadDataSourceUrl"/>
<c:url value="/rbac/sysrole/update" var="sysRoleUpdateUrl"/>
<c:url value="/rbac/sysrole/destory" var="sysRoleDestroyUrl"/>

<c:url value="/rbac/syspermission/read" var="sysPermissionReadUrl"/>
<c:url value="/rbac/sysuser/read" var="sysUserReadUrl"/>
<c:url value="/rbac/sysusergroup/read" var="sysUserGroupReadUrl"/>

<!DOCTYPE html>
<html>
<head>
    <title>角色列表</title>
    <meta charset="UTF-8">
    <link href="<c:url value='/static/kendo/third/LaputaTreeViewCheckboxes/style/laputa.treeview.checkboxes.css'/>" rel="stylesheet">
    <jsp:include page="/WEB-INF/views/include/style/kendoStyle.jsp"/>
    <jsp:include page="/WEB-INF/views/include/js/requirejs.jsp"/>
</head>
<body>

<div id="sysRoleGrid"/>

<script>
    laputaKendoRequire([ "kendo/js/kendo.grid" ,"kendo/third/LaputaTreeViewCheckboxes/laputa.treeview.checkboxes","kendo/third/dropdowntreeview/laputa.dropdown.treelist","kendo/js/kendo.datetimepicker" ], function () {


        var sysPermissionDataSource = kendo.laputa.util.buildReadDataSource("${sysPermissionReadUrl}");
        var sysUserDataSource = kendo.laputa.util.buildReadDataSource("${sysUserReadUrl}");
        var sysUserGroupDataSource = kendo.laputa.util.buildReadDataSource("${sysUserGroupReadUrl}");

        var initFun = function() {

            var sysRoleFilterDataSource = new kendo.data.DataSource({
                page: 1,
                pageSize: 20,
                serverPaging: true,
                serverSorting: true,
                serverFiltering: true,
                error: kendo.laputa.util.dataSourceErrorHandle,
                transport: {
                    read: kendo.laputa.util.transportUrlBuild("${sysRoleReadDataSourceUrl}"),
                    update: kendo.laputa.util.transportUrlBuild("${sysRoleUpdateUrl}"),
                    destroy: kendo.laputa.util.transportUrlBuild("${sysRoleDestroyUrl}"),
                    create: kendo.laputa.util.transportUrlBuild("${sysRoleCreateUrl}"),
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
                            inverted: {editable:  false  ,nullable: false},
                            descript: {editable:  true  },
                            ownSysPermissionCollection: {defaultValue: null},
                            ownSysUserCollection: {defaultValue: null},
                            parentSysUserGroupId:{defaultValue: null,editable: false },
                            createdBy: {editable:  false  ,nullable: false},
                            createdDate: {editable:  false  ,nullable: false,type: "date"},
                            lastModifiedBy: {editable:  false  },
                            lastModifiedDate: {editable:  false  ,type: "date"}
                        }
                    }
                }
            });

            $("#sysRoleGrid").kendoGrid({
                dataSource: sysRoleFilterDataSource,
                toolbar: ["create"],
                columns: [
                            {field: "id", title: "主键", width: 180},
                            {field: "cname", title: "名称", width: 180},
                            {field: "code", title: "编码", width: 180},
                            {field: "inverted", title: "权限反转", width: 180},
                            {field: "descript", title: "描述", width: 180},
                            {field: "ownSysPermissionCollection", title: "拥有权限", width: 180, sortable: false, filterable: kendo.laputa.util.multiFilterableBuild("ownSysPermissionCollection",sysPermissionDataSource.data()), template: kendo.laputa.util.buildEntityJoinTemplate("ownSysPermissionCollection",sysPermissionDataSource.data()),editor:kendo.laputa.util.buildHierarchicaEditor(sysPermissionDataSource.data())},
                            {field: "ownSysUserCollection", title: "拥有用户", width: 180, sortable: false, filterable: kendo.laputa.util.multiFilterableBuild("ownSysUserCollection",sysUserDataSource.data()), template: kendo.laputa.util.buildEntityJoinTemplate("ownSysUserCollection",sysUserDataSource.data()),editor:kendo.laputa.util.buildHierarchicaEditor(sysUserDataSource.data())},
                        {field: "parentSysUserGroupId", title: "所属用户组", width: 180, filterable: kendo.laputa.util.filterableBuild(sysUserGroupDataSource.data()),template: kendo.laputa.util.buildEntityJoinTemplate("parentSysUserGroupId",sysUserGroupDataSource.data()),editor:kendo.laputa.util.buildHierarchicaSelectInputEditor(sysUserGroupDataSource.data())},
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

        kendo.laputa.util.dataSourceFetchQuen([sysPermissionDataSource,sysUserDataSource,sysUserGroupDataSource], initFun);

    });

</script>

</body>
</html>