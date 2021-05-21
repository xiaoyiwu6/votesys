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
    <!-- Custom styles for this template -->
    <link href="${APP_PATH}/css/album.css" rel="stylesheet">
    <script type="application/javascript">

        function vote(playerId) {
            var formData = new FormData();
            formData.append("sessionId",${curSession.sessionId});
            formData.append("playerId",playerId);
            formData.append("userId",${sessionScope.curUser.userId});


            console.log(${curSession.sessionId});
            console.log(playerId);
            console.log(${sessionScope.curUser.userId});

            $.ajax({
                url: "${APP_PATH}/votePlayer.do",
                data: formData,
                async: true,
                type: "post",
                processData: false,
                contentType: false,
                success: function (data) {
                    if(data=="1"){
                        alert("投票成功");
                        location.reload();
                    }else{
                        alert("不可重复投票！");
                    }

                },
                error: function () {
                    alert("出错了");
                }
            })
        }
    </script>
</head>
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
<c:if test="${empty curSession}">
    <div class="jumbotron bg-white">
        <h1>欢迎来到WXY政务投票系统</h1>
        <p>当前尚且没有正在进行的投票活动</p>
    </div>
</c:if>
<c:if test="${!empty curSession}">
    <main role="main">

        <section class="jumbotron text-center">
            <div class="container">
                <h1>${curSession.sessionName}</h1>
                <p class="lead text-muted">活动开始于${curSession.start}</p>
            </div>
        </section>

        <div class="album py-5 bg-light">
            <div class="container">

                <div class="row">
                    <c:forEach items="${curPlayers}" var="player">
                        <div class="col-md-4">
                            <div class="card mb-4 shadow-sm">
                                <img src="${player.player.picAddress}" class="bd-placeholder-img card-img-top" width="100%" height="225" alt="">
                                <div class="card-body">
                                    <p class="card-body">${player.player.playerName}<br>${player.player.slogan}<br>${player.player.info}</p>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-sm btn-outline-secondary" disabled></button>
                                            <button type="button" class="btn btn-sm btn-outline-secondary" onclick="vote(${player.playerId})">投票</button>
                                        </div>
                                        <small class="text-muted">当前票数：${player.count}</small>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

    </main>
</c:if>

</body>
</html>