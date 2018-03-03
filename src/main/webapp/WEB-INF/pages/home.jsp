<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主页</title>
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
<a href="/bbs/login/logout">logout</a>丨<a href="/bbs/content/get/title">发帖</a>丨<a href="/bbs/home/get/search">搜索</a>
</div>
</header>
	<c:forEach items="${sections }" var="obj">
		<h3><a href="${pageContext.request.contextPath}/section/get/section/${obj.id }">${obj.sectionName }</a></h3>
		<c:forEach items="${obj.titles }" var="subObj">
		<h5><a href="${pageContext.request.contextPath }/content/get/content/${subObj.id}/1">${subObj.name }</a> ${subObj.author } <fmt:formatDate value="${subObj.createDate }" pattern="yyyy-MM-dd"/></h5>
		</c:forEach>
	</c:forEach>
</body>
<script type="text/javascript">

</script>
</html>