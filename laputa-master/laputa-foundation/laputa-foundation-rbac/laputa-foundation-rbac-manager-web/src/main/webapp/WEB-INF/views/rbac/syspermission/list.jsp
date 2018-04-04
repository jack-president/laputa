<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/rbac/syspermission/create" var="sysPermissionCreateUrl"/>
<c:url value="/rbac/syspermission/readDataSource" var="sysPermissionReadDataSourceUrl"/>
<c:url value="/rbac/syspermission/update" var="sysPermissionUpdateUrl"/>
<c:url value="/rbac/syspermission/destory" var="sysPermissionDestroyUrl"/>

<c:url value="/rbac/syspermission/configOwnSysFileCollection/{0}" var="configOwnSysFileCollectionUrl"/>
<c:url value="/rbac/syspermission/configOwnSysMenuCollection/{0}" var="configOwnSysMenuCollectionUrl"/>
<c:url value="/rbac/syspermission/configOwnSysOperationCollection/{0}" var="configOwnSysOperationCollectionUrl"/>

<c:url value="/rbac/syselement/read" var="sysElementReadUrl"/>
<c:url value="/rbac/sysfile/read" var="sysFileReadUrl"/>
<c:url value="/rbac/sysmenu/read" var="sysMenuReadUrl"/>
<c:url value="/rbac/sysoperation/read" var="sysOperationReadUrl"/>
<c:url value="/rbac/sysrole/read" var="sysRoleReadUrl"/>

<!DOCTYPE html>
<html>
<head>
    <title>权限列表</title>
    <meta charset="UTF-8">
    <link href="<c:url value='/static/kendo/third/LaputaTreeViewCheckboxes/style/laputa.treeview.checkboxes.css'/>"
          rel="stylesheet">
    <jsp:include page="/WEB-INF/views/include/style/kendoStyle.jsp"/>
    <jsp:include page="/WEB-INF/views/include/js/requirejs.jsp"/>
</head>
<body>

<div id="sysPermissionGrid"/>

<script>
    laputaKendoRequire(["kendo/js/kendo.grid", "kendo/third/LaputaTreeViewCheckboxes/laputa.treeview.checkboxes", "kendo/js/kendo.datetimepicker"], function () {


        var sysElementDataSource = kendo.laputa.util.buildReadDataSource("${sysElementReadUrl}");
        var sysFileDataSource = kendo.laputa.util.buildReadDataSource("${sysFileReadUrl}");
        var sysMenuDataSource = kendo.laputa.util.buildReadDataSource("${sysMenuReadUrl}");
        var sysOperationDataSource = kendo.laputa.util.buildReadDataSource("${sysOperationReadUrl}");
        var sysRoleDataSource = kendo.laputa.util.buildReadDataSource("${sysRoleReadUrl}");

        var initFun = function () {

            var sysPermissionFilterDataSource = new kendo.data.DataSource({
                page: 1,
                pageSize: 20,
                serverPaging: true,
                serverSorting: true,
                serverFiltering: true,
                error: kendo.laputa.util.dataSourceErrorHandle,
                transport: {
                    read: kendo.laputa.util.transportUrlBuild("${sysPermissionReadDataSourceUrl}"),
                    update: kendo.laputa.util.transportUrlBuild("${sysPermissionUpdateUrl}"),
                    destroy: kendo.laputa.util.transportUrlBuild("${sysPermissionDestroyUrl}"),
                    create: kendo.laputa.util.transportUrlBuild("${sysPermissionCreateUrl}"),
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
                            ownSysElementCollection: {defaultValue: null},
                            ownSysFileCollection: {defaultValue: null},
                            ownSysMenuCollection: {editable: true, defaultValue: null},
                            ownSysOperationCollection: {editable: true, defaultValue: null},
                            belongtoSysRoleCollection: {defaultValue: null},
                            createdBy: {editable: false, nullable: false},
                            createdDate: {editable: false, nullable: false, type: "date"},
                            lastModifiedBy: {editable: false},
                            lastModifiedDate: {editable: false, type: "date"}
                        }
                    }
                }
            });

            window.sysPermissionGrid =
                $("#sysPermissionGrid").kendoGrid({
                    dataSource: sysPermissionFilterDataSource,
                    toolbar: ["create"],
                    height: window.innerHeight,
                    resizable: true,
                    columns: [
                        //{field: "id", title: "主键", width: 180},
                        {field: "cname", title: "名称", width: 80},
                        {field: "code", title: "编码", width: 140},
                        {field: "descript", title: "描述", width: 80},
//                    {
//                        field: "ownSysElementCollection",
//                        title: "元素",
//                        width: 180,
//                        sortable: false,
//                        locked:true,
//                        filterable: kendo.laputa.util.multiFilterableBuild("ownSysElementCollection", sysElementDataSource.data()),
//                        template: kendo.laputa.util.buildEntityJoinTemplate("ownSysElementCollection", sysElementDataSource.data()),
//                        editor: kendo.laputa.util.buildHierarchicaEditor(sysElementDataSource.data())
//                    },
//                        {
//                            field: "ownSysFileCollection",
//                            title: "文件",
//                            width: 180,
//                            sortable: false,
//                            filterable: kendo.laputa.util.multiFilterableBuild("ownSysFileCollection", sysFileDataSource.data()),
//                            template: kendo.laputa.util.buildEntityJoinTemplate("ownSysFileCollection", sysFileDataSource.data()),
//                            editor: kendo.laputa.util.buildHierarchicaEditor(sysFileDataSource.data())
//                        },
//                        {
//                            field: "ownSysMenuCollection",
//                            title: "菜单",
//                            width: 180,
//                            sortable: false,
//                            locked: true,
//                            filterable: kendo.laputa.util.multiFilterableBuild("ownSysMenuCollection", sysMenuDataSource.data()),
//                            template: kendo.laputa.util.buildEntityJoinTemplate("ownSysMenuCollection", sysMenuDataSource.data()),
//                            editor: kendo.laputa.util.buildHierarchicaEditor(sysMenuDataSource.data())
//                        },
//                        {
//                            field: "ownSysOperationCollection",
//                            title: "拥有操作",
//                            width: 180,
//                            sortable: false,
//                            locked: true,
//                            filterable: kendo.laputa.util.multiFilterableBuild("ownSysOperationCollection", sysOperationDataSource.data()),
//                            template: kendo.laputa.util.buildEntityJoinTemplate("ownSysOperationCollection", sysOperationDataSource.data()),
//                            editor: kendo.laputa.util.buildHierarchicaEditor(sysOperationDataSource.data())
//                        },
//                    {
//                        field: "belongtoSysRoleCollection",
//                        title: "所属角色",
//                        width: 180,
//                        sortable: false,
//                        locked:true,
//                        filterable: kendo.laputa.util.multiFilterableBuild("belongtoSysRoleCollection", sysRoleDataSource.data()),
//                        template: kendo.laputa.util.buildEntityJoinTemplate("belongtoSysRoleCollection", sysRoleDataSource.data()),
//                        editor: kendo.laputa.util.buildHierarchicaEditor(sysRoleDataSource.data())
//                    },

                        {field: "lastModifiedBy", title: "最后修改用户", width: 80},
                        {
                            field: "lastModifiedDate",
                            title: "最后修改时间",
                            width: 120,
                            format: "{0:yyyy-MM-dd HH:mm:ss tt}",
                            editor: kendo.laputa.util.dateTimePickerEditorBuild(),
                            filterable: kendo.laputa.util.dateTimePickerFilterableBuild()
                        },
                        {
                            command: ["edit", {
                                text: "关联操作",
                                imageClass: "k-edit",
                                //className: "k-grid-edit",
                                iconClass: "k-icon",
                                click: function (e) {
                                    var tr = $(e.target).closest("tr");
                                    var data = this.dataItem(tr);
                                    window.sysPermissionData = data;
                                    window.configWindow =
                                        $("<div />").kendoWindow({
                                            title: kendo.format("关联{0}权限拥有操作", data.cname),
                                            actions: ["Refresh", "Maximize", "Close"],
                                            height: "90%",
                                            width: "40%",
                                            modal: true,
                                            content: kendo.format("${configOwnSysOperationCollectionUrl}", data.id),
                                            iframe: true
                                        }).data("kendoWindow");
                                    configWindow.center();
                                    configWindow.open();
                                }
                            }, {
                                text: "关联菜单",
                                imageClass: "k-edit",
                                //className: "k-grid-edit",
                                iconClass: "k-icon",
                                click: function (e) {
                                    var tr = $(e.target).closest("tr");
                                    var data = this.dataItem(tr);
                                    window.sysPermissionData = data;
                                    window.configWindow =
                                        $("<div />").kendoWindow({
                                            title: kendo.format("关联{0}权限拥有菜单", data.cname),
                                            actions: ["Refresh", "Maximize", "Close"],
                                            height: "90%",
                                            width: "40%",
                                            modal: true,
                                            content: kendo.format("${configOwnSysMenuCollectionUrl}", data.id),
                                            iframe: true
                                        }).data("kendoWindow");
                                    configWindow.center();
                                    configWindow.open();
                                }
                            }, {
                                text: "关联资源",
                                imageClass: "k-edit",
                                //className: "k-grid-edit",
                                iconClass: "k-icon",
                                click: function (e) {
                                    var tr = $(e.target).closest("tr");
                                    var data = this.dataItem(tr);
                                    window.sysPermissionData = data;
                                    window.configWindow =
                                        $("<div />").kendoWindow({
                                            title: kendo.format("关联{0}权限拥有资源", data.cname),
                                            actions: ["Refresh", "Maximize", "Close"],
                                            height: "90%",
                                            width: "40%",
                                            modal: true,
                                            content: kendo.format("${configOwnSysFileCollectionUrl}", data.id),
                                            iframe: true
                                        }).data("kendoWindow");
                                    configWindow.center();
                                    configWindow.open();
                                }
                            }
                                , "destroy"], width: 400
                        },
//                        {field: "createdBy", title: "创建用户", width: 80},
//                        {
//                            field: "createdDate",
//                            title: "创建时间",
//                            width: 120,
//                            format: "{0:yyyy-MM-dd HH:mm:ss tt}",
//                            editor: kendo.laputa.util.dateTimePickerEditorBuild(),
//                            filterable: kendo.laputa.util.dateTimePickerFilterableBuild()
//                        },

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
                }).data("kendoGrid");

        }

        kendo.laputa.util.dataSourceFetchQuen([sysElementDataSource, sysFileDataSource, sysMenuDataSource, sysOperationDataSource, sysRoleDataSource], initFun);

    });

</script>

</body>
</html>