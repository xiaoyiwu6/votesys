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
<link rel="stylesheet" href="${APP_PATH}/css/publicNav.css">
<style type="text/css"> 
       .table {
            border: 1px solid #ddd;
            border-collapse: collapse;
        }

        .table thead tr th,
        .table tbody tr td {
            padding: 8px 12px;
            text-align: center;
            color: #333;
            border: 1px solid #ddd;
            border-collapse: collapse;
            background-color: #fff;
        }
    </style>
<script type="text/javascript" src="${APP_PATH}/plugins/jquery-1.12.4.js"></script>
<script>
function del(id){
	var result=confirm("确认删除?");
	if(result){
		//开始发送异步请求
		var url="${APP_PATH}/delPlayer.do";
		var param ={playerId:id};
		$.post(url, param, function(data) {
			if(data=="1"){
				alert("选手删除成功!")
				location.href="PlayerManagement";
			}else{
				alert("选手删除失败!")
			}
		});
	}
}
</script>
</head>
<body>
<nav>
        <ul>
            <li>
                <a href="${APP_PATH}/index.do">首页</a>
            </li>
            <li>
                <a href="${APP_PATH}/userManagement.do">用户管理</a>
            </li>
            <li>
                <a href="${APP_PATH}/playerContent.do">PK管理</a>
            </li>
            <li>
                <a href="${APP_PATH}/PlayerManagement.do">选手管理</a>
            </li>
            <li>
                <a href="#">更多</a>
            </li>
        </ul>
    </nav>
	<a href="addPlayer">添加选手</a>
	<table class="table" cellspacing="0" cellpadding="0">
    	<thead>
        	<th>选手id</th>
        	<th>选手姓名</th>
        	<th>选手状态</th>
        	<th>选手参赛次数</th>
        	<th>选手大图片</th>
        	<th>选手小图片</th>
        	<th>选手出生日期</th>
        	<th>选手性别</th>
        	<th colspan="2">操作</th>
    	</thead>
    	<tbody>
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
    </tbody>
</table>
</body>
</html>