<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<html>
<html lang="en">
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
</head>
<body>
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
        </ul>
        <form class="form-inline mt-2 mt-md-0" action="${APP_PATH}/signOut.do">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Sign out</button>
        </form>
    </div>
</nav>
<main role="main" class="container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading">Panel heading</div>
        <div class="panel-body">
            <p>Some default panel content here. Nulla vitae elit libero, a pharetra augue. Aenean lacinia bibendum nulla sed
                consectetur. Aenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis vestibulum. Nullam id dolor
                id nibh ultricies vehicula ut id elit.</p>
        </div>

        <!-- Table -->
        <table class="table table-hover table-bordered">
            <thead>
            <tr>
                <td>SessionId</td>
                <td>Session State</td>
                <td>Create</td>
                <td>Start</td>
                <td>End</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${allSession}" var="session">
                <tr>
                    <td>${session.sessionId}</td>
                    <td>${session.state}</td>
                    <td>${session.create}</td>
                    <c:choose>
                        <c:when test="${empty session.start}">
                            <td>未开始</td>
                        </c:when>
                        <c:otherwise>
                            <td>${session.start}</td>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${empty session.end}">
                            <td>未结束</td>
                        </c:when>
                        <c:otherwise>
                            <td>${session.end}</td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div>
        <p>
            <a href="${APP_PATH}/addSession.do"><button class="btn btn-primary btn-block" type="button">添加投票活动</button></a>
        </p>
    </div>
</main>
</body><!-- <script type="text/javascript" src="${APP_PATH}/script/jquery-3.3.1.min.js"></script> -->
<script src="${APP_PATH}/plugins/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${APP_PATH}/js/pkManage.js"></script>
</html>