<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<tr>
				<td>参赛选手A的id</td>
				<td><input type="text" id="playerA" name="playerA"/></td>
			</tr>
			<tr>
				<td>参赛选手B的id</td>
				<td><input type="text" id="playerB" name="playerB"/></td>
			</tr>
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