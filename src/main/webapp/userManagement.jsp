<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="textml; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-3.3.1.min.js"></script>
    <script>
        function del(id){
            var result=confirm("您确定要删除该用户？");//confirm设置弹出框
            if(result){
                //执行删除
                var url="${pageContext.request.contextPath}/user.do";
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
    <style>
ul, li, ol {
	list-style: none;
}

.waikuang {
	width: 1200px;
	height: 800px;
	background: url("/OnlineVoteSystem/images/apic22556_s.png");
	margin-left: auto;
	margin-right: auto;
}

.guan>ul>li {
	width:200px;
	height:50px;
	line-height:50px;
	margin-left:auto;
	margin-right:auto;
	text-align:center;
}

table {
	width: 600px;
	height: 50px;
	line-height: 30px;
	margin-left: auto;
	margin-right: auto;
}

thead>tr>th {
	width: 140px;
	height: 30px;
	border: black 1px solid;
	padding-left: 20px;
	padding-right: 20px;
}

tbody>tr {
	width: 600px;
	text-align: center;
}
</style>
</head>
<body>
<div class="waikuang">
<div class="guan">
	<ul>
	    <li>用户管理界面</li>
	    <li><a href="${pageContext.request.contextPath}/RandomData.do">添加用户</a></li>
		<li><a href="${pageContext.request.contextPath}/index.do">返回主界面</a></li>
	</ul>
</div>
<table >
    <thead >
    <tr>
        <th>用户id</th>
        <th>用户keywords</th>
        <th>用户类型</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
				<c:forEach items="${users}" var="item">
					<tr>
						<td>${item.userId}</td>
						<td>${item.keywords}</td>
						<c:choose>
							<c:when test="${item.type==1}">
								<td>管理员</td>
							</c:when>
							<c:when test="${item.type==0}">
							<td>普通观众</td>
							</c:when>
						</c:choose>
						<td><a href="#" onclick="del(${item.userId})">删除</a></td>
					</tr>
				</c:forEach>
	</tbody>

</table>
</div>
</body>
</html>
