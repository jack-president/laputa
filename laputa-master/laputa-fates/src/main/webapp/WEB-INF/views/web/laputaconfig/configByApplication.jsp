<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/web/laputaconfig/${laputaAplication.code}/create" var="laputaConfigCreateUrl"/>
<c:url value="/web/laputaconfig/${laputaAplication.code}/readDataSource" var="laputaConfigReadDataSourceUrl"/>
<c:url value="/web/laputaconfig/${laputaAplication.code}/readHistoryDataSource"
       var="laputaConfigReadHistoryDataSourceUrl"/>
<c:url value="/web/laputaconfig/${laputaAplication.code}/update" var="laputaConfigUpdateUrl"/>
<c:url value="/web/laputaconfig/${laputaAplication.code}/syn" var="laputaConfigSynUrl"/>
<c:url value="/web/laputaconfig/${laputaAplication.code}/destory" var="laputaConfigDestroyUrl"/>

<c:url value="/web/laputaaplication/read" var="laputaAplicationReadUrl"/>
<c:url value="/web/laputaconfighistory/read" var="laputaConfigHistoryReadUrl"/>

<!DOCTYPE html>
<html>
<head>
    <title>配置 ${laputaAplication.cname}</title>
    <meta charset="UTF-8">
    <link href="<c:url value='/static/kendo/third/LaputaTreeViewCheckboxes/style/laputa.treeview.checkboxes.css'/>"
          rel="stylesheet">
    <jsp:include page="/WEB-INF/views/include/style/kendoStyle.jsp"/>
    <jsp:include page="/WEB-INF/views/include/js/requirejs.jsp"/>

</head>
<body>

<div id="laputaConfigGrid"/>

<script type="text/x-kendo-template" id="historyTemplate">
    <div class="tabstrip">
        <ul>
            <li class="k-state-active">
                修改历史记录
            </li>
            <li>
                配置项说明
            </li>
        </ul>
        <div>
            <div class="grid"></div>
        </div>
        <div>
            <div>
                #= descript #
            </div>
        </div>
    </div>

</script>

<script type="text/x-kendo-template" id="configValueTemplate">
    #if(configValue == cloudValue){#
    #if(configValue == null){#
    <a style="color: blue">null</a>
    #}else{#
    <a>#: configValue #</a>
    #}#
    <strong><a style="color: limegreen;">已同步</a></strong>
    #}else{#
    #if(configValue == null){#
    <a style="color: blue">null</a>
    #}else{#
    <a>#: configValue #</a>
    #}#
    <a>|</a>
    #if(cloudValue == null){#
    <a style="color: blue">null</a>
    #}else{#
    <a>#: cloudValue #</a>
    #}#

    <strong><a style="color: red">未同步</a></strong>
    #}#
</script>


<script>
    laputaKendoRequire(["kendo/js/kendo.grid", "kendo/js/kendo.tabstrip", "kendo/js/kendo.editor", "kendo/third/dropdowntreeview/laputa.dropdown.treelist", "kendo/third/LaputaTreeViewCheckboxes/laputa.treeview.checkboxes", "kendo/js/kendo.datetimepicker"], function () {


        var laputaAplicationDataSource = kendo.laputa.util.buildReadDataSource("${laputaAplicationReadUrl}");
        var laputaConfigHistoryDataSource = kendo.laputa.util.buildReadDataSource("${laputaConfigHistoryReadUrl}");

        var initFun = function () {

            window.laputaConfigFilterDataSource = new kendo.data.DataSource({
                page: 1,
                pageSize: 20,
                serverPaging: true,
                serverSorting: true,
                serverFiltering: true,
                error: kendo.laputa.util.dataSourceErrorHandle,
                transport: {
                    read: kendo.laputa.util.transportUrlBuild("${laputaConfigReadDataSourceUrl}"),
                    update: kendo.laputa.util.transportUrlBuild("${laputaConfigUpdateUrl}"),
                    destroy: kendo.laputa.util.transportUrlBuild("${laputaConfigDestroyUrl}"),
                    create: kendo.laputa.util.transportUrlBuild("${laputaConfigCreateUrl}"),
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
                            code: {editable: true, nullable: false},
                            parentLaputaAplicationId: {defaultValue: ${laputaAplication.id}, editable: false},
                            configValue: {editable: true, nullable: false},
                            cloudValue: {editable: false},
                            configHistoryList: {defaultValue: null, editable: false},
                            descript: {editable: true},
                            createdBy: {editable: false, nullable: false},
                            createdDate: {editable: false, nullable: false, type: "date"},
                            lastModifiedBy: {editable: false},
                            lastModifiedDate: {editable: false, type: "date"}
                        }
                    }
                }
            });

            window.laputaConfigGrid =
                $("#laputaConfigGrid").kendoGrid({
                    dataSource: laputaConfigFilterDataSource,
                    toolbar: ["create"],
                    height: window.innerHeight,
                    detailTemplate: kendo.template($("#historyTemplate").html()),
                    detailInit: historyDetailInit,
                    columns: [
                        // {field: "id", title: "主键", width: 180},
                        {field: "cname", title: "名称", width: 180},
                        {field: "code", title: "编码KEY", width: 180},
                        // {
                        //     field: "parentLaputaAplicationId",
                        //     title: "所属项目",
                        //     width: 180,
                        //     filterable: kendo.laputa.util.filterableBuild(laputaAplicationDataSource.data()),
                        //     template: kendo.laputa.util.buildEntityJoinTemplate("parentLaputaAplicationId", laputaAplicationDataSource.data()),
                        //     editor: kendo.laputa.util.buildHierarchicaSelectInputEditor(laputaAplicationDataSource.data())
                        // },
                        {
                            field: "configValue",
                            title: "配置值",
                            width: 180,
                            template: kendo.template($("#configValueTemplate").html())
                        },
                        //{field: "cloudValue", title: "配置中心值", width: 180},
                        // {
                        //     field: "configHistoryList",
                        //     title: "配置值历史",
                        //     width: 180,
                        //     sortable: false,
                        //     filterable: kendo.laputa.util.multiFilterableBuild("configHistoryList", laputaConfigHistoryDataSource.data()),
                        //     template: kendo.laputa.util.buildEntityJoinTemplate("configHistoryList", laputaConfigHistoryDataSource.data()),
                        //     editor: kendo.laputa.util.buildHierarchicaEditor(laputaConfigHistoryDataSource.data())
                        // },
                        {
                            field: "descript", title: "描述", width: 180, hidden: true,

                            editor: function (container, options) {
                                // create an input element
                                var input = $("<textarea />");
                                // set its name to the field to which the column is bound ('name' in this case)
                                input.attr("name", options.field);
                                // append it to the container
                                input.appendTo(container);

                                input.kendoEditor({
                                    messages: {
                                        editAreaTitle: "描述信息"
                                    }
                                });
                                // initialize a Kendo UI AutoComplete
                                // input.kendoAutoComplete({
                                //     dataTextField: "name",
                                //     dataSource: [
                                //         {name: "Jane Doe"},
                                //         {name: "John Doe"}
                                //     ]
                                // });
                            }
                        },


                        // {field: "createdBy", title: "创建用户", width: 180},
                        // {
                        //     field: "createdDate",
                        //     title: "创建时间",
                        //     width: 180,
                        //     format: "{0:yyyy-MM-dd HH:mm:ss tt}",
                        //     editor: kendo.laputa.util.dateTimePickerEditorBuild(),
                        //     filterable: kendo.laputa.util.dateTimePickerFilterableBuild()
                        // },
                        {field: "lastModifiedBy", title: "最后修改用户", width: 180},
                        {
                            field: "lastModifiedDate",
                            title: "最后修改时间",
                            width: 180,
                            format: "{0:yyyy-MM-dd HH:mm:ss tt}",
                            editor: kendo.laputa.util.dateTimePickerEditorBuild(),
                            filterable: kendo.laputa.util.dateTimePickerFilterableBuild()
                        },
                        {
                            command: [
                                {
                                    text: "配置值",
                                    imageClass: "k-edit",
                                    //className: "k-grid-edit",
                                    iconClass: "k-icon",
                                    click: function (e) {
                                        var tr = $(e.target).closest("tr");
                                        var data = this.dataItem(tr);
                                        window.laputaConfig = data;

                                        function cancelConfig() {
                                            this.destroy();
                                        }

                                        window.configWindow =
                                            $('<div id="configDiv" class="k-popup-edit-form">' +
                                                '<div class="k-edit-form-container">' +

                                                '<div class="k-edit-label">' +
                                                '<label >配置值</label>' +
                                                '</div>' +
                                                '<div class="k-edit-field">' +
                                                '<textarea class="k-input k-textbox"  name="setConfigValue" />' +
                                                '</div>' +

                                                '<div class="k-edit-label">' +
                                                '<label >修改原因</label>' +
                                                '</div>' +
                                                '<div class="k-edit-field">' +
                                                '<textarea class="k-input  k-textbox"  name="setCauseDescript" />' +
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
                                                title: kendo.format("配置 {0} 值", laputaConfig.cname),
                                                actions: ["Maximize", "Close"],
                                                //height: "90%",
                                                //width: "40%",
                                                modal: true,
                                                resizable: false,
                                                draggable: true,
                                                close: cancelConfig,
                                                visible: false,
                                                position: {top: "10%", left: "30%"}
                                            }).data("kendoWindow");
                                        configWindow.open();
                                        $("#configDiv .k-grid-cancel").on("click", function () {
                                            configWindow.close();
                                            configWindow.destroy();
                                        });
                                        $("#configDiv .k-grid-update").on("click", function () {
                                            var configValue = $("#configDiv [name='setConfigValue']").last().val();
                                            var causeDescript = $("#configDiv [name='setCauseDescript']").last().val();
                                            if (configValue == null || configValue.length <= 0) {
                                                alert("请输入配置值");
                                                return;
                                            }
                                            if (causeDescript == null || causeDescript.length <= 0) {
                                                alert("请重输入配置描述");
                                                return;
                                            }


                                            var updateData = {
                                                id: laputaConfig.id,
                                                configValue: configValue,
                                                causeDescript: causeDescript
                                            };

                                            $.ajax({
                                                    url: "${laputaConfigUpdateUrl}",
                                                    type: 'POST',
                                                    contentType: "application/json; charset=utf-8",
                                                    dataType: "json",
                                                    data: JSON.stringify(updateData)
                                                }
                                            ).success(function () {
                                                try {
                                                    alert("配置成果");
                                                    laputaConfigFilterDataSource.read();
                                                    configWindow.close();
                                                    configWindow.destroy();
                                                } catch (e) {
                                                }
                                            });

                                        })
                                    }
                                },

                                {
                                    text: "置空",
                                    imageClass: "k-edit",
                                    //className: "k-grid-edit",
                                    iconClass: "k-icon",
                                    click: function (e) {
                                        var tr = $(e.target).closest("tr");
                                        var data = this.dataItem(tr);
                                        window.laputaConfig = data;

                                        function cancelConfig() {
                                            this.destroy();
                                        }

                                        window.configWindow =
                                            $('<div id="configDiv" class="k-popup-edit-form">' +
                                                '<div class="k-edit-form-container">' +

                                                '<div class="k-edit-label">' +
                                                '<label >修改原因</label>' +
                                                '</div>' +
                                                '<div class="k-edit-field">' +
                                                '<textarea class="k-input  k-textbox"  name="setCauseDescript" />' +
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
                                                title: kendo.format("配置 {0} 值", laputaConfig.cname),
                                                actions: ["Maximize", "Close"],
                                                //height: "90%",
                                                //width: "40%",
                                                modal: true,
                                                resizable: false,
                                                draggable: true,
                                                close: cancelConfig,
                                                visible: false,
                                                position: {top: "10%", left: "30%"}
                                            }).data("kendoWindow");
                                        configWindow.open();
                                        $("#configDiv .k-grid-cancel").on("click", function () {
                                            configWindow.close();
                                            configWindow.destroy();
                                        });
                                        $("#configDiv .k-grid-update").on("click", function () {
                                            var causeDescript = $("#configDiv [name='setCauseDescript']").last().val();

                                            if (causeDescript == null || causeDescript.length <= 0) {
                                                alert("请重输入配置描述");
                                                return;
                                            }


                                            var updateData = {
                                                id: laputaConfig.id,
                                                causeDescript: causeDescript
                                            };

                                            $.ajax({
                                                    url: "${laputaConfigUpdateUrl}",
                                                    type: 'POST',
                                                    contentType: "application/json; charset=utf-8",
                                                    dataType: "json",
                                                    data: JSON.stringify(updateData)
                                                }
                                            ).success(function () {
                                                try {
                                                    alert("配置成功");
                                                    laputaConfigFilterDataSource.read();
                                                    configWindow.close();
                                                    configWindow.destroy();
                                                } catch (e) {
                                                }
                                            });

                                        })
                                    }
                                },

                                {
                                    text: "同步",
                                    imageClass: "k-edit",
                                    iconClass: "k-icon",
                                    click: function (e) {
                                        var tr = $(e.target).closest("tr");
                                        var data = this.dataItem(tr);

                                        var synData = {
                                            code: data.code,
                                            configValue: data.configValue
                                        };

                                        $.ajax({
                                                url: "${laputaConfigSynUrl}",
                                                type: 'POST',
                                                contentType: "application/json; charset=utf-8",
                                                dataType: "json",
                                                data: JSON.stringify(synData)
                                            }
                                        ).success(function () {
                                            try {
                                                alert("同步成功");
                                                laputaConfigFilterDataSource.read();
                                            } catch (e) {
                                            }
                                        });
                                    }
                                }], width: 220
                        }
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


            function historyDetailInit(e) {

                var detailRow = e.detailRow;

                detailRow.find(".tabstrip").kendoTabStrip({
                    animation: {
                        open: {effects: "fadeIn"}
                    }
                });

                var laputaConfig = e.data;
                var historyDataSource = new kendo.data.DataSource({
                    page: 1,
                    pageSize: 20,
                    serverPaging: false,
                    serverSorting: true,
                    serverFiltering: true,
                    error: kendo.laputa.util.dataSourceErrorHandle,
                    transport: {
                        read: kendo.laputa.util.transportUrlBuild("${laputaConfigReadHistoryDataSourceUrl}" + "/" + laputaConfig.id),
                        parameterMap: kendo.laputa.util.parameterMap
                    },
                    schema: {
                        data: kendo.laputa.util.schemaData,
                        total: kendo.laputa.util.schemaTotal,
                        model: {
                            id: "id",
                            fields: {
                                id: {editable: false, nullable: false, defaultValue: null},
                                configValue: {editable: true},
                                descript: {editable: true},
                                parentLaputaConfigId: {defaultValue: null,},
                                createdBy: {editable: false, nullable: false},
                                createdDate: {editable: false, nullable: false, type: "date"},
                                lastModifiedBy: {editable: false},
                                lastModifiedDate: {editable: false, type: "date"}
                            }
                        }
                    }
                });

                //$("<div/>").appendTo(e.detailCell).kendoGrid({
                e.detailRow.find(".grid").kendoGrid({
                    dataSource: historyDataSource,
                    columns: [
                        //{field: "id", title: "主键", width: 180},
                        {field: "configValue", title: "配置值", width: 100},
                        {field: "descript", title: "修改原因", width: 100},
                        // {
                        //     field: "parentLaputaConfigId",
                        //     title: "配置",
                        //     width: 180
                        // },
                        // {field: "createdBy", title: "创建用户", width: 180},
                        // {
                        //     field: "createdDate",
                        //     title: "创建时间",
                        //     width: 180,
                        //     format: "{0:yyyy-MM-dd HH:mm:ss tt}",
                        //     editor: kendo.laputa.util.dateTimePickerEditorBuild(),
                        //     filterable: kendo.laputa.util.dateTimePickerFilterableBuild()
                        // },
                        {field: "lastModifiedBy", title: "用户", width: 80},
                        {
                            field: "lastModifiedDate",
                            title: "时间",
                            width: 80,
                            format: "{0:yyyy-MM-dd HH:mm:ss tt}",
                            editor: kendo.laputa.util.dateTimePickerEditorBuild(),
                            filterable: kendo.laputa.util.dateTimePickerFilterableBuild()
                        },
                        //{command: ["edit", "destroy"], width: 220}
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
        }

        kendo.laputa.util.dataSourceFetchQuen([laputaAplicationDataSource, laputaConfigHistoryDataSource], initFun);

    });

</script>


</body>
</html>