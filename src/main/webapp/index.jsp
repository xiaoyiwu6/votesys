<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tiny.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jquery-1.12.4.js"></script>
    <script>
        function changetovote(userId){
            var url="${pageContext.request.contextPath}/changeToVote.do";
            var param={userId:userId};
            $.post(url, param, function(data) {
                if(data=="1"){
                    location.href="${pageContext.request.contextPath}/vote.do/"+userId;
                }
                if(data=="0"){
                    alert("投票未开启!");
                }
            });
        }
    </script>
</head>
<body>
欢迎来到在线投票系统
<c:if test="${sessionScope.curUser.type==1}">
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/userManagement.do">用户管理</a></li>
            <li><a href="${pageContext.request.contextPath}/PlayerManagement.do">选手管理</a></li>
            <li><a href="${pageContext.request.contextPath}/playerContent.do">比赛管理</a></li>
        </ul>
    </nav>
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
        			<c:if test="${sessionScope.curUser.type==1}">投票展示</c:if>
   					<c:if test="${sessionScope.curUser.type==0}">进入投票</c:if>
        		</a>
        	</span>
</div>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script src="${pageContext.request.contextPath}/js/background.js"></script>
<script src="${pageContext.request.contextPath}/js/ane.js"></script>
<script src="${pageContext.request.contextPath}/js/fruit.js"></script>
<script src="${pageContext.request.contextPath}/js/bigfish.js"></script>
<script src="${pageContext.request.contextPath}/js/collision.js"></script>
<script src="${pageContext.request.contextPath}/js/smallfish.js"></script>
</body>
</html>