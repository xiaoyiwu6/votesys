<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="generator" content="Jekyll v4.1.1">
    <title>WXY投票管理</title>
    <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/navbar-fixed/">

    <!-- Bootstrap core CSS -->
    <script type="text/javascript" src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
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
    <style type="text/css">
        img[src=""],img:not([src]){
            opacity:0;

        }
    </style>
    <!-- Custom styles for this template -->
    <link href="${APP_PATH}/css/navbar.css" rel="stylesheet">
    <script type="text/javascript">
        function add() {
            var url = "/addPlayer/add.do";
            var param = new FormData($("#form")[0]);
            var bp = $("#bigImg").val();
            var sp = $("#smallImg").val();
            if (bp != "" && sp != "") {
                $.ajax({
                    url: url,
                    type: "post",
                    data: param,
                    processData: false,
                    contentType: false,
                    success: function (data) {
                        if (data == "1") {
                            location.href = "${pageContext.request.contextPath}/PlayerManagement.do"
                        } else {
                            alert("新增失败！")
                        }
                    }, error: function (data) {

                    }
                });
            } else {
                alert("未选择图片!")
            }

        }
    </script>
    <script>
        $(function () {
            //显示更换后的图片
            $("#bigImg").change(function () {
                $("#big").attr("src", URL.createObjectURL($(this)[0].files[0]));
            });
            $("#smallImg").change(function () {
                $("#small").attr("src", URL.createObjectURL($(this)[0].files[0]));
            });
        });
    </script>
</head>
<body>
<c:if test="${sessionScope.curUser.level==1}">
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <a class="navbar-brand" href="#">WXY政务投票</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item ">
                    <a class="nav-link" href="${APP_PATH}/index.do">首页</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${APP_PATH}/userManagement.do">选民管理</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${APP_PATH}/playerContent.do">投票活动管理</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${APP_PATH}/PlayerManagement.do">候选人管理</a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link" href="${APP_PATH}/vote.do">我的选票</a>
                </li>
            </ul>
            <form class="form-inline mt-2 mt-md-0" action="${APP_PATH}/signOut.do">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Sign out</button>
            </form>
        </div>
    </nav>
</c:if>
<c:if test="${empty sessionScope.curUser}">
    <a>sesion中的curUser不存在</a><br>
</c:if>
<br><br><br>
<form id="form" enctype="multipart/form-data" method="post">
    <table>
        <tr>
            <td>候选人姓名：</td>
            <td><input type="text" id="playerName" name="playerName"/></td>
        </tr>
        <tr>
            <td>候选人已参与投票次数：</td>
            <td><input type="text"  name="num" id="num" /></td>
        </tr>
        <tr>
            <td>候选人大图片</td>
            <td><img src="" width="200" id="big"></td>
            <td>
                <input type="file" name="bigImg" id="bigImg" value="0"/>
            </td>
        </tr>

        <tr>
            <td>候选人小图片:</td>
            <td><img src="" width="100" id="small"></td>
            <td><input type="file" name="smallImg" id="smallImg"  value="0"/></td>
        </tr>
        <tr>
            <td>候选人出生日期：</td>
            <td><input type="text" name="dateOfBirth" id="dateOfBirth" /></td>
        </tr>
        <tr>
            <td>候选人性别：</td>
            <td>
                <select name="sex" style="width: 160px" id="sex">
                    <option value="1">女</option>
                    <option value="0">男</option>
                </select>
            </td>
        </tr>
        <tr>
        <tr>
            <td>候选人宣传语：</td>
            <td><input type="text" id="slogan" name="slogan"/></td>
        </tr>
        <tr>
            <td>候选人个人信息：</td>
            <td><input type="text" id="info" name="info" /></td>
        </tr>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center"><input type="button" value="修改" onclick="add()"/></td>
        </tr>
    </table>
</form>

</body>
</html>