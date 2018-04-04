<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/weixin/weixinbaseconfig/create" var="weixinBaseConfigCreateUrl"/>
<c:url value="/weixin/weixinbaseconfig/readDataSource" var="weixinBaseConfigReadDataSourceUrl"/>
<c:url value="/weixin/weixinbaseconfig/update" var="weixinBaseConfigUpdateUrl"/>
<c:url value="/weixin/weixinbaseconfig/destory" var="weixinBaseConfigDestroyUrl"/>


<!DOCTYPE html>
<html>
<head>
    <title>微信公众号基础配置列表</title>
    <meta charset="UTF-8">
    <jsp:include page="/WEB-INF/views/include/style/kendoStyle.jsp"/>
    <jsp:include page="/WEB-INF/views/include/js/requirejs.jsp"/>
</head>
<body>

<div id="weixinBaseConfigGrid"/>

<script>
    laputaKendoRequire([ "kendo/js/kendo.grid" ,"kendo/js/kendo.datetimepicker" ], function () {



        var initFun = function() {

            var weixinBaseConfigFilterDataSource = new kendo.data.DataSource({
                page: 1,
                pageSize: 20,
                serverPaging: true,
                serverSorting: true,
                serverFiltering: true,
                error: kendo.laputa.util.dataSourceErrorHandle,
                transport: {
                    read: kendo.laputa.util.transportUrlBuild("${weixinBaseConfigReadDataSourceUrl}"),
                    update: kendo.laputa.util.transportUrlBuild("${weixinBaseConfigUpdateUrl}"),
                    destroy: kendo.laputa.util.transportUrlBuild("${weixinBaseConfigDestroyUrl}"),
                    create: kendo.laputa.util.transportUrlBuild("${weixinBaseConfigCreateUrl}"),
                    parameterMap: kendo.laputa.util.parameterMap
                },
                schema: {
                    data: kendo.laputa.util.schemaData,
                    total: kendo.laputa.util.schemaTotal,
                    model: {
                        id: "id",
                        fields: {
                            id: {editable:  false  ,nullable: false,defaultValue: null},
                            cname: {editable:  true  ,nullable: false},
                            code: {editable:  true  ,nullable: false},
                            descript: {editable:  true  },
                            appid: {editable:  true  ,nullable: false},
                            appsecret: {editable:  true  ,nullable: false},
                            token: {editable:  true  ,nullable: false},
                            createdBy: {editable:  false  ,nullable: false},
                            createdDate: {editable:  false  ,nullable: false,type: "date"},
                            lastModifiedBy: {editable:  false  },
                            lastModifiedDate: {editable:  false  ,type: "date"}
                        }
                    }
                }
            });

            $("#weixinBaseConfigGrid").kendoGrid({
                dataSource: weixinBaseConfigFilterDataSource,
                toolbar: ["create"],
                columns: [
                            {field: "id", title: "主键", width: 180},
                            {field: "cname", title: "名称", width: 180},
                            {field: "code", title: "编码", width: 180},
                            {field: "descript", title: "描述", width: 180},
                            {field: "appid", title: "appid", width: 180},
                            {field: "appsecret", title: "appsecret", width: 180},
                            {field: "token", title: "token", width: 180},
                            {field: "createdBy", title: "创建用户", width: 180},
                            {field: "createdDate", title: "创建时间", width: 180,format: "{0:yyyy-MM-dd HH:mm:ss tt}",editor:kendo.laputa.util.dateTimePickerEditorBuild(),filterable: kendo.laputa.util.dateTimePickerFilterableBuild()},
                            {field: "lastModifiedBy", title: "最后修改用户", width: 180},
                            {field: "lastModifiedDate", title: "最后修改时间", width: 180,format: "{0:yyyy-MM-dd HH:mm:ss tt}",editor:kendo.laputa.util.dateTimePickerEditorBuild(),filterable: kendo.laputa.util.dateTimePickerFilterableBuild()},
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

                editable: "popup"
            });

        }

        kendo.laputa.util.dataSourceFetchQuen([], initFun);

    });

</script>

</body>
</html>