<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>后台管理系统</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>

<style type="text/css">
body, html {
	height: 100%;
	width: 100%;
	padding: 0;
	margin: 0;
}

header {
	height: 10%;
	width: 100%;
}

#big-div {
	height: 80%;
	width: 100%;
	display: flex;
	justify-content: space-between;
}

#left-div {
	height: 100%;
	width: 15%;
	border: 1px solid black;
}

#main-div {
	height: 100%;
	width: 80%;
	border: 1px solid black;
}

.manager {
	cursor: pointer;
}

.info-list-div {
	border: 1px solid black;
	height: 80%;
	width: 95%;
	display: none;
}
</style>
</head>
<body>
	<header>
	<h3>这是标题</h3>
	</header>
	<div id="big-div">
		<div id="left-div">
			<span id="section-manager" class="manager">版块管理</span>
			<div id="section-manager-div"></div>
			<span id="title-manager" class="manager">话题管理</span>
			<div id="title-manager-div"></div>
			<span id="content-manager" class="manager">内容管理</span>
			<div id="content-manager-div"></div>
			<span id="user-manager" class="manager">用户管理</span>
			<div id="user-manager-div"></div>
		</div>
		<div id="main-div">
			<div id="section-div" class="info-list-div">
				1
				<table class="info-table">
					<tr>
						<th>编号</th>
						<th>版块</th>
						<th>操作</th>
					</tr>
				</table>
			</div>
			<div id="title-div" class="info-list-div">
				2
				<table class="info-table">
					<tr>
						<th>编号</th>
						<th>版块</th>
						<th>作者</th>
						<th>创建日期</th>
						<th>操作</th>
					</tr>
				</table>
			</div>
			<div id="content-div" class="info-list-div">
				3
				<table class="info-table">
					<tr>
						<th>编号</th>
						<th>内容</th>
						<th>创建日期</th>
						<th>操作</th>
					</tr>
				</table>
			</div>
			<div id="user-div" class="info-list-div">
				<table class="info-table">
					<tr>
						<th>编号</th>
						<th>用户名</th>
						<th>性别</th>
						<th>邮箱</th>
						<th>电话</th>
						<th>创建日期</th>
						<th>修改日期</th>
						<th>签名</th>
						<th>操作</th>
					</tr>
				</table>
			</div>
		</div>
	</div>



	<script type="text/javascript">
		$(document)
				.ready(
						function() {

							$('.manager')
									.click(
											function() {
												var flag = $(this).prop('id')
												if (flag == "section-manager") {
													$('.info-list-div').hide()
													$('#section-div').show()
													$('#section-div').children('.info-table').children().children().filter(':gt(0)').remove()													
													$
															.post(
																	'/bbs/bg/get/section',
																	function(
																			data) {
																		for (var i = 0; i < data.length; i++) {
																			$(
																					'#section-div')
																					.children(
																							'.info-table')
																					.append(
																							"<tr>"
																									+ "<td>"
																									+ data[i].id
																									+ "</td>"
																									+ "<td>"
																									+ data[i].sectionName
																									+ "</td>"
																									+ "<td><button>修改</button></td>"
																									+ "</tr>")
																		}
																	})

												} else if (flag == "title-manager") {
													$('.info-list-div').hide()
													$('#title-div').show()
													$('#title-div').children('.info-table').children().children().filter(':gt(0)').remove()
													$
															.post(
																	'/bbs/bg/get/title',
																	function(
																			data) {
																		for (var i = 0; i < data.length; i++) {
																			$(
																					'#title-div')
																					.children(
																							'.info-table')
																					.append(
																							"<tr>"
																									+ "<td>"
																									+ data[i].id
																									+ "</td>"
																									+ "<td>"
																									+ data[i].name
																									+ "</td>"
																									+ "<td>"
																									+ data[i].author
																									+ "</td>"
																									+ "<td>"
																									+ data[i].createDate
																									+ "</td>"
																									+ "<td><button>封禁</button>&nbsp;<button>删除</button></td>"
																									+ "</tr>")
																		}
																	})
												} else if (flag == "content-manager") {
													$('.info-list-div').hide()
													$('#content-div').show()
													$('#content-div').children('.info-table').children().children().filter(':gt(0)').remove()
													$
															.post(
																	'/bbs/bg/get/content',
																	function(
																			data) {
																		for (var i = 0; i < data.length; i++) {
																			$(
																					'#content-div')
																					.children(
																							'.info-table')
																					.append(
																							"<tr>"
																									+ "<td>"
																									+ data[i].id
																									+ "</td>"
																									+ "<td width='400px'>"
																									+ data[i].content
																									+ "</td>"
																									+ "<td>"
																									+ data[i].createDate
																									+ "</td>"
																									+ "<td><button>封禁</button>&nbsp;<button>删除</button></td>"
																									+ "</tr>")
																		}
																	})
												} else if (flag == "user-manager") {
													$('.info-list-div').hide()
													$('#user-div').show()
														$('#user-div').children('.info-table').children().children().filter(':gt(0)').remove()
													$
															.post(
																	'/bbs/bg/get/users',
																	function(
																			data) {
																		for (var i = 0; i < data.length; i++) {
																			$(
																					'#user-div')
																					.children(
																							'.info-table')
																					.append(
																							"<tr>"
																									+ "<td>"
																									+ data[i].id
																									+ "</td>"
																									+ "<td>"
																									+ data[i].name
																									+ "</td>"
																									+ "<td>"
																									+ data[i].sex
																									+ "</td>"
																									+ "<td>"
																									+ data[i].email
																									+ "</td>"
																									+ "<td>"
																									+ data[i].phone
																									+ "</td>"
																									+ "<td>"
																									+ data[i].createDate
																									+ "</td>"
																									+ "<td>"
																									+ data[i].updateDate
																									+ "</td>"
																									+ "<td>"
																									+ data[i].sign
																									+ "</td>"
																									+ "<td><button>封禁</button>&nbsp;<button>删除</button></td>"
																									+ "</tr>")
																		}
																	})
												}

											})

						})
	</script>
</body>
</html>