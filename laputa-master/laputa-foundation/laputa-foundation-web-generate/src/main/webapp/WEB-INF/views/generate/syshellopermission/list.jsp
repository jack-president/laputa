<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/generate/syshellopermission/create" var="sysHelloPermissionCreateUrl"/>
<c:url value="/generate/syshellopermission/readDataSource" var="sysHelloPermissionReadDataSourceUrl"/>
<c:url value="/generate/syshellopermission/update" var="sysHelloPermissionUpdateUrl"/>
<c:url value="/generate/syshellopermission/destory" var="sysHelloPermissionDestroyUrl"/>


<!DOCTYPE html>
<html>
<head>
    <title>Hi权限列表</title>
    <meta charset="UTF-8">
    <jsp:include page="/WEB-INF/views/include/style/kendoStyle.jsp"/>
    <jsp:include page="/WEB-INF/views/include/js/requirejs.jsp"/>
</head>
<body>

<div id="sysHelloPermissionGrid"/>

<script>
    laputaKendoRequire([ "kendo/js/kendo.grid"  ], function () {



        var initFun = function() {

            var sysHelloPermissionFilterDataSource = new kendo.data.DataSource({
                page: 1,
                pageSize: 20,
                serverPaging: true,
                serverSorting: true,
                serverFiltering: true,
                transport: {
                    read: kendo.laputa.util.transportUrlBuild("${sysHelloPermissionReadDataSourceUrl}"),
                    update: kendo.laputa.util.transportUrlBuild("${sysHelloPermissionUpdateUrl}"),
                    destroy: kendo.laputa.util.transportUrlBuild("${sysHelloPermissionDestroyUrl}"),
                    create: kendo.laputa.util.transportUrlBuild("${sysHelloPermissionCreateUrl}"),
                    parameterMap: kendo.laputa.util.parameterMap
                },
                schema: {
                    data: kendo.laputa.util.schemaData,
                    total: kendo.laputa.util.schemaTotal,
                    model: {
                        id: "id",
                        fields: {
                            id: {editable: false ,nullable: false,defaultValue: null},
                            cname: { }
                        }
                    }
                }
            });

            $("#sysHelloPermissionGrid").kendoGrid({
                dataSource: sysHelloPermissionFilterDataSource,
                toolbar: ["create"],
                columns: [
                            {field: "id", title: "主键", width: 180},
                            {field: "cname", title: "名字", width: 180},
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

        kendo.laputa.util.dataSourceFetchQuen([], initFun);

    });

</script>

</body>
</html>