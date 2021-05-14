<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/publicNav.css">
<script src="${pageContext.request.contextPath}/plugins/jquery-1.12.4.js"></script>
<script type="text/javascript">
$(function(){
	$("#state").val(${player.state}?1:0);
	$("#sex").val(${player.sex}?1:0);
	//显示更换后的图片
	$("#bigImg").change(function() {  
	     $("#big").attr("src",URL.createObjectURL($(this)[0].files[0]));
	});  
	$("#smallImg").change(function() {  
	     $("#small").attr("src",URL.createObjectURL($(this)[0].files[0]));
	});  
});    	
</script>
<script type="text/javascript">
function edit() {
	var url = "${pageContext.request.contextPath}/editPlayer/update";
	var param = new FormData($("#form")[0]);
	console.log(param);
	$.ajax({  
        url:url,  
        type:"post",  
        data:param,  
        processData:false,  
        contentType:false,  
        success:function(data) {
        	if(data=="1"){
				location.href="${pageContext.request.contextPath}/PlayerManagement.do"
			}else{
				alert("修改失败!")
			}
        },error:function(data) {  
              
        }  
    });  
}
</script>  
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
	<form id="form">
		<table>
			<tr>
				<td>选手姓名：</td>
				<td><input type="text" id="playerName" name="playerName" value="${player.playername}"/></td>
			</tr>
			<tr>
				<td>选手状态：</td>
				<td>
					<select name="state" style="width: 160px" id="state">
						<option value="0">未淘汰</option>
						<option value="1">淘汰</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>选手参赛次数：</td>
				<td><input type="text"  name="num" id="num" value="${player.num}"/></td>
			</tr>
			<tr>
				<td>选手大图片</td>
				<td><img src="${player.picAddress}" width="200" id="big"></td>
				<td>
					<input type="file" name="bigImg" id="bigImg" value="0"/>
				</td>
			</tr>

			<tr>
				<td>选手小图片:</td>
				<td><img src="${player.smallImg}" width="100" id="small"></td>
				<td><input type="file" name="smallImg" id="smallImg"  value="0"/></td>
			</tr>
			<tr>
				<td>选手出生日期：</td>
				<td><input type="text" name="dateOfBirth" id="dateOfBirth" value="${player.dateOfBirth}"/></td>
			</tr>
			<tr>
				<td>选手性别：</td>
				<td>
					<select name="sex" style="width: 160px" id="sex">
						<option value="1">女</option>
						<option value="0">男</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center"><input type="button"
					 value="修改" onclick="edit()"/></td>
				<td><input type="hidden" name="playerId" id="playerId" value="${player.playerId}" /></td>
			</tr>
		</table>
	</form>
</body>
</html>