<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>


<!-- Mirrored from www.zi-han.net/theme/hplus/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:18:23 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <title>Laputa - 登录2</title>


    <link href="<c:url value='/favicon.ico'/>" rel="shortcut icon">
    <link href="<c:url value='/static/hplus/css/bootstrap.min.css'/>" rel="stylesheet">

    <link href="<c:url value='/static/hplus/css/font-awesome.min.css'/>" rel="stylesheet">

    <link href="<c:url value='/static/hplus/css/animate.min.css'/>" rel="stylesheet">

    <link href="<c:url value='/static/hplus/css/style.min.css'/>" rel="stylesheet">

    <script>if (window.top !== window.self) {
        window.top.location = window.location;
    }
    </script>



</head>

<body class="gray-bg">

<div class="middle-box text-center loginscreen  animated fadeInDown">
    <div>
        <div>

            <h1 class="logo-name">H+</h1>

        </div>
        <h3>欢迎使用 Laputa</h3>

        <div id="login-form">
            <div class="form-group">
                <input type="text" id="username" class="form-control" placeholder="用户名" value="admin"  required="require">
            </div>
            <div class="form-group">
                <input type="password" id="password" class="form-control" placeholder="密码" value="admin"  required="require">
            </div>
            <div class="form-group">
                <div class="row">
                    <div class="form-group col-xs-6 ">
                        <input id="captcha" class="form-control" placeholder="验证码" required="require">
                    </div>
                    <div class="form-group col-xs-6" title="点击图片刷新验证码">
                        <img src="<c:url value='/captcha'/>" border="0" onclick="this.src='<c:url value='/captcha'/>?rnd=' + Math.random();">
                    </div>
                </div>
            </div>
            <div class="form-group ">
                <button id="loginButton" class="btn btn-primary block full-width">登 录</button>
            </div>
        </div>
    </div>
</div>


<script src="<c:url value='/static/hplus/js/jquery.min.js'/>"></script>
<script src="<c:url value='/static/hplus/js/bootstrap.min.js'/>"></script>

<script>

    $(document).ready(function () {

        $.get("<c:url value='/logout'/>");

        $("#loginButton").on("click", (tryLogin));

        function tryLogin() {
            var loginUrl = "<c:url value='/login'/>";

            var data = {
                username: $("#username").val(),
                password: $("#password").val(),
                captcha: $("#captcha").val()
            };

            if (data.username == null || data.username.length == 0) {
                alert("用户名未填写");
                return;
            }
            if (data.password == null || data.password.length == 0) {
                alert("密码未填写");
                return;
            }
            if (data.captcha == null || data.captcha.length == 0) {
                alert("验证码未填写");
                return;
            }
            $.post(loginUrl, data)
                    .success(function () {
                        location.href = "<c:url value='/index'/>";
                    }).fail(function (d) {
                alert("登录失败" + d.statusText);
            });
        }
    });


</script>

</body>

</html>
