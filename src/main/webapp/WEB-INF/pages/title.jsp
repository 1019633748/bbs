<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>title</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/bbs/js/common.js"></script>
<link rel="stylesheet" href="/bbs/css/common.css">
</head>
<body>
<header>
<span id="page-title-span">这里写标题</span><br>

<div id="user-div">
<span id="username-span">${bbs.name }</span>
<span id="idd" hidden>${bbs.id }</span>
<a id="login-logout" href=""></a>
<a href="/bbs/login/logout">fuck</a>
</div>
</header>
	<c:forEach items="${section.titles }" var="obj">
	<h4><a href="${pageContext.request.contextPath }/content/get/content/${obj.id}/1">${obj.id } ${obj.name}</a></h4>
	</c:forEach>
</body>
</html>