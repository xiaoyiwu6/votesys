<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/publicNav.css">
</head>
<body>
<nav>
       <ul>
            <li><a href="${pageContext.request.contextPath}/userManagement.do">用户管理</a></li>
            <li><a href="${pageContext.request.contextPath}/PlayerManagement.do">选手管理</a></li>
            <li><a href="${pageContext.request.contextPath}/playerContent.do">比赛管理</a></li>
        </ul>
 </nav>
<table>
	<tr>
		<td>比赛轮数</td>
		<td>当前场次</td>
		<td>当前场次获胜者</td>
		<td>A选手票数</td>
		<td>B选手票数</td>
		<td>选手A</td>
		<td>选手B</td>
		<td>比赛状态</td>
	</tr>
	<c:forEach items="${ses}" var="list">
	<tr>
		<td>${list.wheel}</td>
		<td>${list.nowSession}</td>
		<td>${list.winner}</td>
		<td>${list.votesContrastA}</td>
		<td>${list.votesContrastB}</td>
		<td>${list.playerA}</td>
		<td>${list.playerB}</td>
		<c:if test="${list.gameNow==2}">
			<td>结束</td>
		</c:if>
	</tr>
	</c:forEach>
</table>
</body>
</html>