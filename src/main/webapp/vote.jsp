<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/vote.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jquery-1.12.4.js"></script>
<script src="${pageContext.request.contextPath}/js/regl.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vote.js"></script>
<script src="${pageContext.request.contextPath}/js/screen.js"></script>
<script src="${pageContext.request.contextPath}/js"></script>

<script>
$(function(){
	$("#Btn_01").click(function () {
		VoteRTS(0,${userId});
		$(this).attr({"disabled":"disabled"});
		$("#Btn_02").attr({"disabled":"disabled"});
	});

	$("#Btn_02").click(function () {
		VoteRTS(1,${userId});
		$(this).attr({"disabled":"disabled"});
		$("#Btn_01").attr({"disabled":"disabled"});
	});
});
</script>
</head>
<body>
<c:if test="${type==0}">
	<div class="phone">
        <div class="Voting">

            <div class="songer_01">
                <span><img src="${players.get(0).picAddress}" height="300" width="256"/></span>
                <button class="Btn_01" id="Btn_01">支持</button>
            </div>

            <div class="vs" id="vs"></div>

            <div class="songer_02">
                <span><img src="${players.get(1).picAddress}" height="300" width="256"/></span>
                <button class="Btn_02" id="Btn_02">支持</button>
            </div>

        </div>
    </div>
</c:if>

<canvas width="765" height="661" ></canvas>
    <div class="dapin">
        <ul class="player1">
            <span id="player1"><p id="p1">0</p></span>
            <em>
                <img src="${players.get(0).picAddress}" height="300" width="256"/>
                <h>${players.get(0).playername}</h>
            </em>
        </ul>
        <ul class="player2">
            <span id="player2"><p id="p2">0</p></span>
            <em>
                <img src="${players.get(1).picAddress}" height="300" width="256"/>
                <h>${players.get(1).playername}</h>
            </em>
        </ul>
    </div>
</body>
</html>