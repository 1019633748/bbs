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
<title>${section.section }</title>
<link href="/bbs/css/bootstrap.min.css" rel="stylesheet">
<link href="/bbs/css/common.css" rel="stylesheet">
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
			<li class=""><a href="/bbs/get/home"><span class="glyphicon glyphicon-home"></span>&nbsp;主页</a></li>
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

	<nav aria-label="breadcrumb">
	<ol class="breadcrumb">
		<li class="breadcrumb-item">${section.section }版块&nbsp;(${total })</li>
	</ol>
	</nav>
		<input id="section-id" type="hidden" value="${section.id }"/>
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
			<%-- <c:forEach items="${hot }" var="item">
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
			</c:forEach> --%>
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
		
		<div class="container" style="width:400px">
		<input type="button"  class='btn btn-link' value="上一页" onclick="prevPage(${pageNum-1 })"/> <span>共&nbsp;${totalPage }&nbsp;页，当前&nbsp;<select>
			<c:forEach begin="1" end="${totalPage }" var="i">
				<c:choose>
					<c:when test="${pageNum==i }">
						<option selected value="${i }">第${i }页</option>
					</c:when>
					<c:otherwise>
						<option value="${i }">第${i }页</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select></span>&nbsp;<input class='btn btn-link' type="button" value="下一页" onclick="nextPage(${pageNum+1 })"/>
		</div>
		
		
	</div>
	
	<script src="/bbs/js/common.js"></script>
	<script type="text/javascript">
		
	var total=${total}
	var url = '/bbs/get/sections/'+$('#section-id').val()
	$('select').change(function(){
		location.href=url+"?pageNum="+$(this).val()
	})	
		
	    function nextPage(pageNum){
		var pageIndex = pageNum;
		pageIndex = (pageIndex-1)*10>=total?pageIndex-1:pageIndex
	    	location.href=url+"?pageNum="+pageIndex
	    }
		
		function prevPage(pageNum){
			var pageIndex = pageNum;
			pageIndex = pageIndex==0?1:pageIndex
	    	location.href=url+"?pageNum="+pageIndex
	    }
	
	</script>
</body>
</html>