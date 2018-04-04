<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/rbac/sysoperation/read" var="sysOperationReadUrl"/>
<c:url value="/rbac/syspermission/updateOwnSysOperationCollection" var="updateOwnSysOperationCollectionUrl"/>

<!DOCTYPE html>
<html>
<head>
    <title>权限关联操作</title>
    <meta charset="UTF-8">
    <link href="<c:url value='/static/kendo/third/LaputaTreeViewCheckboxes/style/laputa.treeview.checkboxes.css'/>"
          rel="stylesheet">
    <jsp:include page="/WEB-INF/views/include/style/kendoStyle.jsp"/>
    <jsp:include page="/WEB-INF/views/include/js/requirejs.jsp"/>

</head>
<body class="k-content">
<table id="showTable" hidden="hidden">
    <colgroup>
        <col width="10%">
        <col width="90%">
    </colgroup>
    <thead>
    <tr>
        <th></th>
        <th>
            <button id="zk">展开所有</button>
            <button id="sq">收起所有</button>
            <button id="qx">全选</button>
            <button id="fx">反选</button>
            <button id="tj">提交更改</button>
        </th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td></td>
        <td>
            <select id="sysOperationSelect"></select>
        </td>
    </tr>
    </tbody>
</table>
<table id="showTable2" hidden="hidden">
</table>

<script>
    laputaKendoRequire(["kendo/js/kendo.grid", "kendo/js/kendo.button", "kendo/third/LaputaTreeViewCheckboxes/laputa.treeview.checkboxes", "kendo/js/kendo.datetimepicker"], function () {

        var sysOperationDataSource = kendo.laputa.util.buildReadDataSource("${sysOperationReadUrl}");
        var initFun = function () {
            var hierarchicaData = kendo.laputa.util.hierarchica(sysOperationDataSource.data());
            window.select =
                $("#sysOperationSelect").kendoLaputaTreeviewCheckboxes({
                    treeview: {
                        checkboxes: {
                            checkChildren: true
                        },
                        dataTextField: "cname",
                        dataSpriteCssClassField: "icon",
                        dataUrlField: "_ignore_url_",
                        dataSource: hierarchicaData
                    }
                }).data("kendoLaputaTreeviewCheckboxes");

            select.value(window.parent.sysPermissionData.ownSysOperationCollection);

            window.treeview = select.treeview();

            var zk = $("#zk").kendoButton().data("kendoButton");
            var sq = $("#sq").kendoButton().data("kendoButton");
            var qx = $("#qx").kendoButton().data("kendoButton");
            var fx = $("#fx").kendoButton().data("kendoButton");
            var tj = $("#tj").kendoButton().data("kendoButton");

            zk.bind("click", function () {
                select.expandAll();
            });
            sq.bind("click", function () {
                select.collapseAll();
            });

            qx.bind("click", function () {
                select.checkAll(true);
            });
            fx.bind("click", function () {
                select.checkAll(false);
            });
            tj.bind("click", function () {
                if (!confirm("确认提交更改")) {
                    return;
                }
                var data = {
                    id: ${sysPermissionId},
                    ownSysOperationCollection: []
                };
                if (select.value() != null) {
                    data.ownSysOperationCollection = select.value();
                }
                $.ajax({
                        url: "${updateOwnSysOperationCollectionUrl}",
                        type: 'POST',
                        contentType: "application/json; charset=utf-8",
                        dataType: "json",
                        data: JSON.stringify(data)
                    }
                ).success(function () {
                    try {
                        window.parent.sysPermissionGrid.dataSource.read();
                        window.parent.configWindow.close();
                    } catch (e) {
                        console.log(e);
                    }
                });

            });

            $("#showTable").show();
        };

        kendo.laputa.util.dataSourceFetchQuen([sysOperationDataSource], initFun);

    });
</script>
</body>
</html>