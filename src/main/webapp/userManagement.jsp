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

    <script>
        function del(id){
            var result=confirm("您确定要删除该用户？");//confirm设置弹出框
            if(result){
                //执行删除
                var url="${APP_PATH}/user/del.do";
                var param={id:id};
                $.post(url,param,function(data){
                    if(data=="1"){
                        alert("删除成功");
                        location.reload();
                    }else{
                        alert("删除失败")
                    }
                })
            }
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
	<table class="table table-hover table-bordered">
		<thead>
		<tr>
			<td>用户id</td>
			<td>账户名</td>
			<td>密码</td>
			<td>类型</td>
			<td>操作</td>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${users}" var="item">
			<tr>
				<td>${item.userId}</td>
				<td>${item.account}</td>
				<td>${item.password}</td>
				<td>选民</td>
				<td><a href="#" onclick="del(${item.userId})">删除</a></td>
			</tr>
		</c:forEach>
		</tbody>

	</table>
</main>
</div>
</body>
</html>
