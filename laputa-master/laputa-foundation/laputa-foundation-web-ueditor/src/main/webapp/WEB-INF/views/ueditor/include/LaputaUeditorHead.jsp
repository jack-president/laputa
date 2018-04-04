<%--
  Created by IntelliJ IDEA.
  User: jiangdongping
  Date: 2017/12/26 0026
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" charset="utf-8" src="<c:url value="/static/ueditor/ueditor.config.js" />"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/static/ueditor/ueditor.all.js" />"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="<c:url value="/static/ueditor/lang/zh-cn/zh-cn.js"/>"></script>

<script type="text/javascript" charset="utf-8">
    var context = window.location.protocol + "//" + window.location.host;
    window.UEDITOR_CONFIG.serverUrl = context + '<c:url value="/ueditor/service/config" />';
    window.UEDITOR_CONFIG.UEDITOR_HOME_URL = context + '<c:url value="/static/ueditor/" />';
</script>


