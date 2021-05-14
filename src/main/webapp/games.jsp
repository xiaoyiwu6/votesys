<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pkManage.css">
</head>
<body onLoad="goPage(1,4);">
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
    <a href="${pageContext.request.contextPath}/addSession.do">手动添加比赛</a>
    <a href="${pageContext.request.contextPath}/generateTheGameTable.do">自动生成比赛列表</a>
    <a href="${pageContext.request.contextPath}/queryGameTable.do">查看本轮比赛剩余对战</a>
    <a href="${pageContext.request.contextPath}/queryGameTablePass.do">查看已经进行过的对战</a>
    <div class="banner">
        <table class="tablebox" >
            <tbody id="idData">
            <tr>
            	<td>场次</td>
            	<td>选手A_id</td>
            	<td></td>
            	<td>选手B_id</td>
            	<td colspan="3">操作</td>
            </tr>
            <c:forEach items="${sessions}" var="item">
                <tr>
                    <td>${item.nowSession}</td>
                    <td>${item.playerA}</td>
                    <td class="vs">vs</td>
                    <td>${item.playerB}</td>
                    <td><input class="Btn" type="button" value="开启" onclick="begin(${item.nowSession})"></td>
                    <td><input class="Btn" type="button" value="关闭" onclick="overs(${item.nowSession})"></td>
                    <td><input class="Btn" type="button" value="暂停" onclick="stops(${item.nowSession})"></td>
                </tr>
                  </c:forEach>
            </tbody>
        </table>
        <table id="foot">
            <tr>
                <td>
                    <div id="barcon" name="barcon"></div>
                    跳转到<input id="pageVal" type="text">页<button id="pageBtn">确定</button>
                </td>
            </tr>
        </table>
    </div>
</body><!-- <script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-3.3.1.min.js"></script> -->
<script src="${pageContext.request.contextPath}/plugins/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pkManage.js"></script>
<script>
	function begin(nowSession){
		//开始比赛
	    var url="${pageContext.request.contextPath}/beginGame.do";
	    var param={nowSession:nowSession};
	    $.post(url,param,function(data){
	        if(data=="1"){
	            alert("开始比赛")
	        }else{
	            alert("开始比赛失败,已经有比赛在进行中，请不要再次开启比赛；或者本场比赛已经结束。")
	        }
	    })
	}
    function overs(nowSession){
    	var url="${pageContext.request.contextPath}/oversGame.do";
    	var param={nowSession:nowSession}
    	$.post(url,param,function(data){
    		if(data=="1"){
    			alert("比赛结束");
    		}else{
    			alert("出现错误不能结束比赛");
    		}
    	})
	}
    function stops(nowSession){
    	var url="${pageContext.request.contextPath}/stopsGame.do";
    	var param={nowSession:nowSession}
    	$.post(url,param,function(data){
    		if(data=="1"){
    			alert("已暂停比赛");
    		}else{
    			alert("当前状态不能暂停");
    		}
    	})
    }
</script>
</html>