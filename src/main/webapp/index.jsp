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
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v4.1.1">
    <title>首页</title>

    <link rel="stylesheet" href="${APP_PATH}/css/tiny.css">
    <script src="${APP_PATH}/plugins/jquery-1.12.4.js"></script>

    <script>
        function changetovote(userId){
            var url="${APP_PATH}/changeToVote.do";
            var param={userId:userId};
            $.post(url, param, function(data) {
                if(data=="1"){
                    location.href="${APP_PATH}/vote.do/"+userId;
                }
                if(data=="0"){
                    alert("投票未开启!");
                }
            });
        }
    </script>
</head>
<br>
欢迎来到在线投票系统<br>
<%--<br> user requestScope:${requestScope.curUser}--%>
<%--<br>--%>
<%--<br> user sessionScope:${sessionScope.curUser}--%>
<c:if test="${sessionScope.curUser.level==1}">
    <nav>
        <ul>
            <li><a href="${APP_PATH}/userManagement.do">用户管理</a></li>
            <li><a href="${APP_PATH}/PlayerManagement.do">选手管理</a></li>
            <li><a href="${APP_PATH}/playerContent.do">比赛管理</a></li>
        </ul>
    </nav>
</c:if>
<c:if test="${empty sessionScope.curUser}">
    <a>sesion中的curUser不存在</a><br>
</c:if>
<div class="all_bg">
    <div id="allcanvas">
        <canvas id="canvas1" width="800" height="600"></canvas>
        <canvas id="canvas2" width="800" height="600"></canvas>
    </div>
</div>

<div class="enter_vote">
        	<span>
        		<a href="#" onclick="changetovote(${curUser.userId})">
        			<c:if test="${sessionScope.curUser.level==1}">投票展示</c:if>
   					<c:if test="${sessionScope.curUser.level==0}">进入投票</c:if>
        		</a>
        	</span>
</div>
<script src="${APP_PATH}/js/main.js"></script>
<script src="${APP_PATH}/js/background.js"></script>
<script src="${APP_PATH}/js/ane.js"></script>
<script src="${APP_PATH}/js/fruit.js"></script>
<script src="${APP_PATH}/js/bigfish.js"></script>
<script src="${APP_PATH}/js/collision.js"></script>
<script src="${APP_PATH}/js/smallfish.js"></script>
</body>
</html>