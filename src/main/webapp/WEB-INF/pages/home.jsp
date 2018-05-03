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
<title>主页</title>
<link href="/bbs/css/bootstrap.min.css" rel="stylesheet">
<link href="/bbs/css/common.css" rel="stylesheet">
<script src="/bbs/js/jquery-3.2.1.min.js"></script>
<script src="/bbs/js/bootstrap.min.js"></script>
<style type="text/css">



#big {
	display: flex;
}

#callboard {
	width: 30%;
}

#main {
	width: 70%;
}

.table {
	width: 90%;
	margin-left: 10px
}

td {
	font-size: 13px;
}

.row {
	width: 90%
}

.col-sm:hover {
	cursor: pointer;
}

.logined {
	display: none
}

.fixed-table-body {
	overflow: hidden
}

.section-a {
	margin-left: 40px;
}

</style>
</head>
<body>
	
	<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
	<div class="navbar-header">
		<a class="navbar-brand" href="#">网络论坛</a>
	</div>
	<div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="/bbs/get/home"><span class="glyphicon glyphicon-home"></span>&nbsp;主页</a></li>
			<li><a href="/bbs/get/post"><span class="glyphicon glyphicon-pencil"></span>&nbsp;发帖</a></li>
		</ul>
	</div>
	<div class="pull-right" id="user-info">
			<img class="img-circle logined"
				src="/images/avatar/${bbs.url}"> <span id="username"
				class="logined"><a href="/bbs/get/users/${bbs.id }">${bbs.nickname }</a></span> <a class="logout"
				href="/bbs/get/login">登录</a><a class="logined" href="/bbs/logout">退出</a>
		</div>
	</div>
	</nav>
	



	<div id="big">
		<div id="main">
			<h5>版块</h5>
			<div class="row">
				<c:forEach items="${sectionSize }" var="item">
					<a class="section-a" href="/bbs/get/sections/${item.id }">${item.section }(${item.postAmount })</a>
				</c:forEach>

			</div>
			<br>
			<h5>热门话题</h5>
			<table id="hotpost" class="table table-striped table-hover">
				<thead>
					<tr>
						<th scope="col">话题</th>
						<th scope="col">作者</th>
						<th scope="col">发贴日期</th>
						<th scope="col">最近回复</th>
						<th scope="col">回复数</th>
						<th></th>
					</tr>
					<c:forEach items="${hotpost }" var="item">
						<tr>
							<td>
								<a href="/bbs/get/sections/${item.sectionId }"><span class="badge badge-pill badge-secondary">${item.section }</span></a>&emsp;
								<a href="/bbs/get/posts/${item.id }">${item.post }</a>	
							</td>
							<td><a href="/bbs/get/users/${item.userId }">${item.author }</a> </td>
							<td><fmt:formatDate value="${item.createDate }" type="both" /></td>
							<td><fmt:formatDate value="${item.lastReply }" type="both" /></td>
							<td>${item.replyAmount}</td>
						</tr>
					</c:forEach>
				</thead>
				<tbody>

				</tbody>
			</table>
			<br>
			<h5>热门回复</h5>
			<table id="hotpost" class="table table-striped table-hover" style="table-layout: fixed;">
				<thead>
					<tr>
						<th scope="col">话题</th>
						<th scope="col" style="width:300px">内容</th>
						<th scope="col">作者</th>
						<th scope="col">发贴日期</th>
						<th scope="col" style="text-align:center">顶贴数</th>
						<th></th>
					</tr>
					<c:forEach items="${hotReply }" var="item">
						<tr>
							<td>
								<a href="/bbs/get/posts/${item.id }">${item.post }</a>	
							</td>
							<td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;" title="${item.text }"><a href="/bbs/get/posts/${item.postId }/${item.id}">${item.text }</a></td>
							<td><a href="/bbs/get/users/${item.userId }">${item.author }</a></td>
							<td><fmt:formatDate value="${item.createDate }" type="both" /></td>
							<td style="text-align:center">${item.top}</td>
						</tr>
					</c:forEach>
				</thead>
				<tbody>

				</tbody>
			</table>
		</div>
		<div id="callboard">
			<h5>通告</h5>
			<table id="hotpost" class="table table-striped table-hover">
				<thead>
					<tr>
						<th scope="col">标题</th>
						<th scope="col">日期</th>
					</tr>
					<c:forEach items="${advice }" var="item">
						<c:if test="${item.status==9 }">
							<tr>
							<td><span class="glyphicon glyphicon-bullhorn"></span>&nbsp;<a href="/bbs/get/posts/${item.postId }">${item.post }</a></td>
							<td><fmt:formatDate value="${item.createDate }" type="both" /></td>
						</tr>						
						</c:if>
					</c:forEach>
				</thead>
				<tbody>

				</tbody>
			</table>
		</div>
	</div>


	<script type="text/javascript">
	
	</script>
	<script src="/bbs/js/common.js"></script>
</body>
</html>