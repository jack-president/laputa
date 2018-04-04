<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/generate/syshellodate/create" var="sysHelloDateCreateUrl"/>
<c:url value="/generate/syshellodate/readDataSource" var="sysHelloDateReadDataSourceUrl"/>
<c:url value="/generate/syshellodate/update" var="sysHelloDateUpdateUrl"/>
<c:url value="/generate/syshellodate/destory" var="sysHelloDateDestroyUrl"/>


<!DOCTYPE html>
<html>
<head>
    <title>Hi时间列表</title>
    <meta charset="UTF-8">
    <jsp:include page="/WEB-INF/views/include/style/kendoStyle.jsp"/>
    <jsp:include page="/WEB-INF/views/include/js/requirejs.jsp"/>
</head>
<body>

<div id="sysHelloDateGrid"/>

<script>
    laputaKendoRequire(["kendo/js/kendo.grid", "kendo/js/kendo.datetimepicker"], function () {


        var initFun = function () {

            var sysHelloDateFilterDataSource = new kendo.data.DataSource({
                page: 1,
                pageSize: 20,
                serverPaging: true,
                serverSorting: true,
                serverFiltering: true,
                transport: {
                    read: kendo.laputa.util.transportUrlBuild("${sysHelloDateReadDataSourceUrl}"),
                    update: kendo.laputa.util.transportUrlBuild("${sysHelloDateUpdateUrl}"),
                    destroy: kendo.laputa.util.transportUrlBuild("${sysHelloDateDestroyUrl}"),
                    create: kendo.laputa.util.transportUrlBuild("${sysHelloDateCreateUrl}"),
                    parameterMap: kendo.laputa.util.parameterMap
                },
                schema: {
                    data: kendo.laputa.util.schemaData,
                    total: kendo.laputa.util.schemaTotal,
                    model: {
                        id: "id",
                        fields: {
                            id: {editable: false, nullable: false, defaultValue: null},
                            cname: {editable: true},
                            wakeup: {editable: true, type: "date"},
                            birthDate: {editable: true, type: "date"},
                            acTime: {editable: true, type: "date"},
                            createdBy: {editable: false, nullable: false},
                            createdDate: {editable: false, nullable: false, type: "date"},
                            lastModifiedBy: {editable: false},
                            lastModifiedDate: {editable: false, type: "date"}
                        }
                    }
                }
            });

            $("#sysHelloDateGrid").kendoGrid({
                dataSource: sysHelloDateFilterDataSource,
                toolbar: ["create"],
                columns: [
                    {field: "id", title: "主键", width: 180},
                    {field: "cname", title: "名字", width: 180},
                    {
                        field: "wakeup",
                        title: "闹钟",
                        width: 180,
                        format: "{0:HH:mm:ss tt}",
                        editor: kendo.laputa.util.timePickerEditorBuild(),
                        filterable: kendo.laputa.util.timePickerFilterableBuild()
                    },
                    {
                        field: "birthDate",
                        title: "生日",
                        width: 180,
                        format: "{0:yyyy-MM-dd}",
                        editor: kendo.laputa.util.datePickerEditorBuild(),
                        filterable: kendo.laputa.util.datePickerFilterableBuild()
                    },
                    {
                        field: "acTime",
                        title: "激活时间",
                        width: 180,
                        format: "{0:yyyy-MM-dd HH:mm:ss tt}",
                        editor: kendo.laputa.util.dateTimePickerEditorBuild(),
                        filterable: kendo.laputa.util.dateTimePickerFilterableBuild()
                    },
                    {field: "createdBy", title: "创建用户", width: 180},
                    {
                        field: "createdDate",
                        title: "创建时间",
                        width: 180,
                        format: "{0:yyyy-MM-dd HH:mm:ss tt}",
                        editor: kendo.laputa.util.dateTimePickerEditorBuild(),
                        filterable: kendo.laputa.util.dateTimePickerFilterableBuild()
                    },
                    {field: "lastModifiedBy", title: "最后修改用户", width: 180},
                    {
                        field: "lastModifiedDate",
                        title: "最后修改时间",
                        width: 180,
                        format: "{0:yyyy-MM-dd HH:mm:ss tt}",
                        editor: kendo.laputa.util.dateTimePickerEditorBuild(),
                        filterable: kendo.laputa.util.dateTimePickerFilterableBuild()
                    },
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