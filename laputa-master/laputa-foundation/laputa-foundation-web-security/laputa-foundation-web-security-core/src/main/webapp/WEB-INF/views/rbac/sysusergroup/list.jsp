<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/rbac/sysusergroup/create" var="sysUserGroupCreateUrl"/>
<c:url value="/rbac/sysusergroup/readDataSource" var="sysUserGroupReadDataSourceUrl"/>
<c:url value="/rbac/sysusergroup/update" var="sysUserGroupUpdateUrl"/>
<c:url value="/rbac/sysusergroup/destory" var="sysUserGroupDestroyUrl"/>

<c:url value="/rbac/sysusergroup/configCanhaveRelationSysPermissionCollection/{0}"
       var="configCanhaveRelationSysPermissionCollectionUrl"/>


<c:url value="/rbac/sysrole/create" var="sysRoleCreateUrl"/>
<c:url value="/rbac/sysrole/readDataSource" var="sysRoleReadDataSourceUrl"/>
<c:url value="/rbac/sysrole/update" var="sysRoleUpdateUrl"/>
<c:url value="/rbac/sysrole/destory" var="sysRoleDestroyUrl"/>

<c:url value="/rbac/sysrole/configOwnSysPermissionCollection/{0}"
       var="configOwnSysPermissionCollectionUrl"/>

<c:url value="/rbac/sysrole/read" var="sysRoleReadUrl"/>
<c:url value="/rbac/syspermission/read" var="sysPermissionReadUrl"/>
<c:url value="/rbac/sysuser/read" var="sysUserReadUrl"/>

<c:url value="/rbac/sysusergroup/read" var="sysUserGroupReadUrl"/>

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

<div id="sysUserGroupGrid"/>

<script>
    laputaKendoRequire(["kendo/js/kendo.window", "kendo/js/kendo.grid", "kendo/third/dropdowntreeview/laputa.dropdown.treelist", "kendo/third/LaputaTreeViewCheckboxes/laputa.treeview.checkboxes", "kendo/js/kendo.datetimepicker"], function () {


        var sysRoleDataSource = kendo.laputa.util.buildReadDataSource("${sysRoleReadUrl}");
        var sysPermissionDataSource = kendo.laputa.util.buildReadDataSource("${sysPermissionReadUrl}");
        var sysUserDataSource = kendo.laputa.util.buildReadDataSource("${sysUserReadUrl}");

        var sysUserGroupDataSource = kendo.laputa.util.buildReadDataSource("${sysUserGroupReadUrl}");

        var initFun = function () {

            sysUserGroupFilterDataSource = new kendo.data.DataSource({
                page: 1,
                pageSize: 20,
                serverPaging: true,
                serverSorting: true,
                serverFiltering: true,
                error: kendo.laputa.util.dataSourceErrorHandle,
                transport: {
                    read: kendo.laputa.util.transportUrlBuild("${sysUserGroupReadDataSourceUrl}"),
                    update: kendo.laputa.util.transportUrlBuild("${sysUserGroupUpdateUrl}"),
                    destroy: kendo.laputa.util.transportUrlBuild("${sysUserGroupDestroyUrl}"),
                    create: kendo.laputa.util.transportUrlBuild("${sysUserGroupCreateUrl}"),
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
                            ownSysRoleCollection: {editable: false,defaultValue: null},
                            canhaveRelationSysPermissionCollection: {editable: false, defaultValue: null},
                            ownSysUserCollection: {defaultValue: null},
                            createdBy: {editable: false, nullable: false},
                            createdDate: {editable: false, nullable: false, type: "date"},
                            lastModifiedBy: {editable: false},
                            lastModifiedDate: {editable: false, type: "date"}
                        }
                    }
                }
            });

            window.sysUserGroupGrid =
                $("#sysUserGroupGrid").kendoGrid({
                    dataSource: sysUserGroupFilterDataSource,
                    detailInit: roleListDetailInit,
                    height: window.innerHeight,
                    toolbar: ["create"],
                    columns: [
                        //{field: "id", title: "主键", width: 180},
                        {field: "cname", title: "名称", width: 120},
                        {field: "code", title: "编码", width: 120},
                        {field: "descript", title: "描述", width: 180},
                        {
                            field: "ownSysRoleCollection",
                            title: "拥有角色",
                            width: 180,
                            sortable: false,
                            filterable: kendo.laputa.util.multiFilterableBuild("ownSysRoleCollection", sysRoleDataSource.data()),
                            template: kendo.laputa.util.buildEntityJoinTemplate("ownSysRoleCollection", sysRoleDataSource.data()),
                            editor: kendo.laputa.util.buildHierarchicaEditor(sysRoleDataSource.data())
                        },
                        {
                            field: "canhaveRelationSysPermissionCollection",
                            title: "部门权限",
                            width: 180,
                            sortable: false,
                            filterable: kendo.laputa.util.multiFilterableBuild("canhaveRelationSysPermissionCollection", sysPermissionDataSource.data()),
                            template: kendo.laputa.util.buildEntityJoinTemplate("canhaveRelationSysPermissionCollection", sysPermissionDataSource.data()),
                            editor: kendo.laputa.util.buildHierarchicaEditor(sysPermissionDataSource.data())
                        },
                        //{field: "ownSysUserCollection", title: "拥有用户", width: 180, sortable: false, filterable: kendo.laputa.util.multiFilterableBuild("ownSysUserCollection",sysUserDataSource.data()), template: kendo.laputa.util.buildEntityJoinTemplate("ownSysUserCollection",sysUserDataSource.data()),editor:kendo.laputa.util.buildHierarchicaEditor(sysUserDataSource.data())},
                        //{field: "createdBy", title: "创建用户", width: 180},
                        //{field: "createdDate", title: "创建时间", width: 180,format: "{0:yyyy-MM-dd HH:mm:ss tt}",editor:kendo.laputa.util.dateTimePickerEditorBuild(),filterable: kendo.laputa.util.dateTimePickerFilterableBuild()},
                        //{field: "lastModifiedBy", title: "最后修改用户", width: 180},
                        //{field: "lastModifiedDate", title: "最后修改时间", width: 180,format: "{0:yyyy-MM-dd HH:mm:ss tt}",editor:kendo.laputa.util.dateTimePickerEditorBuild(),filterable: kendo.laputa.util.dateTimePickerFilterableBuild()},
                        {
                            command: ["edit", {
                                text: "设置部门权限",
                                imageClass: "k-edit",
                                //className: "k-grid-edit",
                                iconClass: "k-icon",
                                click: function (e) {
                                    var tr = $(e.target).closest("tr");
                                    var data = this.dataItem(tr);
                                    window.configWindow =
                                        $("<div />").kendoWindow({
                                            title: kendo.format("设置{0}部门权限", data.cname),
                                            actions: ["Refresh", "Maximize", "Close"],
                                            height: "90%",
                                            width: "60%",
                                            modal: true,
                                            content: kendo.format("${configCanhaveRelationSysPermissionCollectionUrl}", data.id),
                                            iframe: true
                                        }).data("kendoWindow");
                                    configWindow.center();
                                    configWindow.open();
                                }
                            }, "destroy"], width: 280
                        }
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

            function roleListDetailInit(e) {
                var userGroup = e.data;
                var sysRoleFilterDataSource = new kendo.data.DataSource({
                    //page: 1,
                    //pageSize: 20,
                    serverPaging: false,
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
                                id: {editable: false, nullable: false, defaultValue: null},
                                cname: {editable: true, nullable: false},
                                code: {editable: true, nullable: false},
                                inverted: {editable: true, nullable: false, type: "boolean"},
                                descript: {editable: true},
                                ownSysPermissionCollection: {editable: false, defaultValue: null},
                                ownSysUserCollection: {defaultValue: null},
                                parentSysUserGroupId: {defaultValue: null, editable: false},
                                createdBy: {editable: false, nullable: false},
                                createdDate: {editable: false, nullable: false, type: "date"},
                                lastModifiedBy: {editable: false},
                                lastModifiedDate: {editable: false, type: "date"}
                            }
                        }
                    }
                });
                sysRoleFilterDataSource.transport.parameterMap = function (options, type) {
                    if (type == "read") {
                        var userGroupFilter = {
                            logic: "and",
                            filters: [
                                {field: "parentSysUserGroupId", operator: "eq", value: "" + userGroup.id}
                            ]
                        };
                        if (options.filter != null) {
                            userGroupFilter.filters.push(options.filter);
                        }
                        options.filter = userGroupFilter;
                        return JSON.stringify(options);
                    }

                    if (type == "create" || type == "update" || type == "destroy") {
                        options.parentSysUserGroupId = userGroup.id;
                        return kendo.stringify(options);
                    }

                    return options;
                };

                $("<div/>").appendTo(e.detailCell).kendoGrid({
                    dataSource: sysRoleFilterDataSource,
                    toolbar: [{name: "create", text: kendo.format("往 {0} 部门添加角色", userGroup.cname)}],
                    //height: window.innerHeight,
                    columns: [
                        //{field: "id", title: "主键", width: 180},
                        {field: "cname", title: "名称", width: 120},
                        {field: "code", title: "编码", width: 120},
                        {
                            field: "inverted", title: "权限反转", width: 60,
                            //template: '<input type="checkbox" #= inverted ? "checked=checked" : "" # disabled="disabled" ></input>',
                            template: "#= inverted ? '是' : '否'#",
                            editor: kendo.laputa.util.buildBoolEditor()
                        },
                        {field: "descript", title: "描述", width: 90},
                        {
                            field: "ownSysPermissionCollection",
                            title: "拥有权限",
                            width: 120,
                            sortable: false,
                            filterable: kendo.laputa.util.multiFilterableBuild("ownSysPermissionCollection", sysPermissionDataSource.data()),
                            template: kendo.laputa.util.buildEntityJoinTemplate("ownSysPermissionCollection", sysPermissionDataSource.data()),
                            editor: kendo.laputa.util.buildHierarchicaEditor(sysPermissionDataSource.data())
                        },
//                    {
//                        field: "ownSysUserCollection",
//                        title: "拥有用户",
//                        width: 180,
//                        sortable: false,
//                        filterable: kendo.laputa.util.multiFilterableBuild("ownSysUserCollection", sysUserDataSource.data()),
//                        template: kendo.laputa.util.buildEntityJoinTemplate("ownSysUserCollection", sysUserDataSource.data()),
//                        editor: kendo.laputa.util.buildHierarchicaEditor(sysUserDataSource.data())
//                    },
                        {
                            field: "parentSysUserGroupId",
                            title: "所属用户组",
                            width: 80,
                            filterable: kendo.laputa.util.filterableBuild(sysUserGroupDataSource.data()),
                            template: kendo.laputa.util.buildEntityJoinTemplate("parentSysUserGroupId", sysUserGroupDataSource.data()),
                            editor: kendo.laputa.util.buildHierarchicaSelectInputEditor(sysUserGroupDataSource.data())
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
//                        {field: "lastModifiedBy", title: "最后修改用户", width: 180},
//                        {
//                            field: "lastModifiedDate",
//                            title: "最后修改时间",
//                            width: 80,
//                            format: "{0:yyyy-MM-dd HH:mm:ss tt}",
//                            editor: kendo.laputa.util.dateTimePickerEditorBuild(),
//                            filterable: kendo.laputa.util.dateTimePickerFilterableBuild()
//                        },
                        {
                            command: ["edit", {
                                text: "设置角色权限",
                                imageClass: "k-edit",
                                //className: "k-grid-edit",
                                iconClass: "k-icon",
                                click: function (e) {
                                    console.log(e);
                                    var tr = $(e.target).closest("tr");
                                    var data = this.dataItem(tr);
                                    window.configSysRoleGrid = this;
                                    window.configsysroleframe =
                                        $(kendo.format("<div id='configsysroleframe_%s' />", data.id)).kendoWindow({
                                            title: kendo.format("设置{0}角色权限", data.cname),
                                            actions: ["Refresh", "Maximize", "Close"],
                                            height: "90%",
                                            width: "60%",
                                            modal: true,
                                            content: kendo.format("${configOwnSysPermissionCollectionUrl}", data.id),
                                            iframe: true
                                        }).data("kendoWindow");
                                    configsysroleframe.center();
                                    configsysroleframe.open();
                                }
                            }, "destroy"], width: 280
                        }
                    ],
//                    pageable: {
//                        refresh: true,
//                        pageSizes: true,
//                        buttonCount: 5
//                    },
                    pageable: false,
                    filterable: {
                        multi: true
                    },
                    sortable: {
                        multi: true
                    },
                    editable: "inline"
                });
            }

        }

        kendo.laputa.util.dataSourceFetchQuen([sysUserGroupDataSource, sysRoleDataSource, sysPermissionDataSource, sysUserDataSource], initFun);

    });

</script>

</body>
</html>