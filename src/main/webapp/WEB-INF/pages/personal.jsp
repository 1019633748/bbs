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
<title>${user.nickname }的主页</title>
<link href="/bbs/css/bootstrap.min.css" rel="stylesheet">
<link href="/bbs/css/common.css" rel="stylesheet">
<link href="/bbs/css/bootstrap-table.css" rel="stylesheet">
<script src="/bbs/js/jquery-3.2.1.min.js"></script>
<script src="/bbs/js/bootstrap.min.js"></script>
<script src="/bbs/js/bootstrap-table.js"></script>
<script src="/bbs/js/bootstrap-table-zh-CN.min.js"></script>
<script src="/bbs/js/section-admin.js"></script>
<script src="/bbs/js/jquery.serializejson.js"></script>
<style type="text/css">


.img-circle {
	border-radius: 50%;
	height: 40px;
	width: 40px;
	margin-right: 10px;
}

#avatar {
	border-radius: 50%;
	height: 200px;
	width: 200px;
}

#basic-info {
	display: flex;
	border: 1px solid black;
	justify-content: space-around;
	align-items: center;
}

#basic-info-div {
	width: 30%;
}

#post-reply-attention {
	display: flex;
	justify-content: space-between;
}

.logined {
	display: none
}

#user-info-sub {
	display: none
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
			<li class=""><a href="/bbs/get/home">主页</a></li>
			<li><a href="/bbs/get/post">发帖</a></li>
				<li class="nav-item active"><a class="nav-link active" href="#">${user.nickname }的个人信息</a>
				<c:if test="${user.id!=bbs.id }">
					<c:choose>
						<c:when test="${isAttention==0 }">				
						<li class="nav-item"><a class="nav-link active"
							href="/bbs/attention/users/${user.id }">关注</a></li>
							</c:when>
							<c:otherwise>
							<li class="nav-item"><a class="nav-link active"
							href="/bbs/unfollow/users/${user.id }">取消关注</a></li>
							</c:otherwise>
					</c:choose>
				</c:if></li>
		</ul>
	</div>
	<div class="pull-right" id="user-info">
			<img class="img-circle img-thumbnail logined"
				src="/images/avatar/male.png"> <span id="username"
				class="logined">${bbs.nickname }</span> <a class="logout"
				href="/bbs/get/login">登录</a><a class="logined" href="/bbs/logout">退出</a>
		</div>
	</div>
	<div id="basic-info">
		<img src="/images/avatar/male.png" class="img-thumbnail" id="avatar"
			alt="...">
		<div id="basic-info-div">
			<input id="user-id" hidden value="${user.id }">
			<form action="/bbs/put/user" id="user-form" method="post">
				<div class="form-group row">
					<label for="nickname" class="col-sm-2 col-form-label">昵称</label>
					<div class="col-xs-3">
						<input type="text" disabled class="form-control" id="nickname"
							value="${user.nickname }" style="width:200px">
					</div>
				</div>
				<div class="form-group row">
					<label for="" class="col-lg-2 col-form-label">邮箱</label>
					<div class="col-xs-3">
						<input name="email" type="text" disabled
							class="user-info form-control" id="" value="${user.email }" style="width:200px" onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;">
					</div>
				</div>
				<div class="form-group row">
					<label for="" class="col-lg-2 col-form-label">性别</label> <input
						id="sex-input" hidden value="${user.sex }">
					<div class="col-xs-3 user-sex-div">
						<c:choose>
							<c:when test="${user.sex==0 }">
								<input type="text" disabled class="form-control" id="" value="女" style="width:200px">
							</c:when>
							<c:otherwise>
								<input type="text" disabled class="form-control" id="" value="男" style="width:200px">
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="form-group row">
					<label for="" class="col-lg-2 col-form-label">签名</label>
					<div class="col-xs-3">
						<input name="sign" type="text" disabled
							class="user-info form-control" id="" value="${user.sign }" onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;"  style="width:200px">
					</div>
				</div>
				<input id="user-info-sub" class="btn btn-primary btn-sm"
					type="submit" value="提交">
			</form>
		</div>
	</div>
	<div id="post-reply-attention">
		<div id="psot">
			<h5>最近发帖</h5>
			<table id="table-post" class="">
			</table>
		</div>
		<div id="reply">
		<h5>最近回复</h5>
			<table id="table-reply" class="">
			</table>
		</div>
		<div id="attention">关注(20)丨被关注(10)</div>
	</div>


	<script type="text/javascript">
		if ($('#username').html() != "") {
			$('.logined').show()
			$('.logout').hide()
		}

		if ($('#username').html() == $('#nickname').val()) {
			$('.user-info').prop('disabled', false)
			$('.user-sex-div').children().remove()
			$('.user-sex-div')
					.append(
							"男&nbsp;<input class='user-info' name='sex' type='radio' value='1'>")
			$('.user-sex-div')
					.append(
							"&nbsp;女&nbsp;<input class='user-info' name='sex' type='radio' value='0'>")
			var sex = $('#sex-input').val()
			if (sex == '1') {
				$(':radio:eq(0)').prop('checked', 'checked')
			} else {
				$(':radio:eq(1)').prop('checked', 'checked')
			}
		}

		var oldInfo = JSON.stringify($('#user-form').serializeJSON())
		$('.user-info').bind('input propertychange', function() {
			var newInfo = JSON.stringify($('#user-form').serializeJSON())
			if (oldInfo == newInfo) {
				$('#user-info-sub').hide()
			} else {
				$('#user-info-sub').show()
			}
		})
		$(':radio').click(function() {
			var newInfo = JSON.stringify($('#user-form').serializeJSON())
			if (oldInfo == newInfo) {
				$('#user-info-sub').hide()
			} else {
				$('#user-info-sub').show()
			}
		})
	</script>
	<script type="text/javascript">
	$('#table-post')
	.bootstrapTable(
			{
				url : '/bbs/get/posts/'+$('#user-id').val(),
				method : 'post',
				contentType : "application/x-www-form-urlencoded",
				pagination : true,
				pageSize : 5,
				pageNumber : 1,
				search : true,
				queryParams : queryParamsPost,//请求服务器时所传的参数
				sidePagination : 'server',//指定服务器端分页
				pageList : [ 5, 10, 15],
				silent : true, //刷新事件必须设置  
				sidePagination : 'server',//指定服务器端分页
				columns : [
						{
							field : 'section',
							title : '版块',
							formatter:function(index,row,value){
								return "<a href='/bbs/get/sections/"+row.sectionId+"'>"+row.section+"</a>";
							}
						},
						{
							field : 'post',
							title : '帖子',
							formatter:function(index,row,value){
								return "<a href='/bbs/get/posts/"+row.id+"'>"+row.post+"</a>";
							}
						},{
							field : 'createDate',
							title : '发帖日期'
						}],
			});
	
	//请求服务数据时所传参数
	function queryParamsPost(params) {
		return {
			pageSize : params.limit, //每一页的数据行数，默认是上面设置的10(pageSize)
			pageIndex : params.offset / params.limit + 1, //当前页面,默认是上面设置的1(pageNumber)
			param : params.search //这里是其他的参数，根据自己的需求定义，可以是多个
		}
	}
	
	
	</script>
	
		<script type="text/javascript">
	$('#table-reply')
	.bootstrapTable(
			{
				url : '/bbs/get/replys/'+$('#user-id').val(),
				method : 'post',
				contentType : "application/x-www-form-urlencoded",
				pagination : true,
				pageSize : 5,
				pageNumber : 1,
				search : true,
				queryParams : queryParamsPost,//请求服务器时所传的参数
				sidePagination : 'server',//指定服务器端分页
				pageList : [ 5, 10, 15],
				silent : true, //刷新事件必须设置  
				sidePagination : 'server',//指定服务器端分页
				columns : [
						{
							field : 'post',
							title : '帖子',
							formatter:function(index,row,value){
								return "<a href='/bbs/get/posts/"+row.postId+"'>"+row.post+"</a>";
							}
						},{
							field : 'reply',
							title : '回复',
							formatter:function(index,row,value){
								var reTag = /<(?:.|\s)*?>/g;
								return row.reply.replace(reTag,'').substring(0,15)+'...'
							}
						},{
							field : 'createDate',
							title : '回复日期'
						}],
			})
	//请求服务数据时所传参数
	function queryParamsPost(params) {
		return {
			pageSize : params.limit, //每一页的数据行数，默认是上面设置的10(pageSize)
			pageIndex : params.offset / params.limit + 1, //当前页面,默认是上面设置的1(pageNumber)
			param : params.search //这里是其他的参数，根据自己的需求定义，可以是多个
		}
	}
	
	</script>
</body>
</html>
