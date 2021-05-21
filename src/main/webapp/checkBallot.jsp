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
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <meta http-equiv="Content-Type" content="multipart/form-data; text/html; charset=UTF-8">
    <title>WXY投票管理</title>

    <!-- Bootstrap core CSS -->
    <script src="${APP_PATH}/script/jquery-3.3.1.min.js"></script>
    <link href="${APP_PATH }/static/bootstrap-4.5.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="${APP_PATH }/static/bootstrap-4.5.3-dist/js/bootstrap.min.js"></script>

    <style type="text/css">
        img[src=""],img:not([src]){
            opacity:0;

        }
    </style>
    <script type="text/javascript">
        $(function(){
            //显示更换后的图片
            $("#ballot").change(function() {
                $("#ballotImg").attr("src",URL.createObjectURL($(this)[0].files[0]));
            });
        });
    </script>
    <script type="text/javascript">
        function check() {
            var url = "${APP_PATH}/checkBallot/check.do";
            var formData = new FormData($("#form")[0]);
            formData.append("userId",${sessionCount.userId});
            formData.append("playerId",${sessionCount.playerId});
            formData.append("sessionId",${sessionCount.sessionId});
            $.ajax({
                url: url,
                data: formData,
                async: true,
                type: "post",
                processData:false,
                contentType:false,
                success: function (data) {
                    if(data=="1"){
                        alert("校验成功");
                        document.getElementById('success').style.display='block';
                        document.getElementById('fail').style.display='none';

                    }else{
                        alert("校验失败");
                        document.getElementById('fail').style.display='block';
                        document.getElementById('success').style.display='none';

                    }
                }, error: function (data) {
                    alert("出错")
                }
            })
        }
    </script>
</head>
<body>
<c:if test="${sessionScope.curUser.level==1}">
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <a class="navbar-brand" href="#">WXY政务投票</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
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
<br><br><br>
<form id="form" enctype="multipart/form-data" method="post">
    <table>
        <tr>
            <td>上传选票</td>
            <td><img src="" alt="Ballot" width="200" id="ballotImg"></td>
            <td>
                <input type="file" name="ballot" id="ballot" value="0"/>
            </td>
            <td><button class="btn btn-success" onclick="check()" type="button">校验</button></td>
        </tr>
        <tr>
            <td><br></td>
        </tr>
        <tr >
            <td><span>检验后的投票结果是：</span></td>
            <td>
                <div class="durl"  contenteditable="true">
                         <span style="display: none" id="success"  ><span style="color: green;">数字签名验证通过！</span></span>
                         <span style="display: none" id="fail"  ><span style="color: red;">数字签名验证失败！</span></span>
                </div>
            </td>

        </tr>

    </table>

</form>
</body>
</html>