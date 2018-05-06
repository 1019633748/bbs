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
<title>搜索</title>
<link href="/bbs/css/bootstrap.min.css" rel="stylesheet">
<link href="/bbs/css/common.css" rel="stylesheet">
<script src="/bbs/js/jquery-3.2.1.min.js"></script>
<script src="/bbs/js/bootstrap.min.js"></script>
<script src="/bbs/js/bootstrap-table.js"></script>
<script src="/bbs/js/bootstrap-table-zh-CN.min.js"></script>
<style type="text/css">



#big1 {
	display: flex;
}

.result-div{
width:70%;
margin:0 auto
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
			<li><a href="/bbs/get/home"><span class="glyphicon glyphicon-home"></span>&nbsp;主页</a></li>
			<li><a href="/bbs/get/post"><span class="glyphicon glyphicon-pencil"></span>&nbsp;发帖</a></li>
			<li class="active"><a href="/bbs/get/search"><span class="glyphicon glyphicon-search"></span>&nbsp;搜索</a></li>
		</ul>
	</div>
	<div class="pull-right" id="user-info">
			<img class="img-circle logined"
				src="/images/avatar/${bbs.url}"> <span id="username"
				class="logined"><a href="/bbs/get/users/${bbs.id }">${bbs.nickname }</a></span> <a class="logout"
				href="/bbs/get/login" target="_blank">登录</a><a class="logined" href="/bbs/logout">退出</a>
		</div>
	</div>
	</nav>
	
	<div id="big">
	
		<div class="result-div">
		<h3>搜索用户</h3>
		<table id="user-table"></table>
		</div>
	
		<div class="result-div">
		<h3>搜索帖子</h3>
		<table id="post-table"></table>
		</div>
		
		<div class="result-div">
		<h3>搜索回复</h3>
		<table id="reply-table"></table>
		</div>
		
	</div>



	

	<script type="text/javascript">
	$('#post-table')
	.bootstrapTable(
			{
				url : '/bbs/get/admin/post',
				method : 'post',
				contentType : "application/x-www-form-urlencoded",
				pagination : true,
				pageSize : 5,
				pageNumber : 1,
				search : true,
				queryParams : queryParamsPost,//请求服务器时所传的参数
				sidePagination : 'server',//指定服务器端分页
				pageList : [ 5,10,15],
				searchAlign:'left',
				columns : [
						{
							field : 'post',
							title : '帖子',
							formatter:function(index,row,value){
								if(row.status==0||row.status==9){
								return "<a href='/bbs/get/posts/"+row.id+"'>"+row.post+"</a>"
								}else{
									return "内容违规"
								}
							}
						},{
							field : 'author',
							title : '作者',
							formatter:function(index,row,value){
								return "<a href='/bbs/get/users/"+row.userId+"'>"+row.author+"</a>"
							}
						},
						
						{
							field : 'createDate',
							title : '创建日期'
						},{
							field : 'lastReply',
							title : '最后回复'
						},{
							field : 'replyAmount',
							title : '回复量'
						},
						],
				silent : true, //刷新事件必须设置  
				sidePagination : 'server',//指定服务器端分页
			//responseHandler:responseHandler,//请求数据成功后，渲染表格前的方法
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
$('#reply-table')
.bootstrapTable(
		{
			url : '/bbs/get/admin/reply',
			method : 'post',
			contentType : "application/x-www-form-urlencoded",
			pagination : true,
			pageSize : 5,
			pageNumber : 1,
			queryParams : queryParamsPost,//请求服务器时所传的参数
			sidePagination : 'server',//指定服务器端分页
			pageList : [ 5,10,15],
			search : true,
			searchAlign:'left',
			columns : [
					{
						field : 'post',
						title : '贴名',
						formatter:function(index,row,value){
							return "<a href='/bbs/get/posts/"+row.id+"'>"+row.post+"</a>"
						}
					},
					{
						field : 'text',
						title : '内容', 
						cellStyle:{  
					        css:{"width":"600px"}  
					    },formatter:function(index,row,value){
					    	if(row.status==0||row.status==9){
					    	return "<a href='/bbs/get/posts/"+row.postId+"/"+row.id+"'>"+row.text+"</a>"
					    	}else{
					    		return "内容违规"
					    	}
					    }
					},{
						field : 'author',
						title : '作者',
						formatter:function(index,row,value){
							return "<a href='/bbs/get/users/"+row.userId+"'>"+row.author+"</a>"
						}
					},
					
					{
						field : 'createDate',
						title : '创建日期'
					},		
					 ],
			silent : true, //刷新事件必须设置  
			sidePagination : 'server',//指定服务器端分页
		//responseHandler:responseHandler,//请求数据成功后，渲染表格前的方法
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
$('#user-table')
.bootstrapTable(
		{
			url : '/bbs/get/admin/user',
			method : 'post',
			contentType : "application/x-www-form-urlencoded",
			pagination : true,
			pageSize : 5,
			pageNumber : 1,
			search : true,
			searchAlign:'left',
			queryParams : queryParamsPost,//请求服务器时所传的参数
			sidePagination : 'server',//指定服务器端分页
			pageList : [ 5,10,15],
			toolbar : '#toolbar',
			columns : [
					{
						field : 'nickname',
						title : '昵称',
						formatter:function(index,row,value){
							return "<a href='/bbs/get/users/"+row.id+"'>"+row.nickname+"</a>"
						}
					},
					{
						field : 'email',
						title : '邮件'
					},{
						field : 'sex',
						title : '性别',
						formatter : function(value, row, index){
							if(row.sex==0){return '女'}
							return '男'
						}
					},
					{
						field : 'sign',
						title : '签名'
					},
					{
						field : 'createDate',
						title : '注册日期'
					} ],
			silent : true, //刷新事件必须设置  
			sidePagination : 'server',//指定服务器端分页
		//responseHandler:responseHandler,//请求数据成功后，渲染表格前的方法
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
	<script src="/bbs/js/common.js"></script>
</body>
</html>