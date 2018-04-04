<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/weixin/weixinuser/create" var="weixinUserCreateUrl"/>
<c:url value="/weixin/weixinuser/readDataSource" var="weixinUserReadDataSourceUrl"/>
<c:url value="/weixin/weixinuser/update" var="weixinUserUpdateUrl"/>
<c:url value="/weixin/weixinuser/destory" var="weixinUserDestroyUrl"/>


<!DOCTYPE html>
<html>
<head>
    <title>微信用户列表</title>
    <meta charset="UTF-8">
    <jsp:include page="/WEB-INF/views/include/style/kendoStyle.jsp"/>
    <jsp:include page="/WEB-INF/views/include/js/requirejs.jsp"/>
</head>
<body>

<div id="weixinUserGrid"/>

<script>
    laputaKendoRequire([ "kendo/js/kendo.grid" ,"kendo/js/kendo.datetimepicker" ], function () {



        var initFun = function() {

            var weixinUserFilterDataSource = new kendo.data.DataSource({
                page: 1,
                pageSize: 20,
                serverPaging: true,
                serverSorting: true,
                serverFiltering: true,
                error: kendo.laputa.util.dataSourceErrorHandle,
                transport: {
                    read: kendo.laputa.util.transportUrlBuild("${weixinUserReadDataSourceUrl}"),
                    update: kendo.laputa.util.transportUrlBuild("${weixinUserUpdateUrl}"),
                    destroy: kendo.laputa.util.transportUrlBuild("${weixinUserDestroyUrl}"),
                    create: kendo.laputa.util.transportUrlBuild("${weixinUserCreateUrl}"),
                    parameterMap: kendo.laputa.util.parameterMap
                },
                schema: {
                    data: kendo.laputa.util.schemaData,
                    total: kendo.laputa.util.schemaTotal,
                    model: {
                        id: "id",
                        fields: {
                            id: {editable:  false  ,nullable: false,defaultValue: null},
                            subscribe: {editable:  true  },
                            openid: {editable:  true  },
                            nickname: {editable:  true  },
                            nicknameEmoji: {editable:  true  },
                            sex: {editable:  true  },
                            language: {editable:  true  },
                            city: {editable:  true  },
                            province: {editable:  true  },
                            country: {editable:  true  },
                            headimgurl: {editable:  true  },
                            subscribeTime: {editable:  true  },
                            privilege: {editable:  true  },
                            unionid: {editable:  true  },
                            groupid: {editable:  true  },
                            remark: {editable:  true  },
                            tagidList: {editable:  true  },
                            weixinCode: {editable:  true  },
                            createdBy: {editable:  false  ,nullable: false},
                            createdDate: {editable:  false  ,nullable: false,type: "date"},
                            lastModifiedBy: {editable:  false  },
                            lastModifiedDate: {editable:  false  ,type: "date"}
                        }
                    }
                }
            });

            $("#weixinUserGrid").kendoGrid({
                dataSource: weixinUserFilterDataSource,
                toolbar: ["create"],
                columns: [
                            {field: "id", title: "主键", width: 180},
                            {field: "subscribe", title: "是否订阅", width: 180},
                            {field: "openid", title: "openid", width: 180},
                            {field: "nickname", title: "昵称", width: 180},
                            {field: "nicknameEmoji", title: "昵称表情", width: 180},
                            {field: "sex", title: "性别", width: 180},
                            {field: "language", title: "语言", width: 180},
                            {field: "city", title: "城市", width: 180},
                            {field: "province", title: "省份", width: 180},
                            {field: "country", title: "国家", width: 180},
                            {field: "headimgurl", title: "头像", width: 180},
                            {field: "subscribeTime", title: "订阅时间", width: 180},
                            {field: "privilege", title: "特权", width: 180},
                            {field: "unionid", title: "unionid", width: 180},
                            {field: "groupid", title: "groupid", width: 180},
                            {field: "remark", title: "remark", width: 180},
                            {field: "tagidList", title: "tagidList", width: 180},
                            {field: "weixinCode", title: "所属微信编码", width: 180},
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

                editable: "inline"
            });

        }

        kendo.laputa.util.dataSourceFetchQuen([], initFun);

    });

</script>

</body>
</html>