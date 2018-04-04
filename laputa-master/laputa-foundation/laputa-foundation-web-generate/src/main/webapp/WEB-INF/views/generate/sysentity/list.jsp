<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/generate/sysentity/create" var="sysEntityCreateUrl"/>
<c:url value="/generate/sysentity/readDataSource" var="sysEntityReadUrl"/>
<c:url value="/generate/sysentity/update" var="sysEntityUpdateUrl"/>
<c:url value="/generate/sysentity/destory" var="sysEntityDestroyUrl"/>

<c:url value="/generate/sysfield/read" var="sysFieldReadUrl"/>

<!DOCTYPE html>
<html>
<head>
    <title>实体表列表</title>
    <meta charset="UTF-8">
    <link href="<c:url value='/static/kendo/third/LaputaTreeViewCheckboxes/style/laputa.treeview.checkboxes.css'/>"
          rel="stylesheet">
    <jsp:include page="/WEB-INF/views/include/style/kendoStyle.jsp"/>
    <jsp:include page="/WEB-INF/views/include/js/requirejs.jsp"/>
</head>
<body>

<div id="sysEntityGrid"/>

<script>
    laputaKendoRequire(["kendo/js/kendo.grid", "kendo/third/LaputaTreeViewCheckboxes/laputa.treeview.checkboxes"], function () {


        var sysFieldDataSource = kendo.laputa.util.buildReadDataSource("${sysFieldReadUrl}");

        var initFun = function () {

            var sysEntityDataSource = new kendo.data.DataSource({
                page: 1,
                pageSize: 20,
                serverPaging: true,
                serverSorting: true,
                serverFiltering: true,
                transport: {
                    read: kendo.laputa.util.transportUrlBuild("${sysEntityReadUrl}"),
                    update: kendo.laputa.util.transportUrlBuild("${sysEntityUpdateUrl}"),
                    destroy: kendo.laputa.util.transportUrlBuild("${sysEntityDestroyUrl}"),
                    create: kendo.laputa.util.transportUrlBuild("${sysEntityCreateUrl}"),
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
                            clazzName: {},
                            sysFieldCollection: {defaultValue: null}
                        }
                    }
                }
            });

            $("#sysEntityGrid").kendoGrid({
                dataSource: sysEntityDataSource,
                toolbar: ["create"],
                columns: [
                    {field: "id", title: "主键", width: 180},
                    {field: "cname", title: "名字", width: 180},
                    {field: "clazzName", title: "类名", width: 180},
                    {
                        field: "sysFieldCollection",
                        title: "字段集合",
                        width: 180,
                        sortable: false,
                        filterable: kendo.laputa.util.multiFilterableBuild("sysFieldCollection", sysFieldDataSource),
                        template: kendo.laputa.util.buildEntityJoinTemplate("sysFieldCollection", sysFieldDataSource),
                        editor: kendo.laputa.util.buildHierarchicaEditor(sysFieldDataSource.data())
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

                editable: "inline"
            });

        }

        kendo.laputa.util.dataSourceFetchQuen([sysFieldDataSource,], initFun);

    });

</script>

</body>
</html>