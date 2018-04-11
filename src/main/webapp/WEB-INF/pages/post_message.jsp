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
<title>发帖</title>
<link href="/bbs/css/bootstrap.min.css" rel="stylesheet">
<link href="/bbs/css/common.css" rel="stylesheet">
<script src="/bbs/js/jquery-3.2.1.min.js"></script>
<script src="/bbs/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/bbs/js/wangEditor.js"></script>
<style type="text/css">

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
			<li><a href="/bbs/get/home">主页</a></li>
			<li  class="active"><a href="/bbs/get/post">发帖</a></li>
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

	<div class="input-group mb-3">
		<div class="input-group-prepend">
			<label class="input-group-text" for="inputGroupSelect01">版块</label>
		</div>
		<select name="sectionId" class="form-control" id="inputGroupSelect01">
			<option selected>选择版块...</option>
		</select>
		
			<input type="text" class="form-control" id="post-input"
				placeholder="标题">
		
	</div>
	<div id="editor"></div>
	<button id="submit_btn">提交</button>





	<script type="text/javascript">
	
	function isLogin(){
		if($('#username').html()==""){
			return false
		}else{
			return true
		}
	}

	
	if (isLogin()) {
		$('.logined').show()
		$('.logout').hide()
	}
	
	
		$.post('/bbs/get/sections', function(data) {
			for (var i = 0; i < data.length; i++) {
				if (data[i].status == 0) {
					$('select').append(
							"<option value='"+data[i].id+"'>" + data[i].section
									+ "</option>")
				}
			}
		})

		var E = window.wangEditor
		var editor = new E('#editor')
		editor.create()

		$('#submit_btn').click(
				function() {
					var data = "post.sectionId=" + $('select').val()
							+ "&post.post=" + $('#post-input').val()
							+ "&reply.reply=" + $('.w-e-text').html()
					$.post('/bbs/post/post', data, function(data) {
						if (data == "SUC") {
							alert("发帖成功")
						} else {
							alert("发帖失败")
						}
					})
				})
	</script>

</body>
</html>