<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/generate/sysfield/create" var="sysFieldCreateUrl"/>
<c:url value="/generate/sysfield/readDataSource" var="sysFieldReadUrl"/>
<c:url value="/generate/sysfield/update" var="sysFieldUpdateUrl"/>
<c:url value="/generate/sysfield/destory" var="sysFieldDestroyUrl"/>

<c:url value="/generate/sysentity/read" var="sysEntityReadUrl"/>

<!DOCTYPE html>
<html>
<head>
    <title>实体表列表</title>
    <meta charset="UTF-8">
    <jsp:include page="/WEB-INF/views/include/style/kendoStyle.jsp"/>
    <jsp:include page="/WEB-INF/views/include/js/requirejs.jsp"/>
</head>
<body>

<div id="sysFieldGrid"/>

<script>
    laputaKendoRequire(["kendo/js/kendo.grid", "kendo/third/dropdowntreeview/laputa.dropdown.treelist"], function () {


        var sysEntityDataSource = kendo.laputa.util.buildReadDataSource("${sysEntityReadUrl}");

        var initFun = function () {

            var sysFieldDataSource = new kendo.data.DataSource({
                page: 1,
                pageSize: 20,
                serverPaging: true,
                serverSorting: true,
                serverFiltering: true,
                transport: {
                    read: kendo.laputa.util.transportUrlBuild("${sysFieldReadUrl}"),
                    update: kendo.laputa.util.transportUrlBuild("${sysFieldUpdateUrl}"),
                    destroy: kendo.laputa.util.transportUrlBuild("${sysFieldDestroyUrl}"),
                    create: kendo.laputa.util.transportUrlBuild("${sysFieldCreateUrl}"),
                    parameterMap: kendo.laputa.util.parameterMap
                },
                schema: {
                    data: kendo.laputa.util.schemaData,
                    total: kendo.laputa.util.schemaTotal,
                    model: {
                        id: "id",
                        fields: {
                            id: {editable: false, nullable: false, defaultValue: null},
                            cname: {},
                            relationSysEntityId: {}
                        }
                    }
                }
            });

            $("#sysFieldGrid").kendoGrid({
                dataSource: sysFieldDataSource,
                toolbar: ["create"],
                columns: [
                    {field: "id", title: "主键", width: 180},
                    {field: "cname", title: "名字", width: 180},
                    {
                        field: "relationSysEntityId",
                        title: "字段关联实体",
                        width: 180,
                        filterable: kendo.laputa.util.filterableBuild(sysEntityDataSource.data()),
                        template: kendo.laputa.util.buildEntityJoinTemplate("relationSysEntityId", sysEntityDataSource),
                        editor: kendo.laputa.util.buildHierarchicaSelectInputEditor(sysEntityDataSource)
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

        kendo.laputa.util.dataSourceFetchQuen([sysEntityDataSource,], initFun);

    });

</script>

</body>
</html>