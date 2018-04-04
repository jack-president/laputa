<%--
  Created by IntelliJ IDEA.
  User: JiangDongPing
  Date: 2016/11/25
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div id="grid"></div>
<script>jQuery(function () {
    jQuery("#grid").kendoGrid({
        "dataSource": {
            "schema": {"total": "total", "data": "data"}, "serverFiltering": true, "transport": {
                "parameterMap": function parameterMap(options, type) {
                    return JSON.stringify(options);
                },
                "read": {
                    "contentType": "application/json",
                    "type": "POST",
                    "url": "/web/grid/filter-menu-customization/read"
                }
            }
        }, "filterable": true, "columns": [{"template": "#=firstName# #=lastName#", "title": "Name"}, {
            "field": "city", "title": "City", "filterable": {
                "ui": function cityFilter(element) {
                    element.kendoDropDownList({
                        optionLabel: "--Select Value --",
                        dataSource: {
                            transport: {
                                read: "/web/grid/filter-menu-customization/cities"
                            }
                        }
                    });
                }
            }
        }, {
            "field": "title", "title": "Title", "filterable": {
                "ui": function cityTitle(element) {
                    element.kendoAutoComplete({
                        dataSource: {
                            transport: {
                                read: "/web/grid/filter-menu-customization/titles"
                            }
                        }
                    });
                }
            }
        }]
    });
})</script>


</body>
</html>
