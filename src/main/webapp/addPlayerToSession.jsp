<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

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
    <!-- Custom styles for this template -->
    <link href="${APP_PATH}/css/navbar.css" rel="stylesheet">

    <!-- 按钮文字变化 -->
    <script type="application/javascript">
        function changeButton(id) {
            //console.log(id);
            $.ajax({
                url:"${APP_PATH}/addSession/addPlayer/${sessionId}/"+id+".do",
                type:"post",
                dataType:"json",
                contentType:"application/json", //这个必须，不然后台接受时会出现乱码现象
                success:function(data){
                    console.log("修改成功");
                    location.reload();
                },
                error:function(){
                    alert("出错了");
                    location.reload();
                }
            })
        }
    </script>
</head>
<body>
<c:if test="${sessionScope.curUser.level==1}">
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <a class="navbar-brand" href="#">WXY政务投票</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
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
<main role="main" class="container">
    <!-- table -->
    <table class="table table-hover table-bordered">
        <thead>
        <tr>
            <td>候选人id</td>
            <td>候选人姓名</td>
            <td>候选人状态</td>
            <td>候选人参赛次数</td>
            <td>候选人大图片</td>
            <td>候选人小图片</td>
            <td>候选人出生日期</td>
            <td>候选人性别</td>
            <td>操作</td>
        </tr>

        </thead>
        <tbody>
        <c:forEach items="${absentPlayers}" var="player">
            <tr>
                <td>${player.playerId}</td>
                <td>${player.playerName}</td>
                <c:if test="${player.state==0}">
                    <td>未参与</td>
                </c:if>
                <c:if test="${player.state==1}">
                    <td>已参与</td>
                </c:if>
                <td>${player.num}</td>
                <td>
                    <img src="${player.picAddress}" width="200">
                </td>
                <td>
                    <img src="${player.smallImg}"  width="100">
                </td>
                <td>${player.dateOfBirth}</td>
                <c:if test="${player.sex==0}">
                    <td>男</td>
                </c:if>
                <c:if test="${player.sex==1}">
                    <td>女</td>
                </c:if>
                <td><button id="addButton" class="btn btn-primary" type="button" onclick="changeButton(${player.playerId})">加入</button></td>

            </tr>
        </c:forEach>
        <c:forEach items="${presentPlayers}" var="player">
            <tr>
                <td>${player.playerId}</td>
                <td>${player.playerName}</td>
                <c:if test="${player.state==0}">
                    <td>未参与</td>
                </c:if>
                <c:if test="${player.state==1}">
                    <td>已参与</td>
                </c:if>
                <td>${player.num}</td>
                <td>
                    <img src="${player.picAddress}" width="200">
                </td>
                <td>
                    <img src="${player.smallImg}"  width="100">
                </td>
                <td>${player.dateOfBirth}</td>
                <c:if test="${player.sex==0}">
                    <td>男</td>
                </c:if>
                <c:if test="${player.sex==1}">
                    <td>女</td>
                </c:if>
                <td><button class="btn btn-default" type="button" disabled="disabled">已加</button></td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form class="form-horizontal" name="myForm" action="${APP_PATH}/addSession/updateName.do">
        <div class="form-group">
            <label for="inputName" class="col-sm-2 control-label">活动名字</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputName" name="sessionName" value="session">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary" >修改</button>
            </div>
        </div>
        <input type="text" value="${sessionId}" hidden="hidden"/>
    </form>
</main>

</body>
</html>