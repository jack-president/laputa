<%--
  Created by IntelliJ IDEA.
  User: hz15071510
  Date: 2015/12/17
  Time: 14:09
  重写主页容器插件为Ajax加载
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    /*
     * 配置插件参数
     * */
    (function (window) {

        function processPrefix(prefix) {
            if(prefix.length > 1){
                prefix = prefix.substring(0,prefix.length-1);
            }
            return prefix;
        }

        window['SIDEBAR_CONFIG'] = {
            appendNode: 'sidebarDiv',
            menuUrlPrefex: processPrefix('<c:url value='/' />'),
            requestUrl: {
                sidebarInfo: "<c:url value='/menu'/>"
            },
            userInfo: {
                imageUrl: "<c:url value='/static/hplus/img/admin.jpg'/>",
                //name: "<c:out value="${sessionScope.sysUser.name}"></c:out>",
                name: "Laputa",

            }
        };
    })(window);
</script>

<script type="text/javascript" src="<c:url value='/static/hplus/js/dongping/hplus-sidebar.js'/>"></script>
