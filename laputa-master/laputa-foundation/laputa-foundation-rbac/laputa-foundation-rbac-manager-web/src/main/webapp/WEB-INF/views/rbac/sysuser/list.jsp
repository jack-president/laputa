<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/rbac/sysuser/create" var="sysUserCreateUrl"/>
<c:url value="/rbac/sysuser/readDataSource" var="sysUserReadDataSourceUrl"/>
<c:url value="/rbac/sysuser/update" var="sysUserUpdateUrl"/>
<c:url value="/rbac/sysuser/destory" var="sysUserDestroyUrl"/>

<c:url value="/rbac/sysrole/read" var="sysRoleReadUrl"/>
<c:url value="/rbac/sysusergroup/read" var="sysUserGroupReadUrl"/>

<c:url value="/rbac/sysuser/configBelongtoSysRoleCollection/{0}" var="configBelongtoSysRoleCollectionUrl"/>
<c:url value="/rbac/sysuser/configBelongtoSysUserGroupCollection/{0}" var="configBelongtoSysUserGroupCollectionUrl"/>


<c:url value="/rbac/sysuser/updatePassword" var="updatePasswordUrl"/>

<!DOCTYPE html>
<html>
<head>
    <title>用户列表</title>
    <meta charset="UTF-8">
    <link href="<c:url value='/static/kendo/third/LaputaTreeViewCheckboxes/style/laputa.treeview.checkboxes.css'/>"
          rel="stylesheet">
    <jsp:include page="/WEB-INF/views/include/style/kendoStyle.jsp"/>
    <jsp:include page="/WEB-INF/views/include/js/requirejs.jsp"/>
</head>
<body>

<div id="sysUserGrid"/>

<script>
    laputaKendoRequire(["kendo/js/kendo.grid", "kendo/third/LaputaTreeViewCheckboxes/laputa.treeview.checkboxes", "kendo/js/kendo.datetimepicker"], function () {


        var sysRoleDataSource = kendo.laputa.util.buildReadDataSource("${sysRoleReadUrl}");
        var sysUserGroupDataSource = kendo.laputa.util.buildReadDataSource("${sysUserGroupReadUrl}");

        var initFun = function () {

            var sysUserFilterDataSource = new kendo.data.DataSource({
                page: 1,
                pageSize: 20,
                serverPaging: true,
                serverSorting: true,
                serverFiltering: true,
                error: kendo.laputa.util.dataSourceErrorHandle,
                transport: {
                    read: kendo.laputa.util.transportUrlBuild("${sysUserReadDataSourceUrl}"),
                    update: kendo.laputa.util.transportUrlBuild("${sysUserUpdateUrl}"),
                    destroy: kendo.laputa.util.transportUrlBuild("${sysUserDestroyUrl}"),
                    create: kendo.laputa.util.transportUrlBuild("${sysUserCreateUrl}"),
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
                            username: {editable: true, nullable: false},
                            password: {editable: true, nullable: false},
                            locked: {editable: false, nullable: false, type: "boolean"},
                            descript: {editable: true},
                            belongtoSysRoleCollection: {editable: false, defaultValue: null},
                            belongtoSysUserGroupCollection: {editable: false,defaultValue: null},
                            createdBy: {editable: false, nullable: false},
                            createdDate: {editable: false, nullable: false, type: "date"},
                            lastModifiedBy: {editable: false},
                            lastModifiedDate: {editable: false, type: "date"}
                        }
                    }
                }
            });

            window.sysUserGrid =
                $("#sysUserGrid").kendoGrid({
                    dataSource: sysUserFilterDataSource,
                    toolbar: ["create"],
                    height: window.innerHeight,
                    columns: [
                        //{field: "id", title: "主键", width: 180},
                        {field: "cname", title: "名称", width: 120},
                        {field: "username", title: "用户名", width: 120},
                        //{field: "password", title: "密码", width: 80, template: "******"},
                        {
                            field: "locked", title: "锁定", width: 70,
                            template: "#= locked ? '是' : '否'#"
                        }
                        ,
                        {
                            field: "belongtoSysUserGroupCollection",
                            title: "部门",
                            width: 180,
                            sortable: false,
                            filterable: kendo.laputa.util.multiFilterableBuild("belongtoSysUserGroupCollection", sysUserGroupDataSource.data()),
                            template: kendo.laputa.util.buildEntityJoinTemplate("belongtoSysUserGroupCollection", sysUserGroupDataSource.data()),
                            editor: kendo.laputa.util.buildHierarchicaEditor(sysUserGroupDataSource.data())
                        },
                        {
                            field: "belongtoSysRoleCollection",
                            title: "角色",
                            width: 180,
                            sortable: false,
                            filterable: kendo.laputa.util.multiFilterableBuild("belongtoSysRoleCollection", sysRoleDataSource.data()),
                            template: kendo.laputa.util.buildEntityJoinTemplate("belongtoSysRoleCollection", sysRoleDataSource.data()),
                            editor: kendo.laputa.util.buildHierarchicaEditor(sysRoleDataSource.data())
                        }
                        ,
                        {
                            field: "descript", title: "描述", width: 100
                        }
                        ,
                        {
                            command: ["edit", {
                                text: "设置部门",
                                imageClass: "k-edit",
                                //className: "k-grid-edit",
                                iconClass: "k-icon",
                                click: function (e) {
                                    var tr = $(e.target).closest("tr");
                                    var data = this.dataItem(tr);
                                    window.sysUserData = data;
                                    window.configWindow =
                                        $("<div />").kendoWindow({
                                            title: kendo.format("设置{0} 部门", data.cname),
                                            actions: ["Refresh", "Maximize", "Close"],
                                            height: "90%",
                                            width: "40%",
                                            modal: true,
                                            content: kendo.format("${configBelongtoSysUserGroupCollectionUrl}", data.id),
                                            iframe: true
                                        }).data("kendoWindow");
                                    configWindow.center();
                                    configWindow.open();
                                }
                            }, {
                                text: "关联角色",
                                imageClass: "k-edit",
                                //className: "k-grid-edit",
                                iconClass: "k-icon",
                                click: function (e) {
                                    var tr = $(e.target).closest("tr");
                                    var data = this.dataItem(tr);
                                    window.sysUserData = data;
                                    window.configWindow =
                                        $("<div />").kendoWindow({
                                            title: kendo.format("关联{0} 角色", data.cname),
                                            actions: ["Refresh", "Maximize", "Close"],
                                            height: "90%",
                                            width: "40%",
                                            modal: true,
                                            content: kendo.format("${configBelongtoSysRoleCollectionUrl}", data.id),
                                            iframe: true
                                        }).data("kendoWindow");
                                    configWindow.center();
                                    configWindow.open();
                                }
                            },

                                <%--{--%>
                                <%--text: "修改密码",--%>
                                <%--imageClass: "k-edit",--%>
                                <%--//className: "k-grid-edit",--%>
                                <%--iconClass: "k-icon",--%>
                                <%--click: function (e) {--%>
                                <%--var tr = $(e.target).closest("tr");--%>
                                <%--var data = this.dataItem(tr);--%>
                                <%--window.sysUserData = data;--%>
                                <%--window.configWindow =--%>
                                <%--$("<div />").kendoWindow({--%>
                                <%--title: kendo.format("重置{0} 密码", data.cname),--%>
                                <%--actions: ["Refresh", "Maximize", "Close"],--%>
                                <%--//height: "90%",--%>
                                <%--//width: "40%",--%>
                                <%--modal: true,--%>
                                <%--resizable: false,--%>
                                <%--draggable: true,--%>
                                <%--visible: false,--%>
                                <%--content: kendo.format("${configPasswordUrl}", data.id),--%>
                                <%--iframe: true--%>
                                <%--}).data("kendoWindow");--%>
                                <%--configWindow.center();--%>
                                <%--configWindow.open();--%>
                                <%--}--%>
                                <%--},--%>
                                {
                                    text: "修改密码",
                                    imageClass: "k-edit",
                                    //className: "k-grid-edit",
                                    iconClass: "k-icon",
                                    click: function (e) {
                                        var tr = $(e.target).closest("tr");
                                        var data = this.dataItem(tr);
                                        window.sysUserData = data;

                                        function cancelConfig() {
                                            configWindow.close();
                                        }

                                        window.configWindow =
                                            $('<div id="configDiv" class="k-popup-edit-form">' +
                                                '<div class="k-edit-form-container">' +

                                                '<div class="k-edit-label">' +
                                                '<label >密码</label>' +
                                                '</div>' +
                                                '<div class="k-edit-field">' +
                                                '<input class="k-input k-textbox"  name="pwd" type="password">' +
                                                '</div>' +

                                                '<div class="k-edit-label">' +
                                                '<label >重复密码</label>' +
                                                '</div>' +
                                                '<div class="k-edit-field">' +
                                                '<input class="k-input k-textbox"  name="repeatPwd" type="password">' +
                                                '</div>' +

                                                '<div class="k-edit-buttons k-state-default">' +
                                                '<a class="k-button k-button-icontext k-primary k-grid-update" href="#">' +
                                                '<span class="k-icon k-update"/>更新' +
                                                '</a>' +
                                                '<a class="k-button k-button-icontext k-grid-cancel" href="#">' +
                                                '<span class="k-icon k-cancel"/>取消' +
                                                '</a>' +
                                                '</div>' +
                                                '</div>' +
                                                '</div>').kendoWindow({
                                                title: kendo.format("重置{0} 密码", data.cname),
                                                actions: ["Maximize", "Close"],
                                                //height: "90%",
                                                //width: "40%",
                                                modal: true,
                                                resizable: false,
                                                draggable: true,
                                                visible: false,
                                                position: {top: "10%", left: "30%"}
                                            }).data("kendoWindow");
                                        configWindow.open();
                                        $("#configDiv .k-grid-cancel").on("click", function () {
                                            configWindow.close();
                                        });
                                        $("#configDiv .k-grid-update").on("click", function () {
                                            var pwd = $("#configDiv [name='pwd']").val();
                                            var repeatPwd = $("#configDiv [name='repeatPwd']").val();
                                            if (pwd == null || pwd.length <= 0) {
                                                alert("请输入密码");
                                                return;
                                            }
                                            if (repeatPwd == null || repeatPwd.length <= 0) {
                                                alert("请重复输入密码");
                                                return;
                                            }
                                            if (pwd != repeatPwd) {
                                                alert("两次输入密码不一致");
                                                return;
                                            }

                                            var updateData = {id: sysUserData.id, password: pwd};

                                            $.ajax({
                                                    url: "${updatePasswordUrl}",
                                                    type: 'POST',
                                                    contentType: "application/json; charset=utf-8",
                                                    dataType: "json",
                                                    data: JSON.stringify(updateData)
                                                }
                                            ).success(function () {
                                                try {
                                                    alert("密码修改成功");
                                                    sysUserGrid.dataSource.read();
                                                    configWindow.close();
                                                } catch (e) {
                                                }
                                            });

                                        })
                                    }
                                }
                                , "destroy"], width: 260
                        }
//                    ,
//                    {
//                        field: "createdBy", title: "创建用户", width: 180
//                    }
//                    ,
//                    {
//                        field: "createdDate",
//                        title: "创建时间",
//                        width: 180,
//                        format: "{0:yyyy-MM-dd HH:mm:ss tt}",
//                        editor: kendo.laputa.util.dateTimePickerEditorBuild(),
//                        filterable: kendo.laputa.util.dateTimePickerFilterableBuild()
//                    }
//                    ,
//                    {
//                        field: "lastModifiedBy", title: "最后修改用户", width: 180
//                    }
//                    ,
//                    {
//                        field: "lastModifiedDate",
//                        title: "最后修改时间",
//                        width: 180,
//                        format: "{0:yyyy-MM-dd HH:mm:ss tt}",
//                        editor: kendo.laputa.util.dateTimePickerEditorBuild(),
//                        filterable: kendo.laputa.util.dateTimePickerFilterableBuild()
//                    }
                    ],
                    pageable: {
                        refresh: true,
                        pageSizes: true,
                        buttonCount: 5
                    }
                    ,
                    filterable: {
                        multi: true
                    }
                    ,
                    sortable: {
                        multi: true
                    }
                    ,

                    editable: "popup"
                }).data("kendoGrid");

        }

        kendo.laputa.util.dataSourceFetchQuen([sysRoleDataSource, sysUserGroupDataSource], initFun);

    });

</script>

</body>
</html>