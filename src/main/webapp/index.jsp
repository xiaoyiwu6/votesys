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
    <script type="application/javascript">
        function vote(playerId) {
            $.ajax({
                url:"${APP_PATH}/votePlayer",
                data:{sessionId:${curSession.sessionId},playerId:playerId,userId:${curUser.userId}},
                type:"post",
                dataType:"json",
                contentType:"application/json", //这个必须，不然后台接受时会出现乱码现象
                success:function(data){
                    console.log("修改成功");

                },
                error:function(){
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
        <p><a class="btn btn-primary btn-lg" href="#" role="button">查看往期投票活动</a></p>
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
                    <c:forEach items="${curPlayers}" var="pbc">
                        <div class="col-sm-6 col-md-4">
                            <div class="thumbnail">
                                <img src="${pbc.player.picAddress}" alt="...">
                                <div class="caption">
                                    <h3>${pbc.player.playerName}</h3>
                                    <p>${pbc.player.slogan}</p>
                                    <p>${pbc.player.info}</p>
                                    <p><button id="voetButton" class="btn btn-primary" onclick="vote(${pbc.player.playerId})" role="button">投票</button>
                                        <button class="btn btn-default" disabled>当前票数：${pbc.count}</button>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <script type="application/javascript">
                    $("#voetButton").click(function () {
                        console.log("有按钮被触发");
                        $(this).text("已投");
                        $(this).attr("disabled","disabled");
                        $(this).attr("class","btn btn-default");
                    })
                </script>
            </div>
        </div>

    </main>
</c:if>

</body>
</html>