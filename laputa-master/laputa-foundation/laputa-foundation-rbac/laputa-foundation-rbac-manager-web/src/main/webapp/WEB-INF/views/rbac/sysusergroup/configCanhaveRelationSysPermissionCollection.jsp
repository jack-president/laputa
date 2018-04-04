<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/rbac/sysusergroup/updateHaveSysPermission" var="updateHaveSysPermissionUrl"/>
<c:url value="/rbac/sysusergroup/readHaveSysPermission/{0}" var="readHaveSysPermissionUrl"/>
<c:url value="/rbac/sysusergroup/readNotHaveSysPermission/{0}" var="readNotHaveSysPermissionUrl"/>


<!DOCTYPE html>
<html>
<head>
    <title>配置部门权限</title>
    <meta charset="UTF-8">
    <link href="<c:url value='/static/kendo/third/LaputaTreeViewCheckboxes/style/laputa.treeview.checkboxes.css'/>"
          rel="stylesheet">
    <jsp:include page="/WEB-INF/views/include/style/kendoStyle.jsp"/>
    <jsp:include page="/WEB-INF/views/include/js/requirejs.jsp"/>
</head>
<body>

<div>
    <div id="leftPermission"></div>
    <div id="buttonDiv" align="center" hidden="hidden">
        <button id="switchButtonLeft" class="k-primary">↓↓↓</button>
        <button id="switchButtonRight" class="k-primary">↑↑↑</button>
        <button id="submitButton" class="k-primary">下方列表为部门拥有权限</button>
    </div>
    <div id="rightPermission"></div>
</div>


<script>
    laputaKendoRequire(["kendo/js/kendo.button", "kendo/js/kendo.grid"], function () {
        function makeDataSource(dataSourceUrl) {
            var dataSource = new kendo.data.DataSource({
                page: 1,
                pageSize: "20",
                serverPaging: false,
                serverSorting: false,

                transport: {
                    read: {
                        url: dataSourceUrl,
                        dataType: "json",
                        type: "GET"
                    }
                }
            });
            return dataSource;
        };

        function makeKendoGrid(divId, dataSource) {
            var grid =
                $("#" + divId).kendoGrid({
                    dataSource: dataSource,
                    selectable: "multiple, row",
                    persistSelection: true,
                    columns: [
                        {field: "id", title: "主键", width: 180},
                        {field: "cname", title: "名称", width: 80},
                        {field: "code", title: "编码", width: 140},
                        {field: "descript", title: "描述", width: 80}
                    ],
                    pageable: {
                        refresh: false,
                        pageSizes: true,
                        buttonCount: 5
                    },
                }).data("kendoGrid");
            return grid;
        };

        function gridSwitch(lg, rg) {
            var select = lg.select();
            if (select.length > 0) {
                var data = [];

                for (var i = 0; i < select.length; ++i) {
                    var dataItem = lg.dataItem(select[i]);
                    if ($.inArray(dataItem, data) < 0) {
                        data.push(dataItem);
                    }
                }
                while (data.length > 0) {
                    var e = data.pop();
                    lg.dataSource.remove(e);
                    rg.dataSource.add(e);
                }
            }
        };

        var leftSysPermissionFilterDataSource = makeDataSource(kendo.format("${readNotHaveSysPermissionUrl}", ${sysUserGroupId}));
        var rightSysPermissionFilterDataSource = makeDataSource(kendo.format("${readHaveSysPermissionUrl}", ${sysUserGroupId}));

        var initFun = function () {
            window.leftGrid = makeKendoGrid("leftPermission", leftSysPermissionFilterDataSource);

            window.rightGrid = makeKendoGrid("rightPermission", rightSysPermissionFilterDataSource);


            var buttonLeft = $("#switchButtonLeft").kendoButton().data("kendoButton");
            buttonLeft.bind("click", function () {
                gridSwitch(leftGrid, rightGrid);
            });

            var buttonRight = $("#switchButtonRight").kendoButton().data("kendoButton");
            buttonRight.bind("click", function () {
                gridSwitch(rightGrid, leftGrid);
            });

            var submitButton = $("#submitButton").kendoButton().data("kendoButton");

            submitButton.bind("click", function () {
                if (!confirm("确认提交更改")) {
                    return;
                }
                var data = {
                    id: ${sysUserGroupId},
                    canhaveRelationSysPermissionCollection: []
                };

                var selectItem = rightGrid.dataItems();
                if(selectItem != null && selectItem.length > 0){
                    for(var i = 0; i < selectItem.length; ++i){
                        if ($.inArray(data.canhaveRelationSysPermissionCollection, selectItem[i].id) < 0) {
                            data.canhaveRelationSysPermissionCollection.push(selectItem[i].id);
                        }
                    }
                }

                $.ajax({
                        url: "${updateHaveSysPermissionUrl}",
                        type: 'POST',
                        contentType: "application/json; charset=utf-8",
                        dataType: "json",
                        data: JSON.stringify(data)
                    }
                ).success(function () {
                    try {
                        window.parent.sysUserGroupGrid.dataSource.read();
                        window.parent.configWindow.close();
                    }catch (e){
                        console.log(e);
                    }
                });
            });

            buttonLeft.enable(false);
            buttonRight.enable(false);
            submitButton.enable(false);

            window.sb = submitButton;

            leftGrid.bind("change", function () {
                submitButton.enable(true);
                $("#submitButton").text("提交更改");
                if (leftGrid.select() != null && leftGrid.select().length > 0) {
                    buttonLeft.enable(true);
                    if (rightGrid.select() != null && rightGrid.select().length > 0) {
                        rightGrid.clearSelection();
                    }
                } else {
                    buttonLeft.enable(false);
                }
            });

            rightGrid.bind("change", function () {
                submitButton.enable(true);
                $("#submitButton").text("提交更改");
                if (rightGrid.select() != null && rightGrid.select().length > 0) {
                    buttonRight.enable(true);
                    if (leftGrid.select() != null && leftGrid.select().length > 0) {
                        leftGrid.clearSelection();
                    }
                } else {
                    buttonRight.enable(false);
                }
            });

            kendo.laputa.util.grid.eventRowDoubleClick(leftGrid, function (dataItem) {
                leftGrid.dataSource.remove(dataItem);
                rightGrid.dataSource.add(dataItem);
                buttonLeft.enable(false);
                buttonRight.enable(false);
            });

            kendo.laputa.util.grid.eventRowDoubleClick(rightGrid, function (dataItem) {
                rightGrid.dataSource.remove(dataItem);
                leftGrid.dataSource.add(dataItem);
                buttonLeft.enable(false);
                buttonRight.enable(false);
            });


            $("#buttonDiv").show();
        };

        kendo.laputa.util.dataSourceFetchQuen([leftSysPermissionFilterDataSource, rightSysPermissionFilterDataSource], initFun);
    });
</script>
</body>
</html>