<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/static/kendo" var="kendoPath"/>
<c:url value="/static/kendo/third/depend/jquery" var="jqueryPath"/>

<script src="<c:url value='/static/kendo/third/depend/requirejs/require.js' />"></script>
<script>
    function laputaKendoRequire(requireArray, f) {
        require.config({
            paths: {
                "jquery": "${jqueryPath}/jquery-1.9.1",
                "kendo": "${kendoPath}"
            }
        });

        require(["jquery"], function () {
            require(["kendo/js/kendo.core", "kendo/third/LaputaUtil/kendo.laputa.util"], function () {
                require(requireArray, function () {
                    window.kendo.culture("zh-CN");
                    require(["kendo/js/messages/kendo.messages.zh-CN", "kendo/js/cultures/kendo.culture.zh-CN"], function () {
                        window.kendo.culture("zh-CN");
                        f();
                    });
                });
            });
        });
    }
</script>

