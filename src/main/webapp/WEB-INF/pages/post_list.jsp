<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>注册</title>
<link href="/bbs/css/bootstrap.min.css" rel="stylesheet">
<script src="/bbs/js/jquery-3.2.1.min.js"></script>
<script src="/bbs/js/common.js"></script>
<script src="/bbs/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/bbs/js/wangEditor.js"></script>
<style type="text/css">
#user-info {
	margin-right: 40px
}

.img-circle {
	border-radius: 50%;
	height: 40px;
	width: 40px;
	margin-right: 10px;
}

.container {
	width: 70%;
}

.replier, .floor {
	width: 300px;
	height: 40px;
	line-height: 40px
}

.reply {
	height: 150px;
	border: 1px solid black;
	display: flex;
	flex-direction: column;
	justify-content: space-between;
}

.reply-bottom {
	display: flex;
	justify-content: space-between;
}

.top, .down, .reply-span {
	cursor: pointer;
}

.logined {
	display: none
}
</style>
</head>
<body>
	<nav class="navbar navbar-default" role="navigation" style="margin-bottom:0">
	<div class="container-fluid">
	<div class="navbar-header">
		<a class="navbar-brand" href="#">网络论坛</a>
	</div>
	<div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="/bbs/get/home">主页</a></li>
			<li><a href="/bbs/get/post">发帖</a></li>
		</ul>
	</div>
	<div class="pull-right" id="user-info">
			<img class="img-circle img-thumbnail logined"
				src="/images/avatar/male.png"> <span id="username"
				class="logined">${bbs.nickname }</span> <a class="logout"
				href="/bbs/get/login">登录</a><a class="logined" href="/bbs/logout">退出</a>
		</div>
	</div>
	</nav>

	<nav aria-label="breadcrumb">
	<ol class="breadcrumb">
		<li class="breadcrumb-item">${section.section }版块</li>
	</ol>
	</nav>

	<div class="container">
		<table class="table">
			<tr>
				<th scope="col">话题</th>
				<th scope="col">作者</th>
				<th scope="col">发贴日期</th>
				<th scope="col">最近回复</th>
				<th scope="col">回复数</th>
				<th></th>
			</tr>
			<c:forEach items="${hot }" var="item">
				<tr>
					<td><a href="/bbs/get/posts/${item.id }">${item.post }</a></td>
					<td><a href="/bbs/get/users/${item.userId }">${item.author }</a></td>
					<td><fmt:formatDate value="${item.createDate }" type="both" /></td>
					<td><fmt:formatDate value="${item.lastReply }" type="both" /></td>
					<td>${item.replyAmount }</td>
					<td><span class="badge badge-secondary">热门</span></td>
				</tr>
			</c:forEach>
			<c:forEach items="${last }" var="item">
				<tr>
					<tr>
					<td><a href="/bbs/get/posts/${item.id }">${item.post }</a></td>
					<td><a href="/bbs/get/users/${item.userId }">${item.author }</a></td>
					<td><fmt:formatDate value="${item.createDate }" type="both" /></td>
					<td><fmt:formatDate value="${item.lastReply }" type="both" /></td>
					<td>${item.replyAmount }</td>
					<td><span class="badge badge-secondary">最新</span></td>
				</tr>
			</c:forEach>
			<c:forEach items="${post }" var="item">
				<tr>
					<td><a href="/bbs/get/posts/${item.id }">${item.post }</a></td>
					<td><a href="/bbs/get/users/${item.userId }">${item.author }</a></td>
					<td><fmt:formatDate value="${item.createDate }" type="both" /></td>
					<td><fmt:formatDate value="${item.lastReply }" type="both" /></td>
					<td>${item.replyAmount }</td>
				</tr>
			</c:forEach>
		</table>

	</div>

	<script type="text/javascript">
		if ($('#username').html() != "") {
			$('.logined').show()
			$('.logout').hide()
		}
	</script>
</body>
</html>