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
    <meta http-equiv="Content-Type" content=" text/html; charset=UTF-8">
    <title>WXY投票管理</title>

    <!-- Bootstrap core CSS -->
    <script type="text/javascript" src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>

    <link href="${APP_PATH }/static/bootstrap-4.5.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="${APP_PATH }/static/bootstrap-4.5.3-dist/js/bootstrap.min.js"></script>

    <script type="application/javascript">
        //url下载图片路径  filename自定义文件名字
        function download(url, filename) {

            getBlob(url, function (blob) {
                saveAs(blob, filename);
            });

        };

        function getBlob(url, cb) {
            var xhr = new XMLHttpRequest();
            xhr.open('GET', url, true);
            xhr.responseType = 'blob';
            xhr.onload = function () {
                if (xhr.status === 200) {
                    cb(xhr.response);
                }
            };
            xhr.send();
        }

        function saveAs(blob, filename) {
            if (window.navigator.msSaveOrOpenBlob) {
                navigator.msSaveBlob(blob, filename);
            } else {
                var link = document.createElement('a');
                var body = document.querySelector('body');
                link.href = window.URL.createObjectURL(blob);
                link.download = filename;
                // fix Firefox
                link.style.display = 'none';
                body.appendChild(link);
                link.click();
                body.removeChild(link);
                window.URL.revokeObjectURL(link.href);
            };

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
<form id="form" method="post">
    <table>
        <tr>
            <td>票选凭据</td>
            <td><img src="${qrSrc}" width="200" ></td>
<%--            <td><button class="btn btn-primary">保存</button></td>--%>
            <td><button class="btn btn-primary" onclick=" download('${qrSrc}','ballot')">保存</button>
            <a class="btn btn-success" href="${APP_PATH}/checkBallot.do">验票</a>
            </td>
        </tr>
        <tr>
            <td>候选人ID：</td>
            <td><input type="text" id="playerId" name="playerId" value="${player.playerId}"/></td>
        </tr>
        <tr>
            <td>候选人姓名：</td>
            <td><input type="text" id="playerName" name="playerName" value="${player.playerName}"/></td>
        </tr>
        <tr>
            <td>候选人大图片</td>
            <td><img src="${player.picAddress}" width="200" id="big"></td>
        </tr>
        <tr>
            <td>选民ID：</td>
            <td><input type="text" id="userId" name="userId" value="${sessionCount.userId}"/></td>
        </tr>
        <tr>
            <td>活动ID：</td>
            <td><input type="text" id="sessionId" name="userId" value="${sessionCount.sessionId}"/></td>
        </tr>

    </table>
</form>

<c:if test="${empty sessionCount}">
    <div class="jumbotron bg-white">
        <h1>暂无选票！</h1>
<%--        <img src="${APP_PATH}/images/QRcode-example.png" width="200" >--%>
    </div>
</c:if>
</body>
</html>