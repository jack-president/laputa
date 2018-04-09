<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/logger/manager/query" var="loggerUrl"/>

<!DOCTYPE html>
<html>
<head>
    <title>日志管理中心</title>
    <meta charset="UTF-8">
    <link href="<c:url value='/static/kendo/third/LaputaTreeViewCheckboxes/style/laputa.treeview.checkboxes.css'/>"
          rel="stylesheet">
    <jsp:include page="/WEB-INF/views/include/style/kendoStyle.jsp"/>
    <jsp:include page="/WEB-INF/views/include/js/requirejs.jsp"/>

</head>
<body>

<input id="transIdInput"/>
<button id="queryButton">查询</button>

<div style="height: 1000px" class="console" id="logConsole"/>


<script>
    laputaKendoRequire(["kendo/js/kendo.grid", "kendo/js/kendo.grid", "kendo/js/kendo.button", "kendo/content/shared/js/console", "kendo/js/kendo.tabstrip", "kendo/js/kendo.editor", "kendo/third/dropdowntreeview/laputa.dropdown.treelist", "kendo/third/LaputaTreeViewCheckboxes/laputa.treeview.checkboxes", "kendo/js/kendo.datetimepicker"], function () {

            var initFun = function () {

                window.kendoButton = $("#queryButton").kendoButton(
                    {
                        click: function () {
                            $("#logConsole").empty();
                            $.ajax({
                                    url: "${loggerUrl}?transId="+$("#transIdInput").val(),
                                    type: 'POST',
                                    contentType: "application/json; charset=utf-8",
                                    dataType: "json",
                                    //data: {transId: $("#transIdInput").val()}
                                }
                            ).success(function (data) {
                                try {
                                    var pos = data.length -1;
                                    while (pos >= 0) {
                                        kendoConsole.log(data[pos--].message);
                                    }
                                } catch (e) {
                                }
                            });
                            console.log(data.data());
                        }
                    }
                ).data("kendoButton");

            }

            kendo.laputa.util.dataSourceFetchQuen([], initFun);
        }
    );

</script>


</body>
</html>