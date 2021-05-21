<%--
  Created by IntelliJ IDEA.
  User: LEO
  Date: 2021-04-03
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="generator" content="Jekyll v4.1.1">
    <title>WXY政务登录</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/sign-in/">
    <!-- jquery core -->
    <script src="${APP_PATH}/plugins/jquery-1.12.4.js"></script>

    <!-- Bootstrap core CSS -->
    <script type="text/javascript" src="${APP_PATH}/script/jsencrypt.min.js"></script>
    <%--	<script type="text/javascript" src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>--%>
    <link href="${APP_PATH }/static/bootstrap-4.5.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="${APP_PATH }/static/bootstrap-4.5.3-dist/js/bootstrap.min.js"></script>

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/signin.css" rel="stylesheet">
    <script type="application/javascript">
        function encrypt(password) {
            var jse = new JSEncrypt();
            var pubStr = 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCLNbmKl9/gLn7Bef/xtUkshC1WyrLZLRpXCcFYR1gQi0isWsZBTicC4efBOkkNG3r+1ue0gvtuU/tjREFGf4Y7HaKHGb5tNCOlMNeNjM5YLRwLFqrUSsQyD4rj4eua1ltearr24R0HilnTvnQm6Z/UY0s21vdOUFQBPY0GNAa+0wIDAQAB';

            jse.setPublicKey(pubStr);
            //RSA公钥加密
            let encryptedStr = jse.encrypt(password);
            return encryptedStr;
        }

        function submit() {
            var url = "${APP_PATH}/submit.do";
            var formData = new FormData($("#form")[0]);
            console.log(formData.get("account"));
            console.log(formData.get("password"));

            //加密密码
            let pwd = formData.get("password");
            let encryptedPwd = encrypt(pwd);
            //修改明文
            formData.set("password",encryptedPwd);

            $.ajax({
                url: url,
                data: formData,
                async: true,
                type: "post",
                processData: false,
                contentType: false,
                success: function (data) {
                    if (data == "1") {
                        location.href = "${APP_PATH}/index.do";
                    } else {
                        alert("登录失败，密码错误")
                    }
                }, error: function (e) {
                }
            })
        }

        function checkForm(form) {
            if (form.account.value == '') {
                window.alert("账户名不能为空");
                return false;
            } else if (form.password.value == '') {
                window.alert("密码不能为空");
                return false;
            } else {
                submit();
            }
        }
    </script>

</head>
<body class="text-center">
<form class="form-signin" id="form" method="post">
    <img class="mb-4" src="${pageContext.request.contextPath}/assets/brand/vote.svg" alt="" width="72" height="72">

    <h1 class="h3 mb-3 font-weight-normal">Please login</h1>
    <label for="inputAccount" class="sr-only">Account</label>
    <input type="text" id="inputAccount" class="form-control" placeholder="Account" name="account" required autofocus>
    <label for="inputPassword" class="sr-only">Password</label>

    <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="password" required>
    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" value="remember-me"> Remember me
        </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" onclick="checkForm(this.form)" type="button">Sign in</button>
    <p class="mt-5 mb-3 text-muted">&copy; 2017-2020</p>
</form>
</body>
</html>
