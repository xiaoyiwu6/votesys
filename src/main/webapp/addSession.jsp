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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/publicNav.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jquery-1.12.4.js"></script>
<!--<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-3.3.1.min.js"></script>-->
</head>
<body>
<nav>
        <ul>
            <li>
                <a href="back">首页</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/userManagement.do">用户管理</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/playerContent.do">PK管理</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/PlayerManagement.do">选手管理</a>
            </li>
            <li>
                <a href="#">更多</a>
            </li>
        </ul>
    </nav>
添加比赛列表
<form id="form">
		<table>
            <c:forEach items="${players}" var="list">
                <tr>
                    <td>${list.playerId}</td>
                    <td>${list.playername}</td>
                    <c:if test="${list.state==0}">
                        <td>未淘汰</td>
                    </c:if>
                    <c:if test="${list.state==1}">
                        <td>淘汰</td>
                    </c:if>
                    <td>${list.num}</td>
                    <td>
                        <img src="${list.picAddress}" width="200">
                    </td>
                    <td>
                        <img src="${list.smallImg}"  width="100">
                    </td>
                    <td>${list.dateOfBirth}</td>
                    <c:if test="${list.sex==0}">
                        <td>男</td>
                    </c:if>
                    <c:if test="${list.sex==1}">
                        <td>女</td>
                    </c:if>
                    <td><a href="${APP_PATH}/editPlayer/${list.playerId}.do">编辑</a></td>
                    <td><a onclick="delPlayer(${list.playerId})" href="#">删除</a></td>
                </tr>
            </c:forEach>
			<tr>
				<td colspan="2" style="text-align: center"><input type="button"
					 value="添加场次" onclick="add()"/></td>
			</tr>
		</table>
	</form>
	<script>
		function add(){
			var playerA=$("#playerA").val();
			var playerB=$("#playerB").val();
			if(playerA==""){
				alert("选手A的编号不能为空");
				return;
			}
			if(playerB==""){
				alert("选手B的编号不能为空");
				return;
			}
			//开始发布异步AJAX请求
			var url="${pageContext.request.contextPath}/addSessions";
			var param=$("#form").serialize();
			$.post(url,param,function(data){
				if(data=="1"){
					alert("添加成功")
				}else if(data=="0"){
					alert("添加失败，可能两位选手比赛轮数不匹配或其中一位选手已经在本轮有了比赛");
				}
			})
		}
	</script>
</body>
</html>