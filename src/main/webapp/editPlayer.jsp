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
            var url = "${APP_PATH}/editPlayer/update.do";
            var formData = new FormData($("#form")[0]);
            $.ajax({
                url: url,
                data: formData,
                async: true,
                type: "post",
                processData:false,
                contentType:false,
                success: function (data) {
                    if(data=="1"){
                        console.log("over");
                        location.href="${APP_PATH}/PlayerManagement.do";
                    }
                }, error: function (data) {
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
            <td>候选人姓名：</td>
            <td><input type="text" id="playerName" name="playerName" value="${player.playerName}"/></td>
        </tr>
        <tr>
            <td>候选人已参与投票次数：</td>
            <td><input type="text"  name="num" id="num" value="${player.num}"/></td>
        </tr>
        <tr>
            <td>候选人大图片</td>
            <td><img src="${player.picAddress}" width="200" id="big"></td>
            <td>
                <input type="file" name="bigImg" id="bigImg" value="0"/>
            </td>
        </tr>

        <tr>
            <td>候选人小图片:</td>
            <td><img src="${player.smallImg}" width="100" id="small"></td>
            <td><input type="file" name="smallImg" id="smallImg"  value="0"/></td>
        </tr>
        <tr>
            <td>候选人出生日期：</td>
            <td><input type="text" name="dateOfBirth" id="dateOfBirth" value="${player.dateOfBirth}"/></td>
        </tr>
        <tr>
            <td>候选人性别：</td>
            <td>
                <select name="sex" style="width: 160px" id="sex">
                    <option value="1">女</option>
                    <option value="0">男</option>
                </select>
            </td>
        </tr>
        <tr>
        <tr>
            <td>候选人宣传语：</td>
            <td><input type="text" id="slogan" name="slogan" value="${player.slogan}"/></td>
        </tr>
        <tr>
            <td>候选人个人信息：</td>
            <td><input type="text" id="info" name="info" value="${player.info}"/></td>
        </tr>
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