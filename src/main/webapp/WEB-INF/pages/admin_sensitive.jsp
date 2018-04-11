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
<title>特殊管理</title>
<link href="/bbs/css/bootstrap.min.css" rel="stylesheet">
<link href="/bbs/css/bootstrap-table.css" rel="stylesheet">
<link href="/bbs/css/common.css" rel="stylesheet">
<script src="/bbs/js/jquery-3.2.1.min.js"></script>
<script src="/bbs/js/bootstrap.min.js"></script>
<script src="/bbs/js/bootstrap-table.js"></script>
<script src="/bbs/js/bootstrap-table-zh-CN.min.js"></script>
<script src="/bbs/js/section-admin.js"></script>
<style type="text/css">
body, html {
	height: 90%;
	width: 100%;
}

#main-div {
	display: flex;
	width: 100%;
	height: 100%;
}

#left-div, #right-div {
	width: 85%
}

#left-div {
	height: 100%;
	width: 200px;
	background:#F8F8F8
}

.left-first {
	height: 30px;
	background: #F8F8F8;
	line-height: 30px;
	cursor: pointer;
	border-top: 1px solid #E7E7E7;
	user-select:none;
}

.left-first:hover {
	background: #EEE
}

.left-second {
	cursor: pointer;
	display: none;
}

li{
list-style:none;
}

li:hover {
	background: #EEE;
	user-select:none;
}


a:hover{
text-decoration:none;
color:#333
}
a{
color:#333
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
			<li class="active"><a href="/bbs/get/bg">后台管理</a></li>
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

	<div id="main-div">
		<div id="left-div">
			<div>
				<div class="left-first">&emsp;<a href="/bbs/get/bg">📟首页</a></div>
				<div class="left-first">&emsp;📊图表</div>
				<div class="left-second">
					<ul>
						<li><a href="/bbs/get/chart/user">用户</a></li>
						<li><a href="/bbs/chart/content">内容</a></li>
					</ul>
				</div>
				<div id="admin-div" class="left-first">&emsp;📇管理</div>
				<div class="left-second">
					<ul>
						<li><a href="/bbs/admin/section">版块管理</a></li>
						<li><a href="/bbs/admin/post">帖子管理</a></li>
						<li><a href="/bbs/admin/reply">回复管理</a></li>
						<li><a href="/bbs/admin/user">用户管理</a></li>
						<li><a href="/bbs/admin/sensitive">特殊管理</a></li>
						<li><a href="/bbs/admin/advice">通告管理</a></li>
					</ul>
				</div>
				<div class="left-first">&emsp;📌审核</div>
				<div class="left-second">
					<ul>
						<li><a href="/bbs/audit/avatar">头像审核</a></li>
						<li><a href="/bbs/audit/content">内容审核</a></li>
					</ul>
				</div>

			</div>
		</div>
		<div id="right-div">
			<h3 id="title-h3" align="center">特殊管理</h3>
			<table id="admin-table"></table>
			<div class="" id=""></div>
			<div id="add-section-div" class="add-div">
				特殊字：<input id="sensitive-input" class="">
				<button id="sensitive-btn" class="btn btn-sm btn-secondary"
					disabled="true" type="submit">提交</button>
				<span id="hint-span"></span>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(document).ready(function(){
		
		if ($('#username').html() != "") {
			$('.logined').show()
			$('.logout').hide()
		}
		
		$('#admin-div').css('background','#EEE')
		$('#admin-div').next().show()
		
		$('.left-first').click(function() {
			$(this).css('background', '#EEE')
			$('.left-first').not(this).css('background', '#F8F8F8')
			if ($(this).index() != 0) {
				if ($(this).next().css('display') == 'none') {
					$(this).next().show()
				} else {
					$(this).next().hide()
				}
			} 
		})

		$('#admin-table')
		.bootstrapTable(
				{
					url : '/bbs/get/admin/sensitive',
					method : 'post',
					contentType : "application/x-www-form-urlencoded",
					//striped : true,
					pagination : true,
					pageSize : 5,
					pageNumber : 1,
					search : true,
					//dataField: 'data',
					showRefresh:true,
					queryParams : queryParamsSection,//请求服务器时所传的参数
					sidePagination : 'server',//指定服务器端分页
					pageList : [ 5,10,15],
					columns : [
							{
								field : 'id',
								title : 'id'
							},
							{
								field : 'word',
								title : '版块名'
							},
							{
								field : 'status',
								title : '状态'
							},
							{
								filed : "tool",
								title : '操作',
								formatter : function(value, row, index) {
									if (row.status === 1) {
										var element = "<input class='btn btn-sm btn-danger' onclick='hideSensitive("
												+ row.id
												+ ","
												+ row.status
												+ ")' type='button' value='恢复'>"
									} else {
										var element = "<input class='btn btn-sm btn-danger' onclick='hideSensitive("
												+ row.id
												+ ","
												+ row.status
												+ ")' type='button' value='隐藏'>"
									}

									return element;
								}
							} ],
					silent : true, //刷新事件必须设置  
					sidePagination : 'server',//指定服务器端分页
				//responseHandler:responseHandler,//请求数据成功后，渲染表格前的方法
				});
		
		//请求服务数据时所传参数
		function queryParamsSection(params) {
			return {
				pageSize : params.limit, //每一页的数据行数，默认是上面设置的10(pageSize)
				pageIndex : params.offset / params.limit + 1, //当前页面,默认是上面设置的1(pageNumber)
				param : params.search //这里是其他的参数，根据自己的需求定义，可以是多个
			}
		}

		
		hideSensitive= function (id, value) {
			var handle = "show";
			if (value == 0) {
				handle = 'hide'
			}
			var url = '/bbs/' + handle + '/sensitives/' + id
			$.post(url, function(data) {
				$("#admin-table").bootstrapTable('refresh')
			})
		}

		$('#add-row-btn').click(function() {
			if ($('#title-h3').html() == '版块管理') {

			}
		})
		
		$('#sensitive-input').bind(
					'input propertychange',
					function() {
						if ($(this).val() != '') {
							$.post('/bbs/verify/sensitive/' + $(this).val(),
									function(data) {
										if (data == 0) {
											$('#hint-span').html('可以添加')
											$('#sensitive-btn').prop('disabled',
													false)
										} else {
											$('#hint-span').html('该字段已存在')
											$('#sensitive-btn')
													.prop('disabled', true)
										}
									})
						} else {
							$('#sensitive-btn').prop('disabled', true)
						}

						$('#sensitive-btn').unbind('click')
						$('#sensitive-btn').click(
								function() {
									$.post('/bbs/post/sensitive', 'word='
											+ $('#sensitive-input').val(), function(
											data) {
										console.log(data)
										$("#admin-table").bootstrapTable('refresh')
									})

								})

					})

		
	})
	</script>
</body>

</html>
